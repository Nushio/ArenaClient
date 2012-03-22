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
package net.k3rnel.arena.client.ui;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Frame;
import mdes.slick.sui.Label;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

import net.k3rnel.arena.client.GameClient;
import net.k3rnel.arena.client.backend.BattleManager;
import net.k3rnel.arena.client.backend.FileLoader;
import net.k3rnel.arena.client.ui.base.BattleButtonFactory;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;

/**
 * Battle window interface
 * 
 * @author ZombieBear
 * 
 */
public class BattleWindow extends Frame {
	static final long serialVersionUID = -4351471892179339349L;

	public Container endPane;
	public Container attackPane;
	public Container monstersContainer;
	public Button move1;
	public Button move2;
	public Button move3;
	public Button move4;
	public Label pp1;
	public Label pp2;
	public Label pp3;
	public Label pp4;
	public Button monsterCancelBtn;
	public Button monsterBtn1;
	public Button monsterBtn2;
	public Button monsterBtn3;
	public Button monsterBtn4;
	public Button monsterBtn5;
	public Button monsterBtn6;
	public Button btnMonster;
	public Button btnBag;
	public Button btnRun;
	public Button cancel;
	public Button close;
	private boolean isWild;
	public List<Button> m_moveButtons = new ArrayList<Button>();
	public List<Label> m_ppLabels = new ArrayList<Label>();
	public List<Button> m_monsterButtons = new ArrayList<Button>();
	public List<Label> m_monsterInfo = new ArrayList<Label>();
	public List<Label> m_monsterStatus = new ArrayList<Label>();
	public HashMap<String, Image> m_statusIcons = new HashMap<String, Image>();
	
	// Image Loading tools
	private String m_path = "res/battle/";
	InputStream f;
	
	private Label m_bg = new Label();

	
	/**
	 * Default constructor
	 * 
	 * @param title
	 * @param wild
	 */
	public BattleWindow(String title) {
		String respath = System.getProperty("res.path");
		if(respath==null)
			respath="";
		m_path=respath+m_path;
		getContentPane().setX(getContentPane().getX() - 1);
		getContentPane().setY(getContentPane().getY() + 1);
		setTitle(title);
		loadStatusIcons();
		initComponents();
		setCenter();
		setSize(259, 369);
	}

	/**
	 * Loads the status icons
	 */
	public void loadStatusIcons(){
		/*LoadingList.setDeferredLoading(true);
		try{
			m_statusIcons.put("Poison", new Image(m_path + "PSN" + ".png", false));
		} catch (SlickException e) {e.printStackTrace();} try{
			m_statusIcons.put("Sleep", new Image(m_path + "SLP" + ".png", false));
		} catch (SlickException e) {e.printStackTrace();} try{
			m_statusIcons.put("Freze", new Image(m_path + "FRZ" + ".png", false));
		} catch (SlickException e) {e.printStackTrace();} try{
			m_statusIcons.put("Burn", new Image(m_path + "BRN" + ".png", false));
		} catch (SlickException e) {e.printStackTrace();} try{
			m_statusIcons.put("Paralysis", new Image(m_path + "PAR" + ".png", false));
		} catch (SlickException e) {e.printStackTrace();}
		LoadingList.setDeferredLoading(false);*/
	}
	
	/**
	 * Disables moves
	 */
	public void disableMoves() {
		attackPane.setVisible(false);
		move1.setEnabled(false);
		move2.setEnabled(false);
		move3.setEnabled(false);
		move4.setEnabled(false);

		pp1.setEnabled(false);
		pp2.setEnabled(false);
		pp3.setEnabled(false);
		pp4.setEnabled(false);

		btnMonster.setEnabled(false);
		btnBag.setEnabled(false);
		btnRun.setEnabled(false);

		cancel.setVisible(false);
	}

	/**
	 * Enables moves
	 */
	public void enableMoves() {
		attackPane.setVisible(true);
		btnMonster.setEnabled(true);
		btnBag.setEnabled(true);
		if (!isWild) {
			btnRun.setEnabled(false);
		} else {
			btnRun.setEnabled(true);
		}

		monsterCancelBtn.setEnabled(true);
		if (!move1.getText().equals("")) {
			move1.setEnabled(true);
			pp1.setEnabled(true);
		}
		if (!move2.getText().equals("")) {
			move2.setEnabled(true);
			pp2.setEnabled(true);
		}
		if (!move3.getText().equals("")) {
			move3.setEnabled(true);
			pp3.setEnabled(true);
		}
		if (!move4.getText().equals("")) {
			move4.setEnabled(true);
			pp4.setEnabled(true);
		}
		cancel.setVisible(false);
	}

