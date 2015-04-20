package presentation.contenui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.component.BgPanel;
import presentation.hotspot.HotspotUtil;

public class HotUI extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String bgStr = "img/hotspot/whitebg.jpg";

	private JPanel bluePanel;

	private JButton playerToday;
	private JButton playerSeason;
	private JButton teamSeason;
	private JButton playerFast;

	private JLabel titleLabel;
	private JButton downBt;

	public HotUI(String s) {
		super(bgStr);

		this.setSize(1000, 650);
		this.setLocation(15, 50);
		this.setLayout(null);
		this.setOpaque(false);
		
		titleLabel = new JLabel();
		titleLabel.setBounds(290, 276, 410, 60);
		titleLabel.setIcon(HotspotUtil.titleIcon);
		this.add(titleLabel);
		
		downBt = new JButton();
		downBt.setBounds(475, 300, 30, 16);
		downBt.setIcon(HotspotUtil.titleBt);
		downBt.setContentAreaFilled(false);
		downBt.setBorder(null);
		downBt.setVisible(false);
		
		bluePanel = new JPanel();
		bluePanel.setSize(1000, 325);
		bluePanel.setLocation(0,0);
		bluePanel.setOpaque(true);
		bluePanel.setBackground(UIUtil.nbaBlue);
		bluePanel.setLayout(null);
		bluePanel.add(downBt);
		this.add(bluePanel);
		
		playerToday = new JButton();
		playerToday.setBounds(75, 398, 145, 180);
		playerToday.setContentAreaFilled(false);
		playerToday.setBorder(null);
		playerToday.setIcon(HotspotUtil.playerTodayIcon);
		playerToday.addMouseListener(new PlayerTodayListener());
		this.add(playerToday);
		
		playerSeason = new JButton();
		playerSeason.setBounds(310, 398, 145, 180);
		playerSeason.setContentAreaFilled(false);
		playerSeason.setBorder(null);
		playerSeason.setIcon(HotspotUtil.playerSeasonIcon);
		playerSeason.addMouseListener(new PlayerSeasonListener());
		this.add(playerSeason);
		
		teamSeason = new JButton();
		teamSeason.setBounds(545, 398, 145, 180);
		teamSeason.setContentAreaFilled(false);
		teamSeason.setBorder(null);
		teamSeason.setIcon(HotspotUtil.teamSeasonIcon);
		teamSeason.addMouseListener(new TeamSeasonListener());
		this.add(teamSeason);
		
		playerFast = new JButton();
		playerFast.setBounds(780, 398, 145, 180);
		playerFast.setContentAreaFilled(false);
		playerFast.setBorder(null);
		playerFast.setIcon(HotspotUtil.playerFastIcon);
		playerFast.addMouseListener(new PlayerFastListener());
		this.add(playerFast);
	}

	
	class PlayerTodayListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			bt.setIcon(HotspotUtil.playerTodayIconChosen);
		}

		public void mouseExited(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			bt.setIcon(HotspotUtil.playerTodayIcon);
		}

	
		
	}
	
	class PlayerSeasonListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			bt.setIcon(HotspotUtil.playerSeasonIconChosen);
		}

		public void mouseExited(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			bt.setIcon(HotspotUtil.playerSeasonIcon);
		}

		
		
	}
	
	class TeamSeasonListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			bt.setIcon(HotspotUtil.teamSeasonIconChosen);
		}

		public void mouseExited(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			bt.setIcon(HotspotUtil.teamSeasonIcon);
		}

		
	}
	
	class PlayerFastListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			bt.setIcon(HotspotUtil.playerFastIconChosen);
		}

		public void mouseExited(MouseEvent e) {
			JButton bt = (JButton)e.getSource();
			bt.setIcon(HotspotUtil.playerFastIcon);
		}

		
		
	}
	
	
	
	
}
