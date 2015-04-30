package presentation.hotspot;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import presentation.component.GLabel;
import presentation.component.TeamImageAssist;
import presentation.contenui.TableUtility;
import presentation.contenui.TurnController;
import presentation.mainui.StartUI;
import data.po.PlayerDataPO;
import data.po.TeamDataPO;

public class RankingFactory {
	
	TeamImageAssist imgAssist = new TeamImageAssist();
	
	public JPanel getTeamSeason(ArrayList<TeamDataPO> teams,String type){
		JPanel panel = getInitPanel();
		String[] info = new String[5];
		
		for(int i=0;i<5;i++){
			if(type.equals("场均得分")){
				info[i] = String.valueOf(teams.get(i).getPPG());
			}else if(type.equals("场均篮板")){
				info[i] = String.valueOf(teams.get(i).getBackBoardPG());
			}else if(type.equals("场均助攻")){
				info[i] = String.valueOf(teams.get(i).getAssitNumberPG());
			}else if(type.equals("场均盖帽")){
				info[i] = String.valueOf(teams.get(i).getRejectionPG());
			}else if(type.equals("场均抢断")){
				info[i] = String.valueOf(teams.get(i).getStealNumberPG());
			}else if(type.equals("三分命中率")){
				info[i] = getPercent(teams.get(i).getTPEff());
			}else if(type.equals("投篮命中率")){
				info[i] = getPercent(teams.get(i).getShootEff());
			}else if(type.equals("罚球命中率")){
				info[i] = getPercent(teams.get(i).getFTEff());
			}
		}
		
		panel.add(getTeamPanel1(teams.get(0),info[0]));
		panel.add(getTeamPanel2(teams.get(1),info[1]));
		panel.add(getTeamPanel3(teams.get(2),info[2]));
		panel.add(getTeamPanel4(teams.get(3),info[3]));
		panel.add(getTeamPanel5(teams.get(4),info[4]));
		
		return panel;
	}
	
	public JPanel getPlayerProgress(PlayerDataPO[] players,String type){
		JPanel panel = getInitPanel();
		String[] info = new String[5];
		
		for(int i=0;i<5;i++){
			if(type.equals("场均得分")){
				info[i] = String.valueOf(players[i].getRecentAvgP()) + "/" + String.valueOf(players[i].getPProgressPecentage());
			}else if(type.equals("场均篮板")){
				info[i] = String.valueOf(players[i].getRecentAvgB()) + "/" + String.valueOf(players[i].getBProgressPecentage());
			}else if(type.equals("场均助攻")){
				info[i] = String.valueOf(players[i].getRecentAvgA()) + "/" + String.valueOf(players[i].getAProgressPecentage());
			}
		}
		
		panel.add(getPlayerPanel1(players[0],info[0]));
		panel.add(getPlayerPanel2(players[1],info[1]));
		panel.add(getPlayerPanel3(players[2],info[2]));
		panel.add(getPlayerPanel4(players[3],info[3]));
		panel.add(getPlayerPanel5(players[4],info[4]));
		
		return panel;
	}
	
	public JPanel getPlayerSeason(PlayerDataPO[] players,String type){
		JPanel panel = getInitPanel();
		String[] info = new String[5];
		
		for(int i=0;i<5;i++){
			if(type.equals("场均得分")){
				info[i] = String.valueOf(players[i].getPPG());
			}else if(type.equals("场均篮板")){
				info[i] = String.valueOf(players[i].getBPG());
			}else if(type.equals("场均助攻")){
				info[i] = String.valueOf(players[i].getAPG());
			}else if(type.equals("场均盖帽")){
				info[i] = String.valueOf(players[i].getRPG());
			}else if(type.equals("场均抢断")){
				info[i] = String.valueOf(players[i].getStealPG());
			}else if(type.equals("三分命中率")){
				info[i] = getPercent(players[i].getThreePGPercentage());
			}else if(type.equals("投篮命中率")){
				info[i] = getPercent(players[i].getFieldGoalPercentage());
			}else if(type.equals("罚球命中率")){
				info[i] = getPercent(players[i].getFTPercentage());
			}
		}
		
		panel.add(getPlayerPanel1(players[0],info[0]));
		panel.add(getPlayerPanel2(players[1],info[1]));
		panel.add(getPlayerPanel3(players[2],info[2]));
		panel.add(getPlayerPanel4(players[3],info[3]));
		panel.add(getPlayerPanel5(players[4],info[4]));
		
		return panel;
	}
	
