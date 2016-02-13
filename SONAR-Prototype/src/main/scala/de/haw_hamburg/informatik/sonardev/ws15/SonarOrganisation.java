package de.haw_hamburg.informatik.sonardev.ws15;

import de.haw_hamburg.informatik.sonardev.ws15.agents.IOma;
import de.haw_hamburg.informatik.sonardev.ws15.agents.IOpa;

import java.util.List;

public class SonarOrganisation implements IOrganisation {

  private List<IOpa> opas;
  private List<IProtocol> protocols;

  public SonarOrganisation() {
  }

  public SonarOrganisation(List<IOpa> opas, List<IProtocol> protocols) {
    this.opas = opas;
    this.protocols = protocols;
  }

  public List<IOpa> getOpas() {
    return opas;
  }

  @Override public void addOpa(IOpa opa) {
    opas.add(opa);
  }

  @Override public void deleteOpa(IOpa opa) {
    if (opas.contains(opa)) {
      opas.remove(opa);
    } else {
      throw new IllegalStateException("Opa was not part of this SonarOrganisation");
    }
  }

  public void setOpas(List<IOpa> opas) {
    this.opas = opas;
  }

  public void setProtocols(List<IProtocol> protocols) {
    this.protocols = protocols;
  }

  @Override public List<IProtocol> getProtocols() {
    return protocols;
  }

  //IMPORANT: oma arent being implemented yet.

  @Override public void addOma(IOma oma) {
    // TODO Auto-generated method stub

  }

  @Override public void deleteOma(IOma oma) {
    // TODO Auto-generated method stub

  }

  @Override public List<IOma> getAllOmas() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override public String toString() {
    return "SonarOrganisation{" +
        "opas=" + opas +
        ", protocols=" + protocols +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof SonarOrganisation))
      return false;

    SonarOrganisation that = (SonarOrganisation) o;

    if (opas != null ? !opas.equals(that.opas) : that.opas != null)
      return false;
    return !(protocols != null ? !protocols.equals(that.protocols) : that.protocols != null);

  }

  @Override public int hashCode() {
    int result = opas != null ? opas.hashCode() : 0;
    result = 31 * result + (protocols != null ? protocols.hashCode() : 0);
    return result;
  }
}
