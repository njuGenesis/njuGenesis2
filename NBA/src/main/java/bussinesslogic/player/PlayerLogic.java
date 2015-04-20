package bussinesslogic.player;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import assistance.GetFileData;
import bslogicService.PlayerInfoService;
import data.player.PlayerDataInAndOut;
import data.po.MatchDataPO;
import data.po.PlayerDataPO;

public class PlayerLogic implements PlayerInfoService{
	GetFileData g = new GetFileData();
	
	ArrayList<PlayerDataPO> PlayerList = new ArrayList<PlayerDataPO>();
	//PlayerRmi p = new PlayerRmi();
	PlayerDataInAndOut pio = new PlayerDataInAndOut();
	public void analysData(String filepath,String season) {
		// TODO Auto-generated method stub
		PlayerList.clear();
		File root = new File(filepath);
		File[] files = root.listFiles();
		for(File file:files){		
		String filePath = "./迭代一数据/players/info/" + file.getName();
		//System.out.println(filepath);
		String basicInfo = g.readPlayerfile(filePath);
		String[] tempbasic = basicInfo.split("\n");
		PlayerDataPO temp = new PlayerDataPO();
		
		if(tempbasic[0].endsWith("Jr.")){
			
		temp.setName(tempbasic[0].replaceAll("\\.", ""));	
		}
		else{
			temp.setName(tempbasic[0]);
		}
		temp.setNumber(tempbasic[1]);
		temp.setPosition(tempbasic[2]);
		temp.setHeight(tempbasic[3]);
		temp.setWeight(Double.valueOf(tempbasic[4]));
		temp.setBirth(tempbasic[5]);
		temp.setAge(Integer.valueOf(tempbasic[6]));
		if(tempbasic[7].equals("R")){
			temp.setExp(0);
		}
		else{
			temp.setExp(Integer.valueOf(tempbasic[7]));
		}
		
		temp.setSchool(tempbasic[8]);
		temp.setTeamName("null");
		//System.out.println(AllInfo.getName());
		
		PlayerList.add(temp);
		
		}
		//loop over
		getAllMatch("./迭代一数据/matches",season);
		//写入所有数据
		//System.out.print(PlayerList.size());
		for(int i = 0;i<PlayerList.size();i++){	
			//System.out.println(PlayerList.get(i).getName());
		pio.WriteIn(PlayerList.get(i),season);
		}
	}
	public void getAllMatch(String filepath,String season){
		//System.out.println(name);	
		File root = new File(filepath);
		File[] files = root.listFiles();
		for(File file:files){
			if(file.getName().startsWith(season)){
				//System.out.println(file.getAbsolutePath());
			MatchDataPO m = g.readMatchfile(file.getAbsolutePath());
			
			int firstb = 0;//鐞冮槦鎬荤鏉�
			double firsttotaltime = 0;//鐞冮槦涓婂満鎬绘椂闂�
			int firstGoal = 0;//鐞冮槦杩涚悆娆℃暟
			int firstFT = 0;//鐞冮槦缃氱悆娆℃暟
			int firstOffb = 0;//鐞冮槦鎬昏繘鏀荤鏉�
			int firstTo = 0;//鐞冮槦鎬诲け璇�
			int secondb = 0;//鐞冮槦鎬荤鏉�
			double secondtotaltime = 0;//鐞冮槦涓婂満鎬绘椂闂�
			int secondGoal = 0;//鐞冮槦杩涚悆娆℃暟
			int secondFT = 0;//鐞冮槦缃氱悆娆℃暟
			int secondOffb = 0;//鐞冮槦鎬昏繘鏀荤鏉�
			int secondTo = 0;//鐞冮槦鎬诲け璇�
			try{
			for(int i = 1;i<m.firstTeamInfo.size();i++){//鏁翠釜鐞冮槦鐨勬暟鎹�
				int fminute = 0;
				int fseconds = 0;
				String[] temp = m.firstTeamInfo.get(i).split(";");
				firstb = firstb + Integer.valueOf(temp[11]);
				
				try{
				String[] ftime = temp[2].split(":");
				if(temp[2].equals(null)){
					
				}
				else{
				fminute = Integer.valueOf(ftime[0]);//time
				fseconds = Integer.valueOf(ftime[1]);
				}
				firsttotaltime = firsttotaltime + fminute*60+fseconds;
				}
				catch(Exception e){
					
				}
				
				firstGoal = firstGoal + Integer.valueOf(temp[3]);
				firstFT = firstFT + Integer.valueOf(temp[8]);
				firstOffb = firstOffb + Integer.valueOf(temp[9]);
				firstTo = firstTo + Integer.valueOf(temp[15]);
			}
			
			for(int i = 1;i<m.secondTeamInfo.size();i++){//鏁翠釜鐞冮槦鐨勬暟鎹�
				int fminute = 0;
				int fseconds = 0;
				
				String[] temp = m.secondTeamInfo.get(i).split(";");
				secondb = secondb + Integer.valueOf(temp[11]);
				
				try{
				String[] ftime = temp[2].split(":");
				if(temp[2].equals(null)){
					
				}
				else{
				 fminute = Integer.valueOf(ftime[0]);//time
				fseconds = Integer.valueOf(ftime[1]);
				}
				secondtotaltime = secondtotaltime + fminute*60+fseconds;
			
				}
				catch(Exception e){
					
				}
				
				secondGoal = secondGoal + Integer.valueOf(temp[3]);
				secondFT = secondFT + Integer.valueOf(temp[8]);
				secondOffb = secondOffb + Integer.valueOf(temp[9]);
				secondTo = secondTo + Integer.valueOf(temp[15]);
			}
			
			for(int i = 1;i<m.firstTeamInfo.size();i++){				
				
				String[] temp = m.firstTeamInfo.get(i).split(";");
				
				for(int k = 0;k<PlayerList.size();k++){
					
				if(temp[0].equals(PlayerList.get(k).getName())){
					
					PlayerList.get(k).setTeamName(m.firstTeamInfo.get(0));
					
					PlayerList.get(k).setTotalb(PlayerList.get(k).getTotalb() + firstb); 
					PlayerList.get(k).setTotalbother(PlayerList.get(k).getTotalbother()+ secondb);
					PlayerList.get(k).setTotalminute(PlayerList.get(k).getTotalminute()+firsttotaltime);
					PlayerList.get(k).setTotalGoal(PlayerList.get(k).getTotalGoal()+firstGoal);
					PlayerList.get(k).setOtherTotalFieldGoal(PlayerList.get(k).getOtherTotalFieldGoal()+ secondGoal);
					PlayerList.get(k).setAllFT(PlayerList.get(k).getAllFT()+firstFT);
					PlayerList.get(k).setTotalOffb(PlayerList.get(k).getTotalOffb()+secondOffb);
					PlayerList.get(k).setAllTo(PlayerList.get(k).getAllTo()+firstTo);
					
					PlayerList.get(k).setGP(PlayerList.get(k).getGP()+1);
					//System.out.println(file.getAbsolutePath());
					if(!temp[1].equals("")){
						PlayerList.get(k).setGS(PlayerList.get(k).getGS()+1);
					}
					
					try{
					String[] time = temp[2].split(":");
					int minute = 0;
					int seconds = 0;
					if(temp[2].equals(null)){
						
					}
					else{
					minute = Integer.valueOf(time[0]);//time
					seconds = Integer.valueOf(time[1]);
					}
					PlayerList.get(k).setMinutesOnField(PlayerList.get(k).getMinutesOnField()+minute*60+seconds);
					}catch(Exception e){
						
					}
					
					PlayerList.get(k).setFieldGoal(PlayerList.get(k).getFieldGoal()+Integer.valueOf(temp[3]));
					PlayerList.get(k).setTotalFieldGoal(PlayerList.get(k).getTotalFieldGoal() + Integer.valueOf(temp[4]));
					
					PlayerList.get(k).setThreeGoal(PlayerList.get(k).getThreeGoal()+ Integer.valueOf(temp[5]));
					PlayerList.get(k).setTotalThreeGoal(PlayerList.get(k).getTotalThreeGoal() + Integer.valueOf(temp[6]));
					
					PlayerList.get(k).setFT(PlayerList.get(k).getFT() + Integer.valueOf(temp[7]));
					PlayerList.get(k).setTotalFT(PlayerList.get(k).getTotalFT() + Integer.valueOf(temp[8]));
					
					PlayerList.get(k).setOffb(PlayerList.get(k).getOffb() + Integer.valueOf(temp[9]));
					
					PlayerList.get(k).setDefb(PlayerList.get(k).getDefb() + Integer.valueOf(temp[10]));
					
					PlayerList.get(k).setBackboard(PlayerList.get(k).getBackboard() + Integer.valueOf(temp[11]));
					
					PlayerList.get(k).setAssist(PlayerList.get(k).getAssist() + Integer.valueOf(temp[12]));
					
					PlayerList.get(k).setRejection (PlayerList.get(k).getRejection() + Integer.valueOf(temp[14]));
					
					PlayerList.get(k).setSteal(PlayerList.get(k).getSteal() + Integer.valueOf(temp[13]));
					
					PlayerList.get(k).setTo (PlayerList.get(k).getTo() + Integer.valueOf(temp[15]));
					
					PlayerList.get(k).setFoul(PlayerList.get(k).getFoul() + Integer.valueOf(temp[16]));
					try{
						PlayerList.get(k).setPTS (PlayerList.get(k).getPTS() + Integer.valueOf(temp[17]));
					}
					catch(Exception e){
						temp[17] = "0";
					}
					int tempd = 0;
					if(Integer.valueOf(temp[17])>=10){
						tempd++;
					}
					if(Integer.valueOf(temp[11])>=10){
						tempd++;
					}
					if(Integer.valueOf(temp[12])>=10){
						tempd++;
					}
					if(Integer.valueOf(temp[13])>=10){
						tempd++;
					}
					if(Integer.valueOf(temp[14])>=10){
						tempd++;
					}
					if(tempd>=2){
						PlayerList.get(k).setDouble(PlayerList.get(k).getDouble()+1);
					}
					
					break;
				}
				}
			}
			for(int i = 1;i<m.secondTeamInfo.size();i++){//鏁翠釜鐞冮槦鐨勬暟鎹�
				int fminute = 0;
				int fseconds = 0;
				
				String[] temp = m.secondTeamInfo.get(i).split(";");
				secondb = secondb + Integer.valueOf(temp[11]);
				
				try{
				String[] ftime = temp[2].split(":");
				if(temp[2].equals(null)){
					
				}
				else{
				 fminute = Integer.valueOf(ftime[0]);//time
				fseconds = Integer.valueOf(ftime[1]);
				}
				secondtotaltime = secondtotaltime + fminute*60+fseconds;
			
				}
				catch(Exception e){
					
				}
				
				secondGoal = secondGoal + Integer.valueOf(temp[3]);
				secondFT = secondFT + Integer.valueOf(temp[8]);
				secondOffb = secondOffb + Integer.valueOf(temp[9]);
				secondTo = secondTo + Integer.valueOf(temp[15]);
			}
			
			for(int i = 1;i<m.secondTeamInfo.size();i++){				
				
				String[] temp = m.secondTeamInfo.get(i).split(";");
				
				for(int k = 0;k<PlayerList.size();k++){
				if(temp[0].equals(PlayerList.get(k).getName())){
					PlayerList.get(k).setTeamName(m.secondTeamInfo.get(0));
					
					PlayerList.get(k).setTotalb(PlayerList.get(k).getTotalb() + secondb); 
					PlayerList.get(k).setTotalbother(PlayerList.get(k).getTotalbother()+ firstb);
					PlayerList.get(k).setTotalminute(PlayerList.get(k).getTotalminute()+secondtotaltime);
					PlayerList.get(k).setTotalGoal(PlayerList.get(k).getTotalGoal()+secondGoal);
					PlayerList.get(k).setOtherTotalFieldGoal(PlayerList.get(k).getOtherTotalFieldGoal()+ firstGoal);
					PlayerList.get(k).setAllFT(PlayerList.get(k).getAllFT()+secondFT);
					PlayerList.get(k).setTotalOffb(PlayerList.get(k).getTotalOffb()+firstOffb);
					PlayerList.get(k).setAllTo(PlayerList.get(k).getAllTo()+secondTo);
					
					PlayerList.get(k).setGP(PlayerList.get(k).getGP()+1);
					//System.out.println(file.getAbsolutePath());
					if(!temp[1].equals("")){
						PlayerList.get(k).setGS(PlayerList.get(k).getGS()+1);
					}
					
					try{
					String[] time = temp[2].split(":");
					int minute = 0;
					int seconds = 0;
					if(temp[2].equals(null)){
						
					}
					else{
					minute = Integer.valueOf(time[0]);//time
					seconds = Integer.valueOf(time[1]);
					}
					PlayerList.get(k).setMinutesOnField(PlayerList.get(k).getMinutesOnField()+minute*60+seconds);
					}catch(Exception e){
						
					}
					
					PlayerList.get(k).setFieldGoal(PlayerList.get(k).getFieldGoal()+Integer.valueOf(temp[3]));
					PlayerList.get(k).setTotalFieldGoal(PlayerList.get(k).getTotalFieldGoal() + Integer.valueOf(temp[4]));
					
					PlayerList.get(k).setThreeGoal(PlayerList.get(k).getThreeGoal()+ Integer.valueOf(temp[5]));
					PlayerList.get(k).setTotalThreeGoal(PlayerList.get(k).getTotalThreeGoal() + Integer.valueOf(temp[6]));
					
					PlayerList.get(k).setFT(PlayerList.get(k).getFT() + Integer.valueOf(temp[7]));
					PlayerList.get(k).setTotalFT(PlayerList.get(k).getTotalFT() + Integer.valueOf(temp[8]));
					
					PlayerList.get(k).setOffb(PlayerList.get(k).getOffb() + Integer.valueOf(temp[9]));
					
					PlayerList.get(k).setDefb(PlayerList.get(k).getDefb() + Integer.valueOf(temp[10]));
					
					PlayerList.get(k).setBackboard(PlayerList.get(k).getBackboard() + Integer.valueOf(temp[11]));
					
					PlayerList.get(k).setAssist(PlayerList.get(k).getAssist() + Integer.valueOf(temp[12]));
					
					PlayerList.get(k).setRejection (PlayerList.get(k).getRejection() + Integer.valueOf(temp[14]));
					
					PlayerList.get(k).setSteal(PlayerList.get(k).getSteal() + Integer.valueOf(temp[13]));
					
					PlayerList.get(k).setTo (PlayerList.get(k).getTo() + Integer.valueOf(temp[15]));
					
					PlayerList.get(k).setFoul(PlayerList.get(k).getFoul() + Integer.valueOf(temp[16]));
					try{
						PlayerList.get(k).setPTS (PlayerList.get(k).getPTS() + Integer.valueOf(temp[17]));
					}
					catch(Exception e){
					   temp[17] = "0";
					}
					int tempd = 0;
					if(Integer.valueOf(temp[17])>=10){
						tempd++;
					}
					if(Integer.valueOf(temp[11])>=10){
						tempd++;
					}
					if(Integer.valueOf(temp[12])>=10){
						tempd++;
					}
					if(Integer.valueOf(temp[13])>=10){
						tempd++;
					}
					if(Integer.valueOf(temp[14])>=10){
						tempd++;
					}
					if(tempd>=2){
						PlayerList.get(k).setDouble(PlayerList.get(k).getDouble()+1);
					}
					
					break;
				}
				}
			}
			}catch(Exception e){
				
				System.out.println(file.getAbsolutePath());
			}
			
		}
		
		}
		
		
		for(int i = 0;i<PlayerList.size();i++){
			int backboard = PlayerList.get(i).getBackboard();
			int GP =  PlayerList.get(i).getGP();
			int assist =  PlayerList.get(i).getAssist();
			double MinutesOnField =  PlayerList.get(i).getMinutesOnField();
			int FieldGoal =  PlayerList.get(i).getFieldGoal();
			int TotalFieldGoal =  PlayerList.get(i).getTotalFieldGoal();
			int TotalThreeGoal =  PlayerList.get(i).getTotalThreeGoal();
			int ThreeGoal =  PlayerList.get(i).getThreeGoal();
			int Offb =  PlayerList.get(i).getOffb();
			int Defb =  PlayerList.get(i).getDefb();
			int Steal =  PlayerList.get(i).getSteal();
			int Rejection =  PlayerList.get(i).getRejection();
			int To =  PlayerList.get(i).getTo();
			int foul =  PlayerList.get(i).getFoul();
			int PTS =  PlayerList.get(i).getPTS();
			int TotalFT =  PlayerList.get(i).getTotalFT();
			int FT =  PlayerList.get(i).getFT();
			double totalminute =  PlayerList.get(i).getTotalminute();
			int totalb =  PlayerList.get(i).getTotalb();
			int totalbother =  PlayerList.get(i).getTotalbother();
			int TotalGoal =  PlayerList.get(i).getTotalGoal();
			int TotalOffb =  PlayerList.get(i).getTotalOffb();
			int OtherTotalFieldGoal =  PlayerList.get(i).getOtherTotalFieldGoal();
			int AllFT  =  PlayerList.get(i).getAllFT();
			int AllTo =  PlayerList.get(i).getAllTo();
		if(PlayerList.get(i).getGP()!=0){
			PlayerList.get(i).setBPG(backboard/(double)GP);
			PlayerList.get(i).setAPG(assist/(double)GP);
			PlayerList.get(i).setMPG( MinutesOnField/(double)GP);
			if(TotalFieldGoal!=0){
			PlayerList.get(i).setFieldGoalPercentage(FieldGoal/(double)TotalFieldGoal);
			}
			else{
				PlayerList.get(i).setFieldGoalPercentage(0);
			}
			if(TotalThreeGoal!=0){
				PlayerList.get(i).setThreePGPercentage(ThreeGoal/(double)TotalThreeGoal);
			}
			else{
				PlayerList.get(i).setThreePGPercentage(0);
			}
			if(TotalFT!=0){
				PlayerList.get(i).setFTPercentage(FT/(double)TotalFT);
			}
			else{
				PlayerList.get(i).setFTPercentage(0);
			}
			PlayerList.get(i).setOff(Offb);//杩涙敾鏁�
			PlayerList.get(i).setOffPG( PlayerList.get(i).getOff()/(double)GP);
			PlayerList.get(i).setDef(Defb);//闃插畧鏁�
			PlayerList.get(i).setDefPG( PlayerList.get(i).getDef()/(double)GP);
			PlayerList.get(i).setStealPG(Steal/(double)GP);
			PlayerList.get(i).setRPG(Rejection/(double)GP);
			PlayerList.get(i).setToPG(To/(double)GP);
			PlayerList.get(i).setFoulPG(foul/(double)GP);
			PlayerList.get(i).setPPG(PTS/(double)GP);
			PlayerList.get(i).setEff( (PTS+backboard+assist+Steal+Rejection)-(TotalFieldGoal-FieldGoal)-(TotalFT-FT)-To); 
			 PlayerList.get(i).setGmsc ( PTS+0.4*FieldGoal-0.7*TotalFieldGoal-0.4*(TotalFT-FT)+0.7*Offb+0.3*Defb+Steal+0.7*assist+0.7*Rejection
				-0.4*foul-To); 
			 
			 if((2*(TotalFieldGoal+0.44*TotalFT))!=0){
			PlayerList.get(i).setTruePercentage(PTS/(double)(2*(TotalFieldGoal+0.44*TotalFT)));
			 }
			 else{
				 PlayerList.get(i).setTruePercentage(0);
			 }
			 
			 if(TotalFieldGoal!=0){
			PlayerList.get(i).setShootEff((FieldGoal+0.5*ThreeGoal)/(double)TotalFieldGoal);//鎶曠鏁堢巼锛�	
			 }
			 else{
				 PlayerList.get(i).setShootEff(0);
			 }
			 
			PlayerList.get(i).setBackboardEff ( backboard*((double)totalminute/5)/(double)MinutesOnField/(totalb+totalbother)) ;//绡澘鐜囷紝		
			PlayerList.get(i).setOffBEff(Offb*((double)totalminute/5)/(double)MinutesOnField/(totalb+totalbother) );//杩涙敾绡澘鐜囷紝		
			PlayerList.get(i).setDefBEff(Defb*((double)totalminute/5)/(double)MinutesOnField/(totalb+totalbother) );//闃插畧绡澘鐜囷紝		
			PlayerList.get(i).setAssitEff (assist/((double)MinutesOnField/((double)totalminute/5)*TotalGoal-TotalFieldGoal)) ;//鍔╂敾鐜囷紝		
			PlayerList.get(i).setStealEff( Steal*((double)totalminute/5)/(double)MinutesOnField/TotalOffb);//鎶㈡柇鐜囷紝		
			PlayerList.get(i).setRejectionEff ( Rejection*((double)totalminute/5)/(double)MinutesOnField/OtherTotalFieldGoal);//鐩栧附鐜囷紝		
			
			if(TotalFieldGoal-TotalThreeGoal+0.44*TotalFT+To!=0){
			PlayerList.get(i).setToEff ( To/(double)(TotalFieldGoal-TotalThreeGoal+0.44*TotalFT+To) );
			}
			else{
				PlayerList.get(i).setToEff (0);//澶辫鐜囷紝		
			}
			PlayerList.get(i).setUseEff((TotalFieldGoal+0.44*TotalFT+To)*(totalminute/5)/(double)MinutesOnField/(TotalGoal+0.44*AllFT
						+AllTo) );//浣跨敤鐜�
		}
		else{
			PlayerList.get(i).setBPG(0);
			PlayerList.get(i).setAPG(0);
			PlayerList.get(i).setMPG (0);
			PlayerList.get(i).setFieldGoalPercentage(0);
			PlayerList.get(i).setThreePGPercentage (0);
			PlayerList.get(i).setFTPercentage (0);
			PlayerList.get(i).setOff (0);//杩涙敾鏁�
			PlayerList.get(i).setOffPG (0);
			PlayerList.get(i).setDef (0);//闃插畧鏁�
			PlayerList.get(i).setDefPG (0);
			PlayerList.get(i).setStealPG(0);
			PlayerList.get(i).setRPG (0);
			PlayerList.get(i).setToPG (0);
			PlayerList.get(i).setFoulPG (0);
			PlayerList.get(i).setPPG (0);
			PlayerList.get(i).setEff (0);
			PlayerList.get(i).setGmsc(0);
			PlayerList.get(i).setTruePercentage(0);
			PlayerList.get(i).setShootEff (0);//鎶曠鏁堢巼锛�	
			PlayerList.get(i).setBackboardEff (0);//绡澘鐜囷紝		
			PlayerList.get(i).setOffBEff (0);//杩涙敾绡澘鐜囷紝		
			PlayerList.get(i).setDefBEff (0);//闃插畧绡澘鐜囷紝		
			PlayerList.get(i).setAssitEff (0);//鍔╂敾鐜囷紝		
			PlayerList.get(i).setStealEff (0);//鎶㈡柇鐜囷紝		
			PlayerList.get(i).setRejectionEff (0);//鐩栧附鐜囷紝		
			PlayerList.get(i).setToEff (0);//澶辫鐜囷紝		
			PlayerList.get(i).setUseEff (0);
		}
		}
			
		//
		//p.addInfo(AllInfo);
	}
	
