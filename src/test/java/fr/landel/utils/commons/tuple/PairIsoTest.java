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
package fr.landel.utils.commons.tuple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Check {@link PairIso}
 *
 * @since Aug 2, 2016
 * @author Gilles
 *
 */
public class PairIsoTest {

    /**
     * Test method for {@link PairIso#getLeft()}, {@link PairIso#getRight()},
     * {@link PairIso#getKey()}, {@link PairIso#getValue()}.
     */
    @Test
    public void testGet() {
        String key = "key";
        String value = "value";

        PairIso<String> bi = PairIso.of(key, value);

        assertNotNull(bi);
        assertEquals(key, bi.getKey());
        assertEquals(key, bi.getLeft());
        assertEquals(value, bi.getValue());
        assertEquals(value, bi.getRight());
    }

    /**
     * Test method for {@link PairIso#equals(Object)}.
     */
    @Test
    public void testEquals() {
        String key = "key";
        String value = "value";

        PairIso<String> bi = PairIso.of(key, value);

        assertTrue(bi.equals(bi));
        assertTrue(bi.equals(PairIso.of(key, value)));
        assertFalse(bi.equals(PairIso.of(key, null)));
        assertFalse(bi.equals(PairIso.of(null, value)));
        assertFalse(bi.equals(PairIso.of(null, null)));
        PairIso<String> biNull = null;
        assertFalse(bi.equals(biNull));
    }

    /**
     * Test method for {@link PairIso#hashCode()}.
     */
    @Test
    public void testHashCode() {
        String key = "key";
        String value = "value";

        PairIso<String> bi = PairIso.of(key, value);

        assertEquals(key.hashCode() ^ value.hashCode(), bi.hashCode());
    }

    /**
     * Test method for {@link PairIso#compareTo(PairIso)}.
     */
    @Test
    public void testCompareTo() {
        String key = "key";
        String value = "value";

        PairIso<String> bi = PairIso.of(key, value);

        assertEquals(0, bi.compareTo(bi));
        assertEquals(0, bi.compareTo(PairIso.of(key, value)));
        assertEquals(1, bi.compareTo(PairIso.of(key, null)));
        assertEquals(1, bi.compareTo(PairIso.of(null, value)));
        assertEquals(1, bi.compareTo(PairIso.of(null, null)));
        assertEquals(Integer.MAX_VALUE, bi.compareTo(null));
    }

    /**
     * Test method for {@link PairIso#toString()}.
     */
    @Test
    public void testToString() {
        String key = "key";
        String value = "value";

        PairIso<String> bi = PairIso.of(key, value);

        assertEquals("(key, value)", bi.toString());
    }

    /**
     * Test method for {@link PairIso#toString(java.lang.String)}.
     */
    @Test
    public void testToStringString() {
        String key = "key";
        String value = "value";

        PairIso<String> bi = PairIso.of(key, value);

        assertEquals("key=value", bi.toString("%s=%s"));
    }

    /**
     * Test method for
     * {@link PairIso#ofMutable(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testOfMutable() {
        String key = "key";
        String value = "value";
        String value2 = "value2";

        MutablePairIso<String> bi = PairIso.ofMutable(key, value);

        assertEquals(value, bi.getValue());
        assertEquals(value, bi.setValue(value2));
        assertEquals(value2, bi.getValue());

        bi.setRight(value);
        assertEquals(value, bi.getValue());
        bi.setRight(value2);
        assertEquals(value2, bi.getValue());

        assertEquals(key, bi.getKey());
        bi.setLeft(value2);
        assertEquals(value2, bi.getKey());

        assertEquals(value2, bi.getKey());
        assertEquals(value2, bi.setKey(key));
        assertEquals(key, bi.getKey());

        PairIso<String> bii = PairIso.of(key, value);
        try {
            bii.setValue("test");
            fail();
        } catch (UnsupportedOperationException e) {
            assertNotNull(e);
        }

    }
}
