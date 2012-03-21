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
package net.k3rnel.client.backend;

import java.util.HashMap;
import java.util.Map;

import net.k3rnel.client.GameClient;
import net.k3rnel.client.backend.entity.OurPlayer;
import net.k3rnel.client.ui.BattleWindow;

/**
 * Handles battle events and controls the battle window
 * 
 * @author ZombieBear
 * 
 */
public class BattleManager {
	private OurPlayer m_player;
	private BattleWindow m_battle;
	private BattleTimeLine m_timeLine;
	private static BattleManager m_instance;
	private static boolean m_isBattling = false;
	private String m_curTrack;
	
	/**
	 * Default Constructor
	 */
	public BattleManager() {
		m_instance = this;
		m_battle = new BattleWindow("Battle!");
		m_timeLine = new BattleTimeLine();
		m_battle.setVisible(false);
		m_battle.setAlwaysOnTop(true);
	}

	/**
	 * Returns the instance
	 * @return
	 */
	public static BattleManager getInstance() {
		return m_instance;
	}
	
	/**
	 * Retrieves player data
	 */
	private void getPlayerData() {
		m_player = GameClient.getInstance().getOurPlayer();
		
	}

	/**
	 * Sets the enemy's data 
	 */
	private void setEnemyData() {
		try{
			m_timeLine.getBattleCanvas().drawEnemyMonster();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			m_timeLine.getBattleCanvas().drawEnemyInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts a new BattleWindow and BattleCanvas
	 * @param isWild
	 * @param monsterAmount
	 */
	public void startBattle(char isWild,
			int monsterAmount) {
		m_isBattling = true;

		m_battle.showAttack();
		m_battle.setVisible(true);
		getPlayerData();
		m_battle.disableMoves();
		m_timeLine.startBattle();
        m_curTrack = GameClient.getSoundPlayer().m_trackName;
        System.out.println("Before Battle Music Name:" + m_curTrack);
		GameClient.getInstance().getDisplay().add(m_battle);
		GameClient.changeTrack("pvnpc");
	}
	
	/**
	 * Ends the battle
	 */
	public void endBattle() {
		m_timeLine.endBattle();
		m_battle.setVisible(false);
		m_isBattling = false;
		GameClient.getInstance().getDisplay().remove(m_battle);
		while (GameClient.getInstance().getDisplay().containsChild(m_battle));
		GameClient.getSoundPlayer().setTrackByLocation(GameClient.getInstance().getMapMatrix().getCurrentMap().getName());
		if (GameClient.getSoundPlayer().m_trackName == "pvnpc") {
			GameClient.getSoundPlayer().setTrack(m_curTrack);
		}
	}

	/**
	 * Returns the TimeLine
	 * @return m_timeLine
	 */
	public BattleTimeLine getTimeLine(){
		return m_timeLine;
	}

	/**
	 * Gets the BattleWindow
	 * @return
	 */
	public BattleWindow getBattleWindow(){
		return m_battle;
	}
	
	/**
	 * Returns true if a battle is in progress
	 * @return true if a battle is in progress
	 */
	public static boolean isBattling(){
		return m_isBattling;
	}
}
