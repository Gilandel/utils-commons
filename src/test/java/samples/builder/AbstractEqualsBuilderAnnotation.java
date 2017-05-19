package samples.builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import fr.landel.utils.commons.builder.EqualsBuilder2;
import fr.landel.utils.commons.builder.HashCodeBuilder2;

public abstract class AbstractEqualsBuilderAnnotation<T extends AbstractEqualsBuilderAnnotation<T>> {

    private static boolean analyzed = false;
    private static final List<Function<AbstractEqualsBuilderAnnotation<?>, Object>> EQUALS_FUNCTIONS = new ArrayList<>();

    protected AbstractEqualsBuilderAnnotation() {
        if (!analyzed) {
            Field[] fields = this.getClass().getDeclaredFields();

            for (Field field : fields) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                field.setAccessible(true);
                for (Annotation annotation : annotations) {
                    if (EqualsProperty.class.equals(annotation.annotationType())) {
                        EQUALS_FUNCTIONS.add(o -> {
                            try {
                                return field.get(o);
                            } catch (final IllegalArgumentException | IllegalAccessException e) {
                                return null;
                            }
                        });
                    }
                }
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
