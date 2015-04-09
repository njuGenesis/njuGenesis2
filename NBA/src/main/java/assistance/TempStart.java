package assistance;

import bussinesslogic.player.PlayerLogic;
import data.po.PlayerDataPO;

public class TempStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlayerLogic p = new PlayerLogic();
		//p.initialize("./迭代一数据/players/info");
		//p.analysData("Willie Reed");
		//p.analysData("Ognjen Kuzmic");
		//GetFileData g = new GetFileData();
		//g.readPlayerfile("D:/学习/软工3/CSEIII data/迭代一数据/players/info/Aaron Gray");
		p.initialize("./迭代一数据/players/info");
//	PlayerDataPO[] res = p.getAllSearch("Au","G", "Central");
//	System.out.println(res.length);
//		for(int i = 0;i<res.length;i++){
//		System.out.println(res[i].getName()+" ; "+res[i].getPosition()+" ; "+res[i].getTeamName());
//	}
		PlayerDataPO res  = p.getInfo("Aaron Gray");
		System.out.print(res.getPosition()+";"+res.getName()+res.getBirth());
	}
	
}
