package presentation.contenui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableUtility {
	
	public static void setTableRowHeight(JTable table,int h){
		table.setRowHeight(h);
	}
	
	public static void setTableHeaderHeight(JTable table,int h){
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getPreferredSize().width,h));
	}
	
	public static void setTabelPanel(JScrollPane tablePanel){
		tablePanel.setOpaque(false);
		tablePanel.getViewport().setOpaque(false);
		tablePanel.setViewportBorder(BorderFactory.createEmptyBorder());
		tablePanel.setBorder(BorderFactory.createEmptyBorder());
	}
	
	public static void setTableColor(JTable table){
		table.setDefaultRenderer(Object.class,new MyCellRenderer());
		setFont(table);
		table.setShowGrid(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	
	public static void setFont(JTable table){
		table.setFont(new Font("微软雅黑",0,12));
		table.getTableHeader().setBackground(new Color(112,112,112));
		table.getTableHeader().setFont(new Font("微软雅黑",0,12));
		table.getTableHeader().setForeground(Color.white);
	}

	public static String getChPosition(String en){
		if(en.contains("-")){
			String[] pos = en.split("-");
			System.out.println(pos[0]+pos[1]);
			return transPosition(pos[0])+"-"+transPosition(pos[1]);
		}else{
			return transPosition(en);
		}
	}
	
	private static String transPosition(String en){
		if(en.equals("G")){
			return "后卫";
		}else if(en.equals("F")){
			return "前锋";
		}else if(en.equals("C")){
			return "中锋";
		}else if(en.equals("后卫")){
			return "G";
		}else if(en.equals("中锋")){
			return "C";
		}else if(en.equals("前锋")){
			return "F";
		}else if(en.equals("全部位置")){
			return "null";
		}else{
			return "无";
		}
		
		
	}
	
	public static String getChTeam(String en){
		String ch;
		if(en.equals("SAS")){
			ch = "圣安东尼奥马刺";
		}else if(en.equals("MEM")){
			ch = "孟菲斯灰熊";
		}else if(en.equals("HOU")){
			ch = "休斯顿火箭";
		}else if(en.equals("DAL")){
			ch = "达拉斯小牛";
		}else if(en.equals("NOP")){
			ch = "新奥尔良鹈鹕";
		}else if(en.equals("SAC")){
			ch = "萨克拉门托国王";
		}else if(en.equals("PHX")){
			ch = "菲尼克斯太阳";
		}else if(en.equals("GSW")){
			ch = "金州勇士";
		}else if(en.equals("LAC")){
			ch = "洛杉矶快船";
		}else if(en.equals("LAL")){
			ch = "洛杉矶湖人";
		}else if(en.equals("MIN")){
			ch = "明尼苏达森林狼";
		}else if(en.equals("UTA")){
			ch = "犹他爵士";
		}else if(en.equals("POR")){
			ch = "波特兰开拓者";
		}else if(en.equals("OKC")){
			ch = "俄克拉荷马城雷霆";
		}else if(en.equals("DEN")){
			ch = "丹佛掘金";
		}else if(en.equals("MIA")){
			ch = "迈阿密热火";
		}else if(en.equals("ORL")){
			ch = "奥兰多魔术";
		}else if(en.equals("ATL")){
			ch = "亚特兰大老鹰";
		}else if(en.equals("WAS")){
			ch = "华盛顿奇才";
		}else if(en.equals("CHA")){
			ch = "夏洛特黄蜂";
		}else if(en.equals("DET")){
			ch = "底特律活塞";
		}else if(en.equals("IND")){
			ch = "印第安纳步行者";
		}else if(en.equals("CLE")){
			ch = "克利夫兰骑士";
		}else if(en.equals("CHI")){
			ch = "芝加哥公牛";
		}else if(en.equals("MIL")){
			ch = "密尔沃基雄鹿";
		}else if(en.equals("NYK")){
			ch = "纽约尼克斯";
		}else if(en.equals("PHI")){
			ch = "费城76人";
		}else if(en.equals("BOS")){
			ch = "波士顿凯尔特人";
		}else if(en.equals("BKN")){
			ch = "布鲁克林篮网";
		}else if(en.equals("TOR")){
			ch = "多伦多猛龙";
		}else if(en.equals("null")){
			ch = "无";
		}else if(en.equals("圣安东尼奥马刺")){
			ch = "SAS";
		}else if(en.equals("孟菲斯灰熊")){
			ch = "MEM";
		}else if(en.equals("休斯顿火箭")){
			ch = "HOU";
		}else if(en.equals("达拉斯小牛")){
			ch = "DAL";
		}else if(en.equals("新奥尔良鹈鹕")){
			ch = "NOP";
		}else if(en.equals("萨克拉门托国王")){
			ch = "SAC";
		}else if(en.equals("菲尼克斯太阳")){
			ch = "PHX";
		}else if(en.equals("金州勇士")){
			ch = "GSW";
		}else if(en.equals("洛杉矶快船")){
			ch = "LAC";
		}else if(en.equals("洛杉矶湖人")){
			ch = "LAL";
		}else if(en.equals("明尼苏达森林狼")){
			ch = "MIN";
		}else if(en.equals("犹他爵士")){
			ch = "UTA";
		}else if(en.equals("波特兰开拓者")){
			ch = "POR";
		}else if(en.equals("俄克拉荷马城雷霆")){
			ch = "OKC";
		}else if(en.equals("丹佛掘金")){
			ch = "DEN";
		}else if(en.equals("迈阿密热火")){
			ch = "MIA";
		}else if(en.equals("奥兰多魔术")){
			ch = "ORL";
		}else if(en.equals("亚特兰大老鹰")){
			ch = "ATL";
		}else if(en.equals("华盛顿奇才")){
			ch = "WAS";
		}else if(en.equals("夏洛特黄蜂")){
			ch = "CHA";
		}else if(en.equals("底特律活塞")){
			ch = "DET";
		}else if(en.equals("印第安纳步行者")){
			ch = "IND";
		}else if(en.equals("克利夫兰骑士")){
			ch = "CLE";
		}else if(en.equals("芝加哥公牛")){
			ch = "CHI";
		}else if(en.equals("密尔沃基雄鹿")){
			ch = "MIL";
		}else if(en.equals("纽约尼克斯")){
			ch = "NYK";
		}else if(en.equals("费城76人")){
			ch = "PHI";
		}else if(en.equals("波士顿凯尔特人")){
			ch = "BOS";
		}else if(en.equals("布鲁克林篮网")){
			ch = "BKN";
		}else if(en.equals("多伦多猛龙")){
			ch = "TOR";
		}else if(en.equals("Spurs")){
			ch = "圣安东尼奥马刺";
		}else if(en.equals("Grizzlies")){
			ch = "孟菲斯灰熊";
		}else if(en.equals("Rockets")){
			ch = "休斯顿火箭";
		}else if(en.equals("Mavericks")){
			ch = "达拉斯小牛";
		}else if(en.equals("Pelicans")){
			ch = "新奥尔良鹈鹕";
		}else if(en.equals("Kings")){
			ch = "萨克拉门托国王";
		}else if(en.equals("Suns")){
			ch = "菲尼克斯太阳";
		}else if(en.equals("Warriors")){
			ch = "金州勇士";
		}else if(en.equals("Clippers")){
			ch = "洛杉矶快船";
		}else if(en.equals("Lakers")){
			ch = "洛杉矶湖人";
		}else if(en.equals("Timberwolves")){
			ch = "明尼苏达森林狼";
		}else if(en.equals("Jazz")){
			ch = "犹他爵士";
		}else if(en.equals("Trail Blazers")){
			ch = "波特兰开拓者";
		}else if(en.equals("Thunder")){
			ch = "俄克拉荷马城雷霆";
		}else if(en.equals("Nuggets")){
			ch = "丹佛掘金";
		}else if(en.equals("Heat")){
			ch = "迈阿密热火";
		}else if(en.equals("Magic")){
			ch = "奥兰多魔术";
		}else if(en.equals("Hawks")){
			ch = "亚特兰大老鹰";
		}else if(en.equals("Wizards")){
			ch = "华盛顿奇才";
		}else if(en.equals("Hornets")){
			ch = "夏洛特黄蜂";
		}else if(en.equals("Pistons")){
			ch = "底特律活塞";
		}else if(en.equals("Pacers")){
			ch = "印第安纳步行者";
		}else if(en.equals("Cavaliers")){
			ch = "克利夫兰骑士";
		}else if(en.equals("Bulls")){
			ch = "芝加哥公牛";
		}else if(en.equals("Bucks")){
			ch = "密尔沃基雄鹿";
		}else if(en.equals("Knicks")){
			ch = "纽约尼克斯";
		}else if(en.equals("76ers")){
			ch = "费城76人";
		}else if(en.equals("Celtics")){
			ch = "波士顿凯尔特人";
		}else if(en.equals("Nets")){
			ch = "布鲁克林篮网";
		}else if(en.equals("Raptors")){
			ch = "多伦多猛龙";
		}else{
			ch = "null";
		}
		return ch;
	}
	
	public static String getChUnion(String en){
		String ch = "";
		if(en.equals("E")){
			ch = "东部联盟";
		}else{
			ch = "西部联盟";
		}
		return ch;
	}
	
	public static String getShortChTeam(String en){
		String ch = "";
		if(en.equals("SAS")){
			ch = "马刺";
		}else if(en.equals("MEM")){
			ch = "灰熊";
		}else if(en.equals("HOU")){
			ch = "火箭";
		}else if(en.equals("DAL")){
			ch = "小牛";
		}else if(en.equals("NOP")){
			ch = "鹈鹕";
		}else if(en.equals("SAC")){
			ch = "国王";
		}else if(en.equals("PHX")){
			ch = "太阳";
		}else if(en.equals("GSW")){
			ch = "勇士";
		}else if(en.equals("LAC")){
			ch = "快船";
		}else if(en.equals("LAL")){
			ch = "湖人";
		}else if(en.equals("MIN")){
			ch = "森林狼";
		}else if(en.equals("UTA")){
			ch = "爵士";
		}else if(en.equals("POR")){
			ch = "开拓者";
		}else if(en.equals("OKC")){
			ch = "雷霆";
		}else if(en.equals("DEN")){
			ch = "掘金";
		}else if(en.equals("MIA")){
			ch = "热火";
		}else if(en.equals("ORL")){
			ch = "魔术";
		}else if(en.equals("ATL")){
			ch = "老鹰";
		}else if(en.equals("WAS")){
			ch = "奇才";
		}else if(en.equals("CHA")){
			ch = "黄蜂";
		}else if(en.equals("DET")){
			ch = "活塞";
		}else if(en.equals("IND")){
			ch = "步行者";
		}else if(en.equals("CLE")){
			ch = "骑士";
		}else if(en.equals("CHI")){
			ch = "公牛";
		}else if(en.equals("MIL")){
			ch = "雄鹿";
		}else if(en.equals("NYK")){
			ch = "尼克斯";
		}else if(en.equals("PHI")){
			ch = "76人";
		}else if(en.equals("BOS")){
			ch = "凯尔特人";
		}else if(en.equals("BKN")){
			ch = "篮网";
		}else if(en.equals("TOR")){
			ch = "猛龙";
		}else if(en.equals("NOH")){
			ch = "黄蜂";
		}else if(en.equals("null")){
			ch = "无";
		}
		
		if(en.equals("马刺")){
			ch = "SAS";
		}else if(en.equals("灰熊")){
			ch = "MEM";
		}else if(en.equals("火箭")){
			ch = "HOU";
		}else if(en.equals("小牛")){
			ch = "DAL";
		}else if(en.equals("鹈鹕")){
			ch = "NOP";
		}else if(en.equals("国王")){
			ch = "SAC";
		}else if(en.equals("太阳")){
			ch = "PHX";
		}else if(en.equals("勇士")){
			ch = "GSW";
		}else if(en.equals("快船")){
			ch = "LAC";
		}else if(en.equals("湖人")){
			ch = "LAL";
		}else if(en.equals("森林狼")){
			ch = "MIN";
		}else if(en.equals("爵士")){
			ch = "UTA";
		}else if(en.equals("开拓者")){
			ch = "POR";
		}else if(en.equals("雷霆")){
			ch = "OKC";
		}else if(en.equals("掘金")){
			ch = "DEN";
		}else if(en.equals("热火")){
			ch = "MIA";
		}else if(en.equals("魔术")){
			ch = "ORL";
		}else if(en.equals("老鹰")){
			ch = "ATL";
		}else if(en.equals("奇才")){
			ch = "WAS";
		}else if(en.equals("黄蜂")){
			ch = "CHA";
		}else if(en.equals("活塞")){
			ch = "DET";
		}else if(en.equals("步行者")){
			ch = "IND";
		}else if(en.equals("骑士")){
			ch = "CLE";
		}else if(en.equals("公牛")){
			ch = "CHI";
		}else if(en.equals("雄鹿")){
			ch = "MIL";
		}else if(en.equals("尼克斯")){
			ch = "NYK";
		}else if(en.equals("76人")){
			ch = "PHI";
		}else if(en.equals("凯尔特人")){
			ch = "BOS";
		}else if(en.equals("篮网")){
			ch = "BKN";
		}else if(en.equals("猛龙")){
			ch = "TOR";
		}else if(en.equals("黄蜂")){
			ch = "NOH";
		}else if(en.equals("无")){
			ch = "null";
		}
		return ch;
	}
	
	public static String checkNOH(String s){
		if(s.equals("NOH")){
			return "NOP";
		}else{
			return s;
		}
	}
	

	
	
}
