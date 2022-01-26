package boosti.service;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boosti.model.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService {

  private Map<String, List<Question>> questions = new HashMap<>();

  public QuestionsService() {}

  public QuestionsService(Map<String, List<Question>> questions) {
    this.questions = questions;
  }

  public void save(Question question) {
    var list = questions.getOrDefault(question.topic(), new ArrayList<>());
    list.add(question);
    questions.putIfAbsent(question.topic(), list);
  }

  public List<Question> getByTopic(String topic) {
    return questions.getOrDefault(topic, emptyList());
  }

  public List<Question> getAll() {
    return questions.values().stream().flatMap(List::stream).collect(toList());
  }
}
