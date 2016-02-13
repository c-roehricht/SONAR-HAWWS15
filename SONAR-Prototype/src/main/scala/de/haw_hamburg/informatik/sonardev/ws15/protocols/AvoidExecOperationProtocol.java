package de.haw_hamburg.informatik.sonardev.ws15.protocols;

import de.haw_hamburg.informatik.sonardev.ws15.data.OperationType;
import de.haw_hamburg.informatik.sonardev.ws15.data.TeamOperation;

import java.util.*;

//TODO document
public class AvoidExecOperationProtocol implements ConflictResolutionProtocol<TeamOperation> {
    /**
     * Source of random values
     */
    private Random random = new Random();

    /**
     * Identifier of the protocol
     */
    private ConflictResolutionProtocolName name;

    /**
     * Default constructor
     * @param name The name to identify the protocol
     */
    public AvoidExecOperationProtocol(ConflictResolutionProtocolName name) {
        this.name = name;
    }

    @Override
    public ConflictOption<TeamOperation> solveConflict(List<ConflictOption<TeamOperation>> conflictOptions) {
        List<ConflictOption<TeamOperation>> preferredOptions = new LinkedList<>(conflictOptions);
        List<ConflictOption<TeamOperation>> execs = new ArrayList<>();
        Iterator<ConflictOption<TeamOperation>> iterator = preferredOptions.iterator();
        //Filter out the exec operations
        while (iterator.hasNext()) {
            ConflictOption<TeamOperation> option = iterator.next();
            if (option.getValue().getType() == OperationType.EXEC) {
                execs.add(option);
                iterator.remove();
            }
        }

        //If preferred options are available, chose one at random; otherwise, pick a random inferior alternative
        if (preferredOptions.size() > 0) {
            return preferredOptions.get(random.nextInt(preferredOptions.size()));
        } else {
            return execs.get(random.nextInt(execs.size()));
        }
    }

    @Override
    public ConflictResolutionProtocolName getName() {
        return name;
    }

    @Override
    public Object handleConflictMessage(Object Message) {
        return null;
    }
}
