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
 * Represents a function that accepts six arguments and produces a result. This
 * is the six-arity specialization of {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #apply(Object, Object, Object, Object, Object, Object)}.
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
 * @param <R>
 *            the return argument type
 */
@FunctionalInterface
public interface HexaFunction<T, U, V, W, X, Y, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t
     *            the first function argument
     * @param u
     *            the second function argument
     * @param v
     *            the third function argument
     * @param w
     *            the fourth function argument
     * @param x
     *            the fifth input argument
     * @param y
     *            the sixth input argument
     * @return the function result
     */
    R apply(T t, U u, V v, W w, X x, Y y);

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
    default <S> HexaFunction<T, U, V, W, X, Y, S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after, "after");
        return (T t, U u, V v, W w, X x, Y y) -> after.apply(apply(t, u, v, w, x, y));
    }
}
