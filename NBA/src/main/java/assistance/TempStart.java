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
		//p.initialize("./迭代一数据/players/info","12-13");
//	PlayerDataPO[] res = p.getAllSearch("Au","G", "Central");
//	System.out.println(res.length);
//		for(int i = 0;i<res.length;i++){
//		System.out.println(res[i].getName()+" ; "+res[i].getPosition()+" ; "+res[i].getTeamName());
//	}
		//PlayerDataPO[] res  = p.getAllInfo("12-13");
//		//System.out.print(res[0].getPosition()+";"+res[0].getName()+res[0].getBirth());
		//for(int i =0 ;i<res.length;i++){
			//if(res[i].getMinutesOnField()==0){
		//	System.out.println(res[i].getName()+";"+res[i].getGP());
		//	}
	//}
		
		//FileListener f = new FileListener();
		//f.Listen("./迭代一数据/matches");
		//PlayerDataPO res = p.getInfo("LeBron James", "13-14");
		//System.out.println(res.getName()+";"+res.getTeamName()+";"+res.getPPG()+";"+res.getAPG()+";"+res.getBPG()+";"+res.getGP());
		//p.setOrder("得分", false, "13-14", false);
		//p.getSearch("d", "13-14");
		//p.getFirstFifty("抢断", p.getAllInfo("13-14"), true);
		//PlayerDataPO[] res = p.hotPlayerSeason("13-14", "投篮命中率");
		//PlayerDataPO[] res = p.hotPlayerToday("13-14", "04-11", "助攻");
		//for(int i =0 ;i<res.length;i++){
		
		//System.out.println(res[i].getName()+";"+res[i].getFieldGoalPercentage()+";"+res[i].getFTPercentage());
	
//}
		
//		PlayerDataPO[] res = p.getAllSeasonInfo("Aaron Brooks");
//		for(int i = 0;i<res.length;i++){
//			System.out.println(res[i].getName()+";"+res[i].getSeason()+res[i].getPPG());
//		}
		System.out.println(p.getLatestSeason());
	}
	
}
