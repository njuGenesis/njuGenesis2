package presentation.contenui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.stats.PlayerStatsPanelNew;
import presentation.stats.TeamStatsPanelNew;

public class StatsUI extends BgPanel implements Runnable{

	private static final long serialVersionUID = 1L;


	private static String bgStr = "img/hotspot/whitebg.jpg";

	private BgPanel bluePanel;

	private JLabel titleLabel;
	private GLabel rightBt;

	private BgPanel statsPanel;

	private GLabel text;


	Point2D[] polygonPlayer = {new Point(163-78-15,325-149),new Point(93-78-15,395-149),new Point(163-78-15,466-149),new Point(234-78-15,395-149)};
	Point2D[] polygonTeam = {new Point(515-78-15,185-149),new Point(445-78-15,255-149),new Point(515-78-15,326-149),new Point(586-78-15,255-149)};

	private RunType runType;

	enum RunType{
		team,
		player,
		back,
	}

	@Override
	public void refreshUI() {
		if(statsPanel!=null){
			statsPanel.refreshUI();
		}else{
			this.remove(text);
			this.remove(bluePanel);
			this.remove(titleLabel);
			this.remove(rightBt);
			
			init();
		}
	}

	public StatsUI(String s) {
		super(bgStr);

		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		this.setSize(1000, 650);
		this.setLocation(15, 50);
		this.setLayout(null);
		this.setOpaque(false);

		init();
	}

	private void init(){
		text = new GLabel(StatsUtil.text_stats,new Point(700, 120), new Point(260, 370), this, true);

		bluePanel = new BgPanel("");
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

		rightBt = new GLabel(StatsUtil.rightIcon, new Point(633-15, 310), new Point(16, 30), bluePanel, false);
		rightBt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightBt.addMouseListener(new BackListener());
		
		this.repaint();
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

			text.setVisible(false);

			for(int i=0;i<600;i++){
				int x = bluePanel.getX();
				x--;
				bluePanel.setLocation(x, bluePanel.getY());

				StatsUI.this.repaint();

				try{
					Thread.sleep(1);
				}catch(Exception ex){}
			}

			if(statsPanel != null){
				this.remove(statsPanel);
			}
			//			PlayerStatsPanel psp = new PlayerStatsPanel();
			statsPanel = new PlayerStatsPanelNew();
			StatsUI.this.add(statsPanel);
			rightBt.setVisible(true);
			StatsUI.this.repaint();
			break;

		case team:

			text.setVisible(false);

			for(int i=0;i<600;i++){
				int x = bluePanel.getX();
				x--;
				bluePanel.setLocation(x, bluePanel.getY());

				StatsUI.this.repaint();

				try{
					Thread.sleep(1);
				}catch(Exception ex){}
			}

			if(statsPanel != null){
				this.remove(statsPanel);
			}
			statsPanel = new TeamStatsPanelNew();
			StatsUI.this.add(statsPanel);
			rightBt.setVisible(true);
			StatsUI.this.repaint();
			break;

		case back:
			if(statsPanel != null){
				this.remove(statsPanel);
				statsPanel = null;
			}
			for(int i=0;i<600;i++){
				int x = bluePanel.getX();
				x++;
				bluePanel.setLocation(x, bluePanel.getY());

				StatsUI.this.repaint();

				try{
					Thread.sleep(1);
				}catch(Exception ex){}
			}

			rightBt.setVisible(false);
			text.setVisible(true);
			StatsUI.this.repaint();
			break;

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
			titleLabel.setIcon(StatsUtil.title);
			titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseMoved(MouseEvent e) {
			Point p = e.getPoint();
			if(StatsUI.this.checkWithJdkPolygon(p, polygonPlayer)){
				titleLabel.setIcon(StatsUtil.title_player);
				titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				text.setIcon(StatsUtil.text_player);
			}else if(StatsUI.this.checkWithJdkPolygon(p, polygonTeam)){
				titleLabel.setIcon(StatsUtil.title_team);
				titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				text.setIcon(StatsUtil.text_team);
			}else{
				titleLabel.setIcon(StatsUtil.title);
				titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				text.setIcon(StatsUtil.text_stats);
			}
		}

	}

	class BackListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			runType = RunType.back;
			Thread thread = new Thread(StatsUI.this);
			thread.start();
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

	}

}
