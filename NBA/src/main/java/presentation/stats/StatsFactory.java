package presentation.stats;

import javax.swing.JScrollPane;

import presentation.component.StyleTable;

public class StatsFactory {
	
	public JScrollPane getTablePane(){
		StyleTable table = new StyleTable(data,header);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(30, 190, 940, 420);
		
		return null;
	}
	
	

}
