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
package fr.landel.utils.commons.expect;

/**
 * Expect exception
 *
 * @since Jul 19, 2016
 * @author Gilles
 */
public class ExpectException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3829486658567119898L;

    /**
     * Constructs a new expect exception with the specified detail message.
     *
     * @param message
     *            the detail message. The detail message is saved for later
     *            retrieval by the {@link #getMessage()} method.
     */
    public ExpectException(final String message) {
        super(message);
    }
}
