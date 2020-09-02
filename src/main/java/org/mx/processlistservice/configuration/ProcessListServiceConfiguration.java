package org.mx.processlistservice.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;

public class ProcessListServiceConfiguration extends Configuration {
    private int maxLength;

    public ProcessListServiceConfiguration() {
        super();
        ((HttpConnectorFactory) ((DefaultServerFactory) getServerFactory()).
            getApplicationConnectors().get(0)).setPort(0);

        ((HttpConnectorFactory) ((DefaultServerFactory) getServerFactory()).
            getAdminConnectors().get(0)).setPort(0);
    }

    @JsonProperty
    public int getMaxLength() {
        return maxLength;
    }
    @JsonProperty
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
