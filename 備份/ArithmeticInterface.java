
// *******************************************************************
// * Network Programming - Unit 5 Remote Method Invocation *
// * Program Name: ArithmeticInterface *
// * The program defines the interface for Java RMI. *
// * 2014.02.26 *
// *******************************************************************
import java.rmi.Remote;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ArithmeticInterface extends Remote {
	public String singIn(String name) throws java.rmi.RemoteException, SQLException;

	public String register(String name) throws java.rmi.RemoteException;

	public String create(String name, String subject, String subject_Content) throws java.rmi.RemoteException;

	public ArrayList<String> subject() throws java.rmi.RemoteException;

	public String reply(String name, int subject_Id_reg, String reply) throws java.rmi.RemoteException;

	public ArrayList<String> discussion(int subject_Id) throws java.rmi.RemoteException;

	public String deleteSubject(String name, int subject_Id) throws java.rmi.RemoteException;

	public String deleteReply(String name, int subject_Id, int reply_Id) throws java.rmi.RemoteException;
	
	public String chatroom(String message,String toAddress,String fromAddress,String toName) throws java.rmi.RemoteException;

}
