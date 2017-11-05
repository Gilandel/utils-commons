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
package samples.builder;

import fr.landel.utils.commons.builder.EqualsBuilder2;
import fr.landel.utils.commons.builder.HashCodeBuilder2;

public class Entity3 {

    private String name;
    private long value;
    private String description;

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public long getValue() {
        return this.value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(long value) {
        this.value = value;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        EqualsBuilder2<Entity3> builder = new EqualsBuilder2<>(this, obj);

        builder.append(o -> o.getValue());
        builder.append(o -> o.getDescription());

        return builder.isEqual();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder2<Entity3> builder = new HashCodeBuilder2<>(this);

        builder.append(o -> o.getValue());
        builder.append(o -> o.getDescription());

        return builder.toHashCode();
    }
}
