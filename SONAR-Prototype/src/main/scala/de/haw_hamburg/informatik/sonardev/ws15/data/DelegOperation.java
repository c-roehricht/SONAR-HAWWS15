package de.haw_hamburg.informatik.sonardev.ws15.data;

import java.util.Collections;
import java.util.List;

/**
 * A "delegate" operation during team formation. Value class.
 */
public class DelegOperation extends TeamOperation {
    private final AssignedTask output;

    public DelegOperation(AssignedTask input, String operator) {
        super(input, OperationType.DELEG);
        this.output = new AssignedTask(input.getTask(), operator);
    }

    @Override
    public List<AssignedTask> getOutputs() {
        return Collections.singletonList(output);
    }

    public AssignedTask getOutput() {
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DelegOperation that = (DelegOperation) o;

        return output != null ? output.equals(that.output) : that.output == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (output != null ? output.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "->" + output;
    }
}
