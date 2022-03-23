package boosti.service

import boosti.domain.Question
import boosti.repo.QuestionRepository
import spock.lang.Specification

import static org.hamcrest.Matchers.hasSize

class QuestionServiceImplSpec extends Specification {

  def questionRepo = Stub(QuestionRepository)
  def questionService = new QuestionServiceImpl(questionRepo)

  def 'service should return all questions from repository'() {
    questionRepo.findAll() >> [
        new Question(id: 41L, text: "What is JVM"),
        new Question(id: 42L, text: "What is JRE"),
        new Question(text: "What is Garbage Collector"),
        new Question(text: "What is BOM (Bill of materials)")
    ]

    when:
    def result = questionService.getAll()

    then:
    result hasSize(4)
  }

}
