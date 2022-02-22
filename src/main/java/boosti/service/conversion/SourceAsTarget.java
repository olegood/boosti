package boosti.service.conversion;

/**
 * Represents the source in target data format.
 *
 * @param <S> type of source
 * @param <T> target type
 */
public abstract class SourceAsTarget<S, T> {

  protected final S source;

  protected SourceAsTarget(S source) {
    this.source = source;
  }

  /**
   * Converted value of the source.
   *
   * @return content in target data format
   */
  public abstract T content() throws Exception;
}
