package presentation.contenui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import data.po.PlayerDataPO;
import data.po.TeamDataPO;
import assistance.ContentItem;
import bussinesslogic.player.PlayerLogic;
import bussinesslogic.team.TeamLogic;
import presentation.component.GButton;
import presentation.component.GLabel;

public class PlayerBasic extends ContentPanel{
	
	private GButton team;
	private GLabel palyer;
	private GLabel name;
	private GLabel number;
	private GLabel position;
	private GLabel height;
	private GLabel weight;
	private GLabel birthday;
	private GLabel age;
	private GLabel exp;
	private GLabel school;
	private GButton back;
	
	protected static String url = "img/Player/playerBasic.png";

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame. setBounds(0, 0, 1250, 650);
		frame.setLayout(null);
		
		PlayerBasic p = new PlayerBasic("");
		
		frame.getContentPane().add(p.getContentPanel());
		frame.setVisible(true);
	}
	
	/*
	 * data:
	 * 0:球队名字//图片地址
	 * 1:球员名字//图片地址
	 * 2:姓名
	 * 3:球衣号码
	 * 4:位置
	 * 5:身高
	 * 6:体重
	 * 7:生日
	 * 8:年龄
	 * 9:球龄
	 * 10:毕业院校
	 */
	public PlayerBasic(String key) {
		super(url);
		
		final String[] data = getData(key);

		team = new GButton("img/teampng/"+data[0]+".png", new Point(0, 0), new Point(250, 250), this.getContentPanel(), true);
		palyer = new GLabel("img/action/"+data[1]+".png", new Point(250, 0), new Point(330, 525), this.getContentPanel(), true);
		name = new GLabel(data[2], new Point(646, 131), new Point(210, 25), this.getContentPanel(), true, 1, 15);//差31
		number = new GLabel(data[3], new Point(681, 162), new Point(210, 25), this.getContentPanel(), true, 1, 15);
		position = new GLabel(data[4], new Point(646, 193), new Point(210, 25), this.getContentPanel(), true, 1, 15);
		height = new GLabel(data[5], new Point(646, 224), new Point(210, 25), this.getContentPanel(), true, 1, 15);
		weight = new GLabel(data[6], new Point(646, 255), new Point(210, 25), this.getContentPanel(), true, 1, 15);
		birthday = new GLabel(data[7], new Point(646, 286), new Point(210, 25), this.getContentPanel(), true, 1, 15);
		age = new GLabel(data[8], new Point(646, 317), new Point(210, 25), this.getContentPanel(), true, 1, 15);
		exp = new GLabel(data[9], new Point(646, 348), new Point(210, 25), this.getContentPanel(), true, 1, 15);
		school = new GLabel(data[10], new Point(681, 379), new Point(210, 25), this.getContentPanel(), true, 1, 15);

		back = new GButton("img/player/back.png", new Point(749, 460), new Point(61, 61), this.getContentPanel(), true);

		back.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent arg0) {
				back.setIcon(new ImageIcon("img/player/back.png"));
			}
			public void mouseEntered(MouseEvent arg0) {
				back.setIcon(new ImageIcon("img/player/backAction.png"));
			}
			public void mouseClicked(MouseEvent arg0) {
				ContentController con = new ContentController();
				con.changeContent(ContentItem.PlayerSearch);
			}
		});
		
		team.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
				team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent arg0) {
				ContentController con = new ContentController();
				TeamLogic l = new TeamLogic();
				//TeamDataPO po = l.GetBySN(data[0]);
				//con.changeToTeamDetails(po);
			}
		});
	}
	
	
	private String[] getData(String palyerName){
		PlayerLogic l = new PlayerLogic();
		//PlayerDataPO po = l.getInfo(palyerName);
//		String[] data = {po.getTeamName(), po.getName(), po.getName(), po.getNumber(), po.getPosition(), po.getHeight(), 
//				String.valueOf(po.getWeight()), po.getBirth(), String.valueOf(po.getAge()), String.valueOf(po.getExp()), po.getSchool()};
 		return null;
	}

}
