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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.collections4.Transformer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Check collections utils
 *
 * @since 2 févr. 2016
 * @author Gilles Landel
 *
 */
public class CollectionUtils2Test extends AbstractTest {

	private static final Transformer<Point, String> TRANSFORMER = new Transformer<Point, String>() {
		@Override
		public String transform(Point input) {
			if (input != null) {
				return ((int) input.getX()) + ", " + ((int) input.getY());
			}
			return null;
		}
	};

	private static final Function<Point, String> FUNCTION = p -> TRANSFORMER.transform(p);

	/**
	 * Test constructor for {@link CollectionUtils2} .
	 */
	@Test
	public void testConstructors() {
		assertTrue(checkPrivateConstructor(CollectionUtils2.class));
	}

	/**
	 * Test method for
	 * {@link CollectionUtils2#transformIntoList(java.lang.Iterable, Transformer)}
	 * {@link CollectionUtils2#transformIntoList(java.lang.Iterable, Function)} .
	 */
	@Test
	public void testTransformIntoListIterableOfITransformerOfIO() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			List<String> strPoints = CollectionUtils2.transformIntoList(points, TRANSFORMER);

			assertThat(strPoints, Matchers.contains("1, 2", "2, 0", null));

			strPoints = CollectionUtils2.transformIntoList(points, FUNCTION);

