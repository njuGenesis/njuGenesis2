package bussinesslogic.player;

import java.io.File;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
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
	//界面层接口方法
	String playerpath;
	String matchpath;
	public PlayerLogic(){
		playerpath = "迭代一数据/players/info";
		matchpath = "迭代一数据/matches";
	}
	public PlayerLogic(String player,String match){
	playerpath = 	player;
	matchpath = match;
	}
	public void analysData(String filepath,String season,String matchPath) {
		// TODO Auto-generated method stub
		PlayerList.clear();
		File root = new File(filepath);
		File[] files = root.listFiles();
		for(File file:files){		
		String filePath = filepath+"\\/" + file.getName();
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
		temp.setSeason(season);
		PlayerList.add(temp);
		
		}
		//loop over
		getAllMatch(matchPath,season);
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
				firsttotaltime = firsttotaltime + fminute+fseconds/(double)60;
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
				secondtotaltime = secondtotaltime + fminute+fseconds/(double)60;
			
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
					PlayerList.get(k).setMinutesOnField(PlayerList.get(k).getMinutesOnField()+minute+seconds/(double)60);
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
					
//					if(PlayerList.get(k).getGP()<=5){
//						PlayerList.get(k).setRecentBackboard(PlayerList.get(k).getRecentBackboard()+Integer.valueOf(temp[11]));
//						PlayerList.get(k).setRecentAssist(PlayerList.get(k).getRecentAssist()+Integer.valueOf(temp[12]));
//						PlayerList.get(k).setRecentPTS(PlayerList.get(k).getRecentPTS()+Integer.valueOf(temp[17]));
//					}
//					else{
//						
//					}
					PlayerList.get(k).addBackboard(Integer.valueOf(temp[11]));
					
					PlayerList.get(k).addAssist(Integer.valueOf(temp[12]));
					
					
					PlayerList.get(k).setAssist(PlayerList.get(k).getAssist() + Integer.valueOf(temp[12]));
					
					PlayerList.get(k).setRejection (PlayerList.get(k).getRejection() + Integer.valueOf(temp[14]));
					
					PlayerList.get(k).setSteal(PlayerList.get(k).getSteal() + Integer.valueOf(temp[13]));
					
					PlayerList.get(k).setTo (PlayerList.get(k).getTo() + Integer.valueOf(temp[15]));
					
					PlayerList.get(k).setFoul(PlayerList.get(k).getFoul() + Integer.valueOf(temp[16]));
					try{
						PlayerList.get(k).setPTS (PlayerList.get(k).getPTS() + Integer.valueOf(temp[17]));
						PlayerList.get(k).addPTS(Integer.valueOf(temp[17]));
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
					PlayerList.get(k).setMinutesOnField(PlayerList.get(k).getMinutesOnField()+minute+seconds/(double)60);
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
					
					PlayerList.get(k).addBackboard(Integer.valueOf(temp[11]));
					
					PlayerList.get(k).addAssist(Integer.valueOf(temp[12]));
					
					PlayerList.get(k).setAssist(PlayerList.get(k).getAssist() + Integer.valueOf(temp[12]));
					
					PlayerList.get(k).setRejection (PlayerList.get(k).getRejection() + Integer.valueOf(temp[14]));
					
					PlayerList.get(k).setSteal(PlayerList.get(k).getSteal() + Integer.valueOf(temp[13]));
					
					PlayerList.get(k).setTo (PlayerList.get(k).getTo() + Integer.valueOf(temp[15]));
					
					PlayerList.get(k).setFoul(PlayerList.get(k).getFoul() + Integer.valueOf(temp[16]));
					try{
						PlayerList.get(k).setPTS (PlayerList.get(k).getPTS() + Integer.valueOf(temp[17]));
						PlayerList.get(k).addPTS(Integer.valueOf(temp[17]));
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
		if(PlayerList.get(i).getGP()>5){
			ArrayList<Integer> tempb = PlayerList.get(i).getRecentBackboard();
			ArrayList<Integer> tempa = PlayerList.get(i).getRecentAssist();
			ArrayList<Integer> tempp = PlayerList.get(i).getRecentPTS();
			int recentb = 0;
			int recenta = 0;
			int recentp = 0;
			for(int j = tempb.size()-5;j<tempb.size();j++){
				recentb = recentb + tempb.get(j);
			}
			for(int j = tempa.size()-5;j<tempa.size();j++){
				recenta = recenta + tempa.get(j);
			}
			for(int j = tempp.size()-5;j<tempp.size();j++){
				recentp = recentp + tempp.get(j);
			}
			double avgb = recentb/(double)5;
			double avga = recenta/(double)5;
			double avgp = recentp/(double)5;
			double avgB = (backboard-recentb)/(double)(GP-5);
			double avgA = (assist - recenta)/(double)(GP-5);
			double avgP = (PTS - recentp)/(double)(GP-5);
			PlayerList.get(i).setRecentAvgA(avga);
			PlayerList.get(i).setRecentAvgB(avgb);
			PlayerList.get(i).setRecentAvgP(avgp);
			if(avgB!=0){
			PlayerList.get(i).setBProgressPecnetage((avgb-avgB)/avgB);
			}
			if(avgA!=0){
			PlayerList.get(i).setAProgressPecentage((avga-avgA)/avgA);
			}
			if(avgP!=0){
			PlayerList.get(i).setPProgressPecentage((avgp-avgP)/avgP);
			}
		}
		}
			
		//
		//p.addInfo(AllInfo);
	}
	
	public PlayerDataPO getInfo(String name,String season) {
		// TODO Auto-generated method stub
		String temp = name;
		if(name.endsWith("Jr.")){
			temp = name.replaceAll("\\.", "");
		}
		PlayerDataPO res = pio.WriteOut(temp,season);
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
			//System.out.println(res[i].getPTS()+";"+res[i].getName());
		}
		}
		else{
			for(int i = 0;i<res.length;i++){
				res[i] = PlayerList.get(res.length-1-i);
				//System.out.println(res[i].getPTS()+";"+res[i].getName());
			}
		}
		return res;
	}
	public PlayerDataPO[] getAllInfo(String season) {
		// TODO Auto-generated method stub
		ArrayList<PlayerDataPO> res = new ArrayList<PlayerDataPO>();
		File root;
		
			root = new File(playerpath);
		
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
			
			if(temp[i].getName().toLowerCase().contains(keys.toLowerCase())){
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
				if(res[i].getName().toLowerCase().contains(namekeys.toLowerCase())){
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
		//filepath = filepath.replaceAll("\\", "/");
		//System.out.println(filepath);
	    String playerpath = filepath+"/players/info";
	    String matchpath = filepath+"/matches";
		analysData(playerpath,season,matchpath);
		return "ok";
	}
	public PlayerDataPO[] getFirstFifty(final String orderName, PlayerDataPO[] orgin,final boolean isAVG) {
		// TODO Auto-generated method stub
		//盖帽，抢断，犯规，失误，分钟，效率，投篮，三分，罚球，两双
		PlayerList.clear();
		for(int i = 0;i<orgin.length;i++){
			PlayerList.add(orgin[i]);
		}
		Comparator<PlayerDataPO> comparator = new Comparator<PlayerDataPO>(){

			public int compare(PlayerDataPO p1, PlayerDataPO p2) {
				// TODO Auto-generated method stub
				if(orderName.equals("盖帽")){
					if(isAVG==true){
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
					
					else{
					if(p1.getRejection()-p2.getRejection()>0){
						return 1;
						}
						else if(p1.getRejection()-p2.getRejection()<0){
							return -1;
						}
						else{
							return 0;
						}
					}
				}
				else if(orderName.equals("抢断")){
					if(isAVG==true){
						if(p1.getStealPG()-p2.getStealPG()>0){
							return 1;
							}
							else if(p1.getStealPG()-p2.getStealPG()<0){
								return -1;
							}
							else{
								return 0;
							}
						}
					
					else{
					if(p1.getSteal()-p2.getSteal()>0){
						return 1;
						}
						else if(p1.getSteal()-p2.getSteal()<0){
							return -1;
						}
						else{
							return 0;
						}
					}
				}
				else if(orderName.equals("犯规")){
					if(isAVG==true){
						if(p1.getFoulPG()-p2.getFoulPG()>0){
							return 1;
							}
							else if(p1.getFoulPG()-p2.getFoulPG()<0){
								return -1;
							}
							else{
								return 0;
							}
						}
					
					else{
					if(p1.getFoul()-p2.getFoul()>0){
						return 1;
						}
						else if(p1.getFoul()-p2.getFoul()<0){
							return -1;
						}
						else{
							return 0;
						}
					}
				}
				else if(orderName.equals("失误")){
					if(isAVG==true){
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
					
					else{
					if(p1.getTo()-p2.getTo()>0){
						return 1;
						}
						else if(p1.getTo()-p2.getTo()<0){
							return -1;
						}
						else{
							return 0;
						}
					}
				}
				else if(orderName.equals("分钟")){
					if(isAVG==true){
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
					
					else{
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
					}
				
				else if(orderName.equals("投篮")){
					if(p1.getFieldGoalPercentage()-p2.getFieldGoalPercentage()>0){
						return 1;
						}
						else if(p1.getFieldGoalPercentage()-p2.getFieldGoalPercentage()<0){
							return -1;
						}
						else{
							return 0;
						}
					
				}
				else if(orderName.equals("三分")){
					if(p1.getThreePGPercentage()-p2.getThreePGPercentage()>0){
						return 1;
						}
						else if(p1.getThreePGPercentage()-p2.getThreePGPercentage()<0){
							return -1;
						}
						else{
							return 0;
						}
				}
				else if(orderName.equals("罚球")){
					if(p1.getFTPercentage()-p2.getFTPercentage()>0){
						return 1;
						}
						else if(p1.getFTPercentage()-p2.getFTPercentage()<0){
							return -1;
						}
						else{
							return 0;
						}
				}
				else if(orderName.equals("两双")){
					if(p1.getDouble()-p2.getDouble()>0){
						return 1;
						}
						else if(p1.getDouble()-p2.getDouble()<0){
							return -1;
						}
						else{
							return 0;
						}
				}
				else{
				return 0;
				}
			}
			
		};
		Collections.sort(PlayerList, comparator);
//		if(orderName.equals("两双")){
//			return null;
//		}
//		else{
//			PlayerDataPO[] res = setOrder(orderName,false,season,isAVG);
//			return res;
//		}
		PlayerDataPO[] res = new PlayerDataPO[50];
		for(int i = 0;i<50;i++){
			res[i] = PlayerList.get(PlayerList.size()-i-1);
			System.out.println(res[i].getName()+";"+res[i].getStealPG()+";"+i);
					}
		return res;
	}
	public PlayerDataPO[] hotPlayerToday(String season,String date,final String key){
		//得分，篮板，助攻，盖帽，抢断
		ArrayList<PlayerDataPO> temp = new ArrayList<PlayerDataPO>();
		String title = season+"_"+date;
		File root;
		
			root = new File(matchpath);
		
				File[] files = root.listFiles();
				for(File file:files){		
					if(file.getName().startsWith(title)){
						MatchDataPO m = g.readMatchfile(file.getAbsolutePath());
						
						for(int i = 1;i<m.firstTeamInfo.size();i++){
							String[] firstTemp = m.firstTeamInfo.get(i).split(";");
							try{
							PlayerDataPO p = new PlayerDataPO();
							p.setName(firstTemp[0]);
							p.setTeamName(m.firstTeamInfo.get(0));
							
							try{
							PlayerDataPO tempp = getInfo(firstTemp[0],season);
							p.setPosition(tempp.getPosition());
							p.setNumber(tempp.getNumber());
							}catch(Exception e){
							p.setPosition("unknown");
							p.setNumber("unknown");
							}
							
							p.setPTS(Integer.valueOf(firstTemp[17]));
							p.setBackboard(Integer.valueOf(firstTemp[11]));
							p.setAssist(Integer.valueOf(firstTemp[12]));
							p.setRejection(Integer.valueOf(firstTemp[14]));
							p.setSteal(Integer.valueOf(firstTemp[13]));
							temp.add(p);
							}
							catch(Exception e){
								System.out.println(file.getAbsolutePath());
							}
						}
						for(int i = 1;i<m.secondTeamInfo.size();i++){
							String[] firstTemp = m.secondTeamInfo.get(i).split(";");
							try{
							PlayerDataPO p = new PlayerDataPO();
							
							p.setName(firstTemp[0]);
							p.setTeamName(m.secondTeamInfo.get(0));
							
							try{
							PlayerDataPO tempp = getInfo(firstTemp[0],season);
							p.setPosition(tempp.getPosition());
							p.setNumber(tempp.getNumber());
							}catch(Exception e){
							p.setPosition("unknown");
							p.setNumber("unknown");
							}
							p.setPTS(Integer.valueOf(firstTemp[17]));
							p.setBackboard(Integer.valueOf(firstTemp[11]));
							p.setAssist(Integer.valueOf(firstTemp[12]));
							p.setRejection(Integer.valueOf(firstTemp[14]));
							p.setSteal(Integer.valueOf(firstTemp[13]));
							temp.add(p);
							}
							catch(Exception e){
								System.out.println(file.getAbsolutePath());
							}
						}
					}
				}
				
						temp.sort(new Comparator<PlayerDataPO>(){

							public int compare(PlayerDataPO p1,
									PlayerDataPO p2) {
								// TODO Auto-generated method stub
								if(key.equals("得分")){
									return p1.getPTS()-p2.getPTS();
								}
								else if(key.equals("篮板")){
									return p1.getBackboard()-p2.getBackboard();
								
								}
								else if(key.equals("助攻")){
									return p1.getAssist()-p2.getAssist();
								}
								else if(key.equals("盖帽")){
									return p1.getRejection() - p2.getRejection();
								}
								else if(key.equals("抢断")){
									return p1.getSteal()-p2.getSteal();
								}
								else{
									return 0;
								}
							}
						});
						
						PlayerDataPO[] res = new PlayerDataPO[5];
						
						for(int i = 0;i<5;i++){
							if(temp.size()!=0){
							res[i] = temp.get(temp.size()-i-1);
							}
							else{
								res[i] = new PlayerDataPO();
							}
						}
						
					
		return res;
	}
	public PlayerDataPO[] hotPlayerSeason(String season,final String key){
		//场均得分，场均篮板，场均助攻，场均盖帽，场均抢断，三分命中率，投篮命中率，罚球命中率
		PlayerDataPO[] temp = getAllInfo(season);
		ArrayList<PlayerDataPO> temparr = new ArrayList<PlayerDataPO>();
		for(int i = 0;i<temp.length;i++){
			temparr.add(temp[i]);
		}
		temparr.sort(new Comparator<PlayerDataPO>(){

			public int compare(PlayerDataPO p1, PlayerDataPO p2) {
				// TODO Auto-generated method stub
				if(key.equals("场均得分")){
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
				else if(key.equals("场均篮板")){
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
				else if(key.equals("场均助攻")){
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
				else if(key.equals("场均盖帽")){
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
				else if(key.equals("场均抢断")){
					if(p1.getStealPG()-p2.getStealPG()>0){
						return 1;
					}
					else if(p1.getStealPG()-p2.getStealPG()<0){
						return -1;
					}
					else{
						return 0;
					}
				}
				else if(key.equals("三分命中率")){
					if(p1.getThreePGPercentage()-p2.getThreePGPercentage()>0){
						return 1;
					}
					else if(p1.getThreePGPercentage()-p2.getThreePGPercentage()<0){
						return -1;
					}
					else{
						return 0;
					}
				}
				else if(key.equals("投篮命中率")){
					if(p1.getFieldGoalPercentage()-p2.getFieldGoalPercentage()>0){
						return 1;
					}
					else if(p1.getFieldGoalPercentage()-p2.getFieldGoalPercentage()<0){
						return -1;
					}
					else{
						return 0;
					}
				}
				else if(key.equals("罚球命中率")){
					if(p1.getFTPercentage()-p2.getFTPercentage()>0){
						return 1;
					}
					else if(p1.getFTPercentage()-p2.getFTPercentage()<0){
						return -1;
					}
					else{
						return 0;
					}
				}
				return 0;
			}
			
		});
		PlayerDataPO[] res = new PlayerDataPO[5];
		for(int i = 0;i<5;i++){
			res[i] = temparr.get(temparr.size()-1-i);
		}
		return res;
	}
	public PlayerDataPO[] progressPlayer(String season,String key){
		PlayerDataPO[] temp = getAllInfo(season);
		ArrayList<PlayerDataPO> temparr = new ArrayList<PlayerDataPO>();
		for(int i = 0;i<temp.length;i++){
			temparr.add(temp[i]);
		}
		if(key.equals("场均得分")){
			temparr.sort(new Comparator<PlayerDataPO>(){


				public int compare(PlayerDataPO p1, PlayerDataPO p2) {
					// TODO Auto-generated method stub
					if(p1.getPProgressPecentage()-p2.getPProgressPecentage()>0){
						return 1;
					}
					else if(p1.getPProgressPecentage()-p2.getPProgressPecentage()<0){
						return -1;
					}
					else{
					return 0;
					}
				}
				
			});
		}
		else if(key.equals("场均篮板")){
			temparr.sort(new Comparator<PlayerDataPO>(){


				public int compare(PlayerDataPO p1, PlayerDataPO p2) {
					// TODO Auto-generated method stub
					if(p1.getBProgressPecentage()-p2.getBProgressPecentage()>0){
						return 1;
					}
					else if(p1.getBProgressPecentage()-p2.getBProgressPecentage()<0){
						return -1;
					}
					else{
					return 0;
					}
				}
				
			});
		}
		else if(key.equals("场均助攻")){
			temparr.sort(new Comparator<PlayerDataPO>(){


				public int compare(PlayerDataPO p1, PlayerDataPO p2) {
					// TODO Auto-generated method stub
					if(p1.getAProgressPecentage()-p2.getAProgressPecentage()>0){
						return 1;
					}
					else if(p1.getAProgressPecentage()-p2.getAProgressPecentage()<0){
						return -1;
					}
					else{
					return 0;
					}
				}
				
			});
		}
		PlayerDataPO[] res = new PlayerDataPO[5];
		for(int i = 0;i<5;i++){
			res[i] = temparr.get(temparr.size()-1-i);
			//System.out.println(res[i].getName()+";"+res[i].getPProgressPecentage());
		}
		return res;
		
	}
    public PlayerDataPO[] getAllSeasonInfo(String name){
    	ArrayList<PlayerDataPO> res = new ArrayList<PlayerDataPO>();
		File root = new File("playerInfo");//从ser文件中读取所有数据
		File[] files = root.listFiles();
		for(File file:files){
			if(file.getName().contains(name)){
			PlayerDataPO temp = pio.WriteOutAllSeason(file.getName());
			res.add(temp);
		}
		}
		
		PlayerDataPO[] res2 = new PlayerDataPO[res.size()];
		for(int i =0;i<res2.length;i++){
			res2[i] = res.get(i);
		}
		return res2 ;
    
    }
    public String getLatestSeason(){
    	String res = "13-14";
    	File root;
    	//System.out.println(matchpath);
    		root = new File(matchpath);
    	
		File[] files = root.listFiles();
		for(File file:files){
			res = file.getName();
		}
		res = res.substring(0,5);
		return res;
    }
    public String getLatestDate(){
    	String res = "04-27";
    	File root;
    	
    		root = new File(matchpath);
    	
		File[] files = root.listFiles();
		for(File file:files){
			res = file.getName();
		}
		res = res.substring(6,11);
		return res;
    }
	public void updatePlayer(String filename,String season){
		PlayerList.clear();
		
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
		String mathchPath = "";
		
		mathchPath = matchpath+"\\/"+filename;
	
		MatchDataPO tempmatch = g.readMatchfile(mathchPath);
		for(int i = 1;i<tempmatch.firstTeamInfo.size();i++){
			String playername =tempmatch.firstTeamInfo.get(i).split(";")[0];
			
				//把原来的数据加载进来
				PlayerDataPO res = getInfo(playername,season);
			if(res==null){
				//System.out.println("初始化"+playername);
			//如果原来不存在这个数据就初始化
			String filePath = "./迭代一数据/players/info/" + playername;
			
			try{
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
				temp.setSeason(season);
				
				PlayerList.add(temp);
				}catch(Exception e2){
				
			}
			}
			else{
				PlayerList.add(res);
			}
		}
		for(int i = 1;i<tempmatch.secondTeamInfo.size();i++){
			String playername =tempmatch.secondTeamInfo.get(i).split(";")[0];
			
				//把原来的数据加载进来
				PlayerDataPO res = getInfo(playername,season);
			if(res==null){
				
			//如果原来不存在这个数据就初始化
			String filePath = "./迭代一数据/players/info/" + playername;
			
			try{
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
				temp.setSeason(season);
				PlayerList.add(temp);
				}catch(Exception e2){
				
			}
			}
			else {
				PlayerList.add(res);
			}
		}
		
		
		//预先计算
		try{
			for(int i = 1;i<tempmatch.firstTeamInfo.size();i++){//鏁翠釜鐞冮槦鐨勬暟鎹�
				int fminute = 0;
				int fseconds = 0;
				String[] temp =tempmatch.firstTeamInfo.get(i).split(";");
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
			
			for(int i = 1;i<tempmatch.secondTeamInfo.size();i++){//鏁翠釜鐞冮槦鐨勬暟鎹�
				int fminute = 0;
				int fseconds = 0;
				
				String[] temp = tempmatch.secondTeamInfo.get(i).split(";");
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
			for(int i = 1;i<tempmatch.firstTeamInfo.size();i++){				
				
				String[] temp = tempmatch.firstTeamInfo.get(i).split(";");
				
				for(int k = 0;k<PlayerList.size();k++){
					
				if(temp[0].equals(PlayerList.get(k).getName())){
					
					PlayerList.get(k).setTeamName(tempmatch.firstTeamInfo.get(0));
					
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
					PlayerList.get(k).setMinutesOnField(PlayerList.get(k).getMinutesOnField()+minute+seconds/(double)60);
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
					
//					if(PlayerList.get(k).getGP()<=5){
//						PlayerList.get(k).setRecentBackboard(PlayerList.get(k).getRecentBackboard()+Integer.valueOf(temp[11]));
//						PlayerList.get(k).setRecentAssist(PlayerList.get(k).getRecentAssist()+Integer.valueOf(temp[12]));
//						PlayerList.get(k).setRecentPTS(PlayerList.get(k).getRecentPTS()+Integer.valueOf(temp[17]));
//					}
//					else{
//						
//					}
					PlayerList.get(k).addBackboard(Integer.valueOf(temp[11]));
					
					PlayerList.get(k).addAssist(Integer.valueOf(temp[12]));
					
					
					PlayerList.get(k).setAssist(PlayerList.get(k).getAssist() + Integer.valueOf(temp[12]));
					
					PlayerList.get(k).setRejection (PlayerList.get(k).getRejection() + Integer.valueOf(temp[14]));
					
					PlayerList.get(k).setSteal(PlayerList.get(k).getSteal() + Integer.valueOf(temp[13]));
					
					PlayerList.get(k).setTo (PlayerList.get(k).getTo() + Integer.valueOf(temp[15]));
					
					PlayerList.get(k).setFoul(PlayerList.get(k).getFoul() + Integer.valueOf(temp[16]));
					try{
						PlayerList.get(k).setPTS (PlayerList.get(k).getPTS() + Integer.valueOf(temp[17]));
						PlayerList.get(k).addPTS(Integer.valueOf(temp[17]));
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

			for(int i = 1;i<tempmatch.secondTeamInfo.size();i++){				
				
				String[] temp = tempmatch.secondTeamInfo.get(i).split(";");
				
				for(int k = 0;k<PlayerList.size();k++){
				if(temp[0].equals(PlayerList.get(k).getName())){
					PlayerList.get(k).setTeamName(tempmatch.secondTeamInfo.get(0));
					
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
					PlayerList.get(k).setMinutesOnField(PlayerList.get(k).getMinutesOnField()+minute+seconds/(double)60);
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
					
					PlayerList.get(k).addBackboard(Integer.valueOf(temp[11]));
					
					PlayerList.get(k).addAssist(Integer.valueOf(temp[12]));
					
					PlayerList.get(k).setAssist(PlayerList.get(k).getAssist() + Integer.valueOf(temp[12]));
					
					PlayerList.get(k).setRejection (PlayerList.get(k).getRejection() + Integer.valueOf(temp[14]));
					
					PlayerList.get(k).setSteal(PlayerList.get(k).getSteal() + Integer.valueOf(temp[13]));
					
					PlayerList.get(k).setTo (PlayerList.get(k).getTo() + Integer.valueOf(temp[15]));
					
					PlayerList.get(k).setFoul(PlayerList.get(k).getFoul() + Integer.valueOf(temp[16]));
					try{
						PlayerList.get(k).setPTS (PlayerList.get(k).getPTS() + Integer.valueOf(temp[17]));
						PlayerList.get(k).addPTS(Integer.valueOf(temp[17]));
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
				e.printStackTrace();
			}
		//计算高阶数据
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
		if(PlayerList.get(i).getGP()>5){
			ArrayList<Integer> tempb = PlayerList.get(i).getRecentBackboard();
			ArrayList<Integer> tempa = PlayerList.get(i).getRecentAssist();
			ArrayList<Integer> tempp = PlayerList.get(i).getRecentPTS();
			int recentb = 0;
			int recenta = 0;
			int recentp = 0;
			for(int j = tempb.size()-5;j<tempb.size();j++){
				recentb = recentb + tempb.get(j);
			}
			for(int j = tempa.size()-5;j<tempa.size();j++){
				recenta = recenta + tempa.get(j);
			}
			for(int j = tempp.size()-5;j<tempp.size();j++){
				recentp = recentp + tempp.get(j);
			}
			double avgb = recentb/(double)5;
			double avga = recenta/(double)5;
			double avgp = recentp/(double)5;
			double avgB = (backboard-recentb)/(double)(GP-5);
			double avgA = (assist - recenta)/(double)(GP-5);
			double avgP = (PTS - recentp)/(double)(GP-5);
			PlayerList.get(i).setRecentAvgA(avga);
			PlayerList.get(i).setRecentAvgB(avgb);
			PlayerList.get(i).setRecentAvgP(avgp);
			if(avgB!=0){
			PlayerList.get(i).setBProgressPecnetage((avgb-avgB)/avgB);
			}
			if(avgA!=0){
			PlayerList.get(i).setAProgressPecentage((avga-avgA)/avgA);
			}
			if(avgP!=0){
			PlayerList.get(i).setPProgressPecentage((avgp-avgP)/avgP);
			}
			}
		}
		for(int i = 0;i<PlayerList.size();i++){	
			//System.out.println(PlayerList.get(i).getName());
		pio.WriteIn(PlayerList.get(i),season);
		}
	}
	public PlayerDataPO[] getPlayerByFirstName(PlayerDataPO[] orgin,String firstCapital){
		ArrayList<PlayerDataPO> temp = new ArrayList<PlayerDataPO>();
		for(int j = 0;j<orgin.length;j++){
			if(orgin[j].getName().startsWith(firstCapital)){
				temp.add(orgin[j]);
			}
		}
		PlayerDataPO[] res = new PlayerDataPO[temp.size()];
		for(int i = 0;i<temp.size();i++){
			res[i] = temp.get(i);
		}
		return res;
	}
	public PlayerDataPO[] getPlayerByTeam(String teamName,String nameKeys,String position,String season){
		PlayerDataPO[] orgin = getAllSearch(nameKeys,position,"null",season);
			ArrayList<PlayerDataPO> temp = new ArrayList<PlayerDataPO>();
			if(!teamName.equals("null")){
			for(int i = 0;i<orgin.length;i++){
				if(orgin[i].getTeamName().equals(teamName)){
					temp.add(orgin[i]);
				}
			}
			PlayerDataPO[] res = new PlayerDataPO[temp.size()];
			for(int i = 0;i<temp.size();i++){
				res[i] = temp.get(i);
			}
			return res;
			}
			else{
				return orgin;
			}
		
	}
	
	public Double[] getSeasonAverage(String season){
		PlayerDataPO[] orgin = getAllInfo(season);
		double ppg = 0;
		double apg = 0;
		double bpg = 0;
		double threeper = 0;
		double ftper = 0;
		for(int i = 0;i<orgin.length;i++){
			ppg = ppg + orgin[i].getPPG();
			apg = apg + orgin[i].getAPG();
			bpg = bpg + orgin[i].getBPG();
			threeper = threeper + orgin[i].getThreePGPercentage();
			ftper = ftper + orgin[i].getFTPercentage();
		}
		Double[] res = new Double[5];
		BigDecimal bg = new BigDecimal(ppg/orgin.length);
		res[0] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//res[0] = ppg/orgin.length;
		BigDecimal bg1 = new BigDecimal(apg/orgin.length);
		res[1] = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//res[1] = apg/orgin.length;
		BigDecimal bg2 = new BigDecimal(bpg/orgin.length);
		res[2] = bg2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//res[2] = bpg/orgin.length;
		BigDecimal bg3 = new BigDecimal(threeper/orgin.length);
		res[3] = bg3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//res[3] = threeper/orgin.length;
		BigDecimal bg4 = new BigDecimal(ftper/orgin.length);
		res[4] = bg4.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//res[4] = ftper/orgin.length;
		return res;
	}
	
	//自动测试需要的方法
	public PlayerDataPO[] sort(final String orderName,boolean isASC,PlayerDataPO[] orgin,final boolean isAVG) {
		// TODO Auto-generated method stub
		//p.setOrder(orderName, isASC);
		PlayerList.clear();
		
		for(int i = 0;i<orgin.length;i++){
			PlayerList.add(orgin[i]);
			//System.out.println(orgin[i].getName());
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
				else if(orderName.equals("两双")){
					return p1.getDouble()-p2.getDouble();
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
			//System.out.println(res[i].getPPG()+";"+res[i].getName());
		}
		}
		else{
			for(int i = 0;i<res.length;i++){
				res[i] = PlayerList.get(res.length-1-i);
				//System.out.println(res[i].getPPG()+";"+res[i].getName());
			}
		}
		return res;
	}

	public PlayerDataPO[] subsort(final String suborder,final String order,PlayerDataPO[] orgin,final boolean isAvg,final boolean isAsc){
		ArrayList<PlayerDataPO> temparr = new ArrayList<PlayerDataPO>();
		for(int i = 0;i<orgin.length;i++){
			temparr.add(orgin[i]);
		}
		temparr.sort(new Comparator<PlayerDataPO>(){
			
			@Override
			public int compare(PlayerDataPO p1, PlayerDataPO p2) {
				// TODO Auto-generated method stub
				double x = 0;
				double y = 0;
				switch(suborder){
				case "point":	
					if(isAvg==true){
					x = p1.getPPG()-p2.getPPG();	
					}
					else{
						x = p1.getPTS() - p2.getPTS();
					}
					break;
				case "rebound":	    
					if(isAvg==true){
						x = p1.getBPG()-p2.getBPG();	
						}
						else{
							x = p1.getBackboard() - p2.getBackboard();
						}
					break;
				case "assist": 	
					if(isAvg==true){
						x = p1.getAPG()-p2.getAPG();	
						}
						else{
							x = p1.getAssist() - p2.getAssist();
						}
					break;
				case "blockShot":   
					if(isAvg==true){
						x = p1.getRPG()-p2.getRPG();	
						}
						else{
							x = p1.getRejection() - p2.getRejection();
						}
					break;
				case "steal": 	
					if(isAvg==true){
						x = p1.getStealPG()-p2.getStealPG();	
						}
						else{
							x = p1.getSteal() - p2.getSteal();
						}
					break;
				case "foul":	
					if(isAvg==true){
						x = p1.getFoulPG()-p2.getFoulPG();	
						}
						else{
							x = p1.getFoul() - p2.getFoul();
						}
					break;
				case "fault":	
					if(isAvg==true){
						x = p1.getToPG()-p2.getToPG();	
						}
						else{
							x = p1.getTo() - p2.getTo();
						}
					break;
				case "minute":	
					if(isAvg==true){
						x = p1.getMPG()-p2.getMPG();	
						}
						else{
							x = p1.getMinutesOnField() - p2.getMinutesOnField();
						}
					break;
				case "efficient":	
					x = p1.getEff()-p2.getEff();break;
				case "shot":
					x = p1.getFieldGoalPercentage()-p2.getFieldGoalPercentage();break;
				case "three":
					x = p1.getThreePGPercentage()  - p2.getThreePGPercentage();break;
				case "penalty":
					x = p1.getFTPercentage() - p2.getFTPercentage();break;
				case "doubleTwo":
					x = p1.getDouble() - p2.getDouble();break;
				case "realShot":
					x = p1.getTruePercentage()   - p2.getTruePercentage();break;
				case "GmSc":
					x = p1.getGmsc() - p2.getGmsc();break;
				case "shotEfficient":
					x  = p1.getShootEff() - p2.getShootEff();break;
				case "reboundEfficient":
					x = p1.getBackboardEff() - p2.getBackboardEff();break;
				case "offendReboundEfficient":
					x = p1.getOffBEff() - p2.getOffBEff();break;
				case "defendReboundEfficient":
					x = p1.getDefBEff() - p2.getDefBEff();break;
				case "assistEfficient":
					x = p1.getAssitEff() - p2.getAssitEff();break;
				case "stealEfficient":
					x = p1.getStealEff() - p2.getStealEff();break;
				case "blockShotEfficient":
					x = p1.getRejectionEff() - p2.getRejectionEff();break;
				case "faultEfficient":
					x = p1.getToEff() - p2.getToEff();break;
				case "frequency":
					x = p1.getUseEff() - p2.getUseEff();break;
				
			}
				switch(order){
				case "point":	
					if(isAvg==true){
					y = p1.getPPG()-p2.getPPG();	
					}
					else{
						y = p1.getPTS() - p2.getPTS();
					}
					break;
				case "rebound":	    
					if(isAvg==true){
						y = p1.getBPG()-p2.getBPG();	
						}
						else{
							y = p1.getBackboard() - p2.getBackboard();
						}
					break;
				case "assist": 	
					if(isAvg==true){
						y = p1.getAPG()-p2.getAPG();	
						}
						else{
							y = p1.getAssist() - p2.getAssist();
						}
					break;
				case "blockShot":   
					if(isAvg==true){
						y = p1.getRPG()-p2.getRPG();	
						}
						else{
							y = p1.getRejection() - p2.getRejection();
						}
					break;
				case "steal": 	
					if(isAvg==true){
						y = p1.getStealPG()-p2.getStealPG();	
						}
						else{
							y = p1.getSteal() - p2.getSteal();
						}
					break;
				case "foul":	
					if(isAvg==true){
						y = p1.getFoulPG()-p2.getFoulPG();	
						}
						else{
							y = p1.getFoul() - p2.getFoul();
						}
					break;
				case "fault":	
					if(isAvg==true){
						y = p1.getToPG()-p2.getToPG();	
						}
						else{
							y = p1.getTo() - p2.getTo();
						}
					break;
				case "minute":	
					if(isAvg==true){
						y = p1.getMPG()-p2.getMPG();	
						}
						else{
							y = p1.getMinutesOnField() - p2.getMinutesOnField();
						}
					break;
				case "efficient":	
					y = p1.getEff()-p2.getEff();break;
				case "shot":
					y = p1.getFieldGoalPercentage()-p2.getFieldGoalPercentage();break;
				case "three":
					y = p1.getThreePGPercentage()  - p2.getThreePGPercentage();break;
				case "penalty":
					y = p1.getFTPercentage() - p2.getFTPercentage();break;
				case "doubleTwo":
					y = p1.getDouble() - p2.getDouble();break;
				case "realShot":
					y = p1.getTruePercentage()   - p2.getTruePercentage();break;
				case "GmSc":
					y = p1.getGmsc() - p2.getGmsc();break;
				case "shotEfficient":
					y  = p1.getShootEff() - p2.getShootEff();break;
				case "reboundEfficient":
					y = p1.getBackboardEff() - p2.getBackboardEff();break;
				case "offendReboundEfficient":
					y = p1.getOffBEff() - p2.getOffBEff();break;
				case "defendReboundEfficient":
					y = p1.getDefBEff() - p2.getDefBEff();break;
				case "assistEfficient":
					y = p1.getAssitEff() - p2.getAssitEff();break;
				case "stealEfficient":
					y = p1.getStealEff() - p2.getStealEff();break;
				case "blockShotEfficient":
					y = p1.getRejectionEff() - p2.getRejectionEff();break;
				case "faultEfficient":
					y = p1.getToEff() - p2.getToEff();break;
				case "frequency":
					y = p1.getUseEff() - p2.getUseEff();break;
				
			}
				if(x==0){
					if(y==0){
						return 0;
					}
					else if(y>0){
						if(isAsc==true){
							return 1;
						}
						else {
							return -1;
						}
					}
					else if(y<0){
						if(isAsc==true){
							return -1;
						}
						else {
							return 1;
						}
					}
				}
				return 0;
			}
			
		});
			for(int i = 0;i<temparr.size();i++){
				orgin[i] = temparr.get(i);
			}
		
		return orgin;
	}
	
	public PlayerDataPO[] filter(String position,String Union,String season,PlayerDataPO[] orgin){//都是英文，如Union可以是“E”或“W”,默认的话不要改就行
		ArrayList<PlayerDataPO> res = new ArrayList<PlayerDataPO>();
		PlayerDataPO[] temp = orgin;
		if(position.equals("null")){
			if(Union.equals("null")){
				return temp;
			}
			else if(Union.equals("East")){
				for(int i = 0;i<temp.length;i++){
					if((temp[i].getTeamName().equals("ATL"))
							||(temp[i].getTeamName().equals("CHA"))
							||(temp[i].getTeamName().equals("MIA"))
							||(temp[i].getTeamName().equals("ORL"))
							||(temp[i].getTeamName().equals("WAS"))
							||(temp[i].getTeamName().equals("BKN"))
							||(temp[i].getTeamName().equals("CHI"))
							||(temp[i].getTeamName().equals("DET"))
							||(temp[i].getTeamName().equals("IND"))
							||(temp[i].getTeamName().equals("MIL"))
							||(temp[i].getTeamName().equals("NYK"))
							||(temp[i].getTeamName().equals("PHI"))
							||(temp[i].getTeamName().equals("TOR"))
							||(temp[i].getTeamName().equals("CLE"))){
						res.add(temp[i]);
					}
				}
			}
			else if(Union.equals("West")){
				for(int i = 0;i<temp.length;i++){
					if((temp[i].getTeamName().equals("DAL"))
							||(temp[i].getTeamName().equals("DEN"))
							||(temp[i].getTeamName().equals("GSW"))
							||(temp[i].getTeamName().equals("HOU"))
							||(temp[i].getTeamName().equals("LAC"))
							||(temp[i].getTeamName().equals("LAL"))
							||(temp[i].getTeamName().equals("MEM"))
							||(temp[i].getTeamName().equals("MIN"))
							||(temp[i].getTeamName().equals("NOP"))
							||(temp[i].getTeamName().equals("OKC"))
							||(temp[i].getTeamName().equals("PHX"))
							||(temp[i].getTeamName().equals("POR"))
							||(temp[i].getTeamName().equals("SAC"))
							||(temp[i].getTeamName().equals("SAS"))
							||(temp[i].getTeamName().equals("UTA"))
							||(temp[i].getTeamName().equals("BOS"))){
						res.add(temp[i]);
					}
				}
			}

		}
		else{
			if(Union.equals("null")){
				for(int i = 0;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
						res.add(temp[i]);
					}
				}
				
			}
			else if(Union.equals("East")){
				for(int i = 0;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
					if((temp[i].getTeamName().equals("ATL"))
							||(temp[i].getTeamName().equals("CHA"))
							||(temp[i].getTeamName().equals("MIA"))
							||(temp[i].getTeamName().equals("ORL"))
							||(temp[i].getTeamName().equals("WAS"))
							||(temp[i].getTeamName().equals("BKN"))
							||(temp[i].getTeamName().equals("CHI"))
							||(temp[i].getTeamName().equals("DET"))
							||(temp[i].getTeamName().equals("IND"))
							||(temp[i].getTeamName().equals("MIL"))
							||(temp[i].getTeamName().equals("NYK"))
							||(temp[i].getTeamName().equals("PHI"))
							||(temp[i].getTeamName().equals("TOR"))
							||(temp[i].getTeamName().equals("CLE"))){
						res.add(temp[i]);
					}
					}
				}
			}
			else if(Union.equals("West")){
				for(int i = 0;i<temp.length;i++){
					if(temp[i].getPosition().contains(position)){
					if((temp[i].getTeamName().equals("DAL"))
							||(temp[i].getTeamName().equals("DEN"))
							||(temp[i].getTeamName().equals("GSW"))
							||(temp[i].getTeamName().equals("HOU"))
							||(temp[i].getTeamName().equals("LAC"))
							||(temp[i].getTeamName().equals("LAL"))
							||(temp[i].getTeamName().equals("MEM"))
							||(temp[i].getTeamName().equals("MIN"))
							||(temp[i].getTeamName().equals("NOP"))
							||(temp[i].getTeamName().equals("OKC"))
							||(temp[i].getTeamName().equals("PHX"))
							||(temp[i].getTeamName().equals("POR"))
							||(temp[i].getTeamName().equals("SAC"))
							||(temp[i].getTeamName().equals("SAS"))
							||(temp[i].getTeamName().equals("UTA"))
							||(temp[i].getTeamName().equals("BOS"))){
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

	public void aotoTest(PrintStream out,String season,String date,boolean isAvg,boolean isHigh,String AllOrHotOrKing,int number,
			String filterCondition,String sortCondition){
		int size = number;
		PlayerDataPO[] res = null;
		//all、hot、king的判断
		if(AllOrHotOrKing.startsWith("hot")){
			if(AllOrHotOrKing.contains("score")){
				res = progressPlayer(season,"场均得分");
			}
			else if(AllOrHotOrKing.contains("rebound")){
				res = progressPlayer(season,"场均篮板");
			}
			else {
				res = progressPlayer(season,"场均助攻");
			}
			//System.out.println("write hot player");
			//out输出流写入进步球员数据
			for(int i = 0;i<res.length;i++){
				PlayerHotInfo reshot = new PlayerHotInfo();
				reshot.setName(res[i].getName());
				reshot.setPosition(res[i].getPosition());
				reshot.setTeamName(res[i].getTeamName());
				
				if(AllOrHotOrKing.contains("score")){
				reshot.setField("score");
				reshot.setUpgradeRate(res[i].getPProgressPecentage());
				reshot.setValue(res[i].getRecentAvgP());
				}
				else if(AllOrHotOrKing.contains("rebound")){
					reshot.setField("rebound");
					reshot.setUpgradeRate(res[i].getBProgressPecentage());
					reshot.setValue(res[i].getRecentAvgB());
				}
				else {
					reshot.setField("assist");
					reshot.setUpgradeRate(res[i].getAProgressPecentage());
					reshot.setValue(res[i].getRecentAvgA());
				}
				out.print(i+1);
				out.print("\n");
				out.print(reshot);
				out.print("\n");
			}
		}
		else if(AllOrHotOrKing.startsWith("king")){
			if(AllOrHotOrKing.contains("season")){
				if(AllOrHotOrKing.contains("score")){
					res = hotPlayerSeason(season,"场均得分");
				}
				else if(AllOrHotOrKing.contains("rebound")){
					res = hotPlayerSeason(season,"场均篮板");
				}
				else {
					res = hotPlayerSeason(season,"场均助攻");
				}
				//System.out.println("write king season");
				//向输出流写入热点球员数据
				for(int i = 0;i<res.length;i++){
					PlayerKingInfo resking = new PlayerKingInfo();
					resking.setName(res[i].getName());
					resking.setPosition(res[i].getPosition());
					resking.setTeamName(res[i].getTeamName());
					if(AllOrHotOrKing.contains("score")){
					resking.setField("score");
					resking.setValue(res[i].getPPG());
					}
					else if(AllOrHotOrKing.contains("rebound")){
						resking.setField("rebound");
						resking.setValue(res[i].getBPG());
					}
					else {
						resking.setField("assist");
						resking.setValue(res[i].getAPG());
					}
					out.print(i+1);
					out.print("\n");
					out.print(resking);
					out.print("\n");
				}
			}
			else if(AllOrHotOrKing.contains("daily")){//需要找一次球员信息
				if(AllOrHotOrKing.contains("score")){
					res = hotPlayerToday(season,date,"得分");
				}
				else if(AllOrHotOrKing.contains("rebound")){
					res = hotPlayerToday(season,date,"篮板");
				}
				else {
					res = hotPlayerToday(season,date,"助攻");
				}
				//向输出流写入热点球员数据
				//System.out.println("write king daily");
				for(int i = 0;i<res.length;i++){
					PlayerKingInfo resking = new PlayerKingInfo();
					resking.setName(res[i].getName());
					resking.setPosition(res[i].getPosition());
					resking.setTeamName(res[i].getTeamName());
					if(AllOrHotOrKing.contains("score")){
					resking.setField("score");
					resking.setValue(res[i].getPTS());
					}
					else if(AllOrHotOrKing.contains("rebound")){
						resking.setField("rebound");
						resking.setValue(res[i].getBackboard());
					}
					else {
						resking.setField("assist");
						resking.setValue(res[i].getAssist());
					}
					out.print(i+1);
					out.print("\n");
					out.print(resking);
					out.print("\n");
				}
			}
			
		}
		else if(AllOrHotOrKing.startsWith("all")){
			res = getAllInfo(season);
			//过滤条件的判断
			String[] temp = filterCondition.split(",");
			String[] sorttemp = sortCondition.split(",");
			for(int i = 0;i<temp.length;i++){
				if(temp[i].startsWith("position")){
					res = filter(temp[i].split("\\.")[1],"null",season,res);
				}
				else if(temp[i].startsWith("league")){
					res = filter("null",temp[i].split("\\.")[1],season,res);
				}
				else if(temp[i].startsWith("age")){
					String[] temp2 = temp[i].split("\\.");
					ArrayList<PlayerDataPO> templist = new ArrayList<PlayerDataPO>();
					
					if(temp2[1].equals("<=22")){
						for(int j = 0;j<res.length;j++){
							if(res[i].getAge()<=22){
								templist.add(res[i]);
							}
						}
						PlayerDataPO[] tempres = new PlayerDataPO[templist.size()];
						for(int j = 0;j<templist.size();j++){
							tempres[i] = templist.get(i);
						}
						res = tempres;
					}
					else if(temp2[1].equals("22< X <=25")){
						for(int j = 0;j<res.length;j++){
							if((res[i].getAge()>22)&&(res[i].getAge()<=25)){
								templist.add(res[i]);
							}
						}
						PlayerDataPO[] tempres = new PlayerDataPO[templist.size()];
						for(int j = 0;j<templist.size();j++){
							tempres[i] = templist.get(i);
						}
						res = tempres;
					}
					else if(temp2[1].equals("25< X <=30")){
						for(int j = 0;j<res.length;j++){
							if((res[i].getAge()>25)&&(res[i].getAge()<=30)){
								templist.add(res[i]);
							}
						}
						PlayerDataPO[] tempres = new PlayerDataPO[templist.size()];
						for(int j = 0;j<templist.size();j++){
							tempres[i] = templist.get(i);
						}
						res = tempres;
					}
					else if(temp2[1].equals(">30")){
						for(int j = 0;j<res.length;j++){
							if(res[i].getAge()>30){
								templist.add(res[i]);
							}
						}
						PlayerDataPO[] tempres = new PlayerDataPO[templist.size()];
						for(int j = 0;j<templist.size();j++){
							tempres[i] = templist.get(i);
						}
						res = tempres;
					}
					else {
						
					}
				}
			}
			
			String[] st1 = sorttemp[0].split("\\.");
			String orderwords = "";
			boolean isAsc = true;
			if(st1[1].equals("desc")){
				isAsc = false;
				
			}
			else {
				isAsc = true;
				
			}
			
			switch(st1[0]){
			case "point":	
				orderwords = "得分";break;
			case "rebound":	    
				orderwords = "篮板数";break;
			case "assist": 	
				orderwords = "助攻数";break;
			case "blockShot":   
				orderwords = "盖帽数";break;
			case "steal": 	
				orderwords = "抢断数";break;
			case "foul":	
				orderwords = "犯规数";break;
			case "fault":	
				orderwords = "失误数";break;
			case "minute":	
				orderwords = "在场时间";break;
			case "efficient":	
				orderwords = "效率";break;
			case "shot":
				orderwords = "投篮命中率";break;
			case "three":
				orderwords = "三分命中率";break;
			case "penalty":
				orderwords = "罚球命中率";break;
			case "doubleTwo":
				orderwords = "两双";break;
			case "realShot":
				orderwords = "真实命中率";break;
			case "GmSc":
				orderwords = "Gmsc";break;
			case "shotEfficient":
				orderwords = "投篮效率";break;
			case "reboundEfficient":
				orderwords = "篮板率";break;
			case "offendReboundEfficient":
				orderwords = "进攻篮板率";break;
			case "defendReboundEfficient":
				orderwords = "防守篮板率";break;
			case "assistEfficient":
				orderwords = "助攻率";break;
			case "stealEfficient":
				orderwords = "抢断率";break;
			case "blockShotEfficient":
				orderwords = "盖帽率";break;
			case "faultEfficient":
				orderwords = "失误率";break;
			case "frequency":
				orderwords = "使用率";break;
			
			}
			
			res = sort(orderwords,isAsc,res,isAvg);
			
			String suborder = st1[0];
			for(int i = 1;i<sorttemp.length;i++){
				
				
				String[] st = sorttemp[i].split("\\.");
				if(st[1].equals("desc")){
					isAsc = false;
				}
				else {
					isAsc = true;
				}
				res = subsort(suborder,st[0],res,isAvg,isAsc);
				suborder = st[0];
			}
			
			PlayerDataPO[] trueres = new PlayerDataPO[size];
			//System.out.println(res.length+";res length");
			if(size>res.length){
				//return res;
				trueres = res;
			}
			else{
				for(int i = 0;i<size;i++){
					trueres[i] = res[i];
				}
				//return trueres;
			}
			
			//System.out.println(trueres.length+";true length");
			//输出对象到输出流
			if(isHigh){
				for(int i = 0;i<trueres.length;i++){
					PlayerHighInfo reshigh = new PlayerHighInfo();
					reshigh.setName(trueres[i].getName());
					reshigh.setPosition(trueres[i].getPosition());
					reshigh.setAssistEfficient(trueres[i].getAssitEff());
					reshigh.setBlockShotEfficient(trueres[i].getRejectionEff());
					reshigh.setDefendReboundEfficient(trueres[i].getDefBEff());
					reshigh.setFaultEfficient(trueres[i].getToEff());
					reshigh.setFrequency(trueres[i].getUseEff());
					reshigh.setGmSc(trueres[i].getGmsc());
					reshigh.setOffendReboundEfficient(trueres[i].getOffBEff());
					reshigh.setRealShot(trueres[i].getTruePercentage());
					reshigh.setReboundEfficient(trueres[i].getBackboardEff());
					reshigh.setShotEfficient(trueres[i].getShootEff());
					reshigh.setStealEfficient(trueres[i].getStealEff());
					reshigh.setTeamName(trueres[i].getTeamName());
					reshigh.setLeague("West");
					if((reshigh.getTeamName().equals("ATL"))
							||(reshigh.getTeamName().equals("CHA"))
							||(reshigh.getTeamName().equals("MIA"))
							||(reshigh.getTeamName().equals("ORL"))
							||(reshigh.getTeamName().equals("WAS"))
							||(reshigh.getTeamName().equals("BKN"))
							||(reshigh.getTeamName().equals("CHI"))
							||(reshigh.getTeamName().equals("DET"))
							||(reshigh.getTeamName().equals("IND"))
							||(reshigh.getTeamName().equals("MIL"))
							||(reshigh.getTeamName().equals("NYK"))
							||(reshigh.getTeamName().equals("PHI"))
							||(reshigh.getTeamName().equals("TOR"))
							||(reshigh.getTeamName().equals("CLE"))){
						reshigh.setLeague("East");
					}
					out.print(i+1);
					out.print("\n");
					out.print(reshigh);
					out.print("\n");
				}
			}
			else {
				
				for(int i = 0;i<trueres.length;i++){
					
					PlayerNormalInfo resnormal = new PlayerNormalInfo();
					if(isAvg){
						
					resnormal.setAssist(trueres[i].getAPG());
					resnormal.setBlockShot(trueres[i].getRPG());
					resnormal.setDefend(trueres[i].getDefPG());					
					resnormal.setFault(trueres[i].getToPG());
					resnormal.setFoul(trueres[i].getFoulPG());
					resnormal.setMinute(trueres[i].getMPG());					
					resnormal.setOffend(trueres[i].getOffPG());					
					resnormal.setPoint(trueres[i].getPPG());
					resnormal.setRebound(trueres[i].getBPG());
					resnormal.setSteal(trueres[i].getStealPG());
					}
					else {
						resnormal.setAssist(trueres[i].getAssist());
						resnormal.setBlockShot(trueres[i].getRejection());
						resnormal.setDefend(trueres[i].getDef());					
						resnormal.setFault(trueres[i].getTo());
						resnormal.setFoul(trueres[i].getFoul());
						resnormal.setMinute(trueres[i].getMinutesOnField());					
						resnormal.setOffend(trueres[i].getOff());					
						resnormal.setPoint(trueres[i].getPTS());
						resnormal.setRebound(trueres[i].getBackboard());
						resnormal.setSteal(trueres[i].getSteal());
					}
					resnormal.setTeamName(trueres[i].getTeamName());
					resnormal.setThree(trueres[i].getThreePGPercentage());
					resnormal.setEfficiency(trueres[i].getUseEff());
					resnormal.setName(trueres[i].getName());
					resnormal.setNumOfGame(trueres[i].getGP());
					resnormal.setPenalty(trueres[i].getFTPercentage());
					resnormal.setShot(trueres[i].getFieldGoalPercentage());
					resnormal.setStart(trueres[i].getGS());
					resnormal.setAge(trueres[i].getAge());
					out.print(i+1);
					out.print("\n");
					out.print(resnormal);
					out.print("\n");
				}
				
			}
			
		}
		else{
			
		}
		
		
		
	}
}