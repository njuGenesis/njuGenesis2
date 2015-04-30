package presentation.match;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import presentation.component.GLabel;
import presentation.component.HoriDynamicBarLeft;
import presentation.component.HoriDynamicBarRight;
import presentation.component.StyleScrollPane;
import presentation.component.StyleTable;
import presentation.component.TeamImageAssist;
import presentation.contenui.TableUtility;
import presentation.contenui.TurnController;
import presentation.contenui.UIUtil;
import presentation.mainui.StartUI;
import data.po.MatchDataPO;
import data.po.Match_PlayerPO;

public class MatchFactory {

	private int oneHeight = 120;
	private int pointIntervalLR = 85;  //得分显示的左右间隔
	private int pointIntervalUD = 52;  //得分显示的上下间隔

	private TeamImageAssist imgAssist = new TeamImageAssist();


	public JPanel getInfoPanel(MatchDataPO po){
		JPanel jp = new JPanel();
		jp.setBounds(30, 120, 940, 500);
		jp.setLayout(null);
		jp.setOpaque(false);

		GLabel team1 = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getFirstteam()+".svg", 180, 140),new Point(88,55),new Point(180,140),jp,true);
		GLabel team2 = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getSecondteam()+".svg", 180, 140),new Point(688,55),new Point(180,140),jp,true);
		team1.addMouseListener(new TeamListener(po.getFirstteam()));
		team1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		team2.addMouseListener(new TeamListener(po.getSecondteam()));
		team2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GLabel pointall_1 = new GLabel(getPoint1(po.getPoints()),new Point(285,120),new Point(150,40),jp,true,0,30);
		pointall_1.setHorizontalAlignment(JLabel.CENTER);
		GLabel pointall_2 = new GLabel(getPoint2(po.getPoints()),new Point(515,120),new Point(150,40),jp,true,0,30);
		pointall_2.setHorizontalAlignment(JLabel.CENTER);
		changeLabelColor(pointall_1,pointall_2);

		GLabel name_1 = new GLabel(TableUtility.getChTeam(po.getFirstteam()),new Point(60,230),new Point(230,40),jp,true,0,26);
		name_1.setHorizontalAlignment(JLabel.CENTER);
		GLabel name_2 = new GLabel(TableUtility.getChTeam(po.getSecondteam()),new Point(660,230),new Point(230,40),jp,true,0,26);
		name_2.setHorizontalAlignment(JLabel.CENTER);

		//		GLabel union_1 = new GLabel(getPoint1(po.getPoints()),new Point(290,160),new Point(150,40),jp,true,0,30);
		//		GLabel union_2 = new GLabel(getPoint2(po.getPoints()),new Point(515,160),new Point(150,40),jp,true,0,30);

		GLabel date = new GLabel(getDate(po.getDate()),new Point(380,30),new Point(200,30),jp,true,0,16);
		GLabel vs = new GLabel("VS",new Point(450,125),new Point(85,30),jp,true,0,24);

		jp.add(getInfoOneMatch(po));

		return jp;
	}

	public JPanel getTeamPanel(ArrayList<Match_PlayerPO> players){
		JPanel jp = new JPanel();
		jp.setBounds(30, 120, 940, 500);
		jp.setLayout(null);
		jp.setOpaque(false);
		
		GLabel team = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+players.get(0).getTeam()+".svg", 50, 40),new Point(45,10),new Point(50,40),jp,true);
		GLabel name = new GLabel(TableUtility.getChTeam(players.get(0).getTeam()),new Point(100,20),new Point(200,30),jp,true,0,16);
		
		String[] headerStr = {"姓名","位置","分钟","投篮%","命中","出手","三分%","三分命中","三分出手","罚球%","罚球命中","罚球出手",
				"进攻","防守","篮板","助攻","犯规","抢断","失误","盖帽","得分"};  
		Vector<String> header = getHeader(headerStr);
		Vector<Vector<Object>> data = getTableDataV(players);
		
//		StyleTable table = new StyleTable(data,header);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table.setSize(940, 420);
//		table.setPreferredScrollableViewportSize(new Dimension(1200, 420));
		
