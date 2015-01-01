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
package codes.soloware.escapade.server.input;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import codes.soloware.escapade.client.messages.pressables.MouseButton;
import codes.soloware.escapade.client.messages.pressables.NonCharacterKey;
import codes.soloware.escapade.server.api.EventQueue;
/**
 * An {@link EventQueue} that implements all underlying functionality using a <code>java.awt.Robot</code> object.
 */
public class RobotAdapter implements EventQueue
{
	private final Robot underlying;

	public RobotAdapter() throws AWTException
	{
		underlying=new Robot();
	}

	@Override
	public void mouseMove(final float xChange, final float yChange)
	{
		final Point location=MouseInfo.getPointerInfo().getLocation();
		underlying.mouseMove(Math.round(location.x+xChange), Math.round(location.y+yChange));
	}

	@Override
	public void press(final NonCharacterKey pressed)
	{
		underlying.keyPress(toCode(pressed));
	}

	@Override
	public void press(final char pressed) throws IllegalArgumentException
	{
		underlying.keyPress(KeyEvent.getExtendedKeyCodeForChar(pressed));
	}

	@Override
	public void press(final MouseButton pressed)
	{
		underlying.mousePress(toCode(pressed));
	}

	@Override
	public void release(final NonCharacterKey released)
	{
		underlying.keyRelease(toCode(released));
	}

	@Override
	public void release(final char released) throws IllegalArgumentException
	{
		underlying.keyRelease(KeyEvent.getExtendedKeyCodeForChar(released));
	}

	@Override
	public void release(final MouseButton released)
	{
		underlying.mouseRelease(toCode(released));
	}

	@Override
	public void rollMouseWheel(final int displacement)
	{
		underlying.mouseWheel(displacement);
	}

	private static int toCode(final MouseButton button)
	{
		switch (button)
		{
			case left:			return InputEvent.BUTTON1_MASK;
			case middle:		return InputEvent.BUTTON2_MASK;
			case right:			return InputEvent.BUTTON3_MASK;
		}
		throw new RuntimeException("The "+button.toString()+" does not have a button code mapping.");
	}

	private static int toCode(final NonCharacterKey key)
	{
		switch (key)
		{
			case backspace:				return KeyEvent.VK_BACK_SPACE;
			case alt:					return KeyEvent.VK_ALT;
			case ctrl:					return KeyEvent.VK_CONTROL;
			case shift:					return KeyEvent.VK_SHIFT;
			case capsLock:				return KeyEvent.VK_CAPS_LOCK;
			case insert:				return KeyEvent.VK_INSERT;
			case numLock:				return KeyEvent.VK_NUM_LOCK;
			case scrollLock:			return KeyEvent.VK_SCROLL_LOCK;
			case end:					return KeyEvent.VK_END;
			case home:					return KeyEvent.VK_HOME;
			case pageDown:				return KeyEvent.VK_PAGE_DOWN;
			case pageUp:				return KeyEvent.VK_PAGE_UP;
			case downArrow:				return KeyEvent.VK_DOWN;
			case leftArrow:				return KeyEvent.VK_LEFT;
			case rightArrow:			return KeyEvent.VK_RIGHT;
			case upArrow:				return KeyEvent.VK_UP;
			case F1:					return KeyEvent.VK_F1;
			case F2:					return KeyEvent.VK_F2;
			case F3:					return KeyEvent.VK_F3;
			case F4:					return KeyEvent.VK_F4;
			case F5:					return KeyEvent.VK_F5;
			case F6:					return KeyEvent.VK_F6;
			case F7:					return KeyEvent.VK_F7;
			case F8:					return KeyEvent.VK_F8;
			case F9:					return KeyEvent.VK_F9;
			case F10:					return KeyEvent.VK_F10;
			case F11:					return KeyEvent.VK_F11;
			case F12:					return KeyEvent.VK_F12;
		}
		throw new RuntimeException("The "+key.toString()+" does not have a key code mapping.");
	}
}
