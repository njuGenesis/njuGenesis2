package presentation.mainui;
 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GMainUIStart {

	public static void main(String[] args) {
		
		final GMainUI turnerNewsPaper = new GMainUI();
		//turnerNewsPaper.setPages("img/", "", "png", 6, 500, 650);//8是个数，后面是图片大小
		turnerNewsPaper.setMargins(0, 50);//设置第一张图片的位置
		//turnerNewsPaper.setBackground(new Color(157,185,235));
		//turnerNewsPaper.setLeftPageIndex(-1);//第一张图片的编号
		//turnerNewsPaper.setLayout(null);
		turnerNewsPaper.setBounds(0, 50, 1000, 650);
		
		JPanel bg = new JPanel();
		bg.setBounds(0, 0, 1000, 800);
		
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.setContentPane(turnerNewsPaper);
		
		//f.setContentPane(bg);
		f.getContentPane().add(turnerNewsPaper);
		f.setSize(1000, 800);
		//f.setLocationRelativeTo(null);
		f.setTitle("JBookPanel (first version)");
		f.setVisible(true);
		
		JButton last = new JButton("last");
		last.setLocation(260, 710);
		last.setSize(60, 30);
		last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = turnerNewsPaper.leftPageIndex;
				if(x!=-1){
					//turnerNewsPaper.previousPage();
				}
			}
		});
		
		JButton next = new JButton("next");
		next.setLocation(260, 750);
		next.setSize(60, 30);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				int x = turnerNewsPaper.leftPageIndex;
//				if(x==turnerNewsPaper.nrOfPages+1){
//				}else{
//						//turnerNewsPaper.nextPage();
//				}
			}
		});
		f.getContentPane().add(last);
		f.getContentPane().add(next);
	}
}