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
package fr.landel.utils.commons.builder;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import fr.landel.utils.commons.Default;
import fr.landel.utils.commons.EnumChar;
import fr.landel.utils.commons.Result;
import fr.landel.utils.commons.StringUtils;

/**
 * ToString base style
 *
 * @since Mar 5, 2017
 * @author Gilles
 *
 */
public abstract class AbstractToStringStyle extends ArrayList<CharSequence> implements ToStringStyle {

    protected static final String EMPTY = "";
    protected static final String EQUALS = EnumChar.EQUALS.getUnicode();
    protected static final String COLON = EnumChar.COLON.getUnicode();
    protected static final String COMMA = EnumChar.COMMA.getUnicode();
    protected static final String QUOTE = EnumChar.QUOTE.getUnicode();
    protected static final String SINGLE_QUOTE = EnumChar.SINGLE_QUOTE.getUnicode();
    protected static final String BRACE_OPEN = EnumChar.BRACE_OPEN.getUnicode();
    protected static final String BRACE_CLOSE = EnumChar.BRACE_CLOSE.getUnicode();
    protected static final String BRACKET_OPEN = EnumChar.BRACKET_OPEN.getUnicode();
    protected static final String BRACKET_CLOSE = EnumChar.BRACKET_CLOSE.getUnicode();
    protected static final String PARENTHESIS_OPEN = EnumChar.PARENTHESIS_OPEN.getUnicode();
    protected static final String PARENTHESIS_CLOSE = EnumChar.PARENTHESIS_CLOSE.getUnicode();

    protected static final Function<CharSequence, CharSequence> FORMATTER_NOTHING = s -> s;
    protected static final Function<CharSequence, CharSequence> FORMATTER_ESCAPE_QUOTES = s -> String.valueOf(s).replace(QUOTE, "\\\"");
    protected static final Function<CharSequence, CharSequence> FORMATTER_REMOVE_QUOTES = s -> String.valueOf(s).replace(QUOTE, EMPTY)
            .replace(SINGLE_QUOTE, EMPTY);

    protected static final Predicate<CharSequence> PREDICATE_TRUE = v -> true;
    protected static final Predicate<CharSequence> PREDICATE_BRACE_NOT_SURRONDED = v -> !(v.charAt(0) == '{'
            && v.charAt(v.length() - 1) == '}');
    protected static final Predicate<CharSequence> PREDICATE_BRACKET_NOT_SURROUNDED = v -> !(v.charAt(0) == '['
            && v.charAt(v.length() - 1) == ']');

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8130375854086601461L;

    private String title;

    @Override
    public ToStringStyle setObject(final Object object) {
        Objects.requireNonNull(object);

        if (object instanceof Class) {
            this.title = ((Class<?>) object).getCanonicalName();
        } else if (CharSequence.class.isAssignableFrom(object.getClass())) {
            this.title = String.valueOf(object);
        } else {
            this.title = object.getClass().getCanonicalName();
        }
        return this;
    }

    @Override
    public void append(final Object object) {
        this.append(object, null, null);
    }

    @Override
    public <T> void append(final T object, final Predicate<T> predicate) {
        this.append(object, predicate, null);
    }

    @Override
    public <T> void append(final T object, final Function<T, CharSequence> formatter) {
        this.append(object, null, formatter);
    }

    @Override
    public void append(final CharSequence key, final Object value) {
        this.append(key, value, null, null);
    }

    @Override
    public <T> void append(final CharSequence key, final T value, final Predicate<T> predicate) {
        this.append(key, value, predicate, null);
    }

    @Override
    public <T> void append(final CharSequence key, final T value, final Function<T, CharSequence> formatter) {
        this.append(key, value, null, formatter);
    }

    @Override
    public <T> void append(final T object, final Predicate<T> predicate, final Function<T, CharSequence> formatter) {
        if (predicate == null || predicate.test(object)) {
            final StringBuilder builder = new StringBuilder();
            this.applyValueFormatter(builder, object, formatter);
            this.add(builder);
        }
    }

