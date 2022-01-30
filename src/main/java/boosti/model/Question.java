package boosti.model;

public record Question(Long id, String topic, String text) {

  public Question(String topic, String text) {
    this(generateId(), topic, text);
  }

  private static long generateId() {
    long leftLimit = 1L;
    long rightLimit = 1000L;
    return  leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
  }
}
