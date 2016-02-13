package de.haw_hamburg.informatik.sonardev.ws15.agents;


import de.haw_hamburg.informatik.sonardev.ws15.protocols.*;
import de.haw_hamburg.informatik.sonardev.ws15.data.AssignedTask;
import de.haw_hamburg.informatik.sonardev.ws15.data.OperationType;
import de.haw_hamburg.informatik.sonardev.ws15.data.TeamOperation;
import de.haw_hamburg.informatik.sonardev.ws15.treeReconstruction.*;

import java.util.*;

/**
 * Default implementation of an OPA.
 */
public class Opa implements IOpa {
    /**
     * The OMA assigned to the OPA
     */
    private Oma oma;

    //TODO
    private OpaDataSource source;

    /**
     * The OPA's knowledge of the current team formation
     */
	private TFVForest formationView;
	private String name;
	private boolean isReady = true;
	private List<String> resources;
	private LinkedList<AssignedTask> generalTasks = new LinkedList<>();
	private LinkedList<AssignedTask> workingTasks = new LinkedList<>();
    private LinkedList<AssignedTask> executingTasks = new LinkedList<>();
	private List<String> communicationProtocols;
	private HashMap<ConflictResolutionProtocolName,
                    ConflictResolutionProtocol<TeamOperation>> operationConflictProtocols = new HashMap<>();
	private ConflictResolutionProtocolName actualOperationConflictProtocol;
	private List<IOpa> opaProxies;

	public Opa(Oma oma, String name, List<TeamOperation> operations,
               List<String> resources, List<String> communicationProtocols) {
		this.oma = oma;
		this.name = name;
		this.resources = resources;
		this.communicationProtocols = communicationProtocols;
        this.formationView = new TFVForest(operations);
        this.formationView.setName(name + "'s formation view");
        System.out.print(formationView);
		this.operationConflictProtocols.put(ConflictResolutionProtocolName.MINTIME,
                new MinTimeOperationProtocol(ConflictResolutionProtocolName.MINTIME));
        this.operationConflictProtocols.put(ConflictResolutionProtocolName.AVOID_EXEC,
                new AvoidExecOperationProtocol(ConflictResolutionProtocolName.AVOID_EXEC));
        this.operationConflictProtocols.put(ConflictResolutionProtocolName.RANDOM,
                new RandomOperationProtocol(ConflictResolutionProtocolName.RANDOM));
        this.operationConflictProtocols.put(ConflictResolutionProtocolName.MINCOST,
                new MinCostOperationProtocol(ConflictResolutionProtocolName.MINCOST, Collections.emptyList()));
        //TODO replace
        //Chose MINCOST
        this.actualOperationConflictProtocol = ConflictResolutionProtocolName.MINCOST;
		System.out.println(this.getName() + " initialized");
	}

	public Oma getOma() {
		return oma;
	}

	public void setOma(Oma oma) {
		this.oma = oma;
	}

    //TODO document
	public void enqueue(AssignedTask task) {
		generalTasks.push(task);
	}

	@Override
	public boolean hasExecutableTasks() {
		return executingTasks.size() > 0;
	}

    @Override
    public Object handleConflictMessage(ConflictResolutionProtocolName name, Object message) {
        ConflictResolutionProtocol<?> protocol = operationConflictProtocols.get(name);
        if (protocol == null) {
            return null;
        }
        return protocol.handleConflictMessage(message);
    }

    //TODO document
    private void dequeue() {
        workingTasks.push(generalTasks.pop());
    }

    //TODO document
    private void enqueueWorking(AssignedTask task) {
        workingTasks.push(task);
    }

    //TODO document
    private AssignedTask dequeueWorking() {
        return workingTasks.pop();
    }

    //TODO document
    private void enqueueExecuting(AssignedTask task) {
        executingTasks.push(task);
    }


    @Override
	public String getName() {
		return name;
	}

	@Override
	public void start() {
        System.out.println("OPA starting: " + this);

        //Run until no tasks are available
		while (!generalTasks.isEmpty()) {
			setIsReady(false);
			teamFormation();
			setIsReady(true);
		}
	}

