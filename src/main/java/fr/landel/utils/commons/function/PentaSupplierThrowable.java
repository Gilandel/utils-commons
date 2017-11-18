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

import fr.landel.utils.commons.exception.FunctionException;
import fr.landel.utils.commons.tuple.Penta;

/**
 * Represents a throwable supplier of five results.
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
 * @since Nov 17, 2017
 * @author Gilles
 *
 * @param <A>
 *            The first type result
 * @param <B>
 *            The second type result
 * @param <C>
 *            The third type result
 * @param <D>
 *            The fourth type result
 * @param <E>
 *            The fifth type result
 */
@FunctionalInterface
public interface PentaSupplierThrowable<A, B, C, D, E, T extends Throwable> extends PentaSupplier<A, B, C, D, E>, Rethrower {

    /**
     * Performs this operation on the given argument.
     *
     * @return the output argument
     */
    @Override
    default Penta<A, B, C, D, E> get() {
        try {
            return getThrows();
        } catch (final Throwable t) {
            rethrowUnchecked(t);
            throw new FunctionException(t); // never reached normally
        }
    }

    /**
     * Performs this operation on the given argument.
     *
     * @return the output argument
     * @throws T
     *             On error exception
     */
    Penta<A, B, C, D, E> getThrows() throws T;
}
