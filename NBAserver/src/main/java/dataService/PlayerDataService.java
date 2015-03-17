package dataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import data.po.PlayerDataPO;

public interface PlayerDataService extends Remote{
	public void addInfo(PlayerDataPO p)throws RemoteException;
	public PlayerDataPO getInfo(String name)throws RemoteException;
	public PlayerDataPO[] getAllInfo()throws RemoteException;
	public void setOrder(String orderName)throws RemoteException;
	public PlayerDataPO[] getFirstFifty(String orderName)throws RemoteException;
}