package bussinesslogic.match;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import assistance.GetFileData;
import bslogicService.MatchInfoService;
import bussinesslogic.team.TeamLogic;
import data.match.Matchdata;
import data.po.MatchDataPO;

public class MatchLogic implements MatchInfoService {
	Matchdata add = new Matchdata();

	/*
	 * private boolean isExit(){ return m.judge(); }
	 */

	public ArrayList<MatchDataPO> ini() {
		ArrayList<MatchDataPO> MatchList12 = new ArrayList<MatchDataPO>();
		ArrayList<MatchDataPO> MatchList13 = new ArrayList<MatchDataPO>();
		GetFileData MatchReader = new GetFileData();
		File[] fileList = MatchReader.getAllMathcFielName();
		for (int i = 0; i < fileList.length; i++) {
			if(MatchReader.detailMatch(fileList[i]).getSeason().equals("12-13")){
			MatchList12.add(calcuRound(MatchReader.detailMatch(fileList[i])));
			}
			if(MatchReader.detailMatch(fileList[i]).getSeason().equals("13-14")){
				MatchList13.add(calcuRound(MatchReader.detailMatch(fileList[i])));
				}
			
		}
		add.writeIn(MatchList12);
		add.writeIn(MatchList13);

		return null;
	}
	
	public void update(String filename){
		GetFileData MatchReader = new GetFileData();
		File file = new File(filename);
		ArrayList<MatchDataPO> matches = new ArrayList<MatchDataPO>();
		matches.add(calcuRound(MatchReader.detailMatch(file)));
		updateMatchInfo(matches);
		TeamLogic t = new TeamLogic();
		t.updateTeamInfo(matches);
	}
	
	public void updateMatchInfo(ArrayList<MatchDataPO> matches){
		for(int i=0;i<matches.size();i++){
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
		if(match.getPoints().equals("0-0")){
			match.setValid(false);;
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

   //获得所有的比赛
	public ArrayList<MatchDataPO> GetAllInfo() {
		return add.GetAllMatch();
	}
	
	  //获得一个赛季所有的比赛
		public ArrayList<MatchDataPO> GetAllInfo(String season) {
			return add.GetPartMatch(season);
		}
	
	//获得一个球队一段时间的所有比赛 ，date的格式为“13-14_01-01”    （赛季+“_”+日期）
	public ArrayList<MatchDataPO> GetInfo(String startDate, String endDate, String shotrName) {
		return add.GetPartMatch(startDate, endDate, shotrName);
	}
	
	//获得一段时间的所有比赛 ，date的格式为“13-14_01-01”    （赛季+“_”+日期）
		public ArrayList<MatchDataPO> GetDateMatch(String startDate, String endDate) {
			return add.GetDateMatch(startDate, endDate);
		}
	
	//获得一个球队一个赛季的所有比赛 ，season的格式为“13-14”
	public ArrayList<MatchDataPO> GetInfo(String shotrName, String season) {
		return add.GetPartMatch(shotrName, season);
	}
	
	//根据球员返回比赛
	public ArrayList<MatchDataPO> GetPlayerInfo(String playername, String season) {
		ArrayList<MatchDataPO> allinfo = GetAllInfo(season);
		ArrayList<MatchDataPO> res = new  ArrayList<MatchDataPO>();
		for(int i=0;i<allinfo.size();i++){
			for(int k=0;k<allinfo.get(i).getPlayers1().size();k++){
				if(allinfo.get(i).getPlayers1().get(k).getPlayername().equals(playername)){
					res.add(allinfo.get(i));
					break;
				}
			}
			for(int k=0;k<allinfo.get(i).getPlayers2().size();k++){
				if(allinfo.get(i).getPlayers2().get(k).getPlayername().equals(playername)){
					res.add(allinfo.get(i));
					break;
				}
			}
		}
		return res;
	}
	
	

	public static void main(String[] args) {
		System.out.println(MatchLogic.getTime());
		MatchLogic match = new MatchLogic();
		//match.ini();
		//System.out.println(match.GetInfo("12-13_01-01", "12-13_12-12", "HOU").size());
		//System.out.println(match.GetPlayerInfo("Al Horford", "13-14").size());
		//System.out.println(match.GetDateMatch("13-14_01-01", "13-14_01-01").size());
	
		System.out.println(match.GetAllInfo().size());
		
		//System.out.println(match.GetAllInfo().size());
	    System.out.println(MatchLogic.getTime());
	}

	public static long getTime() {
		Date d = new Date();
		return d.getTime();
	}

}
