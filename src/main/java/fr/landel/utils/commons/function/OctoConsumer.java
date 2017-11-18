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
import java.util.function.Consumer;

/**
 * Represents an operation that accepts eight input arguments and returns no
 * result. This is the eight-arity specialization of {@link Consumer}. Unlike
 * most other functional interfaces, {@link OctoConsumer} is expected to operate
 * via side-effects.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #accept(Object, Object, Object, Object, Object, Object, Object, Object)}.
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
public interface OctoConsumer<A, B, C, D, E, F, G, H> {

    /**
     * Performs this operation on the given arguments.
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
     */
    void accept(A a, B b, C c, D d, E e, F f, G g, H h);

    /**
     * Returns a composed {@link OctoConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after
     *            the operation to perform after this operation
     * @return a composed {@link OctoConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException
     *             if {@code after} is null
     */
    default OctoConsumer<A, B, C, D, E, F, G, H> andThen(
            final OctoConsumer<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? super G, ? super H> after) {
        Objects.requireNonNull(after, "after");

        return (a, b, c, d, e, f, g, h) -> {
            accept(a, b, c, d, e, f, g, h);
            after.accept(a, b, c, d, e, f, g, h);
        };
    }
}
