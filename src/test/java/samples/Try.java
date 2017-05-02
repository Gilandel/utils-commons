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
package samples;

import java.util.Objects;
import java.util.Optional;

import fr.landel.utils.commons.CastUtils;
import fr.landel.utils.commons.ClassUtils;
import fr.landel.utils.commons.function.ThrowableSupplier;

/**
 * Just a try test for {@link ThrowableSupplier}
 * 
 * <p>
 * Not good for production, because this hides bugs. (can only be used, if a
 * library throws {@link Exception} or {@link Throwable}...)
 * </p>
 *
 * @since Aug 11, 2016
 * @author Gilles
 *
 */
public class Try {

    public static <E extends Throwable> Optional<Catch<E>> that(final ThrowableSupplier<E> exceptionSupplier) {
        Objects.requireNonNull(exceptionSupplier, "exceptionSupplier");

        try {
            exceptionSupplier.throwException();
        } catch (final Throwable e) {
            return Optional.of(new Catch<E>(CastUtils.cast(e)));
        }
        return Optional.empty();
    }

    public static class Catch<E extends Throwable> {
        private final E exception;
        private final Class<E> clazz;

        private Catch(final E exception) {
            this.exception = exception;
            this.clazz = ClassUtils.getClass(exception);
        }

        public boolean is(final Class<?> clazz) {
            return Objects.requireNonNull(clazz, "clazz").isAssignableFrom(this.clazz);
        }

        public boolean has(final String message) {
            return Objects.equals(this.exception.getMessage(), message);
        }

        public E get() {
            return this.exception;
        }
    }
}
