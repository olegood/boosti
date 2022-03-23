package boosti.domain.quiz

import groovy.transform.CompileStatic
import spock.lang.Specification

@CompileStatic
class QuizSpec extends Specification {

  def 'initially created Quiz should be in Draft state'() {
    expect:
    new Quiz().status == Status.DRAFT
  }

}
