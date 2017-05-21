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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import fr.landel.utils.commons.builder.EqualsBuilder2;
import fr.landel.utils.commons.builder.HashCodeBuilder2;
import fr.landel.utils.commons.builder.ToStringBuilder;

public interface BuilderAnnotation<T extends BuilderAnnotation<T>> {

    static class Store {
        private static final ConcurrentMap<Class<?>, List<Function<BuilderAnnotation<?>, Object>>> EQUALS_FUNCTIONS = new ConcurrentHashMap<>();
        private static final ConcurrentMap<Class<?>, Map<CharSequence, Function<BuilderAnnotation<?>, Object>>> TOSTRING_FUNCTIONS = new ConcurrentHashMap<>();
        private static final String EQUALS_ERROR_FIELD = "equals_error_field";
        private static final String GETTER_PREFIX = "get";

        private static Function<BuilderAnnotation<?>, Object> getFunction(final Class<?> clazz, final Field field) {
            Function<BuilderAnnotation<?>, Object> function;
            try {
                final Method method = clazz.getDeclaredMethod(GETTER_PREFIX + StringUtils.capitalize(field.getName()));
                function = o -> {
                    try {
                        return method.invoke(o);
                    } catch (final InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
                        return EQUALS_ERROR_FIELD;
                    }
                };
            } catch (NoSuchMethodException | SecurityException e1) {
                field.setAccessible(true);
                function = o -> {
                    try {
                        return field.get(o);
                    } catch (final IllegalArgumentException | IllegalAccessException e) {
                        return EQUALS_ERROR_FIELD;
                    }
                };
            }
            return function;
        }
    }

    default void prepareBuilders() {
        final Class<?> clazz = this.getClass();
        if (!Store.EQUALS_FUNCTIONS.containsKey(clazz)) {

            final List<Function<BuilderAnnotation<?>, Object>> list = new ArrayList<>();
            Store.EQUALS_FUNCTIONS.put(clazz, list);
            final Map<CharSequence, Function<BuilderAnnotation<?>, Object>> map = new HashMap<>();
            Store.TOSTRING_FUNCTIONS.put(clazz, map);

            for (Field field : this.getClass().getDeclaredFields()) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (EqualsProperty.class.equals(annotation.annotationType())) {
                        list.add(Store.getFunction(clazz, field));
                    } else if (ToStringProperty.class.equals(annotation.annotationType())) {
                        map.put(field.getName(), Store.getFunction(clazz, field));
                    }
                }
            }
        }
    }

    default boolean buildEquals(Object obj) {
        final EqualsBuilder2<BuilderAnnotation<?>> equalsBuilder = new EqualsBuilder2<>(this, obj);

        for (Function<BuilderAnnotation<?>, Object> function : Store.EQUALS_FUNCTIONS.get(this.getClass())) {
            equalsBuilder.append(function);
        }

        return equalsBuilder.isEqual();
    }

    default int buildHashCode() {
        final HashCodeBuilder2<BuilderAnnotation<?>> builder = new HashCodeBuilder2<>(this);

        for (Function<BuilderAnnotation<?>, Object> function : Store.EQUALS_FUNCTIONS.get(this.getClass())) {
            builder.append(function);
        }
        return builder.toHashCode();
    }

    default String buildToString() {
        final ToStringBuilder builder = new ToStringBuilder(this.getClass().getSimpleName());

        for (Entry<CharSequence, Function<BuilderAnnotation<?>, Object>> entry : Store.TOSTRING_FUNCTIONS.get(this.getClass()).entrySet()) {
            builder.append(entry.getKey(), entry.getValue().apply(this));
        }

        return builder.build();
    }
}
