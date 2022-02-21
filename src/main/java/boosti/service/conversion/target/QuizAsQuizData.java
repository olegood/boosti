package boosti.service.conversion.target;

import boosti.domain.quiz.Quiz;
import boosti.service.conversion.SourceAsTarget;
import boosti.web.model.QuizData;
import org.modelmapper.ModelMapper;

public class QuizAsQuizData extends SourceAsTarget<Quiz, QuizData> {

  public QuizAsQuizData(Quiz source) {
    super(source);
  }

  @Override
  public QuizData content() {
    return new ModelMapper().map(source, QuizData.class);
  }
}
