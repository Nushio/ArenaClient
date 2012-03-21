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

import org.newdawn.slick.gui.GUIContext;

public class BattleSpeechFrame extends SpeechFrame {
	private String advancedLine;
   
    public void addSpeech(String speech) {
            if (stringToPrint != null && (stringToPrint.equals("Awaiting your move.") || stringToPrint.equals("Awaiting players' moves."))
                            && speechQueue.peek() == null)
                    triangulate();
            speechQueue.add(speech);
            if (stringToPrint == null || stringToPrint.equals(""))
                    advance();
    }
    public BattleSpeechFrame() {
            super("");
    }
    @Override
    public void advancing(String toPrint) {
    }

    @Override
    public boolean canAdvance() {
            if (speechQueue.peek() == null &&
                            stringToPrint != null && (stringToPrint.equals("Awaiting your move.") || stringToPrint.equals("Awaiting players' moves.") || stringToPrint.equals("Awaiting opponent's Pokemon switch."))) {
                    return false;
            } 
            else return true;
    }

    @Override
    public void update(GUIContext ctx, int delta) {
            super.update(ctx, delta);
            if (speechDisplay.getText().equals("")
                            && speechQueue.peek() != null &&
                            (speechQueue.peek().equals("Awaiting your move.") || speechQueue.peek().equals("Awaiting players' moves.") || speechQueue.peek().equals("Awaiting opponent's Pokemon switch.")))
                    advance();
    }

    @Override
    public void advancedPast(String printed) {
    	advancedLine = printed;
    }
    
    public String getAdvancedLine(){
    	return advancedLine;
    }
    
    public String getCurrentLine(){
    	return stringToPrint;
    }
}

