package com.jinternals.toggle.generator.plugin.code.generator.impl.properties;

import java.util.ArrayList;
import java.util.List;

public class SpringMetadata {

    private List<SpringMetadataProperty> properties = new ArrayList<>();
    private List hists = new ArrayList();

    public List<SpringMetadataProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<SpringMetadataProperty> properties) {
        this.properties = properties;
    }

    public List getHists() {
        return hists;
    }

    public void setHists(List hists) {
        this.hists = hists;
    }

}
