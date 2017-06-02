package ArithmeticServer;

//java -cp mysql-connector-java.jar;. ArithmeticServer

//*******************************************************************
//* Network Programming - Unit 5 Remote Method Invocation *
//* Program Name: ArithmeticRMIImpl *
//* The program implements the services defended in the interface, *
//* ArithmeticInterface.java, for Java RMI. *
//* 2014.02.26 *
//*******************************************************************
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.sql.*;

import java.net.*;

import java.util.*;
import java.lang.NullPointerException;

public class ArithmeticRMIImpl extends UnicastRemoteObject implements ArithmeticInterface {
	// This implementation must have a public constructor.
	// The constructor throws a RemoteException.
	FileReader fr;
	FileWriter fw;
	BufferedReader br;
	int QUIT = 0, FIND = 0;
	String response;
	Connection conn = null;
	Statement st;
	int port = 6669;
	int chatroom_Id = 0;
	int subject_Id = 0, reply_Id = 20;
	java.sql.Timestamp current_Time = new java.sql.Timestamp(System.currentTimeMillis()); // sql
	boolean insideSystem = false; // teimestamp
	// get
	// now
	// time.
	List<String> message = new ArrayList<>();

	public ArithmeticRMIImpl() throws java.rmi.RemoteException {
		super(); // Use constructor of parent class
	}

