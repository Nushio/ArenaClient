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
package net.k3rnel.client.ui.frames;

import net.k3rnel.client.GameClient;

/**
 * NPC speech pop-up
 * @author ZombieBear
 *
 */
public class NPCSpeechFrame extends SpeechFrame {
	/**
	 * Default Constructor
	 * @param text
	 */
    public NPCSpeechFrame(String text) {
            super(text);
    }
    
    /**
	 * Modified constructor, sets time to auto-skip to the next line. 
	 * @param text
	 * @param seconds
	 */
    public NPCSpeechFrame(String text,int seconds) {
        super(text,seconds);
    }
    /**
     * Sends a packet when finished displaying text
     */
    public void advancedPast(String advancedMe) {
            if (speechQueue.peek() == null) {
                    triangle = null;
                    setVisible(false);
                    GameClient.getInstance().getUi().nullSpeechFrame();
//                    GameClient.getInstance().getPacketGenerator().writeTcpMessage("Cf");
            }
    }
}
