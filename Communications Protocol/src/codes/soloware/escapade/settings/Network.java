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
package codes.soloware.escapade.settings;

import com.esotericsoftware.kryo.Kryo;
/**
 * Pre-rename version of {@link codes.soloware.couchpotato.settings.Network}. Retained for compatibility reasons.
 *
 * @deprecated		superseded by {@link codes.soloware.couchpotato.settings.Network}
 */
@Deprecated
public final class Network
{
	/**
	 * Default port number to use for network connections. Value is {@value}.
	 */
	public static final int defaultPort=4198;
	/**
	 * <p>
	 * Registers message classes with the given {@link Kryo} serialization engine.
	 * </p>
	 * <p>
	 * KryoNet will not properly serialize or transmit objects unless:
	 * </p>
	 * <ul>
	 * <li>All serialized classes are registered prior to serialization.</li>
	 * <li>All serialized classes are registered in exactly the same order on both the client and server.</li>
	 * </ul>
	 *
	 * @param in		serialization engine to register message classes in
	 */
	public static void registerMessages(final Kryo in)
	{
		codes.soloware.couchpotato.settings.Network.registerMessages(in);
	}
	/**
	 * This class should not be instantiated, ever.
	 */
	private Network()
	{
	}
}
