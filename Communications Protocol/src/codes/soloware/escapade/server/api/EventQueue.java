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
package codes.soloware.escapade.server.api;

import codes.soloware.escapade.client.messages.pressables.MouseButton;
import codes.soloware.escapade.client.messages.pressables.NonCharacterKey;
/**
 * Pre-rename version of {@link codes.soloware.couchpotato.server.api.EventQueue}. Retained for compatibility reasons.
 *
 * @deprecated		superseded by {@link codes.soloware.couchpotato.server.api.EventQueue}
 */
@Deprecated
public interface EventQueue
{
	/**
	 * Moves the mouse pointer by a given vector.
	 *
	 * @param xChange		movement along the X-axis
	 * @param yChange		movement along the Y-axis
	 */
	public void mouseMove(float xChange, float yChange);
	/**
	 * Presses a {@link NonCharacterKey}.
	 *
	 * @param pressed		{@link NonCharacterKey} to press
	 */
	public void press(NonCharacterKey pressed);
	/**
	 * Presses the keyboard key that produces a given <code>char</code>. Modifiers (such as shift) are not considered
	 * here; a key only "produces" a <code>char</code> if it does so with no modifiers active.
	 *
	 * @param pressed						<code>char</code> produced by the key to press
	 * @throws IllegalArgumentException		if the given <code>char</code> is not produced by a keyboard key
	 */
	public void press(char pressed) throws IllegalArgumentException;
	/**
	 * Presses a {@link MouseButton}.
	 *
	 * @param pressed		{@link MouseButton} to press
	 */
	public void press(MouseButton pressed);
	/**
	 * Releases a previously {@link #press}ed {@link NonCharacterKey}.
	 *
	 * @param released		{@link NonCharacterKey} to release
	 */
	public void release(NonCharacterKey released);
	/**
	 * Releases the keyboard key that produces a given <code>char</code>. Modifiers (such as shift) are not considered
	 * here; a key only "produces" a <code>char</code> if it does so with no modifiers active.
	 *
	 * @param released						<code>char</code> produced by the key to release
	 * @throws IllegalArgumentException		if the given <code>char</code> is not produced by a keyboard key
	 */
	public void release(char released) throws IllegalArgumentException;
	/**
	 * Releases a previously {@link #press}ed {@link MouseButton}.
	 *
	 * @param released		{@link MouseButton} to release
	 */
	public void release(MouseButton released);
	/**
	 * Rolls the mouse's scroll wheel.
	 *
	 * @param displacement		direction and distance to roll the mouse wheel by; negative values represent rolls up/away
	 * 							from the user, positive values indicate movement down/towards the user; absolute value
	 * 							represents the number of "notches" to roll by
	 */
	public void rollMouseWheel(int displacement);
}
