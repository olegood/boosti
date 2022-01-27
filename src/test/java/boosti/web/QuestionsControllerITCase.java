package boosti.web;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionsControllerITCase {

  @LocalServerPort
  int randomServerPort;

  @Test
  void shouldSaveQuestionAndEnsureItIsReturnedBack() {
    // given
    var body = """
      {
        "topic": "Kotlin",
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
        .body(
            "topic", is("Kotlin"),
            "text", is("What is the difference between '.kt' and '.kts' files?"));

    // then
    get("http://localhost:" + randomServerPort + "/api/questions")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .assertThat()
        .body("", hasSize(1));
  }

  @Test
  void shouldReturnOkAndEmptyQuestionsList() {
    get("http://localhost:" + randomServerPort + "/api/questions")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .assertThat()
        .body("", hasSize(0));
  }
}
