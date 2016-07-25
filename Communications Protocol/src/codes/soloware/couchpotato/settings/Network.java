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
package codes.soloware.couchpotato.settings;

import com.esotericsoftware.kryo.Kryo;

import codes.soloware.couchpotato.client.messages.AdjustVolumeByPercentage;
import codes.soloware.couchpotato.client.messages.MediaAccessControlAddressRequest;
import codes.soloware.couchpotato.client.messages.MouseMovement;
import codes.soloware.couchpotato.client.messages.PressableStateChange;
import codes.soloware.couchpotato.client.messages.ScrollWheelRoll;
import codes.soloware.couchpotato.client.messages.ToggleMute;
import codes.soloware.couchpotato.client.messages.pressables.CharacterKey;
import codes.soloware.couchpotato.client.messages.pressables.MouseButton;
import codes.soloware.couchpotato.client.messages.pressables.NonCharacterKey;
import codes.soloware.couchpotato.data.server.MediaAccessControlAddress;
/**
 * Defines network-related configuration data that are shared between the client and server.
 */
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
	@SuppressWarnings("deprecation")
	public static void registerMessages(final Kryo in)
	{
		in.register(CharacterKey.class);
		in.register(NonCharacterKey.class);
		in.register(MouseButton.class);
		in.register(MouseMovement.class);
		in.register(PressableStateChange.class);
		in.register(ScrollWheelRoll.class);
		in.register(AdjustVolumeByPercentage.class);
		in.register(ToggleMute.class);
		in.register(MediaAccessControlAddress.class);
		in.register(MediaAccessControlAddressRequest.class);
		in.register(byte[].class);

		// deprecated classes, included for compatibility reasons
		in.register(codes.soloware.escapade.client.messages.pressables.CharacterKey.class);
		in.register(codes.soloware.escapade.client.messages.pressables.NonCharacterKey.class);
		in.register(codes.soloware.escapade.client.messages.pressables.MouseButton.class);
		in.register(codes.soloware.escapade.client.messages.MouseMovement.class);
		in.register(codes.soloware.escapade.client.messages.PressableStateChange.class);
		in.register(codes.soloware.escapade.client.messages.ScrollWheelRoll.class);
		in.register(codes.soloware.escapade.client.messages.AdjustVolumeByPercentage.class);
		in.register(codes.soloware.escapade.client.messages.ToggleMute.class);
		in.register(codes.soloware.escapade.data.server.MediaAccessControlAddress.class);
		in.register(codes.soloware.escapade.client.messages.MediaAccessControlAddressRequest.class);
	}
	/**
	 * This class should not be instantiated, ever.
	 */
	private Network()
	{
	}
}
