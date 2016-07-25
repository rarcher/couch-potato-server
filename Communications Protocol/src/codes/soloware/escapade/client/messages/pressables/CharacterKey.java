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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import codes.soloware.couchpotato.server.api.EventQueue;
/**
 * Pre-rename version of {@link codes.soloware.couchpotato.client.messages.pressables.CharacterKey}. Retained for
 * compatibility reasons.
 *
 * @deprecated		superseded by {@link codes.soloware.couchpotato.client.messages.pressables.CharacterKey}
 */
@Deprecated
public class CharacterKey implements Pressable
{
	private static final long serialVersionUID=1L;
	private static final Logger logger=LoggerFactory.getLogger(CharacterKey.class);
	private final char keyProduct;
	/**
	 * Constructor for use by Kryo during deserialization. Value set for {@link #keyProduct} is meaningless, as it will
	 * be overwritten by the serialization engine.
	 */
	@SuppressWarnings("unused")
	private CharacterKey()
	{
		keyProduct=0;
	}

	public CharacterKey(final char keyProduct)
	{
		this.keyProduct=keyProduct;
	}

	@Override
	public void press(final EventQueue generateIn)
	{
		try
		{
			generateIn.press(keyProduct);
		}
		catch (final IllegalArgumentException notAKey)
		{
			if (logger.isErrorEnabled())
				logKeyMappingError(true, notAKey);
		}
	}

	@Override
	public void release(final EventQueue generateIn)
	{
		try
		{
			generateIn.release(keyProduct);
		}
		catch (final IllegalArgumentException notAKey)
		{
			if (logger.isErrorEnabled())
				logKeyMappingError(false, notAKey);
		}
	}

	private void logKeyMappingError(final boolean isPress, final IllegalArgumentException error)
	{
		final StringBuilder log=new StringBuilder();
		if (isPress)
			log.append("Pressed");
		else log.append("Released");
		log.append(" character '");
		log.append(keyProduct);
		log.append("' cannot be mapped to a keyboard key.");
		log.append(System.getProperty("line.separator"));
		log.append('\t');
		log.append("Most likely, this is due to '");
		log.append(keyProduct);
		log.append("' being impossible to produce without using a modifier key.");
		logger.error(log.toString(), error);
	}

	@Override
	public final String name()
	{
		return "'"+keyProduct+"'";
	}

	@Override
	public String toString()
	{
		return name()+" key";
	}
}
