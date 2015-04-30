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

public class HoriDynamicBarLeft extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private double value = 40;//百分率
	private JLabel dBar;//会动的条
	private JTextField dVal;//会动的数字
	private Timer timer;
	private Color labelColor = UIUtil.nbaBlue;
	
	private int length = 150;
	
	
	//默认大小：50，130；默认颜色：蓝色
	public HoriDynamicBarLeft(double value){
		this(value,new Dimension(150+50, 50),UIUtil.nbaBlue);
	}
	
	public HoriDynamicBarLeft(double value,Dimension d){
		this(value,d,UIUtil.nbaBlue);
	}
	
	//自定义大小和颜色；宽度建议大于30，长度为150的倍数
	public HoriDynamicBarLeft(double value,Dimension d,Color c){
		this.setSize((int)d.getWidth()+50,(int)d.getHeight());
		this.length = (int) d.getWidth();
		this.setBackground(UIUtil.bgWhite);
		this.setOpaque(true);
		this.labelColor = c;
		this.setValue(value);
		initialise();
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public double getValue(){
		return this.value;
	}
	
	public void setColor(Color c){
		this.labelColor = c;
		dBar.setBackground(labelColor);
	}
	
	private void initialise(){
		dBar = new JLabel();
		dBar.setBounds(this.getWidth(), 0, (int)(value/150*length), this.getHeight());
		dBar.setBackground(labelColor);
		dBar.setOpaque(true);
		this.add(dBar);
		
		dVal = new JTextField();
		dVal.setText(0.0+"");
		dVal.setHorizontalAlignment(JLabel.CENTER);
		dVal.setBounds(length, 0, 50, 30);
		dVal.setOpaque(false);
		dVal.setBorder(null);
		dVal.setEditable(false);
		dVal.setBackground(UIUtil.bgWhite);
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
			int width = length+50 - dBar.getX();
			if(width<dBar.getWidth()){
				int valX = dVal.getX();
				double tempValue = Double.parseDouble(dVal.getText()) + 1;
				dVal.setText(String.format("%.2f", tempValue));
				dVal.setLocation(valX-length/150,0);
				int barX = dBar.getX();
				dBar.setLocation(barX-length/150, 0);
				
				
			}else{
				dVal.setText(value+"");
				timer.stop();
			}
			
		}
		
	}
	
	public static void main(String args[]){
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HoriDynamicBarLeft db = new HoriDynamicBarLeft(120.3,new Dimension(450,40));
		db.setLocation(0, 0);
		f.add(db);
		f.setSize(500, 500);
		f.setVisible(true);
		db.showOut();
	}

}
