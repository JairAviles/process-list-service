import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.AbstractNetworkConnector;

public class ProcessListServiceApplication extends Application<ProcessListServiceConfiguration> {
  Environment environment;
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
    this.environment = environment;
    environment.jersey().register(resource);
  }

  public int getPort() {
    return ((AbstractNetworkConnector) environment.getApplicationContext()
    .getServer().getConnectors()[0]).getLocalPort();
  }
}
