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
package net.k3rnel.arena.client.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import mdes.slick.sui.Button;
import mdes.slick.sui.Label;
import mdes.slick.sui.Window;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;
import net.k3rnel.arena.client.GameClient;
import net.k3rnel.arena.client.backend.Translator;
import net.k3rnel.arena.client.ui.frames.AboutDialog;
import net.k3rnel.arena.client.ui.frames.CharacterSelectDialog;
import net.k3rnel.arena.client.ui.frames.LanguageDialog;
import net.k3rnel.arena.client.ui.frames.LoginDialog;
import net.k3rnel.arena.client.ui.frames.RegisterDialog;
import net.k3rnel.arena.client.ui.frames.ServerDialog;
import net.k3rnel.arena.client.ui.frames.ToSDialog;

import org.newdawn.slick.BigImage;
import org.newdawn.slick.Color;

/**
 * The login screen (contains server selector, login and registration)
 * @author shadowkanji
 * @author HeikaHaku
 *
 */
public class LoginScreen extends Window {
	private Label m_bg, m_serverRev, m_clientRev;
	private ServerDialog m_select;
	private LoginDialog m_login;
	private CharacterSelectDialog m_chooseChar;
	private LanguageDialog m_lang;
	private RegisterDialog m_register;
	private AboutDialog m_about;
	private ToSDialog m_terms;
	private Button m_openAbout, m_openToS;

