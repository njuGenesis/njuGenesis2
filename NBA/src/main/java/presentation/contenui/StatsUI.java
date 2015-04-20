package presentation.contenui;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.component.BgPanel;

public class StatsUI extends BgPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	
	
	private static String bgStr = "img/hotspot/whitebg.jpg";

	private JPanel bluePanel;

	private JButton playerToday;
	private JButton playerSeason;
	private JButton teamSeason;
	private JButton playerFast;

	private JLabel titleLabel;
	private JButton rightBt;
	
	private BgPanel statsPanel;
	
//	List<Point2D.Double> polygonPlayer;
//	List<Point2D.Double> polygonTeam;
	
	Point2D[] polygonPlayer = {new Point(163-78-15,325-149),new Point(93-78-15,395-149),new Point(163-78-15,466-149),new Point(234-78-15,395-149)};
	Point2D[] polygonTeam = {new Point(515-78-15,185-149),new Point(445-78-15,255-149),new Point(515-78-15,326-149),new Point(586-78-15,255-149)};

	private RunType runType;
	
	enum RunType{
		team,
		player,
		back,
	}
	
	public StatsUI(String s) {
		super(bgStr);

		this.setSize(1000, 650);
		this.setLocation(15, 50);
		this.setLayout(null);
		this.setOpaque(false);
		
		bluePanel = new JPanel();
		bluePanel.setSize(650, 650);
		bluePanel.setLocation(0,0);
		bluePanel.setOpaque(true);
		bluePanel.setBackground(UIUtil.nbaBlue);
		bluePanel.setLayout(null);
		this.add(bluePanel);
		
		titleLabel = new JLabel();
		titleLabel.setBounds(78, 149, 493, 354);
		titleLabel.setIcon(StatsUtil.title);
		titleLabel.addMouseMotionListener(new StatsListener());
		titleLabel.addMouseListener(new StatsListener());
		bluePanel.add(titleLabel);
		
		rightBt = new JButton();
		rightBt.setBounds(633-15, 310, 16, 30);
		rightBt.setIcon(StatsUtil.rightIcon);
		rightBt.setContentAreaFilled(false);
		rightBt.setBorder(null);
		rightBt.setVisible(false);
		bluePanel.add(rightBt);
		
//		PlayerStatsPanel psp = new PlayerStatsPanel();
//		this.add(psp);
		
//		playerToday = new JButton();
//		playerToday.setBounds(75, 398, 145, 180);
//		playerToday.setContentAreaFilled(false);
//		playerToday.setBorder(null);
//		playerToday.setIcon(HotspotUtil.playerTodayIcon);
//		playerToday.addMouseListener(new PlayerTodayListener());
//		this.add(playerToday);
//		
//		playerSeason = new JButton();
//		playerSeason.setBounds(310, 398, 145, 180);
//		playerSeason.setContentAreaFilled(false);
//		playerSeason.setBorder(null);
//		playerSeason.setIcon(HotspotUtil.playerSeasonIcon);
//		playerSeason.addMouseListener(new PlayerSeasonListener());
//		this.add(playerSeason);
//		
//		teamSeason = new JButton();
//		teamSeason.setBounds(545, 398, 145, 180);
//		teamSeason.setContentAreaFilled(false);
//		teamSeason.setBorder(null);
//		teamSeason.setIcon(HotspotUtil.teamSeasonIcon);
//		teamSeason.addMouseListener(new TeamSeasonListener());
//		this.add(teamSeason);
//		
//		playerFast = new JButton();
//		playerFast.setBounds(780, 398, 145, 180);
//		playerFast.setContentAreaFilled(false);
//		playerFast.setBorder(null);
//		playerFast.setIcon(HotspotUtil.playerFastIcon);
//		playerFast.addMouseListener(new PlayerFastListener());
//		this.add(playerFast);
	}
	
	
	
	/**
	 * @param point 要判断的点
	 * @param polygon 多边形顶点集合
	 * @return 点是否在多边形范围内
	 */
	public boolean checkWithJdkPolygon(Point2D point, Point2D[] polygon) {
	    java.awt.Polygon p = new Polygon();
	    // java.awt.geom.GeneralPath
	    final int TIMES = 1000;
	    for (Point2D d : polygon) {
	        int x = (int) d.getX() * TIMES;
	        int y = (int) d.getY() * TIMES;
	        p.addPoint(x, y);
	    }
	    int x = (int) point.getX() * TIMES;
	    int y = (int) point.getY() * TIMES;
	    return p.contains(x, y);
	}
	
	public void run(){
		switch(runType){
		case player:
			rightBt.setVisible(true);
			
			for(int i=0;i<600;i++){
				int x = bluePanel.getX();
				x--;
				bluePanel.setLocation(x, bluePanel.getY());
				
				StatsUI.this.repaint();
				
				try{
					Thread.sleep(1);
				}catch(Exception ex){}
			}
			
			PlayerStatsPanel psp = new PlayerStatsPanel();
			StatsUI.this.add(psp);
			StatsUI.this.repaint();
			break;
			
		case team:
			rightBt.setVisible(true);
			
			for(int i=0;i<600;i++){
				int x = bluePanel.getX();
				x--;
				bluePanel.setLocation(x, bluePanel.getY());
				
				StatsUI.this.repaint();
				
				try{
					Thread.sleep(1);
				}catch(Exception ex){}
			}
			
			TeamStatsPanel tsp = new TeamStatsPanel();
			StatsUI.this.add(tsp);
			StatsUI.this.repaint();
			break;
			
		case back:
		
		}
		
		
	}
	
	
	class StatsListener implements MouseListener,MouseMotionListener{

		public void mouseClicked(MouseEvent e) {
			Point p = e.getPoint();
			if(StatsUI.this.checkWithJdkPolygon(p, polygonPlayer)){
				runType = RunType.player;
				Thread thread = new Thread(StatsUI.this);
				thread.start();
			}else if(StatsUI.this.checkWithJdkPolygon(p, polygonTeam)){
				runType = RunType.team;
				Thread thread = new Thread(StatsUI.this);
				thread.start();
				
			}
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseMoved(MouseEvent e) {
			Point p = e.getPoint();
			if(StatsUI.this.checkWithJdkPolygon(p, polygonPlayer)){
				titleLabel.setIcon(StatsUtil.title_player);
			}else if(StatsUI.this.checkWithJdkPolygon(p, polygonTeam)){
				titleLabel.setIcon(StatsUtil.title_team);
			}else{
				titleLabel.setIcon(StatsUtil.title);
			}
		}
		
	}

}
