package presentation.contenui;

import java.util.ArrayList;

import presentation.component.BgPanel;
import presentation.mainui.StartUI;
import presentation.match.MatchDetailPanel;
import presentation.player.PlayerDetials;
import presentation.player.PlayerUI;
import presentation.team.TeamDetials;
import presentation.team.TeamUI;
import bussinesslogic.match.MatchLogic;
import data.po.MatchDataPO;
import data.po.PlayerDataPO;

public class TurnController {

	public TurnController(){
		
	}
	
	public BgPanel turn(PanelKind goingTo){
		BgPanel newPanel = null;
		switch(goingTo){
		case HOT:newPanel = new HotUI("img/Framebg/Hot.png");break;
		case TEAM:newPanel = new TeamUI();break;
		case PLAYER:newPanel = new PlayerUI();break;
		case MATCH:newPanel = new MatchUI("img/Framebg/Match.png");break;
		case STATS:newPanel = new StatsUI("img/Framebg/Stats.png");break;
		}
		newPanel.setBounds(15, 50, 1000, 650);
		newPanel.setVisible(false);
		return newPanel;
	}
	
//	public 
	
	public BgPanel turnToTeamDetials(String shortName){
		BgPanel newPanel = new TeamDetials(shortName);
		newPanel.setBounds(15, 50, 1000, 650);
		newPanel.setVisible(false);
		
		StartUI.startUI.setMenu(1);
		
		return newPanel;
	}
	
	public BgPanel turnToPlayerDetials(String name){
		BgPanel newPanel = new PlayerDetials(name);
		newPanel.setBounds(15, 50, 1000, 650);
		newPanel.setVisible(false);
		
		StartUI.startUI.setMenu(2);
		
		return newPanel;
	}
	
	//date格式为13-14_01-01
	public BgPanel turnToMatchDetials(String date,String shortName){
		MatchLogic l = new MatchLogic();
		ArrayList<MatchDataPO> pos = l.GetInfo(date, date, shortName);
		
		BgPanel newPanel = new MatchDetailPanel(pos.get(0));
		newPanel.setBounds(15, 50, 1000, 650);
		newPanel.setVisible(false);
		
		StartUI.startUI.setMenu(3);
		
		return newPanel;
	}
}