//		JScrollPane pane = new JScrollPane(table);
//		pane.setBounds(0, 60, 940, 420);
//		pane.add(table);
		
		StyleTable table = new StyleTable();
		StyleScrollPane pane = new StyleScrollPane(table);
		table.tableSetting(table, header, data, pane, new Rectangle(0, 60, 940, 420));
		
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer(){
			public java.awt.Component getTableCellRendererComponent(JTable t, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(new Color(235, 236, 231));
				else
					setBackground(new Color(251, 251, 251));

				setForeground(UIUtil.nbaBlue);
				return super.getTableCellRendererComponent(t, value, isSelected,
						hasFocus, row, column);
			}
		};
		table.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer);
		
		jp.add(pane);
		
		return jp;
	}

	public JPanel getComparePanel(MatchDataPO po){
		JPanel jp = new JPanel();
		jp.setBounds(30, 120, 940, 500);
		jp.setLayout(null);
		jp.setOpaque(false);
		
		GLabel team1 = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getFirstteam()+".svg", 180, 140),new Point(88,20),new Point(180,140),jp,true);
		GLabel team2 = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getSecondteam()+".svg", 180, 140),new Point(688,20),new Point(180,140),jp,true);

		GLabel pointall_1 = new GLabel(getPoint1(po.getPoints()),new Point(285,85),new Point(150,40),jp,true,0,30);
		pointall_1.setHorizontalAlignment(JLabel.CENTER);
		GLabel pointall_2 = new GLabel(getPoint2(po.getPoints()),new Point(515,85),new Point(150,40),jp,true,0,30);
		pointall_2.setHorizontalAlignment(JLabel.CENTER);
		changeLabelColor(pointall_1,pointall_2);
		GLabel vs = new GLabel("VS",new Point(450,90),new Point(85,30),jp,true,0,24);

		GLabel name_1 = new GLabel(TableUtility.getChTeam(po.getFirstteam()),new Point(60,170),new Point(230,40),jp,true,0,26);
		name_1.setHorizontalAlignment(JLabel.CENTER);
		GLabel name_2 = new GLabel(TableUtility.getChTeam(po.getSecondteam()),new Point(660,170),new Point(230,40),jp,true,0,26);
		name_2.setHorizontalAlignment(JLabel.CENTER);
		
		String[] item = {"投篮%","三分%","罚球%","篮板","助攻"};
		double[] data1 = getData(1,po);
		double[] data2 = getData(2,po);
		
		for(int i=0;i<5;i++){
			GLabel l = new GLabel(item[i],new Point(430,232+51*i),new Point(80,40),jp,true,0,15);
			l.setHorizontalAlignment(JLabel.CENTER);
			
			HoriDynamicBarLeft left = new HoriDynamicBarLeft(data1[i],new Dimension(300,40));
			left.setLocation(45, 232+51*i);
			jp.add(left);
			
			HoriDynamicBarRight right = new HoriDynamicBarRight(data2[i],new Dimension(300,40));
			right.setLocation(545, 232+51*i);
			jp.add(right);
			
			changeBarColor(left,right);
			
			left.showOut();
			right.showOut();
		}
		
		JPanel grey = new JPanel();
		grey.setBounds(395, 212, 150, 280);
		grey.setBackground(UIUtil.tableGrey);
		grey.setOpaque(true);
		grey.setLayout(null);
		jp.add(grey);
		
		return jp;
	}


	public JScrollPane getMatchPane(Container big,ArrayList<MatchDataPO> matches){
		int num = matches.size();

		JPanel panel = new JPanel();
		
		panel.setOpaque(false);
		//		panel.setBounds(0, 0, 905, oneHeight*num);
		panel.setLocation(0, 0);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(905, oneHeight*num));

		if(num>0){
			
			for(int i=0;i<num;i++){
				panel.add(getOneMatch(big,i,matches.get(i)));
			}
		}else{
			panel.setPreferredSize(new Dimension(905, 500));
			GLabel l = new GLabel(new ImageIcon("img/match/nogame.png"),new Point(350,150),new Point(200,200),panel,true);
		}
		JScrollPane jsp = new JScrollPane(panel);
		jsp.setLocation(30, 119);
		jsp.setBorder(null);
		jsp.setSize(940,500);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		//		jsp.setPreferredSize(new Dimension(910,500));
		//		jsp.setViewportView(panel);

		return jsp;
	}

	//返回显示一场比赛的panel
	public JPanel getOneMatch(Container big,int num,MatchDataPO po){
		JPanel jp = new JPanel();
		jp.setBounds(0,(num-1)*oneHeight,935,oneHeight);
		jp.setLayout(null);
		jp.setOpaque(true);
		if(num%2!=0){
			jp.setBackground(UIUtil.tableGrey);
		}else{
			jp.setBackground(UIUtil.bgWhite);
		}

		int allPointX = 764;  //总得分的x坐标

		GLabel team1 = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getFirstteam()+".svg", 50, 40),new Point(20,22),new Point(50,40),jp,true);
		GLabel team2 = new GLabel(imgAssist.loadImageIcon("迭代一数据/teams/"+po.getSecondteam()+".svg", 50, 40),new Point(20,74),new Point(50,40),jp,true);

		GLabel name1 = new GLabel(TableUtility.getShortChTeam(po.getFirstteam()),new Point(98,30),new Point(180,30),jp,true,0,18);
		GLabel name2 = new GLabel(TableUtility.getShortChTeam(po.getSecondteam()),new Point(98,82),new Point(180,30),jp,true,0,18);

		GLabel l1 = new GLabel("1",new Point(194,6),new Point(50,20),jp,true,0,13);
		GLabel point1_1 = new GLabel(getPoint1(po.getFirst_pts()),new Point(187,32),new Point(100,30),jp,true,0,18);
		GLabel point1_2 = new GLabel(getPoint2(po.getFirst_pts()),new Point(187,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point1_1,point1_2);

		GLabel l2 = new GLabel("2",new Point(279,6),new Point(50,20),jp,true,0,13);
		GLabel point2_1 = new GLabel(getPoint1(po.getSecond_pts()),new Point(187+pointIntervalLR,32),new Point(100,30),jp,true,0,18);
		GLabel point2_2 = new GLabel(getPoint2(po.getSecond_pts()),new Point(187+pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point2_1,point2_2);

		GLabel l3 = new GLabel("3",new Point(366,6),new Point(50,20),jp,true,0,13);
		GLabel point3_1 = new GLabel(getPoint1(po.getThird_pts()),new Point(187+2*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
		GLabel point3_2 = new GLabel(getPoint2(po.getThird_pts()),new Point(187+2*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point3_1,point3_2);

		GLabel l4 = new GLabel("4",new Point(452,6),new Point(50,20),jp,true,0,13);
		GLabel point4_1 = new GLabel(getPoint1(po.getForth_pts()),new Point(187+3*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
		GLabel point4_2 = new GLabel(getPoint2(po.getForth_pts()),new Point(187+3*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point4_1,point4_2);

		if(!po.getFifth_pts().equals("null")){
			GLabel l5 = new GLabel("加时赛1",new Point(520,6),new Point(50,20),jp,true,0,13);
			GLabel point5_1 = new GLabel(getPoint1(po.getFifth_pts()),new Point(187+4*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
			GLabel point5_2 = new GLabel(getPoint2(po.getFifth_pts()),new Point(187+4*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
			changeLabelColor(point5_1,point5_2);
		}

		if(!po.getSixth_pts().equals("null")){
			GLabel l6 = new GLabel("加时赛2",new Point(604,6),new Point(50,20),jp,true,0,13);
			GLabel point6_1 = new GLabel(getPoint1(po.getSixth_pts()),new Point(187+5*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
			GLabel point6_2 = new GLabel(getPoint2(po.getSixth_pts()),new Point(187+5*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
			changeLabelColor(point6_1,point6_2);
		}

		if(!po.getSeventh_pts().equals("null")){
			GLabel l7 = new GLabel("加时赛3",new Point(691,6),new Point(50,20),jp,true,0,13);
			GLabel point7_1 = new GLabel(getPoint1(po.getSeventh_pts()),new Point(187+6*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
			GLabel point7_2 = new GLabel(getPoint2(po.getSeventh_pts()),new Point(187+6*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
			changeLabelColor(point7_1,point7_2);
		}

		GLabel pointall_1 = new GLabel(getPoint1(po.getPoints()),new Point(764,27),new Point(120,30),jp,true,0,26);
		GLabel pointall_2 = new GLabel(getPoint2(po.getPoints()),new Point(764,27+pointIntervalUD),new Point(120,30),jp,true,0,26);
		changeLabelColor(pointall_1,pointall_2);

		MatchDetailLabel turn = new MatchDetailLabel(big,jp,po);

		return jp;
	}


	private JPanel getInfoOneMatch(MatchDataPO po){
		JPanel jp = new JPanel();
		jp.setBounds(30,360,935,oneHeight);
		jp.setLayout(null);
		jp.setOpaque(true);
		jp.setBackground(UIUtil.tableGrey);

		GLabel name1 = new GLabel(TableUtility.getShortChTeam(po.getFirstteam()),new Point(98,30),new Point(180,30),jp,true,0,18);
		GLabel name2 = new GLabel(TableUtility.getShortChTeam(po.getSecondteam()),new Point(98,82),new Point(180,30),jp,true,0,18);

		GLabel l1 = new GLabel("1",new Point(194,6),new Point(50,20),jp,true,0,13);
		GLabel point1_1 = new GLabel(getPoint1(po.getFirst_pts()),new Point(187,32),new Point(100,30),jp,true,0,18);
		GLabel point1_2 = new GLabel(getPoint2(po.getFirst_pts()),new Point(187,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point1_1,point1_2);

		GLabel l2 = new GLabel("2",new Point(279,6),new Point(50,20),jp,true,0,13);
		GLabel point2_1 = new GLabel(getPoint1(po.getSecond_pts()),new Point(187+pointIntervalLR,32),new Point(100,30),jp,true,0,18);
		GLabel point2_2 = new GLabel(getPoint2(po.getSecond_pts()),new Point(187+pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point2_1,point2_2);

		GLabel l3 = new GLabel("3",new Point(366,6),new Point(50,20),jp,true,0,13);
		GLabel point3_1 = new GLabel(getPoint1(po.getThird_pts()),new Point(187+2*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
		GLabel point3_2 = new GLabel(getPoint2(po.getThird_pts()),new Point(187+2*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point3_1,point3_2);

		GLabel l4 = new GLabel("4",new Point(452,6),new Point(50,20),jp,true,0,13);
		GLabel point4_1 = new GLabel(getPoint1(po.getForth_pts()),new Point(187+3*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
		GLabel point4_2 = new GLabel(getPoint2(po.getForth_pts()),new Point(187+3*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
		changeLabelColor(point4_1,point4_2);

		if(!po.getFifth_pts().equals("null")){
			GLabel l5 = new GLabel("加时赛1",new Point(520,6),new Point(50,20),jp,true,0,13);
			GLabel point5_1 = new GLabel(getPoint1(po.getFifth_pts()),new Point(187+4*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
			GLabel point5_2 = new GLabel(getPoint2(po.getFifth_pts()),new Point(187+4*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
			changeLabelColor(point5_1,point5_2);
		}

		if(!po.getSixth_pts().equals("null")){
			GLabel l6 = new GLabel("加时赛2",new Point(604,6),new Point(50,20),jp,true,0,13);
			GLabel point6_1 = new GLabel(getPoint1(po.getSixth_pts()),new Point(187+5*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
			GLabel point6_2 = new GLabel(getPoint2(po.getSixth_pts()),new Point(187+5*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
			changeLabelColor(point6_1,point6_2);
		}

		if(!po.getSeventh_pts().equals("null")){
			GLabel l7 = new GLabel("加时赛3",new Point(691,6),new Point(50,20),jp,true,0,13);
			GLabel point7_1 = new GLabel(getPoint1(po.getSeventh_pts()),new Point(187+6*pointIntervalLR,32),new Point(100,30),jp,true,0,18);
			GLabel point7_2 = new GLabel(getPoint2(po.getSeventh_pts()),new Point(187+6*pointIntervalLR,32+pointIntervalUD),new Point(100,30),jp,true,0,18);
			changeLabelColor(point7_1,point7_2);
		}

		GLabel pointall_1 = new GLabel(getPoint1(po.getPoints()),new Point(764,27),new Point(120,30),jp,true,0,26);
		GLabel pointall_2 = new GLabel(getPoint2(po.getPoints()),new Point(764,27+pointIntervalUD),new Point(120,30),jp,true,0,26);
		changeLabelColor(pointall_1,pointall_2);

		return jp;
	}



	//比较某一小节或比赛两队得分高低，得分高用红色字体
	private void changeLabelColor(GLabel l1,GLabel l2){
		int p1 = Integer.parseInt(l1.getText());
		int p2 = Integer.parseInt(l2.getText());
		if(p1>p2){
			l1.setForeground(UIUtil.nbaRed);
		}else{
			l2.setForeground(UIUtil.nbaRed);
		}
	}


	//获得某一小节或比赛队伍一得分
	private String getPoint1(String str){
		return str.split("-")[0];
	}

	//获得某一小节或比赛队伍二得分
	private String getPoint2(String str){
		return str.split("-")[1];
	}

	//解析日期
	private String getDate(String str){
		String[] season = str.split("_");
		String[] date = season[1].split("-");
		return season[0]+"赛季  "+date[0]+"月"+date[1]+"日";
	}

	private double[] getData(int team,MatchDataPO po){
		DecimalFormat df = new DecimalFormat(".00");
		double[] res = new double[5];
		if(team==1){
			res[0] = Double.parseDouble(df.format(po.getShootEff1()*100));
			res[1] = Double.parseDouble(df.format(po.getTPShootEff1()*100));
			res[2] = Double.parseDouble(df.format(po.getFTShootEff1()*100));
			res[3] = po.getTeam1bank();
			res[4] = po.getAss1();
		}else{
			res[0] = Double.parseDouble(df.format(po.getShootEff2()*100));
			res[1] = Double.parseDouble(df.format(po.getTPShootEff2()*100));
			res[2] = Double.parseDouble(df.format(po.getFTShootEff2()*100));
			res[3] = po.getTeam2bank();
			res[4] = po.getAss2();
		}
		return res;
	}
	
	private void changeBarColor(HoriDynamicBarLeft left,HoriDynamicBarRight right){
		if(left.getValue()>right.getValue()){
			right.setColor(UIUtil.bgGrey);
		}else{
			left.setColor(UIUtil.bgGrey);
		}
	}
	
	private Object[][] getTableData(ArrayList<Match_PlayerPO> players){
		Object[][] res = new Object[players.size()][21];
		
//		String[] header = {"姓名","位置","分钟","投篮%","命中",
//				"出手","三分%","三分命中","三分出手","罚球%",
//				"罚球命中","罚球出手","进攻","防守","篮板",
//				"助攻","犯规","抢断","失误","盖帽",
//				"得分"};  //21列
		
		for(int i=0;i<players.size();i++){
			res[i][0] = players.get(i).getPlayername();
			res[i][1] = players.get(i).getState();
			res[i][2] = players.get(i).getTime();
			res[i][3] = roundDouble(players.get(i).getShootEff());
			res[i][4] = players.get(i).getShootEffNumber();
			
			res[i][5] = players.get(i).getShoot();
			res[i][6] = roundDouble(players.get(i).getTPShootEff());
			res[i][7] = players.get(i).getTPShootEffNumber();
			res[i][8] = players.get(i).getTPShoot();
			res[i][9] = roundDouble(players.get(i).getFTShootEff());
			
			res[i][10] = players.get(i).getFTShootEffNumber();
			res[i][11] = players.get(i).getFT();
			res[i][12] = players.get(i).getBankOff();
			res[i][13] = players.get(i).getBankDef();
			res[i][14] = players.get(i).getBank();
			
			res[i][15] = players.get(i).getAss();
			res[i][16] = players.get(i).getFoul();
			res[i][17] = players.get(i).getSteal();
			res[i][18] = players.get(i).getTo();
			res[i][19] = players.get(i).getRejection();
			res[i][20] = players.get(i).getPoints();
		}
		
		return res;
	}
	
	private Vector<Vector<Object>> getTableDataV(ArrayList<Match_PlayerPO> players){
		Object[][] res = new Object[players.size()][21];
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
//		String[] header = {"姓名","位置","分钟","投篮%","命中",
//				"出手","三分%","三分命中","三分出手","罚球%",
//				"罚球命中","罚球出手","进攻","防守","篮板",
//				"助攻","犯规","抢断","失误","盖帽",
//				"得分"};  //21列
		
		for(int i=0;i<players.size();i++){
			Vector<Object> v = new Vector<Object>();
			v.add(players.get(i).getPlayername());
			v.add(TableUtility.getChPosition(players.get(i).getState()));
			v.add(players.get(i).getTime());
			v.add(roundDouble(players.get(i).getShootEff()));
			v.add((int)players.get(i).getShootEffNumber());
			
			v.add((int)players.get(i).getShoot());
			v.add(roundDouble(players.get(i).getTPShootEff()));
			v.add((int)players.get(i).getTPShootEffNumber());
			v.add((int)players.get(i).getTPShoot());
			v.add(roundDouble(players.get(i).getFTShootEff()));
			
			v.add((int)players.get(i).getFTShootEffNumber());
			v.add((int)players.get(i).getFT());
			v.add((int)players.get(i).getBankOff());
			v.add((int)players.get(i).getBankDef());
			v.add((int)players.get(i).getBank());
			
			v.add((int)players.get(i).getAss());
			v.add((int)players.get(i).getFoul());
			v.add((int)players.get(i).getSteal());
			v.add((int)players.get(i).getTo());
			v.add((int)players.get(i).getRejection());
			v.add((int)players.get(i).getPoints());
			
			data.add(v);
		}
		
		return data;
	}
	
	private Vector<String> getHeader(String[] str){
		Vector<String> v = new Vector<String>();
		for(int i=0;i<str.length;i++){
			v.add(str[i]);
		}
		return v;
	}
	
	private double roundDouble(double d){
		DecimalFormat df = new DecimalFormat(".00");
		return Double.parseDouble(df.format(d));
	}
	
	
	class TeamListener implements MouseListener{
		
		String shortName;
		
		public TeamListener(String shortName){
			this.shortName = shortName;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			TurnController tc = new TurnController();
			StartUI.startUI.turn(tc.turnToTeamDetials(shortName));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
