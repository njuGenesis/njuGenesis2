package presentation.contenui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.ws.api.Component;

import bussinesslogic.match.MatchLogic;
import bussinesslogic.player.PlayerLogic;
import bussinesslogic.team.TeamLogic;
import data.po.MatchDataPO;
import data.po.PlayerDataPO;
import data.po.TeamDataPO;
import presentation.component.BgPanel;
import presentation.component.CustomScrollBarUI;
import presentation.component.DateLabel;
import presentation.component.GLabel;
import presentation.component.StyleScrollPane;
import presentation.component.StyleTable;
import presentation.component.TeamImageAssist;
import presentation.hotspot.SelectLabel;

public class TeamDetials extends BgPanel{
	
	private static final long serialVersionUID = 1L;
	private GLabel title;
	private SelectLabel tdMenu[];
	private TeamDataPO po;
	private BgPanel sonPanel;
	private TeamLogic teamLogic = new TeamLogic();
	private PlayerLogic playerLogic = new PlayerLogic();
	
	public TeamDetials(String shortName){
		super("");

		this.po = teamLogic.GetBySN(shortName, playerLogic.getLatestSeason());
		
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		this.setBounds(0, 0, 1000, 650);
		this.setVisible(true);
		
		title = new GLabel("  "+TableUtility.getChTeam(po.getShortName()), new Point(26, 30), new Point(948, 52), this, true, 0, 25);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);
		
		tdMenu = new SelectLabel[5];
		tdMenu[0] = new SelectLabel("资料", new Point(26, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[1] = new SelectLabel("球员", new Point(26+188+2, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[2] = new SelectLabel("比赛", new Point(26+(188+2)*2, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[3] = new SelectLabel("数据", new Point(26+(188+2)*3, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[4] = new SelectLabel("对比", new Point(26+(188+2)*4, 83), new Point(188, 35), this, true, 0, 18);
		
		tdMenu[0].setSelected(true);
		TeamInfo info = new TeamInfo(TeamDetials.this.po);
		sonPanel = info;
		TeamDetials.this.add(sonPanel);
		
		tdMenu[0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[0].setSelected(true);
				TeamInfo info = new TeamInfo(TeamDetials.this.po);
				TeamDetials.this.remove(sonPanel);
				sonPanel = info;
				TeamDetials.this.add(sonPanel);
				repaint();
			}
		});
		tdMenu[1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[1].setSelected(true);
				TeamPlayer player = new TeamPlayer(TeamDetials.this.po);
				TeamDetials.this.remove(sonPanel);
				sonPanel = player;
				TeamDetials.this.add(sonPanel);
				repaint();
			}
		});
		
		tdMenu[2].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[2].setSelected(true);
				TeamMatch match = new TeamMatch(TeamDetials.this.po);
				TeamDetials.this.remove(sonPanel);
				sonPanel = match;
				TeamDetials.this.add(sonPanel);
				repaint();
			}
		});
		
		tdMenu[3].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[3].setSelected(true);
				TeamData data = new TeamData(TeamDetials.this.po);
				TeamDetials.this.remove(sonPanel);
				sonPanel = data;
				TeamDetials.this.add(sonPanel);
				repaint();
			}
		});
		
		tdMenu[4].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[4].setSelected(true);
				repaint();
			}
		});
	}
}
 
class TeamInfo extends BgPanel{

	private static final long serialVersionUID = 1L;
	private static String file = "img/teamDetials/info.png";
	
	private TeamImageAssist assist;
	private GLabel teamPic, name, shortName, city, time, position;
	private JTextArea place;
	
