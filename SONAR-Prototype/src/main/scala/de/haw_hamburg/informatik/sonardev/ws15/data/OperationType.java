package de.haw_hamburg.informatik.sonardev.ws15.data;

/**
 * Enumeration of operation types used during team formation.
 */
public enum OperationType {
	EXEC("exec"),
    DELEG("deleg"),
    SPLIT("split"),
    REFINE("refine");

    /**
     * The string identifier of the operation type
     */
    private final String name;

    OperationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
