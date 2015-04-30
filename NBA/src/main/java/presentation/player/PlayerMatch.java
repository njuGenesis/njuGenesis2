package presentation.player;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableCellRenderer;

import presentation.component.BgPanel;
import presentation.component.StyleScrollPane;
import presentation.component.StyleTable;
import presentation.contenui.TurnController;
import presentation.contenui.UIUtil;
import presentation.mainui.StartUI;
import bussinesslogic.match.MatchLogic;
import data.po.MatchDataPO;
import data.po.Match_PlayerPO;
import data.po.PlayerDataPO;

public class PlayerMatch extends BgPanel{
	
	private static final long serialVersionUID = 1L;
	private PlayerDataPO po;
	private StyleTable basicTable, pgTable;
	private StyleScrollPane basicSP, pgSP;
	private JCheckBox checkBox1, checkBox2;
	private MatchLogic matchLogic = new MatchLogic();
	private ArrayList<Match_PlayerPO> match_PlayerPOs = new ArrayList<Match_PlayerPO>();
	private ArrayList<ArrayList<MatchDataPO>> matchDataPOs = new ArrayList<ArrayList<MatchDataPO>>();
	private Rectangle rectangle;

	public PlayerMatch(PlayerDataPO po) {
		super("");
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		this.po = po;
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(UIUtil.bgWhite);
		
		init();
		
	}
	
	private void init(){
rectangle = new Rectangle(14, 40, 920, 480);
		
		match_PlayerPOs = matchLogic.GetPlayerInfo(po.getName());
		
		basicSetting();
		pgSetting();
		
		basicSP.setVisible(true);
		
		checkBox1 = new JCheckBox("总览");
		checkBox1.setBounds(740, 5, 70, 30);
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
		checkBox2.setBounds(810, 5, 70, 30);
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
		header.addElement("篮板");header.addElement("三分命中率");header.addElement("罚球命中率");header.addElement("助攻数");header.addElement("比赛链接");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=match_PlayerPOs.size()-1;i>=0;i--){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(match_PlayerPOs.get(i).getData());
			vector.addElement(match_PlayerPOs.get(i).getOtherTeam());
			if(match_PlayerPOs.get(i).getState().equals("")){
				vector.addElement("非首发");
			}else{
				vector.addElement(match_PlayerPOs.get(i).getState());
			}
			vector.addElement(match_PlayerPOs.get(i).getTime());
			vector.addElement((int)match_PlayerPOs.get(i).getPoints());
			vector.addElement((int)match_PlayerPOs.get(i).getBank());
			vector.addElement(ShortDouble(match_PlayerPOs.get(i).getTPShootEff()));
			vector.addElement(ShortDouble(match_PlayerPOs.get(i).getFTShootEff()));
			vector.addElement((int)match_PlayerPOs.get(i).getAss());
			vector.addElement("比赛链接");
			data.addElement(vector);
		}
		
		basicTable = new StyleTable();
		basicSP = new StyleScrollPane(basicTable); 
		basicTable.tableSetting(basicTable, header, data, basicSP, rectangle);
		tableSetting(basicTable);
		basicTable.setSort();
		basicSP.setVisible(false);
		this.add(basicSP);
	}
	
	private void pgSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("日期");header.addElement("对手");
		header.addElement("进攻篮板");header.addElement("防守篮板");
		header.addElement("罚球");header.addElement("失误");header.addElement("投球");
		header.addElement("投篮命中");header.addElement("投篮命中率");header.addElement("三分投篮");
		header.addElement("三分命中");header.addElement("罚篮命中");header.addElement("抢断");header.addElement("盖帽");
		header.addElement("犯规");header.addElement("比赛链接");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=match_PlayerPOs.size()-1;i>=0;i--){
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
			vector.addElement("比赛链接");
			data.addElement(vector);
		}
		
		pgTable = new StyleTable();
		pgSP = new StyleScrollPane(pgTable); 
		pgTable.tableSetting(pgTable, header, data, pgSP, rectangle);
		tableSetting(pgTable);
		pgTable.setSort();
		pgSP.setVisible(false);
		this.add(pgSP);
	}
	
	private void tableSetting(final StyleTable table){
		table.setPreferredScrollableViewportSize(new Dimension(920, 480));//设置大小
		table.setBounds(14, 35, 920, 480);
		table.getTableHeader().setPreferredSize(new Dimension(920, 30));//设置表头大小
		
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
		table.getColumnModel().getColumn(table.getColumnCount()-1).setCellRenderer(defaultTableCellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
		
		table.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/table.getRowHeight();

				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == table.getColumnCount()-1 || column == 1)) {
					table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}else{
					table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
			
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/table.getRowHeight();

				TurnController turnController = new TurnController();
				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == table.getColumnCount()-1)) {
					String date = table.getValueAt(row, 0).toString();
					String team = table.getValueAt(row, 1).toString();
					StartUI.startUI.turn(turnController.turnToMatchDetials(date, team));
				}else{
					if(row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == 1)){
						String team = table.getValueAt(row, 1).toString();
						StartUI.startUI.turn(turnController.turnToTeamDetials(team));
					}
				}
			}
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
