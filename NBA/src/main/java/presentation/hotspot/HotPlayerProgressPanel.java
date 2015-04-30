package presentation.hotspot;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.contenui.UIUtil;
import bussinesslogic.player.PlayerLogic;
import data.po.PlayerDataPO;

public class HotPlayerProgressPanel extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String bg = "";
	
	GLabel title;

	SelectLabel score;  //场均得分
	SelectLabel backboard;  //场均篮板
	SelectLabel assis;  //场均助攻
	SelectLabel[] menuItem = new SelectLabel[3];

	PlayerLogic logic = new PlayerLogic();
	
	JComboBox<String> seasonChooser;

	RankingFactory factory = new RankingFactory();
	JPanel rankingPanel;
	
	@Override
	public void refreshUI() {
		this.remove(title);
		this.remove(score);
		this.remove(backboard);
		this.remove(assis);
		this.remove(seasonChooser);
		this.remove(rankingPanel);

		init();
	}

	public HotPlayerProgressPanel() {
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
		String[] seasons = {"13-14赛季","12-13赛季",};
		seasonChooser = new JComboBox<String>(seasons);
		seasonChooser.setBounds(800-this.getX(), 42, 120, 30);
		seasonChooser.addActionListener(new SeasonListener());
		this.add(seasonChooser);

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

		PlayerDataPO[] players = logic.progressPlayer(getSeasonStr(), type);
		JPanel p = factory.getPlayerProgress(players,type);
		rankingPanel = p;
		this.add(rankingPanel);
		this.repaint();
	}

	private String getSeasonStr(){
		String s = (String)seasonChooser.getSelectedItem();
		return s.substring(0, 5);
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
	
	class SeasonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//更新标签
			for(int i=0;i<menuItem.length;i++){
				menuItem[i].setSelected(false);
			}
			score.setSelected(true);
			
			if(rankingPanel!=null){
				HotPlayerProgressPanel.this.remove(rankingPanel);
			}
			
			getRankingPanel("场均得分");
		}
		
	}
	
}