    @Override
    public <T> void append(final CharSequence key, final T value, final Predicate<T> predicate, final Function<T, CharSequence> formatter) {
        if (predicate == null || predicate.test(value)) {
            final StringBuilder builder = new StringBuilder(this.getKeyStart()).append(this.getKeyFormatter().apply(key))
                    .append(this.getKeyEnd()).append(this.getPropertySeparator());
            this.applyValueFormatter(builder, value, formatter);
            this.add(builder);
        }
    }

    private <T> void applyValueFormatter(final StringBuilder builder, final T value, final Function<T, CharSequence> formatter) {

        final boolean isContainer = value != null && (value instanceof Iterable || value instanceof Map || value.getClass().isArray());

        if (isContainer) {
            builder.append(this.getContainerStart());
            if (value instanceof Map) {
                final Function<CharSequence, CharSequence> keyFormatter = this.getKeyFormatter();
                final String keyStart = this.getKeyStart();
                final String keyEnd = this.getKeyEnd();
                final String propertySeparator = this.getPropertySeparator();
                builder.append(StringUtils.join((Map<?, ?>) value, this.getPropertiesSeparator(), entry -> {
                    final StringBuilder localBuilder = new StringBuilder(keyStart)
                            .append(keyFormatter.apply(String.valueOf(entry.getKey()))).append(keyEnd).append(propertySeparator);
                    applyValueFormatter(localBuilder, entry.getValue(), null);
                    return localBuilder.toString();
                }));
            } else if (value instanceof Iterable) {
                builder.append(StringUtils.join((Iterable<?>) value, this.getPropertiesSeparator(), t -> {
                    final StringBuilder localBuilder = new StringBuilder();
                    applyValueFormatter(localBuilder, t, null);
                    return localBuilder.toString();
                }));
            } else {
                builder.append(StringUtils.join((Object[]) value, this.getPropertiesSeparator(), t -> {
                    final StringBuilder localBuilder = new StringBuilder();
                    applyValueFormatter(localBuilder, t, null);
                    return localBuilder.toString();
                }));
            }
            builder.append(this.getContainerEnd());
        } else {
            CharSequence formattedValue;
            boolean surround = true;

            if (formatter != null) {
                formattedValue = formatter.apply(value);
            } else {
                formattedValue = String.valueOf(value).trim();
                surround = this.applyValueFormatter().test(formattedValue);
                if (surround) {
                    formattedValue = this.getValueFormatter().apply(formattedValue);
                }
            }

            if (surround) {
                builder.append(this.getValueStart());
            }

            builder.append(formattedValue);

            if (surround) {
                builder.append(this.getValueEnd());
            }
        }
    }

    @Override
    public void appendIfNotNull(final Object value) {
        this.appendIfNotNull(value, null, null);
    }

    @Override
    public <T> void appendIfNotNull(final T value, final Predicate<T> predicate) {
        this.appendIfNotNull(value, predicate, null);
    }

    @Override
    public <T> void appendIfNotNull(final T value, final Function<T, CharSequence> formatter) {
        this.appendIfNotNull(value, null, formatter);
    }

    @Override
    public <T> void appendIfNotNull(final T value, final Predicate<T> predicate, final Function<T, CharSequence> formatter) {
        if (value != null) {
            this.append(value, predicate, formatter);
        }
    }

    @Override
    public void appendIfNotNull(final CharSequence key, final Object value) {
        this.appendIfNotNull(key, value, null, null);
    }

    @Override
    public <T> void appendIfNotNull(final CharSequence key, final T value, final Predicate<T> predicate) {
        this.appendIfNotNull(key, value, predicate, null);
    }

    @Override
    public <T> void appendIfNotNull(final CharSequence key, final T value, final Function<T, CharSequence> formatter) {
        this.appendIfNotNull(key, value, null, formatter);
    }

    @Override
    public <T> void appendIfNotNull(final CharSequence key, final T value, final Predicate<T> predicate,
            final Function<T, CharSequence> formatter) {
        if (value != null) {
            this.append(key, value, predicate, formatter);
        }
    }

    @Override
    public <T> void appendDefault(final Default<T> value) {
        this.appendDefault(value, null, null);
    }

