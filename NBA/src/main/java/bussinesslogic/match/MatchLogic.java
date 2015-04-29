package bussinesslogic.match;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import assistance.GetFileData;
import bslogicService.MatchInfoService;
import bussinesslogic.team.TeamLogic;
import data.match.Matchdata;
import data.po.MatchDataPO;
import data.po.Match_PlayerPO;

public class MatchLogic implements MatchInfoService {
	Matchdata add = new Matchdata();
	public static ArrayList<MatchDataPO> matches = new ArrayList<MatchDataPO>();

	/*
	 * private boolean isExit(){ return m.judge(); }
	 */

	public ArrayList<MatchDataPO> ini() {
		ArrayList<MatchDataPO> MatchList12 = new ArrayList<MatchDataPO>();
		ArrayList<MatchDataPO> MatchList13 = new ArrayList<MatchDataPO>();
		GetFileData MatchReader = new GetFileData();
		File[] fileList = MatchReader.getAllMathcFielName();
		for (int i = 0; i < fileList.length; i++) {
			if (MatchReader.detailMatch(fileList[i]).getSeason()
					.equals("12-13")) {
				if (!MatchReader.detailMatch(fileList[i]).getPoints()
						.equals("0-0")) {
					MatchList12.add(calcuRound(MatchReader
							.detailMatch(fileList[i])));
				}
			}

			if (MatchReader.detailMatch(fileList[i]).getSeason()
					.equals("13-14")) {
				if (!MatchReader.detailMatch(fileList[i]).getPoints()
						.equals("0-0")) {
					MatchList13.add(calcuRound(MatchReader
							.detailMatch(fileList[i])));
				}
			}
		}
		System.out.println(MatchLogic.getTime());
		matches.addAll(MatchList12);
		matches.addAll(MatchList13);
		TeamLogic t = new TeamLogic();
		t.initTeamData();
		if (MatchList12.size() != 0) {
			add.writeIn(MatchList12);
		}
		if (MatchList13.size() != 0) {
			add.writeIn(MatchList13);
		}

		return null;
	}

	public void update(String filename) {
		GetFileData MatchReader = new GetFileData();
		File file = new File(filename);
		ArrayList<MatchDataPO> matches = new ArrayList<MatchDataPO>();
		matches.add(calcuRound(MatchReader.detailMatch(file)));
		updateMatchInfo(matches);
		TeamLogic t = new TeamLogic();
		t.updateTeamInfo(matches);
	}

	public void updateMatchInfo(ArrayList<MatchDataPO> matches) {
		for (int i = 0; i < matches.size(); i++) {
			calcuRound(matches.get(i));
		}
		add.writeIn(matches);
	}

	@SuppressWarnings("unused")
	private void addMatch(MatchDataPO match) {
		add.writeIn(calcuRound(match));
	}

	// 计算比赛双方进攻回合数
	private MatchDataPO calcuRound(MatchDataPO match) {
		if (match.getPoints().equals("0-0")) {
			match.setValid(false);
			;
			return match;
		}
		match.setTeamround1(match.getShoot1() + 0.4 * match.getFT1() - 1.07
				* match.getTeam1Off() * match.getLostSH1()
				/ (match.getTeam1Off() + match.getTeam2Def()) + 1.07
				* match.getTo1());

		match.setTeamround2(match.getShoot2() + 0.4 * match.getFT2() - 1.07
				* match.getTeam2Off() * match.getLostSH2()
				/ (match.getTeam2Off() + match.getTeam1Def()) + 1.07
				* match.getTo2());
		return match;
	}

	// 获得所有的比赛
	public ArrayList<MatchDataPO> GetAllInfo() {
		return add.GetAllMatch();
	}

	// 获得一个赛季所有的比赛
	public ArrayList<MatchDataPO> GetAllInfo(String season) {
		return add.GetPartMatch(season);
	}

	// 获得一个球队一段时间的所有比赛 ，date的格式为“13-14_01-01” （赛季+“_”+日期）
	public ArrayList<MatchDataPO> GetInfo(String startDate, String endDate,
			String shotrName) {
		return add.GetPartMatch(startDate, endDate, shotrName);
	}

