package samples.builder;

public class Entity2 extends AbstractEqualsBuilderAnnotation<Entity2> {

    private String name;

    @EqualsProperty
    private long value;

    @EqualsProperty
    private String description;

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
}