package presentation.contenui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.component.BgPanel;
import presentation.component.GComboBox;
import presentation.component.GTable;
import presentation.component.NameCellEditor;
import presentation.component.NameRenderer;
import presentation.component.TeamShortCellEditor;
import presentation.mainui.MainUI;
import assistance.NewFont;
import bussinesslogic.player.PlayerLogic;
import data.po.PlayerDataPO;

public class PlayerStatsPanel extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlayerLogic logic = new PlayerLogic();

	public GTable table;
	public JScrollPane jsp;

	public JLabel title;

	public JComboBox<String> position;
	public JComboBox<String> league;
	public JComboBox<String> dataType;

	public String[] positionItem = {"全部位置","后卫","前锋","中锋"}; 
	public String[] leagueItem = {"全部联盟","东-大西洋分区","东-中央分区","东-东南分区","西-西北分区","西-太平洋分区","西-西南分区"};
//	public String[] statsItem = {"得分","篮板","助攻","得分/篮板/助攻","盖帽","抢断","犯规","失误","分钟","效率","投篮","三分","罚球","两双"};
	public String[] dataTypeItem = {"场均","总数"};

	public JButton submit;

	public JButton left;
	public JButton right;
	public JTextField page;

	private static String url = "img/content/contentTitle.png";

	public PlayerStatsPanel() {

		super(url);

		this.setSize(1000, 650);
		this.setLocation(100, 95);
		this.setLayout(null);
		this.setOpaque(false);
		
		
		//-----初始化翻页按钮-----
		left = UIUtil.getLeftButton();
		left.setBounds(380, 515, 20, 20);
		left.addActionListener(new ButtonListener());
		this.add(left);
		right = UIUtil.getRightButton();
		right.setBounds(450, 515, 20, 20);
		right.addActionListener(new ButtonListener());
		this.add(right);

		
		//-----初始化页数框-----
		page = new JTextField("1");
		page.setBounds(413, 514, 25, 25);
		page.setBorder(null);
		page.setOpaque(false);
		page.setHorizontalAlignment(JTextField.CENTER);
		page.addActionListener(new PageListener());
		page.setFont(new Font("微软雅黑",1,12));
		this.add(page);

		
		
		PagingTableModel model = new PagingTableModel(getPlayerDataAvg(logic.getAllInfo("13-14"))){
			public boolean isCellEditable(int row, int column){
				if(column==1 || column==2){
					return true;
				}else{
					return false;
				}
			}
		};  
		table = new GTable(model,left,right,page,true,0);

		table.setRenderer(1, new NameRenderer());
		table.setEditor(1, new NameCellEditor());
		
		table.setRenderer(2, new NameRenderer());
		table.setEditor(2, new TeamShortCellEditor());
		
//		table.getColumnModel().getColumn(1).setCellRenderer(new NameRenderer());
//		table.getColumnModel().getColumn(1).setCellEditor(new NameCellEditor());
//		
//		table.getColumnModel().getColumn(2).setCellRenderer(new NameRenderer());
//		table.getColumnModel().getColumn(2).setCellEditor(new TeamShortCellEditor());
		
		// Use our own custom scrollpane.  
		jsp = PagingTableModel.createPagingScrollPaneForTable(table,left,right);  
		jsp.setBounds(25, 144, 830, 370);
		TableUtility.setTabelPanel(jsp);

		this.add(jsp);

		title = new JLabel("球员数据    【点击表头可进行降序/升序    点击球员或球队可跳转至相关页面】");
		title.setForeground(Color.white);
		title.setFont(new Font("微软雅黑",1,13));
		title.setBounds(40, 25, 500, 20);
		this.add(title);

		position = new GComboBox(positionItem);
		position.setBounds(45, 63, 120, 30);
		position.setFont(NewFont.ComboBoxFont);
		this.add(position);

		league = new GComboBox(leagueItem);
		league.setBounds(205, 63, 120, 30);
		league.setBackground(new Color(250,250,250));
		league.setFont(NewFont.ComboBoxFont);
		this.add(league);

		dataType = new GComboBox(dataTypeItem);
		dataType.setBounds(365, 63, 120, 30);
		dataType.setFont(NewFont.ComboBoxFont);
		this.add(dataType);

		submit = UIUtil.getSelectButton();
		submit.setBounds(720, 100, 120, 30);
		submit.addMouseListener(new SubmitListener());
		this.add(submit);


	}

	//----------总数据----------
	private TableData[] getPlayerDataAll(PlayerDataPO[] po){
		String[] head = {"序号","姓名","球队","参赛","先发","在场时间","得分","篮板","助攻","投篮命中率","三分命中率",
				"罚球命中率","两双","进攻","防守","抢断","盖帽","失误","犯规","效率","GmSc效率",
				"真实命中率","投篮效率","篮板率","进攻篮板率","防守篮板率","助攻率","抢断率","盖帽率","失误率","使用率"};
		TableData[] data = new TableData[po.length];
		for(int i=0;i<po.length;i++){
			String[] row = {String.valueOf(i+1),po[i].getName(),TableUtility.getChTeam(po[i].getTeamName()),String.valueOf(po[i].getGP()),String.valueOf(po[i].getGS()),
					String.valueOf(po[i].getMinutesOnField()),String.valueOf(po[i].getPTS()),String.valueOf(po[i].getBackboard()),String.valueOf(po[i].getAssist()),String.valueOf(po[i].getFieldGoalPercentage()),String.valueOf(po[i].getThreePGPercentage()),
					String.valueOf(po[i].getFTPercentage()),String.valueOf(po[i].getDouble()),String.valueOf(po[i].getOff()),String.valueOf(po[i].getDef()),String.valueOf(po[i].getSteal()),String.valueOf(po[i].getRejection()),
					String.valueOf(po[i].getTo()),String.valueOf(po[i].getFoul()),String.valueOf(po[i].getEff()),String.valueOf(po[i].getGmsc()),
					String.valueOf(po[i].getTruePercentage()),String.valueOf(po[i].getShootEff()),String.valueOf(po[i].getBackboardEff()),String.valueOf(po[i].getOffBEff()),String.valueOf(po[i].getDefBEff()),
					String.valueOf(po[i].getAssitEff()),String.valueOf(po[i].getStealEff()),String.valueOf(po[i].getRejectionEff()),String.valueOf(po[i].getToEff()),String.valueOf(po[i].getUseEff())
			};
			data[i] = new TableData(head,row);
		}

		return data;
	}

	//----------场均数据----------
	private TableData[] getPlayerDataAvg(PlayerDataPO[] po){
		String[] head = {"序号","姓名","球队","参赛","先发","在场时间","得分","篮板","助攻","投篮命中率","三分命中率",
				"罚球命中率","两双","进攻","防守","抢断","盖帽","失误","犯规","效率","GmSc效率",
				"真实命中率","投篮效率","篮板率","进攻篮板率","防守篮板率","助攻率","抢断率","盖帽率","失误率","使用率"};
		TableData[] data = new TableData[po.length];
		for(int i=0;i<po.length;i++){
			String[] row = {String.valueOf(i+1),po[i].getName(),TableUtility.getChTeam(po[i].getTeamName()),String.valueOf(po[i].getGP()),String.valueOf(po[i].getGS()),
					String.valueOf(po[i].getMinutesOnField()),String.valueOf(po[i].getPPG()),String.valueOf(po[i].getBPG()),String.valueOf(po[i].getAPG()),String.valueOf(po[i].getFieldGoalPercentage()),String.valueOf(po[i].getThreePGPercentage()),
					String.valueOf(po[i].getFTPercentage()),String.valueOf(po[i].getDouble()),String.valueOf(po[i].getOffPG()),String.valueOf(po[i].getDefPG()),String.valueOf(po[i].getStealPG()),String.valueOf(po[i].getRPG()),
					String.valueOf(po[i].getToPG()),String.valueOf(po[i].getFoulPG()),String.valueOf(po[i].getEff()),String.valueOf(po[i].getGmsc()),
					String.valueOf(po[i].getTruePercentage()),String.valueOf(po[i].getShootEff()),String.valueOf(po[i].getBackboardEff()),String.valueOf(po[i].getOffBEff()),String.valueOf(po[i].getDefBEff()),
					String.valueOf(po[i].getAssitEff()),String.valueOf(po[i].getStealEff()),String.valueOf(po[i].getRejectionEff()),String.valueOf(po[i].getToEff()),String.valueOf(po[i].getUseEff())
			};
			data[i] = new TableData(head,row);
		}

		return data;
	}
	
	private String changePosStr(String chinese){
		if(chinese=="前锋"){
			return "F";
		}else if(chinese=="中锋"){
			return "C";
		}else if(chinese=="后卫"){
			return "G";
		}else{
			return "null";
		}
	}
	private String changeUnStr(String chinese){
		if(chinese=="东-大西洋分区"){
			return "Atlantic";
		}else if(chinese=="东-中央分区"){
			return "Central";
		}else if(chinese=="东-东南分区"){
			return "Southeast";
		}else if(chinese=="西-西北分区"){
			return "Northwest";
		}else if(chinese=="西-太平洋分区"){
			return "Pacific";
		}else if(chinese=="西-西南分区"){
			return "Southwest";
		}else{
			return "null";
		}
	}
	
	private boolean isAverageData(String chinese){
		return chinese=="场均";
	}
	
	
	//----------筛选按钮的监听事件----------
	class SubmitListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			String pos = position.getSelectedItem().toString();
			String leag = league.getSelectedItem().toString();
			String type = dataType.getSelectedItem().toString();
			
			PlayerDataPO[] po = logic.getSelect(TableUtility.getChPosition(pos), changeUnStr(leag),"13-14");
			PagingTableModel tm;
			if(isAverageData(type)){
				tm = new PagingTableModel(getPlayerDataAvg(po)){
					public boolean isCellEditable(int row, int column){
						if(column==1 || column==2){
							return true;
						}else{
							return false;
						}
					}
				};
			}else{
				tm = new PagingTableModel(getPlayerDataAll(po)){
					public boolean isCellEditable(int row, int column){
						if(column==1 || column==2){
							return true;
						}else{
							return false;
						}
					}
				};
			}
			
			table.setModel(tm);
			table.fitTableColumns(table);
	
