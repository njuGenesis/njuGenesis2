package presentation.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class DynamicBar extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double value = 50.9;//百分率
	private JLabel dBar;//会动的条
	private JTextField dVal;//会动的数字
	private Timer timer;
	
	public DynamicBar(){
		this.setSize(50, 130);
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		initialise();
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	private void initialise(){
		dBar = new JLabel();
		dBar.setBounds(0, 120, 50, 100);
		dBar.setBackground(new Color(23, 34, 12));
		dBar.setOpaque(true);
		this.add(dBar);
		
		dVal = new JTextField();
		dVal.setText(0.0+"");
		dVal.setBounds(0, 100, 50, 30);
		dVal.setOpaque(false);
		dVal.setBorder(null);
		dVal.setEditable(false);
		dVal.setFont(new Font("微软雅黑", Font.BOLD, 12));
		this.add(dVal);
	}
	
	public void showOut(){
		timer = new Timer(40,new UpAction());
		timer.setRepeats(true);
		timer.start();
	}
	
	class UpAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			int height = 130 - dBar.getY();
			if(height<value){
				int valY = dVal.getY();
				double tempValue = Double.parseDouble(dVal.getText()) + 1;
				dVal.setText(tempValue+"");
				dVal.setLocation(0, valY-1);
				
				int barY = dBar.getY();
				dBar.setLocation(0, barY-1);
				
				
			}else{
				dVal.setText(value+"");
				timer.stop();
			}
			
		}
		
	}
	
	public static void main(String args[]){
		JFrame f = new JFrame();
		f.setLayout(null);
		
		DynamicBar db = new DynamicBar();
		db.setLocation(0, 0);
		f.add(db);
		f.setSize(100, 200);
		f.setVisible(true);
		db.showOut();
	}
}
