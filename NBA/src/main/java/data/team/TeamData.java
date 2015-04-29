package data.team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import data.po.TeamDataPO;

public class TeamData {

	public void WriteIn(ArrayList<TeamDataPO> Teams) {
		try {
			File teamFile = new File("teamInfo/"
					+ "teamInfo"  + ".ser");
			FileOutputStream fos = new FileOutputStream(teamFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Teams);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private ArrayList<TeamDataPO> ReadOut() {
		try {			
			File teamFile = new File("teamInfo/"
					+"teamInfo.ser"  );
			FileInputStream fos = new FileInputStream(teamFile);
			ObjectInputStream oos = new ObjectInputStream(fos);
			@SuppressWarnings("unchecked")
			ArrayList<TeamDataPO> resArray =(ArrayList<TeamDataPO>) oos.readObject();
			for(int i=0;i<resArray.size();i++){
				System.out.println(resArray.get(i).getShortName()+resArray.get(i).getBackBoard());
				if(!(resArray.get(i).getBackBoard()>0)){
					resArray.remove(i);
					i--;
				}
			}
			oos.close();
			return resArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	//返回一只球队所有赛季的数据
		public ArrayList<TeamDataPO> GetInfo(String name) {
			ArrayList<TeamDataPO> res=new ArrayList<TeamDataPO>();
			ArrayList<TeamDataPO> TeamArray =ReadOut();
			for(int i=0;i<TeamArray.size();i++){
				if(TeamArray.get(i).getShortName().equals(name)||TeamArray.get(i).getName().equals(name)){
					res.add(TeamArray.get(i));
				}
			}
			return res;
		}
	
	//返回一只球队某个赛季的数据(没找到估计就要报错了....)
	public TeamDataPO GetInfo(String name,String season) {
		ArrayList<TeamDataPO> origin =   GetInfo(name);
		for(int i=0;i<origin.size();i++){
			if(origin.get(i).getSeason().equals(season)){
				return origin.get(i);
			}
		}
		return origin.get(0);
	}
	
	public ArrayList<TeamDataPO> GetInfoBySeason(String season) {
		ArrayList<TeamDataPO> allInfo=GetAllInfo();
		ArrayList<TeamDataPO> res=new ArrayList<TeamDataPO>();
		for(int i=0;i<allInfo.size();i++ ){
			if(allInfo.get(i).getSeason().equals(season)){
				res.add(allInfo.get(i));
			}
		}
		return res;
	}

	
	//获得所有球队的所有数据
	public ArrayList<TeamDataPO> GetAllInfo() {
		return ReadOut();
	}
	
}
