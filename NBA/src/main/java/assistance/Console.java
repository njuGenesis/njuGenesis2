package assistance;

import java.io.PrintStream;

import test.data.PlayerNormalInfo;
import bussinesslogic.player.PlayerLogic;
import data.po.PlayerDataPO;
import de.tototec.cmdoption.CmdCommand;
import de.tototec.cmdoption.CmdOption;
import de.tototec.cmdoption.CmdlineParser;
import de.tototec.cmdoption.handler.*;
public class Console {
	public class GameCommand{
		
	}
	@CmdCommand(names={"-player", "-p"}, description="Show Player information") 
	public class PlayerCommand extends GameCommand { 
		
		public boolean isAvg = true;
		public String AllOrHotOrKing = "all";
		public int number = 50;
		public boolean isHigh = false;
		public String filterCondition = "position.null,league.null,age.null";
		public String sortCondition = "point.desc";
		
		@CmdOption(names = {"--help", "-h"}, description = "Show this help.", isHelp = true)
	    public boolean help;

		@CmdOption(names={"-total"},description = "total data",maxCount=1,minCount=0,conflictsWith={"-avg"})
		public void getTotalData(){
			isAvg = false;
		}
		
		@CmdOption(names={"-avg"},description ="avg data",maxCount=1,minCount=0,conflictsWith = {"-total"})
		public void getAvgData(){
			isAvg = true;
			//System.out.println("get Avg");
		}
		
		@CmdOption(names = {"-all"},description ="get all player",maxCount=1,minCount=0,conflictsWith = {"-hot","-king"})
		public void getAllplayer(){
			AllOrHotOrKing = "all";
			//System.out.println("get Allplayer");
		}
		
		@CmdOption(names = {"-hot"},description = "get hot player", maxCount=1,minCount=0,args={"field"},handler = StringMethodHandler.class,
			conflictsWith = {"-all","-king","-filter","-sort","-avg","-total"})
		public void getHot(String temp){
			AllOrHotOrKing = "hot"+";"+temp;
			number = 5;
		}
		
		@CmdOption(names={"-king"},description ="king",maxCount = 1,minCount=0,args={"field"},handler=StringMethodHandler.class
				,conflictsWith = {"-hot","-all","-avg","-total","-filter","-sort"})
		public void setking(String temp){
			AllOrHotOrKing = "king"+";"+temp;
			number = 50;
			//System.out.println("-king"+temp);
		}
		
		@CmdOption(names={"-season"}, description="Show Season Player King", maxCount=1, minCount=0 ,requires={"-king"}, 
		conflictsWith={"-daily"})   
		public void setSeasonPlayerKing()   {  
			AllOrHotOrKing = AllOrHotOrKing + ";season";
			//System.out.println("SeasonPlayerKing");
			//return out;
		} 
		@CmdOption(names={"-daily"}, description="Show Season Player King", maxCount=1, minCount=0 ,requires={"-king"}, 
				conflictsWith={"-season"})   
		public void setdailyPlayerKing()   {  
			AllOrHotOrKing = AllOrHotOrKing + ";daily";
					//System.out.println("DailyPlayerKing");
					//return out;
		} 
		 @CmdOption(names = {"-n"},description = "Get number",maxCount = 1,minCount=0,args={"number"},handler = StringMethodHandler.class)
		public void setnumber(String n){
			 number = Integer.valueOf(n);
		 }
		 
		 @CmdOption(names = {"-high"},description = "get high data",maxCount = 1,minCount=0)
		 public void setisHigh(){
			 isHigh = true;
			 sortCondition = "realShot.desc";
		 }
		 @CmdOption(names = {"-filter"},description = "filter data",maxCount =1,minCount = 0,args = {"condition"},handler = StringMethodHandler.class)
		 public void filter(String condition){
			 filterCondition = condition;
			// System.out.println("filter default");
		 }
		 @CmdOption(names = {"-sort"},description = "sort data",maxCount =1,minCount=0,args={"condition"},handler = StringMethodHandler.class)
		 public void sort(String condition){
			 sortCondition = condition;
			// System.out.println("sort default");
		 }
	}
	public void execute(PrintStream out,String[] args){
		PlayerCommand p = new PlayerCommand();
		CmdlineParser  cp = new CmdlineParser(p);
		cp.setProgramName("firstTest");
		cp.parse(args);
		//System.out.println(p.temp);
		if(p.help){
			cp.usage();
			System.exit(0);
		}
		PlayerLogic pl = new PlayerLogic();
		pl.aotoTest(out,"13-14", "4-27", p.isAvg, p.isHigh,p.AllOrHotOrKing, p.number, p.filterCondition, p.sortCondition);
		
	}
	
}