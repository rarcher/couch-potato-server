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
package codes.soloware.couchpotato.client.messages;

import codes.soloware.couchpotato.server.api.EventQueue;
import codes.soloware.couchpotato.server.api.InputEvent;
/**
 * <p>
 * A movement of the mouse cursor.
 * </p>
 * <p>
 * Instances are implemented as position vectors rather than absolute screen coordinates, as the coordinates reported by
 * one device essentially have no meaning on a another (because of varying screen sizes).
 * </p>
 */
public class MouseMovement implements InputEvent
{
	private static final long serialVersionUID=1L;
	private final float xChange;
	private final float yChange;
	/**
	 * Constructor for use by Kryo during deserialization. Values set for {@link #xChange} and {@link #yChange} are
	 * meaningless, as they will be overwritten by the serialization engine.
	 */
	@SuppressWarnings("unused")
	private MouseMovement()
	{
		xChange=0;
		yChange=0;
	}

	public MouseMovement(final float xChange, final float yChange)
	{
		this.xChange=xChange;
		this.yChange=yChange;
	}

	@Override
	public void enqueue(final EventQueue addTo)
	{
		addTo.mouseMove(xChange, yChange);
	}

	@Override
	public String toString()
	{
		return "mouse movement by ("+xChange+", "+yChange+")";
	}
}
