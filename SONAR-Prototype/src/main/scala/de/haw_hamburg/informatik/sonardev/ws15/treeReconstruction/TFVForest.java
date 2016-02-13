package de.haw_hamburg.informatik.sonardev.ws15.treeReconstruction;

import de.haw_hamburg.informatik.sonardev.ws15.data.AssignedTask;
import de.haw_hamburg.informatik.sonardev.ws15.data.TeamOperation;
import de.haw_hamburg.informatik.sonardev.ws15.treeReconstruction.TFVPlace.TFVSubtreeDepthIterator;

import java.util.*;

/** //TODO document
 * Represents a tree of the applicable operations according to the
 * Team-Formation-View of the organization. This class provides access to
 * to all initial Tasks.
 * 
 * @author Francis Opoku, 16.01.2016
 */
public class TFVForest implements Iterable<TFVPlace> {
    /**
     * NOTE Do not implement equals in the current model (see TFVNode for more details)
     */

    /**
     * The roots of the forest (places without parent transitions)
     */
    private LinkedList<TFVPlace> roots = new LinkedList<>();

    /**
     * The name of the team formation view
     */
    private String name = "Team Formation View";

    /**
     * Creates a forest that only contains a single transition, its parent and its children.
     * @param transition The single transition
     */
    public TFVForest(TFVTransition transition) {
        this.roots.add(transition.getParent());
    }

    /**
     * Creates an empty forest.
     */
    public TFVForest() {
    }

    /**
     * Creates a forest that only contains a single transition, its parent and its children.
     * All nodes are constructed from a single operation.
     * @param operation The operation
     */
    public TFVForest(TeamOperation operation) {
        TFVPlace parent = new TFVPlace(operation.getInput());
        TFVTransition transition = new TFVTransition(operation);
        transition.setParent(parent);
        parent.addChild(transition);
        for (AssignedTask output : operation.getOutputs()) {
            TFVPlace child = new TFVPlace(output);
            child.setParent(transition);
            transition.addChild(child);
        }
        this.roots.add(parent);
    }

    /**
     * Creates a forest from a list of possible operations.
     * @param operations The list of possible operations
     */
    public TFVForest(List<TeamOperation> operations) {
        for (TeamOperation operation : operations) {
            try {
                mergeIn(new TFVForest(operation));
            } catch (TFVForestMergeException e) {
                //This can never occur in the current implementation
                e.printStackTrace();
            }
        }
    }

    /**
     * Merges a new forest into this forest.
     * TODO state of the new forest after this operations is currently undefined, TODO
     * @param otherForest The new forest to be merged in
     * @throws TFVForestMergeException If the new forest could not be merged in.
     * TODO currently this does not occur because conflicting parent links are not assumed to exist
     */
    public void mergeIn(TFVForest otherForest) throws TFVForestMergeException {
        //Copy the old list of roots
        LinkedList<TFVPlace> oldRoots = new LinkedList<>(roots);
        LinkedList<TFVPlace> newRoots = new LinkedList<>(otherForest.getRoots());

        boolean mergedIn;
        //Iterate over all roots of the other forest
        for (TFVPlace otherRoot : otherForest.getRoots()) {
            mergedIn = false;
            for (TFVPlace oldRoot : oldRoots) {
                //Try to merge it into the subtree of an old root
                if (oldRoot.mergeIntoSubtree(otherRoot)) {
                    mergedIn = true;
                    break;
                }
            }
            //If it could not be merged in, it is a new root
            if (!mergedIn) {
                roots.add(otherRoot);
            }
        }
        //Iterate over all new roots (even if they were merged in)
        for (TFVPlace newRoot : newRoots) {
            for (Iterator<TFVPlace> oldRootIterator = oldRoots.iterator(); oldRootIterator.hasNext();) {
                TFVPlace oldRoot = oldRootIterator.next();
                //If the new matches the old root, it was already merged in and no further merging is necessary
                if (newRoot.isUnifiableWith(oldRoot)) {
                    continue;
                }
                //Try to merge an old root into the subtree of the new root
                if (newRoot.mergeIntoSubtree(oldRoot)) {
                    //If this worked, it is no longer a root
                    oldRootIterator.remove();
                    roots.remove(oldRoot);
                }
            }
        }
    }

    /**
     * @return A list of the forest's roots (places without a parent transition)
     */
    public List<TFVPlace> getRoots() {
        return new ArrayList<>(roots);
    }

    /**
     * @param place A place that is tested for unification
     * @return a unifiable place of the forest or null if no such root exists
     */
    public TFVPlace getUnifyablePlace(TFVPlace place) {
        for (TFVPlace ownPlace : this) {
            if (ownPlace.isUnifiableWith(place)) {
                return ownPlace;
            }
        }
        return null;
    }

