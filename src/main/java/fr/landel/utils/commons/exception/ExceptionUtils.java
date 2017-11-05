/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilles Landel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package fr.landel.utils.commons.exception;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import fr.landel.utils.commons.ObjectUtils;
import fr.landel.utils.commons.StringUtils;

/**
 * Utility class to manage exception
 *
 * @since Apr 25, 2017
 * @author Gilles
 *
 */
public final class ExceptionUtils extends org.apache.commons.lang3.exception.ExceptionUtils {

    /**
     * Hidden constructor
     */
    private ExceptionUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception if the predicate NOT matches
     * 
     * <pre>
     * String key = "test";
     * // ...
     * ExceptionUtils.throwsException(key, StringUils::isNotEmpty, IllegalArgumentException::new, null, "error on key");
     * </pre>
     * 
     * @param object
     *            the object to check
     * @param predicate
     *            the predicate that validates the object
     * @param supplier
     *            the exception supplier
     * @param locale
     *            the message locale (optional)
     * @param message
     *            the error message (required)
     * @param arguments
     *            the message arguments
     * @param <T>
     *            the object type
     * @param <E>
     *            the exception type
     * @throws E
     *             if predicate matches
     */
    public static <T, E extends Throwable> void throwsException(final T object, final Predicate<T> predicate,
            final Function<String, E> supplier, final Locale locale, final String message, final Object... arguments) throws E {
        Objects.requireNonNull(predicate, "predicate");
        Objects.requireNonNull(supplier, "supplier");
        Objects.requireNonNull(message, "message");

        if (!predicate.test(object)) {
            throw supplier.apply(StringUtils.format(locale, message, new Object[] {object}, arguments));
        }
    }

    /**
     * Creates a supplier of the exception and injects the message
     * 
     * <pre>
     * Optional.of(value).orElseThrow(ExceptionUtils.suppliesException(IllegalArgumentException::new, Locale.FRENCH,
     *         "La valeur n'est pas comprise entre %,.3f et %,.3f", min, max));
     * </pre>
     * 
     * @param supplier
     *            the exception supplier (required)
     * @param locale
     *            the message locale (optional)
     * @param message
     *            the error message (required)
     * @param arguments
     *            the message arguments
     * @param <E>
     *            the exception type
     * @return the exception supplier
     */
    public static <E extends Throwable> Supplier<E> suppliesException(final Function<String, E> supplier, final Locale locale,
            final String message, final Object... arguments) {
        Objects.requireNonNull(supplier, "supplier");
        Objects.requireNonNull(message, "message");

        return () -> supplier
                .apply(String.format(ObjectUtils.defaultIfNull(locale, Locale.getDefault(Locale.Category.FORMAT)), message, arguments));
    }

    /**
     * Emulates the exception throw. The aim is to catch an hidden exception
     * (mainly to avoid to catch {@link Exception}).
     * 
     * <pre>
     * try {
     *     functionThatThrowsAnHiddenException();
     * 
     *     ExceptionUtils.catchable(HiddenException.class);
     * } catch (HiddenException e) {
     *     // ...
     * }
     * </pre>
     * 
     * @param exceptionType
     *            the exception type
     * @param <E>
     *            the type of exception
     * @throws E
     *             the throwable type (never thrown)
     * @throws NullPointerException
     *             if {@code exceptionType} is {@code null}
     */
    public static <E extends Throwable> void catchable(final Class<E> exceptionType) throws E {
        Objects.requireNonNull(exceptionType, "exceptionType");
    }

    /**
     * Returns the original cause. Checks recursively until the root cause was
     * found.
     * 
     * <pre>
     * ExceptionUtils.getCauseOrigin(new IllegalArgumentException("param error"));
     * // -&gt; the instance of IllegalArgumentException
     * 
     * ExceptionUtils.getCauseOrigin(new IllegalArgumentException(new IOException("access error")));
     * // -&gt; the instance of IOException
     * </pre>
     * 
     * @param exception
     *            the exception (required, not {@code null})
     * @return the cause, if no cause was found, returns the exception
     */
    public static Throwable getCauseOrigin(final Throwable exception) {
        Throwable cause = Objects.requireNonNull(exception, "exception");
        Throwable origin = null;

        while ((cause = cause.getCause()) != null) {
            origin = cause;
        }

        return ObjectUtils.defaultIfNull(origin, exception);
    }
}
