package presentation.component;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

import presentation.contenui.UIUtil;
import presentation.hotspot.HotspotUtil;

public class DateLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DateLabel(Point location,Container con){
		Date dateNow = new Date();  
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");  
		String dateNowStr = dateFormat.format(dateNow);  
		
		this.setText(dateNowStr);
		this.setLocation(location);
		this.setSize(130, 30);
		this.setFont(new Font("微软雅黑",1,13));
		this.setOpaque(true);
		this.setBackground(UIUtil.darkBlue);
		this.setForeground(UIUtil.bgWhite);
		this.setIcon(HotspotUtil.dateIcon);
		
		Chooser ser = Chooser.getInstance(this.getForeground());
        ser.register(this);
		
		con.add(this);
	}
	
	public DateLabel(Point location,Container con,Color fore){
		Date dateNow = new Date();  
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");  
		String dateNowStr = dateFormat.format(dateNow);  
		
		this.setText(dateNowStr);
		this.setLocation(location);
		this.setSize(130, 30);
		this.setFont(new Font("微软雅黑",1,13));
		this.setOpaque(true);
		this.setBackground(UIUtil.darkBlue);
		this.setForeground(fore);
		this.setIcon(HotspotUtil.dateIcon);
		
		Chooser ser = Chooser.getInstance(this.getForeground());
        ser.register(this);
		
		con.add(this);
	}
	
	
	
	
	

}
