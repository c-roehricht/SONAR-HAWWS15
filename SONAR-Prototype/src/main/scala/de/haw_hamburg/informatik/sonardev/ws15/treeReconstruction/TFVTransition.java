package de.haw_hamburg.informatik.sonardev.ws15.treeReconstruction;

import de.haw_hamburg.informatik.sonardev.ws15.data.OperationType;
import de.haw_hamburg.informatik.sonardev.ws15.data.TeamOperation;

import java.util.List;

/**
 * A transition in the team formation view. Can be linked to a single parent and multiple child places.
 * Each transitions represents an operation, i.e., the planned fulfilment of a task (its parent),
 * which may create new tasks (its children).
 */
public class TFVTransition extends TFVNode<TFVPlace> {
    /**
     * NOTE Do not implement equals, the TFV currently is a doubly linked structure, so the reference matters.
     * One might argue that this is a wrong model because of uniqueness; however, we did not discuss the implications
     * of uniqueness in the TFV so far.
     */

    /**
     * The operation that this transition represents
     */
	private final TeamOperation operation;

    public TFVTransition(TFVPlace parent, List<TFVPlace> children, TeamOperation operation) {
        super(parent, children);
        this.operation = operation;
    }

    /**
     * Creates an unlinked transition.
     * @param operation The represented operation
     */
    public TFVTransition(TeamOperation operation) {
        this(null, null, operation);
    }

    /**
     * @param otherTransition A transition that could be unified with this transition
     * @return true iff the transitions are unifiable
     */
    public boolean isUnifiableWith(TFVTransition otherTransition) {
        return otherTransition.getOperation().equals(operation);
    }

    /**
     * Unifies a transition with this transition, as well as their subtrees.
     * @param otherTransition The transition to unify with
     */
    public void unifyWith(TFVTransition otherTransition) {
        boolean unified;
        //Iterate over all new children
        for (TFVPlace newChild : otherTransition.getChildren()) {
            unified = false;
            for (TFVPlace oldChild : children) {
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
    }

    /**
     * @return The operation that this transition represents
     */
	public TeamOperation getOperation() {
		return operation;
	}

    //TODO
    public boolean isExecutableLeaf() {
        return (operation.getType() == OperationType.EXEC && children.size() == 0);
    }

    /**
     * @return An independent copy of this transition and its subtree (but without parent)
     */
    public TFVTransition copy() {
        TFVTransition copy = new TFVTransition(operation);
        for (TFVPlace child : children) {
            TFVPlace childCopy = child.copy();
            copy.addChild(childCopy);
            childCopy.setParent(copy);
        }
        return copy;
    }

    /**
     * @return An independent copy of this transition and its deterministic subtree (but without parent)
     */
    public TFVTransition copyDeterministic() {
        TFVTransition copy = new TFVTransition(operation);
        for (TFVPlace child : children) {
            TFVPlace childCopy = child.copyDeterministic();
            copy.addChild(childCopy);
            childCopy.setParent(copy);
        }
        return copy;
    }

	@Override
	public String toString() {
		return "TFVTransition:" + operation;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TFVTransition that = (TFVTransition) o;

        return operation != null ? operation.equals(that.operation) : that.operation == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        return result;
    }

    @Override
    public String getContentString() {
        return operation.toString();
    }
}
