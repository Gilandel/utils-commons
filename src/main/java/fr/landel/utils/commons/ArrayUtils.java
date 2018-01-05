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

import java.util.Objects;

/**
 * Operations on arrays, primitive arrays (like {@code int[]}) and primitive
 * wrapper arrays (like {@code Integer[]}).
 *
 * <p>
 * This class tries to handle {@code null} input gracefully. An exception will
 * not be thrown for a {@code null} array input. However, an Object array that
 * contains a {@code null} element may throw an exception. Each method documents
 * its behaviour.
 * </p>
 *
 * <p>
 * #ThreadSafe#
 * </p>
 *
 * @since Aug 2, 2016
 * @author Gilles
 *
 */
public final class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {

    /**
     * Hidden constructor
     */
    private ArrayUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Search if {@code arrayToSearch} contains all {@code arraySearched}
     * entries.
     * 
     * @param arrayToSearch
     *            where to search array (required, not null)
     * @param arraySearched
     *            what to search array (required, not null)
     * @param <T>
     *            The type of element in array to search
     * @param <U>
     *            The type of element in searched array
     * @return true, if all elements were found
     */
    public static <T, U> boolean containsAll(final T[] arrayToSearch, final U[] arraySearched) {
        return containsAll(arrayToSearch, arraySearched, true);
    }

    /**
     * Search if {@code arrayToSearch} contains all {@code arraySearched}
     * entries.
     * 
     * @param arrayToSearch
     *            where to search array (required, not null)
     * @param arraySearched
     *            what to search array (required, not null)
     * @param checkType
     *            check if the type is identical from each array
     * @param <T>
     *            The type of element in array to search
     * @param <U>
     *            The type of element in searched array
     * @return true, if all elements were found
     */
    public static <T, U> boolean containsAll(final T[] arrayToSearch, final U[] arraySearched, final boolean checkType) {
        Objects.requireNonNull(arrayToSearch, "Array to search cannot be null");
        Objects.requireNonNull(arraySearched, "Searched array cannot be null");

        return has(arrayToSearch, arraySearched, true, checkType);
    }

    /**
     * Search if {@code arrayToSearch} contains any {@code arraySearched}
     * entries.
     * 
     * @param arrayToSearch
     *            where to search array (required, not null)
     * @param arraySearched
     *            what to search array (required, not null)
     * @param <T>
     *            The type of element in array to search
     * @param <U>
     *            The type of element in searched array
     * @return true, if at least one element was found
     */
    public static <T, U> boolean containsAny(final T[] arrayToSearch, final U[] arraySearched) {
        return containsAny(arrayToSearch, arraySearched, true);
    }

    /**
     * Search if {@code arrayToSearch} contains any {@code arraySearched}
     * entries.
     * 
     * @param arrayToSearch
     *            where to search array (required, not null)
     * @param arraySearched
     *            what to search array (required, not null)
     * @param checkType
     *            check if the type is identical from each array
     * @param <T>
     *            The type of element in array to search
     * @param <U>
     *            The type of element in searched array
     * @return true, if at least one element was found
     */
    public static <T, U> boolean containsAny(final T[] arrayToSearch, final U[] arraySearched, final boolean checkType) {
        Objects.requireNonNull(arrayToSearch, "Array to search cannot be null");
        Objects.requireNonNull(arraySearched, "Searched array cannot be null");

        return has(arrayToSearch, arraySearched, false, checkType);
    }

    /**
     * Replaces the searched value by the replacement one in the specified
     * array.
     * 
     * @param array
     *            the array where to replace (cannot be {@code null})
     * @param searchedValue
     *            the searched value
     * @param replacementValue
     *            the replacement value
     * @param <T>
     *            the array components type
     */
    public static <T> void replace(T[] array, final T searchedValue, final T replacementValue) {
        Objects.requireNonNull(array, "Array to search cannot be null");

        for (int i = 0; i < array.length; ++i) {
            if (Objects.equals(array[i], searchedValue)) {
                array[i] = replacementValue;
            }
        }
    }

