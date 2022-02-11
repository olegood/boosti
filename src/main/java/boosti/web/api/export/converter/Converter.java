package boosti.web.api.export.converter;

import java.util.Collection;
import java.util.function.Function;

import boosti.domain.Question;

/**
 * Converts collection of {@link Question} to target type.
 *
 * @param <T> target type
 */
public interface Converter<T> extends Function<Collection<Question>, T> {}
