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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import fr.landel.utils.commons.builder.EqualsBuilder2;
import fr.landel.utils.commons.builder.HashCodeBuilder2;

public abstract class AbstractEqualsBuilderAnnotation<T extends AbstractEqualsBuilderAnnotation<T>> {

    private static final String EQUALS_ERROR_FIELD = "equals_error_field";
    private static final List<Function<AbstractEqualsBuilderAnnotation<?>, Object>> EQUALS_FUNCTIONS = new ArrayList<>();

    private static boolean analyzed = false;

    protected AbstractEqualsBuilderAnnotation() {
        if (!analyzed) {
            Field[] fields = this.getClass().getDeclaredFields();

            for (Field field : fields) {
                final boolean accessible = field.isAccessible();
                field.setAccessible(true);
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (EqualsProperty.class.equals(annotation.annotationType())) {
                        EQUALS_FUNCTIONS.add(o -> {
                            try {
                                return field.get(o);
                            } catch (final IllegalArgumentException | IllegalAccessException e) {
                                return EQUALS_ERROR_FIELD;
                            }
                        });
                    }
                }
                field.setAccessible(accessible);
            }

            analyzed = true;
        }
    }

    @Override
    public boolean equals(Object obj) {
        final EqualsBuilder2<AbstractEqualsBuilderAnnotation<?>> equalsBuilder = new EqualsBuilder2<>(this, obj);

        for (Function<AbstractEqualsBuilderAnnotation<?>, Object> function : EQUALS_FUNCTIONS) {
            equalsBuilder.append(function);
        }

        return equalsBuilder.isEqual();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder2<AbstractEqualsBuilderAnnotation<?>> builder = new HashCodeBuilder2<>(this);

        for (Function<AbstractEqualsBuilderAnnotation<?>, Object> function : EQUALS_FUNCTIONS) {
            builder.append(function);
        }
        return builder.toHashCode();
    }
}
