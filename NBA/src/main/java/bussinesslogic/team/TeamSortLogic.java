package bussinesslogic.team;
import java.util.ArrayList;
import java.util.Comparator;

import data.po.TeamDataPO;

public class TeamSortLogic {
	public static  ArrayList<TeamDataPO> sortByString(ArrayList<TeamDataPO> teams, final String property_stirng) {
		teams.sort(new Comparator<TeamDataPO>() {
			public int compare(TeamDataPO o1, TeamDataPO o2) {
				if (((String) o1.getStringProperty(property_stirng)).compareTo((String) o2.getStringProperty(property_stirng)) > 0) {
					return 1;
				}
				return -1;
			}
		});
		return teams;
	}
	
	public static  ArrayList<TeamDataPO> sortByDouble(ArrayList<TeamDataPO> teams,  final String property_double) {
		teams.sort(new Comparator<TeamDataPO>() {
			public int compare(TeamDataPO o1, TeamDataPO o2) {
				if (o1.getDoubleProperty(property_double)>o2.getDoubleProperty(property_double)) {
					return -1;
				}
				return 1;
			}
		});
		return teams;
	}
	
	

	public static void main(String[] args) {
		TeamLogic t = new TeamLogic();
		ArrayList<TeamDataPO> teams =t.GetAllInfo();
		for(int i=0;i<teams.size();i++){
			System.out.println(teams.get(i).getName());
		}
		TeamSortLogic.sortByString(teams,"Name");
		System.out.println("--------------");
		for(int i=0;i<teams.size();i++){
			System.out.println(teams.get(i).getName());
		}
		System.out.println("=======================================");
		for(int i=0;i<teams.size();i++){
			System.out.println(teams.get(i).getBuildyear());
		}
		TeamSortLogic.sortByDouble(teams,"Buildyear");
		System.out.println("--------------");
		for(int i=0;i<teams.size();i++){
			System.out.println(teams.get(i).getBuildyear());
		}
		
	}

}
