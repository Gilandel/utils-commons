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
package fr.landel.utils.commons.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * Check abstract runtime exception
 *
 * @since Apr 23, 2016
 * @author Gilles
 *
 */
public class IllegalOperationExceptionTest extends IllegalOperationException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6301742225214990685L;

	/**
	 * Test method for {@link IllegalOperationException#IllegalOperationException()}
	 * .
	 *
	 * @throws IllegalOperationException The expected exception
	 */
	@Test
	public void testIllegalOperationException() throws IllegalOperationException {
		IllegalOperationException exception = new IllegalOperationException();

		assertThrows(IllegalOperationException.class, () -> {
			assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

			throw exception;
		});
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionString() {
		IllegalOperationException exception = new IllegalOperationException("test");

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertEquals("test", e.getMessage());
		}

		exception = new IllegalOperationException("test %s", "exception");

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertEquals("test exception", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(Locale, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionStringLocale() {
		IllegalOperationException exception = new IllegalOperationException(Locale.FRANCE, "test");

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertEquals("test", e.getMessage());
		}

		exception = new IllegalOperationException(Locale.FRANCE, "test %.2f", Math.PI);

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertEquals("test 3,14", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(java.lang.Throwable)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionThrowable() {
		IllegalOperationException exception = new IllegalOperationException(new IOException());

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(java.lang.Class, java.lang.Throwable)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionClassOfQextendsIllegalOperationExceptionThrowable() {
		IllegalOperationException exception = new IllegalOperationException(IllegalOperationExceptionTest.class,
				new IOException());

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals(IllegalOperationExceptionTest.class.getSimpleName(), e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(java.lang.String, java.lang.Throwable)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionStringThrowable() {
		IllegalOperationException exception = new IllegalOperationException("test", new IOException());

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(Throwable, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionThrowableString() {
		IllegalOperationException exception = new IllegalOperationException(new IOException(), "test");

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}

		exception = new IllegalOperationException(new IOException(), "test %s", "exception");

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test exception", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(Throwable, Locale, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionThrowableLocale() {
		IllegalOperationException exception = new IllegalOperationException(new IOException(), Locale.FRANCE, "test");

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}

		exception = new IllegalOperationException(new IOException(), Locale.FRANCE, "test %.2f", Math.PI);

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			throw exception;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test 3,14", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(java.lang.String, java.lang.Throwable, boolean, boolean)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionStringThrowableBooleanBoolean() {
		IllegalOperationException exception1 = new IllegalOperationException("test", new IOException(), false, true);
		IllegalOperationException exception2 = new IllegalOperationException("test", new IOException(), true, false);

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			exception1.addSuppressed(new IOException());
			throw exception1;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
			assertNotNull(e.getSuppressed());
			assertEquals(0, e.getSuppressed().length);
			assertNotNull(e.getStackTrace());
			assertTrue(e.getStackTrace().length > 0);
		}

		try {
			exception2.addSuppressed(new IOException());
			throw exception2;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
			assertNotNull(e.getSuppressed());
			assertTrue(e.getSuppressed().length > 0);
			assertNotNull(e.getStackTrace());
			assertEquals(0, e.getStackTrace().length);
		}
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(Throwable, boolean, boolean, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionThrowableBooleanBooleanString() {
		IllegalOperationException exception1 = new IllegalOperationException(new IOException(), false, true, "test");
		IllegalOperationException exception2 = new IllegalOperationException(new IOException(), true, false, "test %s",
				"exception");

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			exception1.addSuppressed(new IOException());
			throw exception1;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
			assertNotNull(e.getSuppressed());
			assertEquals(0, e.getSuppressed().length);
			assertNotNull(e.getStackTrace());
			assertTrue(e.getStackTrace().length > 0);
		}

		try {
			exception2.addSuppressed(new IOException());
			throw exception2;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test exception", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
			assertNotNull(e.getSuppressed());
			assertTrue(e.getSuppressed().length > 0);
			assertNotNull(e.getStackTrace());
			assertEquals(0, e.getStackTrace().length);
		}
	}

	/**
	 * Test method for
	 * {@link IllegalOperationException#IllegalOperationException(Throwable, boolean, boolean, Locale, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testIllegalOperationExceptionThrowableBooleanBooleanLocale() {
		IllegalOperationException exception1 = new IllegalOperationException(new IOException(), false, true,
				Locale.FRANCE, "test");
		IllegalOperationException exception2 = new IllegalOperationException(new IOException(), true, false,
				Locale.FRANCE, "test %.2f", Math.PI);

		assertTrue(Exception.class.isAssignableFrom(IllegalOperationException.class));

		try {
			exception1.addSuppressed(new IOException());
			throw exception1;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
			assertNotNull(e.getSuppressed());
			assertEquals(0, e.getSuppressed().length);
			assertNotNull(e.getStackTrace());
			assertTrue(e.getStackTrace().length > 0);
		}

		try {
			exception2.addSuppressed(new IOException());
			throw exception2;
		} catch (IllegalOperationException e) {
			assertNotNull(e.getCause());
			assertEquals("test 3,14", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
			assertNotNull(e.getSuppressed());
			assertTrue(e.getSuppressed().length > 0);
			assertNotNull(e.getStackTrace());
			assertEquals(0, e.getStackTrace().length);
		}
	}
}
