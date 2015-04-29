package presentation.contenui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import bussinesslogic.player.PlayerLogic;
import bussinesslogic.team.TeamLogic;
import data.po.PlayerDataPO;
import data.po.TeamDataPO;
import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.StyleTable;
import presentation.hotspot.SelectLabel;
import presentation.mainui.StartUI;

public class PlayerUI extends BgPanel{
	
	private static final long serialVersionUID = 1L;
	private static String file = "";
	private GLabel title, chooser;
	private SelectLabel letter[];
	private PlayerLogic playerLogic;
	private TeamLogic teamLogic;
	private StyleTable table;
	private JScrollPane scrollPane;
	private JComboBox<String> comboBoxTeam, comboBoxPosition;
	private JTextField search;
	private JCheckBox checkBox1, checkBox2;
	private Vector<String> header;
	private Vector<Vector<Object>> data;
	private TableModel tableModel;
	private PlayerDataPO[] playList;
	private TurnController turnController;

	public PlayerUI() {
		super(file);
		
		this.setSize(1000, 650);
		this.setLocation(15, 50);
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		
		turnController = new TurnController();
		playerLogic = new PlayerLogic();
		teamLogic = new TeamLogic();
		
		playList = playerLogic.getAllInfo("13-14");
		
		header = new Vector<String>();
		data = new Vector<Vector<Object>>();
		
		title = new GLabel("   比赛",new Point(30,30),new Point(940,52),this,true,0,24);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);
		
		chooser = new GLabel("",new Point(30,83),new Point(940,80),this,true,0,16);
		chooser.setOpaque(true);
		chooser.setBackground(UIUtil.bgGrey);
		chooser.setForeground(UIUtil.bgWhite);
		chooser.setHorizontalAlignment(JLabel.CENTER);
		
		GLabel message = new GLabel("*单击表头可排序", new Point(40, 167), new Point(120, 30), this, true, 0, 13);
		
		letter = new SelectLabel[26];
		for(int i=0;i<letter.length;i++){
			letter[i] = new SelectLabel(String.valueOf((char)(65+i)), new Point(10+i*31, 7), new Point(30, 30), chooser, true, 0, 20);
			letter[i].setOpaque(true);
			letter[i].setSelected(false);
			//letter[i].setBackground(UIUtil.bgGrey);
			letter[i].setForeground(UIUtil.bgWhite);
			letter[i].setHorizontalAlignment(SwingConstants.CENTER);
			letter[i].addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {
					SelectLabel selectLabel = (SelectLabel)e.getSource();
					for(int i=0;i<letter.length;i++){
						letter[i].setSelected(false);
					}
					selectLabel.setSelected(true);
				}
				public void mouseExited(MouseEvent e) {
					SelectLabel selectLabel = (SelectLabel)e.getSource();
					if(!selectLabel.isSelected){
						selectLabel.setBackground(UIUtil.bgGrey);
					}
				}
				public void mouseEntered(MouseEvent e) {
					SelectLabel selectLabel = (SelectLabel)e.getSource();
					selectLabel.setBackground(UIUtil.nbaRed);
					selectLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				public void mouseClicked(MouseEvent e) {
				}
			});
		}
		
		ArrayList<TeamDataPO> teamDataPOs = teamLogic.GetInfoBySeason("13-14");
		int boxHeaderTeamLength = teamDataPOs.size()+1;
		String[] boxHeaderTeam = new String[boxHeaderTeamLength];
		boxHeaderTeam[0] = "根据球队查找";
		for(int i=1;i<boxHeaderTeam.length;i++){
			boxHeaderTeam[i] = TableUtility.getChTeam(teamDataPOs.get(i-1).getShortName())+" "+
					//teamDataPOs.get(i-1).getName()+" "+
					teamDataPOs.get(i-1).getShortName();
		}
		comboBoxTeam = new JComboBox<String>(boxHeaderTeam);
		comboBoxTeam.setBounds(10, 44, 200, 30);
		chooser.add(comboBoxTeam);
		
		String[] boxHeaderPosition = {"根据位置查找", "前锋 F", "中锋 C", "后卫 G"};
		comboBoxPosition = new JComboBox<String>(boxHeaderPosition);
		comboBoxPosition.setBounds(220, 44, 200, 30);
		chooser.add(comboBoxPosition);
		
		search = new JTextField("根据姓名查找");
		search.setBounds(430, 44, 200, 30);
		chooser.add(search);
		
		infoInit();
		tableSetting();
		
	}
	
	private void tableSetting(){
		
		tableModel = new DefaultTableModel(data, header){
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
		
		
		table = new StyleTable();
		table.setStyleTabelModel(tableModel);
		tableSetting(table);
		table.setSort();
		
		scrollPane = new JScrollPane(); 
		scrollPane.setBounds(40, 200, 920, 440);
		//scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);
		this.add(scrollPane);
	}

	private void tableSetting(final JTable table){
		table.setPreferredScrollableViewportSize(new Dimension(920, 440));//设置大小
		table.setBounds(40, 200, 920, 480);
		table.getTableHeader().setPreferredSize(new Dimension(920, 30));//设置表头大小

		//设置列显示蓝色字
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
		table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer(){
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
		
		//设置列手形监听
		table.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/table.getRowHeight();

				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == 0 || column ==1)) {
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

				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == 0)) {
					String playerName = table.getValueAt(row, column).toString();
					PlayerDataPO[] pos = playerLogic.getAllSeasonInfo(playerName);
					StartUI.startUI.turn(turnController.turnToPlayerDetials(pos));
				}else{
				}
			}
		};
		table.addMouseListener(mouseAdapter);
	}
	
	private void infoInit(){
		header.removeAllElements();
		data.removeAllElements();
		header.addElement("姓名");header.addElement("球队");header.addElement("位置");header.addElement("号码");header.addElement("身高");
		header.addElement("体重");header.addElement("生日");header.addElement("球龄");
		for(int i=0;i<playList.length;i++){
			PlayerDataPO p = playList[i];
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(p.getName());
			vector.addElement(p.getTeamName()+" "+TableUtility.getChTeam(p.getTeamName()));
			vector.addElement(p.getPosition());
			vector.addElement(p.getNumber());
			vector.addElement(p.getHeight());
			vector.addElement(p.getWeight());
			vector.addElement(p.getBirth());
			vector.addElement(p.getExp());
			data.addElement(vector);
		}
	}
}
