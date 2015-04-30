package presentation.hotspot;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import presentation.component.BgPanel;
import presentation.component.DatePanel;
import presentation.component.GLabel;
import presentation.contenui.UIUtil;
import bussinesslogic.player.PlayerLogic;
import data.po.PlayerDataPO;

public class HotPlayerTodayPanel extends BgPanel{

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
	SelectLabel[] menuItem = new SelectLabel[5];
	
	DatePanel date;

	PlayerLogic logic = new PlayerLogic();


	RankingFactory factory = new RankingFactory();
	JPanel rankingPanel;
	
	@Override
	public void refreshUI() {
		this.remove(title);
		this.remove(score);
		this.remove(backboard);
		this.remove(assis);
		this.remove(block);
		this.remove(steal);
		this.remove(rankingPanel);
		this.remove(date);

		init();
	}

	public HotPlayerTodayPanel() {
		super(bg);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}

		this.setBounds(50, 0, 950, 650);
		this.setLayout(null);
		this.setOpaque(false);
		
		init();
	}

	
	private void init(){
		date = new DatePanel(new Point(800-this.getX(),42),this);
		date.addDocuListener(new DateListener());


		title = new GLabel("   当天热点球员",new Point(80-this.getX(),30),new Point(890,52),this,true,0,24);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);

		score = new SelectLabel("得分",new Point(80-this.getX(),83),new Point(177,35),this,true,0,16);
		score.setOpaque(true);
		score.setBackground(UIUtil.bgGrey);
		score.setForeground(UIUtil.bgWhite);
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setSelected(true);
		score.addMouseListener(new MenuListener());
		menuItem[0] = score;

		getRankingPanel("得分",getToday());

		backboard = new SelectLabel("篮板",new Point(258-this.getX(),83),new Point(177,35),this,true,0,16);
		backboard.setOpaque(true);
		backboard.setBackground(UIUtil.bgGrey);
		backboard.setForeground(UIUtil.bgWhite);
		backboard.setHorizontalAlignment(JLabel.CENTER);
		backboard.addMouseListener(new MenuListener());
		menuItem[1] = backboard;

		assis = new SelectLabel("助攻",new Point(436-this.getX(),83),new Point(177,35),this,true,0,16);
		assis.setOpaque(true);
		assis.setBackground(UIUtil.bgGrey);
		assis.setForeground(UIUtil.bgWhite);
		assis.setHorizontalAlignment(JLabel.CENTER);
		assis.addMouseListener(new MenuListener());
		menuItem[2] = assis;

		block = new SelectLabel("盖帽",new Point(614-this.getX(),83),new Point(177,35),this,true,0,16);
		block.setOpaque(true);
		block.setBackground(UIUtil.bgGrey);
		block.setForeground(UIUtil.bgWhite);
		block.setHorizontalAlignment(JLabel.CENTER);
		block.addMouseListener(new MenuListener());
		menuItem[3] = block;

		steal = new SelectLabel("抢断",new Point(792-this.getX(),83),new Point(178,35),this,true,0,16);
		steal.setOpaque(true);
		steal.setBackground(UIUtil.bgGrey);
		steal.setForeground(UIUtil.bgWhite);
		steal.setHorizontalAlignment(JLabel.CENTER);
		steal.addMouseListener(new MenuListener());
		menuItem[4] = steal;

		this.repaint();
	}

	public void getRankingPanel(String type,String date){
		String season = getSeason(date);  
		String day = date.substring(5);

		PlayerDataPO[] players = logic.hotPlayerToday(season, day, type);
		JPanel p = factory.getPlayerToday(players,type);
		rankingPanel = p;
		this.add(rankingPanel);
		this.repaint();
	}
	
	public String getSeason(String date){
		String season = "13-14";

		int year = 2010;
		while(true){
			String d1 = String.valueOf(year)+"-10-01";
			String d2 = String.valueOf(year+1)+"-06-30";
			if(date.compareTo(d1)>=0 && date.compareTo(d2)<=0){
				season = String.valueOf(year).substring(2, 4)+"-"+String.valueOf(year+1).substring(2, 4);
				break;
			}else{
				year++;
			}
		}
		return season;
	}
	
	private String getToday(){
		Date dateNow = new Date();  
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");  
		return dateFormat.format(dateNow);
	}
	

	class MenuListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			SelectLabel sl = (SelectLabel)e.getSource();

			for(int i=0;i<menuItem.length;i++){
				menuItem[i].setSelected(false);
			}
			sl.setSelected(true);

			if(rankingPanel!=null){
				HotPlayerTodayPanel.this.remove(rankingPanel);
			}

			String type = sl.getText();
			HotPlayerTodayPanel.this.getRankingPanel(type,date.getText());
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
	
	
	class DateListener implements DocumentListener{

		public void insertUpdate(DocumentEvent e) {
			for(int i=0;i<menuItem.length;i++){
				menuItem[i].setSelected(false);
			}
			score.setSelected(true);

			if(rankingPanel!=null){
				HotPlayerTodayPanel.this.remove(rankingPanel);
			}
			getRankingPanel("得分",date.getText());

			HotPlayerTodayPanel.this.repaint();
		}

		public void removeUpdate(DocumentEvent e) {}

		public void changedUpdate(DocumentEvent e) {}

	}



}
