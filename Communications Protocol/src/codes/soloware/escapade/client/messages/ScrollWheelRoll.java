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
import codes.soloware.escapade.server.api.InputEvent;
/**
 * Pre-rename version of {@link codes.soloware.couchpotato.client.messages.ScrollWheelRoll}. Retained for compatibility
 * reasons.
 *
 * @deprecated		superseded by {@link codes.soloware.couchpotato.client.messages.ScrollWheelRoll}
 */
@Deprecated
public class ScrollWheelRoll implements InputEvent
{
	private static final long serialVersionUID=1L;
	private static int notchesToRollBy=1;
	private static int upwardRollDisplacement=-1*notchesToRollBy;
	private static int downwardRollDisplacement=notchesToRollBy;
	private final boolean isUpward;
	/**
	 * Constructor for use by Kryo during deserialization. Value set for {@link #isUpward} is meaningless, as it will be
	 * overwritten by the serialization engine.
	 */
	@SuppressWarnings("unused")
	private ScrollWheelRoll()
	{
		isUpward=false;
	}

	public ScrollWheelRoll(final boolean isUpward)
	{
		this.isUpward=isUpward;
	}

	@Override
	public void enqueue(final EventQueue addTo)
	{
		if (isUpward)
			addTo.rollMouseWheel(upwardRollDisplacement);
		else addTo.rollMouseWheel(downwardRollDisplacement);
	}
}
