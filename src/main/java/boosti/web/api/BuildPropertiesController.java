package boosti.web.api;

import java.util.Properties;

import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/build-properties")
public class BuildPropertiesController {

  private final BuildProperties build;

  public BuildPropertiesController(BuildProperties build) {
    this.build = build;
  }

  @GetMapping
  public Properties getBuildInfo() {
    final var props = new Properties();
    build.forEach(entry -> props.putIfAbsent(entry.getKey(), entry.getValue()));
    return props;
  }
}
