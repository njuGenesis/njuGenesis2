package presentation.component;

import java.awt.Container;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import data.po.TeamDataPO;

public class GLabel extends JLabel{
	
	private ImageIcon image;
	private BufferedImage bi;
	private BufferedImage sbi;
	private int number;
	public TeamDataPO po = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GLabel(String file, Point location, Point size, Container container, boolean visible){
		image = new ImageIcon(file);
		image.setImage(image.getImage().getScaledInstance(size.x, size.y, Image.SCALE_SMOOTH)); 
		this.setIcon(image);
		this.setBounds(location.x, location.y, size.x, size.y);
		this.setVisible(visible);
		if(container != null){
			container.add(this);
		}
	}
	
	public GLabel(String file, Point location, Point size, Container container, boolean visible, TeamDataPO po){
		this(file, location, size, container, visible);
		this.po = po;
	}
	
	public GLabel(ImageIcon image, Point location, Point size, Container container, boolean visible){
		image.setImage(image.getImage().getScaledInstance(size.x, size.y, Image.SCALE_SMOOTH)); 
		this.setIcon(image);
		this.setBounds(location.x, location.y, size.x, size.y);
		this.setVisible(visible);
		if(container != null){
			container.add(this);
		}
	}
	
	public GLabel(String file, Point location, Point size, Container container, boolean visible, int number){
		this.number = number;
		image = new ImageIcon(file);
		image.setImage(image.getImage().getScaledInstance(size.x, size.y, Image.SCALE_SMOOTH)); 
		this.setIcon(image);
		this.setBounds(location.x, location.y, size.x, size.y);
		this.setVisible(visible);
		if(container != null){
			container.add(this);
		}
	}
	
	public GLabel(String file, Point location, Point size, int x, int y, int width, int height, Container container, boolean visible, int s){
		File f = new File(file);
		try {
			bi = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sbi = bi.getSubimage(x,y,width,height);
		image = new ImageIcon(sbi);
		this.setHorizontalAlignment(s);
		this.setIcon(image);
		this.setBounds(location.x, location.y, size.x, size.y);
		this.setVisible(visible);
		if(container != null){
			container.add(this);
		}
	}

	public GLabel(String message, Point location, Point size, Container container, boolean visible, int bord, int wordSize){
		this.setText(message);
		this.setBounds(location.x, location.y, size.x, size.y);
		this.setVisible(visible);
		this.setFont(new java.awt.Font("微软雅黑", bord, wordSize));
		if(container != null){
			container.add(this);
		}
	}
	
	public void paint(int x, int y, int w, int h){
		sbi = bi.getSubimage(x, y, w, h);
		image = new ImageIcon(sbi);
		this.setIcon(image);
	}
	
	public int getNumber(){
		return number;
	}
}
