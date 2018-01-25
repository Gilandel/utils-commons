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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple logger used by PredicateNoThrow functional interfaces
 *
 * @since 25 janv. 2018
 * @author Gilles
 */
final class PredicateLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredicateLogger.class);

    private static final String MSG = "the predicate throws an exception";

    /**
     * Hidden Constructor
     */
    private PredicateLogger() {
        throw new UnsupportedOperationException();
    }

    /**
     * logs as debug the exception
     * 
     * @param exception
     *            the exception
     */
    public static void debug(final Throwable exception) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(MSG, exception);
        }
    }
}
