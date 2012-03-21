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
package net.k3rnel.arena.client.backend.entity;


/**
 * obstacles
 * @author ZombieBear
 *
 */
public class Obstacle extends Player {
	private int m_neededTrainerLvl;
	private String m_objectName;

	public static enum HMObjectType {
		TREE,
		ROCK,
		BOULDER,
		WHIRLPOOL,
		HEADBUTT_TREE
	}
	
	public static HMObjectType parseHMObject(String s) throws Exception {
		for (HMObjectType HMObj : HMObjectType.values()) {
			if (s.equalsIgnoreCase(HMObj.name()))
				return HMObj;
		}
		throw new Exception("This is not an obstacle");
	}
	
	/**
	 * Returns the objeect's name (for internal use only)
	 * @param e
	 * @return
	 */
	private static String getObjectName(HMObjectType e){
		switch (e) {
		case TREE:
			return "Headbutt Tree";
		case ROCK:
			return "Rocksmash Rock";
		case BOULDER:
			return "Strength Boulder";
		case WHIRLPOOL:
			return "Whirlpool"; 
		case HEADBUTT_TREE:
			return "Headbutt Tree";
		}
		return "";
	}
	
	/**
	 * Returns the necessary trainer level to work the object (for internal use only)
	 * @param e
	 * @return
	 */
	private int getNeededTrainerLvl(HMObjectType e){
		switch (e) {
		case TREE:
			return 15;
		case ROCK:
			return 30;
		case BOULDER:
			return 35;
		case WHIRLPOOL:
			return 40; 
		case HEADBUTT_TREE:
			return 0;
		}
		return 0;
	}
	
	/**
	 * Default constructor
	 * @param e HMObjectType enum
	 */
	public Obstacle(HMObjectType e){
		super();
		m_objectName = getObjectName(e);
		m_neededTrainerLvl = getNeededTrainerLvl(e);
		setUsername("");
	}
	
	
	
	/**
	 * Returns the necessary trainer level to use the object
	 * @return
	 */
	public int getRequiredTrainerLevel() {
		return m_neededTrainerLvl;
	}
	
	/**
	 * Returns the object's name
	 * @return
	 */
	public String getName(){
		return m_objectName;
	}
	
	@Override
	public int getType(){
		return 2;
	}
}
