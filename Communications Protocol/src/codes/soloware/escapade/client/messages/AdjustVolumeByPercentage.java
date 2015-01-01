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
package codes.soloware.escapade.client.messages;

import codes.soloware.escapade.server.api.VolumeControl;
import codes.soloware.escapade.server.api.VolumeSettingChange;
/**
 * A {@link VolumeSettingChange} that adjusts the volume by a percentage of its maximum value.
 */
public class AdjustVolumeByPercentage implements VolumeSettingChange
{
	private static final long serialVersionUID=1L;
	private static final int totalPercentage=100;
	private final int percentage;
	/**
	 * Constructor for use by Kryo during deserialization. Value set for {@link #percentage} is meaningless, as it will
	 * be overwritten by the serialization engine.
	 */
	@SuppressWarnings("unused")
	private AdjustVolumeByPercentage()
	{
		percentage=0;
	}

	public AdjustVolumeByPercentage(final int percentage)
	{
		this.percentage=percentage;
	}

	@Override
	public void implement(final VolumeControl on)
	{
		float newVolume=on.getVolume()+
				((float)percentage)/((float)totalPercentage)*(on.getMaximumVolume()-on.getMinimumVolume());
		if (newVolume>on.getMaximumVolume())
			newVolume=on.getMaximumVolume();
		if (newVolume<on.getMinimumVolume())
			newVolume=on.getMinimumVolume();
		on.setVolume(newVolume);
	}
}
