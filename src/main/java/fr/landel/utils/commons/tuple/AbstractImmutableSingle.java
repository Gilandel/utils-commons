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
package fr.landel.utils.commons.tuple;

import java.util.function.Function;

/**
 * <p>
 * Abstract version for overriding purpose, don't forgot to keep it immutable.
 * </p>
 * 
 * <p>
 * An immutable single consisting of a single {@code Object} element.
 * </p>
 * 
 * <p>
 * Although the implementation is immutable, there is no restriction on the
 * objects that may be stored. If mutable objects are stored in the single, then
 * the single itself effectively becomes mutable.
 * </p>
 * 
 * <p>
 * #ThreadSafe# if the object is thread-safe
 * </p>
 *
 * @param <T>
 *            the element type
 * 
 * @see org.apache.commons.lang3.tuple.Pair
 *
 * @since Jul 26, 2016
 * @author Gilles
 *
 */
public abstract class AbstractImmutableSingle<T> extends Single<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1867548171293331171L;

    /** object */
    public final T element;

    /**
     * Create a new immutable single instance.
     *
     * @param element
     *            the value, may be null
     */
    public AbstractImmutableSingle(final T element) {
        super();
        this.element = element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get() {
        return this.element;
    }

    /**
     * <p>
     * Throws {@code UnsupportedOperationException}.
     * </p>
     * 
     * <p>
     * This single is immutable, so this operation is not supported.
     * </p>
     *
     * @param element
     *            the value to set
     * @return never
     * @throws UnsupportedOperationException
     *             as this operation is not supported
     */
    @Override
    public T set(final T element) {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Throws {@code UnsupportedOperationException}.
     * </p>
     * 
     * <p>
     * This single is immutable, so this operation is not supported.
     * </p>
     *
     * @param function
     *            the update function
     * @return never
     * @throws UnsupportedOperationException
     *             as this operation is not supported
     */
    @Override
    public T update(final Function<T, T> function) {
        throw new UnsupportedOperationException();
    }
}
