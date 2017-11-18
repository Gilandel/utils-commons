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
 * An immutable hepta consisting of seven {@code Object} elements.
 * </p>
 * 
 * <p>
 * Although the implementation is immutable, there is no restriction on the
 * objects that may be stored. If mutable objects are stored in the hepta, then
 * the hepta itself effectively becomes mutable.
 * </p>
 * 
 * <p>
 * #ThreadSafe# if all objects are thread-safe
 * </p>
 *
 * @param <A>
 *            the first element type
 * @param <B>
 *            the second element type
 * @param <C>
 *            the third element type
 * @param <D>
 *            the fourth element type
 * @param <E>
 *            the fifth element type
 * @param <F>
 *            the sixth element type
 * @param <G>
 *            the seventh element type
 *
 * @since Nov 16, 2017
 * @author Gilles
 *
 */
public abstract class AbstractImmutableHepta<A, B, C, D, E, F, G> extends Hepta<A, B, C, D, E, F, G> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3705003920443865539L;

    /** First object */
    private final A first;
    /** Second object */
    private final B second;
    /** Third object */
    private final C third;
    /** Fourth object */
    private final D fourth;
    /** Fifth object */
    private final E fifth;
    /** Sixth object */
    private final F sixth;
    /** Seventh object */
    private final G seventh;

    /**
     * Create a new immutable hepta instance.
     *
     * @param first
     *            the first element, may be null
     * @param second
     *            the second element, may be null
     * @param third
     *            the third element, may be null
     * @param fourth
     *            the fourth element, may be null
     * @param fifth
     *            the fifth element, may be null
     * @param sixth
     *            the sixth element, may be null
     * @param seventh
     *            the seventh element, may be null
     */
    public AbstractImmutableHepta(final A first, final B second, final C third, final D fourth, final E fifth, final F sixth,
            final G seventh) {
        super();
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A getFirst() {
        return this.first;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public B getSecond() {
        return this.second;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public C getThird() {
        return this.third;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D getFourth() {
        return this.fourth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E getFifth() {
        return this.fifth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public F getSixth() {
        return this.sixth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public G getSeventh() {
        return this.seventh;
    }
}
