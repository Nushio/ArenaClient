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

import java.util.ArrayList;
import java.util.List;

import net.k3rnel.arena.client.GameClient;
import net.k3rnel.arena.client.ui.BattleCanvas;
import net.k3rnel.arena.client.ui.frames.BattleSpeechFrame;

/**
 * Handles Battle Events and arranges them for visual purposes.
 * 
 * @author ZombieBear
 */
public class BattleTimeLine {
  private final BattleSpeechFrame m_narrator;
  private BattleCanvas            m_canvas;
  List<String>                    m_translator = new ArrayList<String>();
  // Lines for REGEX needed for l10n
  String                          m_monsterName, m_move, m_trainer, m_foundItem;
  int                             m_newHPValue, m_exp, m_dmg, m_earnings,
      m_level, m_expRemaining;
  private boolean                 m_isBattling;

  /**
   * Default constructor
   */
  public BattleTimeLine() {
    m_translator = Translator.translate("_BATTLE");
    try {
      m_canvas = new BattleCanvas();
    } catch (Exception e) {
      e.printStackTrace();
    }
    m_narrator = new BattleSpeechFrame();
  }

  /**
   * Starts the TimeLine's components
   */
  public void startBattle() {
    m_canvas.startBattle();
    m_isBattling = true;
    GameClient.getInstance().getDisplay().add(m_canvas);
    GameClient.getInstance().getDisplay().add(m_narrator);
    GameClient.getInstance().getUi().nullSpeechFrame();
  }
  
  /**
   * Informs a victory on the player's side
   */
  public void informVictory() {
    m_trainer = GameClient.getInstance().getOurPlayer().getUsername();
    addSpeech(m_translator.get(10));
    BattleManager.getInstance().endBattle();
    m_isBattling = false;
  }

  /**
   * Informs a loss on the player's side
   */
  public void informLoss() {
    m_trainer = GameClient.getInstance().getOurPlayer().getUsername();
    addSpeech(m_translator.get(11));
    BattleManager.getInstance().endBattle();
    m_isBattling = false;
  }

  /**
   * Shows a custom message sent by the server
   * 
   * @param msg
   */
  public void showMessage(String msg) {
    addSpeech(msg);
  }

  /**
   * Informs the player's earnings
   * 
   * @param money
   */
  public void informMoneyGain(int money) {
    m_earnings = money;
    addSpeech(m_translator.get(19));
  }

  /**
   * Informs the player's earnings
   * 
   * @param money
   */
  public void informLevelUp(String monster, int level) {
    m_monsterName = monster;
    m_level = level;
    addSpeech(m_translator.get(20));
  }

  /**
   * Adds speech to the narrator and waits for it to be read before the next
   * action is taken
   * 
   * @param msg
   */
  public void addSpeech(String msg) {
    String newMsg = parsel10n(msg);
    m_narrator.addSpeech(parsel10n(newMsg));
    while (!m_narrator.getCurrentLine().equalsIgnoreCase(newMsg))
      ;
    while (!m_narrator.getAdvancedLine().equalsIgnoreCase(newMsg))
      ;
  }

  /**
   * Returns the battle speech
   * 
   * @return
   */
  public BattleSpeechFrame getBattleSpeech() {
    return m_narrator;
  }

  /**
   * Returns the battle canvas
   * 
   * @return
   */
  public BattleCanvas getBattleCanvas() {
    return m_canvas;
  }

  /**
   * Stops the timeline
   */
  public void endBattle() {
    m_canvas.stop();
    try {
      GameClient.getInstance().getDisplay().remove(m_canvas);
    } catch (Exception e) {
    }
    ;
    while (GameClient.getInstance().getDisplay().containsChild(m_canvas))
      ;
    try {
      GameClient.getInstance().getDisplay().remove(m_narrator);
    } catch (Exception e) {
    }
    ;
    while (GameClient.getInstance().getDisplay().containsChild(m_narrator))
      ;
  }

  /**
   * Uses regexes to create the appropriate battle messages for battle
   * 
   * @param line
   */
  public String parsel10n(String line) {
    if (line.contains("trainerName")) {
      line = line.replaceAll("trainerName", m_trainer);
    }
    if (line.contains("moveName")) {
      line = line.replaceAll("moveName", m_move);
    }
    if (line.contains("monsterName")) {
      line = line.replace("monsterName", m_monsterName);
    }
    if (line.contains("hpNum")) {
      line = line.replaceAll("hpNum", String.valueOf(m_newHPValue));
    }
    if (line.contains("expNum")) {
      line = line.replaceAll("expNum", String.valueOf(m_exp));
    }
    if (line.contains("damageNum")) {
      line = line.replaceAll("damageNum", String.valueOf(m_dmg));
    }
    if (line.contains("earningsNum")) {
      line = line.replaceAll("earningsNum", String.valueOf(m_earnings));
    }
    if (line.contains("levelNum")) {
      line = line.replaceAll("levelNum", String.valueOf(m_level));
    }
    if (line.contains("rewardItem")) {
      line = line.replaceAll("rewardItem", m_foundItem);
    }
    if (line.contains("expRemaining")) {
      line = line.replaceAll("expRemaining", String.valueOf(m_expRemaining));
    }
    return line;
  }

  public boolean isBattling() {
    return m_isBattling;
  }
}
