/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilles Landel
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

/**
 * Check {@link MapUtils2}
 *
 * @since Mar 24, 2017
 * @author Gilles
 *
 */
public class MapUtils2Test extends AbstractTest {

    /**
     * Test constructor for {@link MapUtils2} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(MapUtils2.class));
    }

    /**
     * Test method for
     * {@link MapUtils2#newHashMap(java.lang.Class, java.lang.Class, java.lang.Object[])}.
     */
    @Test
    public void testNewHashMapClass() {
        Map<String, Long> map = MapUtils2.newHashMap(String.class, Long.class, "key1", 1L, "key2", 2L);

        assertTrue(map instanceof HashMap);
        assertEquals(2, map.size());

        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("key1", 1L);
        expectedMap.put("key2", 2L);

        for (Entry<String, Long> entry : expectedMap.entrySet()) {
            assertTrue(map.containsKey(entry.getKey()));
            assertEquals(entry.getValue(), map.get(entry.getKey()));
        }

        map = MapUtils2.newHashMap(String.class, Long.class);

        assertTrue(map instanceof HashMap);
        assertTrue(map.isEmpty());

        map = MapUtils2.newHashMap(String.class, Long.class, new Object[0]);

        assertTrue(map instanceof HashMap);
        assertTrue(map.isEmpty());

        assertException(() -> {
            MapUtils2.newHashMap(null, Long.class, "", "");
        }, NullPointerException.class);

        assertException(() -> {
            MapUtils2.newHashMap(String.class, null, "", "");
        }, NullPointerException.class);
    }

    /**
     * Test method for {@link MapUtils2#newHashMap(java.lang.Object[])}.
     */
    @Test
    public void testNewHashMapObjects() {
        Map<String, Long> map = MapUtils2.newHashMap("key1", 1L, "key2", 2L);

        assertTrue(map instanceof HashMap);
        assertEquals(2, map.size());

        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("key1", 1L);
        expectedMap.put("key2", 2L);

        for (Entry<String, Long> entry : expectedMap.entrySet()) {
            assertTrue(map.containsKey(entry.getKey()));
            assertEquals(entry.getValue(), map.get(entry.getKey()));
        }

        map = MapUtils2.newHashMap();

        assertTrue(map instanceof HashMap);
        assertTrue(map.isEmpty());

        map = MapUtils2.newHashMap(new Object[0]);

        assertTrue(map instanceof HashMap);
        assertTrue(map.isEmpty());
    }

    /**
     * Test method for
     * {@link MapUtils2#newHashMap(org.apache.commons.lang3.tuple.Pair<K,V>[])}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testNewHashMapPair() {
        Map<String, Long> map = MapUtils2.newHashMap(Pair.of("key1", 1L), Pair.of("key2", 2L));

        assertTrue(map instanceof HashMap);
        assertEquals(2, map.size());

        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("key1", 1L);
        expectedMap.put("key2", 2L);

        for (Entry<String, Long> entry : expectedMap.entrySet()) {
            assertTrue(map.containsKey(entry.getKey()));
            assertEquals(entry.getValue(), map.get(entry.getKey()));
        }

        map = MapUtils2.newHashMap();

        assertTrue(map instanceof HashMap);
        assertTrue(map.isEmpty());

        map = MapUtils2.newHashMap(new Pair[0]);

        assertTrue(map instanceof HashMap);
        assertTrue(map.isEmpty());
    }

    /**
     * Test method for
     * {@link MapUtils2#newMap(java.util.function.Supplier, java.lang.Class, java.lang.Class, java.lang.Object[])}.
     */
    @Test
    public void testNewMapSupplierClass() {
        SortedMap<String, Long> map = MapUtils2.newMap(TreeMap::new, String.class, Long.class, "key1", 1L, "key2", 2L);

        assertTrue(map instanceof TreeMap);
        assertEquals(2, map.size());

        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("key1", 1L);
        expectedMap.put("key2", 2L);

        for (Entry<String, Long> entry : expectedMap.entrySet()) {
            assertTrue(map.containsKey(entry.getKey()));
            assertEquals(entry.getValue(), map.get(entry.getKey()));
        }

        map = MapUtils2.newMap(TreeMap::new, String.class, Long.class);

        assertTrue(map instanceof TreeMap);
        assertTrue(map.isEmpty());

        map = MapUtils2.newMap(() -> new TreeMap<>(Comparators.STRING.desc()), String.class, Long.class, "key1", 1L, "key2", 2L, null, 3L,
                "key4", null, 2d, 5L, "key6", true);

        assertTrue(map instanceof TreeMap);
        assertEquals(4, map.size());

        "key2".equals(map.firstKey());

        map = MapUtils2.newMap(TreeMap::new, String.class, Long.class, new Object[0]);

        assertTrue(map instanceof TreeMap);
        assertTrue(map.isEmpty());

        assertException(() -> {
            MapUtils2.newMap(TreeMap::new, null, Long.class, "", "");
        }, NullPointerException.class);

        assertException(() -> {
            MapUtils2.newMap(TreeMap::new, String.class, null, "", "");
        }, NullPointerException.class);

        assertException(() -> {
            MapUtils2.newMap(null, String.class, Long.class, "", "");
        }, NullPointerException.class);

        assertException(() -> {
            MapUtils2.newMap(TreeMap::new, String.class, Long.class, (Object[]) null);
        }, IllegalArgumentException.class, "objects cannot be null or empty and has to contain an odd number of elements");

        assertException(() -> {
            MapUtils2.newMap(TreeMap::new, String.class, Long.class, "key");
        }, IllegalArgumentException.class, "objects cannot be null or empty and has to contain an odd number of elements");
    }

