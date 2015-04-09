package bussinesslogic.team;

import java.util.ArrayList;

import assistance.GetFileData;
import bslogicService.TeamInfoService;
import bussinesslogic.match.MatchLogic;
import bussinesslogic.player.PlayerLogic;
import data.po.MatchDataPO;
import data.po.PlayerDataPO;
import data.po.TeamDataPO;
import data.team.TeamData;

public class TeamLogic implements TeamInfoService {
	private ArrayList<TeamDataPO> Teams;
	// 储存球队的球员名
	private void saveTeamPlayer(PlayerDataPO[] players) {
		for (int i = 0; i < players.length; i++) {
			for (int k = 0; k < 30; k++) {
				if (players[i].getTeamName()
						.equals(Teams.get(k).getShortName())) {
					if (Teams.get(k).getPlayers() == null) {
						Teams.get(k).setPlayers("");
					}
					if (!Teams.get(k).getPlayers().contains(players[i].getName() )) {
						Teams.get(k).setPlayers(Teams.get(k).getPlayers() + players[i].getName() + ";");
					}
					System.out.println(Teams.get(k).getPlayers());
					break;
				}
			}
		}
	}

	// 球队名称，所在地等从teams文件里直接读取的信息
	public void initTeamData() {
		GetFileData MatchFileReader = new GetFileData();
		Teams = MatchFileReader.readTeamfile(); // 球队基本信息初始化
		MatchLogic matchLogic = new MatchLogic();
		ArrayList<MatchDataPO> Matches = matchLogic.GetAllInfo(); // 一个含有全部比赛基本信息的集合
		for (int i = 0; i < Matches.size(); i++) {
			calcuPoints(Matches.get(i).getPoints(), Matches.get(i)
					.getFirstteam(), Matches.get(i).getSecondteam());
		}
		for (int i = 0; i < Matches.size(); i++) {
			calcuRate(Matches.get(i), Matches.get(i).getFirstteam(), Matches
					.get(i).getSecondteam());
		}
		PlayerLogic getPlayers = new PlayerLogic();
		saveTeamPlayer(getPlayers.getAllInfo());

		TeamData add = new TeamData();
		add.WriteIn(Teams);
		System.out.println("team信息成功初始化！");
	}

	// 计算球队的比赛场数，总得分和均分
	private void calcuPoints(String points, String team1, String team2) {
		String[] point = points.split("-");
		for (int i = 0; i < Teams.size(); i++) {
			if (Teams.get(i).getShortName().equals(team1)) {
				Teams.get(i).setMatchNumber(Teams.get(i).getMatchNumber() + 1); // 比赛场数+1
				if (point[0].compareTo(point[1]) >= 0) {
					Teams.get(i).setWinMatch(Teams.get(i).getWinMatch() + 1);
				}
				Teams.get(i).setWR(
						Teams.get(i).getWinMatch()
								/ Teams.get(i).getMatchNumber());// 胜率

				Teams.get(i).setPTS(
						Teams.get(i).getPTS() + Integer.parseInt(point[0])); // 球队1总得分

				Teams.get(i).setPPG(
						Teams.get(i).getPTS() / Teams.get(i).getMatchNumber());// 均分

				Teams.get(i).setLPS(
						Teams.get(i).getLPS() + Integer.parseInt(point[1])); // 球队1总失分

				Teams.get(i).setLPG(
						Teams.get(i).getLPS() / Teams.get(i).getMatchNumber());// 均失分

			} else if (Teams.get(i).getShortName().equals(team2)) {
				Teams.get(i).setMatchNumber(Teams.get(i).getMatchNumber() + 1); // 比赛场数+1
				if (point[0].compareTo(point[1]) <= 0) {
					Teams.get(i).setWinMatch(Teams.get(i).getWinMatch() + 1);
				}
				Teams.get(i).setWR(
						Teams.get(i).getWinMatch()
								/ Teams.get(i).getMatchNumber());
				Teams.get(i).setPTS(
						Teams.get(i).getPTS() + Integer.parseInt(point[1]));// 球队2总得分
				Teams.get(i).setPPG(
						Teams.get(i).getPTS() / Teams.get(i).getMatchNumber());// 均分

				Teams.get(i).setLPS(
						Teams.get(i).getLPS() + Integer.parseInt(point[0])); // 球队2总失分

				Teams.get(i).setLPG(
						Teams.get(i).getLPS() / Teams.get(i).getMatchNumber());// 均失分
			}
		}
	}