	public TeamInfo(TeamDataPO po){
		super(file);
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		
		assist = new TeamImageAssist();
		
		teamPic = new GLabel(assist.loadImageIcon("img/teams/"+po.getShortName()+".svg", 360, 360), new Point(281, 60), new Point(360, 360), this, true);
		name = new GLabel(po.getName(), new Point(103, 40), new Point(180, 25), this, true, 0, 20);
		shortName = new GLabel(po.getShortName(), new Point(103, 75), new Point(180, 25), this, true, 0, 20);
		city = new GLabel(po.getCity(), new Point(733, 144), new Point(180, 25), this, true, 0, 20);
		time = new GLabel(String.valueOf(po.getBuildyear()), new Point(762, 179), new Point(180, 25), this, true, 0, 20);
		position = new GLabel(po.getEorW()+"-"+po.getArea(), new Point(246, 467), new Point(180, 25), this, true, 0, 20);
		place = new JTextArea();
		place.setEditable(false);
		place.setLineWrap(true);
		place.setWrapStyleWord(true);
		place.setBounds(733, 211, 200, 50);
		place.setText(po.getMainposition());
		place.setFont(new Font("微软雅黑", 0, 20));
		place.setOpaque(false);
		this.add(place);
	}
	
}

class TeamPlayer extends BgPanel{

	private static final long serialVersionUID = 1L;
	private static String file = "";
	private PlayerLogic playerLogic;
	private JCheckBox checkBox1, checkBox2;
	private StyleTable tableBasic, tableDetials;
	private StyleScrollPane scrollPaneBasic, scrollPaneDetials;
	private PlayerDataPO[] playerDataPOs;
	private Rectangle rectangle;
	
	public TeamPlayer(TeamDataPO po) {
		super(file);
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(UIUtil.bgWhite);
		
		playerLogic = new PlayerLogic();
		
		GLabel message = new GLabel("*单击表头可排序", new Point(34, 5), new Point(120, 30), this, true, 0, 13);
		
	    playerDataPOs = playerLogic.getPlayerByTeam(po.getShortName(), "null", "null", po.getSeason());
	    
	    rectangle = new Rectangle(14, 35, 920, 480);
	    
	    basicSetting();
	    detialsSetting();
	    scrollPaneBasic.setVisible(true);
		
		checkBox1 = new JCheckBox("信息");
		checkBox1.setBounds(740, 3, 70, 30);
		checkBox1.setSelected(true);
		this.add(checkBox1);
		checkBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox1.isSelected()){
					checkBox2.setSelected(false);
					scrollPaneDetials.setVisible(false);
					scrollPaneBasic.setVisible(true);
				}else{
					checkBox2.setSelected(true);
					scrollPaneDetials.setVisible(false);
					scrollPaneBasic.setVisible(true);
				}
			}
		});
		
		checkBox2 = new JCheckBox("数据");
		checkBox2.setBounds(810, 3, 70, 30);
		this.add(checkBox2);
		checkBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox2.isSelected()){
					checkBox1.setSelected(false);
					scrollPaneBasic.setVisible(false);
					scrollPaneDetials.setVisible(true);
				}else{
					checkBox1.setSelected(true);
					scrollPaneDetials.setVisible(false);
					scrollPaneBasic.setVisible(true);
				}
			}
		});
		
		repaint();
	}
	
	private void basicSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("姓名");header.addElement("位置");
		header.addElement("号码");header.addElement("身高");
		header.addElement("体重");header.addElement("生日");header.addElement("球龄");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<playerDataPOs.length;i++){
			PlayerDataPO p = playerDataPOs[i];
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(p.getName());
			vector.addElement(p.getPosition());
			vector.addElement(Integer.valueOf(p.getNumber()));
			vector.addElement(p.getHeight());
			vector.addElement(p.getWeight());
			vector.addElement(p.getBirth());
			vector.addElement(p.getExp());
			data.addElement(vector);
		}
		
		tableBasic = new StyleTable();
		scrollPaneBasic = new StyleScrollPane(tableBasic);
		tableBasic.tableSetting(tableBasic, header, data, scrollPaneBasic, rectangle);
		tableSetting(tableBasic);
		tableBasic.setSort();
		scrollPaneBasic.setVisible(false);
		this.add(scrollPaneBasic);
	}
	
	private void detialsSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("姓名");header.addElement("场数");
		header.addElement("先发");header.addElement("分钟");
		header.addElement("使用");header.addElement("场均得分");header.addElement("三分");header.addElement("罚球");header.addElement("进攻");header.addElement("防守");
		header.addElement("场均篮板");
		header.addElement("场均助攻");header.addElement("场均抢断");header.addElement("场均盖帽");header.addElement("失误");header.addElement("犯规");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<playerDataPOs.length;i++){
			PlayerDataPO p = playerDataPOs[i];
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(p.getName());
			vector.addElement(p.getGP());
			vector.addElement(p.getGS());
			vector.addElement(p.getMinutesOnField());
			vector.addElement(p.getUseEff());
			vector.addElement(p.getPPG());
			vector.addElement(p.getThreePGPercentage());
			vector.addElement(p.getFTPercentage());
			vector.addElement(p.getOff());
			vector.addElement(p.getDef());
			vector.addElement(p.getBPG());
			vector.addElement(p.getAPG());
			vector.addElement(p.getStealPG());
			vector.addElement(p.getRPG());
			vector.addElement(p.getTo());
			vector.addElement(p.getFoul());
			data.addElement(vector);
		}
		
		tableDetials = new StyleTable();
		scrollPaneDetials = new StyleScrollPane(tableDetials);
		tableDetials.tableSetting(tableDetials, header, data, scrollPaneDetials, rectangle);
		tableSetting(tableDetials);
		tableDetials.setSort();
		scrollPaneDetials.setVisible(false);
		this.add(scrollPaneDetials);
	}
	
	private void tableSetting(final JTable table){
		table.setPreferredScrollableViewportSize(new Dimension(920, 480));//设置大小
		table.setBounds(14, 35, 920, 480);
		table.getTableHeader().setPreferredSize(new Dimension(920, 30));//设置表头大小

		table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer(){
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
		});
		
		table.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/table.getRowHeight();

				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && column == 0) {
					table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}else{
					table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
			
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			private final JTable thisTable = table;

		};
		table.addMouseListener(mouseAdapter);
	}
}

