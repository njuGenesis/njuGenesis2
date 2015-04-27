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


public class VerDynamicBar extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double value;//百分率
	private JLabel dBar;//会动的条
	private JTextField dVal;//会动的数字
	private Timer timer;
	private Color labelColor = UIUtil.nbaBlue;
	
	
	//默认大小：50，130；默认颜色：蓝色
	public VerDynamicBar(double value){
		this(value,50,UIUtil.nbaBlue);
	}
	
	public VerDynamicBar(double value,int width){
		this(value,width,UIUtil.nbaBlue);
	}
	
	//自定义宽度和颜色；宽度建议大于30
	public VerDynamicBar(double value,int width,Color c){
		this.setSize(width,130);
		this.setBackground(UIUtil.bgWhite);
		this.setOpaque(true);
		this.labelColor = c;
		setValue(value);
		initialise();
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	private void initialise(){
		dBar = new JLabel();
		dBar.setBounds(0, this.getHeight()-10, this.getWidth(), this.getHeight()-30);
		dBar.setBackground(labelColor);
		dBar.setOpaque(true);
		this.add(dBar);
		
		dVal = new JTextField();
		dVal.setText(0.0+"");
		dVal.setBounds(0, this.getHeight()-30, 50, 30);
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
			int height = VerDynamicBar.this.getHeight() - dBar.getY();
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
		
		VerDynamicBar db = new VerDynamicBar(92);
		db.setLocation(0, 0);
		f.add(db);
		f.setSize(300, 300);
		f.setVisible(true);
		db.showOut();
	}

}
