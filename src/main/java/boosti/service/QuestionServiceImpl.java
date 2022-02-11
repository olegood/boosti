package boosti.service;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Collection;
import java.util.Optional;

import boosti.domain.Question;
import boosti.domain.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository repository;

  public QuestionServiceImpl(QuestionRepository repository) {
    this.repository = repository;
  }

  @Override
  public Question save(Question question) {
    return repository.save(question);
  }

  @Override
  public Collection<Question> getByTopic(String topic) {
    return repository.findByTopic(topic);
  }

  @Override
  public Collection<Question> getAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Question> deleteById(Long id) {
    var question = repository.findById(id);
    question.ifPresent(repository::delete);
    return question;
  }

  @Override
  public void deleteAllById(Collection<Long> ids) {
    if (!isEmpty(ids)) {
      repository.deleteAllById(ids);
    }
  }
}
