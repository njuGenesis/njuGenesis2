package presentation.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension; 



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Set;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import presentation.contenui.UIUtil;

/**
 * 本类实现了对JTable颜色的控制，提供了两套方案：
 * 1.实现了表格行两种颜色交替的效果
 * 2.实现了用一个控制颜色的字符串数组来设置所对应行的颜色
 * 本文件与PlanetTable.java文件相配合使用
 * @author Sidney
 * @version 1.0 (2008-1-14)
 */
public class StyleTable extends JTable {
	
	private String[] color = null; //用于设定行颜色的数组

	public StyleTable() {
		super();
	}

	public void setStyleTabelModel(TableModel tableModel){
		super.setModel(tableModel);
		setting();
		paintRow(); //将奇偶行分别设置为不同颜色
	}
	
	public StyleTable(TableModel tableModel) {
		super(tableModel);
		setting();
		paintRow(); //将奇偶行分别设置为不同颜色
	}
	
	public StyleTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		setting();
		paintRow(); //将奇偶行分别设置为不同颜色
	}
	
	public StyleTable(Object[][] rowData, Object[] columnNames, String[] color) {
		super(rowData, columnNames);
		setting();
		this.color = color;
		paintColorRow();
	}

	private void setting(){
		//setFixColumnWidth(this); //固定表格的列宽
		this.setIntercellSpacing(new Dimension(1,0)); //设置数据与单元格边框的眉边距
		//根据单元内的数据内容自动调整列宽resize column width accordng to content of cell automatically
		fitTableColumns(this);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//设置表头拖动方式
		this.getTableHeader().setReorderingAllowed(false);//设置表头不允许变换顺序
		this.getTableHeader().setForeground(Color.black);
		this.getTableHeader().setOpaque(false);
		this.setRowHeight(30);//设置行宽
		this.setSelectionBackground(UIUtil.nbaBlue); //设置选中的颜色
		this.setSelectionForeground(UIUtil.bgWhite);
		//this.setBorder(null);
		this.setShowHorizontalLines(false);//取消单元格之间的线
		this.setShowVerticalLines(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//选择单个行
		this.setOpaque(false);
		this.setShowGrid(false);
		Color color = this.getGridColor();
		this.setBorder(new MatteBorder(0, 1, 0, 0, color));

		MultiLineHeaderRenderer multiLineHeaderRenderer = new MultiLineHeaderRenderer();
		TableColumnModel cmodel = this.getColumnModel();  
		for (int i = 0; i < cmodel.getColumnCount(); i++) {  
			cmodel.getColumn(i).setHeaderRenderer(multiLineHeaderRenderer);
		} 
	}
	
	public void setSort(){
		//通过点击表头来排序列中数据resort data by clicking table header
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.getModel());
		this.setRowSorter(sorter);
	}

	public void tableSetting(final StyleTable table, final Vector<String> header, final Vector<Vector<Object>> data, StyleScrollPane scrollPane, java.awt.Rectangle r){
		DefaultTableModel tableModel = new DefaultTableModel(data, header){
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
		
		table.setPreferredScrollableViewportSize(new Dimension(r.width, r.height));//设置大小
		table.setBounds(r);
		table.getTableHeader().setPreferredSize(new Dimension(r.width, 30));//设置表头大小
		table.setStyleTabelModel(tableModel);
		
		scrollPane.setBounds(r);
		
//		MouseAdapter mouseAdapter = new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
//				int row    = e.getY()/table.getRowHeight();
//
//				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0 && (column == 0)) {
//				}else{
//				}
//			}
//		};
//		table.addMouseListener(mouseAdapter);
	}

	/**
	 * 根据color数组中相应字符串所表示的颜色来设置某行的颜色，注意，JTable中可以对列进行整体操作
	 * 而无法对行进行整体操作，故设置行颜色实际上是逐列地设置该行所有单元格的颜色。
	 */
	public void paintRow() {
		TableColumnModel tcm = this.getColumnModel();
		for (int i = 0, n = tcm.getColumnCount(); i < n; i++) 
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setCellRenderer(new RowRenderer());
		}
	}

	public void paintColorRow() {
		TableColumnModel tcm = this.getColumnModel();
		for (int i = 0, n = tcm.getColumnCount(); i < n; i++) 
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setCellRenderer(new RowColorRenderer());
		}
	}

	/**
	 * 将列设置为固定宽度。//fix table column width
	 *
	 */
	public void setFixColumnWidth(JTable table){
		//this.setRowHeight(30);
		this.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		/**/
		//The following code can be used to fix table column width
		TableColumnModel tcm = table.getTableHeader().getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) 
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setPreferredWidth(50);
			// tc.setMinWidth(100);
			tc.setMaxWidth(50);
		}
	}

	/**
	 * 根据数据内容自动调整列宽。//resize column width automatically
	 *
	 */
	public void fitTableColumns(JTable myTable){
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while(columns.hasMoreElements())
		{
			TableColumn column = (TableColumn)columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int)header.getDefaultRenderer().getTableCellRendererComponent
					(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
			for(int row = 0; row < rowCount; row++)
			{
				int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent
						(myTable, myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // 此行很重要
			column.setWidth(width+myTable.getIntercellSpacing().width);
		}
	}
	
	/**
	 * 定义内部类，用于控制单元格颜色，每两行颜色相间，本类中定义为蓝色和绿色。
	 *
	 * @author Sidney
	 *
	 */
	private class RowRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable t, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) 
		{
			//设置奇偶行的背景色，可在此根据需要进行修改
			if (row % 2 == 0)
				setBackground(new Color(235, 236, 231));
			else
				setBackground(new Color(251, 251, 251));
			
			//setHorizontalAlignment(SwingConstants.CENTER);

			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);
			
		}
	}

	/**
	 * 定义内部类，可根据一个指定字符串数组来设置对应行的背景色
	 *
	 * @author Sidney
	 *
	 */
	private class RowColorRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable t, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) 
		{
			//分支判断条件可根据需要进行修改
			if (color[row].trim().equals("E")) 
			{
				setBackground(Color.RED);
			} 
			else if (color[row].trim().equals("H")) 
			{
				setBackground(Color.CYAN);
			} 
			else if (color[row].trim().equals("A")) 
			{
				setBackground(Color.BLUE);
			} 
			else if (color[row].trim().equals("F")) 
			{
				setBackground(Color.ORANGE);
			} 
			else 
			{
				setBackground(Color.WHITE);
			}

			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);
		}
	}
}

class MultiLineHeaderRenderer extends JTextArea implements TableCellRenderer{
	private static final long serialVersionUID = 1L;

	public MultiLineHeaderRenderer() {
		super(1, 50);
		setOpaque(true);
		setLineWrap(true);
		setWrapStyleWord(true);
	}

	public java.awt.Component getTableCellRendererComponent(JTable table, Object obj,
			boolean isSelected, boolean hasFocus,
			int row,int column) {
		int width = 1;
		String value = "";
		if (table != null) {
			JTableHeader header = table.getTableHeader();
			if (header != null) {
				setForeground(header.getForeground());
				setBackground(header.getBackground());
				setFont(header.getFont());
			}
			width = header.getColumnModel().getColumn(column).getWidth();
			if(width==0)
				width = 150;
			value = header.getColumnModel().getColumn(column).getHeaderValue().toString();
		}
		setText( (value == null) ? "Column:" + column : value.toString());
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		this.setRows((10*value.length())/width);
		return this;
	}
}
