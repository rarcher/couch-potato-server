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
package codes.soloware.couchpotato.server.sound;

import codes.soloware.couchpotato.server.api.VolumeControl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * A set of test cases for the {@link VolumeControlMuter} class.
 */
public class VolumeControlMuterTest
{
	private static final float mockMaximumVolume=1.0f;
	private static final float mockMinimumVolume=0.0f;
	private static final float startingMockVolume=0.5f;
	private static final float errorTolerance=0.0001f;
	private VolumeControl mockVolumeControl;
	private VolumeControlMuter testee;

	@Before
	public void setup()
	{
		mockVolumeControl=new NullVolumeControl(mockMinimumVolume, mockMaximumVolume, startingMockVolume);
		testee=new VolumeControlMuter(mockVolumeControl);
	}

	@Test
	public void isMutedEqualsFalse()
	{
		Assert.assertFalse(testee.isMuted());
	}

	@Test
	public void muteUnmute()
	{
		testee.mute();
		Assert.assertTrue(testee.isMuted());

		testee.unmute();
		Assert.assertFalse(testee.isMuted());
		Assert.assertEquals(startingMockVolume, mockVolumeControl.getVolume(), errorTolerance);
	}
}
