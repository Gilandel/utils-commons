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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Abstract version for overriding purpose, don't forgot to keep it immutable.
 * </p>
 * 
 * <p>
 * An immutable generic consisting of {@code Object} elements.
 * </p>
 * 
 * <p>
 * Although the implementation is immutable, there is no restriction on the
 * objects that may be stored. If mutable objects are stored in the generic,
 * then the generic itself effectively becomes mutable.
 * </p>
 * 
 * <p>
 * #ThreadSafe# if all objects are thread-safe
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
public abstract class AbstractImmutableGeneric<T> extends Generic<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1718896890557722887L;

    /** All objects */
    private final List<T> all;

    /**
     * Create a new immutable generic instance.
     *
     * @param objects
     *            the values, may be null but not the whole array, ex:
     *            <tt>(Object[]) null</tt>
     */
    @SafeVarargs
    public AbstractImmutableGeneric(final T... objects) {
        super();
        this.all = Collections.synchronizedList(Arrays.asList(objects));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(final int index) {
        synchronized (this.all) {
            return this.all.get(index);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * The use of this list must be done in synchronized block
     * </p>
     */
    @Override
    public List<T> getList() {
        return this.all;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        synchronized (this.all) {
            return this.all.size();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<T> getAll() {
        synchronized (this.all) {
            return Collections.unmodifiableList(this.all);
        }
    }
}
