package ArithmeticServer;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

public class ArithmeticRMIImplTest {

	@Test
	public void testArithmeticRMIImpl() throws RemoteException {
		ArithmeticRMIImpl rmiTest = new ArithmeticRMIImpl();
		assertEquals("Sign in success.6666", rmiTest.singIn("AAAA"));
		assertEquals("Sign in success.6667", rmiTest.singIn("QQQQ"));
		assertEquals("The Username isn't exist.", rmiTest.singIn("AAQA"));
	}

	

}
