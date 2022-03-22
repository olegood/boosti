package boosti.domain.quiz


import spock.lang.Specification

class QuizSpec extends Specification {

    def "initially crated Quiz should be in Draft state"() {
        when:
        def quiz = new Quiz()

        then:
        quiz.status == Status.DRAFT
    }

}
