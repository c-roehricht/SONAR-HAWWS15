package de.haw_hamburg.informatik.sonardev.ws15.data;

import java.util.Collections;
import java.util.List;

/**
 * An "execute" operation during team formation. Value class.
 */
public class ExecOperation extends TeamOperation {
    public ExecOperation(AssignedTask input) {
        super(input, OperationType.EXEC);
    }

    @Override
    public List<AssignedTask> getOutputs() {
        return Collections.emptyList();
    }
}
