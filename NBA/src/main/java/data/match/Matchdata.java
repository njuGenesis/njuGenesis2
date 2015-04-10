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
			FileOutputStream fos = new FileOutputStream(matchFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ArrayList<MatchDataPO> originMatch = readOut();
			originMatch.addAll(matches);
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
			File matchFile = new File("matchInfo" + "/" + "matchInfo" + ".ser");
			FileOutputStream fos = new FileOutputStream(matchFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ArrayList<MatchDataPO> originMatch = readOut();
			originMatch.add(matches);
			oos.writeObject(matches);
			fos.close();
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 读取一个队伍所有的比赛信息
	public ArrayList<MatchDataPO> readOut() {
		try {
			File matchFile = new File("matchInfo" + "/" + "matchInfo");
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
	
	
	// 读取一个队伍所有的比赛信息
	private ArrayList<MatchDataPO> readOut(File shotrName) {
		try {
			if (!shotrName.exists()) {
				ArrayList<MatchDataPO> Null = new ArrayList<MatchDataPO>();
				return Null;
			}
			FileInputStream fos = new FileInputStream(shotrName);
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

	// 读取一个队伍某个时间段内的比赛（包括startDate和endDate） 
	public ArrayList<MatchDataPO>readMatch(String startDate, String endDate, String shotrName) {
		ArrayList<MatchDataPO> matches = readOut();
		for (int i = 0; i < matches.size(); i++) {
			if (matches.get(i).getDate().compareTo(startDate) < 0
					|| matches.get(i).getDate().compareTo(endDate) > 0) {
				matches.remove(i);
			}
		}
		return matches;
	}

	// 读取所有的比赛信息
	public ArrayList<MatchDataPO> GetAllMatch() {
		ArrayList<MatchDataPO> result = new ArrayList<MatchDataPO>();
		File floder = new File("matchInfo");
		for (int i = 0; i < floder.listFiles().length; i++) {
			result.addAll(readOut(floder.listFiles()[i]));
		}
		return result;
	}

	public static void main(String[] args) {
		Matchdata read = new Matchdata();
		read.GetAllMatch().size();
	}

}
