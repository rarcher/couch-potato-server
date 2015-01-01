/*
 * Copyright 2014 Ryan Archer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package codes.soloware.escapade.server.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import codes.soloware.escapade.server.api.EventQueue;
import codes.soloware.escapade.server.api.InputEvent;
/**
 * A {@link Listener} that {@link InputEvent#enqueue}s any {@link #received} {@link InputEvent}s.
 */
public class InputEventListener extends Listener
{
	private static final Logger logger=LoggerFactory.getLogger(InputEventListener.class);
	private final EventQueue queue;

	public InputEventListener(final EventQueue queue)
	{
		if (queue==null)
			throw new NullPointerException("Given event queue is null.");
		this.queue=queue;
		logger.debug("Instantiated.");
	}

	@Override
	public void received(final Connection ignored, final Object received)
	{
		if (received instanceof InputEvent)
		{
			try
			{
				((InputEvent)received).enqueue(queue);
			}
			catch (final Exception error)
			{
				logger.error("An exception was thrown while enqueueing an input event.", error);
			}
		}
	}
}
