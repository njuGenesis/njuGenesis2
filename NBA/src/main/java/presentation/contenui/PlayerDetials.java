package presentation.contenui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.hotspot.SelectLabel;
import data.po.PlayerDataPO;
import data.po.TeamDataPO;

public class PlayerDetials extends BgPanel{
	private static final long serialVersionUID = 1L;
	private GLabel title;
	private SelectLabel tdMenu[];
	private PlayerDataPO po;
	private BgPanel sonPanel;
	
	public PlayerDetials(PlayerDataPO po){
		super("");
		this.po = po;
		
		this.setLayout(null);
		this.setBackground(UIUtil.bgWhite);
		this.setBounds(0, 0, 1000, 650);
		this.setVisible(true);
		
		title = new GLabel("  "+po.getName(), new Point(27, 30), new Point(946, 52), this, true, 0, 25);
		title.setOpaque(true);
		title.setBackground(UIUtil.nbaBlue);
		title.setForeground(UIUtil.bgWhite);
		
		tdMenu = new SelectLabel[5];
		tdMenu[0] = new SelectLabel("资料", new Point(27, 83), new Point(235, 35), this, true, 0, 18);
		tdMenu[1] = new SelectLabel("数据", new Point(27+235+2, 83), new Point(235, 35), this, true, 0, 18);
		tdMenu[2] = new SelectLabel("比赛", new Point(27+(235+2)*2, 83), new Point(235, 35), this, true, 0, 18);
		tdMenu[3] = new SelectLabel("对比", new Point(27+(235+2)*3, 83), new Point(235, 35), this, true, 0, 18);
		
		tdMenu[0].setSelected(true);
		//Info info = new Info(PlayerDetials.this.po);
		//sonPanel = info;
		//PlayerDetials.this.add(sonPanel);
		
//		tdMenu[0].addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				for(int i=0;i<tdMenu.length;i++){
//					tdMenu[i].setSelected(false);
//				}
//				tdMenu[0].setSelected(true);
//				Info info = new Info(PlayerDetials.this.po);
//				PlayerDetials.this.remove(sonPanel);
//				sonPanel = info;
//				PlayerDetials.this.add(sonPanel);
//				repaint();
//			}
//		});
//		tdMenu[1].addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				for(int i=0;i<tdMenu.length;i++){
//					tdMenu[i].setSelected(false);
//				}
//				tdMenu[1].setSelected(true);
//				Player player = new Player(PlayerDetials.this.po);
//				PlayerDetials.this.remove(sonPanel);
//				sonPanel = player;
//				PlayerDetials.this.add(sonPanel);
//				repaint();
//			}
//		});
//		
//		tdMenu[2].addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				for(int i=0;i<tdMenu.length;i++){
//					tdMenu[i].setSelected(false);
//				}
//				tdMenu[2].setSelected(true);
//				Match match = new Match(PlayerDetials.this.po);
//				PlayerDetials.this.remove(sonPanel);
//				sonPanel = match;
//				PlayerDetials.this.add(sonPanel);
//				repaint();
//			}
//		});
//		
//		tdMenu[3].addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				for(int i=0;i<tdMenu.length;i++){
//					tdMenu[i].setSelected(false);
//				}
//				tdMenu[3].setSelected(true);
//				Data data = new Data(PlayerDetials.this.po);
//				PlayerDetials.this.remove(sonPanel);
//				sonPanel = data;
//				PlayerDetials.this.add(sonPanel);
//				repaint();
//			}
//		});
//		
//		tdMenu[4].addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				for(int i=0;i<tdMenu.length;i++){
//					tdMenu[i].setSelected(false);
//				}
//				tdMenu[4].setSelected(true);
//				repaint();
//			}
//		});
	}
}