class TeamMatch extends BgPanel{

	private static final long serialVersionUID = 1L;
	private static String file = "";
	private MatchLogic matchLogic;
	private DateLabel dateLabel1, dateLabel2;
	
	public TeamMatch(TeamDataPO po) {
		super(file);
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		this.setVisible(true);
		
		matchLogic = new MatchLogic();
		
		dateLabel1 = new DateLabel(new Point(25, 9), this);
		dateLabel1.setBackground(UIUtil.bgWhite);
		dateLabel1.setForeground(Color.black);
		dateLabel1.setIcon(new ImageIcon("img/teamDetials/dateIcon.png"));
		
		dateLabel2 = new DateLabel(new Point(150, 9), this);
		dateLabel2.setBackground(UIUtil.bgWhite);
		dateLabel2.setForeground(Color.black);
		dateLabel2.setIcon(new ImageIcon("img/teamDetials/dateIcon.png"));
		
		JCheckBox checkBox = new JCheckBox("hh");
		checkBox.setBounds(300, 9, 300, 30);
		this.add(checkBox);
		
		final String[] header = {"日期", "对手", "结果", "比分", "链接"};
		ArrayList<MatchDataPO> matches = matchLogic.GetInfo(po.getShortName(), "13-14");
		final String[][] data = new String[matches.size()][header.length];
		for(int i=0;i<data.length;i++){
			MatchDataPO m = matches.get(i);
			data[i][0] = m.getDate();System.out.println(m.getDate());
			if(m.getFirstteam().equals(po.getShortName())){
				data[i][1] = m.getSecondteam();
			}else{
				data[i][1] = m.getFirstteam();
			}
			if(m.getWinner().equals(po.getShortName())){
				data[i][2] = "胜";
			}else{
				data[i][2] = "负";
			}
			data[i][3] = m.getPoints();
			data[i][4] = "比赛链接";
		}
//		final String[][] data = new String[10][5];
//		for(int i=0;i<data.length;i++){
//			data[i][0] = String.valueOf(i);
//			data[i][1] = "hh";
//			data[i][2] = "hh";
//			data[i][3] = "hh";
//			data[i][4] = "hh";
//		}
		
		
		TableModel tableModel = new DefaultTableModel(data, header){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
		        return false;
		    }
		    public String getColumnName(int columnIndex) {
		        return header[columnIndex];
		    }
		 
		    public int getColumnCount() {return header.length;}
		    public int getRowCount() { return data.length; }
		    public Object getValueAt(int row, int col) {
		    	return data[row][col];
		    }
		};
		
//		AbstractTableModel matchModel = new AbstractTableModel() {
//			private static final long serialVersionUID = 1L;
//			private String[] head = header;
//			private Class<?>[] columnTypes = new Class<?>[] {String.class,GLabel.class,String.class,String.class,GLabel.class};
//			private String[][] column = data;
//			
//			public boolean isCellEditable(int rowIndex, int columnIndex) {
//		        return false;
//		    }
//			public int getColumnCount() {
//				return head.length;
//			}
//
//			public int getRowCount() {
//				return column.length;
//			}
//
//			@Override public String getColumnName(int columnIndex) {
//				return head[columnIndex];
//			}
//
//			@Override public Class<?> getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//
//			public Object getValueAt(final int rowIndex, final int columnIndex) {
//				switch(columnIndex){
//				case 0:return column[rowIndex][columnIndex];
//				case 1:GLabel team = new GLabel(column[rowIndex][columnIndex], new Point(180,0), new Point(180, 30), null, true, 0, 20);
//					   team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//					   return team;
//				case 2:return column[rowIndex][columnIndex];
//				case 3:return column[rowIndex][columnIndex];
//				case 4:GLabel link = new GLabel(column[rowIndex][columnIndex], new Point(), new Point(), null, true, 0, 20);
//				       link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//				       return link;
//				}
//				return column[rowIndex][columnIndex];
//			}   
//		};

