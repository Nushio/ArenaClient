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
package net.k3rnel.arena.client.ui.base;

import net.k3rnel.arena.client.GameClient;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BattleButtonFactory {
    static {
    	try {
    		String respath = System.getProperty("res.path");
    		if(respath==null)
    			respath="";
    		String path = respath+"res/ui/";

    		normal = new Image(	path + "button.png", false);
    		normalDown = new Image(path + "button_pressed.png", false);
    		small = new Image(path + "button_small.png", false);
    		smallDown = new Image(path + "button_small_pressed.png", false);
    		
    		font = GameClient.getFontSmall();
    	} catch (SlickException e) {
    		e.printStackTrace();
    		assert(false);
    	}
    }
    private static Image small;
    private static Image normal;
    private static Image normalDown;
    private static Image smallDown;

    private static Font font;
    public static ImageButton getButton(String text) {
            ImageButton out = new ImageButton(normal, normal, normalDown);
            out.setFont(font);
            out.setForeground(Color.white);
            out.setText(text);
            return out;
    }
    public static ImageButton getSmallButton(String text) {
            ImageButton out = new ImageButton(small, small, smallDown);
            out.setFont(font);
            out.setForeground(Color.white);
            out.setText(text);
            return out;
    }
}
