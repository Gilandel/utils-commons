/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilandel
 * %%
 * Authors: Gilles Landel
 * URL: https://github.com/Gilandel
 * 
 * This file is under Apache License, version 2.0 (2004).
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
