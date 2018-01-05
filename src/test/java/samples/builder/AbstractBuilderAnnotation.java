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
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import fr.landel.utils.commons.builder.EqualsBuilder2;
import fr.landel.utils.commons.builder.HashCodeBuilder2;
import fr.landel.utils.commons.builder.ToStringBuilder;

public abstract class AbstractBuilderAnnotation<T extends AbstractBuilderAnnotation<T>> {

    private static final String EQUALS_ERROR_FIELD = "equals_error_field";
    private static final String GETTER_PREFIX = "get";
    private static final List<Function<AbstractBuilderAnnotation<?>, Object>> EQUALS_FUNCTIONS = new ArrayList<>();
    private static final Map<CharSequence, Function<AbstractBuilderAnnotation<?>, Object>> TOSTRING_FUNCTIONS = new HashMap<>();

    private static boolean analyzed = false;
    private static String name;

    protected AbstractBuilderAnnotation() {
        if (!analyzed) {
            final Class<?> clazz = this.getClass();
            name = clazz.getSimpleName();

            for (Field field : clazz.getDeclaredFields()) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (EqualsProperty.class.equals(annotation.annotationType())) {
                        EQUALS_FUNCTIONS.add(getFunction(clazz, field));
                    } else if (ToStringProperty.class.equals(annotation.annotationType())) {
                        TOSTRING_FUNCTIONS.put(field.getName(), getFunction(clazz, field));
                    }
                }
            }

            analyzed = true;
        }
    }

    private static Function<AbstractBuilderAnnotation<?>, Object> getFunction(final Class<?> clazz, final Field field) {
        Function<AbstractBuilderAnnotation<?>, Object> function;
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

    @Override
    public boolean equals(Object obj) {
        final EqualsBuilder2<AbstractBuilderAnnotation<?>> equalsBuilder = new EqualsBuilder2<>(this, obj);

        for (Function<AbstractBuilderAnnotation<?>, Object> function : EQUALS_FUNCTIONS) {
            equalsBuilder.append(function);
        }

        return equalsBuilder.isEqual();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder2<AbstractBuilderAnnotation<?>> builder = new HashCodeBuilder2<>(this);

        for (Function<AbstractBuilderAnnotation<?>, Object> function : EQUALS_FUNCTIONS) {
            builder.append(function);
        }
        return builder.toHashCode();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(name);

        for (Entry<CharSequence, Function<AbstractBuilderAnnotation<?>, Object>> entry : TOSTRING_FUNCTIONS.entrySet()) {
            builder.append(entry.getKey(), entry.getValue().apply(this));
        }

        return builder.build();
    }
}
