package presentation.component;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import data.po.TeamDataPO;

public class GButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon image = null;
	public TeamDataPO po = null;
	
	public GButton(){
	}
	
	public GButton(String file, Point location, Point size, Container container, boolean visible){
		image = new ImageIcon(file);
		image.setImage(image.getImage().getScaledInstance(size.x, size.y, Image.SCALE_SMOOTH)); 
		this.setLocation(location);
		this.setSize(size.x, size.y);
		this.setBorder(null);//去掉边框
		this.setContentAreaFilled(false);//去掉背景色
		this.setOpaque(false);
		this.setIcon(image);
		container.add(this);
		this.setVisible(visible);
	}
	
	public GButton(String file, Point location, Point size, Container container, boolean visible, TeamDataPO po){
		this(file, location, size, container, visible);
		this.po = po;
	}
	
	public GButton(String text, Point location, Point size, Container container, boolean visible, int bord, int wordSize){
		this.setLocation(location);
		this.setSize(size.x, size.y);
		this.setBorder(null);//去掉边框
		this.setContentAreaFilled(false);//去掉背景色
		this.setText(text);
		this.setHorizontalAlignment(LEFT);
		this.setFont(new java.awt.Font("微软雅黑", bord, wordSize));
		container.add(this);
		this.setVisible(visible);
	}
}
