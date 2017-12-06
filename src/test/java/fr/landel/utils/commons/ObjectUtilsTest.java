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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * Check {@link ObjectUtils}
 *
 * @since Aug 15, 2016
 * @author Gilles
 *
 */
public class ObjectUtilsTest extends AbstractTest {

    private static final Predicate<Boolean> PREDICATE = Objects::nonNull;

    /**
     * Test method for {@link ObjectUtils#ObjectUtils()}.
     */
    @Test
    public void testObjectUtils() {
        assertNotNull(new ObjectUtils());
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIfNull(java.lang.Object, java.util.function.Supplier)}.
     */
    @Test
    public void testDefaultIfNullTOfT() {
        assertTrue(ObjectUtils.defaultIfNull(true, () -> false));
        assertFalse(ObjectUtils.defaultIfNull(null, () -> false));
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIfNull(Object, Object, java.util.function.Function)}.
     */
    @Test
    public void testDefaultIfNullTOfT2() {
        assertTrue(ObjectUtils.defaultIfNull(false, false, b -> !b));
        assertFalse(ObjectUtils.defaultIfNull((Boolean) null, false, b -> !b));
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIfSupplyNull(java.util.function.Supplier, Object)}.
     */
    @Test(expected = NullPointerException.class)
    public void testDefaultIfNullSupplierNull() {
        ObjectUtils.defaultIfNull(null, true, null);
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIfSupplyNull(java.util.function.Supplier, java.util.function.Supplier)}.
     */
    @Test(expected = NullPointerException.class)
    public void testDefaultIfNullDefaultNull1() {
        ObjectUtils.defaultIfNull(null, null);
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIfSupplyNull(java.lang.Object, java.util.function.Supplier)}.
     */
    @Test(expected = NullPointerException.class)
    public void testDefaultIfNullTSupplierOfTNull() {
        ObjectUtils.defaultIfNull(true, null);
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIf(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testDefaultIfTOfT() {
        assertTrue(ObjectUtils.defaultIf(PREDICATE, true, false));
        assertFalse(ObjectUtils.defaultIf(PREDICATE, null, false));

        assertException(() -> ObjectUtils.defaultIf(null, true, false), NullPointerException.class,
                "The parameter 'predicate' cannot be null");
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIf(java.lang.Object, java.util.function.Supplier)}.
     */
    @Test
    public void testDefaultIfTOfSupplyT() {
        final Supplier<Boolean> supFalse = () -> false;

        assertTrue(ObjectUtils.defaultIf(PREDICATE, true, supFalse));
        assertFalse(ObjectUtils.defaultIf(PREDICATE, null, supFalse));
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIf(Object, Object, java.util.function.Function)}.
     */
    @Test
    public void testDefaultIfTOfT2() {
        assertTrue(ObjectUtils.defaultIf(PREDICATE, false, false, b -> !b));
        assertFalse(ObjectUtils.defaultIf(PREDICATE, (Boolean) null, false, b -> !b));
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIf(java.util.function.Supplier, Object)}.
     */
    @Test(expected = NullPointerException.class)
    public void testDefaultIfSupplierNull() {
        ObjectUtils.defaultIf(PREDICATE, null, true, null);
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIf(java.util.function.Supplier, java.util.function.Supplier)}.
     */
    @Test(expected = NullPointerException.class)
    public void testDefaultIfDefaultNull1() {
        ObjectUtils.defaultIf(PREDICATE, null, (Supplier<Boolean>) null);
    }

    /**
     * Test method for
     * {@link ObjectUtils#defaultIf(java.lang.Object, java.util.function.Supplier)}.
     */
    @Test(expected = NullPointerException.class)
    public void testDefaultIfTSupplierOfTNull() {
        ObjectUtils.defaultIf(PREDICATE, true, (Supplier<Boolean>) null);
    }

    /**
     * Test method for {@link ObjectUtils#allNull(java.lang.Object...)}.
     */
    @Test
    public void testAllNull() {
        assertFalse(ObjectUtils.allNull(15));
        assertFalse(ObjectUtils.allNull(15, null));
        assertTrue(ObjectUtils.allNull(null, null));
        assertTrue(ObjectUtils.allNull((Object) null));
    }

    /**
     * Test method for {@link ObjectUtils#allNull(java.lang.Object...)}.
     */
    @Test(expected = NullPointerException.class)
    public void testAllNullKO() {
        ObjectUtils.allNull((Object[]) null);
    }

    /**
     * Test method for {@link ObjectUtils#anyNull(java.lang.Object...)}.
     */
    @Test
    public void testAnyNull() {
        assertFalse(ObjectUtils.anyNull(15));
        assertTrue(ObjectUtils.anyNull(15, null));
        assertTrue(ObjectUtils.anyNull(null, null));
        assertTrue(ObjectUtils.anyNull((Object) null));
    }

    /**
     * Test method for {@link ObjectUtils#anyNull(java.lang.Object...)}.
     */
    @Test(expected = NullPointerException.class)
    public void testAnyNullKO() {
        ObjectUtils.anyNull((Object[]) null);
    }

    /**
     * Test method for {@link ObjectUtils#allNull(java.lang.Object...)}.
     */
    @Test
    public void testAllNotNull() {
        assertTrue(ObjectUtils.allNotNull(15));
        assertFalse(ObjectUtils.allNotNull(15, null));
        assertFalse(ObjectUtils.allNotNull(null, null));
        assertFalse(ObjectUtils.allNotNull((Object) null));
    }

    /**
     * Test method for {@link ObjectUtils#allNotNull(java.lang.Object...)}.
     */
    @Test(expected = NullPointerException.class)
    public void testAllNotNullKO() {
        ObjectUtils.allNotNull((Object[]) null);
    }

    /**
     * Test method for {@link ObjectUtils#anyNotNull(java.lang.Object...)}.
     */
    @Test
    public void testAnyNotNull() {
        assertTrue(ObjectUtils.anyNotNull(15));
        assertTrue(ObjectUtils.anyNotNull(15, null));
        assertFalse(ObjectUtils.anyNotNull(null, null));
        assertFalse(ObjectUtils.anyNotNull((Object) null));
    }

    /**
     * Test method for {@link ObjectUtils#anyNull(java.lang.Object...)}.
     */
    @Test(expected = NullPointerException.class)
    public void testAnyNotNullKO() {
        ObjectUtils.anyNotNull((Object[]) null);
    }

    /**
     * Test method for
     * {@link ObjectUtils#requireNonNull(Object, java.util.function.Supplier)}.
     */
    @Test
    public void testRequireNonNullSupplier() {
        assertEquals("test", ObjectUtils.requireNonNull("test", IllegalArgumentException::new));

        assertException(() -> {
            ObjectUtils.requireNonNull(null, IllegalArgumentException::new);
        }, IllegalArgumentException.class);

        assertException(() -> {
            ObjectUtils.requireNonNull("test", null);
        }, NullPointerException.class);
    }

    /**
     * Test method for {@link ObjectUtils#requireNonNulls(Object...)}.
     */
    @Test
    public void testRequireNonNulls() {
        try {
            ObjectUtils.requireNonNulls("test", 12);
        } catch (NullPointerException e) {
            fail(e.getMessage());
        }

        assertException(() -> {
            ObjectUtils.requireNonNulls(null, "test");
        }, NullPointerException.class);

        assertException(() -> {
            ObjectUtils.requireNonNulls("test", null);
        }, NullPointerException.class);
    }

    /**
     * Test method for
     * {@link ObjectUtils#ifNotNull(Object, java.util.function.Function)}.
     */
    @Test
    public void testIfNotNull() {
        assertEquals("true", ObjectUtils.ifNotNull(true, b -> b.toString()));
        assertNull(ObjectUtils.ifNotNull(null, b -> b.toString()));
    }

    /**
     * Test method for
     * {@link ObjectUtils#ifNotNullOptional(Object, java.util.function.Function)}.
     */
    @Test
    public void testIfNotNullOptional() {
        assertEquals("true", ObjectUtils.ifNotNullOptional(true, b -> b.toString()).get());
        assertFalse(ObjectUtils.ifNotNullOptional(null, b -> b.toString()).isPresent());
    }

    /**
     * Test method for
     * {@link ObjectUtils#ifPredicate(Predicate, Object, java.util.function.Function)}.
     */
    @Test
    public void testIfPredicate() {
        assertEquals("true", ObjectUtils.ifPredicate(PREDICATE, true, b -> b.toString()));
        assertNull(ObjectUtils.ifPredicate(PREDICATE, null, b -> b.toString()));
    }

    /**
     * Test method for
     * {@link ObjectUtils#ifPredicateOptional(Predicate, Object, java.util.function.Function)}.
     */
    @Test
    public void testIfPredicateOptional() {
        assertEquals("true", ObjectUtils.ifPredicateOptional(PREDICATE, true, b -> b.toString()).get());
        assertFalse(ObjectUtils.ifPredicateOptional(PREDICATE, null, b -> b.toString()).isPresent());
    }

    /**
     * Test method for
     * {@link ObjectUtils#toPrimitive(Boolean, boolean)}.
     */
    @Test
    public void testToPrimitiveBoolean() {
    	assertTrue(ObjectUtils.toPrimitive(Boolean.TRUE, true));
    	assertFalse(ObjectUtils.toPrimitive(Boolean.FALSE, true));
    	assertTrue(ObjectUtils.toPrimitive(Boolean.TRUE, false));
    	assertFalse(ObjectUtils.toPrimitive(Boolean.FALSE, false));
    	assertTrue(ObjectUtils.toPrimitive(null, true));
    	assertFalse(ObjectUtils.toPrimitive(null, false));
    }
    
    /**
     * Test method for
     * {@link ObjectUtils#toPrimitive(Byte, byte)}.
     */
    @Test
    public void testToPrimitiveByte() {
    	Byte b0 = Byte.valueOf((byte) 0);
    	Byte b1 = Byte.valueOf((byte) 1);
    	assertEquals((byte) 0, ObjectUtils.toPrimitive(b0, (byte) 1));
    	assertEquals((byte) 0, ObjectUtils.toPrimitive(b0, (byte) 0));
    	assertEquals((byte) 1, ObjectUtils.toPrimitive(b1, (byte) 1));
    	assertEquals((byte) 1, ObjectUtils.toPrimitive(b1, (byte) 0));
    	assertEquals((byte) 1, ObjectUtils.toPrimitive((Byte) null, (byte) 1));
    	assertEquals((byte) 0, ObjectUtils.toPrimitive((Byte) null, (byte) 0));
    }
    
    /**
     * Test method for
     * {@link ObjectUtils#toPrimitive(Character, char)}.
     */
    @Test
    public void testToPrimitiveCharacter() {
    	Character c0 = Character.valueOf((char) 0);
    	Character c1 = Character.valueOf((char) 1);
    	assertEquals((char) 0, ObjectUtils.toPrimitive(c0, (char) 1));
    	assertEquals((char) 0, ObjectUtils.toPrimitive(c0, (char) 0));
    	assertEquals((char) 1, ObjectUtils.toPrimitive(c1, (char) 1));
    	assertEquals((char) 1, ObjectUtils.toPrimitive(c1, (char) 0));
    	assertEquals((char) 1, ObjectUtils.toPrimitive((Character) null, (char) 1));
    	assertEquals((char) 0, ObjectUtils.toPrimitive((Character) null, (char) 0));
    }
    
    /**
     * Test method for
     * {@link ObjectUtils#toPrimitive(Short, short)}.
     */
    @Test
    public void testToPrimitiveShort() {
    	Short s0 = Short.valueOf("0");
    	Short s1 = Short.valueOf("1");
    	assertEquals((short) 0, ObjectUtils.toPrimitive(s0, (short) 1));
    	assertEquals((short) 0, ObjectUtils.toPrimitive(s0, (short) 0));
    	assertEquals((short) 1, ObjectUtils.toPrimitive(s1, (short) 1));
    	assertEquals((short) 1, ObjectUtils.toPrimitive(s1, (short) 0));
    	assertEquals((short) 1, ObjectUtils.toPrimitive((Short) null, (short) 1));
    	assertEquals((short) 0, ObjectUtils.toPrimitive((Short) null, (short) 0));
    }
    
    /**
     * Test method for
     * {@link ObjectUtils#toPrimitive(Integer, int)}.
     */
    @Test
    public void testToPrimitiveInteger() {
    	Integer i0 = Integer.valueOf("0");
    	Integer i1 = Integer.valueOf("1");
    	assertEquals((int) 0, ObjectUtils.toPrimitive(i0, 1));
    	assertEquals((int) 0, ObjectUtils.toPrimitive(i0, 0));
    	assertEquals((int) 1, ObjectUtils.toPrimitive(i1, 1));
    	assertEquals((int) 1, ObjectUtils.toPrimitive(i1, 0));
    	assertEquals((int) 1, ObjectUtils.toPrimitive((Integer) null, 1));
    	assertEquals((int) 0, ObjectUtils.toPrimitive((Integer) null, 0));
    }
    
    /**
     * Test method for
     * {@link ObjectUtils#toPrimitive(Long, long)}.
     */
    @Test
    public void testToPrimitiveLong() {
    	Long l0 = Long.valueOf("0");
    	Long l1 = Long.valueOf("1");
    	assertEquals(0l, ObjectUtils.toPrimitive(l0, 1l));
    	assertEquals(0l, ObjectUtils.toPrimitive(l0, 0l));
    	assertEquals(1l, ObjectUtils.toPrimitive(l1, 1l));
    	assertEquals(1l, ObjectUtils.toPrimitive(l1, 0l));
    	assertEquals(1l, ObjectUtils.toPrimitive((Long) null, 1l));
    	assertEquals(0l, ObjectUtils.toPrimitive((Long) null, 0l));
    }
    
    /**
     * Test method for
     * {@link ObjectUtils#toPrimitive(Float, float)}.
     */
    @Test
    public void testToPrimitiveFloat() {
    	double delta = 0.01;
    	
    	Float f0 = Float.valueOf("0");
    	Float f1 = Float.valueOf("1");
    	assertEquals(0f, ObjectUtils.toPrimitive(f0, 1f), delta);
    	assertEquals(0f, ObjectUtils.toPrimitive(f0, 0f), delta);
    	assertEquals(1f, ObjectUtils.toPrimitive(f1, 1f), delta);
    	assertEquals(1f, ObjectUtils.toPrimitive(f1, 0f), delta);
    	assertEquals(1f, ObjectUtils.toPrimitive((Float) null, 1f), delta);
    	assertEquals(0f, ObjectUtils.toPrimitive((Float) null, 0f), delta);
    }
    
    /**
     * Test method for
     * {@link ObjectUtils#toPrimitive(Double, double)}.
     */
    @Test
    public void testToPrimitiveDouble() {
    	double delta = 0.01;
    	
    	Double d0 = Double.valueOf("0");
    	Double d1 = Double.valueOf("1");
    	assertEquals(0d, ObjectUtils.toPrimitive(d0, 1d), delta);
    	assertEquals(0d, ObjectUtils.toPrimitive(d0, 0d), delta);
    	assertEquals(1d, ObjectUtils.toPrimitive(d1, 1d), delta);
    	assertEquals(1d, ObjectUtils.toPrimitive(d1, 0d), delta);
    	assertEquals(1d, ObjectUtils.toPrimitive((Double) null, 1d), delta);
    	assertEquals(0d, ObjectUtils.toPrimitive((Double) null, 0d), delta);
    }
}
