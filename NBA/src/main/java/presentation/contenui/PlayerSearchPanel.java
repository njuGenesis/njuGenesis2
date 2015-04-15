package presentation.contenui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.component.GComboBox;
import presentation.component.GRadioButton;
import presentation.component.GTable;
import presentation.component.NameCellEditor;
import presentation.component.NameRenderer;
import presentation.component.TeamShortCellEditor;
import presentation.mainui.MainUI;
import assistance.NewFont;
import bussinesslogic.player.PlayerLogic;
import data.po.PlayerDataPO;

public class PlayerSearchPanel extends ContentPanel{

	public PlayerLogic logic = new PlayerLogic();

	public GTable table;
	public JScrollPane jsp;

	public JLabel title;

	public JButton left;
	public JButton right;
	public JTextField page;

	public JComboBox<String> position;
	public String[] positionItem = {"全部位置","后卫","前锋","中锋"}; 

	public JButton submit;

	public JTextField nameSearch;

	public GRadioButton[] initials = new GRadioButton[26];

	private static String url = "img/content/contentTitle.png";

	public PlayerSearchPanel() {

		super(url);

		//-----初始化翻页按钮-----
		left = UIUtil.getLeftButton();
		left.setBounds(380, 515, 20, 20);
		left.addActionListener(new ButtonListener());
		panel.add(left);
		right = UIUtil.getRightButton();
		right.setBounds(450, 515, 20, 20);
		right.addActionListener(new ButtonListener());
		panel.add(right);


		//-----初始化页数框-----
		page = new JTextField("1");
		page.setBounds(413, 514, 25, 25);
		page.setBorder(null);
		page.setOpaque(false);
		page.setHorizontalAlignment(JTextField.CENTER);
		page.addActionListener(new PageListener());
		page.setFont(new Font("微软雅黑",1,12));
		panel.add(page);

		PagingTableModel model = new PagingTableModel(getPlayerData(logic.getAllInfo("13-14"))){
			public boolean isCellEditable(int row, int column){
				if(column==1 || column==2){
					return true;
				}else{
					return false;
				}
			}
		};  
		table = new GTable(model,left,right,page,false); 

		//球员和球队名可点击，设置不同的renderer和editor
		table.setRenderer(1, new NameRenderer());
		table.setEditor(1, new NameCellEditor());
		
		table.setRenderer(2, new NameRenderer());
		table.setEditor(2, new TeamShortCellEditor());
		
//		table.getColumnModel().getColumn(1).setCellRenderer(new NameRenderer());
//		table.getColumnModel().getColumn(1).setCellEditor(new NameCellEditor());
//
//		table.getColumnModel().getColumn(2).setCellRenderer(new NameRenderer());
//		table.getColumnModel().getColumn(2).setCellEditor(new TeamShortCellEditor());


		MouseListener[] m = table.getTableHeader().getMouseListeners();
		for(int i=0;i<m.length;i++){
			table.getTableHeader().removeMouseListener(m[i]);
		}

		// Use our own custom scrollpane.  
		jsp = PagingTableModel.createPagingScrollPaneForTable(table,left,right);  
		jsp.setBounds(25, 144, 830, 370);
		TableUtility.setTabelPanel(jsp);

		panel.add(jsp);



		title = new JLabel("球员列表    【点击表头可进行降序/升序    点击球员或球队可跳转至相关页面】");
		title.setForeground(Color.white);
//		title.setFont(new Font("微软雅黑",1,13));
		title.setFont(NewFont.getTableTitleFont());
		title.setBounds(40, 25, 500, 20);
		panel.add(title);

		position = new GComboBox(positionItem);
		position.setBounds(45, 63, 120, 30);
		position.setFont(NewFont.ComboBoxFont);
		panel.add(position);

		submit = UIUtil.getSelectButton();
		submit.setBounds(720, 100, 120, 30);
		submit.addMouseListener(new SubmitListener());
		panel.add(submit);

		nameSearch = new JTextField("根据姓名搜索");
		nameSearch.setBorder(BorderFactory.createLineBorder(UIUtil.nbaBlue));
		nameSearch.setFont(NewFont.ComboBoxFont);
		nameSearch.setOpaque(false);
		nameSearch.setBounds(220, 63, 240, 30);
		nameSearch.addFocusListener(new NameListener());
		panel.add(nameSearch);


		ButtonGroup bg = new ButtonGroup();
		for(int i=0;i<26;i++){
			char c = (char)('A'+i);
			GRadioButton bt = new GRadioButton(c);
			bt.setBounds(45+30*i, 58, 30, 25);
			bt.addItemListener(new LetterListener());
			//			bt.setFont(new Font("微软雅黑",0,8));
			initials[i] = bt;
			bg.add(bt);
			//			panel.add(bt);
		}


	}

