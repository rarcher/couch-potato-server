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
package codes.soloware.couchpotato.server.sound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import codes.soloware.couchpotato.server.api.MuteControl;
import codes.soloware.couchpotato.server.api.MuteSettingChange;
import codes.soloware.couchpotato.server.api.VolumeControl;
import codes.soloware.couchpotato.server.api.VolumeSettingChange;
/**
 * A {@link Listener} that implements any {@link #received} audio setting changes.
 */
public class AudioSettingChangeListener extends Listener
{
	private static final Logger logger=LoggerFactory.getLogger(AudioSettingChangeListener.class);
	private final VolumeControl volume;
	private final MuteControl mute;

	public AudioSettingChangeListener(final AudioControlGroup controls)
	{
		volume=LoggedVolumeControl.decorateIfLoggingEnabled(controls.getVolumeControl());
		if (volume==null)
			throw new NullPointerException("Given audio control group has a null volume control.");

		MuteControl muteCandidate=controls.getMuteControl();
		if (muteCandidate==null)
			muteCandidate=new VolumeControlMuter(volume);
		mute=LoggedMuteControl.decorateIfLoggingEnabled(muteCandidate);
	}

	@Override
	public void received(final Connection ignored, final Object received)
	{
		if (received instanceof VolumeSettingChange)
		{
			try
			{
				((VolumeSettingChange)received).implement(volume);
			}
			catch (final Exception error)
			{
				logger.error("An exception was thrown while implementing a volume setting change.", error);
			}
		}
		if (received instanceof MuteSettingChange)
		{
			try
			{
				((MuteSettingChange)received).implement(mute);
			}
			catch (final Exception error)
			{
				logger.error("An exception was thrown while implementing a mute setting change.", error);
			}
		}
	}
}
