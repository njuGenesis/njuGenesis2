package presentation.hotspot;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import data.po.PlayerDataPO;
import bussinesslogic.player.PlayerLogic;
import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.contenui.UIUtil;

public class HotPlayerProgressPanel extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String bg = "";
	
	GLabel title;

	SelectLabel score;  //得分
	SelectLabel backboard;  //篮板
	SelectLabel assis;  //助攻
	SelectLabel block;  //盖帽
	SelectLabel steal;  //抢断
	SelectLabel[] menuItem = new SelectLabel[3];

	PlayerLogic logic = new PlayerLogic();


	RankingFactory factory = new RankingFactory();
	JPanel rankingPanel;

	public HotPlayerProgressPanel() {
		super(bg);
		
		
		this.setBounds(50, 0, 950, 650);
		this.setLayout(null);
		this.setOpaque(false);


		title = new GLabel("   进步最快球员",new Point(80-this.getX(),30),new Point(890,52),this,true,0,24);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);

		score = new SelectLabel("场均得分",new Point(80-this.getX(),83),new Point(296,35),this,true,0,16);
		score.setOpaque(true);
		score.setBackground(UIUtil.bgGrey);
		score.setForeground(UIUtil.bgWhite);
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setSelected(true);
		score.addMouseListener(new MenuListener());
		menuItem[0] = score;

		getRankingPanel("场均得分");

		backboard = new SelectLabel("场均篮板",new Point(377-this.getX(),83),new Point(296,35),this,true,0,16);
		backboard.setOpaque(true);
		backboard.setBackground(UIUtil.bgGrey);
		backboard.setForeground(UIUtil.bgWhite);
		backboard.setHorizontalAlignment(JLabel.CENTER);
		backboard.addMouseListener(new MenuListener());
		menuItem[1] = backboard;

		assis = new SelectLabel("场均助攻",new Point(674-this.getX(),83),new Point(296,35),this,true,0,16);
		assis.setOpaque(true);
		assis.setBackground(UIUtil.bgGrey);
		assis.setForeground(UIUtil.bgWhite);
		assis.setHorizontalAlignment(JLabel.CENTER);
		assis.addMouseListener(new MenuListener());
		menuItem[2] = assis;

		this.repaint();
	}

	public void getRankingPanel(String type){
		Date dateNow = new Date();  
		SimpleDateFormat dateFormat = new SimpleDateFormat ("MM-dd");  
		String dateNowStr = dateFormat.format(dateNow);  

//		System.out.println(dateNowStr);
		PlayerDataPO[] players = logic.progressPlayer("13-14", type);
		JPanel p = factory.getPlayerProgress(players,type);
		rankingPanel = p;
		this.add(rankingPanel);
		this.repaint();
	}

	class MenuListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			SelectLabel sl = (SelectLabel)e.getSource();

			for(int i=0;i<menuItem.length;i++){
				menuItem[i].setSelected(false);
			}
			sl.setSelected(true);

			if(rankingPanel!=null){
				HotPlayerProgressPanel.this.remove(rankingPanel);
			}

			String type = sl.getText();
			HotPlayerProgressPanel.this.getRankingPanel(type);
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
