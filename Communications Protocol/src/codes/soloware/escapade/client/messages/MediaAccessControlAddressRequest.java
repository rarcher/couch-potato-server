/*
 * Copyright 2014-2016 Ryan Archer
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
package codes.soloware.escapade.client.messages;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import codes.soloware.escapade.data.server.MediaAccessControlAddress;
import codes.soloware.escapade.server.api.Request;
/**
 * Pre-rename version of {@link codes.soloware.couchpotato.client.messages.MediaAccessControlAddressRequest}. Retained
 * for compatibility reasons.
 *
 * @deprecated		superseded by {@link codes.soloware.couchpotato.client.messages.MediaAccessControlAddressRequest}
 */
@Deprecated
public class MediaAccessControlAddressRequest implements Request
{
	private static final Logger logger=LoggerFactory.getLogger(MediaAccessControlAddressRequest.class);
	private static final long serialVersionUID=1L;
	private final String internetProtocolAddress;
	/**
	 * Constructor for use by Kryo during deserialization. Value set for {@link #internetProtocolAddress} is
	 * meaningless, as it will be overwritten by the serialization engine.
	 */
	@SuppressWarnings("unused")
	private MediaAccessControlAddressRequest()
	{
		internetProtocolAddress="";
	}

	public MediaAccessControlAddressRequest(final InetAddress internetProtocolAddress)
	{
		if (internetProtocolAddress==null)
			throw new NullPointerException("Given IP address is null.");
		this.internetProtocolAddress=internetProtocolAddress.getHostAddress();
	}

	@Override
	public Serializable generateResponse()
	{
		try
		{
			return new MediaAccessControlAddress(NetworkInterface.getByInetAddress(
					InetAddress.getByName(internetProtocolAddress)).getHardwareAddress());
		}
		catch (final NullPointerException noSuchNetworkInterface)
		{
			logger.error("This machine does not have any network interface with the IP address {}.",
					internetProtocolAddress);
		}
		catch (final IOException error)
		{
			logger.error("An unknown error occurred while looking up the MAC address for {}.",
					internetProtocolAddress, error);
		}
		return null;
	}
}
