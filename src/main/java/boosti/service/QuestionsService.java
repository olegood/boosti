package boosti.service;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import boosti.model.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService {

  private Set<Question> questions = new HashSet<>();

  public Question save(Question origin) {
    var question = fillQuestionId(origin);
    questions.add(fillQuestionId(origin));
    return question;
  }

  private Question fillQuestionId(Question question) {
    return question.id() == null ? new Question(question.topic(), question.text()) : question;
  }

  public List<Question> getByIds(Collection<Long> ids) {
    return questions.stream().filter(it -> ids.contains(it.id())).collect(toList());
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

  public Set<Question> deleteAll() {
    questions = new HashSet<>();
    return questions;
  }
}