			assertThat(strPoints, Matchers.contains("1, 2", "2, 0", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for
	 * {@link CollectionUtils2#transformIntoSet(java.lang.Iterable, Transformer)}
	 * {@link CollectionUtils2#transformIntoSet(java.lang.Iterable, Function)} .
	 */
	@Test
	public void testTransformIntoSetIterableOfITransformerOfIO() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			Set<String> strPoints = CollectionUtils2.transformIntoSet(points, TRANSFORMER);

			assertThat(strPoints, Matchers.containsInAnyOrder("1, 2", "2, 0", null));

			strPoints = CollectionUtils2.transformIntoSet(points, FUNCTION);

			assertThat(strPoints, Matchers.containsInAnyOrder("1, 2", "2, 0", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for
	 * {@link CollectionUtils2#transformIntoList(java.lang.Iterable)}.
	 */
	@Test
	public void testTransformIntoListIterableOfI() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			List<String> strPoints = CollectionUtils2.transformIntoList(points);

			assertThat(strPoints, Matchers.contains("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for
	 * {@link CollectionUtils2#transformIntoList(java.lang.Iterable, boolean)}.
	 */
	@Test
	public void testTransformIntoListIterableOfIB() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			List<String> strPoints = CollectionUtils2.transformIntoList(points, true);

			assertThat(strPoints, Matchers.contains("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", "null"));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for
	 * {@link CollectionUtils2#transformIntoSet(java.lang.Iterable)}.
	 */
	@Test
	public void testTransformIntoSetIterableOfI() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			Set<String> strPoints = CollectionUtils2.transformIntoSet(points);

			assertThat(strPoints,
					Matchers.containsInAnyOrder("java.awt.Point[x=2,y=0]", "java.awt.Point[x=1,y=2]", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for
	 * {@link CollectionUtils2#transformIntoSet(java.lang.Iterable, boolean)}.
	 */
	@Test
	public void testTransformIntoSetIterableOfIB() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			Set<String> strPoints = CollectionUtils2.transformIntoSet(points, true);

			assertThat(strPoints,
					Matchers.containsInAnyOrder("java.awt.Point[x=2,y=0]", "java.awt.Point[x=1,y=2]", "null"));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#toArray(java.util.Collection)}.
	 */
	@Test
	public void testToArray() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			Point[] pointsArray = CollectionUtils2.toArray(points);

			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray, Matchers.arrayContaining(points.get(0), points.get(1), null));

			pointsArray = CollectionUtils2.toArray(points, Point.class);
			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray, Matchers.arrayContaining(points.get(0), points.get(1), null));

			assertNull(CollectionUtils2.toArray(null));

			points.clear();

			assertNull(CollectionUtils2.toArray(points));

			List<Object> objects = new ArrayList<>();
			objects.add(12);
			objects.add("text");
			assertThat(CollectionUtils2.toArray(objects), Matchers.arrayContaining(objects.get(0), objects.get(1)));

			String[] emptyArray = CollectionUtils2.toArray(Collections.<String>emptyList());
			assertNull(emptyArray);

			emptyArray = CollectionUtils2.toArray(Collections.<String>emptyList(), String.class);
			assertNotNull(emptyArray);
			assertTrue(ArrayUtils.isEmpty(emptyArray));
			assertTrue(emptyArray.getClass().isArray());

		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#transformIntoArray(I[], Transformer)}
	 * . {@link CollectionUtils2#transformIntoArray(I[], Function)} .
	 */
	@Test
	public void testTransformIntoArrayIArrayTransformerOfIO() {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(1, 2);
			points[1] = new Point(2, 0);

			String[] pointsArray = CollectionUtils2.transformIntoArray(points, TRANSFORMER);

			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray, Matchers.arrayContaining("1, 2", "2, 0", null));

			pointsArray = CollectionUtils2.transformIntoArray(points, FUNCTION);

			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray, Matchers.arrayContaining("1, 2", "2, 0", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#transformIntoArray(I[])}.
	 */
	@Test
	public void testTransformIntoArrayIArray() {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(1, 2);
			points[1] = new Point(2, 0);

			String[] pointsArray = CollectionUtils2.transformIntoArray(points);

			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray,
					Matchers.arrayContaining("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#transformIntoArray(I[], boolean)} .
	 */
	@Test
	public void testTransformIntoArrayIArrayB() {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(1, 2);
			points[1] = new Point(2, 0);

			String[] pointsArray = CollectionUtils2.transformIntoArray(points, true);

			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray,
					Matchers.arrayContaining("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", "null"));

			points = new Point[0];
			pointsArray = CollectionUtils2.transformIntoArray(points, true);
			assertNull(pointsArray);
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for
	 * {@link CollectionUtils2#transformIntoArray(java.lang.Iterable, Transformer)}
	 * {@link CollectionUtils2#transformIntoArray(java.lang.Iterable, Function)} .
	 */
	@Test
	public void testTransformIntoArrayIterableOfITransformerOfIO() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			String[] pointsArray = CollectionUtils2.transformIntoArray(points, TRANSFORMER);

			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray, Matchers.arrayContaining("1, 2", "2, 0", null));

			pointsArray = CollectionUtils2.transformIntoArray(points, FUNCTION);

			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray, Matchers.arrayContaining("1, 2", "2, 0", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for
	 * {@link CollectionUtils2#transformIntoArray(java.lang.Iterable)}.
	 */
	@Test
	public void testTransformIntoArrayIterableOfI() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			String[] pointsArray = CollectionUtils2.transformIntoArray(points);

			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray,
					Matchers.arrayContaining("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for
	 * {@link CollectionUtils2#transformIntoArray(java.lang.Iterable, boolean)}.
	 */
	@Test
	public void testTransformIntoArrayIterableOfIB() {
		try {
			List<Point> points = new ArrayList<>();
			points.add(new Point(1, 2));
			points.add(new Point(2, 0));
			points.add(null);

			String[] pointsArray = CollectionUtils2.transformIntoArray(points, true);

			assertNotNull(pointsArray);
			assertTrue(pointsArray.length > 0);
			assertThat(pointsArray,
					Matchers.arrayContaining("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", "null"));

			points.clear();
			pointsArray = CollectionUtils2.transformIntoArray(points, true);
			assertNull(pointsArray);
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#transformIntoList(I[], Transformer)}
	 * . {@link CollectionUtils2#transformIntoList(I[], Function)} .
	 */
	@Test
	public void testTransformIntoListIArrayTransformerOfIO() {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(1, 2);
			points[1] = new Point(2, 0);

			List<String> pointsList = CollectionUtils2.transformIntoList(points, TRANSFORMER);

			assertNotNull(pointsList);
			assertTrue(pointsList.size() > 0);
			assertThat(pointsList, Matchers.contains("1, 2", "2, 0", null));

			pointsList = CollectionUtils2.transformIntoList(points, FUNCTION);

			assertNotNull(pointsList);
			assertTrue(pointsList.size() > 0);
			assertThat(pointsList, Matchers.contains("1, 2", "2, 0", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#transformIntoList(I[])}.
	 */
	@Test
	public void testTransformIntoListIArray() {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(1, 2);
			points[1] = new Point(2, 0);

			List<String> pointsList = CollectionUtils2.transformIntoList(points);

			assertNotNull(pointsList);
			assertTrue(pointsList.size() > 0);
			assertThat(pointsList, Matchers.contains("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#transformIntoList(I[], boolean)}.
	 */
	@Test
	public void testTransformIntoListIArrayB() {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(1, 2);
			points[1] = new Point(2, 0);

			List<String> pointsList = CollectionUtils2.transformIntoList(points, true);

			assertNotNull(pointsList);
			assertTrue(pointsList.size() > 0);
			assertThat(pointsList, Matchers.contains("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", "null"));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#transformIntoSet(I[], Transformer)}
	 * {@link CollectionUtils2#transformIntoSet(I[], Function)} .
	 */
	@Test
	public void testTransformIntoSetIArrayTransformerOfIO() {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(1, 2);
			points[1] = new Point(2, 0);

			Set<String> pointsList = CollectionUtils2.transformIntoSet(points, TRANSFORMER);

			assertNotNull(pointsList);
			assertTrue(pointsList.size() > 0);
			assertThat(pointsList, Matchers.containsInAnyOrder("1, 2", "2, 0", null));

			pointsList = CollectionUtils2.transformIntoSet(points, FUNCTION);

			assertNotNull(pointsList);
			assertTrue(pointsList.size() > 0);
			assertThat(pointsList, Matchers.containsInAnyOrder("1, 2", "2, 0", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#transformIntoSet(I[])}.
	 */
	@Test
	public void testTransformIntoSetIArray() {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(1, 2);
			points[1] = new Point(2, 0);

			Set<String> pointsList = CollectionUtils2.transformIntoSet(points);

			assertNotNull(pointsList);
			assertTrue(pointsList.size() > 0);
			assertThat(pointsList,
					Matchers.containsInAnyOrder("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", null));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#transformIntoSet(I[], boolean)}.
	 */
	@Test
	public void testTransformIntoSetIArrayB() {
		try {
			Point[] points = new Point[3];
			points[0] = new Point(1, 2);
			points[1] = new Point(2, 0);

			Set<String> pointsList = CollectionUtils2.transformIntoSet(points, true);

			assertNotNull(pointsList);
			assertTrue(pointsList.size() > 0);
			assertThat(pointsList,
					Matchers.containsInAnyOrder("java.awt.Point[x=1,y=2]", "java.awt.Point[x=2,y=0]", "null"));
		} catch (IllegalArgumentException e) {
			fail("The test isn't correct");
		}
	}

	/**
	 * Test method for {@link CollectionUtils2#getListClass}
	 * {@link CollectionUtils2#getSetClass} {@link CollectionUtils2#getQueueClass}
	 * {@link CollectionUtils2#getMapClass}.
	 */
	@Test
	public void testGetTypedClass() {
		Class<List<String>> clazz = CollectionUtils2.getListClass(String.class);
		assertNotNull(clazz);
		assertTrue(ArrayList.class.isAssignableFrom(clazz));

		Class<List<Object>> clazz2 = CollectionUtils2.getListClass(null);
		assertNotNull(clazz2);
		assertTrue(ArrayList.class.isAssignableFrom(clazz));

		Class<Set<String>> clazz3 = CollectionUtils2.getSetClass(String.class);
		assertNotNull(clazz3);
		assertTrue(HashSet.class.isAssignableFrom(clazz3));

		Class<Set<Object>> clazz4 = CollectionUtils2.getSetClass(null);
		assertNotNull(clazz4);
		assertTrue(HashSet.class.isAssignableFrom(clazz4));

		Class<Queue<String>> clazz5 = CollectionUtils2.getQueueClass(String.class);
		assertNotNull(clazz5);
		assertTrue(LinkedList.class.isAssignableFrom(clazz5));

		Class<Queue<Object>> clazz6 = CollectionUtils2.getQueueClass(null);
		assertNotNull(clazz6);
		assertTrue(LinkedList.class.isAssignableFrom(clazz6));

		Class<Map<String, Integer>> clazz7 = CollectionUtils2.getMapClass(String.class, Integer.class);
		assertNotNull(clazz7);
		assertTrue(HashMap.class.isAssignableFrom(clazz7));

		Class<Map<Object, Integer>> clazz8 = CollectionUtils2.getMapClass(null, Integer.class);
		assertNotNull(clazz8);
		assertTrue(HashMap.class.isAssignableFrom(clazz8));

		Class<Map<String, Object>> clazz9 = CollectionUtils2.getMapClass(String.class, null);
		assertNotNull(clazz9);
		assertTrue(HashMap.class.isAssignableFrom(clazz9));
	}

	/**
	 * Test method for {@link CollectionUtils2#getOrPut}.
	 */
	@Test
	public void testGetOrPut() {
		Map<String, String> map = new HashMap<>();

		assertFalse(map.containsKey("key"));

		String value = CollectionUtils2.getOrPut(map, "key", "value");
		assertTrue(map.containsKey("key"));
		assertEquals("value", value);

		value = CollectionUtils2.getOrPut(map, "key", "value2");
		assertEquals("value", value);
	}

	/**
	 * Test method for {@link CollectionUtils2#getClasses}.
	 */
	@Test
	public void testGetClasses() {
		Set<Object> set = new HashSet<>();

		set.add("text");
		set.add(2);
		set.add(6);
		set.add(true);

		Set<Class<Object>> classes = CollectionUtils2.getClasses(set);
		List<Class<?>> expectedClasses = Arrays.asList(Integer.class, Boolean.class, String.class);

		for (Class<?> clazz : expectedClasses) {
			assertTrue(classes.contains(clazz));
		}

		Set<Number> set2 = new HashSet<>();

		set2.add(56.55f);
		set2.add(2);
		set2.add(6.0d);
		set2.add((byte) 12);

		Set<Class<Number>> classes2 = CollectionUtils2.getClasses(set2);
		expectedClasses = Arrays.asList(Integer.class, Float.class, Double.class, Byte.class);

		for (Class<?> clazz : expectedClasses) {
			assertTrue(classes2.contains(clazz));
		}

		set = new HashSet<>();

		set.add(56.55f);
		set.add(null);

		classes = CollectionUtils2.getClasses(set);
		expectedClasses = Arrays.asList(Float.class);

		for (Class<?> clazz : expectedClasses) {
			assertTrue(classes.contains(clazz));
		}

		assertEquals(0, CollectionUtils2.getClasses(new HashSet<>()).size());
		assertEquals(0, CollectionUtils2.getClasses(null).size());
	}

	/**
	 * Test method for {@link CollectionUtils2#containsClasses}.
	 */
	@Test
	public void testContainsClasses() {
		Set<Object> set = new HashSet<>();

		set.add("text");
		set.add(2);
		set.add(6);
		set.add(true);

		assertTrue(CollectionUtils2.containsClasses(set, Integer.class, Boolean.class, String.class));

		Set<Number> set2 = new HashSet<>();

		set2.add(56.55f);
		set2.add(2);
		set2.add(6.0d);
		set2.add((byte) 12);

		assertTrue(CollectionUtils2.containsClasses(set2, Integer.class, Float.class, Double.class, Byte.class, null));
		assertFalse(CollectionUtils2.containsClasses(set2, Integer.class, Short.class));
		assertTrue(CollectionUtils2.containsClasses(set2, (Class<?>) null));
		assertFalse(CollectionUtils2.containsClasses(set2, (Class<?>[]) null));
		assertFalse(CollectionUtils2.containsClasses(null, Integer.class, Float.class, Double.class, Byte.class, null));
	}
}
