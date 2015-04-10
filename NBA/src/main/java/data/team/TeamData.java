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
		public void WriteIn(TeamDataPO t){
			try{
				FileOutputStream fos = new FileOutputStream("./TeamInfo/"+t.getShortName()+"-"+t.getName()+".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(t);
				oos.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void WriteIn(ArrayList<TeamDataPO>Teams){
			for(int i=0;i<Teams.size();i++){
				WriteIn(Teams.get(i));
			}
		}
		
		public TeamDataPO ReadOut(String name){
			try{
				File floder = new File("teamInfo");
				for(int i=0;i<floder.list().length;i++){
					if(floder.list()[i].contains(name)){
						name=floder.list()[i];
					}
				}
				FileInputStream fos = new FileInputStream("./TeamInfo/"+name);
				ObjectInputStream oos = new ObjectInputStream(fos);
				TeamDataPO res = (TeamDataPO) oos.readObject();
				oos.close();
				return res;
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		public ArrayList<TeamDataPO> GetAllInfo(){
			ArrayList<TeamDataPO> res = new ArrayList<TeamDataPO>();
			File floder = new File("teamInfo");
			for (int i = 0; i < floder.list().length; i++) {
				res.add(ReadOut(floder.list()[i]));
			}
			return res;
		}
	}

