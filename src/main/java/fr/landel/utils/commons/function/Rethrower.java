/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilandel
 * %%
 * Authors: Gilles Landel
 * URL: https://github.com/Gilandel
 * 
 * This file is under Apache License, version 2.0 (2004).
 * #L%
 */
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
