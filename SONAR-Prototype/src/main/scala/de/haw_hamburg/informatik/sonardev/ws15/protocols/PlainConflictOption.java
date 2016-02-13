package de.haw_hamburg.informatik.sonardev.ws15.protocols;

/**
 * Simple conflict option wrapper for options that can be considered without further computation effort.
 * @param <O> Type of the option that can be extracted from the wrapper
 */
public class PlainConflictOption<O> implements ConflictOption<O> {
	O concreteOption;
	
	public PlainConflictOption(O concreteOption) {
		this.concreteOption = concreteOption;
	}

	@Override
	public O getValue() {
		return concreteOption;
	}
}
