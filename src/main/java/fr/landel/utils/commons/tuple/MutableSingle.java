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

import java.util.Objects;
import java.util.function.Function;

/**
 * <p>
 * A mutable single consisting of a single {@code Object} element.
 * </p>
 * 
 * <p>
 * Not #ThreadSafe#
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
public class MutableSingle<T> extends Single<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6102457045775716036L;

    /** object */
    public T element;

    /**
     * Create a new single instance of one null.
     */
    public MutableSingle() {
        super();
    }

    /**
     * Create a new mutable single instance.
     *
     * @param element
     *            the value, may be null
     */
    public MutableSingle(final T element) {
        this();
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
     * {@inheritDoc}
     */
    @Override
    public T set(final T element) {
        T previous = this.element;
        this.element = element;
        return previous;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(final Function<T, T> function) {
        Objects.requireNonNull(this.element, "Cannot update, current element is null");
        return this.set(function.apply(this.element));
    }
}
