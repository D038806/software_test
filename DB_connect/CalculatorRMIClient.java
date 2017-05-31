
// *******************************************************************
// * Network Programming - Unit 5 Remote Method Invocation *
// * Program Name: CalculatorRMIClient *
// * The program is a RMI client. *
// * Usage: java CalculatorRMIClient op num1 num2, *
// * op = add, sub, mul, div *
// * 2014.02.26 *
// *******************************************************************
import java.io.*;
import java.rmi.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.Attributes.Name;
import java.net.*;

import javax.naming.spi.DirStateFactory.Result;
import javax.security.auth.Subject;

class CalculatorRMIClient {

	
	ArithmeticInterface o = null;
	int op = 0; // add=0, sub=1, mul = 2, div = 3
	int QUIT = 0;
	String result = "";
	String name = "", subject = "";
	int subject_Id;
	int port = 0;
	Scanner scan = new Scanner(System.in);
	// System.setSecurityManager(new RMISecurityManager());
	// Connect to RMIServer
	
	public CalculatorRMIClient() {
		try {
			o = (ArithmeticInterface) Naming.lookup("rmi://127.0.0.1/arithmetic");
			System.out.println("RMI server connected");
		} catch (Exception e) {
			System.out.println("Server lookup exception: " + e.getMessage());
		}
	}

	public void signIn(String name ,String pwd){
		try{
		result = o.singIn(name);
		if(!result.equals("Already have the same username.")){
			port = Integer.parseInt(result.substring(16,20));
			ThreadBySubclass threadListen = new ThreadBySubclass("listen",port);
			threadListen.start();
		}
			
		System.out.println(port);
	} catch (

			Exception e) {
				System.out.println("ArithmeticServer exception: " + e.getMessage());
				e.printStackTrace();
			}
	}

	
	public void register(String name ,String pwd){
		
		try{

			result = o.register(name);

			System.out.println(result);
		} catch (

				Exception e) {
					System.out.println("ArithmeticServer exception: " + e.getMessage());
					e.printStackTrace();
				}
		
	}
	public void chatroom(String message,String toAddress ,String toName){
		
		
		
		try{

			result = o.chatroom(message, toAddress, "127.0.0.1", toName);
			System.out.println(result);
		} catch (

				Exception e) {
					System.out.println("ArithmeticServer exception: " + e.getMessage());
					e.printStackTrace();
				}
		
		
	}
	
	public void subject(String subject,String subject_Content ){
		try{

			result = o.create(name, subject, subject_Content);
		} catch (

				Exception e) {
					System.out.println("ArithmeticServer exception: " + e.getMessage());
					e.printStackTrace();
				}
		
		
	}
	
	
	
