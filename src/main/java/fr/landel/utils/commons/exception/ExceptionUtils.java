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
     * @param exception
     *            the exception
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
