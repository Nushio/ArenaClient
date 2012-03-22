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

import net.k3rnel.arena.client.GameClient;
import net.k3rnel.arena.client.backend.entity.Player.Direction;
import net.k3rnel.arena.client.network.NetworkProtocols.LoginData;
import net.k3rnel.arena.client.network.NetworkProtocols.RegistrationData;
import net.k3rnel.arena.client.utils.Hasher;

import com.esotericsoftware.kryonet.Connection;

/**
 * Generates packets and sends them to the server
 * @author shadowkanji
 * @author Nushio
 *
 */
public class PacketGenerator {
	private Connection m_tcpConnection;
	//private long m_lastMovement = 0;
	
	// Used when attempting to update passwords with old hash method to the new method
	private boolean updatePasswordHashMethod = false;
	private String lastUsername;
	private String lastPassword;
		
	/**
	 * Sets the TCP session
	 * @param s
	 */
	public void setTcpConnection(Connection conn) {
		m_tcpConnection = conn;
	}
	
	/**
	 * Returns the TCP session
	 * @return
	 */
	public Connection getTcpConnection() {
		return m_tcpConnection;
	}
	
	/**
	 * Sends a login packet to server and chat server
	 * @param username
	 * @param password
	 */
	public void login(String username, String password) {
		// store values in case we need to attempt to update to the salted hashes
		this.lastUsername = username;
		this.lastPassword = password;
		int language = 0;
		if(GameClient.getLanguage().equalsIgnoreCase("english")) {
			language = 0;
		} else if(GameClient.getLanguage().equalsIgnoreCase("portuguese")) {
			language = 1;
		} else if(GameClient.getLanguage().equalsIgnoreCase("italian")) {
			language = 2;
		} else if(GameClient.getLanguage().equalsIgnoreCase("french")) {
			language = 3;
		} else if(GameClient.getLanguage().equalsIgnoreCase("finnish")) {
			language = 4;
		} else if(GameClient.getLanguage().equalsIgnoreCase("spanish")) {
			language = 5;
		} else if(GameClient.getLanguage().equalsIgnoreCase("dutch")) {
			language = 6;
		} else if(GameClient.getLanguage().equalsIgnoreCase("german")) {
			language = 7;
		}
		LoginData data = new LoginData();
		data.username = username;
		data.password = getPasswordHash(username, password);
		data.language = language;
		data.force = false;
		m_tcpConnection.sendTCP(data);
	}
	
	/**
	 * Sends a registration packet
	 * @param username
	 * @param password
	 * @param email
	 * @param dob
	 * @param starter
	 */
	public void register(
			String username,
			String password,
			String email) {
		RegistrationData data = new RegistrationData();
		data.username = username;
		data.password = getPasswordHash(username, password);
		data.email = email;
		m_tcpConnection.sendTCP(data);
	}
	
	/**
	 * Sends a password change packet
	 * @param username
	 * @param newPassword
	 * @param oldPassword
	 */
	public void changePassword(String username, String newPassword, String oldPassword) {
//		m_tcpConnection.write("c" + username + "," + (getPasswordHash(username, newPassword)) + "," + (getPasswordHash(username, oldPassword)));
	}
	
	/**
	 * Sends a movement packet
	 * @param d
	 */
	public void move(Direction d) {
		switch(d) {
		case Down:
//			m_tcpConnection.write("D");
			break;
		case Up:
//			m_tcpConnection.write("U");
			break;
		case Left:
//			m_tcpConnection.write("L");
			break;
		case Right:
//			m_tcpConnection.write("R");
			break;
		}
	}
	
	/**
	 * Returns whether or not we are in the process of trying to update their password hash from the old method to the new one.
	 * @return
	 */
	public boolean isUpdatingHashMethod() {
		return updatePasswordHashMethod;
	}
	
	/**
	 * Resets values after attempting to update a password hash
	 */
	public void endUpdateHashMethod() {
		// ended attempt to update their password hash, reset values to default
		updatePasswordHashMethod = false;
		lastUsername = "";
		lastPassword = "";
	}
	
	/**
	 * Returns the username last attempted to log on as (used to update hashes)
	 * @return
	 */
	public String getLastUsername() {
		return lastUsername;
	}
	
	/**
	 * Returns the password last used during attempt to log on (used to update hashes)
	 * @return
	 */
	public String getLastPassword() {
		return lastPassword;
	}
	
	/**
	 * Returns the hashed password
	 * @param password
	 * @return
	 */
	private String getPasswordHash(String user, String password) {
	    return new Hasher().SHA1(new Hasher().SHA1(user.toLowerCase().trim())+new Hasher().SHA1(password.trim()));
	}
}
