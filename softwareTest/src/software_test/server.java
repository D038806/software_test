package software_test;

//*******************************************************************
//*  Network Programming - Unit 2 Simple Client and Server          *
//*  Program Name: SimpleServer                                     *
//*  The program creates a socket and waits for request.            *
//*  2016.02.01                                                     *
//*******************************************************************
//D0381806	邱皇旗
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class server {
	public static void main(String args[]) {
		ServerSocket srverSocket = null;
		Socket sc = null;

		int port = 6666;
		byte[] buf = new byte[100];

		try {
			// Creates a server socket, bound to the specified port.
			while (true) {
				srverSocket = new ServerSocket(port);

				System.out.println("Waiting for request ...");
				try {
					// Listens for a connection to be made to this socket and
					// accepts it.
					sc = srverSocket.accept();
					ThreadBySubclass thread1 = new ThreadBySubclass("T1", sc);
					thread1.start();
					srverSocket = new ServerSocket(port);
					sc = srverSocket.accept();
					// Closes in/out stream and releases any system resources
					// associated with this stream.

					// Closes this socket
					sc.close();
				} catch (IOException e) {
					System.err.println(e);
				} finally {
					srverSocket.close();
				}
			}

		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

class ThreadBySubclass extends Thread {
	String ThreadName;
	Socket sc = null;

	ThreadBySubclass(String name, Socket sc) // Constructor
	{
		ThreadName = name;
		this.sc = sc;
	}

	public void run() // execute after thread has been initialized
	{

		Thread t = Thread.currentThread();
		
		byte[] buf = new byte[100];
		int num = 1;
		String temp = "";
		

		System.out.println("Thread " + (Thread.currentThread()).getName() + " is created!!");
		try {
			PrintStream writer;//在此我使用PrintStream將字串進行編寫和送出
          BufferedReader reader;//在此我使用BufferedReader將資料進行接收和讀取
          
          writer = new PrintStream(sc.getOutputStream());
          reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			while (num > 0) {
				temp = "";
				
				temp=reader.readLine();
				
				num = Integer.parseInt(temp);

				System.out.println("Receive message: " + num);

				if (num >= 0) {
					num = num - 1;
					// Send reply message to client
					// Returns an output stream for socket sc.
					temp = String.valueOf(num);
					
					writer.println(temp);
					
					// getBytes(): Encodes this String into a sequence of
					// bytes using the
					// platform's default charset, storing the result into a
					// new byte array.
				}

			}

			writer.flush();
			// Closes this socket
			sc.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

