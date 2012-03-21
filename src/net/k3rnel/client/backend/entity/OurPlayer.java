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
package net.k3rnel.client.backend.entity;

import java.util.ArrayList;

/**
 * Represents our player
 * @author shadowkanji
 *
 */
public class OurPlayer extends Player {
	private ArrayList<PlayerItem> m_items;
	private int m_money;
	
	/**
	 * Default constructor
	 */
	public OurPlayer() {
		m_items = new ArrayList<PlayerItem>();
		m_money = 0;
	}
	
	/**
	 * Constructor to be used if our player already exists
	 * @param original
	 */
	public OurPlayer(OurPlayer original) {
		m_items = original.getItems();
		m_sprite = original.getSprite();
		m_username = original.getUsername();
		m_isAnimating = original.isAnimating();
	}
	
	public void set(Player p) {
		m_x = p.getX();
		m_y = p.getY();
		m_svrX = p.getServerX();
		m_svrY = p.getServerY();
		m_sprite = p.getSprite();
		m_direction = p.getDirection();
		m_username = p.getUsername();
		m_id = p.getId();
		m_ours = p.isOurPlayer();
	}
	
	/**
	 * Returns our player's bag
	 * @return
	 */
	public ArrayList<PlayerItem> getItems() {
		return m_items;
	}
	
	/**
	 * Adds an item to this player's bag (automatically handles if its in the bag already)
	 * @param number
	 * @param quantity
	 */
	public void addItem(int number, int quantity) {
		boolean exists = false;
		for(int i = 0; i < m_items.size(); i++) {
			if(m_items.get(i) != null && m_items.get(i).getNumber() == number) {
				m_items.get(i).setQuantity(m_items.get(i).getQuantity() + quantity);
				exists = true;
			}
		}
		if(!exists){
			m_items.add(new PlayerItem(number, quantity));
		}
	}
	
	/**
	 * Removes an item from this player's bag
	 * @param number
	 * @param quantity
	 */
	public void removeItem(int number, int quantity) {
		for(int i = 0; i < m_items.size(); i++) {
			if(m_items.get(i) != null && m_items.get(i).getNumber() == number) {
				if(m_items.get(i).getQuantity() - quantity > 0) {
					m_items.get(i).setQuantity(m_items.get(i).getQuantity() - quantity);
				} else {
					m_items.remove(i);
				}
				return;
			}
		}
	}
	
	/**
	 * Gets item quantity from bag. 
	 * @param number
	 */
	public int getItemQuantity(int number) {
		int quantity = 0;
		for(int i = 0; i < m_items.size(); i++) {
			if(m_items.get(i) != null && m_items.get(i).getItem().getId() == number) {
				quantity = m_items.get(i).getQuantity(); //Return quantity
				return quantity;
			} else {
				quantity = 0; //Player doesnt own item
			}
		}
		return quantity;
	}
	
	/**
	 * Returns the player's money
	 * @return
	 */
	public int getMoney(){
		return m_money;
	}
	
	/**
	 * Sets the players money
	 * @param m
	 */
	public void setMoney(int m) {
		m_money = m;
	}
	
}
