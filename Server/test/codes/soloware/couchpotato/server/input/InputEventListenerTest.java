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

import com.esotericsoftware.kryonet.Connection;

import codes.soloware.couchpotato.server.api.EventQueue;
import codes.soloware.couchpotato.server.api.InputEvent;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {@link InputEventListener} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class InputEventListenerTest
{
	private Connection mockConnection;
	private InputEvent mockEvent;
	private EventQueue mockQueue;
	private InputEventListener testee;

	@Before
	public void setup()
	{
		mockConnection=Mockito.mock(Connection.class);
		mockEvent=Mockito.mock(InputEvent.class);
		mockQueue=Mockito.mock(EventQueue.class);
		testee=new InputEventListener(mockQueue);
	}

	@Test
	public void enqueueSuccessfully()
	{
		testee.received(mockConnection, mockEvent);
		Mockito.verify(mockEvent).enqueue(mockQueue);
	}

	@Test
	public void failToEnqueue()
	{
		Mockito.doThrow(new RuntimeException("Test exception.")).when(mockEvent).enqueue(mockQueue);
		testee.received(mockConnection, mockEvent);
	}
}
