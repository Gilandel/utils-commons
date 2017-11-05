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

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import fr.landel.utils.commons.exception.FunctionException;

/**
 * Represents a throwable supplier of {@code boolean}-valued results. This is
 * the {@code boolean}-producing primitive/exception specialization of
 * {@link Supplier}.
 *
 * <p>
 * There is no requirement that a new or distinct result be returned each time
 * the supplier is invoked.
 * </p>
 * 
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #getAsBoolean()}.
 * </p>
 *
 * @since May 14, 2016
 * @author Gilles
 *
 * @param <E>
 *            The exception type
 */
@FunctionalInterface
public interface BooleanSupplierThrowable<E extends Throwable> extends BooleanSupplier, Rethrower {

    /**
     * Performs this operation on the given argument.
     *
     * @return the output argument
     */
    @Override
    default boolean getAsBoolean() {
        try {
            return getAsBooleanThrows();
        } catch (final Throwable e) {
            rethrowUnchecked(e);
            throw new FunctionException(e); // never reached normally
        }
    }

    /**
     * Performs this operation on the given argument.
     *
     * @return t the output argument
     * @throws E
     *             On error exception
     */
    boolean getAsBooleanThrows() throws E;
}