    /**
     * @return A list of the OPA's root (initial) tasks.
     */
	private List<AssignedTask> getOwnRootTasks() {
        ArrayList<AssignedTask> tasks = new ArrayList<>();
        for (TFVPlace root : formationView.getRoots()) {
            AssignedTask task = root.getTask();
            if (task.getOperator() == name) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * @return The current protocol for solving operation conflicts
     */
    private ConflictResolutionProtocol<TeamOperation> getOperationConflictProtocol() {
        return operationConflictProtocols.get(actualOperationConflictProtocol);
    }

    /**
     * Returns an agent proxy for a named agent
     * @param name The name of the agent
     * @return An agent proxy for the specified name
     */
    private IOpa getAgentProxy(String name) {
        for (IOpa proxy : opaProxies) {
            if (proxy.getName().equals(name)) {
                return proxy;
            }
        }
        return null;
    }

    @Override
    public void assign(AssignedTask task) {
        System.out.println("Analysing: " + task);
        //If the task is assigned to another agent, transfer it
        String operator = task.getOperator();
        if (!operator.equals(getName())) {
            source.assignToAgent(operator, task);
            return;
        }
        //Acquire all transitions with applicable operations that solve the task
        List<TFVTransition> transitions = formationView.getApplicableTransitions(task);
        System.out.println(this + " has " + transitions.size() + " applicable operations.");

        //Chose a transition whose operation should be applied
        TFVTransition chosenTransition = null;
        if (transitions.size() == 0) {
            //TODO define behaviour
        } else if (transitions.size() == 1) {
            //No conflict exists
            chosenTransition = transitions.get(0);
        } else {
            //A conflict must be solved
            List<ConflictOption<TeamOperation>> conflictOptions = new ArrayList<>();
            //Create options for all possible operations
            for (TFVTransition transition : transitions) {
                conflictOptions.add(new PlainConflictOption<>(transition.getOperation()));
            }
            //Acquire the conflict resolution protocol
            ConflictResolutionProtocol<TeamOperation> protocol = getOperationConflictProtocol();
            //Solve the conflict
            TeamOperation chosenOperation = protocol.solveConflict(conflictOptions).getValue();
            //Find the matching transition
            for (TFVTransition transition : transitions) {
                if (transition.getOperation().equals(chosenOperation)) {
                    chosenTransition = transition;
                }
            }
        }
        //Select the chosen transition to apply the operation
        formationView.selectTransition(chosenTransition);
        System.out.println("Operation applied: " + chosenTransition.getOperation());
        //Enqueue the resulting new tasks
        for (TFVPlace place : chosenTransition.getChildren()) {
            source.assignToAgent(name, task);
            System.out.println(this + " enqueues " + place.getTask());
        }
        //Enqueue if an execution operation was chosen
        if (chosenTransition.getOperation().getType() == OperationType.EXEC) {
            enqueueExecuting(chosenTransition.getParent().getTask());
            System.out.println(this + " will execute " + chosenTransition.getParent().getTask());
        }
    }

    @Override
    public void setDataSource(OpaDataSource source) {
        this.source = source;
    }

    //TODO document
	private void teamFormation() {
        //Dequeue a task for working
        dequeue();

		while (!workingTasks.isEmpty()) {
            //Dequeue a working task
			AssignedTask task = dequeueWorking();
            System.out.println("Analysing: " + task);
            //If the task is assigned to another agent, transfer it
            String operator = task.getOperator();
            if (!operator.equals(getName())) {
                IOpa assignedAgent = getAgentProxy(operator);
                assignedAgent.enqueue(task);
                //Start the operator
                //NOTE the agents will later run concurrently instead
                if (assignedAgent.isReady()) {
                    assignedAgent.start();
                }
                continue;
            }
            //Acquire all transitions with applicable operations that solve the task
			List<TFVTransition> transitions = formationView.getApplicableTransitions(task);
			System.out.println(this + " has " + transitions.size() + " applicable operations.");

            //Chose a transition whose operation should be applied
            TFVTransition chosenTransition = null;
            if (transitions.size() == 0) {
                //TODO define behaviour
            } else if (transitions.size() == 1) {
                //No conflict exists
                chosenTransition = transitions.get(0);
            } else {
                //A conflict must be solved
                List<ConflictOption<TeamOperation>> conflictOptions = new ArrayList<>();
                //Create options for all possible operations
                for (TFVTransition transition : transitions) {
                    conflictOptions.add(new PlainConflictOption<>(transition.getOperation()));
                }
                //Acquire the conflict resolution protocol
                ConflictResolutionProtocol<TeamOperation> protocol = getOperationConflictProtocol();
                //Solve the conflict
                TeamOperation chosenOperation = protocol.solveConflict(conflictOptions).getValue();
                //Find the matching transition
                for (TFVTransition transition : transitions) {
                    if (transition.getOperation().equals(chosenOperation)) {
                        chosenTransition = transition;
                    }
                }
            }
            //Select the chosen transition to apply the operation
            formationView.selectTransition(chosenTransition);
            System.out.println("Operation applied: " + chosenTransition.getOperation());
            //Enqueue the resulting new tasks
            for (TFVPlace place : chosenTransition.getChildren()) {
                enqueueWorking(place.getTask());
                System.out.println(this + " enqueues " + place.getTask());
            }
            //Enqueue if an execution operation was chosen
            if (chosenTransition.getOperation().getType() == OperationType.EXEC) {
                enqueueExecuting(chosenTransition.getParent().getTask());
                System.out.println(this + " will execute " + chosenTransition.getParent().getTask());
            }
		}
	}

	@Override
	public boolean isReady() {
		return this.isReady;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getResources() {
		return resources;
	}

	public void setResources(List<String> resources) {
		this.resources = resources;
	}

	public List<String> getCommunicationProtocols() {
		return communicationProtocols;
	}

	public void setCommunicationProtocols(List<String> communicationProtocols) {
		this.communicationProtocols = communicationProtocols;
	}

	private void setIsReady(boolean isReady) {
		this.isReady = isReady;
	}

	@Override
	public String toString() {
		return getName();
	}

    //TODO remove?
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Opa))
			return false;

		Opa opa = (Opa) o;

		if (oma != null ? !oma.equals(opa.oma) : opa.oma != null)
			return false;
		if (name != null ? !name.equals(opa.name) : opa.name != null)
			return false;
		/*
		 * if (tasks != null ? !tasks.equals(opa.tasks) : opa.tasks != null)
		 * return false;
		 */
		if (resources != null ? !resources.equals(opa.resources)
				: opa.resources != null)
			return false;
		return !(communicationProtocols != null ? !communicationProtocols
				.equals(opa.communicationProtocols)
				: opa.communicationProtocols != null);

	}

    //TODO remove?
	@Override
	public int hashCode() {
		int result = oma != null ? oma.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		/* result = 31 * result + (tasks != null ? tasks.hashCode() : 0) */;
		result = 31 * result + (resources != null ? resources.hashCode() : 0);
		result = 31
				* result
				+ (communicationProtocols != null ? communicationProtocols
						.hashCode() : 0);
		return result;
	}

	@Override
	public void addOperationConflictProtocol(ConflictResolutionProtocol<TeamOperation> protocol) {
		operationConflictProtocols.put(protocol.getName(), protocol);
	}

    @Override
	public String getInducedTeamWorkflow() {
        StringBuilder result = new StringBuilder();
		for (AssignedTask task : executingTasks) {
            result.append(task.getTask() + "\u2295");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
	}

    @Override
    public TFVForest getTeamFormationView() {
        return formationView.copy();
    }

    @Override
	public void setOpaProxies(List<IOpa> o) {
		this.opaProxies = o;
        ((MinCostOperationProtocol) operationConflictProtocols.get(ConflictResolutionProtocolName.MINCOST))
                .setOpaProxies(o);
	}
}
