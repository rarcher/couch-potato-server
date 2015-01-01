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
package codes.soloware.escapade.server.sound.javax;

import javax.sound.sampled.BooleanControl;

import codes.soloware.escapade.server.api.MuteControl;
/**
 * A {@link MuteControl} that implements all underlying functionality using a
 * <code>javax.sound.sampled.BooleanControl</code> object.
 */
public class BooleanControlAdapter implements MuteControl
{
	private final BooleanControl underlying;

	BooleanControlAdapter(final BooleanControl underlying)
	{
		if (underlying==null)
			throw new NullPointerException("Given boolean control is null.");
		if (!BooleanControl.Type.MUTE.equals(underlying.getType()))
			throw new IllegalArgumentException("Given boolean control is not a mute control.");
		this.underlying=underlying;
	}

	@Override
	public boolean isMuted()
	{
		return underlying.getValue();
	}

	@Override
	public void mute()
	{
		underlying.setValue(true);
	}

	@Override
	public void unmute()
	{
		underlying.setValue(false);
	}
}
