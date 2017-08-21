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
package fr.landel.utils.commons.builder;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * ToString JSON style
 *
 * @since Mar 5, 2017
 * @author Gilles
 *
 */
public class ToStringStyleJSONQuoted extends AbstractToStringStyle {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1671814171209301361L;

    @Override
    protected String getStart() {
        return BRACE_OPEN;
    }

    @Override
    protected Function<CharSequence, CharSequence> getTitleFormatter() {
        return FORMATTER_ESCAPE_QUOTES;
    }

    @Override
    protected String getTitleStart() {
        return QUOTE;
    }

    @Override
    protected String getTitleEnd() {
        return QUOTE;
    }

    @Override
    protected String getTitleSeparator() {
        return COLON;
    }

    @Override
    protected String getPropertiesStart() {
        return BRACE_OPEN;
    }

    @Override
    protected Function<CharSequence, CharSequence> getKeyFormatter() {
        return FORMATTER_ESCAPE_QUOTES;
    }

    @Override
    protected String getKeyStart() {
        return QUOTE;
    }

    @Override
    protected String getKeyEnd() {
        return QUOTE;
    }

    @Override
    protected String getPropertySeparator() {
        return COLON;
    }

    @Override
    protected Predicate<CharSequence> applyValueFormatter() {
        return PREDICATE_BRACE_NOT_SURRONDED;
    }

    @Override
    protected Function<CharSequence, CharSequence> getValueFormatter() {
        return FORMATTER_ESCAPE_QUOTES;
    }

    @Override
    protected String getValueStart() {
        return QUOTE;
    }

    @Override
    protected String getValueEnd() {
        return QUOTE;
    }

    @Override
    protected String getPropertiesSeparator() {
        return COMMA;
    }

    @Override
    protected String getPropertiesEnd() {
        return BRACE_CLOSE;
    }

    @Override
    protected String getEnd() {
        return BRACE_CLOSE;
    }

    @Override
    protected String getContainerStart() {
        return BRACKET_OPEN;
    }

    @Override
    protected String getContainerEnd() {
        return BRACKET_CLOSE;
    }
}
