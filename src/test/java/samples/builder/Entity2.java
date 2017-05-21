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

public class Entity2 extends AbstractBuilderAnnotation<Entity2> {

    @ToStringProperty
    private String name;

    @ToStringProperty
    @EqualsProperty
    private long value;

    @EqualsProperty
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
}
