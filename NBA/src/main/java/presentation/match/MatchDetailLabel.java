package presentation.match;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import presentation.component.GLabel;
import data.po.MatchDataPO;

public class MatchDetailLabel extends GLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MatchDataPO po;
	private static ImageIcon icon = new ImageIcon("img/match/greyrightButton.png");

	public MatchDetailLabel(Container container,MatchDataPO po){
		super(icon,new Point(880,50),new Point(16,30),container,true);
		this.po = po;
		this.addMouseListener(new BtListener());
	}


	class BtListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
