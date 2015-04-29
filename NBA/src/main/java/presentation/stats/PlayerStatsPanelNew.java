package presentation.stats;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import assistance.NewFont;
import presentation.component.BgPanel;
import presentation.component.GComboBox;
import presentation.component.GLabel;
import presentation.component.GTable;
import presentation.contenui.UIUtil;

public class PlayerStatsPanelNew extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String bg = "";
	
	public GTable table;
	public JScrollPane jsp;

	public JLabel title;
	
	JComboBox<String> seasonChooser;

	public JComboBox<String> position;
	public JComboBox<String> league;
	public JComboBox<String> dataType;

	public String[] positionItem = {"全部位置","后卫","前锋","中锋"}; 
	public String[] leagueItem = {"全部联盟","东-大西洋分区","东-中央分区","东-东南分区","西-西北分区","西-太平洋分区","西-西南分区"};
//	public String[] statsItem = {"得分","篮板","助攻","得分/篮板/助攻","盖帽","抢断","犯规","失误","分钟","效率","投篮","三分","罚球","两双"};
	public String[] dataTypeItem = {"场均","总数"};

	public JButton submit;
	
	
	String[] head = {"序号","姓名","球队","参赛","先发","在场时间","得分","篮板","助攻","投篮命中率","三分命中率",
			"罚球命中率","两双","进攻","防守","抢断","盖帽","失误","犯规","效率","GmSc效率",
			"真实命中率","投篮效率","篮板率","进攻篮板率","防守篮板率","助攻率","抢断率","盖帽率","失误率","使用率"};
	
	String[] header1 = {"序号","姓名","球队","参赛","先发","在场时间","得分","篮板","助攻","两双","进攻","防守","抢断","盖帽","失误","犯规","效率"};
	String[] header2 = {"序号","姓名","球队","投篮命中率","三分命中率","罚球命中率","GmSc效率","真实命中率","投篮效率","篮板率","进攻篮板率","防守篮板率","助攻率","抢断率","盖帽率","失误率","使用率"};
	
	JCheckBox all;  //总数
	JCheckBox avg;  //场均
	JCheckBox eff;  //效率
	
	
	public PlayerStatsPanelNew() {
		super(bg);
		
		this.setBounds(50, 0, 950, 650);
		this.setLayout(null);
		this.setOpaque(false);
		
		String[] seasons = {"12-13赛季","13-14赛季"};
		seasonChooser = new JComboBox<String>(seasons);
		seasonChooser.setBounds(800-this.getX(), 42, 120, 30);
//		seasonChooser.addActionListener(new SeasonListener());
		this.add(seasonChooser);
		
		title = new GLabel("   进步最快球员",new Point(80-this.getX(),30),new Point(890,52),this,true,0,24);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);
		
		position = new GComboBox(positionItem);
		position.setBounds(80, 100, 150, 30);
		position.setFont(NewFont.ComboBoxFont);
		this.add(position);

		league = new GComboBox(leagueItem);
		league.setBounds(280, 100, 150, 30);
		league.setBackground(new Color(250,250,250));
		league.setFont(NewFont.ComboBoxFont);
		this.add(league);

		dataType = new GComboBox(dataTypeItem);
		dataType.setBounds(480, 100, 150, 30);
		dataType.setFont(NewFont.ComboBoxFont);
		this.add(dataType);

		submit = UIUtil.getSelectButton();
		submit.setBounds(820, 100, 150, 30);
//		submit.addMouseListener(new SubmitListener());
		this.add(submit);
		
		all = new JCheckBox();
		
	}

}
