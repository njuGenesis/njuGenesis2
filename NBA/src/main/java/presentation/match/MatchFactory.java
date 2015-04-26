package presentation.match;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import presentation.component.GLabel;
import presentation.component.TeamImageAssist;
import presentation.contenui.TableUtility;
import presentation.contenui.UIUtil;
import data.po.MatchDataPO;

public class MatchFactory {
	
	private int oneHeight = 120;
	private int pointIntervalLR = 85;  //得分显示的左右间隔
	private int pointIntervalUD = 52;  //得分显示的上下间隔
	
	private TeamImageAssist imgAssist = new TeamImageAssist();
	
	
	public JScrollPane getMatchPane(ArrayList<MatchDataPO> matches){
		int num = matches.size();
		JPanel panel = new JPanel();
		panel.setLayout(null);
//		panel.setBounds(0, 0, 905, oneHeight*num);
		panel.setLocation(0, 0);
		panel.setPreferredSize(new Dimension(905, oneHeight*num));
		
		for(int i=0;i<num;i++){
			panel.add(getOneMatch(i,matches.get(i)));
		}
		
		JScrollPane jsp = new JScrollPane(panel);
		jsp.setLocation(30, 119);
		jsp.setBorder(null);
		jsp.setSize(940,500);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
//		jsp.setPreferredSize(new Dimension(910,500));
//		jsp.setViewportView(panel);
		
		return jsp;
	}
	
	//返回显示一场比赛的panel
	public JPanel getOneMatch(int num,MatchDataPO po){
		JPanel jp = new JPanel();
		jp.setBounds(0,(num-1)*oneHeight,935,oneHeight);
		jp.setLayout(null);
		jp.setOpaque(true);
		if(num%2!=0){
			jp.setBackground(UIUtil.tableGrey);
		}else{
			jp.setBackground(UIUtil.bgWhite);
		}
		
		int allPointX = 764;  //总得分的x坐标
		
		GLabel team1 = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getFirstteam()+".svg", 50, 40),new Point(20,22),new Point(50,40),jp,true);
		GLabel team2 = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getSecondteam()+".svg", 50, 40),new Point(20,74),new Point(50,40),jp,true);
		
		GLabel name1 = new GLabel(TableUtility.getShortChTeam(po.getFirstteam()),new Point(98,30),new Point(180,30),jp,true,0,18);
		GLabel name2 = new GLabel(TableUtility.getShortChTeam(po.getSecondteam()),new Point(98,82),new Point(180,30),jp,true,0,18);
		
		GLabel l1 = new GLabel("1",new Point(194,6),new Point(50,20),jp,true,0,13);
		GLabel point1_1 = new GLabel(getPoint1(po.getFirst_pts()),new Point(187,32),new Point(100,30),jp,true,0,18);
		GLabel point1_2 = new GLabel(getPoint2(po.getFirst_pts()),new Point(187,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point1_1,point1_2);
		
		GLabel l2 = new GLabel("2",new Point(279,6),new Point(50,20),jp,true,0,13);
		GLabel point2_1 = new GLabel(getPoint1(po.getSecond_pts()),new Point(187+pointIntervalLR,32),new Point(100,30),jp,true,0,18);
		GLabel point2_2 = new GLabel(getPoint2(po.getSecond_pts()),new Point(187+pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point2_1,point2_2);
		
		GLabel l3 = new GLabel("3",new Point(366,6),new Point(50,20),jp,true,0,13);
		GLabel point3_1 = new GLabel(getPoint1(po.getThird_pts()),new Point(187+2*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
		GLabel point3_2 = new GLabel(getPoint2(po.getThird_pts()),new Point(187+2*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point3_1,point3_2);
		
		GLabel l4 = new GLabel("4",new Point(452,6),new Point(50,20),jp,true,0,13);
		GLabel point4_1 = new GLabel(getPoint1(po.getForth_pts()),new Point(187+3*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
		GLabel point4_2 = new GLabel(getPoint2(po.getForth_pts()),new Point(187+3*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point4_1,point4_2);
		
		if(!po.getFifth_pts().equals("null")){
			GLabel l5 = new GLabel("加时赛1",new Point(520,6),new Point(50,20),jp,true,0,13);
			GLabel point5_1 = new GLabel(getPoint1(po.getFifth_pts()),new Point(187+4*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
			GLabel point5_2 = new GLabel(getPoint2(po.getFifth_pts()),new Point(187+4*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
			changeLabelColor(point5_1,point5_2);
		}
		
		if(!po.getSixth_pts().equals("null")){
			GLabel l6 = new GLabel("加时赛2",new Point(604,6),new Point(50,20),jp,true,0,13);
			GLabel point6_1 = new GLabel(getPoint1(po.getSixth_pts()),new Point(187+5*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
			GLabel point6_2 = new GLabel(getPoint2(po.getSixth_pts()),new Point(187+5*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
			changeLabelColor(point6_1,point6_2);
		}
		
		if(!po.getSeventh_pts().equals("null")){
			GLabel l7 = new GLabel("加时赛3",new Point(691,6),new Point(50,20),jp,true,0,13);
			GLabel point7_1 = new GLabel(getPoint1(po.getSeventh_pts()),new Point(187+6*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
			GLabel point7_2 = new GLabel(getPoint2(po.getSeventh_pts()),new Point(187+6*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
			changeLabelColor(point7_1,point7_2);
		}
		
		GLabel pointall_1 = new GLabel(getPoint1(po.getPoints()),new Point(764,27),new Point(120,30),jp,true,0,26);
		GLabel pointall_2 = new GLabel(getPoint2(po.getPoints()),new Point(764,27+pointIntervalUD),new Point(120,30),jp,true,0,26);
		changeLabelColor(pointall_1,pointall_2);
		
		MatchDetailLabel turn = new MatchDetailLabel(jp,po);
		
		return jp;
	}
	
	//比较某一小节或比赛两队得分高低，得分高用红色字体
	private void changeLabelColor(GLabel l1,GLabel l2){
		String s1 = l1.getText();
		String s2 = l2.getText();
		if(s1.compareTo(s2)>0){
			l1.setForeground(UIUtil.nbaRed);
		}else{
			l2.setForeground(UIUtil.nbaRed);
		}
	}
	
	
	//获得某一小节或比赛队伍一得分
	private String getPoint1(String str){
		return str.split("-")[0];
	}
	
	//获得某一小节或比赛队伍二得分
	private String getPoint2(String str){
		return str.split("-")[1];
	}
	
	
	public String getSeason(String date){
		String season = "13-14";
		
		int year = 2010;
		while(true){
			String d1 = String.valueOf(year)+"-10-01";
			String d2 = String.valueOf(year+1)+"-06-30";
			if(date.compareTo(d1)>=0 && date.compareTo(d2)<=0){
				season = String.valueOf(year).substring(2, 3)+"-"+String.valueOf(year+1).substring(2, 3);
				break;
			}else{
				year++;
			}
		}
		return season;
	}

}