	// 获得一段时间的所有比赛 ，date的格式为“13-14_01-01” （赛季+“_”+日期）
	public ArrayList<MatchDataPO> GetDateMatch(String startDate, String endDate) {
		return add.GetDateMatch(startDate, endDate);
	}

	// 获得一个球队一个赛季的所有比赛 ，season的格式为“13-14”
	public ArrayList<MatchDataPO> GetInfo(String shotrName, String season) {
		return add.GetPartMatch(shotrName, season);
	}

	// 获得一个球队一个赛季的所有比赛 ，season的格式为“13-14”
	public ArrayList<MatchDataPO> GetInfo(String teamShotrName) {
		return add.GetInfo(teamShotrName);
	}

	// 根据球员和赛季返回比赛
	public ArrayList<MatchDataPO> GetPlayerInfo(String playername, String season) {
		ArrayList<MatchDataPO> allinfo = GetAllInfo(season);
		ArrayList<MatchDataPO> res = new ArrayList<MatchDataPO>();
		for (int i = 0; i < allinfo.size(); i++) {
			for (int k = 0; k < allinfo.get(i).getPlayers1().size(); k++) {
				if (allinfo.get(i).getPlayers1().get(k).getPlayername()
						.equals(playername)) {
					res.add(allinfo.get(i));
					break;
				}
			}
			for (int k = 0; k < allinfo.get(i).getPlayers2().size(); k++) {
				if (allinfo.get(i).getPlayers2().get(k).getPlayername()
						.equals(playername)) {
					res.add(allinfo.get(i));
					break;
				}
			}
		}
		return res;
	}

	// 根据球员返回比赛
	public ArrayList<Match_PlayerPO> GetPlayerInfo(String playername) {
		ArrayList<MatchDataPO> allinfo = GetAllInfo();
		ArrayList<Match_PlayerPO> res = new ArrayList<Match_PlayerPO>();
		for (int i = 0; i < allinfo.size(); i++) {
			for (int k = 0; k < allinfo.get(i).getPlayers1().size(); k++) {
				if (allinfo.get(i).getPlayers1().get(k).getPlayername()
						.equals(playername)) {
					res.add(allinfo.get(i).getPlayers1().get(k));
					break;
				}
			}
			for (int k = 0; k < allinfo.get(i).getPlayers2().size(); k++) {
				if (allinfo.get(i).getPlayers2().get(k).getPlayername()
						.equals(playername)) {
					res.add(allinfo.get(i).getPlayers2().get(k));
					break;
				}
			}
		}
		return res;
	}

	/*
	 * //
	 * string[0]是赛季（格式13-14），string[1]是日期（格式01-01）,string[3]是赛季_日期（格式13-14_01-
	 * 01） public String[] getDate(){ String date=""; String[] res=new
	 * String[3];
	 * 
	 * ArrayList<MatchDataPO> allMatches =GetAllInfo();
	 * date=allMatches.get(0).getDate(); for(int i=1;i<allMatches.size();i++){
	 * 
	 * if(allMatches.get(i).getDate().compareTo(date)>0){
	 * date=allMatches.get(i).getDate(); } } res[0]=date.split("_")[0];
	 * res[1]=date.split("_")[1]; res[2]=res[0]+"_"+res[1]; return res; }
	 */

	public static void main(String[] args) {
		System.out.println(MatchLogic.getTime());
		MatchLogic match = new MatchLogic();
		// System.out.println(match.GetInfo("HOU").size());
		 match.ini();
		// match.getDate();
		// System.out.println(match.getDate()[2]);
		/*
		 * match.ini(); TeamLogic team = new TeamLogic(); team.initTeamData();
		 */
		/*
		 * ArrayList<Match_PlayerPO> res = match.GetPlayerInfo("CJ Miles");
		 * System.out.println(res.size()); for(int i=0;i<res.size();i++){
		 * System.
		 * out.println(res.get(i).getPlayername()+" "+res.get(i).getData()); }
		 */
		System.out.println(MatchLogic.getTime());
	}

	public static long getTime() {
		Date d = new Date();
		return d.getTime();
	}

}
