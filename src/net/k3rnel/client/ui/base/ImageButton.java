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
package net.k3rnel.client.ui.base;

import mdes.slick.sui.Button;

import org.newdawn.slick.Image;

/**
 * Creates an imagebutton
 * @author shaowkanji
 *
 */
public class ImageButton extends Button {
	/**
	 * Constructor
	 * @param normal
	 * @param hover
	 * @param down
	 */
	public ImageButton(Image normal, Image hover, Image down) {
		super();
		setImage(normal);
		setRolloverImage(hover);
		setDownImage(down);
		setDisabledImage(down); //Temporarily used the button pressed image as disabled image
		setPadding(0);
		setOpaque(false);
	}
	
	/**
	 * Default constuctor
	 */
	public ImageButton() {
		super();
		setPadding(0);
		setOpaque(false);
	}
	
	/**
	 * Sets this button's images
	 * @param normal
	 * @param hover
	 * @param down
	 */
	public void setImages(Image normal, Image hover, Image down) {
		setImage(normal);
		setRolloverImage(hover);
		setDownImage(down);
	}
}
