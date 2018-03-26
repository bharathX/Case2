package ai.swim;

import swim.api.AbstractPlane;
import swim.api.ServiceType;
import swim.api.SwimRoute;
import swim.server.SwimPlane;
import swim.server.SwimServer;

public class App extends AbstractPlane {

  // define the uri for a service with @SwimRoute annotation. Specify dynamic portions of the route with a : prefix
  // All instances of the A service will have a URI of the form /a/:id

  @SwimRoute("/csv/:fileName")
  final ServiceType<?> CsvReaderService = serviceClass(ai.swim.service.CsvReaderService.class);

  public static void main(String[] args) {
    // Instantiate a swim server
    final SwimServer server = new SwimServer();

    // Materialize a swim plane and specify configurations for the plane, here it is the port binding
    final SwimPlane plane = server.materializePlane("plane", App.class);
    plane.bind("0.0.0.0", 9001);
    System.out.println("Listening on port 9001");

    // Run the swim server, this stays alive until termination
    server.run();
  }
}
