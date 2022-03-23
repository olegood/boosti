package boosti.domain.quiz

import spock.lang.Specification

class QuizSpec extends Specification {

  def 'initially created Quiz should be in Draft state'() {
    expect:
    new Quiz().status == Status.DRAFT
  }

}
