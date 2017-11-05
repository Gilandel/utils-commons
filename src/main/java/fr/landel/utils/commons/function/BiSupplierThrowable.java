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

import org.apache.commons.lang3.tuple.Pair;

import fr.landel.utils.commons.exception.FunctionException;

/**
 * Represents a throwable supplier of pair results.
 *
 * <p>
 * There is no requirement that a new or distinct result be returned each time
 * the supplier is invoked.
 * </p>
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #get()}.
 * </p>
 *
 * @since May 14, 2016
 * @author Gilles
 *
 * @param <L>
 *            The left type result
 * @param <R>
 *            The right type result
 * @param <E>
 *            The exception type
 */
@FunctionalInterface
public interface BiSupplierThrowable<L, R, E extends Throwable> extends BiSupplier<L, R>, Rethrower {

    /**
     * Performs this operation on the given argument.
     *
     * @return the output argument
     */
    @Override
    default Pair<L, R> get() {
        try {
            return getThrows();
        } catch (final Throwable e) {
            rethrowUnchecked(e);
            throw new FunctionException(e); // never reached normally
        }
    }

    /**
     * Performs this operation on the given argument.
     *
     * @return the output argument
     * @throws E
     *             On error exception
     */
    Pair<L, R> getThrows() throws E;
}
