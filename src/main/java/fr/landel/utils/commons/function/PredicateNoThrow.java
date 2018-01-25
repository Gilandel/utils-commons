/*-
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
package fr.landel.utils.commons.function;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one argument that doesn't
 * throw exception.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #test(Object)}.
 * </p>
 *
 * <p>
 * If the predicate throws an exception a log is written in debug mode through
 * {@link PredicateLogger}
 * </p>
 * 
 * @since Jan 25, 2018
 * @author Gilles
 * @param <T>
 *            the type of the object to check
 * @param <E>
 *            the exception type thrown by the predicate
 */
@FunctionalInterface
public interface PredicateNoThrow<T, E extends Throwable> extends Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t
     *            the input argument
     * @return {@code true} if the input argument matches the predicate,
     *         otherwise {@code false} even if an exception is thrown
     */
    default boolean test(final T t) {
        try {
            return testThrows(t);
        } catch (final Throwable e) {
            PredicateLogger.debug(e);
            return false;
        }
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t
     *            the input argument
     * @return {@code true} if the input argument matches the predicate,
     *         otherwise {@code false}
     * @throws E
     *             On error exception
     */
    boolean testThrows(T t) throws E;

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another. When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other
     *            a predicate that will be logically-ANDed with this predicate
     * @return a composed predicate that represents the short-circuiting logical
     *         AND of this predicate and the {@code other} predicate
     * @throws NullPointerException
     *             if other is null
     * @throws E
     *             On error exception
     */
    default PredicateNoThrow<T, E> and(final PredicateNoThrow<T, E> other) throws E {
        Objects.requireNonNull(other, "other");
        return (t) -> testThrows(t) && other.testThrows(t);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *         predicate
     * @throws E
     *             On error exception
     */
    default PredicateNoThrow<T, E> negateThrows() throws E {
        return (t) -> !testThrows(t);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another. When evaluating the composed predicate,
     * if this predicate is {@code true}, then the {@code other} predicate is
     * not evaluated.
     *
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other
     *            a predicate that will be logically-ORed with this predicate
     * @return a composed predicate that represents the short-circuiting logical
     *         OR of this predicate and the {@code other} predicate
     * @throws NullPointerException
     *             if other is null
     * @throws E
     *             On error exception
     */
    default PredicateNoThrow<T, E> or(final PredicateNoThrow<T, E> other) throws E {
        Objects.requireNonNull(other, "other");
        return (t) -> testThrows(t) || other.testThrows(t);
    }
}
