package de.haw_hamburg.informatik.sonardev.ws15.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A "split" operation during team formation. Value class.
 */
public class SplitOperation extends TeamOperation {
    private final List<AssignedTask> outputs;

    public SplitOperation(AssignedTask input, List<ConcreteTask> outputs) {
        super(input, OperationType.SPLIT);
        ArrayList<AssignedTask> assignedOutputs = new ArrayList<>();
        for (ConcreteTask output : outputs) {
            assignedOutputs.add(new AssignedTask(output, input.getOperator()));
        }
        this.outputs = assignedOutputs;
    }

    @Override
    public List<AssignedTask> getOutputs() {
        return outputs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SplitOperation that = (SplitOperation) o;

        return outputs != null ? outputs.equals(that.outputs) : that.outputs == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (outputs != null ? outputs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString() + "->");
        for (AssignedTask output : outputs) {
            result.append(output);
            result.append(",");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}