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

/**
 * <p>
 * Abstract version for overriding purpose, don't forgot to keep it immutable.
 * </p>
 * 
 * <p>
 * An immutable quad consisting of four {@code Object} elements.
 * </p>
 * 
 * <p>
 * Although the implementation is immutable, there is no restriction on the
 * objects that may be stored. If mutable objects are stored in the quad, then
 * the quad itself effectively becomes mutable.
 * </p>
 * 
 * <p>
 * #ThreadSafe# if all objects are thread-safe
 * </p>
 *
 * @param <T>
 *            the first element type
 *
 * @since Jul 26, 2016
 * @author Gilles
 *
 */
public abstract class AbstractImmutableQuadIso<T> extends QuadIso<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3705003920443865537L;

    /** First object */
    private final T first;
    /** Second object */
    private final T second;
    /** Third object */
    private final T third;
    /** Fourth object */
    private final T fourth;

    /**
     * Create a new immutable quad instance.
     *
     * @param first
     *            the first element, may be null
     * @param second
     *            the second element, may be null
     * @param third
     *            the third element, may be null
     * @param fourth
     *            the fourth element, may be null
     */
    public AbstractImmutableQuadIso(final T first, final T second, final T third, final T fourth) {
        super();
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getFirst() {
        return this.first;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getSecond() {
        return this.second;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getThird() {
        return this.third;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getFourth() {
        return this.fourth;
    }
}
