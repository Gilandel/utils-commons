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
 * A mutable hexa consisting of six {@code Object} elements.
 * </p>
 * 
 * <p>
 * Not #ThreadSafe#
 * </p>
 *
 * @see fr.landel.utils.commons.tuple.Hexa
 *
 * @since Nov 17, 2017
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
 */
public class MutableHexa<A, B, C, D, E, F> extends Hexa<A, B, C, D, E, F> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -799774328810900228L;

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

    /**
     * Create a new hexa instance of six nulls.
     */
    public MutableHexa() {
        super();
    }

    /**
     * Create a new mutable hexa instance.
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
     */
    public MutableHexa(final A first, final B second, final C third, final D fourth, final E fifth, final F sixth) {
        this();
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A getFirst() {
        return this.first;
    }

    /**
     * Sets the first element of the hexa.
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
     * Sets the second element of the hexa.
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
     * Sets the third element of the hexa.
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
     * Sets the fourth element of the hexa.
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
     * Sets the fifth element of the hexa.
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
     * Sets the sixth element of the hexa.
     * 
     * @param sixth
     *            the new value of the sixth element, may be null
     */
    public void setSixth(final F sixth) {
        this.sixth = sixth;
    }
}