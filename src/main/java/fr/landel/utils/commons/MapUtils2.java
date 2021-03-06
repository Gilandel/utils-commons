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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Helper class to create {@link Map}
 *
 * @since Mar 24, 2017
 * @author Gilles
 *
 */
public class MapUtils2 {

    private static final String ERROR_OBJECTS = "objects cannot be null or empty";
    private static final String ERROR_OBJECTS_ODD = ERROR_OBJECTS + " and has to contain an odd number of elements";
    private static final String ERROR_ENTRY_NULL = "entries cannot be null";

    private static final Supplier<IllegalArgumentException> ERROR_OBJECTS_SUPPLIER = () -> new IllegalArgumentException(ERROR_OBJECTS);

    /**
     * Hidden constructor.
     */
    private MapUtils2() {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a new {@link HashMap} from the {@code objects}. Each objects are
     * checked and only added to the map if key and value are assignable from
     * classes.
     * 
     * <p>
     * precondition: {@code keyClass}, {@code keyValue} and {@code objects}
     * cannot be {@code null}
     * </p>
     * 
     * <pre>
     * Map&lt;String, String&gt; map = MapUtils2.newHashMap(String.class, String.class, "key1", "value1", "key2", "value2");
     * 
     * // equivalent
     * Map&lt;String, String&gt; map = new HashMap&lt;&gt;();
     * map.put("key1", "value1");
     * map.put("key2", "value2");
     * </pre>
     * 
     * @param keyClass
     *            the class of key
     * @param valueClass
     *            the class of value
     * @param objects
     *            objects pair to put in the new {@link Map}
     * @param <K>
     *            the type of map key
     * @param <V>
     *            the type of map value
     * @return the new {@link Map}
     * @throws NullPointerException
     *             if {@code keyClass} or {@code valueClass} are {@code null}
     * @throws IllegalArgumentException
     *             if {@code objects} is {@code null} or if {@code objects}
     *             length is even
     */
    public static <K, V> Map<K, V> newHashMap(final Class<K> keyClass, final Class<V> valueClass, Object... objects) {
        return newMap(HashMap::new, keyClass, valueClass, objects);
    }

    /**
     * Create a new {@link HashMap} from the {@code objects}.
     * 
     * <p>
     * precondition: {@code objects} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * Map&lt;String, String&gt; map = MapUtils2.newHashMap("key1", "value1", "key2", "value2");
     * 
     * // equivalent
     * Map&lt;String, String&gt; map = new HashMap&lt;&gt;();
     * map.put("key1", "value1");
     * map.put("key2", "value2");
     * </pre>
     * 
     * @param objects
     *            objects pair to put in the new {@link Map}
     * @param <K>
     *            the type of map key
     * @param <V>
     *            the type of map value
     * @return the new {@link Map}
     * @throws IllegalArgumentException
     *             if {@code objects} is {@code null} or if {@code objects}
     *             length is even
     */
    public static <K, V> Map<K, V> newHashMap(Object... objects) {
        return newMap(HashMap::new, objects);
    }

    /**
     * Create a new {@link HashMap} from the {@code objects}.
     * 
     * <p>
     * precondition: {@code objects} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * Map&lt;String, String&gt; map = MapUtils2.newHashMap(Pair.of("key1", "value1"), Pair.of("key2", "value2"));
     * 
     * // equivalent
     * Map&lt;String, String&gt; map = new HashMap&lt;&gt;();
     * map.put("key1", "value1");
     * map.put("key2", "value2");
     * </pre>
     * 
     * @param objects
     *            objects pair to put in the new {@link Map}
     * @param <K>
     *            the type of map key
     * @param <V>
     *            the type of map value
     * @return the new {@link Map}
     * @throws IllegalArgumentException
     *             if {@code objects} is {@code null}
     */
    @SafeVarargs
    public static <K, V> Map<K, V> newHashMap(Pair<K, V>... objects) {
        return newMap(HashMap::new, objects);
    }

    /**
     * Create a new {@link HashMap} from the {@code objects}. Each objects are
     * checked and only added to the map if key and value are assignable from
     * classes.
     * 
     * <p>
     * precondition: {@code mapProvider}, {@code keyClass}, {@code keyValue} and
     * {@code objects} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * Map&lt;String, String&gt; map = MapUtils2.newHashMap(TreeMap::new, String.class, String.class, "key1", "value1", "key2", "value2");
     * 
     * // equivalent
     * Map&lt;String, String&gt; map = new TreeMap&lt;&gt;();
     * map.put("key1", "value1");
     * map.put("key2", "value2");
     * </pre>
     * 
     * @param mapProvider
     *            map constructor supplier
     * @param keyClass
     *            the class of key
     * @param valueClass
     *            the class of value
     * @param objects
     *            objects pair to put in the new {@link Map}
     * @param <K>
     *            the type of map key
     * @param <V>
     *            the type of map value
     * @param <M>
     *            the type of map
     * @return the new {@link Map}
     * @throws NullPointerException
     *             if {@code mapProvider}, {@code keyClass} or
     *             {@code valueClass} are {@code null}
     * @throws IllegalArgumentException
     *             if {@code objects} is {@code null} or empty or if
     *             {@code objects} length is even
     */
    @SuppressWarnings("unchecked")
    public static <K, V, M extends Map<K, V>> M newMap(final Supplier<M> mapProvider, final Class<K> keyClass, final Class<V> valueClass,
            Object... objects) {
        Objects.requireNonNull(mapProvider);
        Objects.requireNonNull(keyClass);
        Objects.requireNonNull(valueClass);
        if (objects == null || objects.length % 2 != 0) {
            throw new IllegalArgumentException(ERROR_OBJECTS_ODD);
        }

        final M map = mapProvider.get();

        int j;
        for (int i = 0; i < objects.length; i += 2) {
            j = i + 1;
            if ((objects[i] == null || keyClass.isAssignableFrom(objects[i].getClass()))
                    && (objects[j] == null || valueClass.isAssignableFrom(objects[j].getClass()))) {
                map.put((K) objects[i], (V) objects[j]);
            }
        }

        return map;
    }

    /**
     * Create a map from {@code objects}.
     * 
     * <pre>
     * Map&lt;String, String&gt; map = MapUtils2.newMap(TreeMap::new, "key1", "value1", "key2", "value2");
     * 
     * // equivalent
     * Map&lt;String, String&gt; map = new TreeMap&lt;&gt;();
     * map.put("key1", "value1");
     * map.put("key2", "value2");
     * </pre>
     * 
     * @param mapProvider
     *            map constructor supplier
     * @param objects
     *            objects pair to put in the new {@link Map}
     * @param <K>
     *            the type of map key
     * @param <V>
     *            the type of map value
     * @param <M>
     *            the type of map
     * @return the new {@link Map}
     * @throws NullPointerException
     *             if {@code mapProvider} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code objects} is {@code null} or empty or if
     *             {@code objects} length is even
     */
    @SuppressWarnings("unchecked")
    @SafeVarargs
    public static <K, V, M extends Map<K, V>> M newMap(final Supplier<M> mapProvider, Object... objects) {
        Objects.requireNonNull(mapProvider);
        if (objects == null || objects.length % 2 != 0) {
            throw new IllegalArgumentException(ERROR_OBJECTS_ODD);
        }

        final M map = mapProvider.get();

        for (int i = 0; i < objects.length; i += 2) {
            map.put((K) objects[i], (V) objects[i + 1]);
        }

        return map;
    }

    /**
     * Create a map from {@code objects}.
     * 
     * <pre>
     * Map&lt;String, String&gt; map = MapUtils2.newMap(TreeMap::new, Pair.of("key1", "value1"), Pair.of("key2", "value2"));
     * 
     * // equivalent
     * Map&lt;String, String&gt; map = new TreeMap&lt;&gt;();
     * map.put("key1", "value1");
     * map.put("key2", "value2");
     * </pre>
     * 
     * @param mapProvider
     *            map constructor supplier
     * @param objects
     *            objects pair to put in the new {@link Map}
     * @param <K>
     *            the type of map key
     * @param <V>
     *            the type of map value
     * @param <M>
     *            the type of map
     * @return the new {@link Map}
     * @throws NullPointerException
     *             if {@code mapProvider} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code objects} is {@code null} or empty
     */
    @SafeVarargs
    public static <K, V, M extends Map<K, V>> M newMap(final Supplier<M> mapProvider, Pair<K, V>... objects) {
        Objects.requireNonNull(mapProvider);
        ObjectUtils.requireNonNull(objects, ERROR_OBJECTS_SUPPLIER);

        final M map = mapProvider.get();

        for (Pair<K, V> pair : objects) {
            map.put(pair.getKey(), pair.getValue());
        }

        return map;
    }

    /**
     * Checks if the entries keys are equal
     * 
     * @param entry1
     *            the first entry
     * @param entry2
     *            the second entry
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @return true, if keys are equal
     * @throws NullPointerException
     *             if one entry is {@code null}
     */
    public static <K, V> boolean areKeysEqual(final Entry<K, V> entry1, final Entry<K, V> entry2) {
        return areKeysEqual(entry1, Objects.requireNonNull(entry2, ERROR_ENTRY_NULL).getKey());
    }

    /**
     * Checks if the entry key is equal to the key
     * 
     * @param entry1
     *            the first entry
     * @param key2
     *            the second key
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @return true, if keys are equal
     * @throws NullPointerException
     *             if one entry is {@code null}
     */
    public static <K, V> boolean areKeysEqual(final Entry<K, V> entry1, final K key2) {
        return Objects.equals(Objects.requireNonNull(entry1, ERROR_ENTRY_NULL).getKey(), key2);
    }

    /**
     * Checks if the entry value are equal
     * 
     * @param entry1
     *            the first entry
     * @param entry2
     *            the second entry
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @return true, if values are equal
     * @throws NullPointerException
     *             if one entry is {@code null}
     */
    public static <K, V> boolean areValuesEqual(final Entry<K, V> entry1, final Entry<K, V> entry2) {
        return areValuesEqual(entry1, Objects.requireNonNull(entry2, ERROR_ENTRY_NULL).getValue());
    }

    /**
     * Checks if the entry value is equal to the value
     * 
     * @param entry1
     *            the first entry
     * @param value2
     *            the second value
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @return true, if values are equal
     * @throws NullPointerException
     *             if one entry is {@code null}
     */
    public static <K, V> boolean areValuesEqual(final Entry<K, V> entry1, final V value2) {
        return Objects.equals(Objects.requireNonNull(entry1, ERROR_ENTRY_NULL).getValue(), value2);
    }

    /**
     * Checks if the entries are equal
     * 
     * @param entry1
     *            the first entry
     * @param entry2
     *            the second entry
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @return true, if keys and values are equal
     * @throws NullPointerException
     *             if one entry is {@code null}
     */
    public static <K, V> boolean areEntriesEqual(final Entry<K, V> entry1, final Entry<K, V> entry2) {
        Objects.requireNonNull(entry2, ERROR_ENTRY_NULL);

        return areEntriesEqual(entry1, entry2.getKey(), entry2.getValue());
    }

    /**
     * Checks if the entry key is equal to the value
     * 
     * @param entry1
     *            the first entry
     * @param key2
     *            the second key
     * @param value2
     *            the second value
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @return true, if keys and values are equal
     * @throws NullPointerException
     *             if one entry is {@code null}
     */
    public static <K, V> boolean areEntriesEqual(final Entry<K, V> entry1, final K key2, final V value2) {
        Objects.requireNonNull(entry1, ERROR_ENTRY_NULL);

        return Objects.equals(entry1.getKey(), key2) && Objects.equals(entry1.getValue(), value2);
    }
}
