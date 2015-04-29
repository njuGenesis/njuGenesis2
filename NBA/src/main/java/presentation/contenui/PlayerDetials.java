package presentation.contenui;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import bussinesslogic.match.MatchLogic;
import bussinesslogic.player.PlayerLogic;
import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.StyleScrollPane;
import presentation.component.StyleTable;
import presentation.component.TeamImageAssist;
import presentation.hotspot.SelectLabel;
import data.po.MatchDataPO;
import data.po.Match_PlayerPO;
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
				PlayerMatch playerMatch = new PlayerMatch(pos);
				PlayerDetials.this.remove(sonPanel);
				sonPanel = playerMatch;
				PlayerDetials.this.add(sonPanel);
				repaint();
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
	private StyleTable basicTable,totalTable,  pgTable, effTable;
	private StyleScrollPane basicSP,totalSP, pgSP, effSP;
	private JCheckBox checkBox1, checkBox2, checkBox3, checkBox4;
	private Rectangle rectangle;

	public PlayerData(PlayerDataPO[] pos) {
		super("");
		this.pos = pos;
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(UIUtil.bgWhite);
		
		rectangle = new Rectangle(14, 164, 920, 380);
		
		basicSetting();
		totalSetting();
		pgSetting();
		effSetting();
		
		basicSP.setVisible(true);
		
		GLabel palyerPic = new GLabel("img/portrait/"+pos[pos.length-1].getName()+".png", new Point(50, 30), new Point(120, 97), this, true);
		GLabel ppg = new GLabel("场均得分:"+pos[pos.length-1].getPPG(), new Point(260, 25), new Point(200, 30), this, true, 0, 20);
		GLabel bpg = new GLabel("场均篮板:"+pos[pos.length-1].getBPG(), new Point(300, 60), new Point(200, 30), this, true, 0, 20);
		GLabel apg = new GLabel("场均助攻:"+pos[pos.length-1].getAPG(), new Point(340, 95), new Point(200, 30), this, true, 0, 20);
		
		checkBox1 = new JCheckBox("总览");
		checkBox1.setBounds(600, 115, 70, 30);
		checkBox1.setSelected(true);
		this.add(checkBox1);
		checkBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox1.isSelected()){
					checkBox2.setSelected(false);
					checkBox3.setSelected(false);
					checkBox4.setSelected(false);
					pgSP.setVisible(false);
					effSP.setVisible(false);
					totalSP.setVisible(false);
					basicSP.setVisible(true);
				}else{
					checkBox1.setSelected(true);
				}
			}
		});
		
		checkBox2 = new JCheckBox("总计");
		checkBox2.setBounds(670, 115, 70, 30);
		this.add(checkBox2);
		checkBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox2.isSelected()){
					checkBox1.setSelected(false);
					checkBox3.setSelected(false);
					checkBox4.setSelected(false);
					pgSP.setVisible(false);
					effSP.setVisible(false);
					basicSP.setVisible(false);
					totalSP.setVisible(true);
				}else{
					checkBox2.setSelected(true);
				}
			}
		});
		
		checkBox3 = new JCheckBox("场均");
		checkBox3.setBounds(740, 115, 70, 30);
		this.add(checkBox3);
		checkBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox3.isSelected()){
					checkBox1.setSelected(false);
					checkBox2.setSelected(false);
					checkBox4.setSelected(false);
					basicSP.setVisible(false);
					effSP.setVisible(false);
					totalSP.setVisible(false);
					pgSP.setVisible(true);
				}else{
					checkBox3.setSelected(true);
				}
			}
		});
		
		checkBox4 = new JCheckBox("效率");
		checkBox4.setBounds(810, 115, 70, 30);
		this.add(checkBox4);
		checkBox4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox4.isSelected()){
					checkBox1.setSelected(false);
					checkBox2.setSelected(false);
					checkBox3.setSelected(false);
					basicSP.setVisible(false);
					pgSP.setVisible(false);
					totalSP.setVisible(false);
					effSP.setVisible(true);
				}else{
					checkBox4.setSelected(true);
				}
			}
		});
	}
	
	private void totalSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");
		header.addElement("进攻篮板数");header.addElement("防守篮板数");header.addElement("总篮板数");header.addElement("总助攻数");header.addElement("总投篮数");
		header.addElement("总命中数");header.addElement("总三分数");header.addElement("总三分命中数");header.addElement("总罚球数");header.addElement("罚球命中数");
		header.addElement("进攻数");header.addElement("防守数");header.addElement("抢断数");header.addElement("盖帽数");header.addElement("失误数");
		header.addElement("犯规数");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(pos[pos.length-1-i].getSeason());
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
		totalTable = new StyleTable();
		totalSP = new StyleScrollPane(totalTable);
		totalTable.tableSetting(totalTable, header, data, totalSP,  rectangle);
		totalTable.setSort();
		totalSP.setVisible(false);
		this.add(totalSP);
	}
	
	private void basicSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");
		header.addElement("上场数");header.addElement("先发场数");header.addElement("上场总时间");header.addElement("总得分");header.addElement("两双数");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(pos[pos.length-1-i].getSeason());
			vector.addElement(pos[pos.length-1-i].getGP());
			vector.addElement(pos[pos.length-1-i].getGS());
			vector.addElement(pos[pos.length-1-i].getMinutesOnField());
			vector.addElement(pos[pos.length-1-i].getPTS());
			vector.addElement(pos[pos.length-1-i].getDouble());
			data.addElement(vector);
		}
		
		basicTable = new StyleTable();
		basicSP = new StyleScrollPane(basicTable);
		basicTable.tableSetting(basicTable, header, data, basicSP, rectangle);
		basicTable.setSort();
		basicSP.setVisible(false);
		this.add(basicSP);
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
		pgSP = new StyleScrollPane(pgTable); 
		pgTable.tableSetting(pgTable, header, data, pgSP,  rectangle);
		pgTable.setSort();
		pgSP.setVisible(false);
		this.add(pgSP);
	}
	
	private void effSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");header.addElement("效率       ");header.addElement("GMSC");header.addElement("使用率");header.addElement("真实命中率");
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
		effSP = new StyleScrollPane(effTable); 
		effTable.tableSetting(effTable, header, data, effSP, rectangle);
		effTable.setSort();
		effSP.setVisible(false);
		this.add(effSP);
	}
	
}

