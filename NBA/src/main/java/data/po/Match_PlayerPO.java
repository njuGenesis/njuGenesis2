package data.po;

import java.io.Serializable;

public class Match_PlayerPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Data;
	String OtherTeam;
	String Team;
	String Playername;
	String State;   //位置
	String Time;  //在场时间
	double points;  //得分
	double BankOff;   //进攻篮板
	double BankDef;    //防守篮板
	double LostSH;    //投失球数
	double Shoot;   //投球数
	double FT;   //罚球数
	double To;   //失误
	double Bank;    //篮板
	double ShootEffNumber; //投篮命中数
	double ShootEff;//投篮命中率
	double TPShootEff;//三分命中率
	double FTShootEff;//罚球命中率
	
	
	double TPShoot; //三分投篮数
	double TPShootEffNumber; //三分命数
	double FTShootEffNumber; //罚篮命中数
	double Ass; //助攻数
	double Steal;//抢断数
	double Rejection;//盖帽数
	double Foul;//犯规数

	
	public String getOtherTeam() {
		return OtherTeam;
	}

	public void setOtherTeam(String otherTeam) {
		OtherTeam = otherTeam;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}
	
	public String getTeam() {
		return Team;
	}

	public void setTeam(String team) {
		Team = team;
	}

	public String getPlayername() {
		return Playername;
	}

	public void setPlayername(String playername) {
		Playername = playername;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public double getBankOff() {
		return BankOff;
	}

	public void setBankOff(double bankOff) {
		BankOff = bankOff;
	}

	public double getBankDef() {
		return BankDef;
	}

	public void setBankDef(double bankDef) {
		BankDef = bankDef;
	}

	public double getLostSH() {
		return LostSH;
	}

	public void setLostSH(double lostSH) {
		LostSH = lostSH;
	}

	public double getShoot() {
		return Shoot;
	}

	public void setShoot(double shoot) {
		Shoot = shoot;
	}

	public double getFT() {
		return FT;
	}

	public void setFT(double fT) {
		FT = fT;
	}

	public double getTo() {
		return To;
	}

	public void setTo(double to) {
		To = to;
	}

	public double getBank() {
		return Bank;
	}

	public void setBank(double bank) {
		Bank = bank;
	}

	public double getShootEffNumber() {
		return ShootEffNumber;
	}

	public double getShootEff() {
		return ShootEff;
	}

	public void setShootEff(double shootEff) {
		ShootEff = shootEff;
	}

	public double getTPShootEff() {
		return TPShootEff;
	}

	public void setTPShootEff(double tPShootEff) {
		TPShootEff = tPShootEff;
	}

	public double getFTShootEff() {
		return FTShootEff;
	}

	public void setFTShootEff(double fTShootEff) {
		FTShootEff = fTShootEff;
	}

	public void setShootEffNumber(double shootEffNumber) {
		ShootEffNumber = shootEffNumber;
	}

	public double getTPShoot() {
		return TPShoot;
	}

	public void setTPShoot(double tPShoot) {
		TPShoot = tPShoot;
	}

	public double getTPShootEffNumber() {
		return TPShootEffNumber;
	}

	public void setTPShootEffNumber(double tPShootEffNumber) {
		TPShootEffNumber = tPShootEffNumber;
	}

	public double getFTShootEffNumber() {
		return FTShootEffNumber;
	}

	public void setFTShootEffNumber(double fTShootEffNumber) {
		FTShootEffNumber = fTShootEffNumber;
	}

	public double getAss() {
		return Ass;
	}

	public void setAss(double ass) {
		Ass = ass;
	}

	public double getSteal() {
		return Steal;
	}

	public void setSteal(double steal) {
		Steal = steal;
	}

	public double getRejection() {
		return Rejection;
	}

	public void setRejection(double rejection) {
		Rejection = rejection;
	}

	public double getFoul() {
		return Foul;
	}

	public void setFoul(double foul) {
		Foul = foul;
	}


}