		final StyleTable table = new StyleTable(tableModel);
		tableSetting(table);
		
		JScrollPane scrollPane = new JScrollPane(); 
		scrollPane.setBounds(14, 50, 920, 460);
		scrollPane.setViewportView(table);
		//scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		//scrollPane.setLayout(null);
		scrollPane.getViewport().setOpaque(false);
		//scrollPane.setBackground(UIUtil.nbaBlue);
		
		this.add(scrollPane);
		
		repaint();
	}
	
	private void tableSetting(final JTable table){
		table.setPreferredScrollableViewportSize(new Dimension(920, 480));//设置大小
		table.setBounds(14, 35, 920, 480);
		table.getTableHeader().setPreferredSize(new Dimension(920, 30));//设置表头大小
		
		table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer(){
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
		});
		
		table.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/table.getRowHeight();

				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && column == 0) {
					table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}else{
					table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
			
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			private final JTable thisTable = table;

		};
		table.addMouseListener(mouseAdapter);
	}
}

class TeamData extends BgPanel{
	
	private static final long serialVersionUID = 1L;
	private static String file = "img/teamDetials/data.png";
	private StyleTable tableTotal, tableAverage, tableEff;
	private JScrollPane scrollPaneTotal, scrollPaneAverage, scrollPaneEff;
	private TeamImageAssist assist;
	private JCheckBox checkBox1, checkBox2, checkBox3;
	
