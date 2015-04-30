package presentation.team;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import bussinesslogic.team.TeamLogic;
import data.po.TeamDataPO;
import presentation.component.BgPanel;
import presentation.component.GLabel;
import presentation.contenui.TurnController;
import presentation.mainui.StartUI;

public class TeamUI extends BgPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String bg = "img/team/teamAllBg.png";
	private GLabel[] team = new GLabel[30];
	private TurnController turnController;

	public TeamUI(){
		super(bg);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		super.setVisible(false);
		this.setLayout(null);
		turnController = new TurnController();
		init();
	}

	private void init(){
		ArrayList<TeamDataPO> poList = getTeamDataPOs();
		ArrayList<ArrayList<TeamDataPO>> teamDataPOArea = setTeamDataPOArea(poList);

		for(int i = 0; i<30; i++){
			if(i>=0&&i<=4){
				team[i] = new GLabel(getFileAddress(teamDataPOArea.get(0).get(i)), new Point(52, 71+i*41), new Point(147, 28), this, true, teamDataPOArea.get(0).get(i));//25, 25
			}else{
				if(i>=5&&i<=9){
					team[i] = new GLabel(getFileAddress(teamDataPOArea.get(1).get(i-5)), new Point(303, 71+(i-5)*41), new Point(147, 28), this, true, teamDataPOArea.get(1).get(i-5));
				}else{
					if(i>=10&&i<=14){
						team[i] = new GLabel(getFileAddress(teamDataPOArea.get(2).get(i-10)), new Point(52, 377+(i-10)*41), new Point(147, 28), this, true, teamDataPOArea.get(2).get(i-10));
					}else{
						if(i>=15&&i<=19){
							team[i] = new GLabel(getFileAddress(teamDataPOArea.get(3).get(i-15)), new Point(783, 71+(i-15)*41), new Point(147, 28), this, true, teamDataPOArea.get(3).get(i-15));
						}else{
							if(i>=20&&i<=24){
								team[i] = new GLabel(getFileAddress(teamDataPOArea.get(4).get(i-20)), new Point(532, 377+(i-20)*41), new Point(147, 28), this, true, teamDataPOArea.get(4).get(i-20));
							}else{
								if(i>=25&&i<=29){
									team[i] = new GLabel(getFileAddress(teamDataPOArea.get(5).get(i-25)), new Point(783, 377+(i-25)*41), new Point(147, 28), this, true, teamDataPOArea.get(5).get(i-25));
								}
							}
						}
					}
				}
			}
			team[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				GLabel button = (GLabel)e.getSource();
				StartUI.startUI.turn(turnController.turnToTeamDetials(button.po.getShortName()));
			}
		};

		for(int i = 0; i<30; i++){
			team[i].addMouseListener(mouseAdapter);
		}

	}

	private ArrayList<TeamDataPO> getTeamDataPOs(){
		TeamLogic t = new TeamLogic();
		return t.GetInfoBySeason("13-14");
	}

	private ArrayList<ArrayList<TeamDataPO>> setTeamDataPOArea(ArrayList<TeamDataPO> poList){
		ArrayList<TeamDataPO> Southeast = new ArrayList<TeamDataPO>();
		ArrayList<TeamDataPO> Central = new ArrayList<TeamDataPO>();
		ArrayList<TeamDataPO> Atlantic = new ArrayList<TeamDataPO>();
		ArrayList<TeamDataPO> Southwest = new ArrayList<TeamDataPO>();
		ArrayList<TeamDataPO> Northwest = new ArrayList<TeamDataPO>();
		ArrayList<TeamDataPO> Pacific = new ArrayList<TeamDataPO>();
		for(int i = 0; i<30; i++){
			if(poList.get(i).getArea().equals("Southeast")){
				Southeast.add(poList.get(i));
			}else{
				if(poList.get(i).getArea().equals("Central")){
					Central.add(poList.get(i));
				}else{
					if(poList.get(i).getArea().equals("Atlantic")){
						Atlantic.add(poList.get(i));
					}else{
						if(poList.get(i).getArea().equals("Southwest")){
							Southwest.add(poList.get(i));
						}else{
							if(poList.get(i).getArea().equals("Northwest")){
								Northwest.add(poList.get(i));
							}else{
								if(poList.get(i).getArea().equals("Pacific")){
									Pacific.add(poList.get(i));
								}
							}
						}
					}
				}
			}
		}
		ArrayList<ArrayList<TeamDataPO>> setAera = new ArrayList<ArrayList<TeamDataPO>>();
		setAera.add(Southeast);
		setAera.add(Central);
		setAera.add(Atlantic);
		setAera.add(Southwest);
		setAera.add(Northwest);
		setAera.add(Pacific);
		return setAera;
	}

	/*
	 * data:
	 * 球队的所有简称
	 */
	private String getFileAddress(TeamDataPO po){
		String fileAddress = "img/teamName/"+po.getShortName()+".png";
		return fileAddress;
	}
	
	@Override
	public void refreshUI(){
		if(this!=null){
			this.removeAll();
			this.init();
		}
	}
}
