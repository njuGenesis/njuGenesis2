package presentation.team;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableCellRenderer;

import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.StyleScrollPane;
import presentation.component.StyleTable;
import presentation.contenui.TurnController;
import presentation.contenui.UIUtil;
import presentation.mainui.StartUI;
import bussinesslogic.player.PlayerLogic;
import data.po.PlayerDataPO;
import data.po.TeamDataPO;

public class TeamPlayer extends BgPanel{

	private static final long serialVersionUID = 1L;
	private static String file = "";
	private PlayerLogic playerLogic;
	private JCheckBox checkBox1, checkBox2;
	private StyleTable tableBasic, tableDetials;
	private StyleScrollPane scrollPaneBasic, scrollPaneDetials;
	private PlayerDataPO[] playerDataPOs;
	private Rectangle rectangle;
	private TeamDataPO po;
	
	public TeamPlayer(TeamDataPO po) {
		super(file);
		
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
		this.setVisible(true);
		this.setBackground(UIUtil.bgWhite);
		this.po = po;

		init();

	}

	private void init(){
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
			public void mousePressed(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/table.getRowHeight();

				TurnController turnController = new TurnController();
				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == 0)) {
					String name = table.getValueAt(row, 0).toString();
					StartUI.startUI.turn(turnController.turnToPlayerDetials(name));
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
