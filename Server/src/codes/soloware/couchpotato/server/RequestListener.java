/*
 * Copyright 2014-2017 Ryan Archer
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import codes.soloware.couchpotato.server.api.Request;
/**
 * A {@link Listener} that fills {@link Request}s.
 */
public class RequestListener extends Listener
{
	private static final Logger logger=LoggerFactory.getLogger(RequestListener.class);

	public RequestListener()
	{
		logger.debug("Instantiated.");
	}

	@Override
	public void received(final Connection requester, final Object received)
	{
		if (received instanceof Request)
		{
			try
			{
				final Serializable response=((Request)received).generateResponse();
				if (response!=null)
					requester.sendTCP(response);
			}
			catch (final Exception error)
			{
				logger.error("An exception was thrown while filling a data request.", error);
			}
		}
	}
}
