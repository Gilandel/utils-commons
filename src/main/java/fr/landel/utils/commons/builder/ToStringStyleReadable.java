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
