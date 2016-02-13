package de.haw_hamburg.informatik.sonardev.ws15.data;

import java.util.List;

/**
 * Represents an operation in the team formation process. Value class.
 * Only subclasses may be instantiated externally.
 */
public abstract class TeamOperation {
    protected final AssignedTask input;
    protected final OperationType type;

    protected TeamOperation(AssignedTask input, OperationType type) {
        this.input = input;
        this.type = type;
    }

    /**
     * @return The operation's type
     */
    public OperationType getType() {
        return type;
    }

    /**
     * @return The operation's assigned input task
     */
    public AssignedTask getInput() {
        return input;
    }

    /**
     * @return The operation's assigned output tasks
     */
    public abstract List<AssignedTask> getOutputs();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamOperation that = (TeamOperation) o;

        if (input != null ? !input.equals(that.input) : that.input != null) return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = input != null ? input.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return type + ":" + input;
    }
}
