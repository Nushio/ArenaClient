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

import java.util.HashMap;

import net.k3rnel.arena.client.GameClient;

import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class HUDButtonFactory {
        private static HashMap<String, Image> rollovers;
        private static HashMap<String, Image> normals;
        private static HashMap<String, Image> downs;
        
        static {
                try {
                        //LoadingList.setDeferredLoading(true);
                        
                        normals = new HashMap<String, Image>();
                        rollovers = new HashMap<String, Image>();
                        downs = new HashMap<String, Image>();
                        
                        // -- Player Info Button --
                        normals.put("stats", getNormalImage("stats"));
                        rollovers.put("stats", getRolloverImage("stats"));
                        downs.put("stats", getDownImage("stats"));
                        
                        // -- Chat Buttons --
                        normals.put("chat", getNormalImage("chat"));
                        rollovers.put("chat", getRolloverImage("chat"));
                        downs.put("chat", getDownImage("chat"));
                        
                        // -- Bag Buttons --
                        normals.put("bag", getNormalImage("pokegear"));
                        rollovers.put("bag", getRolloverImage("pokegear"));
                        downs.put("bag", getDownImage("pokegear"));
                        
                        // -- Help Buttons --
                        normals.put("help", getNormalImage("help"));
                        rollovers.put("help", getRolloverImage("help"));
                        downs.put("help", getDownImage("help"));
                        
                        // -- Pokemon Buttons --
                        normals.put("pokemon", getNormalImage("pokemon"));
                        rollovers.put("pokemon", getRolloverImage("pokemon"));
                        downs.put("pokemon", getDownImage("pokemon"));
                        
                        // -- Requests Buttons --
                        normals.put("requests", getNormalImage("requests"));
                        rollovers.put("requests", getRolloverImage("requests"));
                        downs.put("requests", getDownImage("requests"));
                        
                        // -- Options Buttons --
                        normals.put("options", getNormalImage("options"));
                        rollovers.put("options", getRolloverImage("options"));
                        downs.put("options", getDownImage("options"));
                        
                        // -- Map Buttons --
                        normals.put("map", getNormalImage("map"));
                        rollovers.put("map", getRolloverImage("map"));
                        downs.put("map", getDownImage("map"));
                        
                        // -- Friends Buttons --
                        normals.put("friends", getNormalImage("friends"));
                        rollovers.put("friends", getRolloverImage("friends"));
                        downs.put("friends", getDownImage("friends"));
                        
                        font = GameClient.getFontSmall();
                        //LoadingList.setDeferredLoading(false);
                } catch (SlickException e) {
                    System.out.println("Failed to create HUD buttons");    
                	e.printStackTrace();
                        assert(false);
                }
        }
        
        private static Font font;
        
        private static Image getRolloverImage(String text) throws SlickException {
        	String respath = System.getProperty("res.path");
    		if(respath==null)
    			respath="";
            return new Image(respath+"res/ui/" + text + "_32x32.png", false);
        }
        
        private static Image getNormalImage(String text) throws SlickException {
        	String respath = System.getProperty("res.path");
    		if(respath==null)
    			respath="";
            return new Image(respath+"res/ui/" + text + "_32x32.png", false);
        }
        
        private static Image getDownImage(String text) throws SlickException {
        	String respath = System.getProperty("res.path");
    		if(respath==null)
    			respath="";
        	return new Image(respath+"res/ui/" + text + "Pressed_32x32.png", false);
        }
        
        public static ImageButton getButton(String text) {
                ImageButton out = new ImageButton(
                        normals.get(text.toLowerCase()),
                        rollovers.get(text.toLowerCase()),
                        downs.get(text.toLowerCase()));
                
                out.setFont(font);
                return out;
        }
}
