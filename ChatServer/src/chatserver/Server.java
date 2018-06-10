package chatserver;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private static ArrayList<Socket> sockets;
	
	public static void addSocket(Socket socket) {
		sockets.add(socket);
	}
	public static ArrayList<Socket> getSockets(){
		return sockets;
	}
	public static void sendMessage(String message, Socket sendersSocket) {
		for(Socket socket : sockets)
		{
			try {
				if(!socket.isClosed() && socket != sendersSocket) {
					PrintWriter sendingToUser = new PrintWriter(socket.getOutputStream(), true);
					sendingToUser.println(message);					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String [] args) {
		sockets = new ArrayList<>();
		try(ServerSocket serverSocket = new ServerSocket(4000)){
			while(true) {
				new ServerMessageHub(serverSocket.accept()).start();
			} 		
		}catch(IOException e) {
			System.out.println("Server Exception"+e.getMessage());
		}
	}
}