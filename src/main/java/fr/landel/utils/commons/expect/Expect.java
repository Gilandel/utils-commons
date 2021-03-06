/*
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2018 Gilles Landel
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
package fr.landel.utils.commons.expect;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import fr.landel.utils.commons.StringUtils;
import fr.landel.utils.commons.function.ThrowableSupplier;
import fr.landel.utils.commons.function.TriFunction;

/**
 * Assertion utility class that assists in validating thrown exception.
 *
 * @author Gilles
 */
public final class Expect {

    private static final String ERROR_CONSUMER_NULL = "Consumer cannot be null";
    private static final String ERROR_EXPECTED_NULL = "Expected exception cannot be null";
    private static final String ERROR_NO_EXCEPTION = "No exception thrown";
    private static final String ERROR_EXCEPTION_DONT_MATCH = "The expected exception never came up (thrown: {}).";
    private static final String ERROR_MESSAGE_DONT_MATCH = "The exception message isn't as expected.\nExpected (first part) and result (second part):\n{}\n-----\n{}";
    private static final String ERROR_PREDICATE = "predicate";

    /**
     * Hidden constructor
     */
    private Expect() {
        throw new UnsupportedOperationException();
    }

    /**
     * Check that the consumed code raises the specified exception.
     * 
     * <pre>
     * Expect.exception(() -&gt; {
     *     // throw new IllegalArgumentException("parameter cannot be null");
     *     getMyType(null);
     * }, IllegalArgumentException.class);
     * </pre>
     * 
     * @param consumer
     *            The consumer (required, not null)
     * @param expectedException
     *            The expected exception type (required, not null)
     * @param <T>
     *            The generic expected exception type
     */
    public static <T extends Throwable> void exception(final ThrowableSupplier<Throwable> consumer, final Class<T> expectedException) {
        exception(consumer, expectedException, null, null, null, null);
    }

    /**
     * Check that the consumed code raises the specified exception, also check
     * the message.
     * 
     * <pre>
     * Expect.exception(() -&gt; {
     *     // throw new IllegalArgumentException("parameter cannot be null");
     *     getMyType(null);
     * }, IllegalArgumentException.class, "parameter cannot be null");
     * </pre>
     * 
     * @param consumer
     *            The consumer (required, not null)
     * @param expectedException
     *            The expected exception type (required, not null)
     * @param expectedMessage
     *            The expected exception message
     * @param <T>
     *            The generic expected exception type
     */
    public static <T extends Throwable> void exception(final ThrowableSupplier<Throwable> consumer, final Class<T> expectedException,
            final String expectedMessage) {

        exception(consumer, expectedException, expectedMessage, null);
    }

    /**
     * Check that the consumed code raises the specified exception, also check
     * the message with the specified pattern.
     * 
     * <pre>
     * Expect.exception(() -&gt; {
     *     // throw new IllegalArgumentException("parameter cannot be null");
     *     getMyType(null);
     * }, IllegalArgumentException.class, Pattern.compile("^parameter"));
     * </pre>
     * 
     * @param consumer
     *            The consumer (required, not null)
     * @param expectedException
     *            The expected exception type (required, not null)
     * @param messagePattern
     *            The message pattern
     * @param <T>
     *            The generic expected exception type
     */
    public static <T extends Throwable> void exception(final ThrowableSupplier<Throwable> consumer, final Class<T> expectedException,
            final Pattern messagePattern) {

        exception(consumer, expectedException, messagePattern, null);
    }

    /**
     * Check that the consumed code raises the specified exception, also check
     * the message with the specified pattern.
     * 
     * <pre>
     * Expect.exception(() -&gt; {
     *     // throw new IllegalArgumentException("parameter cannot be null");
     *     getMyType(null);
     * }, IllegalArgumentException.class, StringUtils::isNotEmpty);
     * </pre>
     * 
     * @param consumer
     *            The consumer (required, not null)
     * @param expectedException
     *            The expected exception type (required, not null)
     * @param messageChecker
     *            The message checker
     * @param <T>
     *            The generic expected exception type
     */
    public static <T extends Throwable> void exception(final ThrowableSupplier<Throwable> consumer, final Class<T> expectedException,
            final Predicate<String> messageChecker) {

        exception(consumer, expectedException, messageChecker, null);
    }

