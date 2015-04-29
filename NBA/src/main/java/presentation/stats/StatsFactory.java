package presentation.stats;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import presentation.component.StyleScrollPane;
import presentation.component.StyleTable;
import presentation.contenui.TableUtility;
import presentation.contenui.TurnController;
import presentation.mainui.StartUI;

public class StatsFactory {

	public StyleScrollPane getTablePanePlayer(Vector<String> header,Vector<Vector<Object>> data){
		StyleTable table = new StyleTable();
		StyleScrollPane pane = new StyleScrollPane(table);
		table.tableSetting(table, header, data, pane, new Rectangle(30, 190, 890, 420));
		setTablePlayer(table);
		
		return pane;
	}

	public StyleScrollPane getTablePaneTeam(Vector<String> header,Vector<Vector<Object>> data){
		StyleTable table = new StyleTable();
		StyleScrollPane pane = new StyleScrollPane(table);
		table.tableSetting(table, header, data, pane, new Rectangle(30, 190, 890, 420));
		setTableTeam(table);

		return pane;
	}

	private void setTablePlayer(JTable table){
		table.addMouseListener(new TableListenerPlayer());
		
		TableModel tableModel = table.getModel();
		TableRowSorter<TableModel> tableRowSorter=new TableRowSorter<TableModel>(tableModel); // 排序 
		table.setRowSorter(tableRowSorter); 
		
	}

	private void setTableTeam(JTable table){
		table.addMouseListener(new TableListenerTeam());
		
		TableModel tableModel = table.getModel();
		TableRowSorter<TableModel> tableRowSorter=new TableRowSorter<TableModel>(tableModel); // 排序 
		table.setRowSorter(tableRowSorter); 

	}


	class TableListenerPlayer implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable)e.getSource();

			//得到选中的行列的索引值
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();

			if(c==0){
				String player = table.getValueAt(r, c).toString();
				TurnController tc = new TurnController();

				//				StartUI.startUI.turn(tc.turnToPlayerDetials(player));
			}else if(c==1){
				String team = TableUtility.getShortChTeam(table.getValueAt(r, c).toString());
				TurnController tc = new TurnController();
				if(!team.equals("null")){
					StartUI.startUI.turn(tc.turnToTeamDetials(team));
				}
				
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class TableListenerTeam implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable)e.getSource();

			//得到选中的行列的索引值
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();

			if(c==0){
				String team = TableUtility.getChTeam(table.getValueAt(r, c).toString());
				TurnController tc = new TurnController();
				if(!team.equals("null")){
					StartUI.startUI.turn(tc.turnToTeamDetials(team));
				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
