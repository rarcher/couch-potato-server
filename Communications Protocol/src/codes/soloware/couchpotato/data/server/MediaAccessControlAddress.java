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
package codes.soloware.couchpotato.data.server;

import java.io.Serializable;
import java.util.Arrays;
/**
 * A media access control (MAC) address. Instances are immutable and are designed to travel over the network.
 */
public final class MediaAccessControlAddress implements Serializable
{
	private static final long serialVersionUID=1L;
	private static final int mac48Length=6;
	private static final int eui64Length=8;
	private final byte[] composition;
	/**
	 * Constructor for use by Kryo during deserialization. Value set for {@link #composition} is meaningless, as it will
	 * be overwritten by the serialization engine.
	 */
	@SuppressWarnings("unused")
	private MediaAccessControlAddress()
	{
		composition=null;
	}

	public MediaAccessControlAddress(final byte[] composition)
	{
		if ((composition.length!=mac48Length)&&(composition.length!=eui64Length))
			throw new IllegalArgumentException("Given byte array is not a valid MAC address.");
		this.composition=Arrays.copyOf(composition, composition.length);
	}

	public MediaAccessControlAddress(final String hexadecimal)
	{
		final String[] hexBytes=hexadecimal.split(":");
		if ((hexBytes.length!=mac48Length)&&(hexBytes.length!=eui64Length))
			throw new IllegalArgumentException("Given string is not a valid human-readable MAC address.");

		composition=new byte[hexBytes.length];
		for (int loop=0; loop<hexBytes.length; loop++)
			composition[loop]=Integer.decode("0X"+hexBytes[loop]).byteValue();
	}

	@Override
	public boolean equals(final Object object)
	{
		if (object==null)
			return false;
		if (this==object)
			return true;
		if (getClass()!=object.getClass())
			return false;
		if (!Arrays.equals(composition, ((MediaAccessControlAddress)object).composition))
			return false;
		return true;
	}

	@Override
	public int hashCode()
	{
		final int prime=31;
		return prime+Arrays.hashCode(composition);
	}

	public byte[] toByteArray()
	{
		return Arrays.copyOf(composition, composition.length);
	}

	@Override
	public String toString()
	{
		final StringBuilder product=new StringBuilder();
		product.append(String.format("%02X", Byte.valueOf(composition[0])));
		for (int loop=1; loop<composition.length; loop++)
			product.append(String.format(":%02X", Byte.valueOf(composition[loop])));
		return product.toString();
	}
}
