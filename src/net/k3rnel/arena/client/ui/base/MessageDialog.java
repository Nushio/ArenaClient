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

import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Frame;
import mdes.slick.sui.Label;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

/**
 * Shows a message box
 * @author ZombieBear
 *
 */
public class MessageDialog extends Frame{
    /**
     * Default constructor    
     * @param message
     * @param container
     */
	public MessageDialog(String message, Container container){
		getContentPane().setX(getContentPane().getX() - 1);
		getContentPane().setY(getContentPane().getY() + 1);
		Container label = new Container();
		String[] lines = message.split("\n");
		
		int maxWidth = 0;
		int maxHeight = 0;
		
		for (String s : lines) {
			Label line = new Label(s);
			line.pack();
			
			int lineWidth = (int)line.getWidth();
			int lineHeight = (int)line.getHeight();
			
			if (lineWidth > maxWidth)
				maxWidth = lineWidth;
			
			line.setY(maxHeight);
			maxHeight += lineHeight;
			
			label.add(line);
		}
		label.setSize(maxWidth, maxHeight);
		
		Button ok = new Button();
		ok.setText("OK");
		getContentPane().add(label);
		getContentPane().add(ok);
		label.setLocation(5, 15);
		this.setResizable(false);
		this.setSize(label.getWidth() + 10, label.getHeight() + 90);
		ok.setSize(50, 25);
		ok.setLocation(this.getWidth()/2-25, this.getHeight()- 60);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
			}
		});
		this.setLocation((container.getDisplay().getWidth()/2)-(this.getWidth()/2),
				(container.getDisplay().getHeight()/2)-(this.getHeight()/2));
		this.setVisible(true);
		container.add(this);
		this.setAlwaysOnTop(true);
	}
}
