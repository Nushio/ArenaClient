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
package net.k3rnel.client.ui;

import mdes.slick.sui.Frame;
import mdes.slick.sui.Label;
import net.k3rnel.client.GameClient;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

/**
 * The loading screen
 * @author shadowkanji
 *
 */
public class LoadingScreen extends Frame {
	private Label m_bg;
	
	/**
	 * Default constructor
	 */
	public LoadingScreen() {
		getContentPane().setX(getContentPane().getX() - 1);
		getContentPane().setY(getContentPane().getY() + 1);
		String respath = System.getProperty("res.path");
		if(respath==null)
			respath="";
		try {
			this.setSize(GameClient.getInstance().getWidth(), GameClient.getInstance().getHeight() + 32);
			this.setBackground(new Color(255, 255, 255, 70));
			this.setLocation(0, -32);
			this.setResizable(false);
			this.getTitleBar().setVisible(false);
			
			m_bg = new Label(new Image(respath+"res/ui/loading.png", false));
			m_bg.pack();
			m_bg.setLocation((GameClient.getInstance().getWidth() / 2) - (m_bg.getWidth() / 2), 
					(GameClient.getInstance().getHeight() / 2) - (m_bg.getHeight() /2));
			m_bg.setVisible(true);
			this.add(m_bg);
			
			this.setVisible(false);
			this.setAlwaysOnTop(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
