/*
 * Copyright 2014-2016 Ryan Archer
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
package codes.soloware.couchpotato.server.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import codes.soloware.couchpotato.client.messages.pressables.MouseButton;
import codes.soloware.couchpotato.client.messages.pressables.NonCharacterKey;
import codes.soloware.couchpotato.server.api.EventQueue;
/**
 * An {@link EventQueue} decorator that logs all events.
 */
public class LoggedEventQueue implements EventQueue
{
	private static final Logger logger=LoggerFactory.getLogger(LoggedEventQueue.class);
	private final EventQueue base;

	public static EventQueue decorateIfLoggingEnabled(final EventQueue base)
	{
		if (logger.isTraceEnabled())
			return new LoggedEventQueue(base);
		return base;
	}

	private LoggedEventQueue(final EventQueue base)
	{
		if (base==null)
			throw new NullPointerException("Given base event queue is null.");
		this.base=base;
		logger.debug("Instantiated.");
	}

	@Override
	public void mouseMove(final float xChange, final float yChange)
	{
		logger.trace("Moving mouse by ("+xChange+", "+yChange+").");
		base.mouseMove(xChange, yChange);
	}

	@Override
	public void press(final NonCharacterKey pressed)
	{
		logger.trace("Pressing "+pressed.toString()+".");
		base.press(pressed);
	}

	@Override
	public void press(final char pressed) throws IllegalArgumentException
	{
		logger.trace("Pressing '"+pressed+"' key.");
		base.press(pressed);
	}

	@Override
	public void press(final MouseButton pressed)
	{
		logger.trace("Pressing "+pressed.toString()+".");
		base.press(pressed);
	}

	@Override
	public void release(final NonCharacterKey released)
	{
		logger.trace("Releasing "+released.toString()+".");
		base.release(released);
	}

	@Override
	public void release(final char released) throws IllegalArgumentException
	{
		logger.trace("Releasing '"+released+"' key.");
		base.release(released);
	}

	@Override
	public void release(final MouseButton released)
	{
		logger.trace("Releasing "+released.toString()+".");
		base.release(released);
	}

	@Override
	public void rollMouseWheel(final int displacement)
	{
		if (displacement==0)
			logger.trace("Not rolling the mouse wheel.");
		else
		{
			final StringBuilder log=new StringBuilder();
			log.append("Rolling mouse wheel ");
			log.append(Math.abs(displacement));
			log.append(" ");
			if (Math.abs(displacement)==1)
				log.append("notch");
			else log.append("notches");
			log.append(" ");
			if (displacement<0)
				log.append("away from ");
			else log.append("towards");
			log.append(" the user.");
			logger.trace(log.toString());
		}
		base.rollMouseWheel(displacement);
	}
}
