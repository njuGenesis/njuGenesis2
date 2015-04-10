package presentation.component;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

@SuppressWarnings("restriction")
public class GFrame extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭进程
		this.setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);
	}
	
	public void setMiddle(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (int)(screen.getWidth()-this.getWidth())/2;
		int y = (int)(screen.getHeight()-this.getHeight())/2-32;
		this.setLocation(x, y);//设置居中
	}
}
