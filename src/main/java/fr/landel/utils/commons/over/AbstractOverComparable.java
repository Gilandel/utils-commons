/*
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
package fr.landel.utils.commons.over;

/**
 * Abstract class to force implementation of Comparable methods.
 *
 * @since Jul 14, 2015
 * @author Gilles Landel
 * 
 * @param <O>
 *            The over object type.
 */
public abstract class AbstractOverComparable<O extends AbstractOverComparable<O>> extends AbstractOverObject<O> implements Comparable<O> {

    /**
     * Default constructor (mainly for de-serialization).
     */
    public AbstractOverComparable() {
        super(null);
    }

    /**
     * Constructor
     *
     * @param clazz
     *            The over class.
     */
    public AbstractOverComparable(final Class<O> clazz) {
        super(clazz);
    }

    /**
     * To force implementation of compareTo.
     * 
     * @param obj
     *            The object.
     * @return The compare to result.
     */
    protected abstract int overCompareTo(O obj);

    @Override
    public int compareTo(O obj) {
        if (obj == null) {
            return -1;
        }
        return this.overCompareTo(obj);
    }
}