	public JPanel getPlayerToday(PlayerDataPO[] players,String type){
		JPanel panel = getInitPanel();
		String[] info = new String[5];
		
		if(!(players[0].getName()==null)){
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
		}else{
			GLabel l = new GLabel(new ImageIcon("img/match/nogame.png"),new Point(350,150),new Point(200,200),panel,true);
		}
		
		return panel;
	}
	
	private String getPercent(double d){
		DecimalFormat df = new DecimalFormat("#.0");  
		return String.valueOf(df.format(d*100)) + "%";
	}
	
	private JPanel getInitPanel(){
		JPanel p = new JPanel();
		p.setBounds(30, 120, 890, 500);
		p.setLayout(null);
		p.setOpaque(false);
		return p;
	}
	
	private JPanel getTeamPanel1(TeamDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(0, 150-120, 370, 470);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_1,new Point(20,45),new Point(36,40),p,true);
//		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/action/"+po.getName()+".png"),new Point(0,102),new Point(207,329),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getShortName()+".svg", 200, 300),new Point(0,102),new Point(200,300),p,true);
		team.addMouseListener(new TeamListener(po.getShortName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(TableUtility.getChTeam(po.getName()),new Point(160,40),new Point(200,30),p,true,0,20);
		GLabel detail = new GLabel(TableUtility.getChUnion(po.getEorW()),new Point(160,80),new Point(200,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(230,220),new Point(130,60),p,true,0,24);
		
		return p;
	}
	
	private JPanel getTeamPanel2(TeamDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(350, 150-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_2,new Point(18,38),new Point(36,40),p,true);
//		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/portrait/"+po.getName()+".png"),new Point(68,23),new Point(81,62),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getShortName()+".svg", 95, 75),new Point(68,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getShortName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(TableUtility.getChTeam(po.getName()),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(TableUtility.getChUnion(po.getEorW()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(130,60),p,true,0,22);
		
		return p;
	}
	
	private JPanel getTeamPanel3(TeamDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(350, 260-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_3,new Point(18,38),new Point(36,40),p,true);
//		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/portrait/"+po.getName()+".png"),new Point(68,23),new Point(81,62),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getShortName()+".svg", 95, 75),new Point(68,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getShortName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(TableUtility.getChTeam(po.getName()),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(TableUtility.getChUnion(po.getEorW()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(130,60),p,true,0,22);
		
		return p;
	}
	
	private JPanel getTeamPanel4(TeamDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(350, 370-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_4,new Point(18,38),new Point(36,40),p,true);
//		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/portrait/"+po.getName()+".png"),new Point(68,23),new Point(81,62),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getShortName()+".svg", 95, 75),new Point(68,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getShortName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(TableUtility.getChTeam(po.getName()),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(TableUtility.getChUnion(po.getEorW()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(130,60),p,true,0,22);
		
		return p;
	}
	
	private JPanel getTeamPanel5(TeamDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(350, 480-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_5,new Point(18,38),new Point(36,40),p,true);
//		GLabel player = new GLabel(new ImageIcon("迭代一数据/players/portrait/"+po.getName()+".png"),new Point(68,23),new Point(81,62),p,true);
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getShortName()+".svg", 95, 75),new Point(68,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getShortName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(TableUtility.getChTeam(po.getName()),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(TableUtility.getChUnion(po.getEorW()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(130,60),p,true,0,22);
		
		return p;
	}
	
	private JPanel getPlayerPanel1(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(0, 150-120, 370, 470);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_1,new Point(20,45),new Point(36,40),p,true);
		GLabel player = new GLabel(getBigPlayer(po.getName()),new Point(0,102),new Point(207,329),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(215,315),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(160,40),new Point(200,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(160,80),new Point(200,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(230,220),new Point(130,60),p,true,0,24);
		
		return p;
	}
	
	private JPanel getPlayerPanel2(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(350, 150-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_2,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(getPlayer(po.getName()),new Point(68,23),new Point(81,62),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(435,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(130,60),p,true,0,22);
		
		return p;
	}
	
	private JPanel getPlayerPanel3(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(350, 260-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_3,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(getPlayer(po.getName()),new Point(68,23),new Point(81,62),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(435,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(130,60),p,true,0,22);
		
		return p;
	}
	
	private JPanel getPlayerPanel4(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(350, 370-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_4,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(getPlayer(po.getName()),new Point(68,23),new Point(81,62),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(435,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(130,60),p,true,0,22);
		
		return p;
	}
	
	private JPanel getPlayerPanel5(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(350, 480-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_5,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(getPlayer(po.getName()),new Point(68,23),new Point(81,62),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(435,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,13);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(130,60),p,true,0,22);
		
		return p;
	}
	
	
	private JPanel getProgressPanel1(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(0, 150-120, 370, 470);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_1,new Point(20,45),new Point(36,40),p,true);
		GLabel player = new GLabel(getBigPlayer(po.getName()),new Point(0,102),new Point(207,329),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(225,316),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(180,50),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(180,90),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(245,220),new Point(80,60),p,true,0,26);
		
		return p;
	}
	
	private JPanel getProgressPanel2(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(370, 150-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_2,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(getPlayer(po.getName()),new Point(68,23),new Point(81,62),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(422,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(80,60),p,true,0,24);
		
		return p;
	}
	
	private JPanel getProgressPanel3(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(370, 260-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_3,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(getPlayer(po.getName()),new Point(68,23),new Point(81,62),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(422,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(80,60),p,true,0,24);
		
		return p;
	}
	
	private JPanel getProgressPanel4(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(370, 370-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_4,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(getPlayer(po.getName()),new Point(68,23),new Point(81,62),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(422,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,12);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(80,60),p,true,0,24);
		
		return p;
	}
	
	private JPanel getProgressPanel5(PlayerDataPO po,String info){
		JPanel p = new JPanel();
		p.setBounds(370, 480-120, 520, 110);
		p.setLayout(null);
		p.setOpaque(false);
		
		GLabel num = new GLabel(HotspotUtil.ranking_5,new Point(18,38),new Point(36,40),p,true);
		GLabel player = new GLabel(getPlayer(po.getName()),new Point(68,23),new Point(81,62),p,true);
		player.addMouseListener(new PlayerListener(po.getName()));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+TableUtility.checkNOH(po.getTeamName())+".svg", 95, 75),new Point(422,23),new Point(95,75),p,true);
		team.addMouseListener(new TeamListener(po.getTeamName()));
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel name = new GLabel(po.getName(),new Point(170,30),new Point(180,30),p,true,0,20);
		GLabel detail = new GLabel(po.getNumber()+" / "+TableUtility.getChPosition(po.getPosition())+" / "+TableUtility.getChTeam(po.getTeamName()),new Point(170,60),new Point(180,30),p,true,0,13);
		GLabel infoLabel = new GLabel(info,new Point(354,40),new Point(80,60),p,true,0,24);
		
		return p;
	}
	
	private ImageIcon getBigPlayer(String name){
		File f = new File("迭代一数据/players/action/"+name+".png");
		if(f.exists()){
			return new ImageIcon("迭代一数据/players/action/"+name+".png");
		}else{
			return HotspotUtil.noBigPlayer;
		}
	}
	
	private ImageIcon getPlayer(String name){
		File f = new File("迭代一数据/players/portrait/"+name+".png");
		if(f.exists()){
			return new ImageIcon("迭代一数据/players/portrait/"+name+".png");
		}else{
			return HotspotUtil.noPlayer;
		}
	}

	class TeamListener implements MouseListener{
		
		String shortName;
		
		public TeamListener(String shortName){
			this.shortName = shortName;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			TurnController tc = new TurnController();
			StartUI.startUI.turn(tc.turnToTeamDetials(shortName));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class PlayerListener implements MouseListener{
		
		String name;
		
		public PlayerListener(String name){
			this.name = name;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			TurnController tc = new TurnController();
			StartUI.startUI.turn(tc.turnToPlayerDetials(name));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
}
