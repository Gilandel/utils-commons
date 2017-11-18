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
package fr.landel.utils.commons.function;

import java.util.Objects;
import java.util.function.Predicate;

import fr.landel.utils.commons.exception.FunctionException;

/**
 * Represents a throwable predicate (boolean-valued function) of eight
 * arguments. This is the eight-arity/exception specialization of
 * {@link Predicate}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #test(Object, Object, Object, Object, Object, Object, Object, Object)}.
 * </p>
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 * @param <A>
 *            the first argument type
 * @param <B>
 *            the second argument type
 * @param <C>
 *            the third argument type
 * @param <D>
 *            the fourth argument type
 * @param <E>
 *            the fifth argument type
 * @param <F>
 *            the sixth argument type
 * @param <G>
 *            the seventh argument type
 * @param <H>
 *            the eighth argument type
 * @param <T>
 *            the exception type
 */
@FunctionalInterface
public interface OctoPredicateThrowable<A, B, C, D, E, F, G, H, T extends Throwable>
        extends OctoPredicate<A, B, C, D, E, F, G, H>, Rethrower {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param a
     *            the first argument
     * @param b
     *            the second argument
     * @param c
     *            the third argument
     * @param d
     *            the fourth argument
     * @param e
     *            the fifth argument
     * @param f
     *            the sixth argument
     * @param g
     *            the seventh argument
     * @param h
     *            the eighth argument
     * @return {@code true} if the input argument matches the predicate,
     *         otherwise {@code false}
     */
    default boolean test(final A a, final B b, final C c, final D d, final E e, final F f, final G g, final H h) {
        try {
            return testThrows(a, b, c, d, e, f, g, h);
        } catch (final Throwable t) {
            rethrowUnchecked(t);
            throw new FunctionException(t); // never reached normally
        }
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param a
     *            the first argument
     * @param b
     *            the second argument
     * @param c
     *            the third argument
     * @param d
     *            the fourth argument
     * @param e
     *            the fifth argument
     * @param f
     *            the sixth argument
     * @param g
     *            the seventh argument
     * @param h
     *            the eighth argument
     * @return {@code true} if the input argument matches the predicate,
     *         otherwise {@code false}
     * @throws T
     *             On error exception
     */
    boolean testThrows(A a, B b, C c, D d, E e, F f, G g, H h) throws T;

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
     * @throws T
     *             On error exception
     */
    default OctoPredicateThrowable<A, B, C, D, E, F, G, H, T> and(final OctoPredicateThrowable<A, B, C, D, E, F, G, H, T> other) throws T {
        Objects.requireNonNull(other, "other");
        return (a, b, c, d, e, f, g, h) -> testThrows(a, b, c, d, e, f, g, h) && other.testThrows(a, b, c, d, e, f, g, h);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *         predicate
     * @throws T
     *             On error exception
     */
    default OctoPredicateThrowable<A, B, C, D, E, F, G, H, T> negateThrows() throws T {
        return (a, b, c, d, e, f, g, h) -> !testThrows(a, b, c, d, e, f, g, h);
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
     * @throws T
     *             On error exception
     */
    default OctoPredicateThrowable<A, B, C, D, E, F, G, H, T> or(final OctoPredicateThrowable<A, B, C, D, E, F, G, H, T> other) throws T {
        Objects.requireNonNull(other, "other");
        return (a, b, c, d, e, f, g, h) -> testThrows(a, b, c, d, e, f, g, h) || other.testThrows(a, b, c, d, e, f, g, h);
    }
}
