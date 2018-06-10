package chatserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerMessageHub extends Thread{
	
	private Socket socket;
	public ServerMessageHub(Socket socket) {
		this.socket = socket;
		Server.addSocket(this.socket);
	}
	
	@Override
	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				String echoString = input.readLine();
				if(echoString == null || echoString.contains("exit")) {
					break;
				}
				Server.sendMessage(echoString, socket);
				System.out.println("Received Client Input: " + echoString);
			}
		}catch(IOException e) {
			System.out.println("Client dropped: " + e.getMessage());
		}finally {
			try {
				socket.close();
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}	
	}
}