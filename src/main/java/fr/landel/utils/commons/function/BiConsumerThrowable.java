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
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts two input arguments and returns no
 * result. This is the two-arity specialization of {@link Consumer}. Unlike most
 * other functional interfaces, {@link BiConsumerThrowable} is expected to
 * operate via side-effects.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #accept(Object, Object)}.
 *
 * @since May 14, 2016
 * @author Gilles
 *
 * @param <T>
 *            The first input type
 * @param <U>
 *            The second input type
 * @param <E>
 *            The exception type
 */
@FunctionalInterface
public interface BiConsumerThrowable<T, U, E extends Throwable> extends BiConsumer<T, U>, Rethrower {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t
     *            the first argument
     * @param u
     *            the second argument
     */
    @Override
    default void accept(final T t, final U u) {
        try {
            acceptThrows(t, u);
        } catch (final Throwable e) {
            rethrowUnchecked(e);
        }
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param t
     *            the first input argument
     * @param u
     *            the second input argument
     * @throws E
     *             On error exception
     */
    void acceptThrows(T t, U u) throws E;

    /**
     * Returns a composed {@link BiConsumerThrowable} that performs, in
     * sequence, this operation followed by the {@code after} operation. If
     * performing either operation throws an exception, it is relayed to the
     * caller of the composed operation. If performing this operation throws an
     * exception, the {@code after} operation will not be performed.
     *
     * @param after
     *            the operation to perform after this operation
     * @return a composed {@link BiConsumerThrowable} that performs in sequence
     *         this operation followed by the {@code after} operation
     * @throws NullPointerException
     *             if {@code after} is null
     * @throws E
     *             On error exception
     */
    default BiConsumerThrowable<T, U, E> andThen(final BiConsumerThrowable<T, U, E> after) throws E {
        Objects.requireNonNull(after, "after");
        return (t, u) -> {
            acceptThrows(t, u);
            after.acceptThrows(t, u);
        };
    }
}
