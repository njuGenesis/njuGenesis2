package presentation.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("rawtypes")
public class GComboBoxRenderer implements ListCellRenderer{

	private DefaultListCellRenderer defaultCellRenderer = new DefaultListCellRenderer();

	public GComboBoxRenderer() {
		super();
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		JLabel renderer = (JLabel)defaultCellRenderer.getListCellRendererComponent(
				list, value, index, isSelected, cellHasFocus);
		if(isSelected){
			renderer.setBackground(new Color(0, 110, 185));
			renderer.setForeground(Color.WHITE);
		}else{
			renderer.setBackground(new Color(250,250,250));
		}
		list.setSelectionBackground(new Color(250,250,250));
		list.setBorder(null);
//		renderer.setFont(XUtil.defaultComboBoxFont);
		renderer.setHorizontalAlignment(JLabel.LEFT);
		return renderer;
	}

}
