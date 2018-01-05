/*
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
package fr.landel.utils.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to manage enumerations.
 *
 * @since Nov 27, 2015
 * @author Gilles Landel
 *
 */
public final class EnumUtils extends org.apache.commons.lang3.EnumUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnumUtils.class);

    /**
     * Hidden constructor.
     */
    private EnumUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the enumeration if name is not empty and null otherwise.
     * 
     * @param enumType
     *            The type of the enumeration
     * @param name
     *            The string to check, may be null
     * @param <T>
     *            Type of the enumeration
     * @return The enumeration object or null
     */
    public static <T extends Enum<T>> T getNullIfEmpty(final Class<T> enumType, final String name) {
        if (StringUtils.isNotEmpty(name) && enumType != null) {
            try {
                return Enum.valueOf(enumType, name);
            } catch (IllegalArgumentException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(StringUtils.concat("Name parameter '", name, "' not found in enumeration: ", enumType), e);
                }
            }
        }
        return null;
    }
}
