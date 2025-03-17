package clientChat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Invio{
	
	
	private PrintWriter out;
	 
	public Invio (Socket socket) throws IOException {
		out = new PrintWriter(socket.getOutputStream());
	}
	
	
	
	public void inviaDati(String dati) {
		
		String message = dati;
		out.println(message);
		out.flush();
	}
	
	
}
