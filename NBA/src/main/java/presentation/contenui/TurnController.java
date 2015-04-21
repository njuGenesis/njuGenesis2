package presentation.contenui;

import java.io.FileNotFoundException;
import org.apache.batik.transcoder.TranscoderException;
import presentation.component.BgPanel;

public class TurnController {

	public TurnController(){
		
	}
	
	public BgPanel turn(PanelKind goingTo){
		BgPanel newPanel = null;
		switch(goingTo){
		case HOT:newPanel = new HotUI("img/Framebg/Hot.png");break;
		case TEAM:newPanel = new TeamUI();break;
		case PLAYER:newPanel = new PlayerUI("img/Framebg/Player.png");break;
		case MATCH:newPanel = new MatchUI("img/Framebg/Match.png");break;
		case STATS:newPanel = new StatsUI("img/Framebg/Stats.png");break;
		}
		newPanel.setVisible(false);
		return newPanel;
	}
}