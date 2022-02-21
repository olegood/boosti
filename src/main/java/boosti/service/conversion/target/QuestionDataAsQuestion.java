package boosti.service.conversion.target;

import boosti.domain.Question;
import boosti.service.conversion.SourceAsTarget;
import boosti.web.model.QuestionData;
import org.modelmapper.ModelMapper;

public class QuestionDataAsQuestion extends SourceAsTarget<QuestionData, Question> {

  public QuestionDataAsQuestion(QuestionData source) {
    super(source);
  }

  @Override
  public Question content() {
    return new ModelMapper().map(source, Question.class);
  }
}
