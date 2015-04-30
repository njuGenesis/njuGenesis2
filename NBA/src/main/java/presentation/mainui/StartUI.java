package presentation.mainui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import presentation.component.BeanUtil;
import presentation.component.BgPanel;
import presentation.component.BookEffectAssist;
import presentation.component.GFrame;
import presentation.component.GLabel;
import presentation.contenui.PanelKind;
import presentation.contenui.TurnController;
import assistance.FileListener;

public class StartUI extends GFrame implements Runnable,Refresh{
	
	/**
	 * 
	 */
	public static StartUI startUI;
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private GLabel NBAleft, NBAright, blueStrip, redStrip, startWord1, startWord2, startWord3, close;
	private GLabel[] background;
	private typeOfRun type;
	private GMainUI turnerNewsPaper;
	private GLabel[] menu;
	private BookEffectAssist assit;
	private int menuCurrentNumber, menuNextNumber;
	private TurnController turnController;
	private BgPanel currentPanel, nextPanel;
	private Vector<BgPanel> path = new Vector<BgPanel>();
	
	public static void main(String[] args) {
		new StartUI();
		
		RefreshData re = new RefreshData();
		Thread t = new Thread(re);
		t.setDaemon(true);
		t.start();
		
	}
	
	
	
	public StartUI(){
		startUI = this;
		
		turnController = new TurnController();
		menuCurrentNumber = 0;
		menuNextNumber = 0;
		
		assit = new BookEffectAssist();
		this.setSize(1080, 700);
		this.setMiddle();
		this.setLayout(null);
		this.setVisible(true);
		setListener();
		init();
		
		this.repaint();
	}
	
	private void init(){
		
		currentPanel = turnController.turn(PanelKind.HOT);
		this.add(currentPanel);
		currentPanel.setVisible(false);
		
		turnerNewsPaper = new GMainUI();
		turnerNewsPaper.setMargins(0, 50);//设置第一张图片的位置
		turnerNewsPaper.setLayout(null);
		turnerNewsPaper.setBounds(15, 0, 1000, 700);
		turnerNewsPaper.setOpaque(false);
		turnerNewsPaper.setVisible(false);
		this.add(turnerNewsPaper);
		
		startWord1 = new GLabel("img/Framebg/startWord1.png", new Point(256, 305), new Point(488, 39), this, false);
		startWord2 = new GLabel("img/Framebg/startWord2.png", new Point(256, 305), new Point(488, 39), this, false);
		startWord3 = new GLabel("img/Framebg/startWord3.png", new Point(256, 305), new Point(488, 39), this, false);
		
		NBAleft = new GLabel("img/Framebg/left.png", new Point(381, 50), new Point(212, 650), 0, 0, 212, 650, this, true,SwingConstants.LEFT);
		NBAright = new GLabel("img/Framebg/right.png", new Point(520, 50), new Point(130, 650), 0, 0, 130, 650, this, true, SwingConstants.RIGHT);
		
		background = new GLabel[5];
		background[0] = new GLabel("img/Framebg/bgW268.png", new Point(381, 50), new Point(268, 650), this, true);
		background[1] = new GLabel("img/Framebg/bgW268.png", new Point(381, 50), new Point(268, 650), this, true);
		background[2] = new GLabel("img/Framebg/bgW268.png", new Point(381, 50), new Point(268, 650), this, true);
		background[3] = new GLabel("img/Framebg/bgW98.png", new Point(381, 50), new Point(98, 650), this, true);
		background[4] = new GLabel("img/Framebg/bgW98.png", new Point(536, 50), new Point(98, 650), this, true);
		
		blueStrip = new GLabel("img/Framebg/blueStrip.png", new Point(15, 50), new Point(15, 650), this, false);
		redStrip = new GLabel("img/Framebg/redStrip.png", new Point(1000, 50), new Point(15, 650), this, false);
		
		menu = new GLabel[5];
		for(int i=0;i<menu.length;i++){
			if(i==0){
				menu[i] = new GLabel("img/Framebg/menu"+i+"Red.png", new Point(980, 50), new Point(50, 116), this, false, i);
			}else{
				menu[i] = new GLabel("img/Framebg/menu"+i+"Blue.png", new Point(980, 50), new Point(50, 116), this, false, i);
			}
			menu[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		close = new GLabel("img/Framebg/closeBlue.png", new Point(980, 50), new Point(50, 50), this, false, -1);
		close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				GLabel currentMenu = (GLabel)e.getSource();
				menuNextNumber = currentMenu.getNumber();
				
				if(menuNextNumber==-1){
					menu[menuCurrentNumber].setIcon(new ImageIcon("img/Framebg/menu"+menuCurrentNumber+"Blue.png"));
					close.setIcon(new ImageIcon("img/Framebg/closeRed.png"));
					type = typeOfRun.EXIT;
					thread = new Thread(StartUI.this);
					thread.start();
				}else{
					menu[menuCurrentNumber].setIcon(new ImageIcon("img/Framebg/menu"+menuCurrentNumber+"Blue.png"));
					menu[menuNextNumber].setIcon(new ImageIcon("img/Framebg/menu"+menuNextNumber+"Red.png"));
					
					switch(menuNextNumber){
					case 0:nextPanel = turnController.turn(PanelKind.HOT);break;
					case 1:nextPanel = turnController.turn(PanelKind.TEAM);break;
					case 2:nextPanel = turnController.turn(PanelKind.PLAYER);break;
					case 3:nextPanel = turnController.turn(PanelKind.MATCH);break;
					case 4:nextPanel = turnController.turn(PanelKind.STATS);break;
					}
					
					type = typeOfRun.MENURUN;
					thread = new Thread(StartUI.this);
					thread.start();
					while(thread.isAlive()){
						
					}
					turn(nextPanel);
				}
			}
		};
		
		for(int i=0;i<menu.length;i++){
			menu[i].addMouseListener(mouseAdapter);
		}
		close.addMouseListener(mouseAdapter);
		
		type = typeOfRun.ENTER;
		
		thread = new Thread(this);
		thread.start();
	}
	
//	public void setBackPath(){
//		BgPanel panel = BeanUtil.cloneTo(currentPanel);
//		path.addElement(panel);
//	}
//	
//	public void back(){
//		StartUI.this.remove(currentPanel);
//		currentPanel = path.lastElement();
//		StartUI.this.add(currentPanel);
//		int x = path.size()-1;
//		path.removeElementAt(x);
//		currentPanel.setVisible(true);
//		StartUI.this.repaint();
//	}
	
