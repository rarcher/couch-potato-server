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

import javax.sound.sampled.FloatControl;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {@link FloatControlAdapter} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class FloatControlAdapterTest
{
	private static final float mockVolumeValue=0.5f;
	private static final float errorTolerance=0.0001f;
	private FloatControl mockControl;
	private FloatControlAdapter testee;

	@Before
	public void setup()
	{
		mockControl=Mockito.mock(FloatControl.class);
		Mockito.when(mockControl.getType()).thenReturn(FloatControl.Type.VOLUME);

		testee=new FloatControlAdapter(mockControl);
	}

	@Test
	@SuppressWarnings("boxing")
	public void getMaximumVolume()
	{
		Mockito.when(mockControl.getMaximum()).thenReturn(mockVolumeValue);
		Assert.assertEquals(mockVolumeValue, testee.getMaximumVolume(), errorTolerance);
	}

	@Test
	@SuppressWarnings("boxing")
	public void getMinimumVolume()
	{
		Mockito.when(mockControl.getMinimum()).thenReturn(mockVolumeValue);
		Assert.assertEquals(mockVolumeValue, testee.getMinimumVolume(), errorTolerance);
	}

	@Test
	@SuppressWarnings("boxing")
	public void getVolume()
	{
		Mockito.when(mockControl.getValue()).thenReturn(mockVolumeValue);
		Assert.assertEquals(mockVolumeValue, testee.getVolume(), errorTolerance);
	}

	@Test
	public void setVolume()
	{
		testee.setVolume(mockVolumeValue);
		Mockito.verify(mockControl).setValue(mockVolumeValue);
	}
}
