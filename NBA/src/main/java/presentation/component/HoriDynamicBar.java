package presentation.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import presentation.contenui.UIUtil;

public class HoriDynamicBar extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private double value = 51.9;//百分率
	private JLabel dBar;//会动的条
	private JTextField dVal;//会动的数字
	private Timer timer;
	private Color labelColor = UIUtil.nbaBlue;
	
	
	//默认大小：50，130；默认颜色：蓝色
	public HoriDynamicBar(){
		this(new Dimension(130, 50));
	}
	
	public HoriDynamicBar(Dimension d){
		this.setSize(d);
		this.setBackground(UIUtil.bgWhite);
		this.setOpaque(true);
		initialise();
	}
	
	//自定义大小和颜色；宽度建议大于30
	public HoriDynamicBar(Dimension d,Color c){
		this.setSize(d);
		this.setBackground(UIUtil.bgWhite);
		this.setOpaque(true);
		this.labelColor = c;
		initialise();
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	private void initialise(){
		dBar = new JLabel();
		dBar.setBounds(0, 0, this.getWidth()-30, this.getHeight());
		dBar.setBackground(labelColor);
		dBar.setOpaque(true);
		this.add(dBar);
		
		dVal = new JTextField();
		dVal.setText(0.0+"");
		dVal.setBounds(this.getWidth()-30, 0, 50, 30);
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
		
		HoriDynamicBar db = new HoriDynamicBar();
		db.setLocation(0, 0);
		f.add(db);
		f.setSize(200, 200);
		f.setVisible(true);
		db.showOut();
	}

}
