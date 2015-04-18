package data.team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bussinesslogic.match.MatchLogic;
import data.po.TeamDataPO;

public class TeamData {
	public void WriteIn(TeamDataPO t) {
		try {
			File teamFile = new File("teamInfo/"
					+ t.getShortName()+"-"+t.getName()  + ".ser");
			ArrayList<TeamDataPO> origin = ReadOut(t.getShortName());
			
			int change=0;
			for(int i=0;i<origin.size();i++){
				if(origin.get(i).getSeason().equals(t.getSeason())){
					origin.set(i, t);
					change =1;
					break;
				}
			}
			if(change==0){
				origin.add(t);
			}	
			FileOutputStream fos = new FileOutputStream(teamFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(origin);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void WriteIn(ArrayList<TeamDataPO> Teams) {
		for (int i = 0; i < Teams.size(); i++) {
			WriteIn(Teams.get(i));
		}
	}


	private ArrayList<TeamDataPO> ReadOut(String name) {
		try {
			File floder = new File("teamInfo");
			for (int i = 0; i < floder.list().length; i++) {
				if (floder.list()[i].contains(name)) {
					name = floder.list()[i];
				}
			}
			File teamFile = new File("teamInfo/"
					+name  );
			if (!teamFile.exists()) {
				ArrayList<TeamDataPO> Null = new ArrayList<TeamDataPO>();
				return Null;
			}
			FileInputStream fos = new FileInputStream(teamFile);
			ObjectInputStream oos = new ObjectInputStream(fos);
			@SuppressWarnings("unchecked")
			ArrayList<TeamDataPO> resArray =(ArrayList<TeamDataPO>) oos.readObject();
			oos.close();
			return resArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<TeamDataPO> ReadOut(File filename) {
		try {
			FileInputStream fos = new FileInputStream(filename);
			ObjectInputStream oos = new ObjectInputStream(fos);
			@SuppressWarnings("unchecked")
			ArrayList<TeamDataPO> res = (ArrayList<TeamDataPO>) oos.readObject();
			oos.close();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	//返回一只球队所有赛季的数据
		public ArrayList<TeamDataPO> GetInfo(String name) {
			return ReadOut(name);
		}
	
	//返回一只球队某个赛季的数据(没找到估计就要报错了....)
	public TeamDataPO GetInfo(String name,String season) {
		ArrayList<TeamDataPO> res =  ReadOut(name);
		for(int i=0;i<res.size();i++){
			if(res.get(i).getSeason().equals(season)){
				return res.get(i);
			}
		}
		return res.get(0);
	}

	
	//获得所有球队的所有数据
	public ArrayList<TeamDataPO> GetAllInfo() {
		ArrayList<TeamDataPO> res = new ArrayList<TeamDataPO>();
		File floder = new File("teamInfo");
		for (int i = 0; i < floder.listFiles().length; i++) {

			res.addAll(ReadOut(floder.listFiles()[i]));

		}
		return res;
	}
}
