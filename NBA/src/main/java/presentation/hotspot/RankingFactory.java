package presentation.hotspot;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import presentation.component.GLabel;
import presentation.component.TeamImageAssist;
import presentation.contenui.TableUtility;
import data.po.PlayerDataPO;

public class RankingFactory {
	
	TeamImageAssist imgAssist = new TeamImageAssist();
	
	public JPanel getPlayerToday(PlayerDataPO[] players,String type){
		JPanel panel = getInitPanel();
		String[] info = new String[5];
		
		for(int i=0;i<5;i++){
			if(type.equals("得分")){
				info[i] = String.valueOf(players[i].getPTS());
			}else if(type.equals("篮板")){
				info[i] = String.valueOf(players[i].getBackboard());
			}else if(type.equals("助攻")){
				info[i] = String.valueOf(players[i].getAssist());
			}else if(type.equals("盖帽")){
				info[i] = String.valueOf(players[i].getRejection());
			}else if(type.equals("抢断")){
				info[i] = String.valueOf(players[i].getSteal());
			}
		}
		
		panel.add(getPlayerPanel1(players[0],info[0]));
		panel.add(getPlayerPanel2(players[1],info[1]));
		panel.add(getPlayerPanel3(players[2],info[2]));
		panel.add(getPlayerPanel4(players[3],info[3]));
		panel.add(getPlayerPanel5(players[4],info[4]));
		
		return panel;
	}
	
	private JPanel getInitPanel(){
		JPanel p = new JPanel();
		p.setBounds(30, 120, 890, 500);
		p.setLayout(null);
		p.setOpaque(false);
		return p;
	}
	
	private JPanel getPlayerPanel1(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(0, 150-120, 370, 470);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_1,new Point(20,45),new Point(36,40),p,true);
		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/action/"+po.getName()+".png"),new Point(0,102),new Point(207,329),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getTeamName()+".svg", 95, 75),new Point(225,316),new Point(95,75),p,true);
		
		GLabel name = new GLabel(po.getName(),new Point(180,50),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(180,90),new Point(180,30),p,true,0,13);
		GLabel infoLabel = new GLabel(info,new Point(245,220),new Point(80,60),p,true,0,26);
		
		return p;
	}
	
	private JPanel getPlayerPanel2(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(370, 150-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_2,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/portrait/"+po.getName()+".png"),new Point(68,23),new Point(81,62),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getTeamName()+".svg", 95, 75),new Point(422,23),new Point(95,75),p,true);
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,13);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(80,60),p,true,0,26);
		
		return p;
	}
	
	private JPanel getPlayerPanel3(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(370, 260-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_3,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/portrait/"+po.getName()+".png"),new Point(68,23),new Point(81,62),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getTeamName()+".svg", 95, 75),new Point(422,23),new Point(95,75),p,true);
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,13);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(80,60),p,true,0,26);
		
		return p;
	}
	
	private JPanel getPlayerPanel4(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(370, 370-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_4,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/portrait/"+po.getName()+".png"),new Point(68,23),new Point(81,62),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getTeamName()+".svg", 95, 75),new Point(422,23),new Point(95,75),p,true);
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,13);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(80,60),p,true,0,26);
		
		return p;
	}
	
	private JPanel getPlayerPanel5(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(370, 480-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_5,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/portrait/"+po.getName()+".png"),new Point(68,23),new Point(81,62),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getTeamName()+".svg", 95, 75),new Point(422,23),new Point(95,75),p,true);
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,13);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(80,60),p,true,0,26);
		
		return p;
	}

}
