package org.mx.jairaviles.processlistservice;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mx.jairaviles.processlistservice.application.ProcessListResource;
import org.mx.jairaviles.processlistservice.configuration.ProcessListServiceConfiguration;

public class ProcessListServiceApplication extends Application<ProcessListServiceConfiguration> {

  public static void main(String []args) throws Exception {
    new ProcessListServiceApplication().run(args);
  }

  @Override
  public String getName() {
    return "process-list-service";
  }

  @Override
  public void initialize(Bootstrap<ProcessListServiceConfiguration> boostrap) {}

  @Override
  public void run(ProcessListServiceConfiguration configuration, Environment environment) {
    final ProcessListResource resource = new ProcessListResource(
        configuration.getMaxLength()
    );
    environment.jersey().register(resource);
  }
}