	private TableData[] getPlayerData(PlayerDataPO[] po){
		String[] head = {"序号","姓名","球队","位置","身高","体重","球龄"};
		TableData[] data = new TableData[po.length];
		for(int i=0;i<po.length;i++){
			//			String[] row = {String.valueOf(i+1),po[i].getName(),TableUtility.getChTeam(po[i].getTeamName()),TableUtility.getChPosition(po[i].getPosition()),
			//					po[i].getHeight(),String.valueOf(po[i].getWeight()),String.valueOf(po[i].getExp())};
			String[] row = {String.valueOf(i+1),po[i].getName(),TableUtility.getChTeam(po[i].getTeamName()),TableUtility.getChPosition(po[i].getPosition()),
					po[i].getHeight(),String.valueOf(po[i].getWeight()),String.valueOf(po[i].getExp())};
			data[i] = new TableData(head,row);
		}

		return data;
	}


	class NameListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			clearDefault();
		}

		public void focusLost(FocusEvent arg0) {
			showDefault();
		}

		private void showDefault(){
			if(nameSearch.getText().equals("")){
				nameSearch.setText("根据姓名搜索");
			}
		}

		private void clearDefault(){
			if(nameSearch.getText().equals("根据姓名搜索")){
				nameSearch.setText("");
			}
		}

	}


	class LetterListener implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			GRadioButton bt = (GRadioButton)e.getSource();
			if(bt.isSelected()){
				bt.setIcon(GRadioButton.getChosenIcon(bt.letter));

				PagingTableModel model = new PagingTableModel(getPlayerData(logic.getAllInfo("13-14")));
				PagingTableModel tm = new PagingTableModel(TableData.getInitials(model.data, bt.letter, 1));

				table.setModel(tm);

				PagingTableModel.setPagingButton(table, left, right);
				page.setText(String.valueOf(tm.getPageOffset()+1));

				table.repaint();
				MainUI.getMainFrame().repaint();

			}else{
				bt.setIcon(GRadioButton.getIcon(bt.letter));
			}
		}

	}

	//----------筛选按钮的监听事件----------
	class SubmitListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			String pos = position.getSelectedItem().toString();
			String name = nameSearch.getText().trim();

			PlayerDataPO[] po;
			if(name.equals("根据姓名搜索")){
				po = logic.getAllSearch("", TableUtility.getChPosition(pos), "null","13-14");
			}else{
				po = logic.getAllSearch(name, TableUtility.getChPosition(pos), "null","13-14");
			}
			PagingTableModel tm = new PagingTableModel(getPlayerData(po)){
				public boolean isCellEditable(int row, int column){
					if(column==1 || column==2){
						return true;
					}else{
						return false;
					}
				}
			};

			table.setModel(tm);

			//			PagingTableModel.setPagingButton(table, left, right);
			tm.setButton(left, right);
			tm.checkButton(left, right);
			page.setText(String.valueOf(tm.getPageOffset()+1));

			table.getColumnModel().getColumn(1).setCellRenderer(new NameRenderer());
			table.getColumnModel().getColumn(1).setCellEditor(new NameCellEditor());

			table.getColumnModel().getColumn(2).setCellRenderer(new NameRenderer());
			table.getColumnModel().getColumn(2).setCellEditor(new TeamShortCellEditor());

			table.repaint();
			
			nameSearch.setText("根据姓名搜索");

			MainUI.getMainFrame().repaint();
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
