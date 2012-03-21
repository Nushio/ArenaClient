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

import mdes.slick.sui.Display;
import mdes.slick.sui.Frame;

import net.k3rnel.arena.client.GameClient;
import net.k3rnel.arena.client.ui.frames.ChatDialog;
import net.k3rnel.arena.client.ui.frames.NPCSpeechFrame;
import net.k3rnel.arena.client.ui.frames.TownMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/**
 * The main ui on screen
 * @author shadowkanji
 * @author ZombieBear
 *
 */
public class Ui extends Frame {
	private static Ui m_instance;
	private Display m_display;
    private boolean m_isOption;
	private TownMap m_map;
	private ChatDialog m_chat;
	private NPCSpeechFrame m_speechFrame;
	
	/**
	 * Default constructor
	 */
	public Ui(Display display) {
		m_instance = this;
		getContentPane().setX(getContentPane().getX() - 1);
		getContentPane().setY(getContentPane().getY() + 1);
		this.setSize(800, 66);
		this.setLocation(0, -getTitleBar().getHeight());
		this.setBackground(new Color(0, 0, 0, 75));
		this.setResizable(false);
		this.setDraggable(false);
		
	
		m_display = display;
		
		m_map = new TownMap();
		m_map.setAlwaysOnTop(true);
		
		m_map.setVisible(false);
		m_display.add(m_map);
		
		startButtons();

		
		m_chat = new ChatDialog();
		
		this.add(GameClient.getInstance().getTimeService());
		GameClient.getInstance().getTimeService().setX(745);
		this.getTitleBar().setVisible(false);

		m_chat.setLocation(0, GameClient.getInstance().getDisplay().getHeight() - m_chat.getHeight());
		m_display.add(m_chat);
		m_display.add(this);
	}
	
	/**
	 * Returns the map
	 * @return the map
	 */
	public TownMap getMap() {
		return m_map;
	}

	/**
	 * Returns the instance
	 * @return the instance
	 */
	public static Ui getInstance(){
		return m_instance;
	}
	
	/**
	 * Starts the HUD buttons
	 */
	public void startButtons(){
		
	}
	
	/**
	 * Adds a message to its appropriate chat window
	 * @param m
	 */
	public void messageReceived(String m) {
		switch(m.charAt(0)) {
		case 'n':
			//NPC Speeech
			String [] speech = m.substring(1).split(",");
			String result = "";
			for(int i = 0; i < speech.length; i++) {
				result += GameClient.getInstance().getMapMatrix().getSpeech(Integer.parseInt(speech[i])) + "/n";
			}
			talkToNPC(result);
			break;
		case 't':
			//Trade npc speech
			talkToNPC(m.substring(1));
			break;
		// The following is for in case the chat server is down...
		case 'l':
			//Local Chat
			if(m.substring(1).toLowerCase().contains(GameClient.getInstance().getOurPlayer().getUsername().toLowerCase())&&!m.substring(1).contains("<"+GameClient.getInstance().getOurPlayer().getUsername()+">"))
				m_chat.addChatLine(0,"!"+m.substring(1));
			else
				m_chat.addChatLine(0, m.substring(1));
			break;
		case 'p':
			//Private Chat
			String [] details = m.substring(1).split(",");
			m_chat.addChat(details[0], true);
			m_chat.addWhisperLine(details[0], details[1]);
			break;
		case 's':
			//Server Announcement
			m_chat.addSystemMessage(m.substring(1));
			break;
		}
	}
	
	/**
	 * Sets all components visible/invisible
	 * @param b
	 */
	public void setAllVisible(boolean b) {
		this.setVisible(b);
		m_chat.setVisible(b);
	}
    
    /**
     * ????
     * @return
     */
    public boolean isOption() {
            return m_isOption;
    }
        
    /**
     * Toggles the Chat Pane
     */
    public void toggleChat(){
    	if (m_chat.isVisible()) {
    		m_chat.setVisible(false);
		} else{ 
			if(m_chat==null)
				m_chat = new ChatDialog();
			m_chat.setLocation(0, GameClient.getInstance().getDisplay().getHeight() - m_chat.getHeight());
			m_chat.setVisible(true);
		}	
    }
    
    /**
     * Starts to talk to an NPC
     * @param speech
     * @throws SlickException
     */
    public void talkToNPC(String speech){
		m_speechFrame = new NPCSpeechFrame(speech);
		getDisplay().add(m_speechFrame);
//		if (speech.startsWith("*"))
//			useCreatureStorageBox("");
	}
    
    /**
	 * Nulls m_speechFrame
	 */
	public void nullSpeechFrame() {
		getDisplay().remove(m_speechFrame);
		m_speechFrame = null;
	}
	
	/**
	 * Returns the NPC Speech Frame
	 * @return the NPC Speech Frame
	 */
	public NPCSpeechFrame getNPCSpeech(){
		return m_speechFrame;
	}

	/**
	 * Returns the Chat Dialog
	 * @return the Chat Dialog
	 */
	public ChatDialog getChat(){
		return m_chat;
	}	
}
 
