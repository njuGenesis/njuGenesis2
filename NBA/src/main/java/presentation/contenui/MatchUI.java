package presentation.contenui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import presentation.component.BgPanel;
import presentation.component.DatePanel;
import presentation.component.GLabel;
import presentation.match.MatchDetailPanel;
import presentation.match.MatchFactory;
import bussinesslogic.match.MatchLogic;
import data.po.MatchDataPO;

public class MatchUI extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String bgStr = "img/hotspot/whitebg.jpg";

	GLabel title;
	GLabel date;
	DatePanel dateChooser;

	MatchLogic logic = new MatchLogic();
	MatchFactory factory = new MatchFactory();
	JScrollPane matchPane;

	public MatchDetailPanel detail;
	
	@Override
	public void refreshUI() {
		if(detail!=null){
			detail.refreshUI();
		}else{
			this.remove(dateChooser);
			this.remove(title);
			this.remove(date);
			this.remove(matchPane);

			init();
		}
		
	}

	public MatchUI(String s) {
		super(bgStr);

		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		this.setSize(1000, 650);
		this.setLocation(15, 50);
		this.setLayout(null);
		this.setOpaque(false);

		init();

	}

	private void init(){
		dateChooser = new DatePanel(new Point(800-this.getX(),42),this);
		//		dateChooser.addActionListener(new DateListener());
		dateChooser.addDocuListener(new DateListener());

		title = new GLabel("   比赛",new Point(30,30),new Point(940,52),this,true,0,24);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);

		date = new GLabel(getToday(),new Point(30,83),new Point(940,35),this,true,0,16);
		date.setOpaque(true);
		date.setBackground(UIUtil.bgGrey);
		date.setForeground(UIUtil.bgWhite);
		date.setHorizontalAlignment(JLabel.CENTER);

		getMatchJSP();

		this.repaint();
	}

	public void getMatchJSP(){
		String day = dateChooser.getText();
		String d = day.split("-")[1] + "-" + day.split("-")[2];  //日期，如01-01


		ArrayList<MatchDataPO> matches = logic.GetDateMatch(getSeason(day)+"_"+d, getSeason(day)+"_"+d);

		JScrollPane jsp = factory.getMatchPane(this,matches);
		matchPane = jsp;
		this.add(matchPane);
		this.repaint();

	}


	private String getToday(){
		Date dateNow = new Date();  
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy年M月d日");  
		String dateNowStr = dateFormat.format(dateNow);  
		return dateNowStr;
	}

	private void updateDate(String d){
		String[] str = d.split("-");
		date.setText(str[0]+"年"+str[1]+"月"+str[2]+"日");
	}


	public String getSeason(String date){
		String season = "13-14";

		int year = 2010;
		while(true){
			String d1 = String.valueOf(year)+"-10-01";
			String d2 = String.valueOf(year+1)+"-06-30";
			if(date.compareTo(d1)>=0 && date.compareTo(d2)<=0){
				season = String.valueOf(year).substring(2, 4)+"-"+String.valueOf(year+1).substring(2, 4);
				break;
			}else{
				year++;
			}
		}
		return season;
	}


	class DateListener implements ActionListener,DocumentListener{

		public void actionPerformed(ActionEvent e) {
			if(matchPane!=null){
				MatchUI.this.remove(matchPane);
				System.out.println("remove");
			}
			getMatchJSP();
			System.out.println(dateChooser.getText());
			updateDate(dateChooser.getText());

			MatchUI.this.repaint();
		}

		public void insertUpdate(DocumentEvent e) {
			//			Document doc = e.getDocument();  
			//			try {
			//				String s = doc.getText(0, doc.getLength());
			//			} catch (BadLocationException e1) {
			//				// TODO Auto-generated catch block
			//				e1.printStackTrace();
			//			} //返回文本框输入的内容 

			if(matchPane!=null){
				MatchUI.this.remove(matchPane);
			}
			getMatchJSP();
			System.out.println(dateChooser.getText());
			updateDate(dateChooser.getText());

			MatchUI.this.repaint();
		}

		public void removeUpdate(DocumentEvent e) {}

		public void changedUpdate(DocumentEvent e) {}

	}

}
