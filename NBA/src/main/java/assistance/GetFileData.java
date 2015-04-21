package assistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import data.po.MatchDataPO;
import data.po.Match_PlayerPO;
import data.po.TeamDataPO;

public class GetFileData {
	boolean isjoin = false;
	String MatchFileName ="./迭代一数据/matches";  
	String Teamfilename = "./迭代一数据/teams/teams";    

	public String readPlayerfile(String filename) {
		String res = "";
		try {
			// System.out.println("hi"+filename);
			File f = new File(filename);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String data = br.readLine();// 一次读入一行，直到读入null为文件结束
			// res = res + data + '\n';
			isjoin = false;
			while (data != null) {
				// System.out.println(data+";");

				if (isjoin == false) {

					isjoin = true;
				} else {
					data = data.trim();
					// System.out.println(data+";");
					data = getSubPlayerData(data);
					if (data.equals("")) {
						data = "null";
					}

					res = res + data + '\n';
					isjoin = false;
				}
				data = br.readLine(); // 接着读下一行
				// res = res + data + '\n';
			}
			br.close();
			// System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getSubPlayerData(String info) {
		int i = 0;
		for (int j = 0; j < info.length(); j++) {
			if (info.substring(j, j + 1).equals("│")) {
				i = j + 1;
				// System.out.println("hi");
				break;
			}
		}
		String res = info.substring(i, info.length() - 1).trim();
		return res;
	}

	public MatchDataPO readMatchfile(String filename) {// 读取match信息生成matchdatapo
		boolean jud = false;
		MatchDataPO res = new MatchDataPO();
		try {
			File f = new File(filename);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String data = br.readLine();// 一次读入一行，直到读入null为文件结束
			
			String[] temp = data.split(";");		
			res.setDate(temp[0]);
			res.setFirstteam(temp[1].split("-")[0]);
			res.setSecondteam(temp[1].split("-")[1]);
			res.setPoints(temp[2]);
			if (temp[2].split("-")[0].compareTo(temp[2].split("-")[1]) > 0) {
				res.setWinner(res.getFirstteam());
			} else {
				res.setWinner(res.getSecondteam());
			}
			data = br.readLine();
			res.setFirst_pts(data.split(";")[0]);
			res.setSecond_pts(data.split(";")[1]);
			res.setThird_pts(data.split(";")[2]);
			res.setForth_pts(data.split(";")[3]);
			data = br.readLine();
			while (data != null) {
				if ((data.equals(res.getFirstteam()))) {
					jud = false;
				}
				if (data.equals(res.getSecondteam())) {
					jud = true;
				}
				if (jud == false) {
					res.firstTeamInfo.add(data);
				} else {
					res.secondTeamInfo.add(data);
				}
				data = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(filename);
		}
		return res;
	}

	public MatchDataPO detailMatch(File filename) {
		MatchDataPO res = new MatchDataPO();
		Match_PlayerPO player = new Match_PlayerPO();
		try {
			File f = filename;
			res.setSeason(f.getName().split("_")[0]);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String data = br.readLine();// 一次读入一行，直到读入null为文件结束
			String[] temp = data.split(";");
			res.setDate(res.getSeason()+"_"+temp[0]);
			res.setFirstteam(temp[1].split("-")[0]);
			res.setSecondteam(temp[1].split("-")[1]);
			res.setPoints(temp[2]);
			if(res.getPoints().equals("0-0")){
				return res;
			}
			if (temp[2].split("-")[0].compareTo(temp[2].split("-")[1]) > 0) {
				res.setWinner(res.getFirstteam());
			} else {
				res.setWinner(res.getSecondteam());
			}
			data = br.readLine();
			res.setFirst_pts(data.split(";")[0]);
			res.setSecond_pts(data.split(";")[1]);
			res.setThird_pts(data.split(";")[2]);
			res.setForth_pts(data.split(";")[3]);
			if (data.length() > 25) { // 判断是否存在加时
				res.setFifth_pts(data.split(";")[4]);
			} else {
				res.setFifth_pts("null");
			}
			if (data.length() > 31) {
				res.setSixth_pts(data.split(";")[5]);
			} else {
				res.setSixth_pts("null");
			}
			if (data.length() > 37) {
				res.setSeventh_pts(data.split(";")[6]);
			} else {
				res.setSeventh_pts("null");
			}
			data = br.readLine(); // 跳过两行数据
			while ((data = br.readLine()).contains(";")) {
				String[] detail = data.split(";");
				player=new Match_PlayerPO();
				player.setTeam(res.getFirstteam());
				player.setPlayername((detail[0]));
				player.setState((detail[1]));
				player.setTime((detail[2]));
				player.setShootEff(Integer.parseInt(detail[3]));
				res.setShootEff1(res.getShootEff1()
						+ Integer.parseInt(detail[3]));
				player.setShoot(Integer.parseInt(detail[4]));
				res.setShoot1(res.getShoot1() + Integer.parseInt(detail[4]));
				player.setLostSH(Integer.parseInt(detail[4])
						- Integer.parseInt(detail[3]));
				res.setLostSH1(res.getLostSH1() + Integer.parseInt(detail[4])
						- Integer.parseInt(detail[3]));
				player.setTPShootEff(Integer.parseInt(detail[5]));
				res.setTPShootEff1(res.getTPShootEff1()
						+ Integer.parseInt(detail[5]));
				player.setTPShoot(Integer.parseInt(detail[6]));
				res.setTPShoot1(res.getTPShoot1() + Integer.parseInt(detail[6]));
				player.setFTShootEff(Integer.parseInt(detail[7]));
				res.setFTShootEff1(res.getFTShootEff1()
						+ Integer.parseInt(detail[7]));
				player.setFT(Integer.parseInt(detail[8]));
				res.setFT1(res.getFT1() + Integer.parseInt(detail[8]));
				player.setBankOff(Integer.parseInt(detail[9]));
				res.setTeam1Off(res.getTeam1Off() + Integer.parseInt(detail[9]));
				player.setBankDef(Integer.parseInt(detail[10]));
				res.setTeam1Def(res.getTeam1Def()
						+ Integer.parseInt(detail[10]));
				player.setBank(Integer.parseInt(detail[11]));
				res.setTeam1bank(res.getTeam1bank()
						+ Integer.parseInt(detail[11]));
				player.setAss(Integer.parseInt(detail[12]));
				res.setAss1(res.getAss1() + Integer.parseInt(detail[12]));
				player.setSteal(Integer.parseInt(detail[13]));
				res.setSteal1(res.getSteal1() + Integer.parseInt(detail[13]));
				player.setRejection(Integer.parseInt(detail[14]));
				res.setRejection1(res.getRejection1()
						+ Integer.parseInt(detail[14]));
				player.setTo(Integer.parseInt(detail[15]));
				res.setTo1(res.getTo1() + Integer.parseInt(detail[15]));
				player.setFoul(Integer.parseInt(detail[16]));
				res.setFoul1(res.getFoul1() + Integer.parseInt(detail[16]));
				res.getPlayers1().add(player);
			}
			data = br.readLine();
			while (data != null) {
				String[] detail = data.split(";");
				player.setTeam(res.getSecondteam());
				player.setPlayername((detail[0]));
				player.setState((detail[1]));
				player.setTime((detail[2]));
				player.setShootEff(Integer.parseInt(detail[3]));
				res.setShootEff2(res.getShootEff2()
						+ Integer.parseInt(detail[3]));
				player.setShoot(Integer.parseInt(detail[4]));
				res.setShoot2(res.getShoot2() + Integer.parseInt(detail[4]));
				player.setLostSH(Integer.parseInt(detail[4])
						- Integer.parseInt(detail[3]));
				res.setLostSH2(res.getLostSH2() + Integer.parseInt(detail[4])
						- Integer.parseInt(detail[3]));
				player.setTPShootEff(Integer.parseInt(detail[5]));
				res.setTPShootEff2(res.getTPShootEff2()
						+ Integer.parseInt(detail[5]));
				player.setTPShoot(Integer.parseInt(detail[6]));
				res.setTPShoot2(res.getTPShoot2() + Integer.parseInt(detail[6]));
				player.setFTShootEff(Integer.parseInt(detail[7]));
				res.setFTShootEff2(res.getFTShootEff2()
						+ Integer.parseInt(detail[7]));
				player.setFT(Integer.parseInt(detail[8]));
				res.setFT2(res.getFT2() + Integer.parseInt(detail[8]));
				player.setBankOff(Integer.parseInt(detail[9]));
				res.setTeam2Off(res.getTeam2Off() + Integer.parseInt(detail[9]));
				player.setBankDef(Integer.parseInt(detail[10]));
				res.setTeam2Def(res.getTeam2Def()
						+ Integer.parseInt(detail[10]));
				player.setBank(Integer.parseInt(detail[11]));
				res.setTeam2bank(res.getTeam2bank()
						+ Integer.parseInt(detail[11]));
				player.setAss(Integer.parseInt(detail[12]));
				res.setAss2(res.getAss2() + Integer.parseInt(detail[12]));
				player.setSteal(Integer.parseInt(detail[13]));
				res.setSteal2(res.getSteal2() + Integer.parseInt(detail[13]));
				player.setRejection(Integer.parseInt(detail[14]));
				res.setRejection2(res.getRejection2()
						+ Integer.parseInt(detail[14]));
				player.setTo(Integer.parseInt(detail[15]));
				res.setTo2(res.getTo2() + Integer.parseInt(detail[15]));
				player.setFoul(Integer.parseInt(detail[16]));
				res.setFoul2(res.getFoul2() + Integer.parseInt(detail[16]));
				res.getPlayers2().add(player);
				data = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<TeamDataPO> readTeamfile() {
		ArrayList<TeamDataPO> teams = new ArrayList<TeamDataPO>();
		TeamDataPO team = new TeamDataPO();
		TeamDataPO team1 = new TeamDataPO();
		TeamDataPO team2 = new TeamDataPO();
		try {
			File f = new File(Teamfilename);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String data = br.readLine();// 一次读入一行，直到读入null为文件结束

			while (data != null) {
				team = new TeamDataPO();
				team1 = new TeamDataPO();
				team2 = new TeamDataPO();
				while (data.contains("═")) {
					data = br.readLine();
				}
				String[] temp = data.split("║");
				String[] teamData = temp[1].split("│");
				team.setName(teamData[0].trim());
				team.setShortName(teamData[1].trim());
				team.setCity(teamData[2].trim());
				team.setEorW(teamData[3].trim());
				team.setArea(teamData[4].trim());
				team.setMainposition(teamData[5].trim());
				team.setBuildyear(Integer.parseInt(teamData[6].trim()));
				team.setSeason("12-13");
				
				team1.setName(teamData[0].trim());
				team1.setShortName(teamData[1].trim());
				team1.setCity(teamData[2].trim());
				team1.setEorW(teamData[3].trim());
				team1.setArea(teamData[4].trim());
				team1.setMainposition(teamData[5].trim());
				team1.setBuildyear(Integer.parseInt(teamData[6].trim()));
				team1.setSeason("13-14");
				
				team2.setName(teamData[0].trim());
				team2.setShortName(teamData[1].trim());
				team2.setCity(teamData[2].trim());
				team2.setEorW(teamData[3].trim());
				team2.setArea(teamData[4].trim());
				team2.setMainposition(teamData[5].trim());
				team2.setBuildyear(Integer.parseInt(teamData[6].trim()));
				team2.setSeason("14-15");
				
            
				
				teams.add(team);
				teams.add(team1);
				teams.add(team2);
				
				data = br.readLine();
				if (data.contains("═")) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return teams;
	}

	// 获得所有比赛文件的完整路径返回File[]
	public File[] getAllMathcFielName() {
		File file = new File(MatchFileName);
		return file.listFiles();
	}
	
	
	
}
