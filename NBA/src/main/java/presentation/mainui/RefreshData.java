package presentation.mainui;

import assistance.FileListener;

public class RefreshData implements Runnable{
	FileListener fl = new FileListener();

	@Override
	public void run() {
		while(true){
			System.out.println("running~");
			fl.Listen("迭代一数据/matches");
		}
	}
	
}