    /**
     * Returns all transitions that represent applicable operations for the task represented by a place.
     * @param place A place whose task is tested for applicable operations
     * @return A list of all transitions whose operations that can be applied to the place's task
     */
	public List<TFVTransition> getApplicableTransitions(TFVPlace place) {
        LinkedList<TFVTransition> applicableTransitions = new LinkedList<>();
		TFVPlace root = getUnifyablePlace(place);
        if (root != null) {
            for (TFVTransition child : root.getChildren()) {
                applicableTransitions.add(child);
            }
        }
        return applicableTransitions;
	}

    /**
     * Returns all all transitions that represent applicable operations for the task.
     * @param task A task that is tested for applicable operations
     * @return A list of all transitions whose operations that can be applied to the task
     */
    public List<TFVTransition> getApplicableTransitions(AssignedTask task) {
        TFVPlace place = findPlace(task);
        if (place != null) {
            return getApplicableTransitions(place);
        }
        return Collections.emptyList();
    }

    /**
     * Returns all applicable operations for a task.
     * @param task A task that is tested for applicable operations
     * @return A list of all operations that can be applied to the task
     */
    public List<TeamOperation> getApplicableOperations(AssignedTask task) {
        List<TeamOperation> operations = new ArrayList<>();
        for (TFVTransition transition : getApplicableTransitions(task)) {
            operations.add(transition.getOperation());
        }
        return operations;
    }

    /**
     * Selects an active transition. Each place can have one active child transition.
     * @param transition The transition to be selected
     */
    public void selectTransition(TFVTransition transition) {
        transition.getParent().setSelectedTransition(transition);
    }

    /**
     * Returns the place representing the specified assigned task.
     * @param task The assigned task to search for
     * @return The matching place or null if no such place exists
     */
    private TFVPlace findPlace(AssignedTask task) {
        for (TFVPlace place : this) {
            if (place.getTask().equals(task)) {
                return place;
            }
        }
        return null;
    }

    /**
     * @return The name of the team formation view
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The new name of the team formation view
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return An independent copy of this forest
     */
    public TFVForest copy() {
        TFVForest copy = new TFVForest();
        for (TFVPlace root : getRoots()) {
            copy.roots.add(root.copy());
        }
        copy.setName(getName());
        return copy;
    }

    /**
     * @return An independent copy of this forest's deterministic subforest
     */
    public TFVForest copyDeterministic() {
        TFVForest copy = new TFVForest();
        for (TFVPlace root : getRoots()) {
            copy.roots.add(root.copyDeterministic());
        }
        copy.setName(getName());
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[" + getName() + "]\n");
        for (TFVPlace root : getRoots()) {
            root.buildIndentedForestString(result, "|");
        }
        return result.toString();
    }

    @Override
    public Iterator<TFVPlace> iterator() {
        return new TFVDepthIterator(this);
    }

    /**
     * Iterator that iterates a view's places using Depth-First-Search.
     */
    private static class TFVDepthIterator implements Iterator<TFVPlace> {
        private final LinkedList<TFVSubtreeDepthIterator> rootIterators;

        public TFVDepthIterator(TFVForest forest) {
            this.rootIterators = new LinkedList<>();
            for (TFVPlace root : forest.getRoots()) {
                rootIterators.add((TFVSubtreeDepthIterator) root.iterator());
            }
        }

        @Override
        public boolean hasNext() {
            if (rootIterators.size() == 0) {
                return false;
            }
            return rootIterators.peek().hasNext();
        }

        @Override
        public TFVPlace next() {
            while (!rootIterators.peek().hasNext()) {
                rootIterators.pop();
            }
            TFVPlace next = rootIterators.peek().next();
            if (!rootIterators.peek().hasNext()) {
                rootIterators.pop();
            }
            return next;
        }
    }

    /**
     * Exception that indicates that two forests could not be merged.
     */
    public class TFVForestMergeException extends Exception
    {
        private final TFVPlace conflictedOld;
        private final TFVPlace conflictedNew;

        public TFVForestMergeException(TFVPlace conflictedOld, TFVPlace conflictedNew) {
            super("Merge conflict on old place: " + conflictedOld + " and new place: " + conflictedNew + ".");
            this.conflictedOld = conflictedOld;
            this.conflictedNew = conflictedNew;
        }

        /**
         * @return The place that could not be merged in the old forest
         */
        public TFVPlace getConflictedOld() {
            return conflictedOld;
        }

        /**
         * @return The place that could not be merged in the new (merged-in) forest
         */
        public TFVPlace getConflictedNew() {
            return conflictedNew;
        }
    }
}
