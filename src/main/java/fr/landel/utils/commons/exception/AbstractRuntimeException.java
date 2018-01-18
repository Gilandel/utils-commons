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

import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Abstract runtime exception.
 *
 * @since Nov 27, 2015
 * @author Gilles Landel
 *
 */
public abstract class AbstractRuntimeException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 780825498990380553L;

    /**
     * Constructor
     */
    public AbstractRuntimeException() {
        super();
    }

    /**
     * Constructor with message. To format the message, this method uses
     * {@link String#format} function.
     * 
     * @param message
     *            message
     * @param arguments
     *            message arguments
     */
    public AbstractRuntimeException(final CharSequence message, final Object... arguments) {
        super(ArrayUtils.isNotEmpty(arguments) ? String.format(message.toString(), arguments) : message.toString());
    }

    /**
     * Constructor with message. To format the message, this method uses
     * {@link String#format} function.
     * 
     * @param locale
     *            message locale
     * @param message
     *            message
     * @param arguments
     *            message arguments
     */
    public AbstractRuntimeException(final Locale locale, final CharSequence message, final Object... arguments) {
        super(ArrayUtils.isNotEmpty(arguments) ? String.format(locale, message.toString(), arguments) : message.toString());
    }

    /**
     * Constructor.
     *
     * @param cause
     *            The cause
     */
    public AbstractRuntimeException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with class and exception.
     * 
     * @param clazz
     *            The super class
     * @param exception
     *            The exception
     */
    public AbstractRuntimeException(final Class<? extends AbstractRuntimeException> clazz, final Throwable exception) {
        super(clazz.getSimpleName(), exception);
    }

    /**
     * Constructor with message and exception. (inverse parameter order to keep
     * compatibility with standard signature)
     * 
     * @param message
     *            The message
     * @param exception
     *            The exception
     */
    public AbstractRuntimeException(final CharSequence message, final Throwable exception) {
        super(message.toString(), exception);
    }

    /**
     * Constructor with message and exception. To format the message, this
     * method uses {@link String#format} function.
     * 
     * @param exception
     *            The exception
     * @param message
     *            The message
     * @param arguments
     *            message arguments
     */
    public AbstractRuntimeException(final Throwable exception, final CharSequence message, final Object... arguments) {
        super(ArrayUtils.isNotEmpty(arguments) ? String.format(message.toString(), arguments) : message.toString(), exception);
    }

    /**
     * Constructor with message and exception. To format the message, this
     * method uses {@link String#format} function.
     * 
     * @param exception
     *            The exception
     * @param locale
     *            message locale
     * @param message
     *            The message
     * @param arguments
     *            message arguments
     */
    public AbstractRuntimeException(final Throwable exception, final Locale locale, final CharSequence message, final Object... arguments) {
        super(ArrayUtils.isNotEmpty(arguments) ? String.format(locale, message.toString(), arguments) : message.toString(), exception);
    }

    /**
     * Constructor. (inverse parameter order to keep compatibility with standard
     * signature)
     *
     * @param message
     *            The message
     * @param cause
     *            the cause
     * @param enableSuppression
     *            whether or not suppression is enabled or disabled
     * @param writableStackTrace
     *            whether or not the stack trace should be writable
     */
    protected AbstractRuntimeException(final CharSequence message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message.toString(), cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructor. To format the message, this method uses
     * {@link String#format} function.
     *
     * @param cause
     *            the cause
     * @param enableSuppression
     *            whether or not suppression is enabled or disabled
     * @param writableStackTrace
     *            whether or not the stack trace should be writable
     * @param message
     *            message
     * @param arguments
     *            message arguments
     */
    protected AbstractRuntimeException(final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace,
            final CharSequence message, final Object... arguments) {
        super(ArrayUtils.isNotEmpty(arguments) ? String.format(message.toString(), arguments) : message.toString(), cause,
                enableSuppression, writableStackTrace);
    }

    /**
     * Constructor. To format the message, this method uses
     * {@link String#format} function.
     *
     * @param cause
     *            the cause
     * @param enableSuppression
     *            whether or not suppression is enabled or disabled
     * @param writableStackTrace
     *            whether or not the stack trace should be writable
     * @param locale
     *            message locale
     * @param message
     *            message
     * @param arguments
     *            message arguments
     */
    protected AbstractRuntimeException(final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace,
            final Locale locale, final CharSequence message, final Object... arguments) {
        super(ArrayUtils.isNotEmpty(arguments) ? String.format(locale, message.toString(), arguments) : message.toString(), cause,
                enableSuppression, writableStackTrace);
    }
}
