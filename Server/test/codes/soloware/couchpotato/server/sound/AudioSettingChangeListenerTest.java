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

import com.esotericsoftware.kryonet.Connection;

import codes.soloware.couchpotato.server.api.MuteControl;
import codes.soloware.couchpotato.server.api.MuteSettingChange;
import codes.soloware.couchpotato.server.api.VolumeControl;
import codes.soloware.couchpotato.server.api.VolumeSettingChange;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {@link AudioSettingChangeListener} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class AudioSettingChangeListenerTest
{
	private Connection mockConnection;
	private VolumeControl mockVolume;
	private AudioControlGroup mockControls;

	@Before
	public void setup()
	{
		mockConnection=Mockito.mock(Connection.class);
		mockVolume=Mockito.mock(VolumeControl.class);

		mockControls=Mockito.mock(AudioControlGroup.class);
		Mockito.when(mockControls.getVolumeControl()).thenReturn(mockVolume);
	}

	@Test
	public void implementVolumeSettingChangeSuccessfully()
	{
		final VolumeSettingChange mockChange=Mockito.mock(VolumeSettingChange.class);

		new AudioSettingChangeListener(mockControls).received(mockConnection, mockChange);
		Mockito.verify(mockChange).implement(ArgumentMatchers.any(VolumeControl.class));
	}

	@Test
	public void failToImplementVolumeSettingChange()
	{
		final VolumeSettingChange mockChange=Mockito.mock(VolumeSettingChange.class);
		Mockito.doThrow(new RuntimeException("Test exception.")).when(mockChange)
				.implement(ArgumentMatchers.any(VolumeControl.class));

		new AudioSettingChangeListener(mockControls).received(mockConnection, mockChange);
	}

	@Test
	public void implementMuteSettingChangeSuccessfully()
	{
		final MuteSettingChange mockChange=Mockito.mock(MuteSettingChange.class);

		final MuteControl mockMute=Mockito.mock(MuteControl.class);
		Mockito.when(mockControls.getMuteControl()).thenReturn(mockMute);

		new AudioSettingChangeListener(mockControls).received(mockConnection, mockChange);
		Mockito.verify(mockChange).implement(ArgumentMatchers.any(MuteControl.class));
	}

	@Test
	public void implementMuteSettingChangeSuccessfullyOnVirtualMute()
	{
		final MuteSettingChange mockChange=Mockito.mock(MuteSettingChange.class);

		new AudioSettingChangeListener(mockControls).received(mockConnection, mockChange);
		Mockito.verify(mockChange).implement(ArgumentMatchers.any(MuteControl.class));
	}

	@Test
	public void failToImplementMuteSettingChange()
	{
		final MuteSettingChange mockChange=Mockito.mock(MuteSettingChange.class);
		Mockito.doThrow(new RuntimeException("Test exception.")).when(mockChange)
				.implement(ArgumentMatchers.any(MuteControl.class));

		new AudioSettingChangeListener(mockControls).received(mockConnection, mockChange);
	}
}
