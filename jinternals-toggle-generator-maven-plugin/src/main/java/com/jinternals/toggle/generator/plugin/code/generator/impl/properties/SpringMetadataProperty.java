package com.jinternals.toggle.generator.plugin.code.generator.impl.properties;

public class SpringMetadataProperty {
    private String name;
    private String type = "java.lang.Boolean";
    private String sourceType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}
