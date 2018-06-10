package chatclient;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
	public static Socket clientSocket;
	public static String clientName;
	public static void main(String[] args) {
		System.out.println("Enter your name");
		Scanner scanner = new Scanner(System.in);
		clientName = scanner.nextLine();
		try {
			clientSocket = new Socket("localhost", 4000);
			new Sender().start();
			new Receiver().start();
        } catch (IOException e) {
            System.out.println("Client Error: Could not connect to server");
        }
    }
}