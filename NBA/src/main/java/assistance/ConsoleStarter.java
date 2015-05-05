package assistance;

public class ConsoleStarter {
	public static void main(String[] args){
		/*for(int i = 0;i<args.length;i++){
			System.out.println(args[i]);
		}
		Console c = new Console();
		c.execute(System.out, args);*/
		
		for(int i = 0;i<args.length;i++){
			System.out.println(args[i]);
		}
		Console c = new Console();
		c.executeTeam(System.out, args);
	}
}