	public PlayerDataPO getInfo(String name,String season) {
		// TODO Auto-generated method stub
		PlayerDataPO res = pio.WriteOut(name,season);
		return res;
	}
	public PlayerDataPO[] setOrder(final String orderName,boolean isASC,String season,final boolean isAVG) {
		// TODO Auto-generated method stub
		//p.setOrder(orderName, isASC);
		PlayerDataPO[] orgin = getAllInfo(season);
		PlayerList.clear();
		for(int i = 0;i<orgin.length;i++){
			PlayerList.add(orgin[i]);
		}
		Comparator<PlayerDataPO> comparator = new Comparator<PlayerDataPO>(){

			public int compare(PlayerDataPO p1, PlayerDataPO p2) {
				// TODO Auto-generated method stub
				if(orderName.equals("球员名称")){
					
				return p1.getName().compareTo(p2.getName());
				}
				else if(orderName.equals("所属球队")){
					return p1.getTeamName().compareTo(p2.getTeamName());
				}
				else if(orderName.equals("参赛场数")){
					return p1.getGP()-p2.getGP();
				}
				else if(orderName.equals("先发场数")){
					return p1.getGS()-p2.getGS();
				}
				else if(orderName.equals("篮板数")){
					if(isAVG==false){
					return p1.getBackboard()-p2.getBackboard();
					}
					else{
						if(p1.getBPG()-p2.getBPG()>0){
						return 1;
						}
						else if(p1.getBPG()-p2.getBPG()<0){
							return -1;
						}
						else{
							return 0;
						}
					}
				}
				else if(orderName.equals("助攻数")){
					if(isAVG==false){
					return p1.getAssist()-p2.getAssist();
					}
					else{
						if(p1.getAPG()-p2.getAPG()>0){
							return 1;
							}
							else if(p1.getAPG()-p2.getAPG()<0){
								return -1;
							}
							else{
								return 0;
							}
					}
				}
				else if(orderName.equals("在场时间")){
					if(isAVG==false){
						if(p1.getMinutesOnField()-p2.getMinutesOnField()>0){
							return 1;
							}
							else if(p1.getMinutesOnField()-p2.getMinutesOnField()<0){
								return -1;
							}
							else{
								return 0;
							}
					}
					else{
						if(p1.getMPG()-p2.getMPG()>0){
							return 1;
							}
							else if(p1.getMPG()-p2.getMPG()<0){
								return -1;
							}
							else{
								return 0;
							}
					}
				}
				else if(orderName.equals("投篮命中率")){
					if(p1.getFieldGoalPercentage()-p2.getFieldGoalPercentage()>0){
						return 1;
						}
						else if(p1.getFieldGoalPercentage()-p2.getFieldGoalPercentage()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getFieldGoalPercentage()).compareTo(String.valueOf(p2.getFieldGoalPercentage()));
				}
				else if(orderName.equals("三分命中率")){
					if(p1.getThreePGPercentage()-p2.getThreePGPercentage()>0){
						return 1;
						}
						else if(p1.getThreePGPercentage()-p2.getThreePGPercentage()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getThreePGPercentage()).compareTo(String.valueOf(p2.getThreePGPercentage()));
				}
				else if(orderName.equals("罚球命中率")){
					if(p1.getFTPercentage()-p2.getFTPercentage()>0){
						return 1;
						}
						else if(p1.getFTPercentage()-p2.getFTPercentage()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getFTPercentage()).compareTo(String.valueOf(p2.getFTPercentage()));
				}
				else if(orderName.equals("进攻数")){
					if(isAVG==false){
						if(p1.getOff()-p2.getOff()>0){
							return 1;
							}
							else if(p1.getOff()-p2.getOff()<0){
								return -1;
							}
							else{
								return 0;
							}
					//return String.valueOf(p1.getOff()).compareTo(String.valueOf(p2.getOff()));
					}
					else{
						if(p1.getOffPG()-p2.getOffPG()>0){
							return 1;
							}
							else if(p1.getOffPG()-p2.getOffPG()<0){
								return -1;
							}
							else{
								return 0;
							}
					}
				}
				else if(orderName.equals("防守数")){
					if(isAVG==false){
						if(p1.getDef()-p2.getDef()>0){
							return 1;
							}
							else if(p1.getDef()-p2.getDef()<0){
								return -1;
							}
							else{
								return 0;
							}
					//return String.valueOf(p1.getDef()).compareTo(String.valueOf(p2.getDef()));
					}
					else{
						if(p1.getDefPG()-p2.getDefPG()>0){
							return 1;
							}
							else if(p1.getDefPG()-p2.getDefPG()<0){
								return -1;
							}
							else{
								return 0;
							}
						//return String.valueOf(p1.getDefPG()).compareTo(String.valueOf(p2.getDefPG()));
					}
				}
				else if(orderName.equals("抢断数")){
					if(isAVG==false){
						if(p1.getSteal()-p2.getSteal()>0){
							return 1;
							}
							else if(p1.getSteal()-p2.getSteal()<0){
								return -1;
							}
							else{
								return 0;
							}
					//return String.valueOf(p1.getSteal()).compareTo(String.valueOf(p2.getSteal()));
					}
					else{
						if(p1.getDefPG()-p2.getDefPG()>0){
							return 1;
							}
							else if(p1.getDefPG()-p2.getDefPG()<0){
								return -1;
							}
							else{
								return 0;
							}
					}
				}
				else if(orderName.equals("盖帽数")){
					if(isAVG==false){
						if(p1.getRejection()-p2.getRejection()>0){
							return 1;
							}
							else if(p1.getRejection()-p2.getRejection()<0){
								return -1;
							}
							else{
								return 0;
							}
					//return String.valueOf(p1.getRejection()).compareTo(String.valueOf(p2.getRejection()));
					}
					else{
						if(p1.getRPG()-p2.getRPG()>0){
							return 1;
							}
							else if(p1.getRPG()-p2.getRPG()<0){
								return -1;
							}
							else{
								return 0;
							}
					}
				}
				else if(orderName.equals("失误数")){
					if(isAVG==false){
						if(p1.getTo()-p2.getTo()>0){
							return 1;
							}
							else if(p1.getTo()-p2.getTo()<0){
								return -1;
							}
							else{
								return 0;
							}
					//return String.valueOf(p1.getTo()).compareTo(String.valueOf(p2.getTo()));
					}
					else{
						if(p1.getToPG()-p2.getToPG()>0){
							return 1;
							}
							else if(p1.getToPG()-p2.getToPG()<0){
								return -1;
							}
							else{
								return 0;
							}
					}
				}
				else if(orderName.equals("得分")){
					if(isAVG==false){
						if(p1.getPTS()-p2.getPTS()>0){
							return 1;
							}
							else if(p1.getPTS()-p2.getPTS()<0){
								return -1;
							}
							else{
								return 0;
							}
					//return String.valueOf(p1.getPTS()).compareTo(String.valueOf(p2.getPTS()));
					}
					else{
						if(p1.getPPG()-p2.getPPG()>0){
							return 1;
							}
							else if(p1.getPPG()-p2.getPPG()<0){
								return -1;
							}
							else{
								return 0;
							}
					}
				}
				else if(orderName.equals("效率")){
					if(p1.getEff()-p2.getEff()>0){
						return 1;
						}
						else if(p1.getEff()-p2.getEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getEff()).compareTo(String.valueOf(p2.getEff()));
				}
				else if(orderName.equals("GmSc")){
					if(p1.getGmsc()-p2.getGmsc()>0){
						return 1;
						}
						else if(p1.getGmsc()-p2.getGmsc()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getGmsc()).compareTo(String.valueOf(p2.getGmsc()));
				}
				
				else if(orderName.equals("真实命中率")){
					if(p1.getTruePercentage()-p2.getTruePercentage()>0){
						return 1;
						}
						else if(p1.getTruePercentage()-p2.getTruePercentage()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getTruePercentage()).compareTo(String.valueOf(p2.getTruePercentage()));
				}
				else if(orderName.equals("投篮效率")){
					if(p1.getShootEff()-p2.getShootEff()>0){
						return 1;
						}
						else if(p1.getShootEff()-p2.getShootEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getShootEff()).compareTo(String.valueOf(p2.getShootEff()));
				}
				else if(orderName.equals("篮板率")){
					if(p1.getBackboardEff()-p2.getBackboardEff()>0){
						return 1;
						}
						else if(p1.getBackboardEff()-p2.getBackboardEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getBackboardEff()).compareTo(String.valueOf(p2.getBackboardEff()));
				}
				else if(orderName.equals("进攻篮板率")){
					if(p1.getOffBEff()-p2.getOffBEff()>0){
						return 1;
						}
						else if(p1.getOffBEff()-p2.getOffBEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getOffBEff()).compareTo(String.valueOf(p2.getOffBEff()));
				}
				else if(orderName.equals("防守篮板率")){
					if(p1.getDefBEff()-p2.getDefBEff()>0){
						return 1;
						}
						else if(p1.getDefBEff()-p2.getDefBEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getDefBEff()).compareTo(String.valueOf(p2.getDefBEff()));
				}
				else if(orderName.equals("助攻率")){
					if(p1.getAssitEff()-p2.getAssitEff()>0){
						return 1;
						}
						else if(p1.getAssitEff()-p2.getAssitEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getAssitEff()).compareTo(String.valueOf(p2.getAssitEff()));
				}
				else if(orderName.equals("抢断率")){
					if(p1.getStealEff()-p2.getStealEff()>0){
						return 1;
						}
						else if(p1.getStealEff()-p2.getStealEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getStealEff()).compareTo(String.valueOf(p2.getStealEff()));
				}
				else if(orderName.equals("盖帽率")){
					if(p1.getRejectionEff()-p2.getRejectionEff()>0){
						return 1;
						}
						else if(p1.getRejectionEff()-p2.getRejectionEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getRejectionEff()).compareTo(String.valueOf(p2.getRejectionEff()));
				}
				else if(orderName.equals("失误率")){
					if(p1.getToEff()-p2.getToEff()>0){
						return 1;
						}
						else if(p1.getToEff()-p2.getToEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getToEff()).compareTo(String.valueOf(p2.getToEff()));
				}
				else if(orderName.equals("使用率")){
					if(p1.getUseEff()-p2.getUseEff()>0){
						return 1;
						}
						else if(p1.getUseEff()-p2.getUseEff()<0){
							return -1;
						}
						else{
							return 0;
						}
					//return String.valueOf(p1.getUseEff()).compareTo(String.valueOf(p2.getUseEff()));
				}
				
				else{
					return 0;
				}
			}
			
		};
		Collections.sort(PlayerList,comparator);
		PlayerDataPO[] res = new PlayerDataPO[PlayerList.size()];
		if(isASC==true){
		for(int i = 0;i<res.length;i++){
			res[i] = PlayerList.get(i);
			System.out.println(res[i].getPTS()+";"+res[i].getName());
		}
		}
		else{
			for(int i = 0;i<res.length;i++){
				res[i] = PlayerList.get(res.length-1-i);
			}
		}
		return res;
	}
	public PlayerDataPO[] getAllInfo(String season) {
		// TODO Auto-generated method stub
		ArrayList<PlayerDataPO> res = new ArrayList<PlayerDataPO>();
		File root = new File("./迭代一数据/players/info");//从ser文件中读取所有数据
		File[] files = root.listFiles();
		for(File file:files){
			PlayerDataPO temp = pio.WriteOut(file.getName(),season);
			res.add(temp);
		}
		
		PlayerDataPO[] res2 = new PlayerDataPO[res.size()];
		for(int i =0;i<res2.length;i++){
			res2[i] = res.get(i);
		}
		return res2 ;
	}

