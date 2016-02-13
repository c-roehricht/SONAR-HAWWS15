package de.haw_hamburg.informatik.sonardev.ws15.data;

import java.util.Collections;
import java.util.List;

/**
 * A "refine" operation during team formation. Value class.
 */
public class RefineOperation extends TeamOperation {
    private final AssignedTask output;

    public RefineOperation(AssignedTask input, ConcreteTask output) {
        super(input, OperationType.REFINE);
        this.output = new AssignedTask(output, input.getOperator());
    }

    public AssignedTask getOutput() {
        return output;
    }

    @Override
    public List<AssignedTask> getOutputs() {
        return Collections.singletonList(output);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RefineOperation that = (RefineOperation) o;

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
