package presentation.contenui;

import data.po.PlayerDataPO;
import data.po.TeamDataPO;
import presentation.component.BgPanel;

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
	
	public BgPanel turnToTeamDetials(TeamDataPO po){
		BgPanel newPanel = new TeamDetials(po);
		newPanel.setBounds(15, 50, 1000, 650);
		newPanel.setVisible(false);
		return newPanel;
	}
	
	public BgPanel turnToPlayerDetials(PlayerDataPO po){
		BgPanel newPanel = new PlayerDetials(po);
		newPanel.setBounds(15, 50, 1000, 650);
		newPanel.setVisible(false);
		return newPanel;
	}
}