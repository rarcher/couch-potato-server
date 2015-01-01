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

import codes.soloware.escapade.server.api.MuteControl;
import codes.soloware.escapade.server.api.VolumeControl;
/**
 * A group of controls that manipulate a particular audio output.
 */
public interface AudioControlGroup
{
	/**
	 * Returns <code>this</code>'s {@link VolumeControl}.
	 *
	 * @return		<code>this</code>'s {@link VolumeControl}
	 */
	public VolumeControl getVolumeControl();
	/**
	 * Returns <code>this</code>'s {@link MuteControl}, or <code>null</code> if <code>this</code> does not have a
	 * {@link MuteControl}.
	 *
	 * @return		<code>this</code>'s {@link MuteControl}, or <code>null</code> if <code>this</code> does not have a
	 * 				{@link MuteControl}
	 */
	public MuteControl getMuteControl();
}