    /**
     * Test method for
     * {@link MapUtils2#newMap(java.util.function.Supplier, java.lang.Object[])}.
     */
    @Test
    public void testNewMapSupplierObjects() {
        Map<String, Long> map = MapUtils2.newMap(TreeMap::new, "key1", 1L, "key2", 2L);

        assertTrue(map instanceof TreeMap);
        assertEquals(2, map.size());

        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("key1", 1L);
        expectedMap.put("key2", 2L);

        for (Entry<String, Long> entry : expectedMap.entrySet()) {
            assertTrue(map.containsKey(entry.getKey()));
            assertEquals(entry.getValue(), map.get(entry.getKey()));
        }

        map = MapUtils2.newMap(TreeMap::new, new Object[0]);

        assertTrue(map instanceof TreeMap);
        assertTrue(map.isEmpty());

        map = MapUtils2.newMap(TreeMap::new, new Object[0]);

        assertTrue(map instanceof TreeMap);
        assertTrue(map.isEmpty());

        assertException(() -> {
            MapUtils2.newMap(null, new Object[0]);
        }, NullPointerException.class);

        assertException(() -> {
            MapUtils2.newMap(TreeMap::new, (Object[]) null);
        }, IllegalArgumentException.class, "objects cannot be null or empty and has to contain an odd number of elements");

        assertException(() -> {
            MapUtils2.newMap(TreeMap::new, "key");
        }, IllegalArgumentException.class, "objects cannot be null or empty and has to contain an odd number of elements");
    }

