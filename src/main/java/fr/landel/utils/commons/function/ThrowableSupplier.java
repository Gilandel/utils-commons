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

/**
 * Represents an operation that accepts no input argument and returns no result.
 * The only output is through an exception. Unlike most other functional
 * interfaces, {@link ThrowableSupplier} is expected to operate via
 * side-effects.
 * 
 * <p>
 * This class can be used for example to prepare an exception or to assert the
 * good exception is thrown, see: {@link fr.landel.utils.commons.expect.Expect}.
 * </p>
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #throwException()}.
 * </p>
 * 
 * <pre>
 * ThrowableSupplier&lt;Exception&gt; exceptionBuilder = () -&gt; {
 *     throw new Exception("Not possible");
 * };
 * </pre>
 *
 * @since May 14, 2016
 * @author Gilles
 *
 * @param <E>
 *            The exception type
 */
@FunctionalInterface
public interface ThrowableSupplier<E extends Throwable> {

    /**
     * Throws the specified exception.
     *
     * @throws E
     *             On error exception
     */
    void throwException() throws E;
}
