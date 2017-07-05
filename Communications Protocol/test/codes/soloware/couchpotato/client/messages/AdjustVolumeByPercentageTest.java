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
package codes.soloware.couchpotato.client.messages;

import codes.soloware.couchpotato.server.api.VolumeControl;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {link AdjustVolumeByPercentage} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class AdjustVolumeByPercentageTest
{
	private static final int totalPercentage=100;
	private static final float mockMaximumVolume=1.0f;
	private static final float mockMinimumVolume=0.0f;
	private static final float mockVolumeValue=0.5f;
	private VolumeControl mockVolume;

	@Before
	@SuppressWarnings("boxing")
	public void setup()
	{
		mockVolume=Mockito.mock(VolumeControl.class);
		Mockito.when(mockVolume.getMaximumVolume()).thenReturn(mockMaximumVolume);
		Mockito.when(mockVolume.getMinimumVolume()).thenReturn(mockMinimumVolume);
		Mockito.when(mockVolume.getVolume()).thenReturn(mockVolumeValue);
	}

	@Test
	public void changeVolume()
	{
		final int testAdjustmentPercentage=10;
		final float testAdjustment=((float)testAdjustmentPercentage)/((float)totalPercentage);

		new AdjustVolumeByPercentage(testAdjustmentPercentage).implement(mockVolume);
		Mockito.verify(mockVolume).setVolume(mockVolumeValue+testAdjustment);
	}

	@Test
	public void raiseVolumeBeyondMaximum()
	{
		final int testAdjustmentPercentage=70;

		new AdjustVolumeByPercentage(testAdjustmentPercentage).implement(mockVolume);
		Mockito.verify(mockVolume).setVolume(mockMaximumVolume);
	}

	@Test
	public void lowerVolumeBeyondMinimum()
	{
		final int testAdjustmentPercentage=-70;

		new AdjustVolumeByPercentage(testAdjustmentPercentage).implement(mockVolume);
		Mockito.verify(mockVolume).setVolume(mockMinimumVolume);
	}
}
