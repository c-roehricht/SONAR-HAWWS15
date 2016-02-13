package de.haw_hamburg.informatik.sonardev.ws15;

import de.haw_hamburg.informatik.sonardev.ws15.agents.IOpa;
import de.haw_hamburg.informatik.sonardev.ws15.data.OperationType;

import java.util.List;

/**
 * Created by Daniel Hofmeister on 11.01.2016.
 */
public class ConcreteProtocol implements IProtocol {

  private String name;
  private List<String> roles;
  private String file;
  private boolean assigned;

  public ConcreteProtocol(String name, List<String> roles, String file) {
    this.name = name;
    this.roles = roles;
    this.file = file;
    this.assigned = false;
  }

  @Override public List<String> getRoles() {
    return this.roles;
  }

  @Override public List<OperationType> getApplicableOperations() {
    return null;
  }

  @Override public boolean isAssigned() {
    return assigned;
  }

  @Override public IOpa getOwner() {
    return null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

  @Override public String toString() {
    return "ConcreteProtocol{" +
        "name='" + name + '\'' +
        ", roles=" + roles +
        ", file=" + file +
        '}';
  }

  @Override public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof ConcreteProtocol))
      return false;

    ConcreteProtocol protocol = (ConcreteProtocol) o;

    if (name != null ? !name.equals(protocol.name) : protocol.name != null)
      return false;
    if (roles != null ? !roles.equals(protocol.roles) : protocol.roles != null)
      return false;
    return !(file != null ? !file.equals(protocol.file) : protocol.file != null);

  }

  @Override public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (roles != null ? roles.hashCode() : 0);
    result = 31 * result + (file != null ? file.hashCode() : 0);
    return result;
  }

@Override
public void setAssigned(boolean a) {
	// TODO Auto-generated method stub
	this.assigned = a;
}
}
