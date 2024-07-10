package clientChat;

import java.awt.TextField;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextField;

public class Invio{
	
	
	private Socket socket;
	private PrintWriter out;
	 
	public Invio (Socket socket) throws IOException {
		this.socket=socket;
		out = new PrintWriter(socket.getOutputStream());
	}
	
	
	
	public void inviaDati(String dati) {
		
		String message = dati;
		out.println(message);
		out.flush();
	}
	
	
}
