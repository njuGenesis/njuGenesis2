package presentation.component;

import java.awt.AWTException;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BookEffectAssist {

	private Robot robot;

	public BookEffectAssist(){
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage shot(Rectangle r){
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		BufferedImage image = robot.createScreenCapture(new Rectangle(width,height));  
		image = image.getSubimage(r.x, r.y, r.width, r.height);
		//ImageIO.write (image, "png" , new File("c:/1.png"));   //保存在C盘 图片名为1.png
		return image;
	}
	
	
	
	public Image[] createCurrentImage(Container mianFrame) throws IOException{
		BufferedImage screenImage = shot(new Rectangle(mianFrame.getLocation().x+15, mianFrame.getLocation().y+50, 
				mianFrame.getWidth()-80, mianFrame.getHeight()-50));
		BufferedImage assitImg = new BufferedImage(1000, 650, BufferedImage.TYPE_INT_ARGB);
		assitImg.createGraphics().drawImage(screenImage, 0, 0, null);
		BufferedImage assistImgLeft = new BufferedImage(500, 650, BufferedImage.TYPE_INT_ARGB);
		BufferedImage assistImgRight = new BufferedImage(500, 650, BufferedImage.TYPE_INT_ARGB);
		assistImgRight.createGraphics().drawImage(assitImg, 0, 0, 500, 650, 500, 0, 1000, 650, null);
		assistImgLeft.createGraphics().drawImage(assitImg, 0, 0, null);
		Image imgLeft = new ImageIcon(assistImgLeft).getImage();
		Image imgRight = new ImageIcon(assistImgRight).getImage();
		Image[] img= new Image[2];
		img[0] = imgLeft;
		img[1] = imgRight;
		
//		GFrame f = new GFrame();
//		f.setBounds(0, 100, 1000, 650);
//		JLabel l = new JLabel();
//		l.setBounds(0, 0, 1000, 650);
//		l.setIcon(new ImageIcon(assitImg));
//		//l.setIcon(new ImageIcon(assistImgRight));
//		f.add(l);
//		f.setVisible(true);
		
		return img;
	}
	
	public Image[] createPanelImage(BgPanel nextPanel){
		int width = nextPanel.getWidth();
		int height = nextPanel.getHeight();
		BufferedImage assitImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);   
		Graphics2D g2 = assitImg.createGraphics();   
		nextPanel.print(g2); 
		BufferedImage assistImgLeft = new BufferedImage(width/2, height, BufferedImage.TYPE_INT_ARGB);
		BufferedImage assistImgRight = new BufferedImage(width/2, height, BufferedImage.TYPE_INT_ARGB);
		assistImgRight.createGraphics().drawImage(assitImg, 0, 0, width/2, height, width/2, 0, width, height, null);
		assistImgLeft.createGraphics().drawImage(assitImg, 0, 0, null);
		Image imgLeft = new ImageIcon(assistImgLeft).getImage();
		Image imgRight = new ImageIcon(assistImgRight).getImage();
		Image[] img= new Image[2];
		img[0] = imgLeft;
		img[1] = imgRight;
		
//		GFrame f = new GFrame();
//		f.setBounds(0, 100, 1000, 650);
//		JLabel l = new JLabel();
//		l.setBounds(0, 0, 1000, 650);
//		l.setIcon(new ImageIcon(assitImg));
//		//l.setIcon(new ImageIcon(assistImgRight));
//		f.add(l);
//		f.setVisible(true);
		
		return img;
	}
}
