package boosti.service.conversion.target;

import java.util.Collection;

import boosti.service.conversion.SourceAsTarget;
import boosti.web.model.QuestionData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonAsQuestionDataCollection extends SourceAsTarget<String, Collection<QuestionData>> {

  protected JsonAsQuestionDataCollection(String source) {
    super(source);
  }

  @Override
  public Collection<QuestionData> content() throws JsonProcessingException {
    return new ObjectMapper().readValue(source, new TypeReference<>() {});
  }
}