	public PlayerDataPO[] getSelect(String position,String Union,String season){//都是英文，如Union可以是“E”或“W”,默认的话不要改就行
		ArrayList<PlayerDataPO> res = new ArrayList<PlayerDataPO>();
		PlayerDataPO[] temp = getAllInfo(season);
		if(position.equals("null")){
			if(Union.equals("null")){
				return temp;
			}
			else if(Union.equals("Southeast")){
				for(int i = 0;i<temp.length;i++){
					if((temp[i].getTeamName().equals("ATL"))
							||(temp[i].getTeamName().equals("CHA"))
							||(temp[i].getTeamName().equals("MIA"))
							||(temp[i].getTeamName().equals("ORL"))
							||(temp[i].getTeamName().equals("WAS"))){
						res.add(temp[i]);
					}
				}
			}
			else if(Union.equals("Central")){
				for(int i = 0;i<temp.length;i++){
					if((temp[i].getTeamName().equals("CHI"))
							||(temp[i].getTeamName().equals("DET"))
							||(temp[i].getTeamName().equals("CLE"))
							||(temp[i].getTeamName().equals("IND"))
							||(temp[i].getTeamName().equals("MIL"))){
						res.add(temp[i]);
					}
				}
			}
			else if(Union.equals("Atlantic")){
				for(int i = 0;i<temp.length;i++){
					if((temp[i].getTeamName().equals("BOS"))
							||(temp[i].getTeamName().equals("BKN"))
							||(temp[i].getTeamName().equals("NYK"))
							||(temp[i].getTeamName().equals("PHI"))
							||(temp[i].getTeamName().equals("TOR"))){
						res.add(temp[i]);
					}
				}
			}
			else if(Union.equals("Southwest")){
				for(int i = 0;i<temp.length;i++){
					if((temp[i].getTeamName().equals("DAL"))
							||(temp[i].getTeamName().equals("HOU"))
							||(temp[i].getTeamName().equals("MEM"))
							||(temp[i].getTeamName().equals("NOP"))
							||(temp[i].getTeamName().equals("SAS"))){
						res.add(temp[i]);
					}
				}
			}
			else if(Union.equals("Northwest")){
				for(int i = 0;i<temp.length;i++){
					if((temp[i].getTeamName().equals("DEN"))
							||(temp[i].getTeamName().equals("MIN"))
							||(temp[i].getTeamName().equals("OKC"))
							||(temp[i].getTeamName().equals("POR"))
							||(temp[i].getTeamName().equals("UTA"))){
						res.add(temp[i]);
					}
				}
			}
			else if(Union.equals("Pacific")){
				for(int i = 0;i<temp.length;i++){
					if((temp[i].getTeamName().equals("GSW"))
							||(temp[i].getTeamName().equals("LAC"))
							||(temp[i].getTeamName().equals("LAL"))
							||(temp[i].getTeamName().equals("PHX"))
							||(temp[i].getTeamName().equals("SAC"))){
						res.add(temp[i]);
					}
				}
			}
		}
		else{
			if(Union.equals("null")){
				for(int i =0 ;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
						res.add(temp[i]);
					}
				}
			}
			else if(Union.equals("Southeast")){
				for(int i = 0;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
					if((temp[i].getTeamName().equals("ATL"))
							||(temp[i].getTeamName().equals("CHA"))
							||(temp[i].getTeamName().equals("MIA"))
							||(temp[i].getTeamName().equals("ORL"))
							||(temp[i].getTeamName().equals("WAS"))){
						res.add(temp[i]);
					}
					}
				}
			}
			else if(Union.equals("Central")){
				for(int i = 0;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
					if((temp[i].getTeamName().equals("CHI"))
							||(temp[i].getTeamName().equals("DET"))
							||(temp[i].getTeamName().equals("CLE"))
							||(temp[i].getTeamName().equals("IND"))
							||(temp[i].getTeamName().equals("MIL"))){
						res.add(temp[i]);
					}
					}
				}
			}
			else if(Union.equals("Atlantic")){
				for(int i = 0;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
					if((temp[i].getTeamName().equals("BOS"))
							||(temp[i].getTeamName().equals("BKN"))
							||(temp[i].getTeamName().equals("NYK"))
							||(temp[i].getTeamName().equals("PHI"))
							||(temp[i].getTeamName().equals("TOR"))){
						res.add(temp[i]);
					}
					}
				}
			}
			else if(Union.equals("Southwest")){
				for(int i = 0;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
					if((temp[i].getTeamName().equals("DAL"))
							||(temp[i].getTeamName().equals("HOU"))
							||(temp[i].getTeamName().equals("MEM"))
							||(temp[i].getTeamName().equals("NOP"))
							||(temp[i].getTeamName().equals("SAS"))){
						res.add(temp[i]);
					}
					}
				}
			}
			else if(Union.equals("Northwest")){
				for(int i = 0;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
					if((temp[i].getTeamName().equals("DEN"))
							||(temp[i].getTeamName().equals("MIN"))
							||(temp[i].getTeamName().equals("OKC"))
							||(temp[i].getTeamName().equals("POR"))
							||(temp[i].getTeamName().equals("UTA"))){
						res.add(temp[i]);
					}
					}
				}
			}
			else if(Union.equals("Pacific")){
				for(int i = 0;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
					if((temp[i].getTeamName().equals("GSW"))
							||(temp[i].getTeamName().equals("LAC"))
							||(temp[i].getTeamName().equals("LAL"))
							||(temp[i].getTeamName().equals("PHX"))
							||(temp[i].getTeamName().equals("SAC"))){
						res.add(temp[i]);
					}
					}
				}
			}
		}
		PlayerDataPO[] res2 = new PlayerDataPO[res.size()];
		for(int i =0;i<res2.length;i++){
			res2[i] = res.get(i);
		}
		return res2 ;
	}
	public PlayerDataPO[] getSearch(String keys,String season){
		PlayerDataPO[] temp = getAllInfo(season);
		ArrayList<PlayerDataPO> res = new ArrayList<PlayerDataPO>();
		
		for(int i = 0;i<temp.length;i++){
			
			if(temp[i].getName().contains(keys)){
			res.add(temp[i]);
			}
			
		}
		PlayerDataPO[] res2 = new PlayerDataPO[res.size()];
		for(int i =0;i<res2.length;i++){
			res2[i] = res.get(i);
		}
		return res2 ;
	}
	public PlayerDataPO[] getAllSearch(String namekeys,String position,String Union,String season){
		PlayerDataPO[] res = getSelect(position, Union,season);
		if(namekeys.equals("null")){
			return res;
		}
		else {
			ArrayList<PlayerDataPO> temp = new ArrayList<PlayerDataPO>();
			for(int i =0;i<res.length;i++){
				if(res[i].getName().contains(namekeys)){
					temp.add(res[i]);
				}
			}
		PlayerDataPO[] res2 = new PlayerDataPO[temp.size()];
			for(int i = 0;i<temp.size();i++){
				res2[i] =  temp.get(i);
			}
		return res2;
		}
	}
	public String initialize(String filepath,String season){
		//if(p.judge()==true){
//		File root = new File(filepath);
//		File[] files = root.listFiles();
//		for(File file:files){
//			//System.out.println(file.getName());
//			analysData(file.getName());
//			
//		}
//		return "initialization completed.";
		//}
		//else{
		//	return "has initialized";
		//}
		analysData(filepath,season);
		return "ok";
	}
	public PlayerDataPO[] getFirstFifty(String orderName, PlayerDataPO[] orgin,boolean isAVG) {
		// TODO Auto-generated method stub
		//盖帽，抢断，犯规，失误，分钟，效率，投篮，三分，罚球，两双
		PlayerList.clear();
		for(int i = 0;i<orgin.length;i++){
			PlayerList.add(orgin[i]);
		}
		Comparator<PlayerDataPO> comparator = new Comparator<PlayerDataPO>(){

			public int compare(PlayerDataPO p1, PlayerDataPO p2) {
				// TODO Auto-generated method stub
				
			return 0;
			}
			
		};
//		if(orderName.equals("两双")){
//			return null;
//		}
//		else{
//			PlayerDataPO[] res = setOrder(orderName,false,season,isAVG);
//			return res;
//		}
		return null;
	}
}