//			PagingTableModel.setPagingButton(table, left, right);
			tm.setButton(left, right);
			tm.checkButton(left, right);
			page.setText(String.valueOf(tm.getPageOffset()+1));
			
			table.getColumnModel().getColumn(1).setCellRenderer(new NameRenderer());
			table.getColumnModel().getColumn(1).setCellEditor(new NameCellEditor());
			
			table.getColumnModel().getColumn(2).setCellRenderer(new NameRenderer());
			table.getColumnModel().getColumn(2).setCellEditor(new TeamShortCellEditor());
			
			table.repaint();
			
//			MainUI.getMainFrame().repaint();
		}

		public void mouseEntered(MouseEvent arg0) {
			
		}

		public void mouseExited(MouseEvent arg0) {
			
		}

		public void mousePressed(MouseEvent arg0) {
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			PagingTableModel tm = (PagingTableModel)table.getModel();
			page.setText(String.valueOf(tm.getPageOffset()+1));
		}
		
	} 
	
	
	class PageListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			PagingTableModel tm = (PagingTableModel)table.getModel();
			try{
				int p = Integer.parseInt(page.getText());		
				System.out.println(p);
				int pages = tm.getPageCount();
				int now = tm.getPageOffset()+1;
				if(0<p&&p<=pages){
					if(p<now){
						for(int i=0;i<now-p;i++){
							tm.pageUp();
							checkButton();
						}
					}else if(p>now){
						for(int i=0;i<p-now;i++){
							tm.pageDown();
							checkButton();
						}
					}
				}
				page.setText(String.valueOf(tm.getPageOffset()+1));
			}catch(Exception ex){
				page.setText(String.valueOf(tm.getPageOffset()+1));
			}
		}

		private void checkButton(){
			PagingTableModel tm = (PagingTableModel)table.getModel();
			int now = tm.getPageOffset();
			if(now == 0){
				left.setEnabled(false);
				right.setEnabled(true);
			}else if(now == tm.getPageCount()-1){
				left.setEnabled(true);
				right.setEnabled(false);
			}else{
				left.setEnabled(true);
				right.setEnabled(true);
			}
		}
		

	}
}
