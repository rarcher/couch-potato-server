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
package codes.soloware.couchpotato.data.server;

import org.junit.Assert;
import org.junit.Test;
/**
 * A set of test cases for the {@link MediaAccessControlAddress} class.
 */
public class MediaAccessControlAddressTest
{
	private static final byte[] testAddressAsBytes={(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
	private static final String testAddressAsString="FF:FF:FF:FF:FF:FF";

	@Test
	@SuppressWarnings("static-method")
	public void bytesToString()
	{
		Assert.assertEquals(testAddressAsString, new MediaAccessControlAddress(testAddressAsBytes).toString());
	}

	@Test
	@SuppressWarnings("static-method")
	public void stringToBytes()
	{
		Assert.assertArrayEquals(testAddressAsBytes, new MediaAccessControlAddress(testAddressAsString).toByteArray());
	}

	@Test
	@SuppressWarnings("static-method")
	public void equalsIsTrue()
	{
		Assert.assertTrue(new MediaAccessControlAddress(testAddressAsBytes).equals(new MediaAccessControlAddress(testAddressAsString)));
	}

	@Test
	@SuppressWarnings("static-method")
	public void equalsIsFalse()
	{
		Assert.assertFalse(new MediaAccessControlAddress(testAddressAsBytes).equals(new MediaAccessControlAddress("00:00:00:00:00:00")));
	}

	@Test(expected=IllegalArgumentException.class)
	@SuppressWarnings({"static-method", "unused"})
	public void wrongByteArrayLength()
	{
		new MediaAccessControlAddress(new byte[0]);
	}

	@Test(expected=IllegalArgumentException.class)
	@SuppressWarnings({"static-method", "unused"})
	public void wrongStringLength()
	{
		new MediaAccessControlAddress("FF:FF");
	}

	@Test(expected=IllegalArgumentException.class)
	@SuppressWarnings({"static-method", "unused"})
	public void stringWithInvalidCharacters()
	{
		new MediaAccessControlAddress("ZZ:ZZ:ZZ:ZZ:ZZ:ZZ");
	}
}
