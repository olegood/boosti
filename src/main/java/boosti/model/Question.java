package boosti.model;

import java.util.Objects;

public record Question(Long id, String topic, String text) {

  public Question(String topic, String text) {
    this(generateId(), topic, text);
  }

  private static long generateId() {
    long leftLimit = 1L;
    long rightLimit = 1000L;
    return  leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Question question) {
      return Objects.equals(this.topic, question.topic) && Objects.equals(this.text, question.text);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(topic, text);
  }
}
