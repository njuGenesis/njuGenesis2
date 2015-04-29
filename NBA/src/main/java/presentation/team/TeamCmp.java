package presentation.team;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;

import bussinesslogic.team.TeamLogic;
import data.po.TeamDataPO;
import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.HoriDynamicBarLeft;
import presentation.component.HoriDynamicBarRight;
import presentation.contenui.UIUtil;

public class TeamCmp extends BgPanel{
	
	private TeamDataPO po;
	private TeamLogic teamLogic = new TeamLogic();
	private double[] dataLeft, dataRight;
	
	public TeamCmp(TeamDataPO po){
		super("");
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		this.setVisible(true);
		this.po = po;
		
		String[] item = {"场均得分", "场均助攻", "场均篮板", "三分％", "罚球％"};
		dataLeft = new double[item.length];
		dataRight = new double[item.length];
		
		ArrayList<Double> leftList = teamLogic.getAvg();
		for(int i=0;i<leftList.size();i++){
			dataRight[i] = leftList.get(i);System.out.println(dataRight[i]);
		}
		dataRight[0] = po.getPTS();
		dataRight[1] = po.getAssitNumberPG();
		dataRight[2] = po.getBackBoardPG();
		dataRight[3] = po.getTPEff();
		dataRight[4] = po.getFTEff();
		
		for(int i=0;i<item.length;i++){
			GLabel label = new GLabel(item[i], new Point(430, 232+51*i), new Point(80, 40), this, true, 0, 15);
			label.setHorizontalAlignment(JLabel.CENTER);
			
			HoriDynamicBarLeft horiDynamicBarLeft = new HoriDynamicBarLeft(dataLeft[i], new Dimension(300, 40));
			horiDynamicBarLeft.setLocation(45, 232+51*i);
			this.add(horiDynamicBarLeft);

			HoriDynamicBarRight horiDynamicBarRight = new HoriDynamicBarRight(dataLeft[i], new Dimension(300, 40));
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
}
