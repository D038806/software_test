package software_test;

//*******************************************************************
//*  Network Programming - Unit 2 Simple Client and Server          *
//*  Program Name: SimpleClient                                     *
//*  The program connects to server and send/receive message.       *
//*  The program gets the server IP from args[0].                   *
//*  2016.02.01                                                     *
//*******************************************************************
//D0381806	邱皇旗
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.NullPointerException;

public class client {
	public static void main(String args[]) {
		Socket client = null;

		int port = 6666;
		byte[] buf = new byte[100];
		Scanner scan = new Scanner(System.in);
		int num = 10;
		String temp = "";
		if (args.length == 0) {
			System.out.println("Usage: java SimpleClient server_ip");
		} else {
			try {
				// Creates a stream socket and connects it to the specified port
				// number
				// at the specified IP address.
				client = new Socket(args[0], port);
				
				num = scan.nextInt();
				PrintStream writer;// 在此我使用PrintStream將字串進行編寫和送出
				BufferedReader reader;// 在此我使用BufferedReader將資料進行接收和讀取
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				writer = new PrintStream(client.getOutputStream());
				
				while (num > 0) {
					temp = "";
					if (num > 0) {
						num = num - 1;
						// Send message to server
						
						temp = String.valueOf(num);
						writer.println(temp);

					}
					if (num >= 0) {
						
						temp = reader.readLine();
						
						num = Integer.parseInt(temp);
						System.out.println("Receive message: " + num);
					}
				}
				writer.flush();

				client.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
}