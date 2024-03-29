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
public class AbstractRuntimeExceptionTest extends AbstractRuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6301742225214990685L;

	/**
	 * Test method for {@link AbstractRuntimeException#AbstractRuntimeException()} .
	 *
	 * @throws AbstractRuntimeException The expected exception
	 */
	@Test
	public void testAbstractRuntimeException() throws AbstractRuntimeException {
		AbstractRuntimeException exception = new AbstractRuntimeException() {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertThrows(AbstractRuntimeException.class, () -> {
			assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

			throw exception;
		});
	}

	/**
	 * Test method for
	 * {@link AbstractRuntimeException#AbstractRuntimeException(CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionString() {
		AbstractRuntimeException exception = new AbstractRuntimeException("test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertEquals("test", e.getMessage());
		}

		exception = new AbstractRuntimeException("test %s", "exception") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertEquals("test exception", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link AbstractRuntimeException#AbstractRuntimeException(Locale, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionStringLocale() {
		AbstractRuntimeException exception = new AbstractRuntimeException(Locale.FRANCE, "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertEquals("test", e.getMessage());
		}

		exception = new AbstractRuntimeException(Locale.FRANCE, "test %.2f", Math.PI) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertEquals("test 3,14", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link AbstractRuntimeException#AbstractRuntimeException(java.lang.Throwable)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionThrowable() {
		AbstractRuntimeException exception = new AbstractRuntimeException(new IOException()) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertNotNull(e.getCause());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractRuntimeException#AbstractRuntimeException(java.lang.Class, java.lang.Throwable)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionClassOfQextendsAbstractRuntimeExceptionThrowable() {
		AbstractRuntimeException exception = new AbstractRuntimeException(AbstractRuntimeExceptionTest.class,
				new IOException()) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertNotNull(e.getCause());
			assertEquals(AbstractRuntimeExceptionTest.class.getSimpleName(), e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractRuntimeException#AbstractRuntimeException(java.lang.String, java.lang.Throwable)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionStringThrowable() {
		AbstractRuntimeException exception = new AbstractRuntimeException("test", new IOException()) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractRuntimeException#AbstractRuntimeException(Throwable, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionThrowableString() {
		AbstractRuntimeException exception = new AbstractRuntimeException(new IOException(), "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}

		exception = new AbstractRuntimeException(new IOException(), "test %s", "exception") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertNotNull(e.getCause());
			assertEquals("test exception", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractRuntimeException#AbstractRuntimeException(Throwable, Locale, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionThrowableLocale() {
		AbstractRuntimeException exception = new AbstractRuntimeException(new IOException(), Locale.FRANCE, "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertNotNull(e.getCause());
			assertEquals("test", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}

		exception = new AbstractRuntimeException(new IOException(), Locale.FRANCE, "test %.2f", Math.PI) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			throw exception;
		} catch (AbstractRuntimeException e) {
			assertNotNull(e.getCause());
			assertEquals("test 3,14", e.getMessage());
			assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
		}
	}

	/**
	 * Test method for
	 * {@link AbstractRuntimeException#AbstractRuntimeException(java.lang.String, java.lang.Throwable, boolean, boolean)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionStringThrowableBooleanBoolean() {
		AbstractRuntimeException exception1 = new AbstractRuntimeException("test", new IOException(), false, true) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		AbstractRuntimeException exception2 = new AbstractRuntimeException("test", new IOException(), true, false) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			exception1.addSuppressed(new IOException());
			throw exception1;
		} catch (AbstractRuntimeException e) {
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
		} catch (AbstractRuntimeException e) {
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
	 * {@link AbstractRuntimeException#AbstractRuntimeException(Throwable, boolean, boolean, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionThrowableBooleanBooleanString() {
		AbstractRuntimeException exception1 = new AbstractRuntimeException(new IOException(), false, true, "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		AbstractRuntimeException exception2 = new AbstractRuntimeException(new IOException(), true, false, "test %s",
				"exception") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			exception1.addSuppressed(new IOException());
			throw exception1;
		} catch (AbstractRuntimeException e) {
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
		} catch (AbstractRuntimeException e) {
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
	 * {@link AbstractRuntimeException#AbstractRuntimeException(Throwable, boolean, boolean, Locale, CharSequence, Object...)}
	 * .
	 */
	@Test
	public void testAbstractRuntimeExceptionThrowableBooleanBooleanLocale() {
		AbstractRuntimeException exception1 = new AbstractRuntimeException(new IOException(), false, true,
				Locale.FRANCE, "test") {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		AbstractRuntimeException exception2 = new AbstractRuntimeException(new IOException(), true, false,
				Locale.FRANCE, "test %.2f", Math.PI) {

			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
		};

		assertTrue(Exception.class.isAssignableFrom(AbstractRuntimeException.class));

		try {
			exception1.addSuppressed(new IOException());
			throw exception1;
		} catch (AbstractRuntimeException e) {
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
		} catch (AbstractRuntimeException e) {
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
