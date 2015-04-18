package data.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class TeamDataPO implements Serializable {
	/**
			 * 
			 */
	private static final long serialVersionUID = 1L;
	String Season;//赛季   形式如13-14
	String Name;// 队名
	String ShortName; // 缩写
	String City;// 城市
	String EorW;// 东西部
	String Area;// 分区
	String Mainposition;// 场馆
	String Players; // 球员，以英文分号隔开 形如 xxx;xxx;xxx;。。。;

	int Buildyear;// 年份
	double MatchNumber;// 比赛场数
	double WinMatch; // 胜场数
	int ShootNumber;// 投篮数
	double ShootNumberPG;// 场均投篮
	double ShootEffNumber;// 投篮命中数
	double ShootEffNumberPG;// 场均
	int TPNumber;// 三分出手数
	double TPNumberPG;// 场均
	double TPEffNumber;// 三分命中数
	double TPEffNumberPG;// 场均
	double FTEffNumber;// 罚球命中数
	double FTEffNumberPG;// 场均
	int FTNumber;// 罚球出手数
	double FTNumberPG;// 场均
	double OffBackBoard;// 进攻篮板
	double OffBackBoardPG;// 场均
	double DefBackBoard;// 防守篮板
	double DefBackBoradPG;// 场均
	double OtherDefBoard;// 对手后场篮板
	double OtherOffBoard;// 对手前场篮板
	int BackBoard;// 篮板数
	double BackBoardPG;// 场均
	int AssitNumber;// 助攻数
	double AssitNumberPG;
	int StealNumber;// 抢断数
	double StealNumberPG;
	int Rejection;// 盖帽数，
	double RejectionPG;
	int To;// 失误数
	double ToPG;
	int Foul;// 犯规数，
	double FoulPG;
	int PTS;// 比赛得分，
	double PPG;
	double LPS;// 比赛失分
	double LPG;// 场均失分
	double ShootEff;// 投篮命中率，
	double TPEff;// 三分命中率，
	double FTEff;// 罚球命中率，
	double WR;// 胜率，
	double Off;// 进攻回合，
	double OffPG;
	double Def;// 防守回合，
	double DefPG;
	double OffEff;// 进攻效率，
	double DefEff;// 防守效率，
	double OffBackBoardEff;// 进攻篮板效率，
	double BackBoardEff;// 篮板效率，
	double DefBackBoardEff;// 防守篮板效率，
	double StealEff;// 抢断效率，
	double AssistEff;// 助攻率

	public double getDoubleProperty(String property_double) {
		if (property_double.equals("Buildyear"))
			return this.Buildyear;
		else if (property_double.equals("MatchNumber"))
			return this.MatchNumber;
		else if (property_double.equals("WinMatch"))
			return this.WinMatch;
		else if (property_double.equals("ShootNumber"))
			return this.ShootNumber;
		else if (property_double.equals("ShootNumberPG"))
			return this.ShootNumberPG;
		else if (property_double.equals("ShootEffNumber"))
			return this.ShootEffNumber;
		else if (property_double.equals("ShootEffNumberPG"))
			return this.ShootEffNumberPG;
		else if (property_double.equals("TPNumber"))
			return this.TPNumber;
		else if (property_double.equals("TPNumberPG"))
			return this.TPNumberPG;
		else if (property_double.equals("TPEffNumber"))
			return this.TPEffNumber;
		else if (property_double.equals("TPEffNumberPG"))
			return this.TPEffNumberPG;
		else if (property_double.equals("FTEffNumber"))
			return this.FTEffNumber;
		else if (property_double.equals("FTEffNumberPG"))
			return this.FTEffNumberPG;
		else if (property_double.equals("FTNumber"))
			return this.FTNumber;
		else if (property_double.equals("FTNumberPG"))
			return this.FTNumberPG;
		else if (property_double.equals("OffBackBoard"))
			return this.OffBackBoard;
		else if (property_double.equals("OffBackBoardPG"))
			return this.OffBackBoardPG;
		else if (property_double.equals("DefBackBoard"))
			return this.DefBackBoard;
		else if (property_double.equals("DefBackBoradPG"))
			return this.DefBackBoradPG;
		else if (property_double.equals("OtherDefBoard"))
			return this.OtherDefBoard;
		else if (property_double.equals("OtherOffBoard"))
			return this.OtherOffBoard;
		else if (property_double.equals("BackBoard"))
			return this.BackBoard;
		else if (property_double.equals("BackBoardPG"))
			return this.BackBoardPG;
		else if (property_double.equals("AssitNumber"))
			return this.AssitNumber;
		else if (property_double.equals("AssitNumberPG"))
			return this.AssitNumberPG;
		else if (property_double.equals("StealNumber"))
			return this.StealNumber;
		else if (property_double.equals("StealNumberPG"))
			return this.StealNumberPG;
		else if (property_double.equals("AssistEff"))
			return this.AssistEff;
		else if (property_double.equals("StealEff"))
			return this.StealEff;
		else if (property_double.equals("DefBackBoardEff"))
			return this.DefBackBoardEff;
		else if (property_double.equals("OffBackBoardEff"))
			return this.OffBackBoardEff;
		else if (property_double.equals("DefEff"))
			return this.DefEff;
		else if (property_double.equals("OffEff"))
			return this.OffEff;
		else if (property_double.equals("DefPG"))
			return this.DefPG;
		else if (property_double.equals("Def"))
			return this.Def;
		else if (property_double.equals("OffPG"))
			return this.OffPG;
		else if (property_double.equals("Off"))
			return this.OffPG;
		else if (property_double.equals("WR"))
			return this.WR;
		else if (property_double.equals("Rejection"))
			return this.Rejection;
		else if (property_double.equals("RejectionPG"))
			return this.RejectionPG;
		else if (property_double.equals("To"))
			return this.To;
		else if (property_double.equals("ToPG"))
			return this.ToPG;
		else if (property_double.equals("Foul"))
			return this.Foul;
		else if (property_double.equals("FoulPG"))
			return this.FoulPG;
		else if (property_double.equals("PTS"))
			return this.PTS;
		else if (property_double.equals("PPG"))
			return this.PPG;
		else if (property_double.equals("LPS"))
			return this.LPS;
		else if (property_double.equals("LPG"))
			return this.LPG;
		else if (property_double.equals("ShootEff"))
			return this.ShootEff;
		else if (property_double.equals("TPEff"))
			return this.TPEff;
		else if (property_double.equals("FTEff"))
			return this.FTEff;

		else {
			return this.Buildyear;
		}
	}

	public String getStringProperty(String property_stirng) {
		if (property_stirng.equals("Name"))
			return this.Name;
		if (property_stirng.equals("ShortName"))
			return this.ShortName;
		if (property_stirng.equals("City"))
			return this.City;
		if (property_stirng.equals("EorW"))
			return this.EorW;
		if (property_stirng.equals("Area"))
			return this.Area;
		if (property_stirng.equals("Mainposition"))
			return this.Mainposition;
		if (property_stirng.equals("Players"))
			return this.Players;
		else {
			return this.Name;
		}
	}

	public double getLPS() {
		return LPS;
	}

	public void setLPS(double lPS) {
		BigDecimal bg = new BigDecimal(lPS);
		LPS = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getLPG() {
		return LPG;
	}

	public void setLPG(double lPG) {
		BigDecimal bg = new BigDecimal(lPG);
		LPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getWinMatch() {
		return WinMatch;
	}

	public void setWinMatch(double d) {
		WinMatch = d;
	}

	public String getPlayers() {
		return Players;
	}

	public void setPlayers(String players) {
		this.Players = players;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getEorW() {
		return EorW;
	}

	public void setEorW(String eorW) {
		EorW = eorW;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		this.Area = area;
	}

	public String getMainposition() {
		return Mainposition;
	}

	public void setMainposition(String mainposition) {
		this.Mainposition = mainposition;
	}

	public int getBuildyear() {
		return Buildyear;
	}

	public void setBuildyear(int buildyear) {
		this.Buildyear = buildyear;
	}

	public double getMatchNumber() {
		return MatchNumber;
	}

	public void setMatchNumber(double d) {
		MatchNumber = d;
	}

	public int getShootNumber() {
		return ShootNumber;
	}

	public void setShootNumber(int shootNumber) {
		ShootNumber = shootNumber;
	}

	public double getShootNumberPG() {
		return ShootNumberPG;
	}

	public void setShootNumberPG(double shootNumberPG) {
		BigDecimal bg = new BigDecimal(shootNumberPG);
		ShootNumberPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getShootEffNumber() {
		return ShootEffNumber;
	}

	public void setShootEffNumber(double d) {
		BigDecimal bg = new BigDecimal(d);
		ShootEffNumber = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getShootEffNumberPG() {
		return ShootEffNumberPG;
	}

	public void setShootEffNumberPG(double shootEffNumberPG) {
		BigDecimal bg = new BigDecimal(shootEffNumberPG);
		ShootEffNumberPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	public int getTPNumber() {
		return TPNumber;
	}

	public void setTPNumber(int tPNumber) {
		TPNumber = tPNumber;
	}

	public double getTPNumberPG() {
		return TPNumberPG;
	}

	public void setTPNumberPG(double tPNumberPG) {
		BigDecimal bg = new BigDecimal(tPNumberPG);
		TPNumberPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getTPEffNumber() {
		return TPEffNumber;
	}

	public void setTPEffNumber(double d) {
		BigDecimal bg = new BigDecimal(d);
		TPEffNumber = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getTPEffNumberPG() {
		return TPEffNumberPG;
	}

	public void setTPEffNumberPG(double tPEffNumberPG) {
		BigDecimal bg = new BigDecimal(tPEffNumberPG);
		TPEffNumberPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getFTEffNumber() {
		return FTEffNumber;
	}

	public void setFTEffNumber(double d) {
		FTEffNumber = d;
	}

	public double getFTEffNumberPG() {
		return FTEffNumberPG;
	}

	public void setFTEffNumberPG(double fTEffNumberPG) {
		BigDecimal bg = new BigDecimal(fTEffNumberPG);
		FTEffNumberPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getFTNumber() {
		return FTNumber;
	}

	public void setFTNumber(int fTNumber) {
		FTNumber = fTNumber;
	}

	public double getFTNumberPG() {
		return FTNumberPG;
	}

	public void setFTNumberPG(double fTNumberPG) {
		BigDecimal bg = new BigDecimal(fTNumberPG);
		FTNumberPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getOffBackBoard() {
		return OffBackBoard;
	}

	public void setOffBackBoard(double d) {
		OffBackBoard = d;
	}

	public double getOffBackBoardPG() {
		return OffBackBoardPG;
	}

	public void setOffBackBoardPG(double offBackBoardPG) {
		BigDecimal bg = new BigDecimal(offBackBoardPG);
		OffBackBoardPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getDefBackBoard() {
		return DefBackBoard;
	}

	public void setDefBackBoard(double d) {
		DefBackBoard = d;
	}

	public double getDefBackBoardPG() {
		return DefBackBoradPG;
	}

	public void setDefBackBoardPG(double defBackBoradPG) {
		BigDecimal bg = new BigDecimal(defBackBoradPG);
		DefBackBoradPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getBackBoard() {
		return BackBoard;
	}

	public void setBackBoard(int backBoard) {
		BackBoard = backBoard;
	}

	public double getBackBoardPG() {
		return BackBoardPG;
	}

	public void setBackBoardPG(double backBoardPG) {
		BigDecimal bg = new BigDecimal(backBoardPG);
		BackBoardPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getAssitNumber() {
		return AssitNumber;
	}

	public void setAssitNumber(int assitNumber) {
		AssitNumber = assitNumber;
	}

	public double getAssitNumberPG() {
		return AssitNumberPG;
	}

	public void setAssitNumberPG(double assitNumberPG) {
		BigDecimal bg = new BigDecimal(assitNumberPG);
		AssitNumberPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getStealNumber() {
		return StealNumber;
	}

	public void setStealNumber(int stealNumber) {
		StealNumber = stealNumber;
	}

	public double getStealNumberPG() {
		return StealNumberPG;
	}

	public void setStealNumberPG(double stealNumberPG) {
		BigDecimal bg = new BigDecimal(stealNumberPG);
		StealNumberPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getRejection() {
		return Rejection;
	}

	public void setRejection(int rejection) {
		Rejection = rejection;
	}

	public double getRejectionPG() {
		return RejectionPG;
	}

	public void setRejectionPG(double rejectionPG) {
		BigDecimal bg = new BigDecimal(rejectionPG);
		RejectionPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getTo() {
		return To;
	}

	public void setTo(int to) {
		To = to;
	}

	public double getToPG() {
		return ToPG;
	}

	public void setToPG(double toPG) {
		BigDecimal bg = new BigDecimal(toPG);
		ToPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getFoul() {
		return Foul;
	}

	public void setFoul(int foul) {
		Foul = foul;
	}

	public double getFoulPG() {
		return FoulPG;
	}

	public void setFoulPG(double foulPG) {
		BigDecimal bg = new BigDecimal(foulPG);
		FoulPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getPTS() {
		return PTS;
	}

	public void setPTS(int pTS) {
		PTS = pTS;
	}

	public double getPPG() {
		return PPG;
	}

	public void setPPG(double pPG) {
		BigDecimal bg = new BigDecimal(pPG);
		PPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getShootEff() {
		return ShootEff;
	}

	public void setShootEff(double shootEff) {
		BigDecimal bg = new BigDecimal(shootEff);
		ShootEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getTPEff() {
		return TPEff;
	}

	public void setTPEff(double tPEff) {
		BigDecimal bg = new BigDecimal(tPEff);
		TPEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getFTEff() {
		return FTEff;
	}

	public void setFTEff(double fTEff) {
		BigDecimal bg = new BigDecimal(fTEff);
		FTEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getWR() {
		return WR;
	}

	public void setWR(double wR) {
		BigDecimal bg = new BigDecimal(wR);
		WR = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getOff() {
		return Off;
	}

	public void setOff(double d) {
		BigDecimal bg = new BigDecimal(d);
		Off = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getOffPG() {
		return OffPG;
	}

	public void setOffPG(double offPG) {
		BigDecimal bg = new BigDecimal(offPG);
		OffPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getDef() {
		return Def;
	}

	public void setDef(double d) {
		BigDecimal bg = new BigDecimal(d);
		Def = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getDefPG() {
		return DefPG;
	}

	public void setDefPG(double defPG) {
		BigDecimal bg = new BigDecimal(defPG);
		DefPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getOffEff() {
		return OffEff;
	}

	public void setOffEff(double offEff) {
		BigDecimal bg = new BigDecimal(offEff);
		OffEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getDefEff() {
		return DefEff;
	}

	public void setDefEff(double defEff) {
		BigDecimal bg = new BigDecimal(defEff);
		DefEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getBackBoardEff() {
		return BackBoardEff;
	}

	public void setBackBoardEff(double backBoardEff) {
		BigDecimal bg = new BigDecimal(backBoardEff);
		BackBoardEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getOffBackBoardEff() {
		return OffBackBoardEff;
	}

	public void setOffBackBoardEff(double offbackBoardEff) {
		BigDecimal bg = new BigDecimal(offbackBoardEff);
		OffBackBoardEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	public double getDefBackBoardEff() {
		return DefBackBoardEff;
	}

	public void setDefBackBoardEff(double defbackBoardEff) {
		BigDecimal bg = new BigDecimal(defbackBoardEff);
		DefBackBoardEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	public double getStealEff() {
		return StealEff;
	}

	public void setStealEff(double stealEff) {
		BigDecimal bg = new BigDecimal(stealEff);
		StealEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getAssistEff() {
		return AssistEff;
	}

	public void setAssistEff(double assistEff) {
		BigDecimal bg = new BigDecimal(assistEff);
		AssistEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * @return the otherDefBoard
	 */
	public double getOtherDefBoard() {
		return OtherDefBoard;
	}

	/**
	 * @param otherDefBoard
	 *            the otherDefBoard to set
	 */
	public void setOtherDefBoard(double otherDefBoard) {
		BigDecimal bg = new BigDecimal(otherDefBoard);
		OtherDefBoard = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * @return the otherOffBoard
	 */
	public double getOtherOffBoard() {
		return OtherOffBoard;
	}

	/**
	 * @param otherOffBoard
	 *            the otherOffBoard to set
	 */
	public void setOtherOffBoard(double otherOffBoard) {
		BigDecimal bg = new BigDecimal(otherOffBoard);
		OtherOffBoard = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public String getSeason() {
		return Season;
	}

	public void setSeason(String season) {
		Season = season;
	}

	public double getDefBackBoradPG() {
		return DefBackBoradPG;
	}

	public void setDefBackBoradPG(double defBackBoradPG) {
		BigDecimal bg = new BigDecimal(defBackBoradPG);
		DefBackBoradPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
