package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Receiver extends Thread{
	
	@Override
	public void run() {
		try {
			BufferedReader receivingMessage = new BufferedReader(new InputStreamReader(MainClient.clientSocket.getInputStream()));
			while(!MainClient.clientSocket.isClosed())
				System.out.println(receivingMessage.readLine());
		} catch (IOException e) {
			if(MainClient.clientSocket.isClosed())
				return; // Client exited on their own terms
			else
				System.out.println("Server closed unexpectedly\nClosing down"); // Server closed
			System.exit(0);
		}
	}
}