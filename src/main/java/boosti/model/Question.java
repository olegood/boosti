package boosti.model;

import java.util.Random;

public record Question(Long id, String topic, String text) {

  public Question(String topic, String text) {
    this(new Random().nextLong(), topic, text);
  }
}
