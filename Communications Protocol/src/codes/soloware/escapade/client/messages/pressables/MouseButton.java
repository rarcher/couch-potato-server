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
package codes.soloware.escapade.client.messages.pressables;

import codes.soloware.couchpotato.server.api.EventQueue;
/**
 * Pre-rename version of {@link codes.soloware.couchpotato.client.messages.pressables.MouseButton}. Retained for
 * compatibility reasons.
 *
 * @deprecated		superseded by {@link codes.soloware.couchpotato.client.messages.pressables.MouseButton}
 */
@Deprecated
public enum MouseButton implements Pressable
{
	left,
	middle,
	right;

	@Override
	public void press(final EventQueue generateIn)
	{
		generateIn.press(codes.soloware.couchpotato.client.messages.pressables.MouseButton.valueOf(name()));
	}

	@Override
	public void release(EventQueue generateIn)
	{
		generateIn.release(codes.soloware.couchpotato.client.messages.pressables.MouseButton.valueOf(name()));
	}

	@Override
	public String toString()
	{
		return name()+" mouse button";
	}
}
