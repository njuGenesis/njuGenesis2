package presentation.contenui;

import java.awt.Point;

import javax.swing.JPanel;

import presentation.component.GLabel;
import presentation.hotspot.SelectLabel;

public class TeamDetials extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GLabel title;
	private SelectLabel tdMenu[];
	
	public TeamDetials(){
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		this.setBounds(0, 0, 1000, 650);
		this.setVisible(true);
		
		title = new GLabel("球队", new Point(26, 30), new Point(948, 52), this, true, 0, 30);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);
		
		tdMenu = new SelectLabel[5];
		tdMenu[0] = new SelectLabel("资料", new Point(26, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[1] = new SelectLabel("球员", new Point(26+188+2, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[2] = new SelectLabel("比赛", new Point(26+(188+2)*2, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[3] = new SelectLabel("数据", new Point(26+(188+2)*3, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[4] = new SelectLabel("对比", new Point(26+(188+2)*4, 83), new Point(188, 35), this, true, 0, 18);
		
		
	}
}
