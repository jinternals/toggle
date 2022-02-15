package com.jinternals.toggle.core.definition;

import java.util.Objects;

public class ToggleDefinition {

    private String name;
    private String description;
    private State state;

    public ToggleDefinition() {
    }

    public ToggleDefinition(String name, String description, State state) {
        this.name = name;
        this.description = description;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToggleDefinition that = (ToggleDefinition) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, state);
    }

    @Override
    public String toString() {
        return "ToggleDefinition {" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                '}';
    }

    public static enum State {
        WIP, DEV, QA, READY_FOR_RELEASE, RELEASED
    }
}
