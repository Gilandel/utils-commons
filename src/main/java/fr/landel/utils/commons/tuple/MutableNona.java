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
 * A mutable nona consisting of nine {@code Object} elements.
 * </p>
 * 
 * <p>
 * Not #ThreadSafe#
 * </p>
 *
 * @see fr.landel.utils.commons.tuple.Nona
 *
 * @since Jul 26, 2016
 * @author Gilles
 *
 * @param <A>
 *            the type of first object
 * @param <B>
 *            the type of second object
 * @param <C>
 *            the type of third object
 * @param <D>
 *            the type of fourth object
 * @param <E>
 *            the fifth element type
 * @param <F>
 *            the sixth element type
 * @param <G>
 *            the seventh element type
 * @param <H>
 *            the eighth element type
 * @param <I>
 *            the ninth element type
 */
public class MutableNona<A, B, C, D, E, F, G, H, I> extends Nona<A, B, C, D, E, F, G, H, I> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -799774348810900228L;

    /** First object */
    private A first;
    /** Second object */
    private B second;
    /** Third object */
    private C third;
    /** Fourth object */
    private D fourth;
    /** Fifth object */
    private E fifth;
    /** Sixth object */
    private F sixth;
    /** Seventh object */
    private G seventh;
    /** Eighth object */
    private H eighth;
    /** Ninth object */
    private I ninth;

    /**
     * Create a new nona instance of nine nulls.
     */
    public MutableNona() {
        super();
    }

    /**
     * Create a new mutable nona instance.
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
     * @param eighth
     *            the eighth element, may be null
     * @param ninth
     *            the ninth element, may be null
     */
    public MutableNona(final A first, final B second, final C third, final D fourth, final E fifth, final F sixth, final G seventh,
            final H eighth, final I ninth) {
        this();
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
        this.eighth = eighth;
        this.ninth = ninth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A getFirst() {
        return this.first;
    }

    /**
     * Sets the first element of the nona.
     * 
     * @param first
     *            the new value of the first element, may be null
     */
    public void setFirst(final A first) {
        this.first = first;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public B getSecond() {
        return this.second;
    }

    /**
     * Sets the second element of the nona.
     * 
     * @param second
     *            the new value of the second element, may be null
     */
    public void setSecond(final B second) {
        this.second = second;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public C getThird() {
        return this.third;
    }

    /**
     * Sets the third element of the nona.
     * 
     * @param third
     *            the new value of the third element, may be null
     */
    public void setThird(final C third) {
        this.third = third;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D getFourth() {
        return this.fourth;
    }

    /**
     * Sets the fourth element of the nona.
     * 
     * @param fourth
     *            the new value of the fourth element, may be null
     */
    public void setFourth(final D fourth) {
        this.fourth = fourth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E getFifth() {
        return this.fifth;
    }

    /**
     * Sets the fifth element of the nona.
     * 
     * @param fifth
     *            the new value of the fifth element, may be null
     */
    public void setFifth(final E fifth) {
        this.fifth = fifth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public F getSixth() {
        return this.sixth;
    }

    /**
     * Sets the sixth element of the nona.
     * 
     * @param sixth
     *            the new value of the sixth element, may be null
     */
    public void setSixth(final F sixth) {
        this.sixth = sixth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public G getSeventh() {
        return this.seventh;
    }

    /**
     * Sets the seventh element of the nona.
     * 
     * @param seventh
     *            the new value of the seventh element, may be null
     */
    public void setSeventh(final G seventh) {
        this.seventh = seventh;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public H getEighth() {
        return this.eighth;
    }

    /**
     * Sets the eighth element of the nona.
     * 
     * @param eighth
     *            the new value of the eighth element, may be null
     */
    public void setEighth(final H eighth) {
        this.eighth = eighth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public I getNinth() {
        return this.ninth;
    }

    /**
     * Sets the ninth element of the nona.
     * 
     * @param ninth
     *            the new value of the ninth element, may be null
     */
    public void setNinth(final I ninth) {
        this.ninth = ninth;
    }
}
