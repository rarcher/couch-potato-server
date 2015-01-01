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
package codes.soloware.escapade.server.api;
/**
 * An access point to the mute control of the native system's audio.
 */
public interface MuteControl
{
	/**
	 * Returns <code>true</code> if the sound is currently muted.
	 *
	 * @return		<code>true</code> if the sound is currently muted, and <code>false</code> otherwise
	 */
	public boolean isMuted();
	/**
	 * Mutes the sound. Does nothing if the sound is already muted.
	 */
	public void mute();
	/**
	 * Unmutes the sound. Does nothing if the sound is not muted.
	 */
	public void unmute();
}
