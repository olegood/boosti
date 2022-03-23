package boosti.service.conversion.target

import boosti.web.model.QuestionData
import org.hamcrest.Matchers
import spock.lang.Specification

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertThrows

/**
 * Mimic version of the same test written in JUnit 5.
 *
 * @author Oleg Anastassov
 * @see StringAsQuestionDataTest
 */
class StringAsQuestionDataSpec extends Specification {

    def "should return singleton list for one valid question"() {
        setup:
        def questionAsData = new StringAsQuestionData("Java,What is JVM?")
        def expected = QuestionData.builder().withText("What is JVM?").build()

        when:
        def result = questionAsData.content();

        then:
        result is(expected)
    }

    def "should throw exception if input value has no separator"() {
        setup:
        def questionAsData = new StringAsQuestionData("Value that does not contain separator")

        when:
        def exception = assertThrows(RuntimeException.class, questionAsData::content)

        then:
        def expectedMessage =
                "Input value cannot be parsed, separator=, source=Value that does not contain separator"
        def actualMessage = exception.getMessage();

        assertThat(actualMessage, Matchers.is(expectedMessage))
    }

    def "should throw exception when topic or text are empty"() {
        setup:
        def questionAsData = new StringAsQuestionData(source)

        when:
        def exception = assertThrows(RuntimeException.class, questionAsData::content)

        then:
        def expectedMessage = "Could not create 'Question' from empty values."
        def actualMessage = exception.getMessage();

        assertThat(actualMessage, Matchers.is(expectedMessage))

        where:
        source                | _
        " , <topic is empty>" | _
        " <text is empty> , " | _
        "     ,       "       | _
    }
}
