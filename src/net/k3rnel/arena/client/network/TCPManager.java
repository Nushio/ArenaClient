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
package net.k3rnel.arena.client.network;

import java.awt.EventQueue;
import java.io.IOException;

import net.k3rnel.arena.client.GameClient;
import net.k3rnel.arena.client.network.NetworkProtocols.LoginData;
import net.k3rnel.arena.client.network.NetworkProtocols.RegistrationData;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Handles packets received from players over TCP
 * @author Nushio
 * @author HeikaHaku
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
				java.util.Properties prop = net.k3rnel.arena.client.backend.Translator.getInstance().translateText("status");
				if(object instanceof LoginData){
					LoginData data = (LoginData)object;
					if(data.state == 0) {
						GameClient.messageDialog((String)(prop.get("login"+data.state)), GameClient.getInstance().getDisplay());
						//Login.
					}
					else if(data.state > 0)
						GameClient.messageDialog((String)(prop.get("login"+data.state)), GameClient.getInstance().getDisplay());
					else
						GameClient.messageDialog(((String)(prop.get("logindefault"))).replaceFirst("{num}",data.state+""), GameClient.getInstance().getDisplay());
					System.out.println(data.state);
					m_game.getLoginScreen().setVisible(false);
					m_game.getLoadingScreen().setVisible(false);
					m_game.setPlayerId(0);//TODO: Save player id
					m_game.getUi().setVisible(true);
					m_game.getUi().getChat().setVisible(true);
	                m_game.getTimeService().setTime(data.hours,data.minutes);
				} else if(object instanceof RegistrationData){
					RegistrationData data = (RegistrationData)object;
					System.out.println(data.state);
					if(data.state == 0) {
						GameClient.messageDialog((String)(prop.get("register"+data.state)), GameClient.getInstance().getDisplay());
						//Login.
					}
					else if(data.state > 0)
						GameClient.messageDialog((String)(prop.get("register"+data.state)), GameClient.getInstance().getDisplay());
					else
						GameClient.messageDialog(((String)(prop.get("registerdefault"))).replaceFirst("{num}",data.state+""), GameClient.getInstance().getDisplay());
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
