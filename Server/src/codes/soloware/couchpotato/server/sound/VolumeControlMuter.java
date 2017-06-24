/*
 * Copyright 2014-2017 Ryan Archer
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

import codes.soloware.couchpotato.server.api.MuteControl;
import codes.soloware.couchpotato.server.api.VolumeControl;
/**
 * A {@link MuteControl} implemented on top of a {@link VolumeControl}.
 */
public class VolumeControlMuter implements MuteControl
{
	private final VolumeControl volume;
	private float oldVolume;

	public VolumeControlMuter(final VolumeControl volume)
	{
		if (volume==null)
			throw new NullPointerException("Given volume control to mute is null.");
		this.volume=volume;
		oldVolume=volume.getMinimumVolume();
	}

	@Override
	public final boolean isMuted()
	{
		return volume.getVolume()==volume.getMinimumVolume();
	}

	@Override
	public void mute()
	{
		oldVolume=volume.getVolume();
		volume.setVolume(volume.getMinimumVolume());
	}

	@Override
	public void unmute()
	{
		if (isMuted())
			volume.setVolume(oldVolume);
	}
}
