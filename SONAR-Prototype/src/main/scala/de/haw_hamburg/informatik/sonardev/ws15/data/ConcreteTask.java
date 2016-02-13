package de.haw_hamburg.informatik.sonardev.ws15.data;

import java.util.List;

//TODO
public class ConcreteTask {
    private final String protocol;
    private final List<String> roles;

    public ConcreteTask(String protocol, List<String> roles) {
        this.protocol = protocol;
        this.roles = roles;
    }

    public String getProtocol() {
        return protocol;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConcreteTask task = (ConcreteTask) o;

        if (protocol != null ? !protocol.equals(task.protocol) : task.protocol != null) return false;
        return roles != null ? roles.equals(task.roles) : task.roles == null;

    }

    @Override
    public int hashCode() {
        int result = protocol != null ? protocol.hashCode() : 0;
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(protocol + "[");
        for (String role : roles) {
            result.append(role);
            result.append(",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }
}