	public static void main(String args[]) {
		ArithmeticInterface o = null;
		int op = 0; // add=0, sub=1, mul = 2, div = 3
		int QUIT = 0;
		String result = "";
		String name = "", subject = "";
		int subject_Id;
		int port = 0;
		Scanner scan = new Scanner(System.in);
		// System.setSecurityManager(new RMISecurityManager());
		// Connect to RMIServer
		try {
			o = (ArithmeticInterface) Naming.lookup("rmi://127.0.0.1/arithmetic");
			System.out.println("RMI server connected");
		} catch (Exception e) {
			System.out.println("Server lookup exception: " + e.getMessage());
		}

		try {
			while (QUIT == 0) {

				System.out.println(
						"(0)sing in (1)register (2)create (3)subject (4)reply (5)discussion (6)delete (7)QUIT (8)chatroom");
				System.out
						.println("-----------------------------------------------------------------------------------");
				System.out.println("請輸入選樣  : ");
				op = scan.nextInt();

				switch (op) {

				case 0:
					result = "";

					System.out.println("Input your UserName:");
					name = scan.next();
					result = o.singIn(name);
					if (!result.equals("Already have the same username.")) {
						port = Integer.parseInt(result.substring(16, 20));
						ThreadBySubclass threadListen = new ThreadBySubclass("listen", port);
						threadListen.start();
					}

					System.out.println(port);

					break;
				case 1:
					result = "";

					System.out.println("Register your UserName:");
					name = scan.next();
					result = o.register(name);

					System.out.println(result);
					break;
				case 2:
					result = "";
					String subject_Content;
					System.out.println("Creat your Subject:");
					subject = scan.next();
					System.out.println("Input your Subject's Content:");
					subject_Content = scan.next();
					result = o.create(name, subject, subject_Content);
					break;
				case 3:
					result = "";
					ArrayList<String> str = new ArrayList<String>();
					str = o.subject();
					System.out.println("---------------------------------------------------------------------");
					System.out.format("%s%-20s%s", "No.\t", "User", "Subject\n");
					System.out.println("---------------------------------------------------------------------");

					for (int i = 0; i < str.size(); i++) {
						System.out.print(str.get(i).toString());
						System.out.format("%-20s", str.get(i + 1).toString());
						System.out.println(str.get(i + 2).toString());
						i = i + 2;
					}
					System.out.println("---------------------------------------------------------------------");

					break;
				case 4:
					result = "";
					String reply_Content;
					System.out.println("Select the Subject's Number:");
					subject_Id = scan.nextInt();
					System.out.println("Input your Reply's Content");
					reply_Content = scan.next();
					result = o.reply(name, subject_Id, reply_Content);
					System.out.println(result);
					break;
				case 5:
					result = "";
					ArrayList<String> str1 = new ArrayList<String>();
					System.out.println("Select the Subject's Number:");
					subject_Id = scan.nextInt();
					str1 = o.discussion(subject_Id);
					System.out.println("---------------------------------------------------------------------");
					System.out.format("%s%-20s%s", "User\t", "Subject", "Date\n");
					System.out.print(str1.get(0).toString());
					System.out.format("%-20s", str1.get(1).toString());
					System.out.println(str1.get(2).toString());
					System.out.println("---------------------------------------------------------------------");
					System.out.println("Content:");
					System.out.println(str1.get(3).toString());
					System.out.println("---------------------------------------------------------------------");
					System.out.println("Reply");

					for (int i = 4; i < str1.size(); i++) {
						System.out.format("%-20s%-30s", str1.get(i).toString(), str1.get(i + 1).toString());
						System.out.println(str1.get(i + 2).toString());
						i = i + 2;
					}
					System.out.println("---------------------------------------------------------------------");

					break;
				case 6:
					int choice, reply_Id;
					result = "";
					System.out.println("Delete the Subject or Reply? (1 or 2)");
					choice = scan.nextInt();
					System.out.println("Select the Subject's Number:");
					subject_Id = scan.nextInt();
					if (choice == 1) {
						result = o.deleteSubject(name, subject_Id);
					} else if (choice == 2) {
						System.out.println("Select the Reply's Number:");
						reply_Id = scan.nextInt();
						result = o.deleteReply(name, subject_Id, reply_Id);
					}
					System.out.println(result);
					break;
				case 7:
					QUIT = 1;
					break;

				case 8:
					// byte[] buf = new byte[100];
					// DatagramPacket packet = new DatagramPacket(buf,
					// buf.length);
					System.out.print("toAddress :");
					String toAddress = "";
					toAddress = scan.next();
					System.out.print("message :");
					String message = "";
					message = scan.next();
					System.out.print("toName :");
					String toName = "";
					toName = scan.next();
					// String fromAddress = packet.getAddress().toString();
					result = "";
					result = o.chatroom(message, toAddress, "127.0.0.1", toName);
					System.out.println(result);
					break;

				case 9:
					System.out.println("-----------------------------------------------------");
					ServerSocket srverSocket = null;
					Socket sc = null;
					srverSocket = new ServerSocket(6666);
					try {
						// Listens for a connection to be made to this socket
						// and
						// accepts it.
						sc = srverSocket.accept();

						BufferedReader reader;// 在此我使用BufferedReader將資料進行接收和讀取

						reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
						String temp = reader.readLine();
						System.out.println(temp);
						sc.close();
					} catch (IOException e) {
						System.err.println(e);
					} finally {
						srverSocket.close();
					}

					System.out.println("-----------------------------------------------------");
					break;

				}

			}
		} catch (

		Exception e) {
			System.out.println("ArithmeticServer exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

class ThreadBySubclass extends Thread {
	String ThreadName;
	Socket sc = null;
	int portNUmber = 0;
	ServerSocket srverSocket = null;

	ThreadBySubclass(String name, int port) // Constructor
	{

		ThreadName = name;
		portNUmber = port;
	}

	public void run() // execute after thread has been initialized
	{

		Thread t = Thread.currentThread();

		System.out.println(portNUmber);
		System.out.println("Waiting .....");
		while (true) {
			// System.out.println("Thread " + (Thread.currentThread()).getName()
			// + " is created!!");
			try {
				srverSocket = new ServerSocket(portNUmber);

				// Listens for a connection to be made to this socket
				// and
				// accepts it.
				sc = srverSocket.accept();

				BufferedReader reader;// 在此我使用BufferedReader將資料進行接收和讀取

				reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
				String temp = reader.readLine();
				System.out.println(temp);
				srverSocket.close();
				sc.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}

	}
}
