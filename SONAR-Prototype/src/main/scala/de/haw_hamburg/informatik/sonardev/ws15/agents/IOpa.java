package de.haw_hamburg.informatik.sonardev.ws15.agents;

import de.haw_hamburg.informatik.sonardev.ws15.protocols.ConflictResolutionProtocol;
import de.haw_hamburg.informatik.sonardev.ws15.protocols.ConflictResolutionProtocolName;
import de.haw_hamburg.informatik.sonardev.ws15.data.AssignedTask;
import de.haw_hamburg.informatik.sonardev.ws15.data.TeamOperation;
import de.haw_hamburg.informatik.sonardev.ws15.treeReconstruction.TFVForest;

import java.util.List;

/**
 * Organisation positional agent (OPA).
 */
public interface IOpa extends IAgent {
    /**
     * @return true iff the OPA is waiting to be started
     */
    boolean isReady();

    void addOperationConflictProtocol(ConflictResolutionProtocol<TeamOperation> protocol);

    /**
     * @return a string representing the induced team workflow from the last team formation
     */
    String getInducedTeamWorkflow();

    /**
     * @return The OPA's knowledge of the current team formation
     */
    TFVForest getTeamFormationView();

    /**
     * @param proxies The new proxy interfaces used for communicating with other OPAs
     */
    void setOpaProxies(List<IOpa> proxies);


    //TODO
    void assign(AssignedTask task);

    //TODO
    void setDataSource(OpaDataSource source);

    /**
     * Enqueues a new initial task for the OPA.
     * @param task The task to enqueue
     */
    void enqueue(AssignedTask task);

    /**
     * @return true iff the OPA has tasks scheduled for execution
     */
    boolean hasExecutableTasks();

    /**
     * Processes and responds to a conflict protocol message.
     * @param name The protocol's identifier
     * @param message The message to process
     * @return the response (null if the protocol is not supported by the OPA)
     */
    Object handleConflictMessage(ConflictResolutionProtocolName name, Object message);
}