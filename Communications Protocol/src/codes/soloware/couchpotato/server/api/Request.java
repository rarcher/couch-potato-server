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
package codes.soloware.couchpotato.server.api;

import java.io.Serializable;
/**
 * A request for information about the server's operating environment. The server will automatically call
 * {@link #generateResponse} on any message object it receives that implements this interface, and will forward the
 * result back to the client. Combination of the Command and Abstract Factory patterns.
 */
public interface Request extends Serializable
{
	/**
	 * Gathers the data requested by <code>this</code> and fabricates a response from it.
	 *
	 * @return		newly generated response, or <code>null</code> if no response is needed
	 */
	public Serializable generateResponse();
}
