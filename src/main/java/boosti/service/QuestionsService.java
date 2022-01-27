package boosti.service;

import static java.util.Collections.emptySet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import boosti.model.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService {

  private final Map<String, Set<Question>> questions = new HashMap<>();

  public Question save(Question question) {
    var list = questions.getOrDefault(question.topic(), new HashSet<>());
    list.add(question);
    questions.putIfAbsent(question.topic(), list);
    return question;
  }

  public Map<String, Set<Question>> getQuestionsWithTopics() {
    return questions;
  }

  public Set<Question> getByTopic(String topic) {
    return questions.getOrDefault(topic, emptySet());
  }

  public Set<Question> getAll() {
    return questions.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
  }
}
