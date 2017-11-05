/*
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
package fr.landel.utils.commons.listener;

import java.util.HashSet;
import java.util.Set;

/**
 * Asbtract listenable
 *
 * @since Dec 11, 2015
 * @author Gilles Landel
 *
 */
public abstract class AbstractListenable {

    private Set<Listener> listeners;

    /**
     * Constructor
     */
    public AbstractListenable() {
        this.listeners = new HashSet<>();
    }

    /**
     * Fire the event to all listeners
     * 
     * @param event
     *            The event to send to listeners
     */
    public void fire(final EventListener event) {
        for (Listener listener : this.listeners) {
            listener.execute(event);
        }
    }

    /**
     * Add a listener
     * 
     * @param listener
     *            The new listener
     */
    public void addListener(final Listener listener) {
        this.listeners.add(listener);
    }
}
