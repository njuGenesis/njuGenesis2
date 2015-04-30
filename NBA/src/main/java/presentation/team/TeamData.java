package presentation.team;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.StyleScrollPane;
import presentation.component.StyleTable;
import presentation.component.TeamImageAssist;
import presentation.contenui.UIUtil;
import bussinesslogic.team.TeamLogic;
import data.po.TeamDataPO;

public class TeamData extends BgPanel{
	
	private static final long serialVersionUID = 1L;
	private StyleTable tableTotal, tableAverage, tableEff, tableBasic;
	private StyleScrollPane scrollPaneTotal, scrollPaneAverage, scrollPaneEff, scrollPaneBasic;
	private TeamImageAssist assist;
	private JCheckBox checkBox1, checkBox2, checkBox3, checkBox4;
	private ArrayList<TeamDataPO> teamDataPOs;
	private TeamLogic teamLogic = new TeamLogic();
	private Rectangle rectangle;
	private TeamDataPO po;
	
	public TeamData(TeamDataPO po){
		super("");
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		this.setVisible(true);
		this.po = po;

		init();
	}
	private void init(){
		GLabel message = new GLabel("*单击表头可排序", new Point(34, 187), new Point(120, 30), this, true, 0, 13);

		assist = new TeamImageAssist();
		rectangle = new Rectangle(14, 215, 920, 300);

		teamDataPOs = teamLogic.GetInfo(po.getShortName());

		GLabel teamPic = new GLabel(assist.loadImageIcon("img/teams/"+po.getShortName()+".svg", 200, 200), new Point(75, 0), new Point(200, 200), this, true);
		GLabel wr = new GLabel("胜率:"+String.valueOf(po.getWR()), new Point(460, 120), new Point(200, 30), this, true, 0, 24);
		GLabel winNumber = new GLabel("胜:"+String.valueOf((int)po.getWinMatch()), new Point(380, 40), new Point(200, 30), this, true, 0, 24);
		GLabel failNumber = new GLabel("负:"+String.valueOf((int)(po.getMatchNumber()-po.getWinMatch())), new Point(420, 80), new Point(200, 30), this, true, 0, 24);

		basicSetting();
		totalSetting();
		pgSetting();
		effSetting();
		
		scrollPaneBasic.setVisible(true);
		
		checkBox1 = new JCheckBox("总览");
		checkBox1.setBounds(600, 180, 70, 30);
		checkBox1.setSelected(true);
		this.add(checkBox1);
		checkBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox1.isSelected()){
					checkBox2.setSelected(false);
					checkBox3.setSelected(false);
					checkBox4.setSelected(false);
					scrollPaneAverage.setVisible(false);
					scrollPaneEff.setVisible(false);
					scrollPaneTotal.setVisible(false);
					scrollPaneBasic.setVisible(true);
				}else{
					checkBox1.setSelected(true);
				}
			}
		});
		
		checkBox2 = new JCheckBox("总计");
		checkBox2.setBounds(670, 180, 70, 30);
		this.add(checkBox2);
		checkBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox2.isSelected()){
					checkBox1.setSelected(false);
					checkBox3.setSelected(false);
					checkBox4.setSelected(false);
					scrollPaneAverage.setVisible(false);
					scrollPaneBasic.setVisible(false);
					scrollPaneEff.setVisible(false);
					scrollPaneTotal.setVisible(true);
				}else{
					checkBox2.setSelected(true);
				}
			}
		});
		
		checkBox3 = new JCheckBox("场均");
		checkBox3.setBounds(740, 180, 70, 30);
		this.add(checkBox3);
		checkBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox3.isSelected()){
					checkBox1.setSelected(false);
					checkBox2.setSelected(false);
					checkBox4.setSelected(false);
					scrollPaneBasic.setVisible(false);
					scrollPaneEff.setVisible(false);
					scrollPaneTotal.setVisible(false);
					scrollPaneAverage.setVisible(true);
				}else{
					checkBox3.setSelected(true);
				}
			}
		});
		
		checkBox4 = new JCheckBox("效率");
		checkBox4.setBounds(810, 180, 70, 30);
		this.add(checkBox4);
		checkBox4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox4.isSelected()){
					checkBox1.setSelected(false);
					checkBox2.setSelected(false);
					checkBox3.setSelected(false);
					scrollPaneAverage.setVisible(false);
					scrollPaneBasic.setVisible(false);
					scrollPaneTotal.setVisible(false);
					scrollPaneEff.setVisible(true);
				}else{
					checkBox4.setSelected(true);
				}
			}
		});
	}
	
	private void basicSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季   ");header.addElement("胜率");
		header.addElement("场数");header.addElement("胜场");header.addElement("负场");
		header.addElement("总得分");header.addElement("总失分");
		header.addElement("场均得分");header.addElement("场均失分");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=teamDataPOs.size()-1;i>=0;i--){
			TeamDataPO p = teamDataPOs.get(i);
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(p.getSeason());
			vector.addElement(p.getWR());
			vector.addElement((int)p.getMatchNumber());
			vector.addElement((int)p.getWinMatch());
			vector.addElement((int)(p.getMatchNumber()-p.getWinMatch()));
			vector.addElement(p.getPTS());
			vector.addElement(p.getLPS());
			vector.addElement(p.getPPG());
			vector.addElement(p.getLPG());
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
	
	private void totalSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季   ");header.addElement("投篮");
		header.addElement("投篮命中");header.addElement("三分");
		header.addElement("三分命中");header.addElement("罚球");header.addElement("罚球命中");header.addElement("进攻篮板");header.addElement("防守篮板");
		header.addElement("篮板");
		header.addElement("助攻");
		header.addElement("抢断");header.addElement("盖帽");header.addElement("失误");header.addElement("犯规");header.addElement("进攻回合");
		header.addElement("防守回合");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=teamDataPOs.size()-1;i>=0;i--){
			TeamDataPO p = teamDataPOs.get(i);
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(p.getSeason());
			vector.addElement(p.getShootNumber());
			vector.addElement(p.getShootEffNumber());
			vector.addElement(p.getTPNumber());
			vector.addElement(p.getTPEffNumber());
			vector.addElement(p.getFTNumber());
			vector.addElement(p.getFTEffNumber());
			vector.addElement(p.getOffBackBoard());
			vector.addElement(p.getDefBackBoard());
			vector.addElement(p.getBackBoard());
			vector.addElement(p.getAssitNumber());
			vector.addElement(p.getStealNumber());
			vector.addElement(p.getRejection());
			vector.addElement(p.getTo());
			vector.addElement(p.getFoul());
			vector.addElement(p.getOff());
			vector.addElement(p.getDef());
			data.addElement(vector);
		}
		
		tableTotal = new StyleTable();
		scrollPaneTotal = new StyleScrollPane(tableTotal);
		tableTotal.tableSetting(tableTotal, header, data, scrollPaneTotal, rectangle);
		tableSetting(tableTotal);
		tableTotal.setSort();
		scrollPaneTotal.setVisible(false);
		this.add(scrollPaneTotal);
	}
	
	private void pgSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季   ");header.addElement("投篮");
		header.addElement("投篮命中");header.addElement("三分");
		header.addElement("三分命中");header.addElement("罚球");header.addElement("罚球命中");header.addElement("进攻篮板");
		header.addElement("防守篮板");header.addElement("篮板数");header.addElement("助攻");
		header.addElement("抢断");header.addElement("盖帽");header.addElement("失误");header.addElement("犯规");
		header.addElement("进攻回合");header.addElement("防守回合");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=teamDataPOs.size()-1;i>=0;i--){
			TeamDataPO p = teamDataPOs.get(i);
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(p.getSeason());
			vector.addElement(p.getShootNumberPG());
			vector.addElement(p.getShootEffNumberPG());
			vector.addElement(p.getTPNumberPG());
			vector.addElement(p.getTPEffNumberPG());
			vector.addElement(p.getFTNumberPG());
			vector.addElement(p.getFTEffNumberPG());
			vector.addElement(p.getOffBackBoardPG());
			vector.addElement(p.getDefBackBoardPG());
			vector.addElement(p.getBackBoardPG());
			vector.addElement(p.getAssitNumberPG());
			vector.addElement(p.getStealNumberPG());
			vector.addElement(p.getRejectionPG());
			vector.addElement(p.getToPG());
			vector.addElement(p.getFoulPG());
			vector.addElement(p.getOffPG());
			vector.addElement(p.getDefPG());
			data.addElement(vector);
		}
		
		tableAverage = new StyleTable();
		scrollPaneAverage = new StyleScrollPane(tableAverage);
		tableAverage.tableSetting(tableAverage, header, data, scrollPaneAverage, rectangle);
		tableSetting(tableAverage);
		tableAverage.setSort();
		scrollPaneAverage.setVisible(false);
		this.add(scrollPaneAverage);
	}
	
	private void effSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季   ");header.addElement("投篮命中率");
		header.addElement("三分命中率");header.addElement("罚球命中率");
		header.addElement("进攻效率");header.addElement("防守效率");header.addElement("进攻篮板效率");header.addElement("防守篮板效率");
		header.addElement("篮板效率");header.addElement("抢断效率");header.addElement("助攻率");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=teamDataPOs.size()-1;i>=0;i--){
			TeamDataPO p = teamDataPOs.get(i);
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(p.getSeason());
			vector.addElement(p.getShootEff());
			vector.addElement(p.getTPEff());
			vector.addElement(p.getFTEff());
			vector.addElement(p.getOffEff());
			vector.addElement(p.getDefEff());
			vector.addElement(p.getOffBackBoardEff());
			vector.addElement(p.getDefBackBoardEff());
			vector.addElement(p.getBackBoardEff());
			vector.addElement(p.getStealEff());
			vector.addElement(p.getAssistEff());
			data.addElement(vector);
		}
		
		tableEff = new StyleTable();
		scrollPaneEff = new StyleScrollPane(tableEff);
		tableEff.tableSetting(tableEff, header, data, scrollPaneEff, rectangle);
		tableSetting(tableEff);
		tableEff.setSort();
		scrollPaneEff.setVisible(false);
		this.add(scrollPaneEff);
	}

	private void tableSetting(final JTable table){
		table.setPreferredScrollableViewportSize(new Dimension(920, 300));//设置大小
		table.setBounds(14, 215, 920, 300);
		table.getTableHeader().setPreferredSize(new Dimension(920, 30));//设置表头大小


		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		MouseAdapter mouseAdapter = new MouseAdapter() {
			private final JTable thisTable = table;

		};
		table.addMouseListener(mouseAdapter);
	}
	@Override
	public void refreshUI(){
		if(this!=null){
			this.removeAll();
			this.init();
		}
	}
}
