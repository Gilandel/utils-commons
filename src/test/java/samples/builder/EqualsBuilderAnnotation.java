package samples.builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import fr.landel.utils.commons.builder.EqualsBuilder2;
import fr.landel.utils.commons.builder.HashCodeBuilder2;

public interface EqualsBuilderAnnotation<T extends EqualsBuilderAnnotation<T>> {

    ConcurrentMap<Class<?>, List<Function<EqualsBuilderAnnotation<?>, Object>>> EQUALS_FUNCTIONS = new ConcurrentHashMap<>();
    String EQUALS_ERROR_FIELD = "equals_error_field";

    default void prepare() {
        final Class<?> clazz = this.getClass();
        if (!EQUALS_FUNCTIONS.containsKey(clazz)) {
            final Field[] fields = this.getClass().getDeclaredFields();

            final List<Function<EqualsBuilderAnnotation<?>, Object>> list = new ArrayList<>();
            EQUALS_FUNCTIONS.put(clazz, list);

            for (Field field : fields) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                field.setAccessible(true);
                for (Annotation annotation : annotations) {
                    if (EqualsProperty.class.equals(annotation.annotationType())) {
                        list.add(o -> {
                            try {
                                return field.get(o);
                            } catch (final IllegalArgumentException | IllegalAccessException e) {
                                return EQUALS_ERROR_FIELD;
                            }
                        });
                    }
                }
            }
        }
    }

    default boolean buildEquals(Object obj) {
        final EqualsBuilder2<EqualsBuilderAnnotation<?>> equalsBuilder = new EqualsBuilder2<>(this, obj);

        for (Function<EqualsBuilderAnnotation<?>, Object> function : EQUALS_FUNCTIONS.get(this.getClass())) {
            equalsBuilder.append(function);
        }

        return equalsBuilder.isEqual();
    }

    default int buildHashCode() {
        final HashCodeBuilder2<EqualsBuilderAnnotation<?>> builder = new HashCodeBuilder2<>(this);

        for (Function<EqualsBuilderAnnotation<?>, Object> function : EQUALS_FUNCTIONS.get(this.getClass())) {
            builder.append(function);
        }
        return builder.toHashCode();
    }
}
