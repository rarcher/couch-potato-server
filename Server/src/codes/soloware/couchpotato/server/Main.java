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
package codes.soloware.couchpotato.server;

import java.awt.AWTException;
import java.io.IOException;
import java.net.BindException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryonet.Server;

import codes.soloware.couchpotato.server.input.InputEventListener;
import codes.soloware.couchpotato.server.input.LoggedEventQueue;
import codes.soloware.couchpotato.server.input.RobotAdapter;
import codes.soloware.couchpotato.server.sound.AudioControlGroup;
import codes.soloware.couchpotato.server.sound.AudioSettingChangeListener;
import codes.soloware.couchpotato.server.sound.javax.MixerControlGroupFactory;
import codes.soloware.couchpotato.server.sound.javax.MixerDescription;
import codes.soloware.couchpotato.settings.Network;
/**
 * Startup class of the Couch Potato server.
 */
public class Main
{
	private static final Logger logger=LoggerFactory.getLogger(Main.class);
	private static final int mixerIndent=1;

	public static void main(final String[] args)
	{
		logger.info("Operating system is {}.", System.getProperty("os.name"));
		logger.info("Operating system version is {}.", System.getProperty("os.version"));
		logger.info("Operating system architecture is {}.", System.getProperty("os.arch"));
		logger.info("JVM version is {}.", System.getProperty("java.version"));
		logger.info("JVM vendor is {}.", System.getProperty("java.vendor"));
		logger.info("JVM installation directory is \"{}\".", System.getProperty("java.home"));

		Server server=null;
		try
		{
			server=new Server();
			Network.registerMessages(server.getKryo());
			server.addListener(new RequestListener());
			try
			{
				final MixerControlGroupFactory factory=new MixerControlGroupFactory();
				final StringBuilder log=new StringBuilder();
				if (logger.isInfoEnabled())
					log.append("Audio system layout:");
				for (final Mixer.Info info : AudioSystem.getMixerInfo())
				{
					final Mixer mixer=AudioSystem.getMixer(info);
					for (final AudioControlGroup output : factory.construct(mixer))
						server.addListener(new AudioSettingChangeListener(output));
					if (logger.isInfoEnabled())
					{
						log.append(System.getProperty("line.separator"));
						log.append(new MixerDescription(mixer, mixerIndent).toString());
					}
				}
				logger.info(log.toString());
			}
			catch (final Exception soundSystemError)
			{
				logger.error("Encountered an unknown error while setting up the sound system controls.",
						soundSystemError);
			}
			server.addListener(new InputEventListener(LoggedEventQueue.decorateIfLoggingEnabled(new RobotAdapter())));
			server.bind(Network.defaultPort, Network.defaultPort);
			logger.info("Server started on port {}.", new Integer(Network.defaultPort));
			server.run();
		}
		catch (final AWTException noInput)
		{
			logger.error("The current environment does not support mouse or keyboard input.", noInput);
		}
		catch (final BindException cantBindToPort)
		{
			logger.error("The server process cannot bind to port {}. Reason: {}", new Integer(Network.defaultPort),
					cantBindToPort.getMessage());
		}
		catch (final IOException listenError)
		{
			logger.error("Encountered an unknown error while trying to open a port for incoming connections.",
					listenError);
		}
		catch (final Exception error)
		{
			logger.error("Encountered an unknown error.", error);
		}
		finally
		{
			if (server!=null)
			{
				server.close();
				server.stop();
			}
		}
	}

	private Main()
	{
	}
}
