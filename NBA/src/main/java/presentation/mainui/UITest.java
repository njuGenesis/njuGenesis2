package presentation.mainui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.batik.swing.*;

public class UITest {
	
	int[] num = new int[5];

	public static void main(String[] args){
		new UITest().random();

	}

	public UITest(){
		JSVGCanvas s = new JSVGCanvas(); 
		JFrame frame = new JFrame( "显示矢量图形 "); 
		
		JPanel pane = new JPanel();
		JLabel label = new JLabel();
		pane.setLayout(null);

		frame.add(pane); 
//		s.setEnableImageZoomInteractor(true); 
//		s.setEnableZoomInteractor(true);//图片大小 
//		s.setEnablePanInteractor(true);//图片移动 
		s.setSize(400, 400);
		s.setBackground(null);
		s.setURI("迭代一数据/teams/ATL.svg"); 
		pane.add(s); 
		frame.setSize(500,500); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}

	public void random(){
		int i = 0;
		while(i<5){
			int n = (int) (Math.random()*40);
			if(!exist(n)){
				num[i] = n;
				i++;
			}else{
				continue;
			}
		}
		
		for(int j=0;j<5;j++){
			System.out.println(num[j]);
		}
		
		
	}
	
	
	public boolean exist(int n){
		int i=0;
		while(i<5){
			if(n==num[i]){
				return true;
			}
			i++;
		}
		return false;
	}
	
	
	
}
