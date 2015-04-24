package presentation.component;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class TeamImageAssist extends ImageTranscoder{

	private BufferedImage img = null;

	public TeamImageAssist(){
	}

	@Override
	public BufferedImage createImage(int w, int h){
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		return bi;
	}

	@Override
	public void writeImage(BufferedImage img, TranscoderOutput output){
		this.img = img;
	}

	public BufferedImage getBufferedImage(){
		return img;
	}

	public BufferedImage loadImage(String svgFile, float width, float height){
		//BufferedImageTranscoder imageTranscoder = new BufferedImageTranscoder();

		FileInputStream fileSvg = null;
		try {
			fileSvg = new FileInputStream(svgFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.addTranscodingHint(PNGTranscoder.KEY_WIDTH, width);
		this.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, height);

		TranscoderInput input = new TranscoderInput(fileSvg);
		try {
			this.transcode(input, null);
		} catch (TranscoderException e) {
			e.printStackTrace();
		}

		return this.getBufferedImage();
	}
	
	public ImageIcon loadImageIcon(String svgFile, float width, float height){
		//BufferedImageTranscoder imageTranscoder = new BufferedImageTranscoder();

		FileInputStream fileSvg = null;
		try {
			fileSvg = new FileInputStream(svgFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.addTranscodingHint(PNGTranscoder.KEY_WIDTH, width);
		this.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, height);

		TranscoderInput input = new TranscoderInput(fileSvg);
		try {
			this.transcode(input, null);
		} catch (TranscoderException e) {
			e.printStackTrace();
		}

		BufferedImage i = this.getBufferedImage();
		
		return new ImageIcon(i);
	}

	public static void main(String[] args) throws TranscoderException, FileNotFoundException {
		JFrame frame = new JFrame();
		frame.setBounds(0,0,1000,650);
		frame.setLayout(null);
		TeamImageAssist assist = new TeamImageAssist();
		BufferedImage i = assist.loadImage("迭代一数据/teams/ATL.svg", 40, 40);
		ImageIcon image = new ImageIcon(i);
		JLabel label = new JLabel();
		label.setBounds(0,0,40,40);
		label.setIcon(image);
		frame.add(label);
		frame.setVisible(true);
	}
}
