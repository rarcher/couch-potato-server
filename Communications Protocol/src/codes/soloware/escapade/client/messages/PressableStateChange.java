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
package codes.soloware.escapade.client.messages;

import codes.soloware.couchpotato.server.api.EventQueue;
import codes.soloware.escapade.client.messages.pressables.Pressable;
import codes.soloware.escapade.server.api.InputEvent;
/**
 * Pre-rename version of {@link codes.soloware.couchpotato.client.messages.PressableStateChange}. Retained for
 * compatibility reasons.
 *
 * @deprecated		superseded by {@link codes.soloware.couchpotato.client.messages.PressableStateChange}
 */
@Deprecated
public class PressableStateChange implements InputEvent
{
	private static final long serialVersionUID=1L;
	private final Pressable change;
	private final boolean isPress;
	/**
	 * Constructor for use by Kryo during deserialization. Values set for {@link #change} and {@link #isPress} are
	 * meaningless, as they will be overwritten by the serialization engine.
	 */
	@SuppressWarnings("unused")
	private PressableStateChange()
	{
		change=null;
		isPress=false;
	}

	public PressableStateChange(final Pressable change, final boolean isPress)
	{
		if (change==null)
			throw new NullPointerException("Given pressable is null.");
		this.change=change;
		this.isPress=isPress;
	}

	@Override
	public void enqueue(final EventQueue addTo)
	{
		if (isPress)
			change.press(addTo);
		else change.release(addTo);
	}

	@Override
	public String toString()
	{
		if (isPress)
			return "press of the "+change.toString();
		return "release of the "+change.toString();
	}
}
