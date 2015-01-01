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

import codes.soloware.escapade.server.api.VolumeControl;
/**
 * A {@link VolumeControl} decorator that logs all {@link #setVolume} operations.
 */
public class LoggedVolumeControl implements VolumeControl
{
	private static final Logger logger=LoggerFactory.getLogger(LoggedVolumeControl.class);
	private final VolumeControl base;

	public static VolumeControl decorateIfLoggingEnabled(final VolumeControl base)
	{
		if (logger.isDebugEnabled())
			return new LoggedVolumeControl(base);
		return base;
	}

	private LoggedVolumeControl(final VolumeControl base)
	{
		if (base==null)
			throw new NullPointerException("Given base volume control is null.");
		this.base=base;
		logger.debug("Instantiated.\n"
				+"Initial volume is "+base.getVolume()+".\n"
				+"Maximum volume is "+base.getMaximumVolume()+".\n"
				+"Minimum volume is "+base.getMinimumVolume()+".\n");
	}

	@Override
	public float getMaximumVolume()
	{
		return base.getMaximumVolume();
	}

	@Override
	public float getMinimumVolume()
	{
		return base.getMinimumVolume();
	}

	@Override
	public float getVolume()
	{
		return base.getVolume();
	}

	@Override
	public void setVolume(final float newVolume) throws IllegalArgumentException
	{
		logger.debug("Changing volume to "+newVolume+".");
		base.setVolume(newVolume);
	}
}
