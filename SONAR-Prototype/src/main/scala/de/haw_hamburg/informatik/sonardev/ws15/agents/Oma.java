package de.haw_hamburg.informatik.sonardev.ws15.agents;

/**
 * Default implementation of an OMA.
 */
public class Oma implements IOma {
    /**
     * The OMA's name
     */
    private String name;

    /**
     * Default constructor
     * @param name The OMA's name
     */
    public Oma(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void start() {
        //TODO not implemented yet
    }
}
