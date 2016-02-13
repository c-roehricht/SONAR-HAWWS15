package de.haw_hamburg.informatik.sonardev.ws15.setUp;

import de.haw_hamburg.informatik.sonardev.ws15.SonarOrganisation;

/**
 * Objects that enable loading a SONAR organisation from a file.
 */
public interface IOrganisationLoader {
    /**
     * Loads a SONAR organisation from a file.
     * @param filepath The path to the organisation file
     * @return The loaded SONAR organisation
     */
    SonarOrganisation loadOrganisation(String filepath);
}
