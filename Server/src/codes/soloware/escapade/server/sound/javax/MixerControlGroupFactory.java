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

import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import codes.soloware.escapade.server.sound.AudioControlGroup;
/**
 * A factory for building all {@link AudioControlGroup}s needed to manipulate an entire <code>Mixer</code>.
 */
public class MixerControlGroupFactory
{
	private static final Logger logger=LoggerFactory.getLogger(MixerControlGroupFactory.class);

	public MixerControlGroupFactory()
	{
		logger.debug("Instantiated.");
	}

	public AudioControlGroup[] construct(final Mixer from)
	{
		final List<AudioControlGroup> products=new LinkedList<AudioControlGroup>();
		for (final Line.Info info : from.getTargetLineInfo())
		{
			if ("Master target port".equals(info.toString()))
			{
				logger.debug("Found master sound output in mixer \"{}\".", from.getMixerInfo());
				addProduct(products, info, from);
				return products.toArray(new AudioControlGroup[0]);
			}
		}
		for (final Line.Info info : from.getTargetLineInfo())
			addProduct(products, info, from);
		return products.toArray(new AudioControlGroup[0]);
	}

	@SuppressWarnings("static-method")
	private void addProduct(final List<AudioControlGroup> to, final Line.Info info, final Mixer from)
	{
		try
		{
			final AudioControlGroup product=new LineControlGroup(from.getLine(info));
			to.add(product);
		}
		catch (final LineUnavailableException notAvailable)
		{
			logger.warn("Line \"{}\" is not available.", info, notAvailable);
		}
		catch (final Exception noVolume)
		{
			logger.debug(noVolume.getMessage());
		}
	}
}
