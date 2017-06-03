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
		assertEquals("Sign in success.6670", rmiTest.singIn("AAQasdasd132A"));
		assertEquals("It's illegal.", rmiTest.singIn("AAQA+_)(*$W"));
		assertEquals("It's illegal.", rmiTest.singIn("AAA "));
		assertEquals("It's illegal.", rmiTest.singIn(" AQA"));
		assertEquals("It's illegal.", rmiTest.singIn("AAQ fA"));
		assertEquals("It's illegal.", rmiTest.singIn("AAQ****A"));
	}
	
	@Test
	public void registertest() throws RemoteException {
		ArithmeticRMIImpl rmiTest = new ArithmeticRMIImpl();
		assertEquals("Already have the same username.", rmiTest.register("AAAA"));
		assertEquals("Already have the same username.", rmiTest.register("QQQQ"));	
		assertEquals("register Success6669", rmiTest.register("AAA"));
		assertEquals("register Success6670", rmiTest.register("AAQasdasd132A"));
		assertEquals("It's illegal.", rmiTest.register("AAQA+_)(*$W"));
		assertEquals("It's illegal.", rmiTest.register("AAQA "));
		assertEquals("It's illegal.", rmiTest.register(" AQA"));
		assertEquals("It's illegal.", rmiTest.register("AAQ fA"));
		assertEquals("It's illegal.", rmiTest.register("AAQ****A"));
	}

	

}
