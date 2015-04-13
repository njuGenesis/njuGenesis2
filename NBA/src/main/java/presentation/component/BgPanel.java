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
		this.setBounds(15, 50, 1000, 650);
		im = new ImageIcon(s);  
		
//		this.addMouseListener(new MouseListener() {
//			public void mouseReleased(MouseEvent e) {
//			}
//			public void mousePressed(MouseEvent e) {
//				System.out.println("nextpanel");
//			}
//			public void mouseExited(MouseEvent e) {
//			}
//			public void mouseEntered(MouseEvent e) {
//			}
//			public void mouseClicked(MouseEvent e) {
//			}
//		});
	}
	public void paintComponent(Graphics g) {  
		super.paintComponent(g);
		g.drawImage(im.getImage(), 0, 0, this);   
		//this.repaint();
	}

}
