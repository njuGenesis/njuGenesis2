package presentation.player;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.sun.org.apache.xml.internal.security.Init;

import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.StyleScrollPane;
import presentation.component.StyleTable;
import presentation.contenui.UIUtil;
import data.po.PlayerDataPO;

public class PlayerData extends BgPanel{
	
	private static final long serialVersionUID = 1L;
	private PlayerDataPO[] pos;
	private StyleTable basicTable,totalTable,  pgTable, effTable;
	private StyleScrollPane basicSP,totalSP, pgSP, effSP;
	private JCheckBox checkBox1, checkBox2, checkBox3, checkBox4;
	private Rectangle rectangle;

	public PlayerData(PlayerDataPO[] pos) {
		super("");
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		this.pos = pos;
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(UIUtil.bgWhite);
		
		init();
	}

	private void init(){
		rectangle = new Rectangle(14, 164, 920, 380);

		basicSetting();
		totalSetting();
		pgSetting();
		effSetting();

		basicSP.setVisible(true);

		GLabel palyerPic = new GLabel("img/portrait/"+pos[pos.length-1].getName()+".png", new Point(50, 30), new Point(120, 97), this, true);
		GLabel ppg = new GLabel("场均得分:"+pos[pos.length-1].getPPG(), new Point(260, 25), new Point(200, 30), this, true, 0, 20);
		GLabel bpg = new GLabel("场均篮板:"+pos[pos.length-1].getBPG(), new Point(300, 60), new Point(200, 30), this, true, 0, 20);
		GLabel apg = new GLabel("场均助攻:"+pos[pos.length-1].getAPG(), new Point(340, 95), new Point(200, 30), this, true, 0, 20);

		checkBox1 = new JCheckBox("总览");
		checkBox1.setBounds(600, 115, 70, 30);
		checkBox1.setSelected(true);
		this.add(checkBox1);
		checkBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox1.isSelected()){
					checkBox2.setSelected(false);
					checkBox3.setSelected(false);
					checkBox4.setSelected(false);
					pgSP.setVisible(false);
					effSP.setVisible(false);
					totalSP.setVisible(false);
					basicSP.setVisible(true);
				}else{
					checkBox1.setSelected(true);
				}
			}
		});
		
		checkBox2 = new JCheckBox("总计");
		checkBox2.setBounds(670, 115, 70, 30);
		this.add(checkBox2);
		checkBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox2.isSelected()){
					checkBox1.setSelected(false);
					checkBox3.setSelected(false);
					checkBox4.setSelected(false);
					pgSP.setVisible(false);
					effSP.setVisible(false);
					basicSP.setVisible(false);
					totalSP.setVisible(true);
				}else{
					checkBox2.setSelected(true);
				}
			}
		});
		
		checkBox3 = new JCheckBox("场均");
		checkBox3.setBounds(740, 115, 70, 30);
		this.add(checkBox3);
		checkBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox3.isSelected()){
					checkBox1.setSelected(false);
					checkBox2.setSelected(false);
					checkBox4.setSelected(false);
					basicSP.setVisible(false);
					effSP.setVisible(false);
					totalSP.setVisible(false);
					pgSP.setVisible(true);
				}else{
					checkBox3.setSelected(true);
				}
			}
		});
		
		checkBox4 = new JCheckBox("效率");
		checkBox4.setBounds(810, 115, 70, 30);
		this.add(checkBox4);
		checkBox4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox4.isSelected()){
					checkBox1.setSelected(false);
					checkBox2.setSelected(false);
					checkBox3.setSelected(false);
					basicSP.setVisible(false);
					pgSP.setVisible(false);
					totalSP.setVisible(false);
					effSP.setVisible(true);
				}else{
					checkBox4.setSelected(true);
				}
			}
		});
	}
	
	private void totalSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");
		header.addElement("进攻篮板");header.addElement("防守篮板");header.addElement("总篮板");header.addElement("助攻");header.addElement("投篮");
		header.addElement("命中");header.addElement("三分");header.addElement("三分命中");header.addElement("罚球");header.addElement("罚球命中");
		header.addElement("进攻");header.addElement("防守");header.addElement("抢断");header.addElement("盖帽");header.addElement("失误");
		header.addElement("犯规");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(pos[pos.length-1-i].getSeason());
			vector.addElement(pos[pos.length-1-i].getOffb());
			vector.addElement(pos[pos.length-1-i].getDefb());
			vector.addElement(pos[pos.length-1-i].getBackboard());
			vector.addElement(pos[pos.length-1-i].getAssist());
			vector.addElement(pos[pos.length-1-i].getTotalFieldGoal());
			vector.addElement(pos[pos.length-1-i].getFieldGoal());
			vector.addElement(pos[pos.length-1-i].getTotalThreeGoal());
			vector.addElement(pos[pos.length-1-i].getThreeGoal());
			vector.addElement(pos[pos.length-1-i].getTotalFT());
			vector.addElement(pos[pos.length-1-i].getFT());
			vector.addElement(pos[pos.length-1-i].getOff());
			vector.addElement(pos[pos.length-1-i].getDef());
			vector.addElement(pos[pos.length-1-i].getSteal());
			vector.addElement(pos[pos.length-1-i].getRejection());
			vector.addElement(pos[pos.length-1-i].getTo());
			vector.addElement(pos[pos.length-1-i].getFoul());
			data.addElement(vector);
		}
		totalTable = new StyleTable();
		totalSP = new StyleScrollPane(totalTable);
		totalTable.tableSetting(totalTable, header, data, totalSP,  rectangle);
		totalTable.setSort();
		totalSP.setVisible(false);
		this.add(totalSP);
	}
	
	private void basicSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");
		header.addElement("上场数");header.addElement("先发场数");header.addElement("上场总时间");header.addElement("总得分");header.addElement("两双数");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(pos[pos.length-1-i].getSeason());
			vector.addElement(pos[pos.length-1-i].getGP());
			vector.addElement(pos[pos.length-1-i].getGS());
			vector.addElement(pos[pos.length-1-i].getMinutesOnField());
			vector.addElement(pos[pos.length-1-i].getPTS());
			vector.addElement(pos[pos.length-1-i].getDouble());
			data.addElement(vector);
		}
		
		basicTable = new StyleTable();
		basicSP = new StyleScrollPane(basicTable);
		basicTable.tableSetting(basicTable, header, data, basicSP, rectangle);
		basicTable.setSort();
		basicSP.setVisible(false);
		this.add(basicSP);
	}
	
	private void pgSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");header.addElement("上场时间");header.addElement("得分");
		header.addElement("篮板");header.addElement("助攻");header.addElement("进攻");header.addElement("防守");header.addElement("抢断");
		header.addElement("盖帽");header.addElement("失误");header.addElement("犯规");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(pos[pos.length-1-i].getSeason());
			vector.addElement(pos[pos.length-1-i].getMPG());
			vector.addElement(pos[pos.length-1-i].getPPG());
			vector.addElement(pos[pos.length-1-i].getBPG());
			vector.addElement(pos[pos.length-1-i].getAPG());
			vector.addElement(pos[pos.length-1-i].getOffPG());
			vector.addElement(pos[pos.length-1-i].getDefPG());
			vector.addElement(pos[pos.length-1-i].getStealPG());
			vector.addElement(pos[pos.length-1-i].getRPG());
			vector.addElement(pos[pos.length-1-i].getToPG());
			vector.addElement(pos[pos.length-1-i].getFoulPG());
			data.addElement(vector);
		}
		
		pgTable = new StyleTable();
		pgSP = new StyleScrollPane(pgTable); 
		pgTable.tableSetting(pgTable, header, data, pgSP,  rectangle);
		pgTable.setSort();
		pgSP.setVisible(false);
		this.add(pgSP);
	}
	
	private void effSetting(){
		final Vector<String> header = new Vector<String>();
		header.addElement("赛季");header.addElement("效率       ");header.addElement("GMSC");header.addElement("使用率");header.addElement("真实命中率");
		header.addElement("投篮命中率");header.addElement("三分命中率");header.addElement("罚篮命中率");header.addElement("投篮");header.addElement("篮板");
		header.addElement("进攻篮板");header.addElement("防守篮板");header.addElement("助攻");header.addElement("抢断");header.addElement("盖帽");
		header.addElement("失误率");
		
		final Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<pos.length;i++){
			Vector<Object> vector = new Vector<Object>();
			vector.addElement(pos[pos.length-1-i].getSeason());
			vector.addElement(pos[pos.length-1-i].getEff());
			vector.addElement(pos[pos.length-1-i].getGmsc());
			vector.addElement(pos[pos.length-1-i].getUseEff());
			vector.addElement(pos[pos.length-1-i].getTruePercentage());
			vector.addElement(pos[pos.length-1-i].getFieldGoalPercentage());
			vector.addElement(pos[pos.length-1-i].getThreePGPercentage());
			vector.addElement(pos[pos.length-1-i].getFTPercentage());
			vector.addElement(pos[pos.length-1-i].getShootEff());
			vector.addElement(pos[pos.length-1-i].getBackboardEff());
			vector.addElement(pos[pos.length-1-i].getOffBEff());
			vector.addElement(pos[pos.length-1-i].getDefBEff());
			vector.addElement(pos[pos.length-1-i].getAssitEff());
			vector.addElement(pos[pos.length-1-i].getStealEff());
			vector.addElement(pos[pos.length-1-i].getRejectionEff());
			vector.addElement(pos[pos.length-1-i].getToEff());
			data.addElement(vector);
		}
		
		effTable = new StyleTable();
		effSP = new StyleScrollPane(effTable); 
		effTable.tableSetting(effTable, header, data, effSP, rectangle);
		effTable.setSort();
		effSP.setVisible(false);
		this.add(effSP);
	}
	@Override
	public void refreshUI(){
		if(this!=null){
			this.removeAll();
			this.init();
		}
	}
	
}
