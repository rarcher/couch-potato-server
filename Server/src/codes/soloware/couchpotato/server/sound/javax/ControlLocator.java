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
package codes.soloware.couchpotato.server.sound.javax;

import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line;
/**
 * A utility for locating and retrieving an audio control of a given <code>Control.Type</code>.
 */
public class ControlLocator
{
	private final Type locate;

	public ControlLocator(final Type locate)
	{
		if (locate==null)
			throw new NullPointerException("Given control type to locate is null.");
		this.locate=locate;
	}

	public Control find(final Line in)
	{
		return find(in.getControls());
	}

	public Control find(final Control... in)
	{
		for (final Control element : in)
		{
			if (locate.equals(element.getType()))
				return element;
			if (element instanceof CompoundControl)
			{
				final Control found=find(((CompoundControl)element).getMemberControls());
				if (found!=null)
					return found;
			}
		}
		return null;
	}
}