	public void connect() throws java.rmi.RemoteException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connect to MySQLToJava");
			String datasource = "jdbc:mysql://localhost/newschema?user=root&password=0000";
			conn = DriverManager.getConnection(datasource);
			System.out.println("Connect to MySQL");
			st = conn.createStatement();

		} catch (Exception e) {
			System.out.println("error");
		}
	}

	// Implementation of the service defended in the interface
	public String singIn(String names) throws java.rmi.RemoteException {
		String name = names.replaceAll("\\s+", "");
		response = "";
		try {
			connect();
			st.execute("SELECT * FROM user WHERE username='" + name + "'");
			ResultSet rs = st.getResultSet();
			if (rs.next() == false) {
				response = "The Username isn't exist.";
			} else if (rs.getString("username").compareTo(name) == 0) {
				response = "Sign in success.";

				st.execute("SELECT * FROM user");
				rs = st.getResultSet();

				while (rs.next()) { // readline repeat
					if (rs.getString("username").equals(name)) {
						response += rs.getString("portNumber");
					}
				}

				System.out.println(response);

				insideSystem = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return response;
	}

	public String register(String names) throws java.rmi.RemoteException {
		String name = names.replaceAll("\\s+", "");
		try {
			connect();
			st.execute("SELECT * FROM user WHERE username='" + name + "'");
			ResultSet rs = null;

			rs = st.getResultSet();
			if (rs.next() == false) {
				response = "register Success" + port;
				System.out.println(response);

				String query = " insert into user (username, portNumber)" + " values (?, ?)";
				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, name);
				preparedStmt.setInt(2, port);

				preparedStmt.execute();
			} else if (rs.getString("username").compareTo(name) == 0) {
				response = "Already have the same username.";
				System.out.println(response);

				System.out.println(rs.getString("username"));
			}

		} catch (Exception e) {
			System.out.println("error");
		}
		port++;
		return response;
	}

	public String create(String name, String subject, String subject_Content) throws java.rmi.RemoteException {
		if (insideSystem) {
			try {
				connect();
				subject_Id++;
				// the mysql insert statement

				String query = " insert into subject (subject_id, user, subject, date, content)"
						+ " values (?, ?, ?, ?, ?)";
				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, subject_Id);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, subject);
				preparedStmt.setTimestamp(4, current_Time);
				preparedStmt.setString(5, subject_Content);
				preparedStmt.execute();

				response = "creat subject success.";
			} catch (Exception e) {
				System.out.println("error");
			}
		} else {
			response = "Please sign in the system .";
		}

		return response;
	}

	public ArrayList<String> subject() throws java.rmi.RemoteException {
		ArrayList<String> str = new ArrayList<String>();
		try {
			connect();
			st.execute("SELECT * FROM subject");
			ResultSet rs = st.getResultSet();

			while (rs.next()) { // readline repeat
				str.add(rs.getString("subject_id") + "\t");
				str.add(rs.getString("user"));
				System.out.println(rs.getString("subject"));
				str.add(rs.getString("subject"));
				subject_Id = rs.getInt("subject_id");
			}
		} catch (Exception e) {
			System.out.println("error");
		}
		return str;
	}

	public String reply(String name, int subject_Id_reg, String reply) throws java.rmi.RemoteException {
		String query = " insert into reply (reply_id, subject_id, subject, user, reply, date)"
				+ " values (?, ?, ?, ?, ?, ?)";
		response = "";
		try {
			connect();
			st.execute("SELECT reply_id FROM reply ORDER BY reply_id ASC");
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				reply_Id = rs.getInt("reply_id");
				System.out.println(reply_Id);

			}

			reply_Id++;

			st.execute("SELECT * FROM subject WHERE subject_id='" + subject_Id_reg + "'");
			rs = st.getResultSet();
			if (rs.next() == false) {
				response = "subject isn't exist.";
			} else {
				System.out.println(rs.getString("subject"));
				String subject = rs.getString("subject");

				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt;
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, reply_Id);
				preparedStmt.setInt(2, subject_Id_reg);
				preparedStmt.setString(3, subject);
				preparedStmt.setString(4, name);
				preparedStmt.setString(5, reply);
				preparedStmt.setTimestamp(6, current_Time);
				preparedStmt.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public ArrayList<String> discussion(int subject_Id) throws java.rmi.RemoteException {
		ArrayList<String> str = new ArrayList<String>();

		try {
			connect();
			st.execute("SELECT * FROM subject WHERE subject.subject_id='" + subject_Id + "'");

			ResultSet rs = st.getResultSet();
			rs.next();
			str.add(rs.getString("user") + "\t");
			str.add(rs.getString("subject"));
			str.add(rs.getString("date"));
			str.add(rs.getString("content"));

			st.execute("SELECT * FROM reply WHERE reply.subject_id='" + subject_Id + "'");
			rs = st.getResultSet();

			while (rs.next()) {
				str.add(rs.getInt("reply_id") + "\t" + rs.getString("user"));
				str.add(rs.getString("reply"));
				str.add(rs.getString("date"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public String deleteSubject(String name, int subject_Id) throws java.rmi.RemoteException {
		String query = " delete from subject where user = ? and subject_id = ?";
		try {
			connect();
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, subject_Id);
			preparedStmt.execute();
			response = "Delete success.";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public String deleteReply(String name, int subject_Id, int reply_Id) throws java.rmi.RemoteException {
		String check = "SELECT * FROM subject,reply WHERE subject.subject_id= ? and reply_Id= ? and subject.user= ?";
		String query = " delete from reply where reply_id = ?";
		try {
			connect();

			PreparedStatement preparedStmt = conn.prepareStatement(check);
			preparedStmt.setInt(1, subject_Id);
			preparedStmt.setInt(2, reply_Id);
			preparedStmt.setString(3, name);
			preparedStmt.execute();
			ResultSet rs = preparedStmt.getResultSet();
			if (rs.next() != false) {
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, reply_Id);
				preparedStmt.execute();
				response = "Delete success.";
			} else {
				response = "Delete fair.";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public String chatroom(String message, String toAddress, String fromAddress, String toName)
			throws java.rmi.RemoteException {
		if (insideSystem) {
			Socket client = null;
			Socket sc = null;
			chatroom_Id++;
			current_Time = new java.sql.Timestamp(System.currentTimeMillis());
			try {
				connect();
				String query = " insert into chatroom (No,toAddress, fromAddress, message, date)"
						+ " values ( ?,?,?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, chatroom_Id);
				preparedStmt.setString(2, toAddress);
				preparedStmt.setString(3, fromAddress);
				preparedStmt.setString(4, message);
				preparedStmt.setTimestamp(5, current_Time);
				preparedStmt.execute();

				st.execute("SELECT * FROM user");
				ResultSet rs = st.getResultSet();

				while (rs.next()) { // readline repeat
					
					if (rs.getString("username").equals(toName)) {
						
						port = Integer.parseInt(rs.getString("portNumber"));
					}
				}

			} catch (Exception e) {
				System.out.println("error");
			}

			try {
				// Listens for a connection to be made to this socket and
				// accepts it.
				System.out.println(port);
				client = new Socket(toAddress, port);
				PrintStream writer;// 在此我使用PrintStream將字串進行編寫和送出
				writer = new PrintStream(client.getOutputStream());
				writer.println(message + " " + fromAddress);
				response = "The message have been deliver to " + toAddress;
				writer.flush();

				client.close();
				// Closes this socket

			} catch (IOException e) {
				System.err.println(e);
			}
		} else {
			response = "Please sign in the system .";
		}

		return response;
	}

}
