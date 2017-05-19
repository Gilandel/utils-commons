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

public class Entity1 implements EqualsBuilderAnnotation<Entity1> {

    private String name;

    @EqualsProperty
    private long value;

    @EqualsProperty
    private String description;

    public Entity1() {
        this.prepareEqualsBuilder();
    }

    /**
     * @return the name
     */
    protected String getName() {
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    protected long getValue() {
        return this.value;
    }

    /**
     * @param value
     *            the value to set
     */
    protected void setValue(long value) {
        this.value = value;
    }

    /**
     * @return the description
     */
    protected String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            the description to set
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        return this.buildEquals(obj);
    }

    @Override
    public int hashCode() {
        return this.buildHashCode();
    }
}
