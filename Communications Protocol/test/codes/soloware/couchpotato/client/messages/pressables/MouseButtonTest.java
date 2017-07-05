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
package codes.soloware.couchpotato.client.messages.pressables;

import codes.soloware.couchpotato.server.api.EventQueue;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {link MouseButton} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class MouseButtonTest
{
	private EventQueue mockQueue;

	@Before
	public void setup()
	{
		mockQueue=Mockito.mock(EventQueue.class);
	}

	@Test
	public void pressAllInstances()
	{
		for (final MouseButton testee : MouseButton.values())
		{
			testee.press(mockQueue);
			Mockito.verify(mockQueue).press(testee);
		}
	}

	@Test
	public void releaseAllInstances()
	{
		for (final MouseButton testee : MouseButton.values())
		{
			testee.release(mockQueue);
			Mockito.verify(mockQueue).release(testee);
		}
	}
}
