package presentation.team;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.TeamImageAssist;
import presentation.contenui.UIUtil;
import data.po.TeamDataPO;

public class TeamInfo extends BgPanel{

	private static final long serialVersionUID = 1L;
	private static String file = "img/teamDetials/info.png";
	
	private TeamImageAssist assist;
	private GLabel teamPic, name, shortName, city, time, position;
	private JTextArea place;
	private TeamDataPO po;
	
	public TeamInfo(TeamDataPO po){
		super(file);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		
		assist = new TeamImageAssist();
		this.po = po;
		
		init();
	}
	private void init(){
		teamPic = new GLabel(assist.loadImageIcon("img/teams/"+po.getShortName()+".svg", 360, 360), new Point(281, 60), new Point(360, 360), this, true);
		name = new GLabel(po.getName(), new Point(103, 40), new Point(180, 25), this, true, 0, 20);
		shortName = new GLabel(po.getShortName(), new Point(103, 75), new Point(180, 25), this, true, 0, 20);
		city = new GLabel(po.getCity(), new Point(733, 144), new Point(180, 25), this, true, 0, 20);
		time = new GLabel(String.valueOf(po.getBuildyear()), new Point(762, 179), new Point(180, 25), this, true, 0, 20);
		position = new GLabel(po.getEorW()+"-"+po.getArea(), new Point(246, 467), new Point(180, 25), this, true, 0, 20);
		place = new JTextArea();
		place.setEditable(false);
		place.setLineWrap(true);
		place.setWrapStyleWord(true);
		place.setBounds(733, 211, 200, 50);
		place.setText(po.getMainposition());
		place.setFont(new Font("微软雅黑", 0, 20));
		place.setOpaque(false);
		place.setBackground(UIUtil.bgWhite);
		place.setBorder(null);
		place.setLineWrap(true);
		place.setWrapStyleWord(true);
		this.add(place);
	}
	@Override
	public void refreshUI(){
		if(this!=null){
			this.removeAll();
			this.init();
		}
	}
}
