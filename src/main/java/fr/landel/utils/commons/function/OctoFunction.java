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
import java.util.function.Function;

/**
 * Represents a function that accepts eight arguments and produces a result.
 * This is the eight-arity specialization of {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #apply(Object, Object, Object, Object, Object, Object, Object, Object)}.
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
 */
@FunctionalInterface
public interface OctoFunction<A, B, C, D, E, F, G, H, R> {

    /**
     * Applies this function to the given arguments.
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
     * @return the function result
     */
    R apply(A a, B b, C c, D d, E e, F f, G g, H h);

    /**
     * Returns a composed function that first applies this function to its
     * input, and then applies the {@code after} function to the result. If
     * evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S>
     *            the type of output of the {@code after} function, and of the
     *            composed function
     * @param after
     *            the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     *         applies the {@code after} function
     * @throws NullPointerException
     *             if after is null
     */
    default <S> OctoFunction<A, B, C, D, E, F, G, H, S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after, "after");
        return (A a, B b, C c, D d, E e, F f, G g, H h) -> after.apply(apply(a, b, c, d, e, f, g, h));
    }
}
