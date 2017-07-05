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

import javax.sound.sampled.BooleanControl;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {@link BooleanControlAdapter} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class BooleanControlAdapterTest
{
	private BooleanControl mockControl;
	private BooleanControlAdapter testee;

	@Before
	public void setup()
	{
		mockControl=Mockito.mock(BooleanControl.class);
		Mockito.when(mockControl.getType()).thenReturn(BooleanControl.Type.MUTE);

		testee=new BooleanControlAdapter(mockControl);
	}

	@Test
	@SuppressWarnings("boxing")
	public void checkWhileMuted()
	{
		Mockito.when(mockControl.getValue()).thenReturn(true);
		Assert.assertTrue(testee.isMuted());
	}

	@Test
	@SuppressWarnings("boxing")
	public void checkWhileNotMuted()
	{
		Mockito.when(mockControl.getValue()).thenReturn(false);
		Assert.assertFalse(testee.isMuted());
	}

	@Test
	public void mute()
	{
		testee.mute();
		Mockito.verify(mockControl).setValue(true);
	}

	@Test
	public void unmute()
	{
		testee.unmute();
		Mockito.verify(mockControl).setValue(false);
	}
}
