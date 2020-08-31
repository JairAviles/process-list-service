package org.mx.jairaviles.processlistservice.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class ProcessListServiceConfiguration extends Configuration {
    private int maxLength;

    @JsonProperty
    public int getMaxLength() {
        return maxLength;
    }
    @JsonProperty
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
