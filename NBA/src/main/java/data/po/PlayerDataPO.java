package data.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class PlayerDataPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Name;//名字
	String Number;//号码
	String position;//位置
	String Height;//高度
	double Weight;//体重
	String Birth;//生日
	int Age;//年龄
	int Exp;//球龄
	String school;//学校
	String TeamName;//队伍
	
	
	int GP = 0;//姣旇禌鍦烘暟
	int GS = 0;//鍏堝彂鍦烘暟
	
	int totalb = 0;//鐞冮槦鎬荤鏉�
	int totalbother = 0;//瀵规柟鐞冮槦鎬荤鏉�
	int Offb = 0;//杩涙敾绡澘
	int Defb = 0;
	int backboard = 0;//鎬荤鏉�
	double BPG;//鍦哄潎绡澘
	
	int assist = 0;//鍔╂敾鏁�
	double APG;// 鍦哄潎鍔╂敾
	
	double totalminute = 0;//鐞冮槦鎬讳笂鍦烘椂闂�
	double MinutesOnField = 0;//鍦ㄥ満鏃堕棿
	double MPG = 0;//鍦哄潎鏃堕棿
	
	int OtherTotalFieldGoal = 0;//瀵归潰鐞冮槦鍑烘墜娆℃暟
	int TotalGoal = 0;//鐞冮槦鎬诲嚭鎵�
	int FieldGoal = 0;//鎶曠鍛戒腑
	int TotalFieldGoal = 0;//鎶曠鎬绘暟
	double FieldGoalPercentage ;//鎶曠鍛戒腑鐜�
	
	int ThreeGoal = 0;//涓夊垎鍛戒腑涓暟
	int TotalThreeGoal = 0;//涓夊垎鎬诲嚭鎵�
	double ThreePGPercentage ;//涓夊垎鍛戒腑鐜�
	
	int AllFT = 0;//鐞冮槦缃氱悆娆℃暟
	int FT = 0;//缃氱悆鍛戒腑
	int TotalFT = 0;
	double FTPercentage ;//缃氱悆鍛戒腑鐜囷紝Free Throw
	
	int TotalOffb = 0;//瀵规墜杩涙敾娆℃暟
	int Off = 0;//杩涙敾鏁�
	double OffPG = 0;//鍦哄潎
	
	int Def = 0;//闃插畧鏁�
	double DefPG = 0;//鍦哄潎
	
	int Steal = 0;//鎶㈡柇鏁�
	double StealPG = 0;//鍦哄潎鎶㈡柇
	
	int Rejection = 0;//鐩栧附鏁�
	double RPG = 0;//鍦哄潎鐩栧附
	
	int AllTo = 0;//鐞冮槦鎬诲け璇�
	int To = 0;//澶辫
	double ToPG = 0;//鍦哄潎澶辫
	
	int foul = 0;//鐘
	double foulPG = 0;//鍦哄潎鐘
	
	int PTS = 0;//寰楀垎
	double PPG = 0;//鍦哄潎寰楀垎
	
	double Eff;//鏁堢巼
	
	double Gmsc;//GMSC鏁堢巼
	
	double TruePercentage;//鐪熷疄鍛戒腑鐜�
	
	double ShootEff;//鎶曠鏁堢巼锛�
	
	double BackboardEff;//绡澘鐜囷紝
	
	double OffBEff;//杩涙敾绡澘鐜囷紝
	
	double DefBEff;//闃插畧绡澘鐜囷紝
	
	double AssitEff;//鍔╂敾鐜囷紝
	
	double StealEff;//鎶㈡柇鐜囷紝
	
	double RejectionEff;//鐩栧附鐜囷紝
	
	double ToEff;//澶辫鐜囷紝
	
	double UseEff;//浣跨敤鐜�

	int Double = 0;//涓ゅ弻
	
	ArrayList<Integer> RecentBackboard = new ArrayList<Integer>();

	ArrayList<Integer> RecentAssist = new ArrayList<Integer>();
	 
	ArrayList<Integer> RecentPTS = new ArrayList<Integer>();
	
	double BProgressPecentage = 0;
	
	
	double AProgressPecentage = 0;
	
	double PProgressPecentage = 0;
	
	public double getAProgressPecentage() {
		return AProgressPecentage;
	}
	public void setAProgressPecentage(double aProgressPecentage) {
		BigDecimal bg = new BigDecimal(aProgressPecentage);
		AProgressPecentage = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public double getPProgressPecentage() {
		return PProgressPecentage;
	}
	public void setPProgressPecentage(double pProgressPecentage) {
		BigDecimal bg = new BigDecimal(pProgressPecentage);
		PProgressPecentage =bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public ArrayList<Integer> getRecentBackboard(){
		return RecentBackboard;
	}
	public void addBackboard(int e){
		RecentBackboard.add(e);
	}
	
	public ArrayList<Integer> getRecentAssist(){
		return RecentAssist;
	}
	public void addAssist(int e){
		RecentAssist.add(e);
	}
	
	public ArrayList<Integer> getRecentPTS(){
		return RecentPTS;
	}
	public void addPTS(int e){
		RecentPTS.add(e);
	}
	
	public double getBProgressPecentage(){
		return BProgressPecentage;
	}
	public void setBProgressPecnetage(double p){
		BigDecimal bg = new BigDecimal(p);
		BProgressPecentage = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public int getTotalb() {
		return totalb;
	}

	public void setTotalb(int totalb) {
		this.totalb = totalb;
	}

	public int getTotalbother() {
		return totalbother;
	}

	public void setTotalbother(int totalbother) {
		this.totalbother = totalbother;
	}

	public int getOffb() {
		return Offb;
	}

	public void setOffb(int offb) {
		Offb = offb;
	}

	public int getDefb() {
		return Defb;
	}

	public void setDefb(int defb) {
		Defb = defb;
	}

	public double getTotalminute() {
		return totalminute;
	}

	public void setTotalminute(double totalminute) {
		this.totalminute = totalminute;
	}

	public int getOtherTotalFieldGoal() {
		return OtherTotalFieldGoal;
	}

	public void setOtherTotalFieldGoal(int otherTotalFieldGoal) {
		OtherTotalFieldGoal = otherTotalFieldGoal;
	}

	public int getTotalGoal() {
		return TotalGoal;
	}

	public void setTotalGoal(int totalGoal) {
		TotalGoal = totalGoal;
	}

	public int getAllFT() {
		return AllFT;
	}

	public void setAllFT(int allFT) {
		AllFT = allFT;
	}

	public int getTotalOffb() {
		return TotalOffb;
	}

	public void setTotalOffb(int totalOffb) {
		TotalOffb = totalOffb;
	}

	public int getAllTo() {
		return AllTo;
	}

	public void setAllTo(int allTo) {
		AllTo = allTo;
	}

	public int getFieldGoal() {
		return FieldGoal;
	}

	public void setFieldGoal(int fieldGoal) {
		FieldGoal = fieldGoal;
	}

	public int getTotalFieldGoal() {
		return TotalFieldGoal;
	}

	public void setTotalFieldGoal(int totalFieldGoal) {
		TotalFieldGoal = totalFieldGoal;
	}

	public int getThreeGoal() {
		return ThreeGoal;
	}

	public void setThreeGoal(int threeGoal) {
		ThreeGoal = threeGoal;
	}

	public int getTotalThreeGoal() {
		return TotalThreeGoal;
	}

	public void setTotalThreeGoal(int totalThreeGoal) {
		TotalThreeGoal = totalThreeGoal;
	}

	public int getFT() {
		return FT;
	}

	public void setFT(int fT) {
		FT = fT;
	}

	public int getTotalFT() {
		return TotalFT;
	}

	public void setTotalFT(int totalFT) {
		TotalFT = totalFT;
	}

	public int getDouble(){
		return Double;
	}
	
	public void setDouble(int d){
		Double = d;
	}
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHeight() {
		return Height;
	}

	public void setHeight(String height) {
		Height = height;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		BigDecimal bg = new BigDecimal(weight);
		Weight = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public String getBirth() {
		return Birth;
	}

	public void setBirth(String birth) {
		Birth = birth;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public int getExp() {
		return Exp;
	}

	public void setExp(int exp) {
		Exp = exp;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
	}

	public int getGP() {
		return GP;
	}

	public void setGP(int gP) {
		GP = gP;
	}

	public int getGS() {
		return GS;
	}

	public void setGS(int gS) {
		GS = gS;
	}

	public int getBackboard() {
		return backboard;
	}

	public void setBackboard(int backboard) {
		this.backboard = backboard;
	}

	public double getBPG() {
		return BPG;
	}

	public void setBPG(double bPG) {
		BigDecimal bg = new BigDecimal(bPG);
		BPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public double getAPG() {
		return APG;
	}

	public void setAPG(double aPG) {
		BigDecimal bg = new BigDecimal(aPG);
		APG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getMinutesOnField() {
		return MinutesOnField;
	}

	public void setMinutesOnField(double minutesOnField) {
		BigDecimal bg = new BigDecimal(minutesOnField);
		MinutesOnField = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getMPG() {
		return MPG;
	}

	public void setMPG(double mPG) {
		BigDecimal bg = new BigDecimal(mPG);
		MPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getFieldGoalPercentage() {
		return FieldGoalPercentage;
	}

	public void setFieldGoalPercentage(double fieldGoalPercentage) {
		BigDecimal bg = new BigDecimal(fieldGoalPercentage);
		FieldGoalPercentage = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getThreePGPercentage() {
		return ThreePGPercentage;
	}

	public void setThreePGPercentage(double threePGPercentage) {
		BigDecimal bg = new BigDecimal(threePGPercentage);
		ThreePGPercentage = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	public double getFTPercentage() {
		return FTPercentage;
	}

	public void setFTPercentage(double fTPercentage) {
		BigDecimal bg = new BigDecimal(fTPercentage);
		FTPercentage = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getOff() {
		return Off;
	}

	public void setOff(int off) {
		Off = off;
	}

	public double getOffPG() {
		return OffPG;
	}

	public void setOffPG(double offPG) {
		BigDecimal bg = new BigDecimal(offPG);
		OffPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//OffPG = offPG;
	}

	public int getDef() {
		return Def;
	}

	public void setDef(int def) {
		Def = def;
	}

	public double getDefPG() {
		return DefPG;
	}

	public void setDefPG(double defPG) {
		BigDecimal bg = new BigDecimal(defPG);
		DefPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//DefPG = defPG;
	}

	public int getSteal() {
		return Steal;
	}

	public void setSteal(int steal) {
		Steal = steal;
	}

	public double getStealPG() {
		return StealPG;
	}

	public void setStealPG(double stealPG) {
		BigDecimal bg = new BigDecimal(stealPG);
		StealPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//StealPG = stealPG;
	}

	public int getRejection() {
		return Rejection;
	}

	public void setRejection(int rejection) {
		Rejection = rejection;
	}

	public double getRPG() {
		return RPG;
	}

	public void setRPG(double rPG) {
		BigDecimal bg = new BigDecimal(rPG);
		RPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//RPG = rPG;
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
		//ToPG = toPG;
	}

	public int getFoul() {
		return foul;
	}

	public void setFoul(int foul) {
		this.foul = foul;
	}

	public double getFoulPG() {
		return foulPG;
	}

	public void setFoulPG(double foulPG) {
		BigDecimal bg = new BigDecimal(foulPG);
		this.foulPG = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//this.foulPG = foulPG;
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
		//PPG = pPG;
	}

	public double getEff() {
		return Eff;
	}

	public void setEff(double eff) {
		BigDecimal bg = new BigDecimal(eff);
		Eff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//Eff = eff;
	}

	public double getGmsc() {
		return Gmsc;
	}

	public void setGmsc(double gmsc) {
		BigDecimal bg = new BigDecimal(gmsc);
		Gmsc = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//Gmsc = gmsc;
	}

	public double getTruePercentage() {
		return TruePercentage;
	}

	public void setTruePercentage(double truePercentage) {
		BigDecimal bg = new BigDecimal(truePercentage);
		TruePercentage = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//TruePercentage = truePercentage;
	}

	public double getShootEff() {
		return ShootEff;
	}

	public void setShootEff(double shootEff) {
		BigDecimal bg = new BigDecimal(shootEff);
		ShootEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//ShootEff = shootEff;
	}

	public double getBackboardEff() {
		return BackboardEff;
	}

	public void setBackboardEff(double backboardEff) {
		BigDecimal bg = new BigDecimal(backboardEff);
		BackboardEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//BackboardEff = backboardEff;
	}

	public double getOffBEff() {
		return OffBEff;
	}

	public void setOffBEff(double offBEff) {
		BigDecimal bg = new BigDecimal(offBEff);
		OffBEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//OffBEff = offBEff;
	}

	public double getDefBEff() {
		return DefBEff;
	}

	public void setDefBEff(double defBEff) {
		BigDecimal bg = new BigDecimal(defBEff);
		DefBEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//DefBEff = defBEff;
	}

	public double getAssitEff() {
		return AssitEff;
	}

	public void setAssitEff(double assitEff) {
		BigDecimal bg = new BigDecimal(assitEff);
		AssitEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//AssitEff = assitEff;
	}

	public double getStealEff() {
		return StealEff;
	}

	public void setStealEff(double stealEff) {
		BigDecimal bg = new BigDecimal(stealEff);
		StealEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//StealEff = stealEff;
	}

	public double getRejectionEff() {
		return RejectionEff;
	}

	public void setRejectionEff(double rejectionEff) {
		BigDecimal bg = new BigDecimal(rejectionEff);
		RejectionEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//RejectionEff = rejectionEff;
	}

	public double getToEff() {
		return ToEff;
	}

	public void setToEff(double toEff) {
		BigDecimal bg = new BigDecimal(toEff);
		ToEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//ToEff = toEff;
	}

	public double getUseEff() {
		return UseEff;
	}

	public void setUseEff(double useEff) {
		BigDecimal bg = new BigDecimal(useEff);
		UseEff = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//UseEff = useEff;
	}

	
}
