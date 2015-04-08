package data.player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.po.PlayerDataPO;

public class PlayerDataInAndOut {
	public void WriteIn(PlayerDataPO p){
		try{
			FileOutputStream fs = new FileOutputStream("./playerInfo/"+p.getName()+".ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(p);
			os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public PlayerDataPO WriteOut(String name){
		try{
			FileInputStream fs = new FileInputStream("./playerInfo/"+name+".ser");
			ObjectInputStream os = new ObjectInputStream(fs);
			Object temp = os.readObject();
			PlayerDataPO res = (PlayerDataPO)temp;
			os.close();
			return res;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
