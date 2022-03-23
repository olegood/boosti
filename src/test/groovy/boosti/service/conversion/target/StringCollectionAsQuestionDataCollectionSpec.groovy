package boosti.service.conversion.target

import boosti.web.model.QuestionData
import spock.lang.Specification

import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.is

class StringCollectionAsQuestionDataCollectionSpec extends Specification {

    def "should parse correct using simple separator"() {
        setup:
        def collectionQuestionData = new StringCollectionAsQuestionDataCollection(source)

        when:
        def result = collectionQuestionData.content()

        then:
        result hasSize(expectedResult.size())
        result is(expectedResult)

        where:
        source                                                                   | expectedResult
        ['Design Patterns,,,Could you please describe Strategy design pattern?'] | singletonQuestionData(',,Could you please describe Strategy design pattern?')
        ['Java,What is JVM?']                                                    | singletonQuestionData('What is JVM?')
        ['SQL  ,What is DDL? ']                                                  | singletonQuestionData('What is DDL?')
    }

    private def singletonQuestionData(String text) {
        [QuestionData.builder().withText(text).build()]
    }

}
