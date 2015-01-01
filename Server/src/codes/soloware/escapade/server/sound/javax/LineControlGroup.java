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
package codes.soloware.escapade.server.sound.javax;

import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import codes.soloware.escapade.server.api.MuteControl;
import codes.soloware.escapade.server.api.VolumeControl;
import codes.soloware.escapade.server.sound.AudioControlGroup;
/**
 * An {@link AudioControlGroup} that manipulates a given <code>Line</code>.
 */
public class LineControlGroup implements AudioControlGroup
{
	private static final Logger logger=LoggerFactory.getLogger(LineControlGroup.class);
	private final VolumeControl volume;
	private final MuteControl mute;

	public LineControlGroup(final Line underlying) throws LineUnavailableException, Exception
	{
		if (!underlying.isOpen())
			underlying.open();

		final FloatControl volumeInput=(FloatControl)new ControlLocator(FloatControl.Type.VOLUME).find(underlying);
		if (volumeInput==null)
			throw new Exception("Could not find volume control for line \""+underlying.getLineInfo().toString()+"\".");
		volume=new FloatControlAdapter(volumeInput);

		final StringBuilder log=new StringBuilder();
		if (logger.isDebugEnabled())
		{
			log.append("Instantiated for line \"");
			log.append(underlying.getLineInfo().toString());
			log.append("\". ");
		}

		final BooleanControl muteInput=(BooleanControl)new ControlLocator(BooleanControl.Type.MUTE).find(underlying);
		if (muteInput==null)
		{
			mute=null;
			if (logger.isDebugEnabled())
				log.append("Found volume control but no mute control.");
		}
		else
		{
			mute=new BooleanControlAdapter(muteInput);
			if (logger.isDebugEnabled())
				log.append("Found volume and mute controls.");
		}
		logger.debug(log.toString());
	}

	@Override
	public VolumeControl getVolumeControl()
	{
		return volume;
	}

	@Override
	public MuteControl getMuteControl()
	{
		return mute;
	}
}
