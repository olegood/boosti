package boosti.service.conversion.target;

import boosti.domain.Question;
import boosti.service.conversion.SourceAsTarget;
import boosti.web.model.QuestionData;
import org.modelmapper.ModelMapper;

public class QuestionAsQuestionData extends SourceAsTarget<Question, QuestionData> {

  public QuestionAsQuestionData(Question source) {
    super(source);
  }

  @Override
  public QuestionData content() {
    return new ModelMapper().map(source, QuestionData.class);
  }
}