	private GameClient m_gameClient = GameClient.getInstance();
	/**
	 * Default constructor
	 */
	public LoginScreen() {
		String respath = System.getProperty("res.path");
		if(respath==null)
			respath="";
		try {
			Properties translated = new Properties();
			translated = Translator.translate("gui");
			/*
			 * Load the background image
			 */
			BigImage img = new BigImage(respath + "res/k3rnel_normal.png", BigImage.FILTER_LINEAR	);
			img = (BigImage) img.getScaledCopy( (float)GameClient.getInstance().getWidth() / (float)img.getWidth());
			
			m_bg = new Label(img);
			m_bg.pack();
			m_bg.setLocation(0, 0);
			m_bg.setVisible(true);
			this.add(m_bg);
			
			/*
			 * Create the server selector container
			 */
			m_select = new ServerDialog();
			this.add(m_select);
			/*
			 * Create the language selector container
			 */
			m_lang = new LanguageDialog();
			this.add(m_lang);
			
			
			/*
			 * Create the login container
			 */
			m_login = new LoginDialog();
			this.add(m_login);
			
			m_chooseChar = new CharacterSelectDialog();
			this.add(m_chooseChar);
			
			m_register = new RegisterDialog();
			this.add(m_register);
			
			m_about = new AboutDialog();
			this.add(m_about);
			
			m_terms = new ToSDialog();
			this.add(m_terms);
			
			m_openAbout = new Button(translated.get("login.about")+"");
			m_openAbout.setSize(64, 32);
			m_openAbout.setLocation(m_gameClient.getWidth() - 64 - 8, 8);
			m_openAbout.setVisible(false);
			m_openAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAbout();
				}
			});
			this.add(m_openAbout);
			
			m_openToS = new Button(translated.get("login.tos")+"");
			m_openToS.setSize(64, 32);
			m_openToS.setLocation(m_gameClient.getWidth() - 64 - 8, 40);
			m_openToS.setVisible(false);
			m_openToS.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showToS();
				}
			});
			this.add(m_openToS);
			
			setClientRevision();
			
			m_serverRev = new Label("Server Version: ?");
			m_serverRev.setFont(GameClient.getFontSmall());
			m_serverRev.setForeground(new Color(255, 255, 255));
			m_serverRev.pack();
			m_serverRev.setLocation(m_clientRev.getX() + m_clientRev.getWidth() + 16, 
					m_clientRev.getY());
			m_serverRev.setVisible(true);
			this.add(m_serverRev);

			this.setLocation(0, 0);
			this.setSize(m_gameClient.getWidth(), m_gameClient.getHeight());
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the server version to be displayed
	 * @param rev
	 */
	public void setServerRevision(String rev) {
		m_serverRev.setText("Server Version: r" + rev);
		m_serverRev.pack();
		m_serverRev.setLocation(m_clientRev.getX() + m_clientRev.getWidth() + 16, 
				m_clientRev.getY());
	}
	
	/**
	 * Displays client version (ThinClient Version) based on rev.txt
	 * If rev.txt is not found, ? is displayed
	 */
	private void setClientRevision() {
		String path = System.getProperty("res.path");
		if(path == null || path.equalsIgnoreCase("NULL")) {
			path = "./";
		}
		File f = new File(path + "rev.txt");
		if(f.exists()) {
			try {
				Scanner s = new Scanner(f);
				m_clientRev = new Label("Client Version: r" + s.nextLine());
				m_clientRev.setFont(GameClient.getFontSmall());
				m_clientRev.setForeground(new Color(255, 255, 255));
				m_clientRev.pack();
				m_clientRev.setLocation(4, m_gameClient.getHeight() - m_clientRev.getHeight() - 8);
				this.add(m_clientRev);
			} catch (Exception e) {
				m_clientRev = new Label("Client Version: ?");
				m_clientRev.setFont(GameClient.getFontSmall());
				m_clientRev.setForeground(new Color(255, 255, 255));
				m_clientRev.pack();
				m_clientRev.setLocation(4, m_gameClient.getHeight() - m_clientRev.getHeight() - 8);
				this.add(m_clientRev);
			}
		} else {
			m_clientRev = new Label("Client Version: ?");
			m_clientRev.setFont(GameClient.getFontSmall());
			m_clientRev.setForeground(new Color(255, 255, 255));
			m_clientRev.pack();
			m_clientRev.setLocation(4, 600 - m_clientRev.getHeight() - 8);
			this.add(m_clientRev);
		}
	}
	
	/**
	 * Shows the login dialog
	 */
	public void showLogin() {
		m_login.reloadStrings();
		m_select.setVisible(false);
		m_register.setVisible(false);
		m_chooseChar.setVisible(false);
		m_login.setVisible(true);
		m_openAbout.setVisible(true);
		m_openToS.setVisible(true);
		m_login.getLoginButton().setEnabled(true);
		m_lang.setVisible(false);
	}
	
	/**
	 * Shows the registration dialog
	 */
	public void showRegistration() {
		m_select.setVisible(false);
		m_login.setVisible(false);
		m_chooseChar.setVisible(false);
		m_openAbout.setVisible(true);
		m_openToS.setVisible(true);
		m_lang.setVisible(false);
		m_register.reloadStrings();
		m_register.setVisible(true);
		m_register.grabFocus();
	}
	
	/**
	 * Shows the server selection dialog
	 */
	public void showServerSelect() {
		m_register.setVisible(false);
		m_login.setVisible(false);
		m_chooseChar.setVisible(false);
		m_select.reloadStrings();
		m_select.setVisible(true);
		m_openAbout.setVisible(false);
		m_openToS.setVisible(false);
		m_lang.setVisible(false);
	}
	
	/**
	 * Shows the server selection dialog
	 */
	public void showLanguageSelect() {
		m_register.setVisible(false);
		m_chooseChar.setVisible(false);
		m_login.setVisible(false);
		m_select.setVisible(false);
		m_lang.setVisible(true);
		m_openAbout.setVisible(false);
		m_openToS.setVisible(false);
	}
	
	/**
	 * Shows the server selection dialog
	 */
	public void showCharacterSelect() {
		m_register.setVisible(false);
		m_chooseChar.setVisible(true);
		m_login.setVisible(false);
		m_select.setVisible(false);
		m_lang.setVisible(false);
		m_openAbout.setVisible(false);
		m_openToS.setVisible(false);
	}
	
	/**
	 * Shows about dialog
	 */
	public void showAbout() {
		m_about.reloadStrings();
		m_about.setVisible(true);
	}
	
	/**
	 * Shows the terms of service dialog
	 */
	public void showToS() {
		m_terms.reloadStrings();
		m_terms.setVisible(true);
	}
	
	/**
	 * Enables the login button
	 */
	public void enableLogin() {
		m_login.getLoginButton().setEnabled(true);
	}
	
	 /**
     * Returns the register screen
     * @return
     */
	public RegisterDialog getRegistration() {
		return m_register;
	}
	/**
	 * Logs the user with current user and pass, this way they don't have to click "Login". 
	 * @return
	 */
	public void enterKeyDefault() {
		if (!m_lang.isVisible()){
			if(m_select.isVisible()){
				m_select.goServer();
			}else{
				m_login.goLogin();
			}
		}
	}
	
	/**
     * Tabs on Login for easy login. Redundant?
     * @return
     */
	public void tabKeyDefault() {
		if(m_register.isActive()){
			m_register.goToNext();
		}else{
			m_login.goToPass();
		}
	}
}
