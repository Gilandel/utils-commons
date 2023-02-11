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
package fr.landel.utils.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Check {@link EnumChar}
 *
 * @since May 31, 2016
 * @author Gilles
 *
 */
public class EnumCharTest {

    /**
     * Test method for {@link EnumChar#EnumChar(EnumChar)}.
     */
    @Test
    public void testEnumCharEnumChar() {
        assertEquals(EnumChar.DOT.getCharacter(), EnumChar.PERIOD.getCharacter());
        assertEquals(EnumChar.DOT.getCode(), EnumChar.PERIOD.getCode());
        assertEquals(EnumChar.DOT.getHTML(), EnumChar.PERIOD.getHTML());
        assertEquals(EnumChar.DOT.getUnicode(), EnumChar.PERIOD.getUnicode());

        assertEquals(EnumChar.NUL, EnumChar.valueOf("NUL"));
        assertEquals("NUL", EnumChar.NUL.name());
        assertEquals(0, EnumChar.NUL.ordinal());
    }

    /**
     * Test method for {@link EnumChar#getCharacter()}.
     */
    @Test
    public void testGetCharacter() {
        assertEquals('@', EnumChar.AT.getCharacter());
    }

    /**
     * Test method for {@link EnumChar#getCode()}.
     */
    @Test
    public void testGetCode() {
        assertEquals(64, EnumChar.AT.getCode());
    }

    /**
     * Test method for {@link EnumChar#getUnicode()}.
     */
    @Test
    public void testGetUnicode() {
        assertEquals("@", EnumChar.AT.getUnicode());
        assertEquals("\u0040", EnumChar.AT.getUnicode());
    }

    /**
     * Test method for {@link EnumChar#getHTML()}.
     */
    @Test
    public void testGetHTML() {
        assertEquals("&commat;", EnumChar.AT.getHTML());
    }

    /**
     * Test method for {@link EnumChar#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("@", EnumChar.AT.toString());
    }
}
