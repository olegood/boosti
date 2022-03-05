package boosti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application main runnable class.
 *
 * @author Oleg Anastassov
 */
@SpringBootApplication
public class BoosTIApp {

  /**
   * Running the application.
   *
   * @param args app arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(BoosTIApp.class, args);
  }
}
