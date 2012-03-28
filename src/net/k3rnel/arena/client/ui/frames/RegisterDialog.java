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

import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

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
 * Handles registration dialog
 * @author shadowkanji
 * @author HeikaHaku
 *
 */
public class RegisterDialog extends Frame{
	private TextField m_username, m_password, m_confirmPass, m_email, m_confirmEmail;
	private Label m_u, m_p, m_cp, m_e, m_ce, m_tos;
	private Button m_register, m_cancel, m_terms;
	private Color m_white = new Color(255, 255, 255);
	
	/**
	 * Default constructor
	 */
	public RegisterDialog() {
		getContentPane().setX(getContentPane().getX() - 1);
		getContentPane().setY(getContentPane().getY() + 1);
		Properties translated = Translator.translate("_LOGIN");
		this.setVisible(false);
		this.setSize(320, 300);
		this.setBackground(new Color(0, 0, 0, 140));
		this.setTitle(translated.get(9));
		this.getTitleBar().getCloseButton().setVisible(false);
		this.setLocation(420, 220);
		this.setResizable(false);
		this.setDraggable(false);
		
		m_u = new Label(translated.get(5));
		m_u.pack();
		m_u.setLocation(4, 4);
		m_u.setFont(GameClient.getFontSmall());
		m_u.setForeground(m_white);
		this.add(m_u);
		
		m_username = new TextField();
		m_username.setSize(128, 24);
		m_username.setLocation(4, 24);
		m_username.setVisible(true);
		m_username.grabFocus();
		this.add(m_username);
		
		m_p = new Label(translated.get(6));
		m_p.pack();
		m_p.setLocation(4, 52);
		m_p.setFont(GameClient.getFontSmall());
		m_p.setForeground(m_white);
		this.add(m_p);
		
		m_password = new TextField();
		m_password.setSize(128, 24);
		m_password.setLocation(4, 72);
		m_password.setMaskCharacter('*');
		m_password.setMaskEnabled(true);
		m_password.setVisible(true);
		this.add(m_password);
		
		m_cp = new Label(translated.get(10));
		m_cp.pack();
		m_cp.setLocation(172, 52);
		m_cp.setFont(GameClient.getFontSmall());
		m_cp.setForeground(m_white);
		this.add(m_cp);
		
		m_confirmPass = new TextField();
		m_confirmPass.setSize(128, 24);
		m_confirmPass.setLocation(172, 72);
		m_confirmPass.setMaskCharacter('*');
		m_confirmPass.setMaskEnabled(true);
		m_confirmPass.setVisible(true);
		this.add(m_confirmPass);
		
		m_e = new Label(translated.get(12));
		m_e.pack();
		m_e.setLocation(4, 102);
		m_e.setFont(GameClient.getFontSmall());
		m_e.setForeground(m_white);
		this.add(m_e);
		
		m_email = new TextField();
		m_email.setSize(128, 24);
		m_email.setLocation(4, 122);
		m_email.setVisible(true);
		this.add(m_email);
		
		m_ce = new Label(translated.get(13));
		m_ce.pack();
		m_ce.setLocation(172, 102);
		m_ce.setFont(GameClient.getFontSmall());
		m_ce.setForeground(m_white);
		this.add(m_ce);
		
		m_confirmEmail = new TextField();
		m_confirmEmail.setSize(128, 24);
		m_confirmEmail.setLocation(172, 122);
		m_confirmEmail.setVisible(true);
		this.add(m_confirmEmail);
		
		m_tos = new Label(translated.get(18));
		m_tos.pack();
		m_tos.setLocation(102, 166);
		m_tos.setFont(GameClient.getFontSmall());
		m_tos.setForeground(m_white);
		this.add(m_tos);
		
		m_terms = new Button(translated.get(19));
		m_terms.setSize(128, 24);
		m_terms.setLocation(95, 186);
		m_terms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m_terms.setEnabled(false);
			}
		});
		this.add(m_terms);
		
		m_register = new Button(translated.get(8));
		m_register.setSize(64, 24);
		m_register.setLocation(96, 228);
		m_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				register();
			}
		});
		this.add(m_register);
		
		m_cancel = new Button(translated.get(20));
		m_cancel.setSize(64, 24);
		m_cancel.setLocation(160, 228);
		m_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancel();
			}
		});
		this.add(m_cancel);
	}
	
	/**
	 * Registers the player
	 */
	private void register() {
		List<String> translated = Translator.translate("_LOGIN");
		if(m_username.getText() != null
				&& m_username.getText().length() >= 4 && m_username.getText().length() <= 12) {
			if(m_password.getText() != null & !m_password.getText().equalsIgnoreCase("")
					&& m_confirmPass.getText() != null && !m_confirmPass.getText().equalsIgnoreCase("") &&
					m_password.getText().compareTo(m_confirmPass.getText()) == 0) {
				if(m_email.getText() != null && this.isValidEmail(m_email.getText())
						&& m_confirmEmail.getText() != null && m_confirmEmail.getText().compareTo(m_email.getText()) == 0) {
						if(!m_terms.isEnabled()) {
							m_register.setEnabled(false);
							GameClient.getInstance().getLoadingScreen().setVisible(true);
							GameClient.getInstance().getPacketGenerator().register(m_username.getText(),
									m_password.getText(), m_email.getText());
					} else {
						GameClient.messageDialog(translated.get(29), GameClient.getInstance().getDisplay());
					}
				} else {
					GameClient.messageDialog(translated.get(30), GameClient.getInstance().getDisplay());
				}
			} else {
				GameClient.messageDialog(translated.get(31), GameClient.getInstance().getDisplay());
			}
		} else {
			GameClient.messageDialog(translated.get(32), GameClient.getInstance().getDisplay());
		}
	}
	
	/**
	 * Cancels the registration
	 */
	private void cancel() {
		GameClient.getInstance().getLoginScreen().showLogin();
		m_register.setEnabled(true);
	}
	
	/**
	 * Enables the registration
	 */
	public void enableRegistration() {
		m_register.setEnabled(true);
	}
	
	/**
	 * Returns true if the email is a valid email address
	 * @param email
	 * @return
	 */
	private boolean isValidEmail(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(email).matches())
                return true;
        else
                return false;
	}
	
	/**
	 * Tabs to the next field. 
	 * @param email
	 * @return
	 */
	public void goToNext() {
		if (m_username.hasFocus()) {
			m_username.releaseFocus();
			m_password.grabFocus();
		} else if (m_password.hasFocus()) {
			m_password.releaseFocus();
			m_confirmPass.grabFocus();
		} else if (m_confirmPass.hasFocus()) {
			m_confirmPass.releaseFocus();
			m_email.grabFocus();
		} else if (m_email.hasFocus()) {
			m_email.releaseFocus();
			m_confirmEmail.grabFocus();
		}else if (m_confirmEmail.hasFocus()) {
			m_confirmEmail.releaseFocus();
			m_username.grabFocus();
		}else{
			m_username.grabFocus();
		}
	}
	
	public void reloadStrings(){
		List<String> translated = Translator.translate("_LOGIN");
		this.setTitle(translated.get(9));
		m_u.setText(translated.get(5));
		m_p.setText(translated.get(6));
		m_cp.setText(translated.get(10));
		m_e.setText(translated.get(12));
		m_ce.setText(translated.get(13));
		m_tos.setText(translated.get(17));
		m_terms.setText(translated.get(18));
		m_register.setText(translated.get(8));
		m_cancel.setText(translated.get(19));
	}
}
