package presentation.stats;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import presentation.component.BgPanel;
import presentation.component.GComboBox;
import presentation.component.GLabel;
import presentation.component.GTable;
import presentation.component.StyleScrollPane;
import presentation.contenui.TableUtility;
import presentation.contenui.UIUtil;
import assistance.NewFont;
import bussinesslogic.player.PlayerLogic;
import data.po.PlayerDataPO;

public class PlayerStatsPanelNew extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String bg = "";

	private PlayerLogic logic = new PlayerLogic();

	public GTable table;
	public StyleScrollPane jspAll;
	public StyleScrollPane jspAvg;
	public StyleScrollPane jspEff;

	public JLabel title;

	public JComboBox<String> position;
	public JComboBox<String> league;
	public JComboBox<String> season;

	public String[] positionItem = {"全部位置","后卫","前锋","中锋"}; 
	public String[] leagueItem = {"全部联盟","东-大西洋分区","东-中央分区","东-东南分区","西-西北分区","西-太平洋分区","西-西南分区"};
	//	public String[] statsItem = {"得分","篮板","助攻","得分/篮板/助攻","盖帽","抢断","犯规","失误","分钟","效率","投篮","三分","罚球","两双"};
	public String[] seasonItem = {"13-14赛季","12-13赛季"};

	public JButton submit;


	//	String[] head = {"序号","姓名","球队","参赛","先发","在场时间","得分","篮板","助攻","投篮命中率","三分命中率",
	//			"罚球命中率","两双","进攻","防守","抢断","盖帽","失误","犯规","效率","GmSc效率",
	//			"真实命中率","投篮效率","篮板率","进攻篮板率","防守篮板率","助攻率","抢断率","盖帽率","失误率","使用率"};

	String[] header1 = {"姓名","球队","参赛","先发","在场时间","得分","篮板","助攻","两双","进攻","防守","抢断","盖帽","失误","犯规","效率"};
	String[] header2 = {"姓名","球队","投篮%","三分%","罚球%","GmSc效率","真实命中率","投篮效率","篮板%","进攻篮板%","防守篮板%","助攻%","抢断%","盖帽%","失误%","使用%"};

	JCheckBox all;  //总数
	JCheckBox avg;  //场均
	JCheckBox eff;  //效率

	StatsFactory factory = new StatsFactory();
	
	@Override
	public void refreshUI() {
		this.remove(title);
		this.remove(jspAll);
		this.remove(jspAvg);
		this.remove(jspEff);
		this.remove(position);
		this.remove(league);
		this.remove(season);
		this.remove(submit);
		this.remove(all);
		this.remove(avg);
		this.remove(eff);

		init();
	}

	public PlayerStatsPanelNew() {
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
		title = new GLabel("   球员",new Point(80-this.getX(),30),new Point(890,52),this,true,0,24);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);

		position = new GComboBox(positionItem);
		position.setBounds(80-this.getX(), 100, 150, 30);
		position.setFont(NewFont.ComboBoxFont);
		this.add(position);

		league = new GComboBox(leagueItem);
		league.setBounds(280-this.getX(), 100, 150, 30);
		league.setBackground(new Color(250,250,250));
		league.setFont(NewFont.ComboBoxFont);
		this.add(league);

		season = new GComboBox(seasonItem);
		season.setBounds(480-this.getX(), 100, 150, 30);
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

		jspAll = factory.getTablePanePlayer(getHeader1(), getAllData());
		this.add(jspAll);

		jspAvg = factory.getTablePanePlayer(getHeader1(), getAvgData());
		jspAvg.setVisible(false);
		this.add(jspAvg);

		jspEff = factory.getTablePanePlayer(getHeader2(), getEffData());
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
		String pos = position.getSelectedItem().toString();
		String leag = league.getSelectedItem().toString();
		String s = getSeasonStr(season.getSelectedItem().toString());

		PlayerDataPO[] po = logic.getSelect(TableUtility.getChPosition(pos), changeUnStr(leag),s);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i=0;i<po.length;i++){
			Vector<Object> v = new Vector<Object>();
			//			"序号","姓名","球队","参赛","先发","在场时间","得分","篮板","助攻","两双","进攻","防守","抢断","盖帽","失误","犯规","效率"
			v.addElement(po[i].getName());
			v.addElement(TableUtility.getShortChTeam(po[i].getTeamName()));
			//			v.addElement(po[i].getTeamName());
			v.addElement(po[i].getGP());
			v.addElement(po[i].getGS());
			v.addElement(getMinute(po[i].getMinutesOnField()));
			v.addElement(po[i].getPTS());
			v.addElement(po[i].getBackboard());
			v.addElement(po[i].getAssist());
			v.addElement(po[i].getDouble());
			v.addElement(po[i].getOff());
			v.addElement(po[i].getDef());
			v.addElement(po[i].getSteal());
			v.addElement(po[i].getRejection());
			v.addElement(po[i].getTo());
			v.addElement(po[i].getFoul());
			v.addElement((int)po[i].getEff());

			data.add(v);
		}
		return data;
	}

	private Vector<Vector<Object>> getAvgData(){
		String pos = position.getSelectedItem().toString();
		String leag = league.getSelectedItem().toString();
		String s = getSeasonStr(season.getSelectedItem().toString());

		PlayerDataPO[] po = logic.getSelect(TableUtility.getChPosition(pos), changeUnStr(leag),s);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i=0;i<po.length;i++){
			Vector<Object> v = new Vector<Object>();
			//			"序号","姓名","球队","参赛","先发","在场时间","得分","篮板","助攻","两双","进攻","防守","抢断","盖帽","失误","犯规","效率"
			v.addElement(po[i].getName());
			v.addElement(TableUtility.getShortChTeam(po[i].getTeamName()));
			v.addElement(po[i].getGP());
			v.addElement(po[i].getGS());
			v.addElement(getMinute(po[i].getMPG()));
			v.addElement(po[i].getPPG());
			v.addElement(po[i].getBPG());
			v.addElement(po[i].getAPG());
			v.addElement(po[i].getDouble());
			v.addElement(po[i].getOffPG());
			v.addElement(po[i].getDefPG());
			v.addElement(po[i].getStealPG());
			v.addElement(po[i].getRPG());
			v.addElement(po[i].getToPG());
			v.addElement(po[i].getFoulPG());
			v.addElement(po[i].getEff());

			data.add(v);
		}
		return data;
	}
	private Vector<Vector<Object>> getEffData(){
		String pos = position.getSelectedItem().toString();
		String leag = league.getSelectedItem().toString();
		String s = getSeasonStr(season.getSelectedItem().toString());

		PlayerDataPO[] po = logic.getSelect(TableUtility.getChPosition(pos), changeUnStr(leag),s);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for(int i=0;i<po.length;i++){
			Vector<Object> v = new Vector<Object>();
			//			"序号","姓名","球队","投篮命中率","三分命中率","罚球命中率",
			//			"GmSc效率","真实命中率","投篮效率","篮板率","进攻篮板率","防守篮板率",
			//			"助攻率","抢断率","盖帽率","失误率","使用率"
			v.addElement(po[i].getName());
			v.addElement(TableUtility.getShortChTeam(po[i].getTeamName()));
			v.addElement(po[i].getFieldGoalPercentage());
			v.addElement(po[i].getThreePGPercentage());
			v.addElement(po[i].getFTPercentage());
			v.addElement(po[i].getGmsc());
			v.addElement(po[i].getTruePercentage());
			v.addElement(po[i].getShootEff());
			v.addElement(po[i].getBackboardEff());
			v.addElement(po[i].getOffBEff());
			v.addElement(po[i].getDefBEff());
			v.addElement(po[i].getAssitEff());
			v.addElement(po[i].getStealEff());
			v.addElement(po[i].getRejectionEff());
			v.addElement(po[i].getToEff());
			v.addElement(po[i].getUseEff());

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

	class SubmitListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			PlayerStatsPanelNew.this.remove(jspAll);
			PlayerStatsPanelNew.this.remove(jspAvg);
			PlayerStatsPanelNew.this.remove(jspEff);

			all.setSelected(true);
			avg.setSelected(false);
			eff.setSelected(false);

			jspAll = factory.getTablePanePlayer(getHeader1(), getAllData());
			PlayerStatsPanelNew.this.add(jspAll);

			jspAvg = factory.getTablePanePlayer(getHeader1(), getAvgData());
			jspAvg.setVisible(false);
			PlayerStatsPanelNew.this.add(jspAvg);

			jspEff = factory.getTablePanePlayer(getHeader2(), getEffData());
			jspEff.setVisible(false);
			PlayerStatsPanelNew.this.add(jspEff);
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






	public static void main(String[] args){
		JFrame f = new JFrame();
		f.setSize(1000, 650);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new PlayerStatsPanelNew());
		f.setVisible(true);
	}


}
