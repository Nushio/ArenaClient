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

import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Represents an item
 * @author shadowkanji
 * @author Nushio
 */
public class Item {
	@Element
	private String m_name;
	@Element
	private String m_description;
	@Element
	private int m_id;
	@Element
	private String m_category;
	@Element
	private int m_price;
	@Element
	private int m_shop;
	@ElementList
	private ArrayList<ItemAttribute> m_attributes;
	/* Handles item attributes, lets us know what we need to do with them */
	public enum ItemAttribute { POKEMON, MOVESLOT, BATTLE, FIELD, CRAFT, HOLD, OTHER }
	
	/**
	 * Adds an attribute
	 * @param i
	 */
	public void addAttribute(ItemAttribute i) {
		if(m_attributes == null)
			m_attributes = new ArrayList<ItemAttribute>();
		m_attributes.add(i);
	}
	
	/**
	 * Returns the arraylist of attributes
	 * @return
	 */
	public ArrayList<ItemAttribute> getAttributes() {
		return m_attributes;
	}
	
	/**
	 * Sets the description of the item
	 * @param s
	 */
	public void setDescription(String s) {
		m_description = s;
	}
	
	/**
	 * Sets the category of the item
	 * @param s
	 */
	public void setCategory(String s) {
		m_category = s;
	}
	
	/**
	 * Sets the price of the item
	 * @param s
	 */
	public void setPrice(int s) {
		m_price = s;
	}
	/**
	 * Sets the shop of the item
	 * @param s
	 */
	public void setShop(int s) {
		m_shop = s;
	}
	/**
	 * Sets the name of the item
	 * @param s
	 */
	public void setName(String s) {
		m_name = s;
	}
	
	/**
	 * Sets the id of the item
	 * @param i
	 */
	public void setId(int i) {
		m_id = i;
	}
	/**
	 * Returns the shop of the item
	 * @return
	 */
	public int getShop() {
		return m_shop;
	}
	/**
	 * Returns the description of the item
	 * @return
	 */
	public String getDescription() {
		return m_description;
	}
	
	/**
	 * Returns the name of the item
	 * @return
	 */
	public String getName() {
		return m_name;
	}
	
	/**
	 * Returns the id of the item
	 * @return
	 */
	public int getId() {
		return m_id;
	}
	
	/**
	 * Returns the price of the item
	 * @return
	 */
	public int getPrice() {
		return m_price;
	}
	
	/**
	 * Returns the category of the item
	 * @return
	 */
	public String getCategory() {
		return m_category;
	}
}
