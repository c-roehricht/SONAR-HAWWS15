package de.haw_hamburg.informatik.sonardev.ws15.protocols;

import de.haw_hamburg.informatik.sonardev.ws15.data.TeamOperation;

import java.util.List;
import java.util.Random;

//TODO document
public class RandomOperationProtocol implements ConflictResolutionProtocol<TeamOperation> {
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
    public RandomOperationProtocol(ConflictResolutionProtocolName name) {
        this.name = name;
    }

    @Override
    public ConflictOption<TeamOperation> solveConflict(List<ConflictOption<TeamOperation>> conflictOptions) {
        //Chose a random option
        return conflictOptions.get(random.nextInt(conflictOptions.size()));
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
