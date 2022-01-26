package boosti.web;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class QuestionsControllerTest {

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
        .post("http://localhost:8082/api/questions")
        .then()
        .statusCode(HttpStatus.SC_CREATED)
        .body(
            "topic", is("Kotlin"),
            "text", is("What is the difference between '.kt' and '.kts' files?"));

    // then
    get("http://localhost:8082/api/questions")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .assertThat()
        .body("", hasSize(1));
  }

  @Test
  void shouldReturnOkAndEmptyQuestionsList() {
    get("http://localhost:8082/api/questions")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .assertThat()
        .body("", hasSize(0));
  }
}
