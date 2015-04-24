package presentation.component;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class ButtonLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonLabel(){
		super();
		this.addMouseListener(new ButtonListener());
		
	}
	
	class ButtonListener implements MouseListener{

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
			ButtonLabel.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		public void mouseExited(MouseEvent e) {
			ButtonLabel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
	}

}
