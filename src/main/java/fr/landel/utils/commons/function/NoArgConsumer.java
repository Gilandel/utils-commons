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

/**
 * Represents a throwable operation that doesn't accept any argument and returns
 * no result. Unlike most other functional interfaces, {@link NoArgConsumer} is
 * expected to operate via side-effects.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #run()}.
 *
 * @since Nov 16, 2017
 * @author Gilles
 */
@FunctionalInterface
public interface NoArgConsumer {

    /**
     * Performs this operation.
     */
    void run();

    /**
     * Returns a composed {@link NoArgConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after
     *            the operation to perform after this operation
     * @return a composed {@link NoArgConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException
     *             if {@code after} is null
     */
    default NoArgConsumer andThen(final NoArgConsumer after) {
        Objects.requireNonNull(after, "after");
        return () -> {
            run();
            after.run();
        };
    }
}
