/*
 * Copyright 2014 The original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.spring.events.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventBusScope;
import org.vaadin.spring.events.EventScope;

/**
 * An {@link org.springframework.context.ApplicationListener} that will forward all received events to an {@link org.vaadin.spring.events.EventBus}.
 *
 * @author Petter Holmström (petter@vaadin.com)
 */
public class ApplicationContextEventBroker implements ApplicationListener<ApplicationEvent> {

    private Log logger = LogFactory.getLog(getClass());

    private final EventBus eventBus;

    @Autowired
    public ApplicationContextEventBroker(@EventBusScope(EventScope.APPLICATION) EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.debug(String.format("Propagating application event [%s] to event bus [%s]", event, this));
        eventBus.publish(event.getSource(), event);
    }
}
