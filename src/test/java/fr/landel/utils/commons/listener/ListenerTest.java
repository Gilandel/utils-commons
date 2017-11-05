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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Class to check the listenable design pattern
 *
 * @since Apr 23, 2016
 * @author Gilles
 *
 */
public class ListenerTest {

    private static int eventCount = 0;

    /**
     * The object to listen
     *
     * @since Apr 23, 2016
     * @author Gilles
     *
     */
    private class Listenable extends AbstractListenable {

    }

    /**
     * An object which listens the listenable
     *
     * @since Apr 23, 2016
     * @author Gilles
     *
     */
    private class ListenerImpl implements Listener {
        /**
         * Add event type
         */
        public static final int ADD_EVENT = 1;

        /**
         * Remove event type
         */
        public static final int REMOVE_EVENT = 2;

        @Override
        public void execute(final EventListener event) {
            if (event.getSource() != null) {
                switch (event.getValue()) {
                case ADD_EVENT:
                    ListenerTest.eventCount++;
                    break;
                case REMOVE_EVENT:
                    ListenerTest.eventCount--;
                    break;
                default:
                }
            }
        }
    }

    @Test
    public void test() {
        final Listenable listenable = new Listenable();

        // register events
        listenable.addListener(new ListenerImpl());
        listenable.addListener(new ListenerImpl());

        // Send an event to the listenable, and the listenable sends itself an
        // event to all registered listeners
        listenable.fire(new EventListener(ListenerImpl.ADD_EVENT, this));

        assertEquals(2, eventCount);

        listenable.fire(new EventListener(ListenerImpl.REMOVE_EVENT, this));

        assertEquals(0, eventCount);
    }
}
