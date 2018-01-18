/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2018 Gilles Landel
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
 * An nona consisting of nine elements.
 * </p>
 * 
 * <p>
 * This class is an abstract implementation defining the basic API. It refers to
 * the elements as 'first', 'second', 'third', 'fourth', 'fifth', 'sixth',
 * 'seventh' and 'eighth' and 'ninth'.
 * </p>
 * 
 * <p>
 * Subclass implementations may be mutable or immutable. However, there is no
 * restriction on the type of the stored objects that may be stored. If mutable
 * objects are stored in the nona, then the nona itself effectively becomes
 * mutable.
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
 * @param <H>
 *            the eighth element type
 * @param <I>
 *            the ninth element type
 *
 * @since Nov 16, 2017
 * @author Gilles
 *
 */
public abstract class Nona<A, B, C, D, E, F, G, H, I> extends AbstractNona<A, B, C, D, E, F, G, H, I> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7859128872551227846L;

    /**
     * <p>
     * Obtains an immutable nona of from nine objects inferring the generic
     * types.
     * </p>
     * 
     * <p>
     * This factory allows the nona to be created using inference to obtain the
     * generic types.
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
     * @param <H>
     *            the eighth element type
     * @param <I>
     *            the ninth element type
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
     * @return an nona formed from the nine parameters, not null
     */
    public static <A, B, C, D, E, F, G, H, I> Nona<A, B, C, D, E, F, G, H, I> of(final A first, final B second, final C third,
            final D fourth, final E fifth, final F sixth, final G seventh, final H eighth, final I ninth) {
        return new ImmutableNona<A, B, C, D, E, F, G, H, I>(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth);
    }

    /**
     * <p>
     * Obtains a mutable nona of from nine objects inferring the generic types.
     * </p>
     * 
     * <p>
     * This factory allows the nona to be created using inference to obtain the
     * generic types.
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
     * @param <H>
     *            the eighth element type
     * @param <I>
     *            the ninth element type
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
     * @return an nona formed from the nine parameters, not null
     */
    public static <A, B, C, D, E, F, G, H, I> MutableNona<A, B, C, D, E, F, G, H, I> ofMutable(final A first, final B second, final C third,
            final D fourth, final E fifth, final F sixth, final G seventh, final H eighth, final I ninth) {
        return new MutableNona<A, B, C, D, E, F, G, H, I>(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth);
    }
}
