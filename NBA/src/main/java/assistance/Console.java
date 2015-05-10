package assistance;

import java.io.PrintStream;

import test.data.PlayerNormalInfo;
import bussinesslogic.player.PlayerLogic;
import bussinesslogic.team.TeamLogic;
import data.po.PlayerDataPO;
import de.tototec.cmdoption.CmdCommand;
import de.tototec.cmdoption.CmdOption;
import de.tototec.cmdoption.CmdlineParser;
import de.tototec.cmdoption.handler.*;

public class Console {
	public class GameCommand {

	}
	@CmdCommand(names = { "-player", "-p" }, description = "Show Player information")
	public class PlayerCommand extends GameCommand {

		public boolean isAvg = true;
		public String AllOrHotOrKing = "all";
		public int number = 50;
		public boolean isHigh = false;
		public String filterCondition = "position.null,league.null,age.null";
		public String sortCondition = "point.desc";

		@CmdOption(names = { "--help", "-h" }, description = "Show this help.", isHelp = true)
		public boolean help;

		@CmdOption(names = { "-total" }, description = "total data", maxCount = 1, minCount = 0, conflictsWith = { "-avg" })
		public void getTotalData() {
			isAvg = false;
		}

		@CmdOption(names = { "-avg" }, description = "avg data", maxCount = 1, minCount = 0, conflictsWith = { "-total" })
		public void getAvgData() {
			isAvg = true;
			// System.out.println("get Avg");
		}

		@CmdOption(names = { "-all" }, description = "get all player", maxCount = 1, minCount = 0, conflictsWith = {
				"-hot", "-king" })
		public void getAllplayer() {
			AllOrHotOrKing = "all";
			// System.out.println("get Allplayer");
		}

		@CmdOption(names = { "-hot" }, description = "get hot player", maxCount = 1, minCount = 0, args = { "field" }, handler = StringMethodHandler.class, conflictsWith = {
				"-all", "-king", "-filter", "-sort", "-avg", "-total" })
		public void getHot(String temp) {
			AllOrHotOrKing = "hot" + ";" + temp;
			number = 5;
		}

		@CmdOption(names = { "-king" }, description = "king", maxCount = 1, minCount = 0, args = { "field" }, handler = StringMethodHandler.class, conflictsWith = {
				"-hot", "-all", "-avg", "-total", "-filter", "-sort" })
		public void setking(String temp) {
			AllOrHotOrKing = "king" + ";" + temp;
			number = 50;
			// System.out.println("-king"+temp);
		}

		@CmdOption(names = { "-season" }, description = "Show Season Player King", maxCount = 1, minCount = 0, requires = { "-king" }, conflictsWith = { "-daily" })
		public void setSeasonPlayerKing() {
			AllOrHotOrKing = AllOrHotOrKing + ";season";
			// System.out.println("SeasonPlayerKing");
			// return out;
		}

		@CmdOption(names = { "-daily" }, description = "Show Season Player King", maxCount = 1, minCount = 0, requires = { "-king" }, conflictsWith = { "-season" })
		public void setdailyPlayerKing() {
			AllOrHotOrKing = AllOrHotOrKing + ";daily";
			// System.out.println("DailyPlayerKing");
			// return out;
		}

		@CmdOption(names = { "-n" }, description = "Get number", maxCount = 1, minCount = 0, args = { "number" }, handler = StringMethodHandler.class)
		public void setnumber(String n) {
			number = Integer.valueOf(n);
		}

		@CmdOption(names = { "-high" }, description = "get high data", maxCount = 1, minCount = 0)
		public void setisHigh() {
			isHigh = true;
			sortCondition = "realShot.desc";
		}

		@CmdOption(names = { "-filter" }, description = "filter data", maxCount = 1, minCount = 0, args = { "condition" }, handler = StringMethodHandler.class)
		public void filter(String condition) {
			filterCondition = condition;
			// System.out.println("filter default");
		}

		@CmdOption(names = { "-sort" }, description = "sort data", maxCount = 1, minCount = 0, args = { "condition" }, handler = StringMethodHandler.class)
		public void sort(String condition) {
			sortCondition = condition;
			// System.out.println("sort default");
		}
	}

	@CmdCommand(names={"-team", "-p"}, description="Show Team information") 
	public class TeamCommand extends GameCommand { 
		
		public boolean isAvg = true;
		public String AllOrHot = "all";
		public int number = 30;
		public boolean isHigh = false;
		public String sortCondition = "score.desc";
		
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
		
		@CmdOption(names = {"-all"},description ="get all team",maxCount=1,minCount=0,conflictsWith = {"-hot"})
		public void getAllplayer(){
			AllOrHot = "all";
			//System.out.println("get AllTeam");
		}
		
		@CmdOption(names = {"-hot"},description = "get hot team", maxCount=1,minCount=0,handler = StringMethodHandler.class,
			conflictsWith = {"-all","-sort","-avg","-total"})
		public void getHot(String temp){
			AllOrHot = "hot"+";"+temp;
			number = 5;
		}
		
		 @CmdOption(names = {"-n"},description = "Get number",maxCount = 1,minCount=0,args={"number"},handler = StringMethodHandler.class)
		public void setnumber(String n){
			 number = Integer.valueOf(n);
		 }
		 
		 @CmdOption(names = {"-high"},description = "get high data",maxCount = 1,minCount=0)
		 public void setisHigh(){
			 isHigh = true;
			 sortCondition = "winRate.desc";
		 }
		 
		 @CmdOption(names = {"-sort"},description = "sort data",maxCount =1,minCount=0,args={"condition"},handler = StringMethodHandler.class)
		 public void sort(String condition){
			 sortCondition = condition;
			// System.out.println("sort default");
		 }
	}
	
	
	public void execute(PrintStream out, String[] args) {
		PlayerCommand p = new PlayerCommand();
		TeamCommand t = new TeamCommand();
		
		PlayerLogic pl = new PlayerLogic();
		TeamLogic tl = new TeamLogic();
		
		String season = pl.getLatestSeason();
		String date = pl.getLatestDate();
		if(args[0].equals("-player")){
		CmdlineParser cp = new CmdlineParser(p);
		cp.setProgramName("playerTest");
		cp.parse(args);
		// System.out.println(p.temp);
		if (p.help) {
			cp.usage();
			System.exit(0);
		}
		
		pl.aotoTest(out, season, date, p.isAvg, p.isHigh, p.AllOrHotOrKing,
				p.number, p.filterCondition, p.sortCondition);
		}
		else if(args[0].equals("-team")){
			CmdlineParser cp = new CmdlineParser(t);
			cp.setProgramName("teamTest");
			cp.parse(args);
			// System.out.println(p.temp);
			if (t.help) {
				cp.usage();
				System.exit(0);
			}
			//TeamLogic tl = new TeamLogic();
			tl.aotoTest(out,  false, true, season,"hot score",
					t.number, t.sortCondition);
		}
		else{
			//init
			String datasource = args[1].replaceAll("\\\\", "/");
			String playerPath = datasource+"/players/info";
			String matchPath = datasource +"/matches";
			PlayerLogic temppl = new PlayerLogic(playerPath,matchPath);
			temppl.initialize(datasource, season);
			tl.initTeamData();
		}

	}

}
