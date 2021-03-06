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
package fr.landel.utils.commons.function;

import java.util.Objects;
import java.util.function.Function;

import fr.landel.utils.commons.exception.FunctionException;

/**
 * Represents a function that accepts three arguments, produces a result and
 * handles generic exception. This is the three-arity/exception specialization
 * of {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #apply(Object, Object, Object)}.
 * </p>
 *
 * @since May 14, 2016
 * @author Gilles
 *
 * @param <T>
 *            the first argument type
 * @param <U>
 *            the second argument type
 * @param <V>
 *            the third argument type
 * @param <R>
 *            the return argument type
 * @param <E>
 *            The exception type
 */
@FunctionalInterface
public interface TriFunctionThrowable<T, U, V, R, E extends Throwable> extends TriFunction<T, U, V, R>, Rethrower {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t
     *            the first argument
     * @param u
     *            the second argument
     * @param v
     *            the third argument
     * @return The output result
     */
    @Override
    default R apply(final T t, final U u, final V v) {
        try {
            return applyThrows(t, u, v);
        } catch (final Throwable e) {
            rethrowUnchecked(e);
            throw new FunctionException(e); // never reached normally
        }
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param t
     *            the first argument
     * @param u
     *            The second argument
     * @param v
     *            the third argument
     * @return The output result
     * @throws E
     *             On error exception
     */
    R applyThrows(T t, U u, V v) throws E;

    /**
     * Returns a composed function that first applies this function to its
     * input, and then applies the {@code after} function to the result. If
     * evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <O>
     *            the type of output of the {@code after} function, and of the
     *            composed function
     * @param after
     *            the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     *         applies the {@code after} function
     * @throws NullPointerException
     *             if after is null
     * @throws E
     *             On error exception
     */
    default <O> TriFunctionThrowable<T, U, V, O, E> andThen(final FunctionThrowable<R, O, E> after) throws E {
        Objects.requireNonNull(after, "after");

        return (t, u, v) -> after.applyThrows(applyThrows(t, u, v));
    }
}
