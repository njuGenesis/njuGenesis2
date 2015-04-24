package presentation.hotspot;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import presentation.component.GLabel;
import presentation.contenui.UIUtil;

public class SelectLabel extends GLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public boolean isSelected = false;

	public SelectLabel(String message, Point location, Point size, Container container, boolean visible, int bord, int wordSize){
		super(message,location,size,container,visible,bord,wordSize);
		
		this.setOpaque(true);
		this.setBackground(UIUtil.bgGrey);
		this.setForeground(UIUtil.bgWhite);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.addMouseListener(new LabelListener());
	}
	
	public void setSelected(boolean sel){
		isSelected = sel;
		if(isSelected){
			this.setBackground(UIUtil.nbaRed);
		}else{
			this.setBackground(UIUtil.bgGrey);
		}
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	
	class LabelListener implements MouseListener{

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
			SelectLabel l = (SelectLabel)e.getSource();
			l.setBackground(UIUtil.nbaRed);
		}

		public void mouseExited(MouseEvent e) {
			SelectLabel l = (SelectLabel)e.getSource();
			if(!l.isSelected){
				l.setBackground(UIUtil.bgGrey);
			}
		}
		
	}
	
	

}