    /**
     * Check that the consumed code raises the specified exception and allow to
     * change the thrown exception.
     * 
     * <pre>
     * // Obviously, you can save this in a static variable to share it
     * TriFunction&lt;Boolean, String, String&gt; junitError = (catched, expected, actual) -&gt; {
     *     if (catched) {
     *         return new ComparisonFailure("The exception message don't match.", expected, actual);
     *     } else {
     *         return new AssertionError("The expected exception never came up");
     *     }
     * };
     * 
     * Expect.exception(() -&gt; {
     *     // throw new IllegalArgumentException("parameter cannot be null");
     *     getMyType(null);
     * }, IllegalArgumentException.class, junitError);
     * 
     * // ComparisonFailure come from: org.junit.ComparisonFailure
     * </pre>
     * 
     * @param consumer
     *            The consumer (required, not null)
     * @param expectedException
     *            The expected exception type (required, not null)
     * @param exceptionFunction
     *            The exception function (three parameters are injected: (first:
     *            if it's the expected exception), (second: the expected
     *            message) and (third: the actual message), the return has to be
     *            a {@link Throwable}). If the exceptions don't match, the
     *            {@link String} parameters are {@code null}}.
     * @param <T>
     *            The generic expected exception type
     * @param <E>
     *            The exception thrown if the expected exception isn't raised
     * @throws E
     *             Exception provided
     */
    public static <T extends Throwable, E extends Throwable> void exception(final ThrowableSupplier<Throwable> consumer,
            final Class<T> expectedException, final TriFunction<Boolean, String, String, E> exceptionFunction) throws E {

        exception(consumer, expectedException, null, null, null, exceptionFunction);
    }

    /**
     * Check that the consumed code raises the specified exception, also check
     * the message and allow to change the thrown exception.
     * 
     * <pre>
     * // Obviously, you can save this in a static variable to share it
     * TriFunction&lt;Boolean, String, String&gt; junitError = (catched, expected, actual) -&gt; {
     *     if (catched) {
     *         return new ComparisonFailure("The exception message don't match.", expected, actual);
     *     } else {
     *         return new AssertionError("The expected exception never came up");
     *     }
     * };
     * 
     * Expect.exception(() -&gt; {
     *     // throw new IllegalArgumentException("parameter cannot be null");
     *     getMyType(null);
     * }, IllegalArgumentException.class, "parameter cannot be null", junitError);
     * 
     * // ComparisonFailure come from: org.junit.ComparisonFailure
     * </pre>
     * 
     * @param consumer
     *            The consumer (required, not null)
     * @param expectedException
     *            The expected exception type (required, not null)
     * @param expectedMessage
     *            The expected exception message
     * @param exceptionFunction
     *            The exception function (three parameters are injected: (first:
     *            if it's the expected exception), (second: the expected
     *            message) and (third: the actual message), the return has to be
     *            a {@link Throwable}). If the exceptions don't match, the
     *            {@link String} parameters are {@code null}}.
     * @param <T>
     *            The generic expected exception type
     * @param <E>
     *            The exception thrown if the expected exception isn't raised
     * @throws E
     *             Provided exception
     */
    public static <T extends Throwable, E extends Throwable> void exception(final ThrowableSupplier<Throwable> consumer,
            final Class<T> expectedException, final String expectedMessage, final TriFunction<Boolean, String, String, E> exceptionFunction)
            throws E {

        Expect.exception(consumer, expectedException, expectedMessage, null, null, exceptionFunction);
    }

    /**
     * Check that the consumed code raises the specified exception, also check
     * the message and allow to change the thrown exception.
     * 
     * <pre>
     * // Obviously, you can save this in a static variable to share it
     * TriFunction&lt;Boolean, String, String&gt; junitError = (catched, expected, actual) -&gt; {
     *     if (catched) {
     *         return new ComparisonFailure("The exception message don't match.", expected, actual);
     *     } else {
     *         return new AssertionError("The expected exception never came up");
     *     }
     * };
     * 
     * Expect.exception(() -&gt; {
     *     // throw new IllegalArgumentException("parameter cannot be null");
     *     getMyType(null);
     * }, IllegalArgumentException.class, StringUtils::isNotEmpty, junitError);
     * 
     * // ComparisonFailure come from: org.junit.ComparisonFailure
     * </pre>
     * 
     * @param consumer
     *            The consumer (required, not null)
     * @param expectedException
     *            The expected exception type (required, not null)
     * @param messageChecker
     *            The message checker
     * @param exceptionFunction
     *            The exception function (three parameters are injected: (first:
     *            if it's the expected exception), (second: the expected
     *            message) and (third: the actual message), the return has to be
     *            a {@link Throwable}). If the exceptions don't match, the
     *            {@link String} parameters are {@code null}}.
     * @param <T>
     *            The generic expected exception type
     * @param <E>
     *            The exception thrown if the expected exception isn't raised
     * @throws E
     *             Provided exception
     */
    public static <T extends Throwable, E extends Throwable> void exception(final ThrowableSupplier<Throwable> consumer,
            final Class<T> expectedException, final Predicate<String> messageChecker,
            final TriFunction<Boolean, String, String, E> exceptionFunction) throws E {

        Expect.exception(consumer, expectedException, null, null, messageChecker, exceptionFunction);
    }

