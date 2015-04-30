package presentation.team;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableCellRenderer;

import presentation.component.BgPanel;
import presentation.component.DateLabel;
import presentation.component.DatePanel;
import presentation.component.GLabel;
import presentation.component.StyleScrollPane;
import presentation.component.StyleTable;
import presentation.contenui.TurnController;
import presentation.contenui.UIUtil;
import presentation.mainui.StartUI;
import bussinesslogic.match.MatchLogic;
import data.po.MatchDataPO;
import data.po.TeamDataPO;

public class TeamMatch extends BgPanel{

	private static final long serialVersionUID = 1L;
	private static String file = "";
	private MatchLogic matchLogic;
	private DatePanel dateLabel1;
	private ArrayList<MatchDataPO> matchDataPOs;
	private TeamDataPO po;
	private StyleScrollPane scrollPane;
	private StyleTable table;
	private Rectangle rectangle;
	
	public TeamMatch(TeamDataPO po) {
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
		this.setBackground(UIUtil.bgWhite);
		this.setVisible(true);
		
		this.po = po;
		
		init();
	}
	
	private void init(){
		GLabel message = new GLabel("*单击表头可排序", new Point(34, 5), new Point(120, 30), this, true, 0, 13);

		matchLogic = new MatchLogic();
		this.po = po;
		matchDataPOs = matchLogic.GetInfo(po.getShortName());

		rectangle = new Rectangle(14, 50, 920, 460);

		//		dateLabel1 = new DatePanel(new Point(25, 9), this, Color.black, new ImageIcon("img/teamDetials/dateIcon.png"));
		//		dateLabel1.setBackground(UIUtil.bgWhite);

		matchSetting();
		repaint();
	}
	
	private void matchSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("日期");header.addElement("对手");
		header.addElement("结果");header.addElement("比分");header.addElement("比赛链接");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=matchDataPOs.size()-1;i>=0;i--){
			MatchDataPO m = matchDataPOs.get(i);
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(m.getDate());
			if(m.getFirstteam().equals(po.getShortName())){
				vector.addElement(m.getSecondteam());
			}else{
				vector.addElement(m.getFirstteam());
			}
			if(m.getWinner().equals(po.getShortName())){
				vector.addElement("胜");
			}else{
				vector.addElement("负");
			}
			vector.addElement(m.getPoints());
			vector.addElement("比赛链接");
			data.addElement(vector);
		}
		
		table = new StyleTable();
		scrollPane = new StyleScrollPane(table);
		table.tableSetting(table, header, data, scrollPane, rectangle);
		tableSetting(table);
		table.setSort();
		this.add(scrollPane);
	}
	
	private void tableSetting(final JTable table){
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
		table.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(defaultTableCellRenderer);
		
		table.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/table.getRowHeight();

				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == 1 || column ==4)) {
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
				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == 1)) {
					String name = table.getValueAt(row, 1).toString();
					StartUI.startUI.turn(turnController.turnToTeamDetials(name));
				}else{
					if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == 4)) {
						String date = table.getValueAt(row, 0).toString();
						String name = table.getValueAt(row, 1).toString();
						StartUI.startUI.turn(turnController.turnToMatchDetials(date, name));
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
