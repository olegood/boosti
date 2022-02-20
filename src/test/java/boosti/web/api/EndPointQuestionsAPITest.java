package boosti.web.api;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(
    properties = "spring.main.banner-mode=off",
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EndPointQuestionsAPITest {

  @LocalServerPort int randomServerPort;

  @Test
  void shouldSaveQuestionAndEnsureItIsReturnedBack() {
    // given
    var body = """
            {
              "category": {
                "id": 13
              },
              "text": "What is the difference between '.kt' and '.kts' files?"
            }
            """;

    // when
    given()
        .contentType(ContentType.JSON)
        .body(body)
        .when()
        .post("http://localhost:" + randomServerPort + "/api/questions")
        .then()
        .statusCode(HttpStatus.SC_CREATED)
        .body("text", is("What is the difference between '.kt' and '.kts' files?"));

    // then
    get("http://localhost:" + randomServerPort + "/api/questions")
        .then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test
  void shouldReturnOkAndEmptyQuestionsList() {
    get("http://localhost:" + randomServerPort + "/api/questions")
        .then()
        .statusCode(HttpStatus.SC_OK);
  }
}
