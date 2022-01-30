package boosti.service;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

import boosti.model.Question;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService {

  private final Map<String, Set<Question>> questions = new HashMap<>();

  public Question save(Question origin) {
    var question = fillQuestionId(origin);
    var list = questions.getOrDefault(question.topic(), new HashSet<>());
    list.add(question);
    questions.putIfAbsent(question.topic(), list);
    return question;
  }

  private Question fillQuestionId(Question question) {
    return question.id() == null ? new Question(question.topic(), question.text()) : question;
  }

  public Map<String, Set<Question>> getQuestionsWithTopics() {
    return questions;
  }

  public List<Question> getById(Collection<Long> ids) {
    return getAll().stream().filter(it -> ids.contains(it.id())).toList();
  }

  public Set<Question> getByTopic(String topic) {
    return questions.getOrDefault(topic, emptySet());
  }

  public Set<Question> getAll() {
    return questions.values().stream().flatMap(Set::stream).collect(toSet());
  }
}