	public TeamData(TeamDataPO po){
		super(file);
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		this.setVisible(true);
		
		GLabel message = new GLabel("*单击表头可排序", new Point(34, 187), new Point(120, 30), this, true, 0, 13);
		
		assist = new TeamImageAssist();
		
		GLabel teamPic = new GLabel(assist.loadImageIcon("img/teams/"+po.getShortName()+".svg", 200, 200), new Point(75, 0), new Point(200, 200), this, true);
		GLabel wr = new GLabel(String.valueOf(po.getWR()), new Point(372, 68), new Point(200, 50), this, true, 0, 50);
		GLabel matchNumber = new GLabel(String.valueOf((int)po.getMatchNumber()), new Point(610, 20), new Point(200, 37), this, true, 1, 24);
		GLabel winNumber = new GLabel(String.valueOf((int)po.getWinMatch()), new Point(723, 20), new Point(200, 37), this, true, 1, 24);
		GLabel failNumber = new GLabel(String.valueOf((int)(po.getMatchNumber()-po.getWinMatch())), new Point(828, 20), new Point(200, 37), this, true, 1, 24);
		GLabel pts = new GLabel(String.valueOf((int)po.getPTS()), new Point(627, 69), new Point(200, 37), this, true, 1, 24);
		GLabel ptsPG = new GLabel(String.valueOf(po.getPPG()), new Point(781, 69), new Point(200, 37), this, true, 1, 24);
		GLabel lps = new GLabel(String.valueOf((int)po.getLPS()), new Point(627, 118), new Point(200, 37), this, true, 1, 24);
		GLabel lpsP = new GLabel(String.valueOf(po.getLPG()), new Point(781, 118), new Point(200, 37), this, true, 1, 24);
		
		final String[] headerTotal = {"赛季        ", "投篮数", "投篮命中数", "三分数", "三分命中数", "罚球数", "罚球命中数", "进攻篮板", "防守篮板", "对手后场篮板", 
				"对手前场篮板", "篮板数", "助攻数", "抢断数", "盖帽数", "失误数", "犯规数", "进攻回合数", "防守回合数"};
		final Object[][] dataToral = new Object[1][headerTotal.length];
		for(int i=0;i<dataToral.length;i++){
			
			dataToral[i][0] = po.getSeason();
			dataToral[i][1] = po.getShootNumber();
			dataToral[i][2] = (int) po.getShootEffNumber();
			dataToral[i][3] = po.getTPNumber();
			dataToral[i][4] = (int)po.getTPEffNumber();
			dataToral[i][5] = po.getFTNumber();
			dataToral[i][6] = (int)po.getFTEffNumber();
			dataToral[i][7] = (int)po.getOffBackBoard();
			dataToral[i][8] = (int)po.getDefBackBoard();
			dataToral[i][9] = (int)po.getOtherDefBoard();
			dataToral[i][10] = (int)po.getOtherOffBoard();
			dataToral[i][11] = po.getBackBoard();
			dataToral[i][12] = po.getAssitNumber();
			dataToral[i][13] = po.getStealNumber();
			dataToral[i][14] = po.getRejection();
			dataToral[i][15] = po.getTo();
			dataToral[i][16] = po.getFoul();
			dataToral[i][17] = (int)po.getOff();
			dataToral[i][18] = (int)po.getDef();
		}
		final TableModel tableModelTotal = new DefaultTableModel(dataToral, headerTotal){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
		        return false;
		    }
		    public String getColumnName(int columnIndex) {
		        return headerTotal[columnIndex];
		    }
		 
		    public int getColumnCount() {return headerTotal.length;}
		    public int getRowCount() { return dataToral.length; }
		    public Object getValueAt(int row, int col) {
		    	return dataToral[row][col];
		    }
		    public Class<?> getColumnClass(int column) {  
		        Class<?> returnValue;  
		        if ((column >= 0) && (column < getColumnCount())) {  
		            returnValue = getValueAt(0, column).getClass();  
		        } else {  
		            returnValue = Object.class;  
		        }  
		        return returnValue;  
		    }
		};
		tableTotal = new StyleTable();
		tableTotal.setStyleTabelModel(tableModelTotal);
		tableSetting(tableTotal);
		tableTotal.setSort();
		
