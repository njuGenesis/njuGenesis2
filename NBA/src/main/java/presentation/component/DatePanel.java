package presentation.component;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import presentation.contenui.UIUtil;
import presentation.hotspot.HotspotUtil;

/*
 * 日期组件
 * 通过addDocuListener方法，传入一个DocumentListener，对文字改变进行监听
 * DocumentListener中，重写insert方法
 * 
 * 
 * 
 */


public class DatePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel icon;
	public JTextField jtf;
	
	public DatePanel(Point location,Container con){
		this(location,con,UIUtil.bgWhite,HotspotUtil.dateIcon);
	}
	
	public DatePanel(Point location,Container con,Color fore,ImageIcon ic){
		
		this.setLocation(location);
		this.setSize(130, 30);
		this.setFont(new Font("微软雅黑",1,13));
		this.setOpaque(true);
		this.setBackground(UIUtil.darkBlue);
		this.setLayout(null);
		
		Date dateNow = new Date();  
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");  
		String dateNowStr = dateFormat.format(dateNow);
		
		jtf = new JTextField(dateNowStr);
		jtf.setLocation(30,0);
		jtf.setSize(100, 30);
		jtf.setFont(new Font("微软雅黑",1,13));
		jtf.setForeground(fore);
		jtf.setOpaque(false);
		jtf.setBackground(UIUtil.darkBlue);
		jtf.setEditable(false);
		jtf.setBorder(null);
		
		Chooser ser = Chooser.getInstance(jtf.getForeground());
        ser.register(jtf);
		
		this.add(jtf);
		
		icon = new JLabel(ic);
		icon.setBounds(3, 3, 20, 20);
		this.add(icon);
		
		con.add(this);
	}
	
	public String getText(){
		return jtf.getText();
	}
	
	
	public void addDocuListener(DocumentListener lis){
		jtf.getDocument().addDocumentListener(lis);
	}
	
	
//	public static void main(String[] args){
//		JFrame f = new JFrame();
//		f.setBounds(100, 100, 500, 500);
//		f.setLayout(null);
//		
//		DatePanel p = new DatePanel(new Point(),f,UIUtil.bgWhite,"img/hotspot/dateIcon.png");
//		f.setVisible(true);
//		
//	}

}
