package de.haw_hamburg.informatik.sonardev.ws15.protocols;

import de.haw_hamburg.informatik.sonardev.ws15.agents.IOpa;
import de.haw_hamburg.informatik.sonardev.ws15.data.OperationType;
import de.haw_hamburg.informatik.sonardev.ws15.data.TeamOperation;

import java.util.List;
import java.util.Random;

//TODO document
public class MinCostOperationProtocol implements ConflictResolutionProtocol<TeamOperation> {
    /**
     * Source of random values
     */
    private Random random = new Random();

    /**
     * Identifier of the protocol
     */
    private ConflictResolutionProtocolName name;

    /**
     * Agent proxy table
     */
    private List<IOpa> opaProxies;

    /**
     * Default constructor
     * @param name The name to identify the protocol
     */
    public MinCostOperationProtocol(ConflictResolutionProtocolName name, List<IOpa> agentProxyTable) {
        this.name = name;
        this.opaProxies = agentProxyTable;
    }

    @Override
    public ConflictOption<TeamOperation> solveConflict(List<ConflictOption<TeamOperation>> conflictOptions) {
        ConflictOption<TeamOperation> chosenOption = null;
        int chosenCost = 0;
        for (ConflictOption<TeamOperation> option : conflictOptions) {
            int cost = 0;
            TeamOperation operation = option.getValue();
            IOpa operator = getAgentForName(operation.getInput().getOperator());
            if (operator == null) {
                continue;
            }
            Integer response = (Integer) operator.handleConflictMessage(name, operation.getInput());
            if (response == null) {
                continue;
            }
            cost += response;
            if (operation.getType() == OperationType.DELEG) {
                IOpa subOperator = getAgentForName(operation.getOutputs().get(0).getOperator());
                if (subOperator == null) {
                    continue;
                }
                response = (Integer) subOperator.handleConflictMessage(name, operation.getOutputs().get(0));
                if (response == null) {
                    continue;
                }
                cost += response;
            }
            if (chosenOption == null || cost < chosenCost) {
                chosenCost = cost;
                chosenOption = option;
            }
        }

        //If no cost could be determined, chose one at random
        if (chosenOption == null) {
            return conflictOptions.get(random.nextInt(conflictOptions.size()));
        } else {
            return chosenOption;
        }
    }

    private IOpa getAgentForName(String name) {
        for (IOpa opa : opaProxies) {
            if (opa.getName().equals(name)) {
                return opa;
            }
        }
        return null;
    }

    @Override
    public ConflictResolutionProtocolName getName() {
        return name;
    }

    @Override
    public Object handleConflictMessage(Object Message) {
        //TODO replace
        //Everything costs 1 for now
        return Integer.valueOf(1);
    }

    //TODO
    public void setOpaProxies(List<IOpa> opaProxies) {
        this.opaProxies = opaProxies;
    }
}
