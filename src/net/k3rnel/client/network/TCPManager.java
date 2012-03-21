/**
 * This file is part of Distro Wars (Client).
 * 
 *  Distro Wars (Client) is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  Distro Wars (Client) is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with Distro Wars (Client).  If not, see <http://www.gnu.org/licenses/>.
*/
package net.k3rnel.client.network;

import java.awt.EventQueue;
import java.io.IOException;

import net.k3rnel.client.GameClient;
import net.k3rnel.client.network.NetworkProtocols.LoginData;
import net.k3rnel.client.network.NetworkProtocols.RegistrationData;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Handles packets received from players over TCP
 * @author Nushio
 *
 */
public class TCPManager {
		
	private String host;
	private Client client;
	private GameClient m_game;
	
	/**
	 * Constructor
	 * @param login
	 * @param logout
	 */
	public TCPManager(String server,GameClient game)  throws IOException {
        this.m_game = game;
		this.client = new Client();
		this.host = server;
		client.start();
		NetworkProtocols.register(client);
				
		//This allows us to get incoming classes and all. 
		client.addListener(new Listener(){
			public void connected (Connection connection) {
			}
			public void received (Connection connection, Object object) {
				if(object instanceof LoginData){
					LoginData data = (LoginData)object;
					System.out.println(data.state);
					m_game.getLoginScreen().setVisible(false);
					m_game.getLoadingScreen().setVisible(false);
					m_game.setPlayerId(0);//TODO: Display player id
					m_game.getUi().setVisible(true);
					m_game.getUi().getChat().setVisible(true);
//	                m_game.getTimeService().setTime(Integer.parseInt(details[1].substring(0, 2)), 
//	                        Integer.parseInt(details[1].substring(2)));
				} else if(object instanceof RegistrationData){
					RegistrationData data = (RegistrationData)object;
					System.out.println(data.state);
					m_game.getLoadingScreen().setVisible(false);
					m_game.getLoginScreen().showLogin();
				}
			}

			public void disconnected (Connection connection) {
				EventQueue.invokeLater(new Runnable() {
					public void run () {
					}
				});
			}
			
		});
		client.connect(5000, host, NetworkProtocols.tcp_port);
	}
	// This holds per connection state.
	static class PlayerConnection extends Connection {
		public String name; //Default is IP
	}
	public Client getClient(){
		return client;
	}
}