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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Check {@link ToStringStyles}
 *
 * @since Mar 11, 2017
 * @author Gilles
 *
 */
public class ToStringStylesTest {

    /**
     * Test method for {@link ToStringStyles#getSupplier()}.
     */
    @Test
    public void testGetSupplier() {
        assertTrue(ToStringStyleDefault.class.isAssignableFrom(ToStringStyles.DEFAULT.getSupplier().get().getClass()));
        assertTrue(ToStringStyleJSON.class.isAssignableFrom(ToStringStyles.JSON.getSupplier().get().getClass()));
        assertTrue(ToStringStyleJSONSpaced.class.isAssignableFrom(ToStringStyles.JSON_SPACED.getSupplier().get().getClass()));
        assertTrue(ToStringStyleJSONQuoted.class.isAssignableFrom(ToStringStyles.JSON_QUOTED.getSupplier().get().getClass()));
        assertTrue(ToStringStyleParenthesis.class.isAssignableFrom(ToStringStyles.PARENTHESIS.getSupplier().get().getClass()));
        assertTrue(ToStringStyleReadable.class.isAssignableFrom(ToStringStyles.READABLE.getSupplier().get().getClass()));

        assertEquals(ToStringStyles.DEFAULT, ToStringStyles.valueOf("DEFAULT"));
        assertEquals(0, ToStringStyles.DEFAULT.ordinal());
        assertEquals("DEFAULT", ToStringStyles.DEFAULT.name());
    }
}
