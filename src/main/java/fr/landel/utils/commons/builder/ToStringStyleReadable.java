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
 * ToString readable style
 *
 * @since Mar 5, 2017
 * @author Gilles
 *
 */
public class ToStringStyleReadable extends AbstractToStringStyle {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1671814171209301363L;

    private static final String EQUALS = " = ";
    private static final String START = "\n[";
    private static final String END = BRACKET_CLOSE;
    private static final String SEP = ",\n";
    private static final String QUOTE = "'";

    @Override
    protected String getStart() {
        return EMPTY;
    }

    @Override
    protected Function<CharSequence, CharSequence> getTitleFormatter() {
        return FORMATTER_NOTHING;
    }

    @Override
    protected String getTitleStart() {
        return EMPTY;
    }

    @Override
    protected String getTitleEnd() {
        return EMPTY;
    }

    @Override
    protected String getTitleSeparator() {
        return EQUALS;
    }

    @Override
    protected String getPropertiesStart() {
        return START;
    }

    @Override
    protected Function<CharSequence, CharSequence> getKeyFormatter() {
        return FORMATTER_NOTHING;
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
        return EQUALS;
    }

    @Override
    protected Predicate<CharSequence> applyValueFormatter() {
        return PREDICATE_BRACKET_NOT_SURROUNDED;
    }

    @Override
    protected Function<CharSequence, CharSequence> getValueFormatter() {
        return FORMATTER_NOTHING;
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
        return SEP;
    }

    @Override
    protected String getPropertiesEnd() {
        return END;
    }

    @Override
    protected String getEnd() {
        return EMPTY;
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
