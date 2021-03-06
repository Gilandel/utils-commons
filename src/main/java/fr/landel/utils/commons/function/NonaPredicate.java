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
 * Represents a predicate (boolean-valued function) of nine arguments. This is
 * the nine-arity specialization of {@link Predicate}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #test(Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
 * </p>
 *
 * @since Nov 16, 2017
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
 * @param <I>
 *            the ninth argument type
 */
@FunctionalInterface
public interface NonaPredicate<A, B, C, D, E, F, G, H, I> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param a
     *            the first input argument
     * @param b
     *            the second input argument
     * @param c
     *            the third input argument
     * @param d
     *            the fourth input argument
     * @param e
     *            the fifth input argument
     * @param f
     *            the sixth input argument
     * @param g
     *            the seventh input argument
     * @param h
     *            the eighth input argument
     * @param i
     *            the ninth input argument
     * @return {@code true} if the input arguments match the predicate,
     *         otherwise {@code false}
     */
    boolean test(A a, B b, C c, D d, E e, F f, G g, H h, I i);

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
    default NonaPredicate<A, B, C, D, E, F, G, H, I> and(
            final NonaPredicate<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? super G, ? super H, ? super I> other) {
        Objects.requireNonNull(other, "other");
        return (A a, B b, C c, D d, E e, F f, G g, H h, I i) -> test(a, b, c, d, e, f, g, h, i) && other.test(a, b, c, d, e, f, g, h, i);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *         predicate
     */
    default NonaPredicate<A, B, C, D, E, F, G, H, I> negate() {
        return (A a, B b, C c, D d, E e, F f, G g, H h, I i) -> !test(a, b, c, d, e, f, g, h, i);
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
    default NonaPredicate<A, B, C, D, E, F, G, H, I> or(
            final NonaPredicate<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? super G, ? super H, ? super I> other) {
        Objects.requireNonNull(other, "other");
        return (A a, B b, C c, D d, E e, F f, G g, H h, I i) -> test(a, b, c, d, e, f, g, h, i) || other.test(a, b, c, d, e, f, g, h, i);
    }
}
