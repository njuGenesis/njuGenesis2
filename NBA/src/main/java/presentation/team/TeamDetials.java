package presentation.team;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import bussinesslogic.player.PlayerLogic;
import bussinesslogic.team.TeamLogic;
import data.po.TeamDataPO;
import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.contenui.TableUtility;
import presentation.contenui.UIUtil;
import presentation.hotspot.SelectLabel;

public class TeamDetials extends BgPanel{
	
	private static final long serialVersionUID = 1L;
	private GLabel title;
	private SelectLabel tdMenu[];
	private TeamDataPO po;
	private BgPanel sonPanel;
	private TeamLogic teamLogic = new TeamLogic();
	private PlayerLogic playerLogic = new PlayerLogic();
	
	public TeamDetials(String shortName){
		super("");
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}

		this.po = teamLogic.GetBySN(shortName, playerLogic.getLatestSeason());
		
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		this.setBounds(0, 0, 1000, 650);
		this.setVisible(true);
		
		init();
	}
	
	private void init(){
		title = new GLabel("  "+TableUtility.getChTeam(po.getShortName()), new Point(26, 30), new Point(948, 52), this, true, 0, 25);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);
		
		tdMenu = new SelectLabel[5];
		tdMenu[0] = new SelectLabel("资料", new Point(26, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[1] = new SelectLabel("球员", new Point(26+188+2, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[2] = new SelectLabel("比赛", new Point(26+(188+2)*2, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[3] = new SelectLabel("数据", new Point(26+(188+2)*3, 83), new Point(188, 35), this, true, 0, 18);
		tdMenu[4] = new SelectLabel("对比", new Point(26+(188+2)*4, 83), new Point(188, 35), this, true, 0, 18);
		
		tdMenu[0].setSelected(true);
		TeamInfo info = new TeamInfo(TeamDetials.this.po);
		sonPanel = info;
		TeamDetials.this.add(sonPanel);
		
		tdMenu[0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[0].setSelected(true);
				TeamInfo info = new TeamInfo(TeamDetials.this.po);
				TeamDetials.this.remove(sonPanel);
				sonPanel = info;
				TeamDetials.this.add(sonPanel);
				repaint();
			}
		});
		tdMenu[1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[1].setSelected(true);
				TeamPlayer player = new TeamPlayer(TeamDetials.this.po);
				TeamDetials.this.remove(sonPanel);
				sonPanel = player;
				TeamDetials.this.add(sonPanel);
				repaint();
			}
		});
		
		tdMenu[2].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[2].setSelected(true);
				TeamMatch match = new TeamMatch(TeamDetials.this.po);
				TeamDetials.this.remove(sonPanel);
				sonPanel = match;
				TeamDetials.this.add(sonPanel);
				repaint();
			}
		});
		
		tdMenu[3].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[3].setSelected(true);
				TeamData data = new TeamData(TeamDetials.this.po);
				TeamDetials.this.remove(sonPanel);
				sonPanel = data;
				TeamDetials.this.add(sonPanel);
				repaint();
			}
		});
		
		tdMenu[4].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				for(int i=0;i<tdMenu.length;i++){
					tdMenu[i].setSelected(false);
				}
				tdMenu[4].setSelected(true);
				TeamCmp teamCmp = new TeamCmp(TeamDetials.this.po);
				TeamDetials.this.remove(sonPanel);
				sonPanel = teamCmp;
				TeamDetials.this.add(sonPanel);
				repaint();
			}
		});
	}
	@Override
	public void refreshUI(){
		if(this!=null){
			
//			sonPanel.refreshUI();
//			sonPanel.setVisible(true);
			this.removeAll();
//			this.add(sonPanel);
			this.init();
			
		}
	}
}
