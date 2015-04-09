package data.match;
import java.io.*;
import java.util.ArrayList;

import data.po.MatchDataPO;

	public class Cat implements Serializable {
	        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
			public ArrayList<MatchDataPO>  matches=new ArrayList<MatchDataPO>();
	        public Cat () {
	                MatchDataPO match1= new MatchDataPO();
	                match1.setAss1(1);
	                MatchDataPO match2= new MatchDataPO();
	                match1.setAss1(2);
	                this.matches.add(match1);
	                this.matches.add(match2);
	        }
	    
	        public static void main(String[] args) {         
	          /*      Cat cat = new Cat();
	                try {
	                	 File f = new File("d:/456.txt");// 保存两个对象的文件对象 
	                        FileOutputStream fos = new FileOutputStream(f);
	                        ObjectOutputStream oos = new ObjectOutputStream(fos);
	                       
	                        System.out.println("没有被序列化时的对象如下："); 
	                        System.out.println(cat.matches.get(0).getAss1()); 
	                        System.out.println(cat.matches.get(1).getAss1()); 	                        
	                        oos.writeObject(cat); 

	                        System.out.println("序列化成功！！"); 
	                        oos.flush(); 
	                        fos.close(); 
	                        oos.close();                     
	                } catch (Exception ex) {  ex.printStackTrace();   }
	                try {
	                	 File f = new File("d:/456.txt");
	                        FileInputStream fis = new FileInputStream(f);
	                        ObjectInputStream ois = new ObjectInputStream(fis);
	                        
	                        Cat cat2 = (Cat) ois.readObject();
	                        
	                        System.out.println(cat2.matches.get(0).getAss1()); 
	                        System.out.println(cat2.matches.get(1).getAss1()); 	
	                        ois.close();
	                } catch (Exception ex) {
	                        ex.printStackTrace();
	                }*/
	        	
	        	ArrayList<MatchDataPO> match = new ArrayList<MatchDataPO>();
	        	System.out.println(match.size());
	        }
	}

