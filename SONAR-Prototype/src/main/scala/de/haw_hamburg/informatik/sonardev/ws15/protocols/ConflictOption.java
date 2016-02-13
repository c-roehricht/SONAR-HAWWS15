package de.haw_hamburg.informatik.sonardev.ws15.protocols;

/**
 * Generic wrapper for an alternative option in conflict resolution.
 * @param <O> Type of the option that can be extracted from the wrapper
 */
public interface ConflictOption<O> {
    /**
     * @return The option that can be considered in conflict resolution. Accessing might require additional computation effort.
     */
	O getValue();
}
