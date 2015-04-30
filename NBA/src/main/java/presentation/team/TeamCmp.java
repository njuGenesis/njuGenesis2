package presentation.team;

import java.awt.Dimension;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.omg.CORBA.PRIVATE_MEMBER;

import bussinesslogic.team.TeamLogic;
import data.po.TeamDataPO;
import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.HoriDynamicBarLeft;
import presentation.component.HoriDynamicBarRight;
import presentation.component.TeamImageAssist;
import presentation.contenui.UIUtil;

public class TeamCmp extends BgPanel{
	
	private TeamDataPO po;
	private TeamLogic teamLogic = new TeamLogic();
	private double[] dataLeft, dataRight;
	private TeamImageAssist assist = new TeamImageAssist();
	
	public TeamCmp(TeamDataPO po){
		super("");
		
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
		this.setBackground(UIUtil.bgWhite);
		this.setVisible(true);
		this.po = po;
		
		init();
	}
	private void init(){
		GLabel teamPic = new GLabel(assist.loadImageIcon("img/teams/"+po.getShortName()+".svg", 170, 170), new Point(80, 20), new Point(170, 170), this, true);
		GLabel vs = new GLabel("VS", new Point(454, 80), new Point(60, 60), this, true, 1, 40);
		GLabel defaultPic = new GLabel("img/teamDetials/default.png", new Point(742, 15), new Point(61, 146), this, true);
		GLabel defaultText = new GLabel("联盟平均", new Point(730, 171), new Point(200,30), this, true, 0, 20);
		
		String[] item = {"场均得分", "场均助攻", "场均篮板", "三分％", "罚球％"};
		dataLeft = new double[item.length];
		dataRight = new double[item.length];
		
		ArrayList<Double> rightList = teamLogic.getAvg();
		dataRight[0] = ShortDouble(rightList.get(0));
		dataRight[1] = ShortDouble(rightList.get(1));
		dataRight[2] = ShortDouble(rightList.get(2));
		dataRight[3] = ShortDouble(rightList.get(3)*100);
		dataRight[4] = ShortDouble(rightList.get(4)*100);
		
		dataLeft[0] = ShortDouble(po.getPPG());
		dataLeft[1] = ShortDouble(po.getAssitNumberPG());
		dataLeft[2] = ShortDouble(po.getBackBoardPG());
		dataLeft[3] = ShortDouble(po.getTPEff()*100);
		dataLeft[4] = ShortDouble(po.getFTEff()*100);
		
		for(int i=0;i<item.length;i++){
			GLabel label = new GLabel(item[i], new Point(430, 232+51*i), new Point(80, 40), this, true, 0, 15);
			label.setHorizontalAlignment(JLabel.CENTER);
			
			HoriDynamicBarLeft horiDynamicBarLeft = new HoriDynamicBarLeft(dataLeft[i], new Dimension(300, 40));
			horiDynamicBarLeft.setLocation(45, 232+51*i);
			this.add(horiDynamicBarLeft);

			HoriDynamicBarRight horiDynamicBarRight = new HoriDynamicBarRight(dataRight[i], new Dimension(300, 40));
			horiDynamicBarRight.setLocation(545, 232+51*i);
			this.add(horiDynamicBarRight);

			if(dataLeft[i] < dataRight[i]){
				horiDynamicBarLeft.setColor(UIUtil.bgGrey);
			}else{
				if(dataLeft[i] > dataRight[i]){
					horiDynamicBarRight.setColor(UIUtil.bgGrey);
				}
			}
			
			horiDynamicBarLeft.showOut();
			horiDynamicBarRight.showOut();
			
			this.repaint();
		}
	}
	private double ShortDouble(double d){
		DecimalFormat df = new DecimalFormat(".00");
		return Double.parseDouble(df.format(d));
	}
	@Override
	public void refreshUI(){
		if(this!=null){
			this.removeAll();
			this.init();
		}
	}
}
