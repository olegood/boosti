package boosti.web.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@Disabled
@SpringBootTest(
    properties = "spring.main.banner-mode=off",
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EndPointBuildPropertiesAPITest {

  @LocalServerPort int randomServerPort;

  @Test
  void shouldReturnAllBuildInfoItems() {
    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .when()
        .get("http://localhost:" + randomServerPort + "/api/build-properties")
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
