package boosti.web.api

import boosti.domain.Question
import boosti.service.QuestionService
import org.springframework.http.HttpStatus
import spock.lang.Specification

class EndPointQuestionsSpec extends Specification {

  def mockService = Stub(QuestionService)
  def endPointQuestions = new EndPointQuestions(mockService)

  def 'should return HTTP 404 (NOT FOUND) if question not found'() {
    setup:
    mockService.getById(_ as Long) >> Optional.empty()

    when:
    def response = endPointQuestions.getQuestion(42L)

    then:
    response.statusCode == HttpStatus.NOT_FOUND
  }

  def 'should return HTTP 200 (OK) if question is found'() {
    setup:
    mockService.getById(_ as Long) >> Optional.of(new Question())

    when:
    def response = endPointQuestions.getQuestion(42L)

    then:
    response.statusCode == HttpStatus.OK
  }

  def 'should contain self link'() {
    setup:
    mockService.getById(_ as Long) >> Optional.of(new Question())

    when:
    def response = endPointQuestions.getQuestion(42L)

    then:
    with(response.body) {
      links.size() == 2

      links.getRequiredLink("self")
      links.getRequiredLink("deleteQuestion")
    }
  }

}
