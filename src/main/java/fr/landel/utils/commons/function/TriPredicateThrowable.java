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

import fr.landel.utils.commons.exception.FunctionException;

/**
 * Represents a throwable predicate (boolean-valued function) of three
 * arguments. This is the three-arity/exception specialization of
 * {@link Predicate}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #test(Object, Object, Object)}.
 * </p>
 *
 * @since May 30, 2016
 * @author Gilles
 *
 * @param <T>
 *            the first argument type
 * @param <U>
 *            the second argument type
 * @param <V>
 *            the third argument type
 * @param <E>
 *            the exception type
 */
@FunctionalInterface
public interface TriPredicateThrowable<T, U, V, E extends Throwable> extends TriPredicate<T, U, V>, Rethrower {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t
     *            the input argument
     * @param u
     *            the second argument
     * @param v
     *            the third argument
     * @return {@code true} if the input argument matches the predicate,
     *         otherwise {@code false}
     */
    default boolean test(final T t, final U u, final V v) {
        try {
            return testThrows(t, u, v);
        } catch (final Throwable e) {
            rethrowUnchecked(e);
            throw new FunctionException(e); // never reached normally
        }
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t
     *            the first argument
     * @param u
     *            the second argument
     * @param v
     *            the third argument
     * @return {@code true} if the input argument matches the predicate,
     *         otherwise {@code false}
     * @throws E
     *             On error exception
     */
    boolean testThrows(T t, U u, V v) throws E;

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
    default TriPredicateThrowable<T, U, V, E> and(final TriPredicateThrowable<T, U, V, E> other) throws E {
        Objects.requireNonNull(other, "other");

        return (t, u, v) -> testThrows(t, u, v) && other.testThrows(t, u, v);
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
    default TriPredicateThrowable<T, U, V, E> negateThrows() throws E {
        return (t, u, v) -> !testThrows(t, u, v);
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
    default TriPredicateThrowable<T, U, V, E> or(final TriPredicateThrowable<T, U, V, E> other) throws E {
        Objects.requireNonNull(other, "other");

        return (t, u, v) -> testThrows(t, u, v) || other.testThrows(t, u, v);
    }
}
