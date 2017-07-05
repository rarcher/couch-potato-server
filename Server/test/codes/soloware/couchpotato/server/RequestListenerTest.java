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
package codes.soloware.couchpotato.server;

import java.io.Serializable;

import com.esotericsoftware.kryonet.Connection;

import codes.soloware.couchpotato.server.api.Request;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A set of test cases for the {@link RequestListener} class.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class RequestListenerTest
{
	private Connection mockConnection;
	private Request mockRequest;
	private RequestListener testee;

	@Before
	public void setup()
	{
		mockConnection=Mockito.mock(Connection.class);
		mockRequest=Mockito.mock(Request.class);
		testee=new RequestListener();
	}

	@Test
	public void replySuccessfully()
	{
		final Serializable mockResponse=Mockito.mock(Serializable.class);

		Mockito.when(mockRequest.generateResponse()).thenReturn(mockResponse);
		testee.received(mockConnection, mockRequest);
		Mockito.verify(mockConnection).sendTCP(mockResponse);
	}

	@Test
	public void processSuccessfullyWithoutReply()
	{
		testee.received(mockConnection, mockRequest);
		Mockito.verify(mockConnection, Mockito.never()).sendTCP(null);
	}

	@Test
	public void failToProcess()
	{
		Mockito.doThrow(new RuntimeException("Test exception.")).when(mockRequest).generateResponse();
		testee.received(mockConnection, mockRequest);
	}
}
