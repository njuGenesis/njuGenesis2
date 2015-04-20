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
		//p.initialize("./迭代一数据/players/info","13-14");
//	PlayerDataPO[] res = p.getAllSearch("Au","G", "Central");
//	System.out.println(res.length);
//		for(int i = 0;i<res.length;i++){
//		System.out.println(res[i].getName()+" ; "+res[i].getPosition()+" ; "+res[i].getTeamName());
//	}
//		PlayerDataPO[] res  = p.getAllInfo("12-13");
//		//System.out.print(res[0].getPosition()+";"+res[0].getName()+res[0].getBirth());
//		for(int i =0 ;i<res.length;i++){
//			System.out.println(res[i].getName()+";"+res[i].getPosition()+";"+res[i].getFieldGoalPercentage());
//		}
		
		//FileListener f = new FileListener();
		//f.Listen("./迭代一数据/matches");
		//PlayerDataPO res = p.getInfo("Aaron Brooks", "12-13");
		//System.out.println(res.getName()+";"+res.getFieldGoalPercentage());
		//p.setOrder("得分", false, "13-14", false);
		//p.getSearch("d", "13-14");
		p.getFirstFifty("抢断", p.getAllInfo("13-14"), true);
	}
	
}