	/**
	 * Initializes the interface
	 */
	private void initComponents() {
		LoadingList.setDeferredLoading(true);
		this.setBackground(new Color(0, 0, 0, 0));
		String respath = System.getProperty("res.path");
		if(respath==null)
			respath="";
		try {
			f = FileLoader.loadFile(respath+"res/ui/bg.png");
			m_bg = new Label(new Image(f, respath+"res/ui/bg.png", false));
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		m_bg.setSize(256, 203);
		m_bg.setLocation(0, 142);
		getContentPane().add(m_bg);

		attackPane = new Container();
		attackPane.setBackground(new Color(0, 0, 0, 0));

		move1 = BattleButtonFactory.getButton("");
		move2 = BattleButtonFactory.getButton("");
		move3 = BattleButtonFactory.getButton("");
		move4 = BattleButtonFactory.getButton("");

		setResizable(false);

		this.getTitleBar().setVisible(false);

		// start attackPane
		attackPane.add(move1);
		move1.setLocation(7, 10);
		move1.setSize(116, 51);
		move1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				useMove(0);
			}
		});
		pp1 = new Label();
		pp1.setHorizontalAlignment(Label.RIGHT_ALIGNMENT);
		pp1.setBounds(0, move1.getHeight() - 20, move1.getWidth() - 5, 20);
		move1.add(pp1);

		attackPane.add(move2);
		move2.setLocation(130, 10);
		move2.setSize(116, 51);
		move2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				useMove(1);
			}
		});
		pp2 = new Label();
		pp2.setHorizontalAlignment(Label.RIGHT_ALIGNMENT);
		pp2.setBounds(0, move2.getHeight() - 20, move2.getWidth() - 5, 20);
		move2.add(pp2);

		attackPane.add(move3);
		move3.setLocation(7, 65);
		move3.setSize(116, 51);
		move3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				useMove(2);
			}
		});
		pp3 = new Label();
		pp3.setHorizontalAlignment(Label.RIGHT_ALIGNMENT);
		pp3.setBounds(0, move3.getHeight() - 20, move3.getWidth() - 5, 20);
		move3.add(pp3);

		attackPane.add(move4);
		move4.setLocation(130, 65);
		move4.setSize(116, 51);
		move4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				useMove(3);
			}
		});
		pp4 = new Label();
		pp4.setHorizontalAlignment(Label.RIGHT_ALIGNMENT);
		pp4.setBounds(0, move4.getHeight() - 20, move4.getWidth() - 5, 20);
		move4.add(pp4);

		pp1.setFont(GameClient.getFontSmall());
		pp2.setFont(GameClient.getFontSmall());
		pp3.setFont(GameClient.getFontSmall());
		pp4.setFont(GameClient.getFontSmall());
		
		pp1.setForeground(Color.white);
		pp2.setForeground(Color.white);
		pp3.setForeground(Color.white);
		pp4.setForeground(Color.white);
		
		m_moveButtons.add(move1);
		m_moveButtons.add(move2);
		m_moveButtons.add(move3);
		m_moveButtons.add(move4);

		m_ppLabels.add(pp1);
		m_ppLabels.add(pp2);
		m_ppLabels.add(pp3);
		m_ppLabels.add(pp4);

		btnRun = BattleButtonFactory.getSmallButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				run();
			}
		});
		attackPane.add(btnRun);

		btnRun.setBounds(97, 148, 60, 47);

		btnBag = BattleButtonFactory.getSmallButton("Bag");
		attackPane.add(btnBag);
		btnBag.setLocation(3, 122);
		btnBag.setSize(82, 48);

		btnBag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showBag();
			}
		});

		btnMonster = BattleButtonFactory.getSmallButton("Monstermon");
		attackPane.add(btnMonster);
		btnMonster.setLocation(168, 122);
		btnMonster.setSize(82, 48);

		btnMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showMonsterPane(false);
			}
		});

		cancel = BattleButtonFactory.getSmallButton("Cancel");
		attackPane.add(cancel);
		cancel.setVisible(false);
		cancel.setLocation(162, 110);
		cancel.setSize(82, 48);

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

			}
		});

		attackPane.setBounds(2, 140, 257, 201);
		getContentPane().add(attackPane);
		// end attackPane

		// start monstersContainer
		monstersContainer = new Container();
		monstersContainer.setBackground(new Color(0, 0, 0, 0));
		monstersContainer.setBounds(2, 140, 257, 201);

		monsterBtn1 = BattleButtonFactory.getButton(" ");
		monstersContainer.add(monsterBtn1);
		monsterBtn1.setBounds(8, 8, 116, 51);

		monsterBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchMonster(0);
			}
		});

		monsterBtn2 = BattleButtonFactory.getButton(" ");
		monstersContainer.add(monsterBtn2);
		monsterBtn2.setBounds(128, 8, 116, 51);

		monsterBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchMonster(1);
			}
		});

		monsterBtn3 = BattleButtonFactory.getButton(" ");
		monstersContainer.add(monsterBtn3);
		monsterBtn3.setBounds(8, 59, 116, 51);

		monsterBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchMonster(2);
			}
		});

		monsterBtn4 = BattleButtonFactory.getButton(" ");
		monstersContainer.add(monsterBtn4);
		monsterBtn4.setBounds(128, 59, 116, 51);

		monsterBtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchMonster(3);
			}
		});

		monsterBtn5 = BattleButtonFactory.getButton(" ");
		monstersContainer.add(monsterBtn5);
		monsterBtn5.setBounds(8, 110, 116, 51);

		monsterBtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchMonster(4);
			}
		});

		monsterBtn6 = BattleButtonFactory.getButton(" ");
		monstersContainer.add(monsterBtn6);
		monsterBtn6.setBounds(128, 110, 116, 51);

		monsterBtn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				switchMonster(5);
			}
		});

		m_monsterButtons.add(monsterBtn1);
		m_monsterButtons.add(monsterBtn2);
		m_monsterButtons.add(monsterBtn3);
		m_monsterButtons.add(monsterBtn4);
		m_monsterButtons.add(monsterBtn5);
		m_monsterButtons.add(monsterBtn6);

		for (int i = 0; i < 6; i++){
			Label status = new Label();
			status.setSize(30, 12);
			status.setGlassPane(true);
			m_monsterButtons.get(i).add(status);
			status.setLocation(6, 5);
			
			Label info = new Label();
			m_monsterButtons.get(i).add(info);
			info.setText("                               ");
			info.setLocation(3, 34);
			info.setSize(107, 14);
			info.setForeground(Color.white);
			info.setGlassPane(true);
			info.setFont(GameClient.getFontSmall());
			m_monsterInfo.add(info);
		}

		monsterCancelBtn = BattleButtonFactory.getSmallButton("Cancel");
		monstersContainer.add(monsterCancelBtn);
		monsterCancelBtn.setLocation(162, 161);
		monsterCancelBtn.setSize(82, 48);
		
		monsterCancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showAttack();
			}
		});
		monstersContainer.setVisible(false);
		getContentPane().add(monstersContainer);
		// End monstersContainer

		endPane = new Container();
		endPane.setBackground(new Color(0, 0, 0, 0));
		getContentPane().add(endPane);
		endPane.setBounds(0, 154, 253, 192);

		close = new Button();
		close.setVisible(true);
		endPane.add(close);
		close.setText("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
			}
		});

		endPane.setVisible(false);
		LoadingList.setDeferredLoading(false);
	}

	/**
	 * Sends the run packer
	 */
	private void run() {
//		GameClient.getInstance().getPacketGenerator().writeTcpMessage("br");
	}

	/**
	 * Centers the battle window
	 */
	public void setCenter() {
		int height = (int) GameClient.getInstance().getDisplay().getHeight();
		int width = (int) GameClient.getInstance().getDisplay().getWidth();
		int x = (width / 2) - 130;
		int y = (height / 2) - 238;
		this.setBounds(x, y, 259, 475);
	}

	/**
	 * Sets whether the battle is a wild monstermon
	 * @param isWild
	 */
	public void setWild(boolean isWild) {
		this.isWild = isWild;
		btnRun.setEnabled(isWild);
	}

	/**
	 * Shows the attack Pane
	 */
	public void showAttack() {
		monstersContainer.setVisible(false);
		// bagPane.setVisible(false);
		attackPane.setVisible(true);
		endPane.setVisible(false);
	}

	/**
	 * Shows the Bag Pane
	 */
	public void showBag() {
		attackPane.setVisible(false);
		endPane.setVisible(false);
		monstersContainer.setVisible(false);
	}

	/**
	 * Shows the monstermon Pane
	 */
	public void showMonsterPane(boolean isForced) {
		attackPane.setVisible(false);
		// bagPane.setVisible(false);
		monstersContainer.setVisible(true);
		endPane.setVisible(false);
		if (isForced)
			monsterCancelBtn.setEnabled(false);
		else
			monsterCancelBtn.setEnabled(true);
	}

	/**
	 * Sends the monstermon switch packet
	 * 
	 * @param i
	 */
	private void switchMonster(int i) {
		attackPane.setVisible(false);
		monstersContainer.setVisible(false);
//		GameClient.getInstance().getPacketGenerator().writeTcpMessage("bs" + i);
	}

}