		scrollPaneTotal = new JScrollPane(); 
		scrollPaneTotal.setBounds(14, 215, 920, 300);
		//scrollPaneTotal.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPaneTotal.setBorder(null);
		scrollPaneTotal.setOpaque(false);
		scrollPaneTotal.getViewport().setOpaque(false);
		scrollPaneTotal.setViewportView(tableTotal);
		scrollPaneTotal.setVisible(true);
		this.add(scrollPaneTotal);
		

		final String[] headerAverage = {"赛季        ", "场均投篮数", "场均投篮命中数", "场均三分数", "场均三分命中数", "场均罚球数", "场均罚球命中数", "场均进攻篮板", 
				"场均防守篮板", "场均篮板数", "场均助攻数", "场均抢断数", "场均盖帽数", "场均失误数", "场均犯规数", "场均进攻回合数", "场均防守回合数"};
		final Object[][] dataAverage = new Object[1][headerAverage.length];
		for(int i=0;i<dataToral.length;i++){

			dataAverage[i][0] = po.getSeason();
			dataAverage[i][1] = po.getShootNumberPG();
			dataAverage[i][2] = po.getShootEffNumberPG();
			dataAverage[i][3] = po.getTPNumberPG();
			dataAverage[i][4] = po.getTPEffNumberPG();
			dataAverage[i][5] = po.getFTNumberPG();
			dataAverage[i][6] = po.getFTEffNumberPG();
			dataAverage[i][7] = po.getOffBackBoardPG();
			dataAverage[i][8] = po.getDefBackBoardPG();
			dataAverage[i][9] = po.getBackBoardPG();
			dataAverage[i][10] = po.getAssitNumberPG();
			dataAverage[i][11] = po.getStealNumberPG();
			dataAverage[i][12] = po.getRejectionPG();
			dataAverage[i][13] = po.getToPG();
			dataAverage[i][14] = po.getFoulPG();
			dataAverage[i][15] = po.getOffPG();
			dataAverage[i][16] = po.getDefPG();
		}
		final TableModel tableModelAverage = new DefaultTableModel(dataAverage, headerAverage){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			public String getColumnName(int columnIndex) {
				return headerAverage[columnIndex];
			}

			public int getColumnCount() {return headerAverage.length;}
			public int getRowCount() { return dataAverage.length; }
			public Object getValueAt(int row, int col) {
				return dataAverage[row][col];
			}
			public Class<?> getColumnClass(int column) {  
				Class<?> returnValue;  
				if ((column >= 0) && (column < getColumnCount())) {  
					returnValue = getValueAt(0, column).getClass();  
				} else {  
					returnValue = Object.class;  
				}  
				return returnValue;  
			}
		};
		tableAverage = new StyleTable();
		tableAverage.setStyleTabelModel(tableModelAverage);
		tableSetting(tableAverage);
		tableAverage.setSort();

