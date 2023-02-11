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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

/**
 * Check utility class (enumerations).
 * 
 * @since Dec 11, 2015
 * @author Gilles Landel
 * 
 */
public class EnumUtilsTest extends AbstractTest {

    /**
     * Test constructor for {@link EnumUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(EnumUtils.class));
    }

    /**
     * Test method for
     * {@link EnumUtils#getNullIfEmpty(java.lang.Class, java.lang.String)} .
     */
    @Test
    public void testGetNullIfEmpty() {
        assertEquals(EnumUtilsData.FIELD, EnumUtils.getNullIfEmpty(EnumUtilsData.class, "FIELD"));
        assertNull(EnumUtils.getNullIfEmpty(null, "FIELD"));
        assertNull(EnumUtils.getNullIfEmpty(EnumUtilsData.class, ""));

        Logger logger = (Logger) LoggerFactory.getLogger(EnumUtils.class);
        logger.setLevel(Level.INFO);
        assertNull(EnumUtils.getNullIfEmpty(EnumUtilsData.class, "FIEL"));
        logger.setLevel(null);
        assertNull(EnumUtils.getNullIfEmpty(EnumUtilsData.class, "FIEL"));
    }
}
