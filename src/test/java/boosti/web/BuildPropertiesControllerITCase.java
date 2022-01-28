package boosti.web;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(
    properties = "spring.main.banner-mode=off",
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BuildPropertiesControllerITCase {

  @LocalServerPort int randomServerPort;

  @Test
  void shouldReturnAllBuildInfoItems() {
    get("http://localhost:" + randomServerPort + "/api/build-properties")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .assertThat()
        .body(
            "group", is("boosti"),
            "artifact", is("boosti-web"),
            "name", is("Boost TI"),
            "version", is(notNullValue()),
            "timestamp", is(notNullValue()));
  }
}
