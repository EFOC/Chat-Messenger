package chatclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sender extends Thread{
	@Override
	public void run() {
		 try {
			 Scanner scanner;
			 String message_;
			 PrintWriter message = new PrintWriter(MainClient.clientSocket.getOutputStream(), true);
			 message.println(MainClient.clientName + " has entered the chat");
			 do {
				 scanner = new Scanner(System.in);
				 message_ = scanner.nextLine();
				 System.out.println("\t\t\t\t\t" + MainClient.clientName + ": "+ message_);
				 message.println(MainClient.clientName + ": "+ message_);
			 }while(!message_.equals("exit"));
			 message.println(MainClient.clientName + " has left the chat");
			 MainClient.clientSocket.close();
			 scanner.close();
			 System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}