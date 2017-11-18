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
 * Represents an operation that accepts seven input arguments and returns no
 * result. This is the seven-arity specialization of {@link Consumer}. Unlike
 * most other functional interfaces, {@link HeptaConsumer} is expected to operate
 * via side-effects.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is
 * {@link #accept(Object, Object, Object, Object, Object, Object, Object)}.
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
public interface HeptaConsumer<T, U, V, W, X, Y, Z> {

    /**
     * Performs this operation on the given arguments.
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
     */
    void accept(T t, U u, V v, W w, X x, Y y, Z z);

    /**
     * Returns a composed {@link HeptaConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after
     *            the operation to perform after this operation
     * @return a composed {@link HeptaConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException
     *             if {@code after} is null
     */
    default HeptaConsumer<T, U, V, W, X, Y, Z> andThen(
            final HeptaConsumer<? super T, ? super U, ? super V, ? super W, ? super X, ? super Y, ? super Z> after) {
        Objects.requireNonNull(after, "after");

        return (t, u, v, w, x, y, z) -> {
            accept(t, u, v, w, x, y, z);
            after.accept(t, u, v, w, x, y, z);
        };
    }
}
