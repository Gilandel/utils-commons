/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilandel
 * %%
 * Authors: Gilles Landel
 * URL: https://github.com/Gilandel
 * 
 * This file is under Apache License, version 2.0 (2004).
 * #L%
 */
package fr.landel.utils.commons.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.function.Supplier;

import org.junit.Test;

import fr.landel.utils.commons.AbstractTest;
import fr.landel.utils.commons.StringUtils;

/**
 * Check {@link ExceptionUtils}
 *
 * @since Apr 25, 2017
 * @author Gilles
 *
 */
public class ExceptionUtilsTest extends AbstractTest {

    /**
     * Test constructor for {@link ExceptionUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(ExceptionUtils.class));
    }

    /**
     * Test method for
     * {@link ExceptionUtils#throwsException(Object, java.util.function.Predicate, java.util.function.Function, java.util.Locale, String, Object...)}.
     */
    @Test
    public void testThrowsException() {
        assertException(() -> {
            ExceptionUtils.throwsException("test", StringUtils::isEmpty, IOException::new, null, "error %s*");
        }, IOException.class, "error test");

        try {
            ExceptionUtils.throwsException("test", StringUtils::isNotEmpty, IOException::new, null, "error %s*");
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Test method for
     * {@link ExceptionUtils#suppliesException(java.util.function.Function, java.util.Locale, String, Object...)}.
     */
    @Test
    public void testSuppliesException() {
        assertException(() -> {
            final Supplier<IOException> supplier = ExceptionUtils.suppliesException(IOException::new, null, "error");
            assertNotNull(supplier);
            throw supplier.get();
        }, IOException.class, "error");
    }

    /**
     * Test method for {@link ExceptionUtils#catchable(java.lang.Class)}.
     */
    @Test
    public void testCatchable() {
        try {
            ExceptionUtils.catchable(IOException.class);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertException(() -> ExceptionUtils.catchable(null), NullPointerException.class, "exceptionType");
    }

    /**
     * Test method for {@link ExceptionUtils#getCauseOrigin(Throwable)}.
     */
    @Test
    public void testGetCauseOrigin() {
        final IOException e1 = new IOException("test");
        final IOException e2 = new IOException(new IllegalArgumentException("test"));
        final IOException e3 = new IOException(new IllegalArgumentException(e2));

        assertEquals(e1, ExceptionUtils.getCauseOrigin(e1));
        assertEquals(e2.getCause(), ExceptionUtils.getCauseOrigin(e2));
        assertEquals(e2.getCause(), ExceptionUtils.getCauseOrigin(e3));

        assertException(() -> {
            ExceptionUtils.getCauseOrigin(null);
        }, NullPointerException.class, "exception");
    }
}