    /**
     * Replaces the searched values by the replacements values in the specified
     * array.
     * 
     * @param array
     *            the array where to replace (cannot be {@code null})
     * @param searchedValues
     *            the searched values (cannot be {@code null})
     * @param replacementValues
     *            the replacement values (cannot be {@code null})
     * @param <T>
     *            the array components type
     * @throws NullPointerException
     *             if any parameter is {@code null}
     * @throws IllegalArgumentException
     *             if {@code searchedValues} and {@code replacementValues}
     *             haven't the same length
     */
    public static <T> void replace(T[] array, final T[] searchedValues, final T[] replacementValues) {
        Objects.requireNonNull(array, "Array to search cannot be null");
        Objects.requireNonNull(searchedValues, "Array of searched values cannot be null");
        Objects.requireNonNull(replacementValues, "Array of replacement values cannot be null");

        if (searchedValues.length != replacementValues.length) {
            throw new IllegalArgumentException("Searched values and replacement values arrays must have the same length");
        }

        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < searchedValues.length; ++j) {
                if (Objects.equals(array[i], searchedValues[j])) {
                    array[i] = replacementValues[j];
                }
            }
        }
    }

    /**
     * Concatenates all the elements of the given arrays into a new array.
     * <p>
     * The new array contains all of the element of {@code array1} followed by
     * all of the elements {@code array2}. When an array is returned, it is
     * always a new array.
     * </p>
     * 
     * @param array1
     *            the first array
     * @param array2
     *            the second array
     * @param <T>
     *            the array components type
     * @return the new array
     */
    @SafeVarargs
    public static <T> T[] concat(final T[] array1, final T... array2) {
        return addAll(array1, array2);
    }

    /**
     * Concatenates two arrays in the output array
     * <p>
     * The output array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}.
     * </p>
     * 
     * @param outputArray
     *            the output array (cannot be {@code null} and length must be
     *            greater than array1 plus array2 length)
     * @param array1
     *            the first input array
     * @param array2
     *            the second input array
     * @param <T>
     *            the array components type
     * @return the {@code outputArray} instance
     * @throws NullPointerException
     *             if {@code outputArray} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array1} plus {@code array2} length is greater than
     *             {@code outputArray} length
     */
    @SafeVarargs
    public static <T> T[] concat(final T[] outputArray, final T[] array1, final T... array2) {
        Objects.requireNonNull(outputArray, "The output array cannot be null");

        final int array1Length = getLength(array1);
        final int array2Length = getLength(array2);

        if (outputArray.length < array1Length + array2Length) {
            throw new IllegalArgumentException("The output array cannot be smaller than array1 plus array2 length");
        }

        if (array1 != null) {
            System.arraycopy(array1, 0, outputArray, 0, array1Length);
        }

        if (array2 != null) {
            System.arraycopy(array2, 0, outputArray, array1Length, array2Length);
        }

        return outputArray;
    }

    /**
     * Count the number of {@code arraySearched} in {@code arrayToSearch}.
     * 
     * @param arrayToSearch
     *            where to search array (required, not null)
     * @param arraySearched
     *            what to search array (required, not null)
     * @param <T>
     *            The type of element in array to search
     * @param <U>
     *            The type of element in searched array
     * @return number, the count of elements found
     */
    public static <T, U> int count(final T[] arrayToSearch, final U[] arraySearched) {
        return count(arrayToSearch, arraySearched, true);
    }

    /**
     * Count the number of {@code arraySearched} in {@code arrayToSearch}.
     * 
     * @param arrayToSearch
     *            where to search array (required, not null)
     * @param arraySearched
     *            what to search array (required, not null)
     * @param checkType
     *            check if the type is identical from each array
     * @param <T>
     *            The type of element in array to search
     * @param <U>
     *            The type of element in searched array
     * @return number, the count of elements found
     */
    public static <T, U> int count(final T[] arrayToSearch, final U[] arraySearched, final boolean checkType) {
        Objects.requireNonNull(arrayToSearch, "Array to search cannot be null");
        Objects.requireNonNull(arraySearched, "Searched array cannot be null");

        return count(arrayToSearch, arraySearched, checkType, false);
    }

    /**
     * Count the number of object in {@code arrayToSearch}.
     * 
     * @param arrayToSearch
     *            where to search array (required, not null)
     * @param object
     *            what to search
     * @param <T>
     *            The type of element in array to search
     * @param <U>
     *            The type of element to search
     * @return the number of iterations
     */
    public static <T, U> int count(final T[] arrayToSearch, final U object) {
        return count(arrayToSearch, object, true);
    }

    /**
     * Count the number of object in {@code arrayToSearch}.
     * 
     * @param arrayToSearch
     *            where to search array (required, not null)
     * @param object
     *            what to search
     * @param checkType
     *            check if the type is identical from each array
     * @param <T>
     *            The type of element in array to search
     * @param <U>
     *            The type of element to search
     * @return the number of iterations
     */
    @SuppressWarnings("unlikely-arg-type")
    public static <T, U> int count(final T[] arrayToSearch, final U object, final boolean checkType) {
        Objects.requireNonNull(arrayToSearch, "Array to search cannot be null");

        int found = 0;
        if (object == null) {
            for (T objectArray : arrayToSearch) {
                if (objectArray == null) {
                    ++found;
                }
            }
        } else if (!checkType || arrayToSearch.getClass().getComponentType().isInstance(object)) {
            for (T objectArray : arrayToSearch) {
                if (object.equals(objectArray)) {
                    ++found;
                }
            }
        }
        return found;
    }

    private static <T, U> int count(final T[] arrayToSearch, final U[] arraySearched, final boolean checkType, final boolean stopOnFirst) {
        if (checkType && !arrayToSearch.getClass().getComponentType().isAssignableFrom(arraySearched.getClass().getComponentType())) {
            return 0;
        }

        if (stopOnFirst) {
            for (U objectArray : arraySearched) {
                if (has(arrayToSearch, objectArray)) {
                    return 1;
                }
            }
            return 0;
        } else {
            int found = 0;
            for (U objectArray : arraySearched) {
                if (has(arrayToSearch, objectArray)) {
                    ++found;
                }
            }
            return found;
        }
    }

    @SuppressWarnings("unlikely-arg-type")
    private static <T, U> boolean has(final T[] array, final U object) {
        if (object == null) {
            for (T objectArray : array) {
                if (objectArray == null) {
                    return true;
                }
            }
        } else {
            for (T objectArray : array) {
                if (object.equals(objectArray)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static <T, U> boolean has(final T[] arrayToSearch, final U[] arraySearched, final boolean all, final boolean checkType) {
        if (checkType && !arrayToSearch.getClass().getComponentType().isAssignableFrom(arraySearched.getClass().getComponentType())) {
            return false;
        }

        if (all) {
            return count(arrayToSearch, arraySearched, false, false) == arraySearched.length;
        } else {
            return count(arrayToSearch, arraySearched, false, true) > 0;
        }
    }
}
