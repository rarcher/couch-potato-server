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
package codes.soloware.couchpotato.client.messages.pressables;

import codes.soloware.couchpotato.server.api.EventQueue;
/**
 * <p>
 * A keyboard key that does not produce a character when pressed.
 * </p>
 * <p>
 * The constants that represent keyboard keys have entirely different values under Android and vanilla Java, and neither
 * platform makes the other's available at runtime. This class enables conversion between the two formats.
 * </p>
 */
public enum NonCharacterKey implements Pressable
{
	backspace,
	escape,

	alt,
	ctrl,
	shift,

	capsLock,
	insert,
	numLock,
	scrollLock,

	end,
	home,
	pageDown,
	pageUp,

	downArrow,
	leftArrow,
	rightArrow,
	upArrow,

	F1,
	F2,
	F3,
	F4,
	F5,
	F6,
	F7,
	F8,
	F9,
	F10,
	F11,
	F12;

	@Override
	public void press(final EventQueue generateIn)
	{
		generateIn.press(this);
	}

	@Override
	public void release(final EventQueue generateIn)
	{
		generateIn.release(this);
	}

	@Override
	public String toString()
	{
		return name()+" key";
	}
}
