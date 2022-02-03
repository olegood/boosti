package boosti.service;

import boosti.model.Question;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService {

  private final Set<Question> questions = new HashSet<>();

  public Question save(Question origin) {
    var question = fillQuestionId(origin);
    questions.add(fillQuestionId(origin));
    return question;
  }

  private Question fillQuestionId(Question question) {
    return question.id() == null ? new Question(question.topic(), question.text()) : question;
  }

  public Map<String, Set<Question>> getQuestionsWithTopics() {
    return questions.stream().collect(Collectors.groupingBy(Question::topic, Collectors.toSet()));
  }

  public List<Question> getById(Collection<Long> ids) {
    return getAll().stream().filter(it -> ids.contains(it.id())).toList();
  }

  public Set<Question> getByTopic(String topic) {
    return questions.stream()
        .filter(question -> topic.equals(question.topic()))
        .collect(Collectors.toSet());
  }

  public Set<Question> getAll() {
    return questions;
  }

  public Optional<Question> delete(Long id) {
    Predicate<Question> matchById = question -> Objects.equals(question.id(), id);
    var questionToDelete = questions.stream().filter(matchById).findAny();
    questions.removeIf(matchById);
    return questionToDelete;
  }
}
