package presentation.component;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BgPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon im;

	public BgPanel(String s){
		im = new ImageIcon(s);  
		
	}
	public void paintComponent(Graphics g) {  
		super.paintComponent(g);
		g.drawImage(im.getImage(), 0, 0, this);   
		//this.repaint();
	}

}
