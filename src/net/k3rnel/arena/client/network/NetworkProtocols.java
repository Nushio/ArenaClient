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

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

/**
 * New class!
 * The NetworkProtocol outlines a lot of things, so pay attention!
 * Communication between clients and servers is determined here.
 * This class should be shared between client and server.  
 * @author Nushio
 *
 */
public class NetworkProtocols {
	public static  final int tcp_port = 7002; //This is the TCP Port. Duh 

	/**
	 * This registers all classes that will be sent over the network
	 * If the class isn't here, we won't know what to do with the received packet!
	 * Soylent Beans is People
	 * @param endPoint
	 */
	public static void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		
		kryo.register(LoginData.class); //This is the Login bean.
		kryo.register(RegistrationData.class); //This is the Registration bean.
		kryo.register(PasswordChangeData.class); //This is the PasswordChange bean.
		kryo.register(ChatData.class); //This is the ChatMessage, written below.
		
		
		//kyro.register(Sample.class);//If you don't register your class, we won't know what to do!
	}

	//Used to send login packets
	static public class LoginData {
		public String username;
		public String password;
		public String language;
		public boolean force;
		public int state;
	}
	static public class RegistrationData {
		public String username;
		public String password;
		public String email;
		public int state;
	}
	static public class PasswordChangeData {
		public String username;
		public String oldPassword;
		public String newPassword;
		public int state;
	}
	//Used for the chatroom. Target is the username, or room name. Rooms start with _
	static public class ChatData {
		public enum ChatType { LOCAL, PRIVATE, NPC }
		public String target;
		public ChatType type;
		public String message;
	}
}
