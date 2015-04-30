package presentation.match;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import presentation.component.GLabel;
import presentation.contenui.MatchUI;
import data.po.MatchDataPO;

public class MatchDetailLabel extends GLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MatchDataPO po;
	private static ImageIcon icon = new ImageIcon("img/match/greyrightButton.png");
	
	private static Point commonLocation = new Point(880,50);
	private static Point moveLocation = new Point(878,48);
	private MatchUI big;

	public MatchDetailLabel(Container big,Container container,MatchDataPO po){
		super(icon,commonLocation,new Point(16,30),container,true);
		this.po = po;
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.addMouseListener(new BtListener());
		
		this.big = (MatchUI) big;
	}


	class BtListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			big.removeAll();
			
			big.detail = new MatchDetailPanel(po);
			
			big.add(big.detail);
			big.repaint();
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseEntered(MouseEvent e) {
			MatchDetailLabel.this.setLocation(moveLocation);
		}

		public void mouseExited(MouseEvent e) {
			MatchDetailLabel.this.setLocation(commonLocation);
		}
	}
}
