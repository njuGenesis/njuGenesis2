package presentation.contenui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.StyleTable;
import presentation.component.TeamImageAssist;
import presentation.hotspot.SelectLabel;
import data.po.PlayerDataPO;

public class PlayerDetials extends BgPanel{
	private static final long serialVersionUID = 1L;
	private GLabel title;
	private SelectLabel tdMenu[];
	private PlayerDataPO po;
	private BgPanel sonPanel;
	
	public PlayerDetials(final PlayerDataPO[] pos){
		super("");
		this.po = pos[pos.length-1];
		
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		this.setBounds(0, 0, 1000, 650);
		this.setVisible(true);
		
		title = new GLabel("  "+po.getName(), new Point(27, 30), new Point(946, 52), this, true, 0, 25);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);
		
		tdMenu = new SelectLabel[4];
		tdMenu[0] = new SelectLabel("资料", new Point(27, 83), new Point(235, 35), this, true, 0, 18);
		tdMenu[1] = new SelectLabel("数据", new Point(27+235+2, 83), new Point(235, 35), this, true, 0, 18);
		tdMenu[2] = new SelectLabel("比赛", new Point(27+(235+2)*2, 83), new Point(235, 35), this, true, 0, 18);
		tdMenu[3] = new SelectLabel("对比", new Point(27+(235+2)*3, 83), new Point(235, 35), this, true, 0, 18);
		
		tdMenu[0].setSelected(true);
		PlayerInfo info = new PlayerInfo(PlayerDetials.this.po);
		sonPanel = info;
		this.add(sonPanel);
		
		tdMenu[0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[0].setSelected(true);
				PlayerInfo info = new PlayerInfo(PlayerDetials.this.po);
				PlayerDetials.this.remove(sonPanel);
				sonPanel = info;
				PlayerDetials.this.add(sonPanel);
				repaint();
			}
		});
		tdMenu[1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[1].setSelected(true);
				PlayerData playerData = new PlayerData(pos);
				PlayerDetials.this.remove(sonPanel);
				sonPanel = playerData;
				PlayerDetials.this.add(sonPanel);
				repaint();
			}
		});
		
		tdMenu[2].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[2].setSelected(true);
//				Match match = new Match(PlayerDetials.this.po);
//				PlayerDetials.this.remove(sonPanel);
//				sonPanel = match;
//				PlayerDetials.this.add(sonPanel);
//				repaint();
			}
		});
		
		tdMenu[3].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[3].setSelected(true);
//				Data data = new Data(PlayerDetials.this.po);
//				PlayerDetials.this.remove(sonPanel);
//				sonPanel = data;
//				PlayerDetials.this.add(sonPanel);
//				repaint();
			}
		});
	}
}

class PlayerInfo extends BgPanel{
	
	private static String file = "img/playerDetials/info.png";
	private TeamImageAssist assist;
	private GLabel playerPic, teamPic, number, position, height, weight, birthday, age, exp;
	private JTextArea school;

	public PlayerInfo(PlayerDataPO po) {
		super(file);
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		
		assist = new TeamImageAssist();
		
		playerPic = new GLabel("img/action/"+po.getName()+".png", new Point(276, 2), new Point(330, 525), this, true);
		teamPic = new GLabel(assist.loadImageIcon("img/teams/"+po.getTeamName()+".svg", 150, 150), new Point(47, 42), new Point(150, 150), this, true);
		number = new GLabel(po.getNumber(), new Point(163, 408), new Point(200, 25), this, true, 0, 18);
		position = new GLabel(TableUtility.getChPosition(po.getPosition())+" "+po.getPosition(), new Point(160, 438), new Point(200, 25), this, true, 0, 18);
		height = new GLabel(po.getHeight(), new Point(718, 147), new Point(200, 25), this, true, 0, 18);
		weight = new GLabel(String.valueOf(po.getWeight()), new Point(718, 180), new Point(200, 25), this, true, 0, 18);
		birthday = new GLabel(po.getBirth(), new Point(718, 213), new Point(200, 25), this, true, 0, 18);
		age = new GLabel(String.valueOf(po.getAge()), new Point(718, 244), new Point(200, 25), this, true, 0, 18);
		exp = new GLabel(String.valueOf(po.getExp()), new Point(718, 275), new Point(200, 25), this, true, 0, 18);
		school = new JTextArea();
		school.setEditable(false);
		school.setLineWrap(true);
		school.setWrapStyleWord(true);
		school.setBounds(747, 308, 200, 50);
		school.setText(po.getSchool());
		school.setFont(new Font("微软雅黑", 0, 18));
		school.setOpaque(false);
		this.add(school);
	}
}

class PlayerData extends BgPanel{
	
	private PlayerDataPO[] pos;
	private StyleTable basicTable, pgTable, effTable;
	private JScrollPane basicSP, pgSP, effSP;

	public PlayerData(PlayerDataPO[] pos) {
		super("");
		this.pos = pos;
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(UIUtil.bgWhite);
		
		basicSetting();
	}
	