    /**
     * Check that the consumed code raises the specified exception, also check
     * the message with the specified pattern and allow to change the thrown
     * exception.
     * 
     * <pre>
     * // Obviously, you can save this in a static variable to share it
     * TriFunction&lt;Boolean, String, String&gt; junitError = (catched, expected, actual) -&gt; {
     *     if (catched) {
     *         return new ComparisonFailure("The exception message don't match.", expected, actual);
     *     } else {
     *         return new AssertionError("The expected exception never came up");
     *     }
     * };
     * 
     * Expect.exception(() -&gt; {
     *     // throw new IllegalArgumentException("parameter cannot be null");
     *     getMyType(null);
     * }, IllegalArgumentException.class, Pattern.compile("^parameter"), junitError);
     * 
     * // ComparisonFailure come from: org.junit.ComparisonFailure
     * </pre>
     * 
     * @param consumer
     *            The consumer (required, not null)
     * @param expectedException
     *            The expected exception type (required, not null)
     * @param messagePattern
     *            The message pattern
     * @param exceptionFunction
     *            The exception function (three parameters are injected: (first:
     *            if it's the expected exception), (second: the expected pattern
     *            message) and (third: the actual message), the return has to be
     *            a {@link Throwable}). If the exceptions don't match, the
     *            {@link String} parameters are {@code null}}.
     * @param <T>
     *            The generic expected exception type
     * @param <E>
     *            The exception thrown if the expected exception isn't raised
     * @throws E
     *             Provided exception
     */
    public static <T extends Throwable, E extends Throwable> void exception(final ThrowableSupplier<Throwable> consumer,
            final Class<T> expectedException, final Pattern messagePattern, final TriFunction<Boolean, String, String, E> exceptionFunction)
            throws E {

        Expect.exception(consumer, expectedException, null, messagePattern, null, exceptionFunction);
    }

    /**
     * Check that the consumed code raises the specified exception, also check
     * the message and allow to change the thrown exception.
     * 
     * @param exceptionSupplier
     *            The consumer (required, not null)
     * @param expectedException
     *            The expected exception type (required, not null)
     * @param expectedMessage
     *            The expected exception message (applied if not {@code null})
     * @param messagePattern
     *            The message pattern (applied if not {@code null})
     * @param messageChecker
     *            The message checker (applied if not {@code null})
     * @param exceptionFunction
     *            The exception function (three parameters are injected: (first:
     *            if it's the expected exception), (second: the expected message
     *            or the pattern as message) and (third: the actual message),
     *            the return has to be a {@link Throwable}). If the exceptions
     *            don't match, the {@link String} parameters are {@code null}}.
     * @param <T>
     *            The generic expected exception type
     * @param <E>
     *            The exception thrown if the expected exception isn't raised
     * @throws E
     *             Provided exception
     */
    private static <T extends Throwable, E extends Throwable> void exception(final ThrowableSupplier<Throwable> exceptionSupplier,
            final Class<T> expectedException, final String expectedMessage, final Pattern messagePattern,
            final Predicate<String> messageChecker, final TriFunction<Boolean, String, String, E> exceptionFunction) throws E {

        Objects.requireNonNull(exceptionSupplier, ERROR_CONSUMER_NULL);
        Objects.requireNonNull(expectedException, ERROR_EXPECTED_NULL);

        Throwable e = null;
        try {
            exceptionSupplier.throwException();
        } catch (Throwable e1) {
            e = e1;
        }

        if (e == null) {
            throw new ExpectException(ERROR_NO_EXCEPTION);
        }

        boolean exceptionDontMatch = !expectedException.isAssignableFrom(e.getClass());
        boolean messageDontMatch = expectedMessage != null && !expectedMessage.equals(e.getMessage());
        messageDontMatch |= messagePattern != null && !messagePattern.matcher(e.getMessage()).matches();
        messageDontMatch |= messageChecker != null && !messageChecker.test(e.getMessage());

        if (exceptionDontMatch || messageDontMatch) {

            final String expectedResult;
            if (messagePattern != null) {
                expectedResult = messagePattern.pattern();
            } else if (messageChecker != null) {
                expectedResult = ERROR_PREDICATE;
            } else {
                expectedResult = expectedMessage;
            }

            if (exceptionFunction != null) {
                throw exceptionFunction.apply(!exceptionDontMatch, expectedResult, e.getMessage());
            } else if (exceptionDontMatch) {
                throw new ExpectException(StringUtils.inject(ERROR_EXCEPTION_DONT_MATCH, e.getClass().getSimpleName()));
            } else {
                throw new ExpectException(StringUtils.inject(ERROR_MESSAGE_DONT_MATCH, expectedResult, e.getMessage()));
            }
        }
    }
}
