package fr.landel.utils.commons.function;

import java.util.Objects;

import fr.landel.utils.commons.CastUtils;

/**
 * Interface utility to rethrow an exception
 *
 * @since Apr 17, 2017
 * @author Gilles
 */
public interface Rethrower {

    /**
     * Rethrow an exception as unchecked
     * 
     * @param throwable
     *            the exception to throw
     * @param <X>
     *            the throwable type
     * @throws X
     *             the provided exception if not {@code null}
     */
    default <X extends Throwable> void rethrowUnchecked(final Throwable throwable) throws X {
        throw CastUtils.<X> cast(Objects.requireNonNull(throwable, "throwable"));
    }
}
