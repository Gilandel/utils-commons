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
 * Check abstract exception
 *
 * @since Apr 23, 2016
 * @author Gilles
 *
 */
public class AbstractExceptionTest extends AbstractException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6301742295214990685L;

	/**
	 * Test method for {@link AbstractException#AbstractException()} .
	 *
	 * @throws AbstractException The expected exception
	 */
	@Test
	public void testAbstractException() throws AbstractException {
		AbstractException exception = new AbstractException() {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertThrows(AbstractException.class, () -> {
			assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

			throw exception;
		});
	}

	/**
	 * Test method for
	 * {@link AbstractException#AbstractException(CharSequence, Object...)} .
	 */
	@Test
	public void testAbstractExceptionString() {
		AbstractException exception = new AbstractException("test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertEquals("test", e.getMessage());
		}

		exception = new AbstractException("test %s", "exception") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertEquals("test exception", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link AbstractException#AbstractException(Locale, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractExceptionStringLocale() {
		AbstractException exception = new AbstractException(Locale.FRANCE, "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertEquals("test", e.getMessage());
		}

		exception = new AbstractException(Locale.FRANCE, "test %.2f", Math.PI) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertEquals("test 3,14", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link AbstractException#AbstractException(java.lang.Throwable)} .
	 */
	@Test
	public void testAbstractExceptionThrowable() {
		AbstractException exception = new AbstractException(new IOException()) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertNotNull(e.getCause());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractException#AbstractException(java.lang.Class, java.lang.Throwable)}
	 * .
	 */
	@Test
	public void testAbstractExceptionClassOfQextendsAbstractExceptionThrowable() {
		AbstractException exception = new AbstractException(AbstractExceptionTest.class, new IOException()) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertNotNull(e.getCause());
			assertEquals(AbstractExceptionTest.class.getSimpleName(), e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractException#AbstractException(java.lang.String, java.lang.Throwable)}
	 * .
	 */
	@Test
	public void testAbstractExceptionStringThrowable() {
		AbstractException exception = new AbstractException("test", new IOException()) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractException#AbstractException(Throwable, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractExceptionThrowableString() {
		AbstractException exception = new AbstractException(new IOException(), "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}

		exception = new AbstractException(new IOException(), "test %s", "exception") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertNotNull(e.getCause());
			assertEquals("test exception", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractException#AbstractException(Throwable, Locale, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractExceptionThrowableLocale() {
		AbstractException exception = new AbstractException(new IOException(), Locale.FRANCE, "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}

		exception = new AbstractException(new IOException(), Locale.FRANCE, "test %.2f", Math.PI) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			throw exception;
		} catch (AbstractException e) {
			assertNotNull(e.getCause());
			assertEquals("test 3,14", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractException#AbstractException(java.lang.String, java.lang.Throwable, boolean, boolean)}
	 * .
	 */
	@Test
	public void testAbstractExceptionStringThrowableBooleanBoolean() {
		AbstractException exception1 = new AbstractException("test", new IOException(), false, true) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		AbstractException exception2 = new AbstractException("test", new IOException(), true, false) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			exception1.addSuppressed(new IOException());
			throw exception1;
		} catch (AbstractException e) {
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
		} catch (AbstractException e) {
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
	 * {@link AbstractException#AbstractException(Throwable, boolean, boolean, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractExceptionThrowableBooleanBooleanString() {
		AbstractException exception1 = new AbstractException(new IOException(), false, true, "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		AbstractException exception2 = new AbstractException(new IOException(), true, false, "test %s", "exception") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			exception1.addSuppressed(new IOException());
			throw exception1;
		} catch (AbstractException e) {
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
		} catch (AbstractException e) {
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
	 * {@link AbstractException#AbstractException(Throwable, boolean, boolean, Locale, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractExceptionThrowableBooleanBooleanLocale() {
		AbstractException exception1 = new AbstractException(new IOException(), false, true, Locale.FRANCE, "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		AbstractException exception2 = new AbstractException(new IOException(), true, false, Locale.FRANCE, "test %.2f",
				Math.PI) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractException.class));

		try {
			exception1.addSuppressed(new IOException());
			throw exception1;
		} catch (AbstractException e) {
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
		} catch (AbstractException e) {
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
