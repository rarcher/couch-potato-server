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

import java.io.Serializable;

import codes.soloware.couchpotato.server.api.EventQueue;
/**
 * An input device that can be pressed and released, such as a mouse button or keyboard key.
 */
public interface Pressable extends Serializable
{
	/**
	 * Generates a press of <code>this</code> in the given {@link EventQueue}.
	 *
	 * @param generateIn		{@link EventQueue} to generate a press event in
	 */
	public void press(EventQueue generateIn);
	/**
	 * Generates a release of <code>this</code> in the given {@link EventQueue}.
	 *
	 * @param generateIn		{@link EventQueue} to generate a release event in
	 */
	public void release(EventQueue generateIn);
	/**
	 * Returns an identifier for <code>this</code>.
	 *
	 * @return					identifier for <code>this</code>
	 */
	public String name();
	/**
	 * Returns the {@link #name} of <code>this</code>, along with the type of input device it is.
	 *
	 * @return					{@link #name} of <code>this</code>, along with the type of input device it is
	 */
	@Override
	public String toString();
}