    @Override
    public <T> void appendDefault(final Default<T> value, final Predicate<T> predicate) {
        this.appendDefault(value, predicate, null);
    }

    @Override
    public <T> void appendDefault(final Default<T> value, final Function<T, CharSequence> formatter) {
        this.appendDefault(value, null, formatter);
    }

    @Override
    public <T> void appendDefault(final Default<T> value, final Predicate<T> predicate, final Function<T, CharSequence> formatter) {
        final T defaultResult = value.get();
        if (predicate == null || predicate.test(defaultResult)) {
            if (formatter != null) {
                this.append(formatter.apply(defaultResult));
            } else {
                this.append(defaultResult);
            }
        }
    }

    @Override
    public <T> void appendDefault(final CharSequence key, final Default<T> value) {
        this.appendDefault(key, value, null, null);
    }

    @Override
    public <T> void appendDefault(final CharSequence key, final Default<T> value, final Predicate<T> predicate) {
        this.appendDefault(key, value, predicate, null);
    }

    @Override
    public <T> void appendDefault(final CharSequence key, final Default<T> value, final Function<T, CharSequence> formatter) {
        this.appendDefault(key, value, null, formatter);
    }

    @Override
    public <T> void appendDefault(final CharSequence key, final Default<T> value, final Predicate<T> predicate,
            final Function<T, CharSequence> formatter) {
        final T defaultResult = value.get();
        if (predicate == null || predicate.test(defaultResult)) {
            if (formatter != null) {
                this.append(key, formatter.apply(defaultResult));
            } else {
                this.append(key, defaultResult);
            }
        }
    }

    @Override
    public <T> void appendIfPresent(final Optional<T> value) {
        this.appendIfPresent(value, null, null);
    }

    @Override
    public <T> void appendIfPresent(final Optional<T> value, final Predicate<T> predicate) {
        this.appendIfPresent(value, predicate, null);
    }

    @Override
    public <T> void appendIfPresent(final Optional<T> value, final Function<T, CharSequence> formatter) {
        this.appendIfPresent(value, null, formatter);
    }

    @Override
    public <T> void appendIfPresent(final Optional<T> value, final Predicate<T> predicate, final Function<T, CharSequence> formatter) {
        if (value.isPresent()) {
            final T optionalResult = value.get();
            if (predicate == null || predicate.test(optionalResult)) {
                if (formatter != null) {
                    this.append(formatter.apply(optionalResult));
                } else {
                    this.append(optionalResult);
                }
            }
        }
    }

    @Override
    public <T> void appendIfPresent(final CharSequence key, final Optional<T> value) {
        this.appendIfPresent(key, value, null, null);
    }

    @Override
    public <T> void appendIfPresent(final CharSequence key, final Optional<T> value, final Predicate<T> predicate) {
        this.appendIfPresent(key, value, predicate, null);
    }

    @Override
    public <T> void appendIfPresent(final CharSequence key, final Optional<T> value, final Function<T, CharSequence> formatter) {
        this.appendIfPresent(key, value, null, formatter);
    }

    @Override
    public <T> void appendIfPresent(final CharSequence key, final Optional<T> value, final Predicate<T> predicate,
            final Function<T, CharSequence> formatter) {
        if (value.isPresent()) {
            final T optionalResult = value.get();
            if (predicate == null || predicate.test(optionalResult)) {
                if (formatter != null) {
                    this.append(key, formatter.apply(optionalResult));
                } else {
                    this.append(key, optionalResult);
                }
            }
        }
    }

    @Override
    public <T> void appendIfPresent(final Result<T> value) {
        this.appendIfPresent(value, null, null);
    }

    @Override
    public <T> void appendIfPresent(final Result<T> value, final Predicate<T> predicate) {
        this.appendIfPresent(value, predicate, null);
    }

    @Override
    public <T> void appendIfPresent(final Result<T> value, final Function<T, CharSequence> formatter) {
        this.appendIfPresent(value, null, formatter);
    }

