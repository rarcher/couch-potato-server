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
package codes.soloware.couchpotato.server.api;
/**
 * An access point to the volume control of the native system's audio.
 */
public interface VolumeControl
{
	/**
	 * Returns the maximum allowed volume.
	 *
	 * @return		maximum allowed volume
	 */
	public float getMaximumVolume();
	/**
	 * Returns the minimum allowed volume.
	 *
	 * @return		minimum allowed volume
	 */
	public float getMinimumVolume();
	/**
	 * Returns the current volume setting.
	 *
	 * @return		current volume setting
	 */
	public float getVolume();
	/**
	 * Changes the volume setting to a given value. Rounds to the nearest valid setting if necessary.
	 *
	 * @param newVolume						new volume setting
	 * @throws IllegalArgumentException		if the given volume setting is greater than {@link #getMaximumVolume}, or
	 * 										less than {@link #getMinimumVolume}
	 */
	public void setVolume(float newVolume) throws IllegalArgumentException;
}
