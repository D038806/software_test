package ArithmeticServer;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

public class ArithmeticRMIImplTest {

	/*
	 * ���յn�J�Ҳ�
	 * ���ծרҤ��]�t:
	 * 	(1)���`��J (2)���s�b��J (3) �S��r����J 
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
	 * ���յ��U�Ҳ�
	 * ���ծרҤ��]�t:
	 * 	(1)�w�s�b�|������ (2)���s�b�|����J (3) �S��r����J 
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
	 * ���եD�D�Ҳ�
	 * ���ծרҤ��]�t:
	 * 	(1)�ʤֶ��ش��� (2)��J���ťզb �r�e�B�r��B�r���C  
	 * 
	 * �i�s�Wname������
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
	 * ���եD�D�Ҳ�
	 * ���ծרҤ��]�t:
	 * 	(1)���`��J (2)���ʤ� (3)�������s�b��  
	 * 
	 * �i�s�Wname������
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
