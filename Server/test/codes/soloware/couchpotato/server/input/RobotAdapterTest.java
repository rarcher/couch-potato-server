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
package codes.soloware.couchpotato.server.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import codes.soloware.couchpotato.client.messages.pressables.MouseButton;
import codes.soloware.couchpotato.client.messages.pressables.NonCharacterKey;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {@link RobotAdapter} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class RobotAdapterTest
{
	private static final int mouseDisplacement=1;
	private Robot mockRobot;
	private RobotAdapter testee;

	@Before
	public void setup()
	{
		mockRobot=Mockito.mock(Robot.class);
		testee=new RobotAdapter(mockRobot);
	}

	@Test
	public void mouseMove()
	{
		final Point location=MouseInfo.getPointerInfo().getLocation();

		testee.mouseMove(mouseDisplacement, mouseDisplacement);
		Mockito.verify(mockRobot).mouseMove(location.x+mouseDisplacement, location.y+mouseDisplacement);
	}

	@Test
	public void rollMouseWheel()
	{
		testee.rollMouseWheel(mouseDisplacement);
		Mockito.verify(mockRobot).mouseWheel(mouseDisplacement);
	}

	@Test
	public void pressAllNonCharacterKeys()
	{
		for (final NonCharacterKey key : NonCharacterKey.values())
		{
			testee.press(key);
			Mockito.verify(mockRobot).keyPress(RobotAdapter.toKeyEventCode(key));
		}
	}

	@Test
	public void releaseAllNonCharacterKeys()
	{
		for (final NonCharacterKey key : NonCharacterKey.values())
		{
			testee.release(key);
			Mockito.verify(mockRobot).keyRelease(RobotAdapter.toKeyEventCode(key));
		}
	}

	@Test
	public void pressAllMouseButtons()
	{
		for (final MouseButton button : MouseButton.values())
		{
			testee.press(button);
			Mockito.verify(mockRobot).mousePress(RobotAdapter.toInputEventCode(button));
		}
	}

	@Test
	public void releaseAllMouseButtons()
	{
		for (final MouseButton button : MouseButton.values())
		{
			testee.release(button);
			Mockito.verify(mockRobot).mouseRelease(RobotAdapter.toInputEventCode(button));
		}
	}
}
