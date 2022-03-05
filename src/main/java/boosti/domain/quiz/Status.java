package boosti.domain.quiz;

/**
 * {@link Quiz} status.
 *
 * @author Oleg Anastassov
 */
public enum Status {

  /**
   * Initial status.
   *
   * <p>Indicates that {@link Quiz} is being edited.
   */
  DRAFT,

  /**
   * {@link Quiz} contains all the questions.
   *
   * <p>And is ready to be exported.
   */
  FINALIZED
}
