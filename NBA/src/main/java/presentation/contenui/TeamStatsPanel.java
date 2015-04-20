package presentation.contenui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import assistance.NewFont;
import bussinesslogic.team.TeamLogic;
import data.po.PlayerDataPO;
import data.po.TeamDataPO;
import presentation.component.BgPanel;
import presentation.component.GComboBox;
import presentation.component.GTable;
import presentation.component.NameCellEditor;
import presentation.component.NameRenderer;
import presentation.component.TeamCellEditor;
import presentation.component.TeamShortCellEditor;
import presentation.contenui.PlayerStatsPanel.ButtonListener;
import presentation.contenui.PlayerStatsPanel.PageListener;
import presentation.contenui.PlayerStatsPanel.SubmitListener;
import presentation.mainui.MainUI;

public class TeamStatsPanel extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TeamLogic logic = new TeamLogic();

	public GTable table;
	public JScrollPane jsp;

	public JLabel title;

	public JComboBox<String> dataType;

	public String[] dataTypeItem = {"场均","总数"};

	public JButton submit;

	public JButton left;
	public JButton right;
	public JTextField page;

	private static  String url = "img/content/contentTitle.png";

	public TeamStatsPanel() {

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

		
		
		
		PagingTableModel model = new PagingTableModel(getTeamDataAvg(logic.GetAllInfo())){
			public boolean isCellEditable(int row, int column){
				if(column==1){
					return true;
				}else{
					return false;
				}
			}
		};  
		table = new GTable(model,left,right,page,true,1);
		
		table.setRenderer(1, new NameRenderer());
		table.setEditor(1, new TeamCellEditor());
		

		// Use our own custom scrollpane.  
		jsp = PagingTableModel.createPagingScrollPaneForTable(table,left,right);  
		jsp.setBounds(25, 144, 830, 370);
		TableUtility.setTabelPanel(jsp);

		this.add(jsp);

		title = new JLabel("球队数据    【点击表头可进行降序/升序    点击球队可跳转至相关页面】");
		title.setForeground(Color.white);
		title.setFont(new Font("微软雅黑",1,13));
		title.setBounds(40, 25, 500, 20);
		this.add(title);


		dataType = new GComboBox(dataTypeItem);
		dataType.setBounds(45, 63, 120, 30);
		dataType.setFont(NewFont.ComboBoxFont);
		this.add(dataType);

		submit = UIUtil.getSelectButton();
		submit.setBounds(720, 100, 120, 30);
		submit.addMouseListener(new SubmitListener());
		this.add(submit);


	}


	//	private TableData[] getPlayerDataAll(){
	//		TeamDataPO[] po = logic
	//		String[] head = {"序号","名称","球队","参赛","先发","篮板","助攻","在场时间","投篮%","三分%",
	//				"罚球%","进攻","防守","抢断","盖帽","失误","犯规","得分","效率","GmSc效率",
	//				"真实命中%","投篮效率","篮板%","进攻篮板%","防守篮板%","助攻%","抢断%","盖帽%","失误%","使用%"};
	//		TableData[] data = new TableData[po.length];
	//		for(int i=0;i<po.length;i++){
	//			String[] row = {String.valueOf(i+1),po.get(i).getName(),po.get(i).getTeamName(),String.valueOf(po.get(i).getGP()),String.valueOf(po.get(i).getGS()),
	//					String.valueOf(po.get(i).getBackboard()),String.valueOf(po.get(i).getAssist()),String.valueOf(po.get(i).getMinutesOnField()),String.valueOf(po.get(i).getFieldGoalPercentage()),String.valueOf(po.get(i).getThreePGPercentage()),
	//					String.valueOf(po.get(i).getFTPercentage()),String.valueOf(po.get(i).getOff()),String.valueOf(po.get(i).getDef()),String.valueOf(po.get(i).getSteal()),String.valueOf(po.get(i).getRejection()),
	//					String.valueOf(po.get(i).getTo()),String.valueOf(po.get(i).getFoul()),String.valueOf(po.get(i).getPTS()),String.valueOf(po.get(i).getEff()),String.valueOf(po.get(i).getGmsc()),
	//					String.valueOf(po.get(i).getTruePercentage()),String.valueOf(po.get(i).getShootEff()),String.valueOf(po.get(i).getBackboardEff()),String.valueOf(po.get(i).getOffBEff()),String.valueOf(po.get(i).getDefBEff()),
	//					String.valueOf(po.get(i).getAssitEff()),String.valueOf(po.get(i).getStealEff()),String.valueOf(po.get(i).getRejectionEff()),String.valueOf(po.get(i).getToEff()),String.valueOf(po.get(i).getUseEff())
	//			};
	//			data[i] = new TableData(head,row);
	//		}
	//
	//		return data;
	//	}

	private TableData[] getTeamDataAll(ArrayList<TeamDataPO> po){
		String[] head = {"序号","名称","场数","投篮命中","投篮出手","三分命中","三分出手","罚球命中","罚球出手","进攻篮板",
				"防守篮板","篮板","助攻","抢断","盖帽","失误","犯规","得分","投篮命中率","三分命中率",
				"罚球命中率","胜率","进攻回合","进攻效率","防守效率","篮板效率","抢断效率","助攻率"};

		TableData[] data = new TableData[po.size()];
		for(int i=0;i<po.size();i++){
			String[] row = {String.valueOf(i+1),po.get(i).getName()+" "+TableUtility.getChTeam(po.get(i).getName()),String.valueOf(po.get(i).getMatchNumber()),String.valueOf(po.get(i).getShootEffNumber()),String.valueOf(po.get(i).getShootNumber()),
					String.valueOf(po.get(i).getTPEffNumber()),String.valueOf(po.get(i).getTPNumber()),String.valueOf(po.get(i).getFTEffNumber()),String.valueOf(po.get(i).getFTNumber()),String.valueOf(po.get(i).getOffBackBoard()),
					String.valueOf(po.get(i).getDefBackBoard()),String.valueOf(po.get(i).getBackBoard()),String.valueOf(po.get(i).getAssitNumber()),String.valueOf(po.get(i).getStealNumber()),String.valueOf(po.get(i).getRejection()),
					String.valueOf(po.get(i).getTo()),String.valueOf(po.get(i).getFoul()),String.valueOf(po.get(i).getPTS()),String.valueOf(po.get(i).getShootEff()),String.valueOf(po.get(i).getTPEff()),
					String.valueOf(po.get(i).getFTEff()),String.valueOf(po.get(i).getWR()),String.valueOf(po.get(i).getOff()),String.valueOf(po.get(i).getOffEff()),String.valueOf(po.get(i).getDefEff()),
					String.valueOf(po.get(i).getBackBoardEff()),String.valueOf(po.get(i).getStealEff()),String.valueOf(po.get(i).getAssistEff())
			};
			data[i] = new TableData(head,row);
		}

		return data;
	}

	private TableData[] getTeamDataAvg(ArrayList<TeamDataPO> po){
		String[] head = {"序号","名称","场数","投篮命中","投篮出手","三分命中","三分出手","罚球命中","罚球出手","进攻篮板",
				"防守篮板","篮板","助攻","抢断","盖帽","失误","犯规","得分","投篮命中率","三分命中率",
				"罚球命中率","胜率","进攻回合","进攻效率","防守效率","篮板效率","抢断效率","助攻率"};
		
		TableData[] data = new TableData[po.size()];
		for(int i=0;i<po.size();i++){
			String[] row = {String.valueOf(i+1),po.get(i).getName()+" "+TableUtility.getChTeam(po.get(i).getName()),String.valueOf(po.get(i).getMatchNumber()),String.valueOf(po.get(i).getShootEffNumberPG()),String.valueOf(po.get(i).getShootNumberPG()),
					String.valueOf(po.get(i).getTPEffNumberPG()),String.valueOf(po.get(i).getTPNumberPG()),String.valueOf(po.get(i).getFTEffNumberPG()),String.valueOf(po.get(i).getFTNumberPG()),String.valueOf(po.get(i).getOffBackBoardPG()),
					String.valueOf(po.get(i).getDefBackBoardPG()),String.valueOf(po.get(i).getBackBoardPG()),String.valueOf(po.get(i).getAssitNumberPG()),String.valueOf(po.get(i).getStealNumberPG()),String.valueOf(po.get(i).getRejectionPG()),
					String.valueOf(po.get(i).getToPG()),String.valueOf(po.get(i).getFoulPG()),String.valueOf(po.get(i).getPPG()),String.valueOf(po.get(i).getShootEff()),String.valueOf(po.get(i).getTPEff()),
					String.valueOf(po.get(i).getFTEff()),String.valueOf(po.get(i).getWR()),String.valueOf(po.get(i).getOffPG()),String.valueOf(po.get(i).getOffEff()),String.valueOf(po.get(i).getDefEff()),
					String.valueOf(po.get(i).getBackBoardEff()),String.valueOf(po.get(i).getStealEff()),String.valueOf(po.get(i).getAssistEff())
			};
			data[i] = new TableData(head,row);
		}

		return data;
	}
	
	private boolean isAverageData(String chinese){
		return chinese=="场均";
	}

	
	//----------筛选按钮的监听事件----------
		class SubmitListener implements MouseListener{

			public void mouseClicked(MouseEvent arg0) {
				String type = dataType.getSelectedItem().toString();
				
				ArrayList<TeamDataPO> po = logic.GetAllInfo();
				PagingTableModel tm;
				if(isAverageData(type)){
					tm = new PagingTableModel(getTeamDataAvg(po)){
						public boolean isCellEditable(int row, int column){
							if(column==1){
								return true;
							}else{
								return false;
							}
						}
					};
				}else{
					tm = new PagingTableModel(getTeamDataAll(po)){
						public boolean isCellEditable(int row, int column){
							if(column==1){
								return true;
							}else{
								return false;
							}
						}
					};
				}
				
				table.setModel(tm);
				table.fitTableColumns(table);
				
//				PagingTableModel.setPagingButton(table, left, right);
				tm.setButton(left, right);
				tm.checkButton(left, right);
				page.setText(String.valueOf(tm.getPageOffset()+1));
				
				table.getColumnModel().getColumn(1).setCellRenderer(new NameRenderer());
				table.getColumnModel().getColumn(1).setCellEditor(new TeamCellEditor());
				
				table.repaint();
				
//				MainUI.getMainFrame().repaint();
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
