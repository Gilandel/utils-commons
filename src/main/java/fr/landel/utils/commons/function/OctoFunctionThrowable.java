/*
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

import fr.landel.utils.commons.exception.FunctionException;

/**
 * Represents a throwable function that accepts eight arguments and produces a
 * result. This is the eight-arity/exception specialization of {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #apply(Object, Object, Object, Object, Object, Object, Object, Object)}.
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
 * @param <R>
 *            the return argument type
 * @param <T>
 *            The exception type
 */
@FunctionalInterface
public interface OctoFunctionThrowable<A, B, C, D, E, F, G, H, R, T extends Throwable>
        extends OctoFunction<A, B, C, D, E, F, G, H, R>, Rethrower {

    /**
     * Performs this operation on the given arguments.
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
     * @return The output result
     */
    @Override
    default R apply(final A a, final B b, final C c, final D d, final E e, final F f, final G g, final H h) {
        try {
            return applyThrows(a, b, c, d, e, f, g, h);
        } catch (final Throwable t) {
            rethrowUnchecked(t);
            throw new FunctionException(t); // never reached normally
        }
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param a
     *            the first argument
     * @param b
     *            The second argument
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
     * @return The output result
     * @throws T
     *             On error exception
     */
    R applyThrows(A a, B b, C c, D d, E e, F f, G g, H h) throws T;

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
     * @throws T
     *             On error exception
     */
    default <O> OctoFunctionThrowable<A, B, C, D, E, F, G, H, O, T> andThen(final FunctionThrowable<R, O, T> after) throws T {
        Objects.requireNonNull(after, "after");
        return (a, b, c, d, e, f, g, h) -> after.applyThrows(applyThrows(a, b, c, d, e, f, g, h));
    }
}
