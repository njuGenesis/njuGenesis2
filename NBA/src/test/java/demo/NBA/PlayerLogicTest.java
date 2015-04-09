/**
 * 
 */
package demo.NBA;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.po.PlayerDataPO;
import bussinesslogic.player.PlayerLogic;

/**
 * @author lenovo
 *
 */
public class PlayerLogicTest {
	PlayerLogic p;
	public PlayerLogicTest() {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		p = new PlayerLogic();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link bussinesslogic.player.PlayerLogic#getAllMatch(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetAllMatch() {
//		p.getAllMatch("./迭代一数据/matches","Aaron Brooks");
//		assertEquals(p.getpo().getGP(),71);
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link bussinesslogic.player.PlayerLogic#getInfo(java.lang.String)}.
	 */
	@Test
	public void testGetInfo() {
		
//		File root = new File("./迭代一数据/players/info");
//		File[] files = root.listFiles();
//		for(File file:files){
//			//System.out.println(file.getName());
//			p.getAllMatch("./迭代一数据/matches",file.getName());
//			PlayerDataPO res = p.getInfo(file.getName());
//			double r = res.getFTPercentage();
//			System.out.println(r);
//			assertEquals(p.getpo().getFTPercentage(),r,0.001);
//		}
		
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link bussinesslogic.player.PlayerLogic#initialize(java.lang.String)}.
	 */
	@Test
	public void testInitialize() {
//		String res = p.initialize("./迭代一数据/players/info");
//		assertEquals(res,"has initialized");
		//fail("Not yet implemented");
	}

}
