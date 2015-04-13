package assistance;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import data.po.PlayerDataPO;
import bussinesslogic.player.PlayerLogic;

public class FileListener {
	PlayerLogic p = new PlayerLogic();
	public void Listen(String path){
		 try{
		        WatchService watchService=FileSystems.getDefault().newWatchService();  
		        Paths.get(path).register(watchService,   
		                StandardWatchEventKinds.ENTRY_CREATE,  
		                StandardWatchEventKinds.ENTRY_DELETE,  
		                StandardWatchEventKinds.ENTRY_MODIFY);  
		        while(true)  
		        {  
		            WatchKey key=watchService.take();  
		            for(WatchEvent<?> event:key.pollEvents())  
		            {  
		            	//String[] temp = event.context().toString().split("_");
		            	if(event.kind().toString().equals("ENTRY_CREATE")){
		            		p.initialize("./迭代一数据/players/info", "12-13");
		            		PlayerDataPO res = p.getInfo("Aaron Brooks", "12-13");
		            		System.out.println(res.getName()+";"+res.getFieldGoalPercentage());
		            	}
		                 
		            }  
		            if(!key.reset())  
		            {  
		                break;  
		            }  
		        }
		          }
		          catch(Exception e){
		        	  e.printStackTrace();
		          }
		} 
	}