	public void setMenu(int number){
		menu[menuCurrentNumber].setIcon(new ImageIcon("img/Framebg/menu"+menuCurrentNumber+"Blue.png"));
		menuCurrentNumber = number;
		menu[menuCurrentNumber].setIcon(new ImageIcon("img/Framebg/menu"+menuCurrentNumber+"Red.png"));
	}
	
	public void turn(BgPanel nextPanel){
		//setBackPath();
		
		this.nextPanel = nextPanel;
		StartUI.this.remove(currentPanel);
		currentPanel = this.nextPanel;
		StartUI.this.add(currentPanel);
		currentPanel.setVisible(true);
		StartUI.this.repaint();
	}

	public void run() {
		switch(type){
		case ENTER:{
			int leftMove = background[1].getLocation().x, rightMove = leftMove;
			int NBAleftX = NBAleft.getLocation().x, NBAleftY = NBAleft.getLocation().y;
			int NBArightX = NBAright.getLocation().x, NBArightY = NBAright.getLocation().y;
			while(NBAleftX>15){
				NBAleftX--;
				NBArightX++;
				leftMove--;
				rightMove++;
				NBAleft.setLocation(NBAleftX, NBAleftY);
				NBAright.setLocation(NBArightX, NBArightY);
				if(leftMove>=113){
					background[1].setLocation(leftMove, NBAleftY);
					background[2].setLocation(rightMove, NBArightY);
					background[3].setLocation(leftMove, NBAleftY);
					background[4].setLocation(rightMove+170, NBArightY);
				}else{
					background[3].setLocation(leftMove, NBAleftY);
					background[4].setLocation(rightMove+170, NBArightY);
				}
				threadSleep(1);
			}
			threadSleep(10);
//			threadSleep(1000);
//			startWord1.setVisible(true);
//			threadSleep(1000);
//			startWord1.setVisible(false);
//			startWord2.setVisible(true);
//			threadSleep(1000);
//			startWord2.setVisible(false);
//			startWord3.setVisible(true);
//			threadSleep(1000);
//			startWord3.setVisible(false);
			type = typeOfRun.WORK;
			currentPanel.setVisible(true);
			for(int i=0;i<background.length;i++){
				background[i].setVisible(false);
			}
			//break;
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
			
			menu[0].setVisible(true);
			menu[1].setVisible(true);
			menu[2].setVisible(true);
			menu[3].setVisible(true);
			menu[4].setVisible(true);
			close.setVisible(true);
			int menuX = menu[0].getLocation().x;
			int menu1Y = menu[0].getLocation().y, menu2Y = menu[0].getLocation().y, 
					menu3Y = menu[0].getLocation().y, menu4Y = menu[0].getLocation().y, 
					menu5Y = menu[0].getLocation().y, closeY = close.getLocation().y;
			while(menuX<1030){
				menuX++;
				menu[0].setLocation(menuX, menu1Y);
				menu[1].setLocation(menuX, menu2Y);
				menu[2].setLocation(menuX, menu3Y);
				menu[3].setLocation(menuX, menu4Y);
				menu[4].setLocation(menuX, menu5Y);
				close.setLocation(menuX, closeY);
				threadSleep(2);
			}
			while(menu2Y<167){
				menu2Y++;
				menu3Y+=2;
				menu4Y+=3;
				menu5Y+=4;
				closeY+=5;
				menu[0].setLocation(menuX, menu1Y);
				menu[1].setLocation(menuX, menu2Y);
				menu[2].setLocation(menuX, menu3Y);
				menu[3].setLocation(menuX, menu4Y);
				menu[4].setLocation(menuX, menu5Y);
				close.setLocation(menuX, closeY);
				threadSleep(2);
			}
			thread.stop();
			break;
		}
		case MENURUN:{
			Image[] imgCurrent = new Image[2];
			imgCurrent = assit.createPanelImage(currentPanel);
			Image[] imgNext = new Image[2];
			imgNext = assit.createPanelImage(nextPanel);
			turnerNewsPaper.setPages(imgCurrent, imgNext, 500, 650);
			currentPanel.setVisible(false);
			turnerNewsPaper.setVisible(true);
			if(menuNextNumber>menuCurrentNumber){
				turnerNewsPaper.nextPage();
			}else{
				if(menuNextNumber<menuCurrentNumber){
					turnerNewsPaper.previousPage();
				}else{
					turnerNewsPaper.setVisible(false);
				}
			}
			menuCurrentNumber = menuNextNumber;
			thread.stop();
			break;
		}
		case EXIT:{
			int closeX = close.getLocation().x, closeY = close.getLocation().y;
			int[] menuBottomY = new int[menu.length];
			for(int i=0;i<menuBottomY.length;i++){
				menuBottomY[i] = menu[i].getLocation().y+menu[i].getHeight();
			}
			while(closeY>50){
				closeY--;
				close.setLocation(closeX, closeY);
				for(int i=0;i<menuBottomY.length;i++){
					if(closeY == menuBottomY[i]){
						menu[i].setVisible(false);
						break;
					}
				}
				threadSleep(1);
			}
			
			currentPanel.setVisible(false);
			for(int i=0;i<background.length;i++){
				background[i].setVisible(true);
			}
			NBAleft.setVisible(true);
			NBAright.setVisible(true);
			int bsX = blueStrip.getLocation().x, bsY = blueStrip.getLocation().y;
			int rsX = redStrip.getLocation().x, rsY = redStrip.getLocation().y;
			int NBAleftPaintX = 210, NBAleftPaintW = 2, NBArightPaintW = 10;
			while(NBAleftPaintX>0){
				closeX-=4;
				bsX++;
				rsX--;
				NBAleftPaintX-=14;
				NBAleftPaintW+=14;
				NBArightPaintW+=8;
				close.setLocation(closeX, closeY);
				blueStrip.setLocation(bsX, bsY);
				redStrip.setLocation(rsX, rsY);
				NBAleft.paint(NBAleftPaintX, 0, NBAleftPaintW, 650);
				NBAright.paint(0, 0, NBArightPaintW, 650);
				threadSleep(20);
			}
			blueStrip.setVisible(false);
			redStrip.setVisible(false);
			close.setVisible(false);
			
			int leftMove = background[3].getLocation().x, rightMove = background[4].getLocation().x;
			int NBAleftX = NBAleft.getLocation().x, NBAleftY = NBAleft.getLocation().y;
			int NBArightX = NBAright.getLocation().x, NBArightY = NBAright.getLocation().y;
			while(NBAleftX<381){
				NBAleftX++;
				NBArightX--;
				leftMove++;
				rightMove--;
				NBAleft.setLocation(NBAleftX, NBAleftY);
				NBAright.setLocation(NBArightX, NBArightY);
				if(leftMove<=113){
					background[3].setLocation(leftMove, NBAleftY);
					background[4].setLocation(rightMove, NBArightY);
				}else{
					background[1].setLocation(leftMove, NBAleftY);
					background[2].setLocation(rightMove-170, NBArightY);
					background[3].setLocation(leftMove, NBAleftY);
					background[4].setLocation(rightMove, NBArightY);
				}
				threadSleep(1);
			}
			threadSleep(1000);
			this.dispose();
			break;
		}
		default:break;
		}
	}
	
	public void setMiddle(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (int)(screen.getWidth()-1080)/2;
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

	public void setCurrentPanel(BgPanel currentPanel){
		this.currentPanel = currentPanel;
	}

	@Override
	public void refreshUI() {
		System.out.println("refresh~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		currentPanel.refreshUI();
	}
}

enum typeOfRun{
	ENTER,
	WORK,
	EXIT,
	MENURUN;
}
