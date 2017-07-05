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

import codes.soloware.couchpotato.server.api.MuteControl;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {link ToggleMute} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ToggleMuteTest
{
	private MuteControl mockMute;

	@Before
	public void setup()
	{
		mockMute=Mockito.mock(MuteControl.class);
	}

	@Test
	@SuppressWarnings("boxing")
	public void mute()
	{
		Mockito.when(mockMute.isMuted()).thenReturn(false);

		new ToggleMute().implement(mockMute);
		Mockito.verify(mockMute).mute();
	}

	@Test
	@SuppressWarnings("boxing")
	public void unmute()
	{
		Mockito.when(mockMute.isMuted()).thenReturn(true);

		new ToggleMute().implement(mockMute);
		Mockito.verify(mockMute).unmute();
	}
}
