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
 * ToString JSON style
 *
 * @since Mar 5, 2017
 * @author Gilles
 *
 */
public class ToStringStyleJSON extends AbstractToStringStyle {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1671814171209301363L;

    @Override
    protected String getStart() {
        return BRACE_OPEN;
    }

    @Override
    protected Function<CharSequence, CharSequence> getTitleFormatter() {
        return FORMATTER_REMOVE_QUOTES;
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
        return COLON;
    }

    @Override
    protected String getPropertiesStart() {
        return BRACE_OPEN;
    }

    @Override
    protected Function<CharSequence, CharSequence> getKeyFormatter() {
        return FORMATTER_REMOVE_QUOTES;
    }

    @Override
    protected String getKeyStart() {
        return EMPTY;
    }

    @Override
    protected String getKeyEnd() {
        return EMPTY;
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
        return FORMATTER_REMOVE_QUOTES;
    }

    @Override
    protected String getValueStart() {
        return EMPTY;
    }

    @Override
    protected String getValueEnd() {
        return EMPTY;
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
