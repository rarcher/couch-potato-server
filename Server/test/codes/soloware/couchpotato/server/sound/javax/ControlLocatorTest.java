/*
 * Copyright 2017 Ryan Archer
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
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {@link ControlLocator} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ControlLocatorTest
{
	private static final Control.Type testType=FloatControl.Type.VOLUME;
	private Line mockLine;
	private FloatControl mockVolume;

	@Before
	public void setup()
	{
		mockLine=Mockito.mock(Line.class);

		mockVolume=Mockito.mock(FloatControl.class);
		Mockito.when(mockVolume.getType()).thenReturn(testType);
	}

	@Test
	public void findDirectChildSuccessfully()
	{
		Mockito.when(mockLine.getControls()).thenReturn(new Control[]{mockVolume});

		Assert.assertEquals(mockVolume, new ControlLocator(testType).find(mockLine));
	}

	@Test
	public void findIndirectDescendantSuccessfully()
	{
		final CompoundControl mockParentControl=Mockito.mock(CompoundControl.class);
		Mockito.when(mockLine.getControls()).thenReturn(new Control[]{mockParentControl});
		Mockito.when(mockParentControl.getMemberControls()).thenReturn(new Control[]{mockVolume});

		Assert.assertEquals(mockVolume, new ControlLocator(testType).find(mockLine));
	}

	@Test
	public void failToFindNonexistentControl()
	{
		Mockito.when(mockLine.getControls()).thenReturn(new Control[0]);

		Assert.assertNull(new ControlLocator(testType).find(mockLine));
	}
}
