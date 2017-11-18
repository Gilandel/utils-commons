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
import java.util.function.Consumer;

/**
 * Represents a throwable operation that accepts nine input arguments and
 * returns no result. This is the nine-arity/exception specialization of
 * {@link Consumer}. Unlike most other functional interfaces,
 * {@link NonaConsumerThrowable} is expected to operate via side-effects.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #accept(Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
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
 * @param <T>
 *            The exception type
 */
@FunctionalInterface
public interface NonaConsumerThrowable<A, B, C, D, E, F, G, H, I, T extends Throwable>
        extends NonaConsumer<A, B, C, D, E, F, G, H, I>, Rethrower {

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
     *            the fifth input argument
     * @param f
     *            the sixth input argument
     * @param g
     *            the seventh input argument
     * @param h
     *            the eighth input argument
     * @param i
     *            the ninth input argument
     */
    @Override
    default void accept(final A a, final B b, final C c, final D d, final E e, final F f, final G g, final H h, final I i) {
        try {
            acceptThrows(a, b, c, d, e, f, g, h, i);
        } catch (final Throwable t) {
            rethrowUnchecked(t);
        }
    }

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
     *            the fifth input argument
     * @param f
     *            the sixth input argument
     * @param g
     *            the seventh input argument
     * @param h
     *            the eighth input argument
     * @param i
     *            the ninth input argument
     * @throws T
     *             On error exception
     */
    void acceptThrows(A a, B b, C c, D d, E e, F f, G g, H h, I i) throws T;

    /**
     * Returns a composed {@link NonaConsumerThrowable} that performs, in
     * sequence, this operation followed by the {@code after} operation. If
     * performing either operation throws an exception, it is relayed to the
     * caller of the composed operation. If performing this operation throws an
     * exception, the {@code after} operation will not be performed.
     *
     * @param after
     *            the operation to perform after this operation
     * @return a composed {@link NonaConsumerThrowable} that performs in
     *         sequence this operation followed by the {@code after} operation
     * @throws NullPointerException
     *             if {@code after} is null
     * @throws T
     *             On error exception
     */
    default NonaConsumerThrowable<A, B, C, D, E, F, G, H, I, T> andThen(final NonaConsumerThrowable<A, B, C, D, E, F, G, H, I, T> after)
            throws T {
        Objects.requireNonNull(after, "after");
        return (a, b, c, d, e, f, g, h, i) -> {
            acceptThrows(a, b, c, d, e, f, g, h, i);
            after.acceptThrows(a, b, c, d, e, f, g, h, i);
        };
    }
}
