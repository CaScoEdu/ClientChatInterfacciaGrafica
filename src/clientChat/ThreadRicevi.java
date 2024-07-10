package clientChat;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class ThreadRicevi implements Runnable{
	private Socket socket;
	private JTextArea textArea;
	 BufferedReader in; 
	public  ThreadRicevi(Socket socket, JTextArea textArea) throws IOException {
		this.socket=socket;
		this.textArea = textArea;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} 
	
	
	public void run() {
		String messaggio;
		try {
			messaggio = in.readLine();
		
			while(messaggio!=null){
				textArea.append(messaggio+"\n");
				messaggio = in.readLine();
			}
			System.out.println("Server Chiuso");
			socket.close();
		} catch (IOException e) {
			System.out.println("Errore di connessione");
		}
	}
	

}