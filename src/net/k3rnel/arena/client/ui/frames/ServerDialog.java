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
package net.k3rnel.arena.client.ui.frames;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import mdes.slick.sui.Button;
import mdes.slick.sui.Frame;
import mdes.slick.sui.Label;
import mdes.slick.sui.TextField;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;
import net.k3rnel.arena.client.GameClient;
import net.k3rnel.arena.client.backend.Translator;

import org.newdawn.slick.Color;

/**
 * Handles server selection
 * @author shadowkanji
 * @author HeikaHaku
 *
 */
public class ServerDialog extends Frame {
	private Button [] m_servers;
	private String [] m_host;
	private Label m_info;
	private Color m_black;
	private Button privateServer;
	private TextField privateIP;
	
	private GameClient m_gameClient = GameClient.getInstance();
	
	/**
	 * Default constructor
	 */
	public ServerDialog() {
		getContentPane().setX(getContentPane().getX() - 1);
		getContentPane().setY(getContentPane().getY() + 1);
		m_black = new Color(0, 0, 0);
		Properties translate = Translator.translate("login");
		
		this.setSize(316, 280);
		this.setLocation((m_gameClient.getWidth() / 2) - 160, 280);
		this.setTitle(translate.get("server")+"");
		this.setBackground(new Color(0, 0, 0, 140));
		this.getTitleBar().setForeground(m_black);
		this.setDraggable(false);
		this.setResizable(false);
		this.getTitleBar().getCloseButton().setVisible(false);
		
		/*
		 * Create the info label
		 */
		m_info = new Label(translate.get("accnote")+"");
		m_info.pack();
		m_info.setLocation(24, 8);
		m_info.setForeground(new Color(255, 255, 255));
		this.add(m_info);
		
		/*
		 * Create all the server buttons
		 */
		String respath = System.getProperty("res.path");
		if(respath==null)
			respath="";
		try {
			m_servers = new Button[5];
			m_host = new String[5];
			InputStream stream;
			try {
//				URL url = new URL("http://pokeglobal.sourceforge.net/servers.txt");
//				stream = url.openStream();
				stream = new FileInputStream(respath+"res/servers.txt");
			} catch (Exception e) {
				stream = new FileInputStream(respath+"res/servers.txt");
			}
	        BufferedReader in = new BufferedReader(new InputStreamReader(stream));

			
			m_servers[0] = new Button(in.readLine());
			m_servers[0].setSize(280, 24);
			m_servers[0].setLocation(16, 32);
			m_servers[0].setVisible(true);
			m_servers[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[0]);
				}
			});
			this.add(m_servers[0]);
			m_host[0] = in.readLine();
			
			m_servers[1] = new Button(in.readLine());
			m_servers[1].setSize(280, 24);
			m_servers[1].setLocation(16, 64);
			m_servers[1].setVisible(true);
			m_servers[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[1]);
				}
			});
			this.add(m_servers[1]);
			m_host[1] = in.readLine();
			
			m_servers[2] = new Button(in.readLine());
			m_servers[2].setSize(280, 24);
			m_servers[2].setLocation(16, 96);
			m_servers[2].setVisible(true);
			m_servers[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[2]);
				}
			});
			this.add(m_servers[2]);
			m_host[2] = in.readLine();
			
			m_servers[3] = new Button(in.readLine());
			m_servers[3].setSize(280, 24);
			m_servers[3].setLocation(16, 128);
			m_servers[3].setVisible(true);
			m_servers[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[3]);
				}
			});
			this.add(m_servers[3]);
			m_host[3] = in.readLine();
			
			m_servers[4] = new Button(in.readLine());
			m_servers[4].setSize(280, 24);
			m_servers[4].setLocation(16, 160);
			m_servers[4].setVisible(true);
			m_servers[4].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GameClient.setHost(m_host[4]);
				}
			});
			this.add(m_servers[4]);
			m_host[4] = in.readLine();
			
			/*
			 * Finally, check which servers don't exist and disable their buttons
			 */
			for(int i = 0; i < 5; i++) {
				if(m_host[i].equalsIgnoreCase("-")) {
					m_servers[i].setEnabled(false);
					m_host[i] = "";
				}
				m_servers[i].setForeground(m_black);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		privateIP = new TextField();
		privateIP.setLocation(16, 204);
		privateIP.setSize(128, 24);
		this.add(privateIP);
		
		privateServer = new Button();
		privateServer.setText(translate.get("privserv")+"");
		privateServer.setSize(128, 24);
		privateServer.setLocation(168, 204);
		privateServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				GameClient.setHost(getPrivateServer());
			}
		});
		this.add(privateServer);
		
		this.setVisible(false);
	}
	
	public String getPrivateServer() {
		if(privateIP.getText().length() > 0)
			return privateIP.getText();
		else
			return "localhost";
	}
	public void goServer(){
		 if(privateIP.getText().length() > 0)
			GameClient.setHost(getPrivateServer());
	}
	public void reloadStrings(){
		Properties translate = Translator.translate("login");
		this.setTitle(translate.get("server")+"");
		m_info.setText(translate.get("accnote")+"");
		privateServer.setText(translate.get("privserv")+"");
	}
}
