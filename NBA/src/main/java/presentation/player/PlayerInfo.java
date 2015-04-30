package presentation.player;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.component.TeamImageAssist;
import presentation.contenui.TableUtility;
import presentation.contenui.TurnController;
import presentation.contenui.UIUtil;
import presentation.mainui.StartUI;
import data.po.PlayerDataPO;

public class PlayerInfo extends BgPanel{
	
	private static final long serialVersionUID = 1L;
	private static String file = "img/playerDetials/info.png";
	private TeamImageAssist assist;
	private GLabel playerPic, teamPic, number, position, height, weight, birthday, age, exp;
	private JTextArea school;
	private PlayerDataPO po;

	public PlayerInfo(PlayerDataPO po) {
		super(file);
		this.po = po;
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		this.setBounds(26, 120, 948, 530);
		this.setLayout(null);
		this.setVisible(true);
		init();
	}

	private void init(){
		assist = new TeamImageAssist();

		playerPic = new GLabel("img/action/"+po.getName()+".png", new Point(276, 2), new Point(330, 525), this, true);
		teamPic = new GLabel(assist.loadImageIcon("img/teams/"+po.getTeamName()+".svg", 150, 150), new Point(47, 42), new Point(150, 150), this, true);
		teamPic.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				teamPic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mousePressed(MouseEvent e){
				String teamName = po.getTeamName();
				TurnController turnController = new TurnController();
				StartUI.startUI.turn(turnController.turnToTeamDetials(teamName));
			}
		});
		number = new GLabel(po.getNumber(), new Point(163, 408), new Point(200, 25), this, true, 0, 18);
		position = new GLabel(TableUtility.getChPosition(po.getPosition())+" "+po.getPosition(), new Point(160, 438), new Point(200, 25), this, true, 0, 18);
		height = new GLabel(po.getHeight(), new Point(718, 147), new Point(200, 25), this, true, 0, 18);
		weight = new GLabel(String.valueOf(po.getWeight()), new Point(718, 180), new Point(200, 25), this, true, 0, 18);
		birthday = new GLabel(po.getBirth(), new Point(718, 213), new Point(200, 25), this, true, 0, 18);
		age = new GLabel(String.valueOf(po.getAge()), new Point(718, 244), new Point(200, 25), this, true, 0, 18);
		exp = new GLabel(String.valueOf(po.getExp()), new Point(718, 275), new Point(200, 25), this, true, 0, 18);
		school = new JTextArea();
		school.setEditable(false);
		school.setLineWrap(true);
		school.setWrapStyleWord(true);
		school.setBounds(747, 308, 200, 50);
		school.setText(po.getSchool());
		school.setFont(new Font("微软雅黑", 0, 18));
		school.setBorder(null);
		school.setBackground(UIUtil.bgWhite);
		school.setOpaque(false);
		this.add(school);
	}
	
	@Override
	public void refreshUI(){
		if(this!=null){
			this.removeAll();
			this.init();
		}
	}
}
