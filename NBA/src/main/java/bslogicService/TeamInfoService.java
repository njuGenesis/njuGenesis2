package bslogicService;

import java.util.ArrayList;

import data.po.TeamDataPO;

public interface TeamInfoService {
	public ArrayList<TeamDataPO> GetAllInfo();
	public ArrayList<TeamDataPO> GetInfo(String name);
}
