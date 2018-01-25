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
package fr.landel.utils.commons.function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;
import fr.landel.utils.commons.AbstractTest;

/**
 * Check {@link PredicateLogger}
 *
 * @since 25 janv. 2018
 * @author Gilles
 */
public class PredicateLoggerTest extends AbstractTest {

    /**
     * Test constructor for {@link PredicateLogger} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(PredicateLogger.class));
    }

    /**
     * Test method for {@link PredicateLogger#debug(java.lang.Throwable)}.
     * 
     * @throws SecurityException
     *             on loading LOGGER field error
     * @throws NoSuchFieldException
     *             on loading LOGGER field error
     * @throws IllegalAccessException
     *             on loading LOGGER field error
     * @throws IllegalArgumentException
     *             on loading LOGGER field error
     */
    @Test
    public void testDebug() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final Field field = PredicateLogger.class.getDeclaredField("LOGGER");
        field.setAccessible(true);

        final Logger logger = (Logger) field.get(null);

        final AtomicInteger step = new AtomicInteger(0);

        final Appender<ILoggingEvent> appender = new AppenderBase<ILoggingEvent>() {

            @Override
            protected void append(ILoggingEvent eventObject) {
                assertEquals("the predicate throws an exception", eventObject.getMessage());
                assertEquals(2, step.getAndIncrement());
            }
        };

        appender.start();
        logger.addAppender(appender);
        final Level level = logger.getLevel();

        logger.setLevel(Level.INFO);
        step.set(1);
        PredicateLogger.debug(new IOException());

        logger.setLevel(Level.DEBUG);
        step.set(2);
        PredicateLogger.debug(new IOException());
        assertEquals(3, step.get());

        logger.setLevel(level);
        appender.stop();
    }
}
