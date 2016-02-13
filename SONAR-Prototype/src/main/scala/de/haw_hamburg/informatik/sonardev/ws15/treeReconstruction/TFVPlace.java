package de.haw_hamburg.informatik.sonardev.ws15.treeReconstruction;

import de.haw_hamburg.informatik.sonardev.ws15.data.AssignedTask;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A place in the team formation view. Can be linked to a single parent and multiple child transitions.
 * Each place represents an assigned task. This task may be an initial task, or it results from an operation (its parent).
 * Multiple operations (its children) can fulfil the task.
 * Iterating over place means iterating over its subtree (itself and all of its descendant places).
 */
public class TFVPlace extends TFVNode<TFVTransition> implements Iterable<TFVPlace> {
    /**
     * NOTE Do not implement equals, the TFV currently is a doubly linked structure, so the reference matters.
     * One might argue that this is a wrong model because of uniqueness; however, we did not discuss the implications
     * of uniqueness in the TFV so far.
     */

	/**
	 * Assigned task that the place represents.
	 */
	private final AssignedTask task;

    /**
     * Currently selected transition.
     */
    private TFVTransition selectedTransition;

    public TFVPlace(TFVTransition parent, List<TFVTransition> children, AssignedTask task) {
        super(parent, children);
        this.task = task;
    }

    /**
     * Creates an unlinked place.
     * @param task The represented assigned task
     */
    public TFVPlace(AssignedTask task) {
        this(null, null, task);
    }

    /**
     * Attempt to merge a new place's subtree into this place's subtree.
     * @param newPlace The new place that shall be merged in
     * @return true iff the merge was possible
     */
    public boolean mergeIntoSubtree(TFVPlace newPlace) {
        for (TFVPlace subtreeMember : this) {
            //If possible, unify the new place with a subtree member
            if (subtreeMember.isUnifiableWith(newPlace)) {
                subtreeMember.unifyWith(newPlace);
                return true;
            }
        }
        //If not, return false
        return false;
    }

    /**
     * @param otherPlace A place that could be unified with this place
     * @return true iff the places are unifiable
     */
    public boolean isUnifiableWith(TFVPlace otherPlace) {
        return otherPlace.getTask().equals(task);
    }

    /**
     * Unifies a place with this place, as well as their subtrees.
     * @param otherPlace The place to unify with
     */
    public void unifyWith(TFVPlace otherPlace) {
        boolean unified;
        //Iterate over all new children
        for (TFVTransition newChild : otherPlace.getChildren()) {
            unified = false;
            for (TFVTransition oldChild : children) {
                //If the new child can be unified with an old child, unify them
                if (oldChild.isUnifiableWith(newChild)) {
                    oldChild.unifyWith(newChild);
                    unified = true;
                    break;
                }
            }
            //If the new child could not be unified, add it
            if (!unified) {
                addChild(newChild);
                //Link the new child to its new parent
                newChild.setParent(this);
            }
        }
        TFVTransition otherSelectedTransition = otherPlace.getSelectedTransition();
        if (selectedTransition == null && otherSelectedTransition != null) {
            for (TFVTransition child : children) {
                if (child.isUnifiableWith(otherSelectedTransition)) {
                    setSelectedTransition(child);
                    break;
                }
            }
        }
    }

    /**
     * @return The assigned task that this place represents
     */
    public AssignedTask getTask() {
        return task;
    }

    /**
     * @return The currently selected transition
     */
    public TFVTransition getSelectedTransition() {
        return selectedTransition;
    }

    /**
     * @param selectedTransition The new selected transition
     */
    public void setSelectedTransition(TFVTransition selectedTransition) {
        this.selectedTransition = selectedTransition;
    }

    //TODO document
    public boolean hasExecutableLeafSelected() {
        if (selectedTransition != null) {
            return selectedTransition.isExecutableLeaf();
        }
        return false;
    }

    /**
     * @return An independent copy of this place and its subtree (but without parent)
     */
    public TFVPlace copy() {
        TFVPlace copy = new TFVPlace(task);
        for (TFVTransition child : children) {
            TFVTransition childCopy = child.copy();
            copy.addChild(childCopy);
            childCopy.setParent(copy);
            if (child == getSelectedTransition()) {
                copy.setSelectedTransition(childCopy);
            }
        }
        return copy;
    }

    /**
     * @return An independent copy of this place and its deterministic subtree (but without parent)
     */
    public TFVPlace copyDeterministic() {
        TFVPlace copy = new TFVPlace(task);
        if (selectedTransition != null) {
            TFVTransition childCopy = selectedTransition.copyDeterministic();
            copy.addChild(childCopy);
            childCopy.setParent(copy);
            copy.setSelectedTransition(childCopy);
        }
        return copy;
    }

    @Override
	public String toString() {
		return "TFVPlace:" + task;
	}

    @Override
    public Iterator<TFVPlace> iterator() {
        return new TFVSubtreeDepthIterator(this);
    }

    @Override
    public String getContentString() {
        return task.toString();
    }

    /**
     * Iterator that iterates over a place's descendant places using Depth-First-Search.
     */
    static class TFVSubtreeDepthIterator implements Iterator<TFVPlace> {
        private final LinkedList<TFVPlace> currentRoots;

        public TFVSubtreeDepthIterator(TFVPlace place) {
            this.currentRoots = new LinkedList<>();
            currentRoots.add(place);
        }

        @Override
        public boolean hasNext() {
            return currentRoots.size() > 0;
        }

        @Override
        public TFVPlace next() {
            TFVPlace current = currentRoots.pop();
            for (TFVTransition child : current.getChildren()) {
                for (TFVPlace grandchild : child.getChildren()) {
                    currentRoots.push(grandchild);
                }
            }
            return current;
        }
    }
}
