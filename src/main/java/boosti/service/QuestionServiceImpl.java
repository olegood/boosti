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

  private final QuestionRepository questionRepo;

  public QuestionServiceImpl(QuestionRepository questionRepo) {
    this.questionRepo = questionRepo;
  }

  @Override
  public Question save(Question question) {
    return questionRepo.save(question);
  }

  @Override
  public Optional<Question> getById(Long id) {
    return questionRepo.findById(id);
  }

  @Override
  public Collection<Question> getAll() {
    return questionRepo.findAll();
  }

  @Override
  public Collection<Question> getAllById(Collection<Long> ids) {
    return questionRepo.findAllById(ids);
  }

  @Override
  public void deleteAll() {
    questionRepo.deleteAll();
  }

  @Override
  public Optional<Question> deleteById(Long id) {
    var question = questionRepo.findById(id);
    question.ifPresent(questionRepo::delete);
    return question;
  }

  @Override
  public void deleteAllById(Collection<Long> ids) {
    if (!isEmpty(ids)) {
      questionRepo.deleteAllById(ids);
    }
  }

  @Override
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