	private void basicSetting(){
		
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");
		header.addElement("上场数");header.addElement("先发场数");header.addElement("上场总时间");header.addElement("总得分");header.addElement("两双数");
		header.addElement("进攻篮板数");header.addElement("防守篮板数");header.addElement("总篮板数");header.addElement("总助攻数");header.addElement("总投篮数");
		header.addElement("总命中数");header.addElement("总三分数");header.addElement("总三分命中数");header.addElement("总罚球数");header.addElement("罚球命中数");
		header.addElement("进攻数");header.addElement("防守数");header.addElement("抢断数");header.addElement("盖帽数");header.addElement("失误数");
		header.addElement("犯规数");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(pos[pos.length-1-i].getSeason());
			vector.addElement(pos[pos.length-1-i].getGP());
			vector.addElement(pos[pos.length-1-i].getGS());
			vector.addElement(pos[pos.length-1-i].getMinutesOnField());
			vector.addElement(pos[pos.length-1-i].getPTS());
			vector.addElement(pos[pos.length-1-i].getDouble());
			vector.addElement(pos[pos.length-1-i].getOffb());
			vector.addElement(pos[pos.length-1-i].getDefb());
			vector.addElement(pos[pos.length-1-i].getBackboard());
			vector.addElement(pos[pos.length-1-i].getAssist());
			vector.addElement(pos[pos.length-1-i].getTotalFieldGoal());
			vector.addElement(pos[pos.length-1-i].getFieldGoal());
			vector.addElement(pos[pos.length-1-i].getTotalThreeGoal());
			vector.addElement(pos[pos.length-1-i].getThreeGoal());
			vector.addElement(pos[pos.length-1-i].getTotalFT());
			vector.addElement(pos[pos.length-1-i].getFT());
			vector.addElement(pos[pos.length-1-i].getOff());
			vector.addElement(pos[pos.length-1-i].getDef());
			vector.addElement(pos[pos.length-1-i].getSteal());
			vector.addElement(pos[pos.length-1-i].getRejection());
			vector.addElement(pos[pos.length-1-i].getTo());
			vector.addElement(pos[pos.length-1-i].getFoul());
			data.addElement(vector);
		}
		
		basicTable = new StyleTable();
		basicSP = new JScrollPane(); 
		tableSetting(basicTable, header, data, basicSP);
		basicTable.setSort();
	}
	
	private void pgSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");header.addElement("场均上场时间");header.addElement("场均得分");
		header.addElement("场均篮板数");header.addElement("场均助攻数");header.addElement("场均进攻数");header.addElement("场均防守数");header.addElement("场均抢断数");
		header.addElement("场均盖帽数");header.addElement("场均失误数");header.addElement("场均犯规数");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(pos[pos.length-1-i].getSeason());
			vector.addElement(pos[pos.length-1-i].getMPG());
			vector.addElement(pos[pos.length-1-i].getPPG());
			vector.addElement(pos[pos.length-1-i].getBPG());
			vector.addElement(pos[pos.length-1-i].getAPG());
			vector.addElement(pos[pos.length-1-i].getOffPG());
			vector.addElement(pos[pos.length-1-i].getDefPG());
			vector.addElement(pos[pos.length-1-i].getStealPG());
			vector.addElement(pos[pos.length-1-i].getRPG());
			vector.addElement(pos[pos.length-1-i].getToPG());
			vector.addElement(pos[pos.length-1-i].getFoulPG());
			data.addElement(vector);
		}
		
		pgTable = new StyleTable();
		pgSP = new JScrollPane(); 
		tableSetting(pgTable, header, data, pgSP);
		pgTable.setSort();
	}
	
	private void effSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");header.addElement("效率");header.addElement("GMSC效率");header.addElement("使用率");header.addElement("真实命中率");
		header.addElement("投篮命中率");header.addElement("三分命中率");header.addElement("罚篮命中率");header.addElement("投篮效率");header.addElement("篮板效率");
		header.addElement("进攻篮板效率");header.addElement("防守篮板效率");header.addElement("助攻效率");header.addElement("抢断效率");header.addElement("盖帽效率");
		header.addElement("失误率");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(pos[pos.length-1-i].getSeason());
			vector.addElement(pos[pos.length-1-i].getEff());
			vector.addElement(pos[pos.length-1-i].getGmsc());
			vector.addElement(pos[pos.length-1-i].getUseEff());
			vector.addElement(pos[pos.length-1-i].getTruePercentage());
			vector.addElement(pos[pos.length-1-i].getFieldGoalPercentage());
			vector.addElement(pos[pos.length-1-i].getThreePGPercentage());
			vector.addElement(pos[pos.length-1-i].getFTPercentage());
			vector.addElement(pos[pos.length-1-i].getShootEff());
			vector.addElement(pos[pos.length-1-i].getBackboardEff());
			vector.addElement(pos[pos.length-1-i].getOffBEff());
			vector.addElement(pos[pos.length-1-i].getDefBEff());
			vector.addElement(pos[pos.length-1-i].getAssitEff());
			vector.addElement(pos[pos.length-1-i].getStealEff());
			vector.addElement(pos[pos.length-1-i].getRejectionEff());
			vector.addElement(pos[pos.length-1-i].getToEff());
			data.addElement(vector);
		}
		
		effTable = new StyleTable();
		effSP = new JScrollPane(); 
		tableSetting(effTable, header, data, effSP);
		effTable.setSort();
	}
	
	private void tableSetting(final StyleTable table, final Vector<String> header, final Vector<Vector<Object>> data, JScrollPane scrollPane){
		DefaultTableModel tableModel = new DefaultTableModel(data, header){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
		        return false;
		    }
		    public String getColumnName(int columnIndex) {
		        return header.get(columnIndex);
		    }
		 
		    public int getColumnCount() {return header.size();}
		    public int getRowCount() { return data.size(); }
		    public Object getValueAt(int row, int col) {
		    	return data.get(row).get(col);
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
		
		table.setPreferredScrollableViewportSize(new Dimension(920, 440));//设置大小
		table.setBounds(14, 20, 920, 480);
		table.getTableHeader().setPreferredSize(new Dimension(920, 30));//设置表头大小
		table.setStyleTabelModel(tableModel);
		
		scrollPane.setBounds(14, 20, 920, 440);
		//basicSP.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);
		this.add(scrollPane);
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/table.getRowHeight();

				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == 0)) {
				}else{
				}
			}
		};
		table.addMouseListener(mouseAdapter);
	}
}