class PlayerMatch extends BgPanel{
	
	private static final long serialVersionUID = 1L;
	private PlayerDataPO[] pos;
	private StyleTable basicTable, pgTable;
	private StyleScrollPane basicSP, pgSP;
	private JCheckBox checkBox1, checkBox2;
	private MatchLogic matchLogic = new MatchLogic();
	private ArrayList<Match_PlayerPO> match_PlayerPOs = new ArrayList<Match_PlayerPO>();
	private ArrayList<ArrayList<MatchDataPO>> matchDataPOs = new ArrayList<ArrayList<MatchDataPO>>();
	private Rectangle rectangle;

	public PlayerMatch(PlayerDataPO[] pos) {
		super("");
		this.pos = pos;
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(UIUtil.bgWhite);
		
		rectangle = new Rectangle(14, 64, 920, 480);
		
		for(int i=pos.length-1;i>=0;i--){
			matchDataPOs.add(matchLogic.GetPlayerInfo(pos[i].getName(), pos[i].getSeason()));
		}
		
		for(int i=0;i<matchDataPOs.size();i++){
			for(int k=matchDataPOs.get(i).size()-1;k>=0;k--){
				ArrayList<Match_PlayerPO> players1 = matchDataPOs.get(i).get(k).getPlayers1();
				for(int m=0;m<players1.size();m++){
					if(players1.get(m).getPlayername().equals(pos[i].getName())){
						match_PlayerPOs.add(players1.get(m));
						break;
					}
				}
				ArrayList<Match_PlayerPO> players2 = matchDataPOs.get(i).get(k).getPlayers2();
				for(int m=0;m<players2.size();m++){
					if(players2.get(m).getPlayername().equals(pos[i].getName())){
						match_PlayerPOs.add(players2.get(m));
						break;
					}
				}
			}
		}
		
		basicSetting();
		pgSetting();
		
		basicSP.setVisible(true);
		
		checkBox1 = new JCheckBox("总览");
		checkBox1.setBounds(740, 15, 70, 30);
		checkBox1.setSelected(true);
		this.add(checkBox1);
		checkBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox1.isSelected()){
					checkBox2.setSelected(false);
					pgSP.setVisible(false);
					basicSP.setVisible(true);
				}else{
					checkBox1.setSelected(true);
				}
			}
		});
		
		checkBox2 = new JCheckBox("详细");
		checkBox2.setBounds(810, 15, 70, 30);
		this.add(checkBox2);
		checkBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox2.isSelected()){
					checkBox1.setSelected(false);
					basicSP.setVisible(false);
					pgSP.setVisible(true);
				}else{
					checkBox2.setSelected(true);
				}
			}
		});
	}
	
	private double ShortDouble(double d){
		DecimalFormat df = new DecimalFormat(".00");
		return Double.parseDouble(df.format(d));
	}
	
	private void basicSetting(){
		
		final Vector<String> header = new Vector<String>();
		header.addElement("日期");header.addElement("对手");
		header.addElement("位置");header.addElement("在场时间");header.addElement("得分");
		header.addElement("篮板");header.addElement("三分命中率");header.addElement("罚球命中率");header.addElement("助攻数");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(match_PlayerPOs.get(i).getData());
			vector.addElement(match_PlayerPOs.get(i).getOtherTeam());
			vector.addElement(match_PlayerPOs.get(i).getState());
			vector.addElement(match_PlayerPOs.get(i).getTime());
			vector.addElement((int)match_PlayerPOs.get(i).getPoints());
			vector.addElement((int)match_PlayerPOs.get(i).getBank());
			vector.addElement(ShortDouble(match_PlayerPOs.get(i).getTPShootEff()));
			vector.addElement(ShortDouble(match_PlayerPOs.get(i).getFTShootEff()));
			vector.addElement((int)match_PlayerPOs.get(i).getAss());
			data.addElement(vector);
		}
		
		basicTable = new StyleTable();
		basicSP = new StyleScrollPane(basicTable); 
		basicTable.tableSetting(basicTable, header, data, basicSP, rectangle);
		basicTable.setSort();
		basicSP.setVisible(false);
		this.add(basicSP);
	}
	
	private void pgSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("日期");header.addElement("对手");
		header.addElement("进攻篮板");header.addElement("防守篮板");
		header.addElement("罚球数");header.addElement("失误");header.addElement("投球数");
		header.addElement("投篮命中数");header.addElement("投篮命中率");header.addElement("三分投篮数");
		header.addElement("三分命中数");header.addElement("罚篮命中数");header.addElement("抢断数");header.addElement("盖帽数");
		header.addElement("犯规数");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(match_PlayerPOs.get(i).getData());
			vector.addElement(match_PlayerPOs.get(i).getOtherTeam());
			vector.addElement((int)match_PlayerPOs.get(i).getBankOff());
			vector.addElement((int)match_PlayerPOs.get(i).getBankDef());
			vector.addElement((int)match_PlayerPOs.get(i).getFT());
			vector.addElement((int)match_PlayerPOs.get(i).getTo());
			vector.addElement((int)match_PlayerPOs.get(i).getShoot());
			vector.addElement((int)match_PlayerPOs.get(i).getShootEffNumber());
			vector.addElement(ShortDouble(match_PlayerPOs.get(i).getShootEff()));
			vector.addElement((int)match_PlayerPOs.get(i).getTPShoot());
			vector.addElement((int)match_PlayerPOs.get(i).getTPShootEffNumber());
			vector.addElement((int)match_PlayerPOs.get(i).getFTShootEffNumber());
			vector.addElement((int)match_PlayerPOs.get(i).getSteal());
			vector.addElement((int)match_PlayerPOs.get(i).getRejection());
			vector.addElement((int)match_PlayerPOs.get(i).getFoul());
			data.addElement(vector);
		}
		
		pgTable = new StyleTable();
		pgSP = new StyleScrollPane(pgTable); 
		pgTable.tableSetting(pgTable, header, data, pgSP, rectangle);
		pgTable.setSort();
		pgSP.setVisible(false);
		this.add(pgSP);
	}
}

class PlayerCmp extends BgPanel{
	
	public PlayerCmp(){
		super("");
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(UIUtil.bgWhite);
		
		
	}
}

