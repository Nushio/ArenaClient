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
package net.k3rnel.arena.client.backend;

import java.util.HashMap;

import net.k3rnel.arena.client.backend.entity.Player.Direction;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * Handles overworld sprites
 * 
 * @author shinobi
 * 
 */
public class SpriteFactory {
	private HashMap<Integer, SpriteSheet> spriteSheets;

	/**
	 * Returns the requested sprite
	 * 
	 * @param dir
	 * @param isMoving
	 * @param isLeftFoot
	 * @param sprite
	 * @return
	 */
	public Image getSprite(Direction dir, boolean isMoving, boolean isLeftFoot,
			int sprite) {
		SpriteSheet sheet = spriteSheets.get(sprite);
		if (isMoving) {
			if (isLeftFoot) {
				switch (dir) {
				case Up:
					return sheet.getSprite(0, 0);
				case Down:
					return sheet.getSprite(0, 2);
				case Left:
					return sheet.getSprite(0, 3);
				case Right:
					return sheet.getSprite(0, 1);
				}
			} else {
				switch (dir) {
				case Up:
					return sheet.getSprite(2, 0);
				case Down:
					return sheet.getSprite(2, 2);
				case Left:
					return sheet.getSprite(2, 3);
				case Right:
					return sheet.getSprite(2, 1);
				}
			}
		} else {
			switch (dir) {
			case Up:
				return sheet.getSprite(1, 0);
			case Down:
				return sheet.getSprite(1, 2);
			case Left:
				return sheet.getSprite(1, 3);
			case Right:
				return sheet.getSprite(1, 1);
			}
		}
		return null;
	}

	/**
	 * Initialises the database of sprites
	 */
	public SpriteFactory() {

		spriteSheets = new HashMap<Integer, SpriteSheet>();
		try {
			String location;
			String respath = System.getProperty("res.path");
			if (respath == null)
				respath = "";
			Image temp;
			Image[] imgArray = new Image[250];
			SpriteSheet ss = null;
			/*
			 * WARNING: Change 224 to the amount of sprites we have in client
			 * the load bar only works when we don't make a new SpriteSheet ie.
			 * ss = new SpriteSheet(temp, 41, 51); needs to be commented out in
			 * order for the load bar to work.
			 */
			for (int i = -5; i < 224; i++) {
				try {
					location = respath + "res/characters/" + String.valueOf(i)
							+ ".png";
					temp = new Image(location);
					imgArray[i + 5] = temp;
					ss = new SpriteSheet(temp, 41, 51);

					spriteSheets.put(i, ss);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SpriteFactory(Image[] imgArray) {
		spriteSheets = new HashMap<Integer, SpriteSheet>();

		for (int i = 0; i < imgArray.length; i++) {
			try{
				spriteSheets.put(i, new SpriteSheet(imgArray[i], 41, 51));
			}catch(Exception e){
				
			}
		}
	}
}
