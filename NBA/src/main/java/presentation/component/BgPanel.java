package presentation.component;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import presentation.mainui.Refresh;

public class BgPanel extends JPanel implements Refresh{
	
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
	@Override
	public void refreshUI() {
		// TODO Auto-generated method stub
		
	}

}
