package assistance;

import java.io.File;

import bussinesslogic.player.PlayerLogic;

public class ConsoleStarter {
	public static void main(String[] args){
		/*for(int i = 0;i<args.length;i++){
			System.out.println(args[i]);
		}
		Console c = new Console();
		c.execute(System.out, args);*/
		
		File root = new File("./playerInfo");//从ser文件中读取所有数据
		File[] files = root.listFiles();
		int i = 0;
		for(File file:files){
			i++;
		}
		if(i==0){
			PlayerLogic p = new PlayerLogic();
			p.initialize("./迭代一数据/players/info",p.getLatestDate());
		}
		Console c = new Console();
		c.execute(System.out, args);
	}
}
