package data.player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.po.PlayerDataPO;

public class PlayerDataInAndOut {
	public void WriteIn(PlayerDataPO p,String season){
		try{
			
			FileOutputStream fs = new FileOutputStream("playerInfo/"+season+p.getName()+".ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(p);
			os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public PlayerDataPO WriteOut(String name,String season){
		try{
			FileInputStream fs = new FileInputStream("playerInfo/"+season + name+".ser");
			ObjectInputStream os = new ObjectInputStream(fs);
			Object temp = os.readObject();
			PlayerDataPO res = (PlayerDataPO)temp;
			os.close();
			return res;
		}
		catch(Exception e){
			//e.printStackTrace();
			System.out.println("找不到"+name);
			return null;
		}
	}
	public PlayerDataPO WriteOutAllSeason(String filename){
		try{
			FileInputStream fs = new FileInputStream("playerInfo/"+filename);
			ObjectInputStream os = new ObjectInputStream(fs);
			Object temp = os.readObject();
			PlayerDataPO res = (PlayerDataPO)temp;
			os.close();
			return res;
		}catch(Exception e){
			System.out.println("找不到"+filename);
			return null;
		}
	}
}
