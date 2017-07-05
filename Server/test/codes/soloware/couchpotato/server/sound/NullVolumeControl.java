/*
 * Copyright 2017 Ryan Archer
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

import codes.soloware.couchpotato.server.api.VolumeControl;
/**
 * A dummy {@link VolumeControl} that stores values internally, but has no actual implementation.
 */
public class NullVolumeControl implements VolumeControl
{
	private final float maximumVolume;
	private final float minimumVolume;
	private float volume;

	public NullVolumeControl(final float minimumVolume, final float maximumVolume, final float startingVolume)
	{
		this.maximumVolume=maximumVolume;
		this.minimumVolume=minimumVolume;
		volume=startingVolume;
	}

	@Override
	public float getMaximumVolume()
	{
		return maximumVolume;
	}

	@Override
	public float getMinimumVolume()
	{
		return minimumVolume;
	}

	@Override
	public float getVolume()
	{
		return volume;
	}

	@Override
	public void setVolume(final float newVolume)
	{
		volume=newVolume;
	}

}
