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
 * An immutable triple consisting of three {@code Object} elements.
 * </p>
 * 
 * <p>
 * Although the implementation is immutable, there is no restriction on the
 * objects that may be stored. If mutable objects are stored in the triple, then
 * the triple itself effectively becomes mutable.
 * </p>
 * 
 * <p>
 * #ThreadSafe# if all objects are thread-safe
 * </p>
 *
 * @param <T>
 *            the element type
 * 
 * @see org.apache.commons.lang3.tuple.Triple
 *
 * @since Jul 26, 2016
 * @author Gilles
 *
 */
public abstract class AbstractImmutableTripleIso<T> extends TripleIso<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 513699263747939321L;

    /** Left object */
    private final T left;
    /** Middle object */
    private final T middle;
    /** Right object */
    private final T right;

    /**
     * Create a new immutable triple instance.
     *
     * @param left
     *            the left value, may be null
     * @param middle
     *            the middle value, may be null
     * @param right
     *            the right value, may be null
     */
    public AbstractImmutableTripleIso(final T left, final T middle, final T right) {
        super();
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getLeft() {
        return this.left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getMiddle() {
        return this.middle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getRight() {
        return this.right;
    }
}
