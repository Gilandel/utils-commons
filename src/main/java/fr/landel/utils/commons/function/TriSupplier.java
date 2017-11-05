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

import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Triple;

/**
 * Represents a supplier of triple results.
 *
 * <p>
 * There is no requirement that a new or distinct result be returned each time
 * the supplier is invoked.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #get()}.
 *
 * @since Aug 10, 2016
 * @author Gilles
 *
 * @param <L>
 *            The left type result
 * @param <M>
 *            The middle type result
 * @param <R>
 *            The right type result
 */
@FunctionalInterface
public interface TriSupplier<L, M, R> extends Supplier<Triple<L, M, R>> {
}
