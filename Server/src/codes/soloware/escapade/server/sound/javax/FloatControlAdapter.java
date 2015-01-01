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

import javax.sound.sampled.FloatControl;

import codes.soloware.escapade.server.api.VolumeControl;
/**
 * A {@link VolumeControl} that implements all underlying functionality using a
 * <code>javax.sound.sampled.FloatControl</code> object.
 */
public class FloatControlAdapter implements VolumeControl
{
	private final FloatControl underlying;

	FloatControlAdapter(final FloatControl underlying)
	{
		if (underlying==null)
			throw new NullPointerException("Given float control is null.");
		if (!FloatControl.Type.VOLUME.equals(underlying.getType()))
			throw new IllegalArgumentException("Given float control is not a volume control.");
		this.underlying=underlying;
	}

	@Override
	public float getMaximumVolume()
	{
		return underlying.getMaximum();
	}

	@Override
	public float getMinimumVolume()
	{
		return underlying.getMinimum();
	}

	@Override
	public float getVolume()
	{
		return underlying.getValue();
	}

	@Override
	public void setVolume(final float newVolume) throws IllegalArgumentException
	{
		underlying.setValue(newVolume);
	}
}
