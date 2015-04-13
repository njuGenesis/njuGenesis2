package presentation.contenui;

import java.awt.Container;

import javax.swing.JPanel;

import presentation.component.BgPanel;
import presentation.mainui.StartUI;

public class TurnController {

	public TurnController(){
		
	}
	
	public BgPanel turn(PanelKind goingTo){
		BgPanel newPanel = null;
		switch(goingTo){
		case HOT:newPanel = new HotUI("img/Framebg/Hot.png");break;
		case TEAM:newPanel = new TeamUI("img/Framebg/Team.png");break;
		case PLAYER:newPanel = new PlayerUI("img/Framebg/Player.png");break;
		case MATCH:newPanel = new MatchUI("img/Framebg/Match.png");break;
		case STATS:newPanel = new StatsUI("img/Framebg/Stats.png");break;
		}
		return newPanel;
	}
}
