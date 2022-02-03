package boosti.web.api;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildPropertiesController {

  private final BuildProperties build;

  @Autowired
  public BuildPropertiesController(BuildProperties build) {
    this.build = build;
  }

  @GetMapping("/api/build-properties")
  public Properties getBuildInfo() {
    final var props = new Properties();
    build.forEach(entry -> props.putIfAbsent(entry.getKey(), entry.getValue()));
    return props;
  }
}
