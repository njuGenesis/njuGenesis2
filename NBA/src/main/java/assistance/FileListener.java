package assistance;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NOTATIONDatatypeValidator;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import presentation.component.GFrame;
import presentation.component.GLabel;
import presentation.contenui.UIUtil;
import presentation.mainui.StartUI;
import bussinesslogic.match.MatchLogic;
import bussinesslogic.player.PlayerLogic;

public class FileListener {
	PlayerLogic p = new PlayerLogic();
	MatchLogic m = new MatchLogic();
	
	int count = 0;
	
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
//		            WatchKey key=watchService.poll(10, TimeUnit.SECONDS);  
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
		            		Thread.sleep(200);
		            		//Thread.sleep(20);
		            		p.updatePlayer(event.context().toString(), ptemp);
		            		System.out.println("play  "+MatchLogic.getTime());
		            		m.update(temp);
		            		//System.out.println(p.getInfo("Paul Pierce", "12-13").getFieldGoalPercentage());
		            		System.out.println("end   "+MatchLogic.getTime());
		            		
		            		//PlayerDataPO res = p.getInfo("Aaron Brooks", "12-13");
		            		//System.out.println(res.getName()+";"+res.getFieldGoalPercentage()+res.getGP());
		            		
		            		count++;
		            		if(count>50){
		            			count = 0;
		            			showNotice();
		    		            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		            		}
		            		
		            		
		            		
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
		final GFrame notice = new GFrame();
		notice.setSize(300, 180);
		notice.setMiddle();
		notice.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 300, 180);
		panel.setBackground(UIUtil.nbaBlue);
		panel.setLayout(null);
		notice.add(panel);
		
		GLabel message = new GLabel("数据已刷新！", new Point(0, 0), new Point(300, 140), panel, true, 0, 20);
		message.setForeground(UIUtil.bgWhite);
		message.setHorizontalAlignment(JLabel.CENTER);
		
		JButton yes = new JButton("确认");
		yes.setBounds(110, 125, 80, 30);
		panel.add(yes);
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notice.dispose();
				StartUI.startUI.refreshUI();
			}
		});
		
		notice.setVisible(true);
	}
	   
	
	public static void main(String[] args) {
		FileListener l = new FileListener();
		l.Listen("迭代一数据/matches");
		
	}
}

