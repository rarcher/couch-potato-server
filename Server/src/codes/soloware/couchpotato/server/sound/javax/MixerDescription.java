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

import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>
 * A human-readable <code>CharSequence</code> describing a <code>Mixer</code> in detail.
 * </p>
 * <p>
 * Note that this class makes no attempt to <code>Line.open()</code> any <code>Mixer</code>'s <code>Line</code>s, as
 * doing so would require it to manage associated system resources. Non-open <code>Line</code>s may have some
 * <code>Control</code> information missing.
 * </p>
 */
public class MixerDescription implements CharSequence
{
	private static final Logger logger=LoggerFactory.getLogger(MixerDescription.class);
	private static final int defaultIndent=0;
	private final Mixer subject;
	private final int indent;
	private String contents;

	public MixerDescription(final Mixer subject)
	{
		this(subject, defaultIndent);
	}

	public MixerDescription(final Mixer subject, final int indent)
	{
		if (subject==null)
			throw new NullPointerException("Given mixer to describe is null.");
		if (indent<0)
			throw new IllegalArgumentException("Indent setting is negative. More specifically, it is "+indent+".");
		this.subject=subject;
		this.indent=indent;
		contents=null;
	}

	@Override
	public char charAt(final int index)
	{
		return toString().charAt(index);
	}

	@Override
	public int length()
	{
		return toString().length();
	}

	@Override
	public CharSequence subSequence(final int start, final int end)
	{
		return toString().subSequence(start, end);
	}

	@Override
	public final String toString()
	{
		if (contents==null)
		{
			final StringBuilder contentBuilder=new StringBuilder();
			final int headerIndent=indent+1;
			final int lineIndent=headerIndent+1;

			contentBuilder.append(indent(indent));
			contentBuilder.append(subject.getMixerInfo().toString());

			contentBuilder.append(System.getProperty("line.separator"));
			contentBuilder.append(indent(headerIndent));
			contentBuilder.append("Source Lines:");

			for (final Line.Info sourceLine : subject.getSourceLineInfo())
			{
				try
				{
					final CharSequence lineDescription=new LineDescription(subject.getLine(sourceLine), lineIndent);
					contentBuilder.append(System.getProperty("line.separator"));
					contentBuilder.append(lineDescription.toString());
				}
				catch (final LineUnavailableException notAvailable)
				{
					logger.warn("Unable to retrieve the line object for source line \"{}\".", sourceLine, notAvailable);
				}
			}

			contentBuilder.append(System.getProperty("line.separator"));
			contentBuilder.append(indent(headerIndent));
			contentBuilder.append("Target Lines:");

			for (final Line.Info targetLine : subject.getTargetLineInfo())
			{
				try
				{
					final CharSequence lineDescription=new LineDescription(subject.getLine(targetLine), lineIndent);
					contentBuilder.append(System.getProperty("line.separator"));
					contentBuilder.append(lineDescription.toString());
				}
				catch (final LineUnavailableException notAvailable)
				{
					logger.warn("Unable to retrieve the line object for target line \"{}\".", targetLine, notAvailable);
				}
			}

			contents=contentBuilder.toString();
		}
		return contents;
	}

	private static String indent(final int depth)
	{
		final StringBuilder product=new StringBuilder();
		for (int loop=0; loop<depth; loop++)
			product.append('\t');
		return product.toString();
	}
}
