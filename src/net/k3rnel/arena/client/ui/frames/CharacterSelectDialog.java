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

import mdes.slick.sui.Button;
import mdes.slick.sui.Frame;
import mdes.slick.sui.Label;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;
import net.k3rnel.arena.client.GameClient;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Handles the Character Selection box
 * @author Nushio
 *
 */
public class CharacterSelectDialog extends Frame {
//	private TextField m_username, m_password;
//	private Label m_selChar;
	private Label m_selChar, m_charName1, m_charName2;
//	private ArrayList<Label> m_charName;
//	private ArrayList<Button> m_char;
	private Button m_char1, m_char2;
	private Button m_leftButton, m_rightButton, m_newChar;
	private Color m_white;
	
	/**
	 * Default constructor
	 */
	public CharacterSelectDialog() {
		getContentPane().setX(getContentPane().getX() - 1);
		getContentPane().setY(getContentPane().getY() + 1);
//		List<String> translated = Translator.translate("_LOGIN");
		this.setBorderRendered(true);
		this.getTitleBar().setVisible(true);
		setSizeAndCenter();
		
		this.setBackground(new Color(0, 0, 0, 140));
		this.setDraggable(true);
		this.setResizable(true);
		
		this.reload();
		
		this.setVisible(false);
	}
	
	/**
	 * Reloads strings with language selected. 
	 */
	public void reloadStrings(){
//		List<String> translated = Translator.translate("_LOGIN");
//		m_userLabel.setText(translated.get(5));
	}
	
	/**
	 * Centers the frame
	 */
	public void setSizeAndCenter() {
		
		int height = (int) GameClient.getInstance().getDisplay().getHeight();
		int width = (int) GameClient.getInstance().getDisplay().getWidth(); 
		int frameWidth = (int) (width*0.6);
		int frameHeight = (int) (height*0.6);
		int x = (width / 2) - (frameWidth/2); 
		int y = (height / 2) - (frameHeight/2); 
		getContentPane().setX(getContentPane().getX() - 1);
		getContentPane().setY(getContentPane().getY() + 1);
		this.setBounds(x, y, frameWidth, frameHeight);
	}
	/**
	 * Centers the frame
	 */
	public void reload() {

		this.setVisible(false);
		String respath = System.getProperty("res.path");
		if(respath==null)
			respath="";
		
		int width = (int) this.getWidth();
		int height = (int) this.getHeight();
		/*
		 * Set up the components
		 */
		m_white = new Color(255, 255, 255);
		
		m_selChar = new Label("Select or Create a Character to Play!");
		m_selChar.pack();
		m_selChar.setLocation((width/2) - (m_selChar.getWidth()/2), 12);
		m_selChar.setVisible(true);
		m_selChar.setFont(GameClient.getFontSmall());
		m_selChar.setForeground(m_white);
		this.add(m_selChar);
		
		m_char1 = new Button("");
		m_char1.setSize((int)(width*0.326), (int)(height*0.4));
		try {
			m_char1.setImage(new Image(respath+"res/ui/droids/green.png", false).getScaledCopy((int)m_char1.getWidth(), (int)m_char1.getHeight()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		m_char1.setLocation((width/2)-m_char1.getWidth()-20,(float)(height*0.2));
		m_char1.setVisible(true);
		m_char1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		this.add(m_char1);
		
		m_charName1 = new Label("Nushio");
		m_charName1.pack();
		m_charName1.setLocation(m_char1.getX()+(m_char1.getWidth()/2)-m_charName1.getWidth()/2, m_char1.getY()-m_charName1.getHeight());
		m_charName1.setVisible(true);
		m_charName1.setFont(GameClient.getFontSmall());
		m_charName1.setForeground(m_white);
		this.add(m_charName1);
				
		m_char2 = new Button("");
		m_char2.setSize((int)(width*0.326), (int)(height*0.4));
		try {
			m_char2.setImage(new Image(respath+"res/ui/droids/gray.png",false).getScaledCopy((int)m_char2.getWidth(), (int)m_char2.getHeight()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		m_char2.setLocation((width/2)+20,(float)(height*0.2));
		m_char2.setVisible(true);
		m_char2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		this.add(m_char2);
		
		m_charName2 = new Label("I/O");
		m_charName2.pack();
		m_charName2.setLocation(m_char2.getX()+(m_char2.getWidth()/2)-m_charName2.getWidth()/2, m_char2.getY()-m_charName2.getHeight());
		m_charName2.setVisible(true);
		m_charName2.setFont(GameClient.getFontSmall());
		m_charName2.setForeground(m_white);
		this.add(m_charName2);
		
		// Scrolling Button LEFT
		m_leftButton = new Button("<");
		m_leftButton.setSize((int)(height*0.1), (int)(height*0.1));
		m_leftButton.setLocation((int)(height*0.05),(float)(height*0.2)+m_char1.getHeight()/2 - m_leftButton.getHeight()/2);
		m_leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				destroyPopup();
//				int i = m_scrollIndex.get(m_curCategory) - 1;
//				m_scrollIndex.remove(m_curCategory);
//				m_scrollIndex.put(m_curCategory, i);
//				m_update = true;
				
				
			}
		});
		getContentPane().add(m_leftButton);
		
		// Scrolling Button Right
		m_rightButton = new Button(">");
		m_rightButton.setSize((int)(height*0.1), (int)(height*0.1));
		m_rightButton.setLocation(width-m_rightButton.getWidth()-(int)(height*0.05),(float)(height*0.2)+m_char2.getHeight()/2 - m_rightButton.getHeight()/2);
		m_rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				destroyPopup();
//				int i = m_scrollIndex.get(m_curCategory) + 1;
//				m_scrollIndex.remove(m_curCategory);
//				m_scrollIndex.put(m_curCategory, i);
//				m_update = true;
				
			}
		});
		getContentPane().add(m_rightButton);
		
		// Scrolling Button Right
		m_newChar = new Button("Create New Character");
		m_newChar.setSize(m_newChar.getWidth(),(int)(height*0.1));
		m_newChar.setLocation(width/2-m_newChar.getWidth()/2,(float)(height*0.76));
		m_newChar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				destroyPopup();
//				int i = m_scrollIndex.get(m_curCategory) + 1;
//				m_scrollIndex.remove(m_curCategory);
//				m_scrollIndex.put(m_curCategory, i);
//				m_update = true;
			}
		});
		getContentPane().add(m_newChar);

		this.setVisible(true);
	}
}
