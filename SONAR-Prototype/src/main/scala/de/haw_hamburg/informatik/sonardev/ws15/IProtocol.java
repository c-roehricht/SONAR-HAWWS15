package de.haw_hamburg.informatik.sonardev.ws15;

import de.haw_hamburg.informatik.sonardev.ws15.agents.IOpa;
import de.haw_hamburg.informatik.sonardev.ws15.data.OperationType;

import java.util.List;

/**
 * Should hold a petri net representation of the protocol
 * @author Francis
 *
 */
public interface IProtocol {

	public List<String> getRoles();
	
	public List<OperationType> getApplicableOperations();
	
	/**
	 * True when the protocol has been assigned to an agent
	 * @return
	 */
	public boolean isAssigned();
	
	/**
	 * Each protocol (task) has an owner.
	 * @return o The owner of that task
	 */
	public IOpa getOwner();
	
	public void setAssigned(boolean a);

	public String getName();
}
