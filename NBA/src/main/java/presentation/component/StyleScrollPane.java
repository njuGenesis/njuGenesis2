package presentation.component;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StyleScrollPane extends JScrollPane{

	private static final long serialVersionUID = 1L;

	public StyleScrollPane(JTable table){
		super();
		
		//basicSP.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setViewportBorder(BorderFactory.createEmptyBorder());
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
		this.setViewportView(table);
	}
}
