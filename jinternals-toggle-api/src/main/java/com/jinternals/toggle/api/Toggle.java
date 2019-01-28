package com.jinternals.toggle.api;

public class Toggle {

    private String name;
    private String description;
    private State state;

    public Toggle() {
    }

    public Toggle(String name, String description,State state) {
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
    public String toString() {
        return "Toggle {" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                '}';
    }

    public static enum State {
        WIP, DEV, QA, READY_FOR_RELEASE, RELEASED
    }
}
