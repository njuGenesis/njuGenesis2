package presentation.contenui;

import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import presentation.component.BgPanel;
import presentation.component.DateLabel;
import presentation.component.GLabel;
import presentation.match.MatchFactory;
import bussinesslogic.match.MatchLogic;
import data.po.MatchDataPO;

public class MatchUI extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String bgStr = "img/hotspot/whitebg.jpg";
	
	GLabel title;
	GLabel date;
	DateLabel dateChooser;
	
	MatchLogic logic = new MatchLogic();
	MatchFactory factory = new MatchFactory();
	JScrollPane matchPane;
	

	public MatchUI(String s) {
		super(bgStr);

		this.setSize(1000, 650);
		this.setLocation(15, 50);
		this.setLayout(null);
		this.setOpaque(false);
		
		dateChooser = new DateLabel(new Point(800-this.getX(),42),this);
		
		title = new GLabel("   比赛",new Point(30,30),new Point(940,52),this,true,0,24);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);
		
		date = new GLabel(getToday(),new Point(30,83),new Point(940,35),this,true,0,16);
		date.setOpaque(true);
		date.setBackground(UIUtil.bgGrey);
		date.setForeground(UIUtil.bgWhite);
		date.setHorizontalAlignment(JLabel.CENTER);
		
		getMatchJSP();


	}
	
	public void getMatchJSP(){
		String d = dateChooser.getText();
		
		ArrayList<MatchDataPO> matches = logic.GetAllInfo("13-14");
		
		JScrollPane jsp = factory.getMatchPane(matches);
		matchPane = jsp;
		this.add(matchPane);
		this.repaint();
		
	}
	
	
	private String getToday(){
		Date dateNow = new Date();  
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy年M月d日");  
		String dateNowStr = dateFormat.format(dateNow);  
		return dateNowStr;
	}

}
