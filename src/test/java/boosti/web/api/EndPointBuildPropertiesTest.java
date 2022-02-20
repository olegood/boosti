package boosti.web.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;

class EndPointBuildPropertiesTest {

  EndPointBuildProperties endPointBuildProperties;

  @BeforeEach
  void setUp() {
    var props = new Properties();
    props.put("name", "boosti-web");
    props.put("version", "dev");

    endPointBuildProperties = new EndPointBuildProperties(new BuildProperties(props));
  }

  @Test
  void shouldReturnAllAvailableBuildInfoProperties() {
    // when
    var result = endPointBuildProperties.getBuildInfo();

    // then
    var keySet = result.keySet();
    assertThat(keySet, hasSize(2));
    assertThat(keySet, containsInAnyOrder("name", "version"));
  }
}