    @Override
    public <T> void appendIfPresent(final Result<T> value, final Predicate<T> predicate, final Function<T, CharSequence> formatter) {
        if (value.isPresent()) {
            final T resultResult = value.get();
            if (predicate == null || predicate.test(resultResult)) {
                if (formatter != null) {
                    this.append(formatter.apply(resultResult));
                } else {
                    this.append(resultResult);
                }
            }
        }
    }

    @Override
    public <T> void appendIfPresent(final CharSequence key, final Result<T> value) {
        this.appendIfPresent(key, value, null, null);
    }

    @Override
    public <T> void appendIfPresent(final CharSequence key, final Result<T> value, final Predicate<T> predicate) {
        this.appendIfPresent(key, value, predicate, null);
    }

    @Override
    public <T> void appendIfPresent(final CharSequence key, final Result<T> value, final Function<T, CharSequence> formatter) {
        this.appendIfPresent(key, value, null, formatter);
    }

    @Override
    public <T> void appendIfPresent(final CharSequence key, final Result<T> value, final Predicate<T> predicate,
            final Function<T, CharSequence> formatter) {
        if (value.isPresent()) {
            final T resultResult = value.get();
            if (predicate == null || predicate.test(resultResult)) {
                if (formatter != null) {
                    this.append(key, formatter.apply(resultResult));
                } else {
                    this.append(key, resultResult);
                }
            }
        }
    }

    @Override
    public String build() {
        final StringBuilder builder = new StringBuilder();
        boolean appendEnd = false;
        final String title = this.getTitle();
        if (StringUtils.isNotEmpty(title)) {
            builder.append(this.getStart());
            builder.append(this.getTitleStart());
            builder.append(this.getTitleFormatter().apply(title));
            builder.append(this.getTitleEnd());
            builder.append(this.getTitleSeparator());
            appendEnd = true;
        }
        builder.append(this.getPropertiesStart());
        if (!this.isEmpty()) {
            builder.append(StringUtils.join(this, this.getPropertiesSeparator()));
        }
        builder.append(this.getPropertiesEnd());
        if (appendEnd) {
            builder.append(this.getEnd());
        }
        return builder.toString();
    }

    /**
     * Get the title
     * 
     * @return a {@link String} representing the title
     */
    protected String getTitle() {
        return this.title;
    }

    /**
     * @return the start tag
     */
    protected abstract String getStart();

    /**
     * @return the title formatter
     */
    protected abstract Function<CharSequence, CharSequence> getTitleFormatter();

    /**
     * @return the title start tag
     */
    protected abstract String getTitleStart();

    /**
     * @return the title end tag
     */
    protected abstract String getTitleEnd();

    /**
     * @return the title separator tag
     */
    protected abstract String getTitleSeparator();

    /**
     * @return the global properties start tag
     */
    protected abstract String getPropertiesStart();

    /**
     * @return the key formatter
     */
    protected abstract Function<CharSequence, CharSequence> getKeyFormatter();

    /**
     * @return the key start tag
     */
    protected abstract String getKeyStart();

    /**
     * @return the key end tag
     */
    protected abstract String getKeyEnd();

    /**
     * @return the property separator (ex: '=')
     */
    protected abstract String getPropertySeparator();

    /**
     * @return a predicate that validates if the value formatter has to be
     *         applied on the value
     */
    protected abstract Predicate<CharSequence> applyValueFormatter();

    /**
     * @return the value formatter
     */
    protected abstract Function<CharSequence, CharSequence> getValueFormatter();

    /**
     * @return the value start tag
     */
    protected abstract String getValueStart();

    /**
     * @return the value end tag
     */
    protected abstract String getValueEnd();

    /**
     * @return the properties separator (ex: ',')
     */
    protected abstract String getPropertiesSeparator();

    /**
     * @return the global properties end tag
     */
    protected abstract String getPropertiesEnd();

    /**
     * @return the end tag
     */
    protected abstract String getEnd();

    /**
     * @return the start tag container (for list, array, map...)
     */
    protected abstract String getContainerStart();

    /**
     * @return the end tag container (for list, array, map...)
     */
    protected abstract String getContainerEnd();
}
