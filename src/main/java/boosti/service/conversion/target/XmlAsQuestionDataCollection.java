package boosti.service.conversion.target;

import java.io.IOException;
import java.util.Collection;

import boosti.service.conversion.SourceAsTarget;
import boosti.web.model.QuestionData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlAsQuestionDataCollection extends SourceAsTarget<String, Collection<QuestionData>> {

  protected XmlAsQuestionDataCollection(String source) {
    super(source);
  }

  @Override
  public Collection<QuestionData> content() throws IOException {
    return new XmlMapper().readValue(source, new TypeReference<>() {});
  }
}
