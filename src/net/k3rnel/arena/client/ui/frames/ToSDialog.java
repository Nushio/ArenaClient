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

import mdes.slick.sui.Frame;
import mdes.slick.sui.TextArea;
import net.k3rnel.arena.client.GameClient;
import net.k3rnel.arena.client.backend.Translator;

import org.newdawn.slick.Color;

/**
 * A window with information about the game
 * @author shadowkanji
 * @author HeikaHaku
 *
 */
public class ToSDialog extends Frame {
	private TextArea m_info;
	private Color m_bg, m_white;

	/**
	 * Default constructor
	 */
	public ToSDialog() {
		getContentPane().setX(getContentPane().getX() - 1);
		getContentPane().setY(getContentPane().getY() + 1);
		m_bg = new Color(0, 0, 0, 70);
		m_white = new Color(255, 255, 255);
		Properties translated = Translator.translate("gui");
		this.setTitle(translated.get("register.terms")+"");
		this.setLocation(128, 256);
		this.setBackground(m_bg);
		this.setResizable(false);
		
		m_info = new TextArea();
		m_info.setSize(280, 320);
		m_info.setLocation(4, 4);
		m_info.setWrapEnabled(true);
		m_info.setText(translated.get("misc.rules")+"");
		m_info.setFont(GameClient.getFontSmall());
		m_info.setBackground(m_bg);
		m_info.setForeground(m_white);
		this.add(m_info);
		
		this.setSize(288, 320);
		
		this.setVisible(false);
	}
	
	public void reloadStrings(){
		Properties translated = Translator.translate("gui");
		this.setTitle(translated.get("register.terms")+"");
		m_info.setText(translated.get("misc.rules")+"");
	}
}
