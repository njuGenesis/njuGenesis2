package bslogicService;

import data.po.PlayerDataPO;

public interface PlayerInfoService {
	public void analysData(String name,String season);
	public PlayerDataPO getInfo(String name,String season);
	public void setOrder(String orderName,boolean isASC,String season,boolean isAVG);
	public PlayerDataPO[] getAllInfo(String season);
}