    /**
     * Test method for {@link MapUtils2#newMap(java.util.function.Supplier,
     * org.apache.commons.lang3.tuple.Pair<K,V>[])}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testNewMapSupplierPair() {
        Map<String, Long> map = MapUtils2.newMap(TreeMap::new, Pair.of("key1", 1L), Pair.of("key2", 2L));

        assertTrue(map instanceof TreeMap);
        assertEquals(2, map.size());

        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("key1", 1L);
        expectedMap.put("key2", 2L);

        for (Entry<String, Long> entry : expectedMap.entrySet()) {
            assertTrue(map.containsKey(entry.getKey()));
            assertEquals(entry.getValue(), map.get(entry.getKey()));
        }

        map = MapUtils2.newMap(TreeMap::new);

        assertTrue(map instanceof TreeMap);
        assertTrue(map.isEmpty());

        map = MapUtils2.newMap(TreeMap::new, new Pair[0]);

        assertTrue(map instanceof TreeMap);
        assertTrue(map.isEmpty());

        assertException(() -> {
            MapUtils2.newMap(null, new Pair[0]);
        }, NullPointerException.class);

        assertException(() -> {
            MapUtils2.newMap(TreeMap::new, (Pair[]) null);
        }, IllegalArgumentException.class, "objects cannot be null or empty");
    }

    /**
     * Test method for {@link MapUtils2#areKeysEqual(Entry, Entry)}.
     */
    @Test
    public void testAreKeysEqual() {
        Entry<String, Integer> entry1 = new SimpleEntry<>("key", 1);
        Entry<String, Integer> entry1b = new SimpleEntry<>("key", 1);
        Entry<String, Integer> entry2 = new SimpleEntry<>("key", 12);
        Entry<String, Integer> entry3 = new SimpleEntry<>("key1", 1);
        Entry<String, Integer> entry4 = new SimpleEntry<>(null, 1);
        Entry<String, Integer> entry5 = new SimpleEntry<>("key", null);

        assertTrue(MapUtils2.areKeysEqual(entry1, entry1b));
        assertTrue(MapUtils2.areKeysEqual(entry1, entry2));
        assertFalse(MapUtils2.areKeysEqual(entry1, entry3));
        assertFalse(MapUtils2.areKeysEqual(entry1, entry4));
        assertTrue(MapUtils2.areKeysEqual(entry1, entry5));

        assertException(() -> {
            MapUtils2.areKeysEqual((Entry<String, Integer>) null, entry5);
        }, NullPointerException.class, "entries cannot be null");

        assertException(() -> {
            MapUtils2.areKeysEqual(entry1, (Entry<String, Integer>) null);
        }, NullPointerException.class, "entries cannot be null");
    }

    /**
     * Test method for {@link MapUtils2#areValuesEqual(Entry, Entry)}.
     */
    @Test
    public void testAreValuesEqual() {
        Entry<String, Integer> entry1 = new SimpleEntry<>("key", 1);
        Entry<String, Integer> entry1b = new SimpleEntry<>("key", 1);
        Entry<String, Integer> entry2 = new SimpleEntry<>("key", 12);
        Entry<String, Integer> entry3 = new SimpleEntry<>("key1", 1);
        Entry<String, Integer> entry4 = new SimpleEntry<>(null, 1);
        Entry<String, Integer> entry5 = new SimpleEntry<>("key", null);

        assertTrue(MapUtils2.areValuesEqual(entry1, entry1b));
        assertFalse(MapUtils2.areValuesEqual(entry1, entry2));
        assertTrue(MapUtils2.areValuesEqual(entry1, entry3));
        assertTrue(MapUtils2.areValuesEqual(entry1, entry4));
        assertFalse(MapUtils2.areValuesEqual(entry1, entry5));

        assertException(() -> {
            MapUtils2.areValuesEqual((Entry<String, Integer>) null, entry5);
        }, NullPointerException.class, "entries cannot be null");

        assertException(() -> {
            MapUtils2.areValuesEqual(entry1, (Entry<String, Integer>) null);
        }, NullPointerException.class, "entries cannot be null");
    }

    /**
     * Test method for {@link MapUtils2#areEntriesEqual(Entry, Entry)}.
     */
    @Test
    public void testAreEntriesEqual() {
        Entry<String, Integer> entry1 = new SimpleEntry<>("key", 1);
        Entry<String, Integer> entry1b = new SimpleEntry<>("key", 1);
        Entry<String, Integer> entry2 = new SimpleEntry<>("key", 12);
        Entry<String, Integer> entry3 = new SimpleEntry<>("key1", 1);
        Entry<String, Integer> entry4 = new SimpleEntry<>(null, 1);
        Entry<String, Integer> entry5 = new SimpleEntry<>("key", null);

        assertTrue(MapUtils2.areEntriesEqual(entry1, entry1b));
        assertFalse(MapUtils2.areEntriesEqual(entry1, entry2));
        assertFalse(MapUtils2.areEntriesEqual(entry1, entry3));
        assertFalse(MapUtils2.areEntriesEqual(entry1, entry4));
        assertFalse(MapUtils2.areEntriesEqual(entry1, entry5));

        assertException(() -> {
            MapUtils2.areEntriesEqual(null, entry5);
        }, NullPointerException.class, "entries cannot be null");

        assertException(() -> {
            MapUtils2.areEntriesEqual(entry1, null);
        }, NullPointerException.class, "entries cannot be null");
    }
}
