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
 * Represents a predicate (boolean-valued function) of seven arguments. This is
 * the seven-arity specialization of {@link Predicate}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #test(Object, Object, Object, Object, Object, Object, Object)}.
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
 * @param <Z>
 *            the seventh argument type
 */
@FunctionalInterface
public interface HeptaPredicate<T, U, V, W, X, Y, Z> {

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
     * @param z
     *            the seventh input argument
     * @return {@code true} if the input arguments match the predicate,
     *         otherwise {@code false}
     */
    boolean test(T t, U u, V v, W w, X x, Y y, Z z);

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
    default HeptaPredicate<T, U, V, W, X, Y, Z> and(
            final HeptaPredicate<? super T, ? super U, ? super V, ? super W, ? super X, ? super Y, ? super Z> other) {
        Objects.requireNonNull(other, "other");
        return (T t, U u, V v, W w, X x, Y y, Z z) -> test(t, u, v, w, x, y, z) && other.test(t, u, v, w, x, y, z);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *         predicate
     */
    default HeptaPredicate<T, U, V, W, X, Y, Z> negate() {
        return (T t, U u, V v, W w, X x, Y y, Z z) -> !test(t, u, v, w, x, y, z);
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
    default HeptaPredicate<T, U, V, W, X, Y, Z> or(
            final HeptaPredicate<? super T, ? super U, ? super V, ? super W, ? super X, ? super Y, ? super Z> other) {
        Objects.requireNonNull(other, "other");
        return (T t, U u, V v, W w, X x, Y y, Z z) -> test(t, u, v, w, x, y, z) || other.test(t, u, v, w, x, y, z);
    }
}
