package de.haw_hamburg.informatik.sonardev.ws15.agents;

import de.haw_hamburg.informatik.sonardev.ws15.data.AssignedTask;

public interface OpaDataSource {
    void assignToAgent(String agent, AssignedTask task);
}