		scrollPaneAverage = new JScrollPane(); 
		scrollPaneAverage.setBounds(14, 215, 920, 300);
		//scrollPaneAverage.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPaneAverage.setBorder(null);
		scrollPaneAverage.setOpaque(false);
		scrollPaneAverage.getViewport().setOpaque(false);
		scrollPaneAverage.setViewportView(tableAverage);
		scrollPaneAverage.setVisible(false);
		this.add(scrollPaneAverage);
		
		
		final String[] headerEff = {"赛季        ", "投篮命中率", "三分命中率", "罚球命中率", "进攻效率", "防守效率", "进攻篮板效率", 
				"防守篮板效率", "篮板效率", "抢断效率", "助攻率"};
		final Object[][] dataEff = new Object[1][headerEff.length];
		for(int i=0;i<dataToral.length;i++){

			dataEff[i][0] = po.getSeason();
			dataEff[i][1] = po.getShootEff();
			dataEff[i][2] = po.getTPEff();
			dataEff[i][3] = po.getFTEff();
			dataEff[i][4] = po.getOffEff();
			dataEff[i][5] = po.getDefEff();
			dataEff[i][6] = po.getOffBackBoardEff();
			dataEff[i][7] = po.getDefBackBoardEff();
			dataEff[i][8] = po.getBackBoardEff();
			dataEff[i][9] = po.getStealEff();
			dataEff[i][10] = po.getAssistEff();
		}
		final TableModel tableModelEff = new DefaultTableModel(dataEff, headerEff){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			public String getColumnName(int columnIndex) {
				return headerEff[columnIndex];
			}

			public int getColumnCount() {return headerEff.length;}
			public int getRowCount() { return dataEff.length; }
			public Object getValueAt(int row, int col) {
				return dataEff[row][col];
			}
			public Class<?> getColumnClass(int column) {  
				Class<?> returnValue;  
				if ((column >= 0) && (column < getColumnCount())) {  
					returnValue = getValueAt(0, column).getClass();  
				} else {  
					returnValue = Object.class;  
				}  
				return returnValue;  
			}
		};
		tableEff = new StyleTable();
		tableEff.setStyleTabelModel(tableModelEff);
		tableSetting(tableEff);
		tableEff.setSort();

		scrollPaneEff = new JScrollPane(); 
		scrollPaneEff.setBounds(14, 215, 920, 300);
		//scrollPaneEff.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPaneEff.setBorder(null);
		scrollPaneEff.setOpaque(false);
		scrollPaneEff.getViewport().setOpaque(false);
		scrollPaneEff.setViewportView(tableEff);
		scrollPaneEff.setVisible(false);
		this.add(scrollPaneEff);
		
		checkBox1 = new JCheckBox("总览");
		checkBox1.setBounds(670, 185, 70, 30);
		checkBox1.setSelected(true);
		this.add(checkBox1);
		checkBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox1.isSelected()){
					checkBox2.setSelected(false);
					checkBox3.setSelected(false);
					scrollPaneAverage.setVisible(false);
					scrollPaneEff.setVisible(false);
					scrollPaneTotal.setVisible(true);
				}else{
					checkBox1.setSelected(true);
				}
			}
		});
		
		checkBox2 = new JCheckBox("场均");
		checkBox2.setBounds(740, 185, 70, 30);
		this.add(checkBox2);
		checkBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox2.isSelected()){
					checkBox1.setSelected(false);
					checkBox3.setSelected(false);
					scrollPaneTotal.setVisible(false);
					scrollPaneEff.setVisible(false);
					scrollPaneAverage.setVisible(true);
				}else{
					checkBox2.setSelected(true);
				}
			}
		});
		
		checkBox3 = new JCheckBox("效率");
		checkBox3.setBounds(810, 185, 70, 30);
		this.add(checkBox3);
		checkBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox3.isSelected()){
					checkBox1.setSelected(false);
					checkBox2.setSelected(false);
					scrollPaneTotal.setVisible(false);
					scrollPaneAverage.setVisible(false);
					scrollPaneEff.setVisible(true);
				}else{
					checkBox3.setSelected(true);
				}
			}
		});
	}

	private void tableSetting(final JTable table){
		table.setPreferredScrollableViewportSize(new Dimension(920, 300));//设置大小
		table.setBounds(14, 215, 920, 300);
		table.getTableHeader().setPreferredSize(new Dimension(920, 30));//设置表头大小


		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer(){
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
		});

		table.addMouseMotionListener(new MouseMotionListener() {

			public void mouseMoved(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/table.getRowHeight();

				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && column == 0) {
					table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}else{
					table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}

			public void mouseDragged(MouseEvent e) {
			}
		});

		MouseAdapter mouseAdapter = new MouseAdapter() {
			private final JTable thisTable = table;

		};
		table.addMouseListener(mouseAdapter);
	}
}

