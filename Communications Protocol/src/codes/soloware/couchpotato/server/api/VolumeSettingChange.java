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

import java.io.Serializable;
/**
 * A change to the native system's volume setting. Message objects that implement this interface will be automatically
 * {@link #implement}ed on a {@link VolumeControl} by the server.
 */
public interface VolumeSettingChange extends Serializable
{
	/**
	 * Implements <code>this</code> on the given {@link VolumeControl}.
	 *
	 * @param on		{@link VolumeControl} to implement <code>this</code> on
	 */
	public void implement(VolumeControl on);
}