	// 计算和回合数相关的rate   以及根据比赛单场信息汇总球队信息
	private void calcuRate(MatchDataPO match, String team1, String team2) {
		for (int i = 0; i < Teams.size(); i++) {
			if (Teams.get(i).getShortName().equals(team1)
					|| Teams.get(i).getShortName().equals(team2)) {
				if (Teams.get(i).getShortName().equals(team1)) {
					Teams.get(i).setOff(
							Teams.get(i).getOff() + match.getTeamround1()); // 进攻，防守回合总数

					Teams.get(i).setDef(
							Teams.get(i).getDef() + match.getTeamround2());
					Teams.get(i).setDefPG(
							Teams.get(i).getDef()
									/ Teams.get(i).getMatchNumber());
					Teams.get(i).setOffPG(
							Teams.get(i).getOff()
									/ Teams.get(i).getMatchNumber());

					Teams.get(i).setOffBackBoard(
							Teams.get(i).getOffBackBoard()
									+ match.getTeam1Off()); // 总前场篮板和对手前场篮板
					Teams.get(i).setOtherOffBoard(
							Teams.get(i).getOtherOffBoard()
									+ match.getTeam2Off());

					Teams.get(i).setDefBackBoard(
							Teams.get(i).getDefBackBoard()
									+ match.getTeam1Def()); // 总后场篮板和对手后场篮板
					Teams.get(i).setOtherDefBoard(
							Teams.get(i).getOtherDefBoard()
									+ match.getTeam2Def());
					
					
					Teams.get(i).setShootNumber((int) (Teams.get(i).getShootNumber()
							+ match.getShoot1())); // 投篮总数，场均数
					Teams.get(i).setShootNumberPG(Teams.get(i).getShootNumber()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setShootEffNumber(Teams.get(i).getShootEffNumber()
							+ match.getShootEff1()); // 投篮命中总数，场均数
					Teams.get(i).setShootEffNumberPG(Teams.get(i).getShootEffNumber()
							/ Teams.get(i).getMatchNumber());

					if ((Teams.get(i).getShootNumber() > 0)) {
						Teams.get(i).setShootEff(Teams.get(i).getShootEffNumber()
								/ Teams.get(i).getShootNumber()); // 投篮进球率
					}
					// -------------------------------------------------------------------------------------------------------

					Teams.get(i).setTPNumber((int) (Teams.get(i).getTPNumber() + match.getTPShoot1())); // 总三分投篮书，场均数
					Teams.get(i).setTPNumberPG(Teams.get(i).getTPNumber() / Teams.get(i).getMatchNumber());

					Teams.get(i).setTPEffNumber(Teams.get(i).getTPEffNumber() + match.getTPShootEff1());// 总三分命中书，场均数
					Teams.get(i).setTPEffNumberPG(Teams.get(i).getTPEffNumber()
							/ Teams.get(i).getMatchNumber());

					if ((Teams.get(i).getTPNumber() > 0)) {
						Teams.get(i).setTPEff(Teams.get(i).getTPEffNumber() / Teams.get(i).getTPNumber()); // 三分进球率
					}
					// -------------------------------------------------------------------------------------------------------

					Teams.get(i).setFTNumber((int) (Teams.get(i).getFTNumber() + match.getFT1())); // 总罚球投篮书，场均
					Teams.get(i).setFTNumberPG(Teams.get(i).getFTNumber() / Teams.get(i).getMatchNumber());

					Teams.get(i).setFTEffNumber(Teams.get(i).getFTEffNumber() + match.getFTShootEff1());// 总罚球命中书，场均数
					Teams.get(i).setFTEffNumberPG(Teams.get(i).getFTEffNumber()
							/ Teams.get(i).getMatchNumber());

					if ((Teams.get(i).getFTNumber() > 0)) {
						Teams.get(i).setFTEff(Teams.get(i).getFTEffNumber() / Teams.get(i).getFTNumber()); // 罚球进球率
					}
					// -------------------------------------------------------------------------------------------------------

					Teams.get(i).setOffBackBoardPG(Teams.get(i).getOffBackBoard()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setDefBackBoardPG(Teams.get(i).getDefBackBoard()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setBackBoard((int) (Teams.get(i).getBackBoard() + match.getTeam1bank())); // 总篮板数
					Teams.get(i).setBackBoardPG(Teams.get(i).getBackBoard() / Teams.get(i).getMatchNumber());

					Teams.get(i).setAssitNumber((int) (Teams.get(i).getAssitNumber() + match.getAss1())); // 总助攻数
					Teams.get(i).setAssitNumberPG(Teams.get(i).getAssitNumber()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setStealNumber((int) (Teams.get(i).getStealNumber() + match.getSteal1())); // 总抢断数
					Teams.get(i).setStealNumberPG(Teams.get(i).getStealNumber()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setRejection((int) (Teams.get(i).getRejection() + match.getRejection1())); // 总盖帽数
					Teams.get(i).setRejectionPG(Teams.get(i).getRejection() / Teams.get(i).getMatchNumber());

					Teams.get(i).setTo((int) (Teams.get(i).getTo() + match.getTo1())); // 总失误数
					Teams.get(i).setToPG(Teams.get(i).getTo() / Teams.get(i).getMatchNumber());

					Teams.get(i).setFoul((int) (Teams.get(i).getFoul() + match.getFoul1())); // 总犯规数
					Teams.get(i).setFoulPG(Teams.get(i).getFoul() / Teams.get(i).getMatchNumber());
					

				} else if (Teams.get(i).getShortName().equals(team2)) {
					Teams.get(i).setOff(
							Teams.get(i).getOff() + match.getTeamround2()); // 进攻，防守回合总数
					Teams.get(i).setDef(
							Teams.get(i).getDef() + match.getTeamround1());
					Teams.get(i).setDefPG(
							Teams.get(i).getDef()
									/ Teams.get(i).getMatchNumber());
					Teams.get(i).setOffPG(
							Teams.get(i).getOff()
									/ Teams.get(i).getMatchNumber());

					Teams.get(i).setOffBackBoard(
							Teams.get(i).getOffBackBoard()
									+ match.getTeam2Off()); // 总前场篮板和对手前场篮板
					Teams.get(i).setOtherOffBoard(
							Teams.get(i).getOtherOffBoard()
									+ match.getTeam1Off());

					Teams.get(i).setDefBackBoard(
							Teams.get(i).getDefBackBoard()
									+ match.getTeam2Def()); // 总后场篮板和对手后场篮板
					Teams.get(i).setOtherDefBoard(
							Teams.get(i).getOtherDefBoard()
									+ match.getTeam1Def());

					Teams.get(i).setShootNumber((int) (Teams.get(i).getShootNumber()
							+ match.getShoot2())); // 投篮总数，场均数
					Teams.get(i).setShootNumberPG(Teams.get(i).getShootNumber()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setShootEffNumber(Teams.get(i).getShootEffNumber()
							+ match.getShootEff2()); // 投篮命中总数，场均数
					Teams.get(i).setShootEffNumberPG(Teams.get(i).getShootEffNumber()
							/ Teams.get(i).getMatchNumber());

					if ((Teams.get(i).getShootNumber() > 0)) {
						Teams.get(i).setShootEff(Teams.get(i).getShootEffNumber()
								/ Teams.get(i).getShootNumber()); // 投篮进球率
					}
					// -------------------------------------------------------------------------------------------------------

					Teams.get(i).setTPNumber((int) (Teams.get(i).getTPNumber() + match.getTPShoot2())); // 总三分投篮书，场均数
					Teams.get(i).setTPNumberPG(Teams.get(i).getTPNumber() / Teams.get(i).getMatchNumber());

					Teams.get(i).setTPEffNumber(Teams.get(i).getTPEffNumber() + match.getTPShootEff2());// 总三分命中书，场均数
					Teams.get(i).setTPEffNumberPG(Teams.get(i).getTPEffNumber()
							/ Teams.get(i).getMatchNumber());

					if ((Teams.get(i).getTPNumber() > 0)) {
						Teams.get(i).setTPEff(Teams.get(i).getTPEffNumber() / Teams.get(i).getTPNumber()); // 三分进球率
					}
					// -------------------------------------------------------------------------------------------------------

					Teams.get(i).setFTNumber((int) (Teams.get(i).getFTNumber() + match.getFT2())); // 总罚球投篮书，场均
					Teams.get(i).setFTNumberPG(Teams.get(i).getFTNumber() / Teams.get(i).getMatchNumber());

					Teams.get(i).setFTEffNumber(Teams.get(i).getFTEffNumber() + match.getFTShootEff2());// 总罚球命中书，场均数
					Teams.get(i).setFTEffNumberPG(Teams.get(i).getFTEffNumber()
							/ Teams.get(i).getMatchNumber());

					if ((Teams.get(i).getFTNumber() > 0)) {
						Teams.get(i).setFTEff(Teams.get(i).getFTEffNumber() / Teams.get(i).getFTNumber()); // 罚球进球率
					}
					// -------------------------------------------------------------------------------------------------------

					Teams.get(i).setOffBackBoardPG(Teams.get(i).getOffBackBoard()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setDefBackBoardPG(Teams.get(i).getDefBackBoard()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setBackBoard((int) (Teams.get(i).getBackBoard() + match.getTeam2bank())); // 总篮板数
					Teams.get(i).setBackBoardPG(Teams.get(i).getBackBoard() / Teams.get(i).getMatchNumber());

					Teams.get(i).setAssitNumber((int) (Teams.get(i).getAssitNumber() + match.getAss2())); // 总助攻数
					Teams.get(i).setAssitNumberPG(Teams.get(i).getAssitNumber()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setStealNumber((int) (Teams.get(i).getStealNumber() + match.getSteal2())); // 总抢断数
					Teams.get(i).setStealNumberPG(Teams.get(i).getStealNumber()
							/ Teams.get(i).getMatchNumber());

					Teams.get(i).setRejection((int) (Teams.get(i).getRejection() + match.getRejection2())); // 总盖帽数
					Teams.get(i).setRejectionPG(Teams.get(i).getRejection() / Teams.get(i).getMatchNumber());

					Teams.get(i).setTo((int) (Teams.get(i).getTo() + match.getTo2())); // 总失误数
					Teams.get(i).setToPG(Teams.get(i).getTo() / Teams.get(i).getMatchNumber());

					Teams.get(i).setFoul((int) (Teams.get(i).getFoul() + match.getFoul2())); // 总犯规数
					Teams.get(i).setFoulPG(Teams.get(i).getFoul() / Teams.get(i).getMatchNumber());
					
				}
				
				Teams.get(i).setStealEff(Teams.get(i).getStealNumber() * 100 / Teams.get(i).getDef()); // 抢断效率
				Teams.get(i).setAssistEff(Teams.get(i).getAssitNumber() * 100 / Teams.get(i).getOff()); // 助攻效率
			
				Teams.get(i).setOffPG(
						Teams.get(i).getOff() / Teams.get(i).getMatchNumber());// 进攻场均回合数

				Teams.get(i).setOffEff(
						Teams.get(i).getPTS() * 100 / Teams.get(i).getOff()); // 进攻、防守效率
				Teams.get(i).setDefEff(
						Teams.get(i).getLPS() * 100 / Teams.get(i).getDef());

				Teams.get(i).setOffBackBoardEff(
						Teams.get(i).getOffBackBoard()
								/ (Teams.get(i).getOffBackBoard() + Teams
										.get(i).getOtherDefBoard()));
				Teams.get(i).setDefBackBoardEff(
						Teams.get(i).getDefBackBoard()
								/ (Teams.get(i).getDefBackBoard() + Teams
										.get(i).getOtherOffBoard()));
				Teams.get(i).setBackBoardEff(
						Teams.get(i).getOffBackBoardEff()
								+ Teams.get(i).getDefBackBoardEff());
			}

		}
	}

	
	public ArrayList<TeamDataPO> GetAllInfo() {
		TeamData t =new TeamData();
		return t.GetAllInfo();
	}

	public TeamDataPO GetInfo(String name) {
		TeamData t =new TeamData();
		return t.ReadOut(name);
	}

	public TeamDataPO GetBySN(String Shortname) {
		TeamData t =new TeamData();
		return t.ReadOut(Shortname);
	}

	public static void main(String[] args) {
		TeamLogic team = new TeamLogic();
		MatchLogic match = new MatchLogic();
		team.initTeamData();
		System.out.println(team.GetAllInfo().size());
		System.out.println(match.GetAllInfo().size());
	}



}
