package ArithmeticServer;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

public class ArithmeticRMIImplTest {

	/*
	 * 測試登入模組
	 * 測試案例中包含:
	 * 	(1)正常輸入 (2)不存在輸入 (3) 特殊字元輸入 
	 * 
	 * */
	
	@Test
	public void signintest() throws RemoteException {
		ArithmeticRMIImpl rmiTest = new ArithmeticRMIImpl();
		assertEquals("Sign in success.6666", rmiTest.singIn("AAAA"));
		assertEquals("Sign in success.6667", rmiTest.singIn("QQQQ"));	
		assertEquals("The Username isn't exist.", rmiTest.singIn("AAsQA"));
		assertEquals("Sign in success.6670", rmiTest.singIn("AAQasdasd132A"));
		assertEquals("It's illegal.", rmiTest.singIn("AAQA+_)(*$W"));
		assertEquals("It's illegal.", rmiTest.singIn("AAA "));
		assertEquals("It's illegal.", rmiTest.singIn(" AQA"));
		assertEquals("It's illegal.", rmiTest.singIn("AAQ fA"));
		assertEquals("It's illegal.", rmiTest.singIn("AAQ****A"));
	}
	
	/*
	 * 測試註冊模組
	 * 測試案例中包含:
	 * 	(1)已存在會員重測 (2)不存在會員輸入 (3) 特殊字元輸入 
	 * 
	 * */
	
	@Test
	public void registertest() throws RemoteException {
		ArithmeticRMIImpl rmiTest = new ArithmeticRMIImpl();
		assertEquals("Already have the same username.", rmiTest.register("AAAA"));
		assertEquals("Already have the same username.", rmiTest.register("QQQQ"));	
		assertEquals("Already have the same username.", rmiTest.register("AAA"));
		assertEquals("Already have the same username.", rmiTest.register("AAQasdasd132A"));
		assertEquals("It's illegal.", rmiTest.register("AAQA+_)(*$W"));
		assertEquals("It's illegal.", rmiTest.register("AAQA "));
		assertEquals("It's illegal.", rmiTest.register(" AQA"));
		assertEquals("It's illegal.", rmiTest.register("AAQ fA"));
		assertEquals("It's illegal.", rmiTest.register("AAQ****A"));
	}

	/*
	 * 測試主題模組
	 * 測試案例中包含:
	 * 	(1)缺少項目測試 (2)輸入為空白在 字前、字後、字中。  
	 * 
	 * 可新增name的驗證
	 * */
	
	
//	@Test
//	public void createtest() throws RemoteException {
//		ArithmeticRMIImpl rmiTest = new ArithmeticRMIImpl();
//		assertEquals("creat subject success.", rmiTest.create("ADSD","aaasd","WERTYUIO"));
//		assertEquals("creat subject success.", rmiTest.create("ADSD","","WERTYUIO"));
//		assertEquals("creat subject success.", rmiTest.create("ADSD","aaasd",""));
//		assertEquals("creat subject success.", rmiTest.create("ADSD","aaasd"," WERTYUIO"));
//		assertEquals("creat subject success.", rmiTest.create("ADSD","aaasd","WERTYUIO "));
//		assertEquals("creat subject success.", rmiTest.create("ADSD","aaasd ","WERTYUIO"));
//		assertEquals("creat subject success.", rmiTest.create("ADSD"," aaasd","WERTYUIO"));
//
//	}
	
	/*
	 * 測試主題模組
	 * 測試案例中包含:
	 * 	(1)正常輸入 (2)欄位缺少 (3)給予不存在的  
	 * 
	 * 可新增name的驗證
	 * */
	
	@Test
	public void replytest() throws RemoteException {
		ArithmeticRMIImpl rmiTest = new ArithmeticRMIImpl();
		assertEquals("", rmiTest.reply("AAAA", 1, "OK"));
		assertEquals("", rmiTest.reply("AAA", 1, "OK"));
		assertEquals("", rmiTest.reply("AAAA", 1, ""));
		assertEquals("", rmiTest.reply("", 1, "OK"));
		assertEquals("subject isn't exist.", rmiTest.reply("AAAA", 0, "OK"));
	}
	
	
	
	

}
