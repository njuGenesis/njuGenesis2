package presentation.stats;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import data.po.TeamDataPO;
import assistance.NewFont;
import bussinesslogic.team.TeamLogic;
import presentation.component.BgPanel;
import presentation.component.GComboBox;
import presentation.component.GLabel;
import presentation.component.GTable;
import presentation.component.StyleScrollPane;
import presentation.contenui.TableUtility;
import presentation.contenui.UIUtil;

public class TeamStatsPanelNew extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String bg = "";
	
	private TeamLogic logic = new TeamLogic();

	public GTable table;
	public JScrollPane jsp;

	public JLabel title;

	public JComboBox<String> season;
	
	public String[] seasonItem = {"13-14赛季","12-13赛季",};

	public JButton submit;
	
	String[] header1 = {"名称","场数","投篮命中","投篮","三分命中","三分","罚球命中","罚球","进攻篮板","防守篮板","篮板","助攻","抢断","盖帽","失误","犯规","得分",};
	String[] header2 = {"名称","投篮%","三分%","罚球%","胜率","进攻效率","防守效率","篮板效率","抢断效率","助攻率"};

	JCheckBox all;  //总数
	JCheckBox avg;  //场均
	JCheckBox eff;  //效率
	
	public StyleScrollPane jspAll;
	public StyleScrollPane jspAvg;
	public StyleScrollPane jspEff;

	StatsFactory factory = new StatsFactory();
	
	@Override
	public void refreshUI() {
		this.remove(title);
		this.remove(jspAll);
		this.remove(jspAvg);
		this.remove(jspEff);
		this.remove(season);
		this.remove(submit);
		this.remove(all);
		this.remove(avg);
		this.remove(eff);

		init();
	}
	

	public TeamStatsPanelNew() {
		super(bg);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		this.setBounds(50, 0, 950, 650);
		this.setLayout(null);
		this.setOpaque(false);
		
		init();
		
		
	}
	
	
	private void init(){
		title = new GLabel("   球队",new Point(80-this.getX(),30),new Point(890,52),this,true,0,24);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);
		
		season = new GComboBox(seasonItem);
		season.setBounds(80-this.getX(), 100, 150, 30);
		season.setFont(NewFont.ComboBoxFont);
		this.add(season);
		
		submit = UIUtil.getSelectButton();
		submit.setBounds(820-this.getX(), 100, 150, 30);
		submit.addMouseListener(new SubmitListener());
		this.add(submit);
		
		all = new JCheckBox("总数");
		all.setBounds(740-this.getX(), 150, 70, 30);
		all.setSelected(true);
		all.setOpaque(false);
		all.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(all.isSelected()){
					avg.setSelected(false);
					eff.setSelected(false);
					jspAll.setVisible(true);
					jspAvg.setVisible(false);
					jspEff.setVisible(false);
				}else{
					all.setSelected(true);
				}
			}
		});
		this.add(all);

		avg = new JCheckBox("场均");
		avg.setBounds(820-this.getX(), 150, 70, 30);
		avg.setOpaque(false);
		avg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(avg.isSelected()){
					all.setSelected(false);
					eff.setSelected(false);
					jspAll.setVisible(false);
					jspAvg.setVisible(true);
					jspEff.setVisible(false);
				}else{
					avg.setSelected(true);
				}
			}

		});
		this.add(avg);

		eff = new JCheckBox("效率");
		eff.setBounds(900-this.getX(), 150, 70, 30);
		eff.setOpaque(false);
		eff.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(eff.isSelected()){
					all.setSelected(false);
					avg.setSelected(false);
					jspAll.setVisible(false);
					jspAvg.setVisible(false);
					jspEff.setVisible(true);
				}else{
					eff.setSelected(true);
				}
			}

		});
		this.add(eff);

		jspAll = factory.getTablePaneTeam(getHeader1(), getAllData());
		this.add(jspAll);

		jspAvg = factory.getTablePaneTeam(getHeader1(), getAvgData());
		jspAvg.setVisible(false);
		this.add(jspAvg);

		jspEff = factory.getTablePaneTeam(getHeader2(), getEffData());
		jspEff.setVisible(false);
		this.add(jspEff);
		
		this.repaint();
	}
	
	private Vector<String> getHeader1(){
		Vector<String> v = new Vector<String>();
		for(int i=0;i<header1.length;i++){
			v.addElement(header1[i]);
		}
		return v;
	}

	private Vector<String> getHeader2(){
		Vector<String> v = new Vector<String>();
		for(int i=0;i<header2.length;i++){
			v.addElement(header2[i]);
		}
		return v;
	}

	private Vector<Vector<Object>> getAllData(){
		String s = getSeasonStr(season.getSelectedItem().toString());

		ArrayList<TeamDataPO> po = logic.GetInfoBySeason(s);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i=0;i<po.size();i++){
			Vector<Object> v = new Vector<Object>();
//			{"序号","名称","场数","投篮命中","投篮出手",
//			"三分命中","三分出手","罚球命中","罚球出手",
//			"进攻篮板","防守篮板","篮板",
//			"助攻","抢断","盖帽","失误","犯规","得分",};
			v.addElement(TableUtility.getChTeam(po.get(i).getName()));
			v.addElement((int)po.get(i).getMatchNumber());
			v.addElement((int)po.get(i).getShootEffNumber());
			v.addElement((int)po.get(i).getShootNumber());
			v.addElement((int)po.get(i).getTPEffNumber());
			v.addElement((int)po.get(i).getTPNumber());
			v.addElement((int)po.get(i).getFTEffNumber());
			v.addElement((int)po.get(i).getFTNumber());
			v.addElement((int)po.get(i).getOffBackBoard());
			v.addElement((int)po.get(i).getDefBackBoard());
			v.addElement(po.get(i).getBackBoard());
			v.addElement(po.get(i).getAssitNumber());
			v.addElement(po.get(i).getStealNumber());
			v.addElement(po.get(i).getRejection());
			v.addElement(po.get(i).getTo());
			v.addElement(po.get(i).getFoul());
			v.addElement(po.get(i).getPTS());

			data.add(v);
		}
		return data;
	}

	private Vector<Vector<Object>> getAvgData(){
		String s = getSeasonStr(season.getSelectedItem().toString());

		ArrayList<TeamDataPO> po = logic.GetInfoBySeason(s);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i=0;i<po.size();i++){
			Vector<Object> v = new Vector<Object>();
//			{"序号","名称","场数","投篮命中","投篮出手",
//			"三分命中","三分出手","罚球命中","罚球出手",
//			"进攻篮板","防守篮板","篮板",
//			"助攻","抢断","盖帽","失误","犯规","得分",};
			v.addElement(TableUtility.getChTeam(po.get(i).getName()));
			v.addElement((int)po.get(i).getMatchNumber());
			v.addElement(po.get(i).getShootEffNumberPG());
			v.addElement(po.get(i).getShootNumberPG());
			v.addElement(po.get(i).getTPEffNumberPG());
			v.addElement(po.get(i).getTPNumberPG());
			v.addElement(po.get(i).getFTEffNumberPG());
			v.addElement(po.get(i).getFTNumberPG());
			v.addElement(po.get(i).getOffBackBoardPG());
			v.addElement(po.get(i).getDefBackBoardPG());
			v.addElement(po.get(i).getBackBoardPG());
			v.addElement(po.get(i).getAssitNumberPG());
			v.addElement(po.get(i).getStealNumberPG());
			v.addElement(po.get(i).getRejectionPG());
			v.addElement(po.get(i).getToPG());
			v.addElement(po.get(i).getFoulPG());
			v.addElement(po.get(i).getPPG());

			data.add(v);
		}
		return data;
	}
	private Vector<Vector<Object>> getEffData(){
		String s = getSeasonStr(season.getSelectedItem().toString());

		ArrayList<TeamDataPO> po = logic.GetInfoBySeason(s);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i=0;i<po.size();i++){
			Vector<Object> v = new Vector<Object>();
//			"序号","名称",
//			"投篮命中率","三分命中率","罚球命中率","胜率",
//			"进攻效率","防守效率","篮板效率",
//			"抢断效率","助攻率"			
			v.addElement(TableUtility.getChTeam(po.get(i).getName()));
			v.addElement(po.get(i).getShootEff());
			v.addElement(po.get(i).getTPEff());
			v.addElement(po.get(i).getFTEff());
			v.addElement(po.get(i).getWR());
			v.addElement(po.get(i).getOffEff());
			v.addElement(po.get(i).getDefEff());
			v.addElement(po.get(i).getBackBoardEff());
			v.addElement(po.get(i).getStealEff());
			v.addElement(po.get(i).getAssistEff());

			data.add(v);
		}
		return data;
	}

	private int getMinute(double d){
		return (int)d/60;
	}

	private String getSeasonStr(String s){
		return s.substring(0, 5);
	}
	
	
	class SubmitListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			TeamStatsPanelNew.this.remove(jspAll);
			TeamStatsPanelNew.this.remove(jspAvg);
			TeamStatsPanelNew.this.remove(jspEff);

			all.setSelected(true);
			avg.setSelected(false);
			eff.setSelected(false);

			jspAll = factory.getTablePaneTeam(getHeader1(), getAllData());
			TeamStatsPanelNew.this.add(jspAll);

			jspAvg = factory.getTablePaneTeam(getHeader1(), getAvgData());
			jspAvg.setVisible(false);
			TeamStatsPanelNew.this.add(jspAvg);

			jspEff = factory.getTablePaneTeam(getHeader2(), getEffData());
			jspEff.setVisible(false);
			TeamStatsPanelNew.this.add(jspEff);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
