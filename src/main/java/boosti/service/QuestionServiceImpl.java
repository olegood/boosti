package boosti.service;

import static java.nio.ByteBuffer.allocate;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Collection;
import java.util.Optional;

import boosti.domain.Question;
import boosti.domain.QuestionRepository;
import boosti.service.conversion.target.QuestionAsByteArray;
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
  public Collection<Question> getAll() {
    return repository.findAll();
  }

  @Override
  public Collection<Question> getAllById(Collection<Long> ids) {
    return repository.findAllById(ids);
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

  public byte[] getAllAsByteArray(Collection<Long> ids) {
    return getAllById(ids).stream()
        .map(QuestionAsByteArray::new)
        .map(QuestionAsByteArray::content)
        .reduce(
            new byte[] {},
            (one, two) ->
                allocate(one.length + two.length + 1).put(one).put((byte) '\n').put(two).array());
  }
}
