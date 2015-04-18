package data.match;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import data.po.MatchDataPO;

public class Matchdata implements Serializable {
	/**
 * 
 */
	private static final long serialVersionUID = 1L;

	// 写入一组matchPO
	public void writeIn(ArrayList<MatchDataPO> matches) {
		try {
			File matchFile = new File("matchInfo" + "/" + "matchInfo" + ".ser");
			ArrayList<MatchDataPO> originMatch = readOut();
			originMatch.addAll(matches);
			
			FileOutputStream fos = new FileOutputStream(matchFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(originMatch);
			fos.close();
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 写入一个matchPO
	public void writeIn(MatchDataPO matches) {
		try {
			File matchFile = new File("matchInfo" +"/"+ "matchInfo" + ".ser");
			ArrayList<MatchDataPO> originMatch = readOut();
			originMatch.add(matches);
			FileOutputStream fos = new FileOutputStream(matchFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(matches);
			fos.close();
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 读取所有的比赛信息
	private ArrayList<MatchDataPO> readOut() {
		try {                                       
			File matchFile = new File("matchInfo" +"/"+ "matchInfo" + ".ser");
			if (!matchFile.exists()) {
				ArrayList<MatchDataPO> Null = new ArrayList<MatchDataPO>();
				return Null;
			}

			FileInputStream fos = new FileInputStream(matchFile);
			ObjectInputStream oos = new ObjectInputStream(fos);
			@SuppressWarnings("unchecked")
			ArrayList<MatchDataPO> matches = (ArrayList<MatchDataPO>) oos
					.readObject();
			fos.close();
			oos.close();
			return matches;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		ArrayList<MatchDataPO> Null = new ArrayList<MatchDataPO>();
		return Null;
	}

	// 读取一个队伍一个赛季所有的比赛信息
	private ArrayList<MatchDataPO> getMatch(String shotrName, String season) {

		ArrayList<MatchDataPO> allMatches = readOut();
		
		ArrayList<MatchDataPO> res = new ArrayList<MatchDataPO>();
		for (int i = 0; i < allMatches.size(); i++) {

			if ((allMatches.get(i).getFirstteam().equals(shotrName) || allMatches.get(i)
					.getSecondteam().equals(shotrName))
					&& (allMatches.get(i).getSeason().equals(season))) {
				res.add(allMatches.get(i));
			}
		}
		return res;
	}
	
	// 读取一个赛季所有的比赛信息
	private ArrayList<MatchDataPO> getMatch( String season) {
		ArrayList<MatchDataPO> allMatches = readOut();
		ArrayList<MatchDataPO> res = new ArrayList<MatchDataPO>();
		for (int i = 0; i < allMatches.size(); i++) {
			if ( allMatches.get(i).getSeason().equals(season)) {
				res.add(allMatches.get(i));
			}
		}
		return res;
	}
	

	// 读取一个队伍某个时间段内的比赛（包括startDate和endDate）
	private ArrayList<MatchDataPO> getMatch(String startDate, String endDate,
			String shotrName) {
		ArrayList<MatchDataPO> matches = readOut();
		ArrayList<MatchDataPO> res = new ArrayList<MatchDataPO>();
		for (int i = 0; i < matches.size(); i++) {
			if (    (matches.get(i).getDate().compareTo(startDate) >= 0)
					&&   (matches.get(i).getDate().compareTo(endDate) <= 0)
					&& (matches.get(i).getFirstteam().equals(shotrName) || matches
							.get(i).getSecondteam().equals(shotrName))) {
				res.add(matches.get(i));
			}
		}
		return res;
	}

	// 返回所有的比赛信息
	public ArrayList<MatchDataPO> GetAllMatch() {
		ArrayList<MatchDataPO> result = readOut();
		return result;
	}
	
	//返回一个赛季的比赛信息
	public ArrayList<MatchDataPO> GetPartMatch(String season) {
		ArrayList<MatchDataPO> result = getMatch(season);
		return result;
	}

	// 返回一个队伍某个时间段内的比赛（包括startDate和endDate）
	public ArrayList<MatchDataPO> GetPartMatch(String startDate,
			String endDate, String shotrName) {
		ArrayList<MatchDataPO> result = getMatch(startDate, endDate, shotrName);
		return result;
	}

	// 返回一个队伍一个赛季常规赛的所有比赛（包括startDate和endDate）
	public ArrayList<MatchDataPO> GetPartMatch(String shotrName, String date) {
		ArrayList<MatchDataPO> result = getMatch(shotrName, date);
		return result;
	}

	public static void main(String[] args) {
		Matchdata read = new Matchdata();
		System.out.println(read.GetAllMatch().size());
	}

}
