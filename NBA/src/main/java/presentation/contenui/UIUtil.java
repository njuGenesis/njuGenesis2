package presentation.contenui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicArrowButton;

import assistance.NewFont;

public class UIUtil {

	public static ImageIcon defaultComboBoxArrowIcon = new ImageIcon("img/icon/donwBt.png");
	
	public static Color nbaBlue = new Color(0,110,185);
	public static Color nbaRed = new Color(228,42,81);
	public static Color bgWhite = new Color(251,251,251);
	public static Color bgGrey = new Color(83,83,83);
	public static Color darkBlue = new Color(0,82,138);
	public static Color tableGrey = new Color(238,238,238);
	
	public static JButton getSelectButton(){
		JButton bt = new JButton("筛选");
		bt.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent e) {
				JButton bt = (JButton)e.getSource();
				bt.setBorder(BorderFactory.createLineBorder(nbaBlue, 2));
			}

			public void mouseExited(MouseEvent e) {
				JButton bt = (JButton)e.getSource();
				bt.setBorder(BorderFactory.createLineBorder(nbaBlue));
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		bt.setBorder(BorderFactory.createLineBorder(nbaBlue));
		bt.setContentAreaFilled(false);
//		bt.setForeground(nbaBlue);
		bt.setFont(NewFont.ButtonFont);
		
		return bt;
	}

	public static JButton getComboBoxArrowButton(){
		BasicArrowButton bt = new BasicArrowButton(BasicArrowButton.LEFT,Color.black,Color.black,Color.black,Color.black);
		bt.setSize(30, 30);

		return bt;
	}
	
	public static JButton getLeftButton(){
		JButton left = new JButton(new ImageIcon("img/icon/leftBt.png"));
		left.setBorder(null);
		left.setContentAreaFilled(false);
		return left;
	}
	
	public static JButton getRightButton(){
		JButton right = new JButton(new ImageIcon("img/icon/rightBt.png"));
		right.setBorder(null);
		right.setContentAreaFilled(false);
		return right;
	}
	
}
