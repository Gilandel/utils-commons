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

/**
 * Represents a predicate (boolean-valued function) of six arguments. This is
 * the six-arity specialization of {@link Predicate}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #test(Object, Object, Object, Object, Object, Object)}.
 * </p>
 *
 * @since Nov 16, 2017
 * @author Gilles
 *
 * @param <T>
 *            the first argument type
 * @param <U>
 *            the second argument type
 * @param <V>
 *            the third argument type
 * @param <W>
 *            the fourth argument type
 * @param <X>
 *            the fifth argument type
 * @param <Y>
 *            the sixth argument type
 */
@FunctionalInterface
public interface HexaPredicate<T, U, V, W, X, Y> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t
     *            the first input argument
     * @param u
     *            the second input argument
     * @param v
     *            the third input argument
     * @param w
     *            the fourth input argument
     * @param x
     *            the fifth input argument
     * @param y
     *            the sixth input argument
     * @return {@code true} if the input arguments match the predicate,
     *         otherwise {@code false}
     */
    boolean test(T t, U u, V v, W w, X x, Y y);

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
     */
    default HexaPredicate<T, U, V, W, X, Y> and(
            final HexaPredicate<? super T, ? super U, ? super V, ? super W, ? super X, ? super Y> other) {
        Objects.requireNonNull(other, "other");
        return (T t, U u, V v, W w, X x, Y y) -> test(t, u, v, w, x, y) && other.test(t, u, v, w, x, y);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *         predicate
     */
    default HexaPredicate<T, U, V, W, X, Y> negate() {
        return (T t, U u, V v, W w, X x, Y y) -> !test(t, u, v, w, x, y);
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
     */
    default HexaPredicate<T, U, V, W, X, Y> or(
            final HexaPredicate<? super T, ? super U, ? super V, ? super W, ? super X, ? super Y> other) {
        Objects.requireNonNull(other, "other");
        return (T t, U u, V v, W w, X x, Y y) -> test(t, u, v, w, x, y) || other.test(t, u, v, w, x, y);
    }
}
