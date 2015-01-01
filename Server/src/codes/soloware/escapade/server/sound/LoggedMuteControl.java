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
package codes.soloware.escapade.server.sound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import codes.soloware.escapade.server.api.MuteControl;
/**
 * A {@link MuteControl} decorator that logs all {@link #mute} and {@link #unmute} operations.
 */
public class LoggedMuteControl implements MuteControl
{
	private static final Logger logger=LoggerFactory.getLogger(LoggedMuteControl.class);
	private final MuteControl base;

	public static MuteControl decorateIfLoggingEnabled(final MuteControl base)
	{
		if (logger.isDebugEnabled())
			return new LoggedMuteControl(base);
		return base;
	}

	private LoggedMuteControl(final MuteControl base)
	{
		if (base==null)
			throw new NullPointerException("Given base mute control is null.");
		this.base=base;
		final StringBuilder log=new StringBuilder();
		log.append("Instantiated. ");
		if (base.isMuted())
			log.append("Initially muted.");
		else log.append("Not initially muted.");
		logger.debug(log.toString());
	}

	@Override
	public boolean isMuted()
	{
		return base.isMuted();
	}

	@Override
	public void mute()
	{
		logger.debug("Muting sound.");
		base.mute();
	}

	@Override
	public void unmute()
	{
		logger.debug("Unmuting sound.");
		base.unmute();
	}
}
