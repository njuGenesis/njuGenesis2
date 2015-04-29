package assistance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import presentation.mainui.StartUI;
import bussinesslogic.match.MatchLogic;
import bussinesslogic.player.PlayerLogic;

public class FileListener {
	PlayerLogic p = new PlayerLogic();
	MatchLogic m = new MatchLogic();
	public void Listen(String path){
		 try{
			 int i = 0;
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
		            	
		            	if(event.kind().toString().equals("ENTRY_MODIFY")){
		            		System.out.println(i);
		            		i++;
		            		String temp = "./迭代一数据/matches/"+event.context().toString();
		            		String ptemp = event.context().toString().substring(0,5);
		            		//System.out.println(event.context().toString()+event.kind());
		            		System.out.println("start "+MatchLogic.getTime());
		            		//p.initialize("./迭代一数据/players/info",ptemp);
		            		//Thread.sleep(200);
		            		p.updatePlayer(event.context().toString(), ptemp);
		            		System.out.println("play  "+MatchLogic.getTime());
		            		m.update(temp);
		            		
		            		
		            		showNotice();
		            		
		            		
		            		//System.out.println(p.getInfo("Paul Pierce", "12-13").getFieldGoalPercentage());
		            		System.out.println("end   "+MatchLogic.getTime());
		            		
		            		//PlayerDataPO res = p.getInfo("Aaron Brooks", "12-13");
		            		//System.out.println(res.getName()+";"+res.getFieldGoalPercentage()+res.getGP());
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
	
	
	public void showNotice(){
		final JFrame notice = new JFrame();
		notice.setSize(300, 300);
		
		JLabel info = new JLabel("数据已更新，即将刷新界面");
		notice.add(info);
		
		JButton comfirm = new JButton("确认");
		comfirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				notice.setVisible(false);
				StartUI.startUI.refreshUI();
			}
			
		});
		notice.add(comfirm);
		
		notice.setVisible(true);
		
	}
	   
	
	public static void main(String[] args) {
		FileListener l = new FileListener();
		l.Listen("迭代一数据/matches");
	}
}

