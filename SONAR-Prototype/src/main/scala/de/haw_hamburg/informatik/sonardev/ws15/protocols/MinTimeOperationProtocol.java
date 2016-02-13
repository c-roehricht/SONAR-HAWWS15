package de.haw_hamburg.informatik.sonardev.ws15.protocols;

import de.haw_hamburg.informatik.sonardev.ws15.data.TeamOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

//TODO document
public class MinTimeOperationProtocol implements ConflictResolutionProtocol<TeamOperation> {
	private static class TimeEntry {
        private int runs;
        private double average;

        public TimeEntry(int runs, double average) {
            this.runs = runs;
            this.average = average;
        }
        public int getRuns() {
            return runs;
        }
        public double getAverage() {
            return average;
        }
        public void addRun(double time) {
            average *= runs;
            average += time;
            runs++;
            average /= runs;
        }
    }

    /**
     * Average time spent on previous operations
     */
    private HashMap<TeamOperation, TimeEntry> timeEstimates = new HashMap<>();

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
	public MinTimeOperationProtocol(ConflictResolutionProtocolName name) {
		this.name = name;
	}

	@Override
	public ConflictOption<TeamOperation> solveConflict(List<ConflictOption<TeamOperation>> options) {
        ConflictOption<TeamOperation> chosen = null;
        double chosenTime = 0;
        for (ConflictOption<TeamOperation> option : options) {
            TimeEntry estimate = timeEstimates.get(option.getValue());
            if (estimate != null) {
                double newTime = estimate.getAverage();
                if (chosen == null || newTime < chosenTime) {
                    chosen = option;
                    chosenTime = newTime;
                }
            }
        }
        //If no time estimates are known, chose a random option
        if (chosen == null) {
            return options.get(random.nextInt(options.size()));
        } else {
            return chosen;
        }
	}

	@Override
	public ConflictResolutionProtocolName getName(){
		return name;
	}

    @Override
    public Object handleConflictMessage(Object Message) {
        return null;
    }
}
