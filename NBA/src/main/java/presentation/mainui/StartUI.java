package presentation.mainui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

import presentation.component.GFrame;
import presentation.component.GLabel;

public class StartUI extends GFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private GLabel background, NBAleft, NBAright, blueStrip, redStrip, startWord1, startWord2, startWord3;
	private typeOfRun type;
	private GMainUI turnerNewsPaper;
	
	public static void main(String[] args) {
		new StartUI();
	}
	
	public StartUI(){
		this.setSize(1030, 700);
		this.setMiddle();
		this.setLayout(null);
		setListener();
		init();
	}
	
	private void init(){
		
		turnerNewsPaper = new GMainUI();
		turnerNewsPaper.setPages("img/", "", "png", 7, 500, 650);//8是个数，后面是图片大小
		turnerNewsPaper.setMargins(0, 50);//设置第一张图片的位置
		turnerNewsPaper.setLeftPageIndex(-1);//第一张图片的编号
		turnerNewsPaper.setLayout(null);
		turnerNewsPaper.setBounds(15, 0, 1000, 700);
		turnerNewsPaper.setOpaque(false);
		turnerNewsPaper.setVisible(false);
		turnerNewsPaper.setLeftPageIndex(0);
		this.setVisible(true);
		this.add(turnerNewsPaper);
		
		startWord1 = new GLabel("img/Framebg/startWord1.png", new Point(256, 305), new Point(488, 39), this, false);
		startWord2 = new GLabel("img/Framebg/startWord2.png", new Point(256, 305), new Point(488, 39), this, false);
		startWord3 = new GLabel("img/Framebg/startWord3.png", new Point(256, 305), new Point(488, 39), this, false);
		
		NBAleft = new GLabel("img/Framebg/left.png", new Point(380, 50), new Point(212, 650), 0, 0, 212, 650, this, true,SwingConstants.LEFT);
		NBAright = new GLabel("img/Framebg/right.png", new Point(520, 50), new Point(130, 650), 0, 0, 130, 650, this, true, SwingConstants.RIGHT);
		
		background = new GLabel("img/Framebg/background.png", new Point(15, 50), new Point(1000, 650), 365, 0, 270, 650, this, true, SwingConstants.CENTER);
		
		blueStrip = new GLabel("img/Framebg/blueStrip.png", new Point(15, 50), new Point(15, 650), this, false);
		redStrip = new GLabel("img/Framebg/redStrip.png", new Point(1000, 50), new Point(15, 650), this, false);
		
		
		redStrip.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				int x = turnerNewsPaper.leftPageIndex;
				if(x==turnerNewsPaper.nrOfPages+1){
				}else{
					turnerNewsPaper.nextPage();
				}
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
			}
		});
		blueStrip.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				int x = turnerNewsPaper.leftPageIndex;
				if(x!=-1){
					turnerNewsPaper.previousPage();
				}
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
			}
		});
		type = typeOfRun.ENTER;
		
		thread = new Thread(this);
		thread.start();
		
		
	}

	public void run() {
		switch(type){
		case ENTER:{
			int bgPaintX = 365, bgPaintW = 270;
			int NBAleftX = NBAleft.getLocation().x, NBAleftY = NBAleft.getLocation().y;
			int NBArightX = NBAright.getLocation().x, NBArightY = NBAright.getLocation().y;
			int sleepTime = 1;
			while(bgPaintX>0){
				NBAleftX--;
				bgPaintX--;
				bgPaintW+=2;
				NBArightX++;
				NBAleft.setLocation(NBAleftX, NBAleftY);
				NBAright.setLocation(NBArightX, NBArightY);
				background.paint(bgPaintX, 0, bgPaintW, 650);
				threadSleep(sleepTime);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			threadSleep(1000);
			startWord1.setVisible(true);
			threadSleep(1000);
			startWord1.setVisible(false);
			startWord2.setVisible(true);
			threadSleep(1000);
			startWord2.setVisible(false);
			startWord3.setVisible(true);
			type = typeOfRun.WORK;
			threadSleep(1000);
			startWord3.setVisible(false);
			thread.run();
			break;
		}
		case WORK:{
			blueStrip.setVisible(true);
			redStrip.setVisible(true);
			int bsX = blueStrip.getLocation().x, bsY = blueStrip.getLocation().y;
			int rsX = redStrip.getLocation().x, rsY = redStrip.getLocation().y;
			int NBAleftPaintX = 0, NBAleftPaintW = 212, NBArightPaintW = 130;
			while(NBAleftPaintX<210){
				bsX--;
				rsX++;
				NBAleftPaintX+=14;
				NBAleftPaintW-=14;
				NBArightPaintW-=8;
				blueStrip.setLocation(bsX, bsY);
				redStrip.setLocation(rsX, rsY);
				NBAleft.paint(NBAleftPaintX, 0, NBAleftPaintW, 650);
				NBAright.paint(0, 0, NBArightPaintW, 650);
				threadSleep(20);
			}
			NBAleft.setVisible(false);
			NBAright.setVisible(false);
			turnerNewsPaper.setVisible(true);
			break;
		}
		case EXIT:{
			break;
		}
		default:break;
		}
	}
	
	public void setMiddle(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (int)(screen.getWidth()-1000)/2;
		int y = (int)(screen.getHeight()-750)/2-40;
		this.setLocation(x, y);//设置居中
	}
	
	private void setListener(){
		MouseAdapter mouseListener = new MouseAdapter(){

			int xOld = 0;
			int yOld = 0;

			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				StartUI.this.setLocation(xx, yy);
			}
		};

		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
	}
	
	private void threadSleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

enum typeOfRun{
	ENTER,
	WORK,
	EXIT;
}
