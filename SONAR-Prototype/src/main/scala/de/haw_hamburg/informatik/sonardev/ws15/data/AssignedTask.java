package de.haw_hamburg.informatik.sonardev.ws15.data;

import java.util.List;

/**
 * TODO protocol, roles and optional OPA
 */
public class AssignedTask {
    private final ConcreteTask task;
    private final String operator;

    public AssignedTask(ConcreteTask task, String operator) {
        this.task = task;
        this.operator = operator;
    }

    public ConcreteTask getTask() {
        return task;
    }

    public String getOperator() {
        return operator;
    }

    public String getProtocol() {
        return task.getProtocol();
    }

    public List<String> getRoles() {
        return task.getRoles();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignedTask that = (AssignedTask) o;

        if (task != null ? !task.equals(that.task) : that.task != null) return false;
        return operator != null ? operator.equals(that.operator) : that.operator == null;

    }

    @Override
    public int hashCode() {
        int result = task != null ? task.hashCode() : 0;
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return operator + ":" + task;
    }
}
