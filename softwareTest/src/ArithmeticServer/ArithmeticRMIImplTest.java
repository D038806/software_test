package ArithmeticServer;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

public class ArithmeticRMIImplTest {

	@Test
	public void signintest() throws RemoteException {
		ArithmeticRMIImpl rmiTest = new ArithmeticRMIImpl();
		assertEquals("Sign in success.6666", rmiTest.singIn("AAAA"));
		assertEquals("Sign in success.6667", rmiTest.singIn("QQQQ"));	
		assertEquals("The Username isn't exist.", rmiTest.singIn("AAsQA"));
		assertEquals("The Username isn't exist.", rmiTest.singIn("AAQasdasd132A"));
		assertEquals("The Username isn't exist.", rmiTest.singIn("AAQA+_)(*$W"));
		assertEquals("The Username isn't exist.", rmiTest.singIn("AAA "));
		assertEquals("The Username isn't exist.", rmiTest.singIn(" AQA"));
		assertEquals("The Username isn't exist.", rmiTest.singIn("AAQ fA"));
		assertEquals("The Username isn't exist.", rmiTest.singIn("AAQ****A"));
	}
	
	@Test
	public void registertest() throws RemoteException {
		ArithmeticRMIImpl rmiTest = new ArithmeticRMIImpl();
		assertEquals("Already have the same username.", rmiTest.register("AAAA"));
		assertEquals("Already have the same username.", rmiTest.register("QQQQ"));	
		assertEquals("Already have the same username.", rmiTest.register("AAA"));
		assertEquals("Already have the same username.", rmiTest.register("AAQasdasd132A"));
//		assertEquals("register Success7001", rmiTest.register("AAQA+_)(*$W"));
//		assertEquals("register Success7002", rmiTest.register("AAQA "));
//		assertEquals("register Success7003", rmiTest.register(" AQA"));
//		assertEquals("register Success7004", rmiTest.register("AAQ fA"));
//		assertEquals("register Success7005", rmiTest.register("AAQ****A"));
	}

	

}
