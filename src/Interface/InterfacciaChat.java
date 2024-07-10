package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;

import clientChat.Invio;
import clientChat.ThreadRicevi;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class InterfacciaChat {

	private JFrame frmChat;
	private JTextField textInvio;
	private JButton btnInvia;
	private JLabel lblTitolo;
	private JTextArea txtMessaggi;
	private Invio invio;
	private JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaChat window = new InterfacciaChat();
					window.frmChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfacciaChat() {
		
		
		
		initialize();
		
		
		Socket clientSocket; 
		 
		 try {
			clientSocket = new Socket("127.0.0.1", 5500);
			invio = new  Invio(clientSocket);
			Thread riceviThread = new Thread(new ThreadRicevi(clientSocket,txtMessaggi));
			
			riceviThread.start();
			
			String nomeUtente =  JOptionPane.showInputDialog(null, "Dammi il nome utente", "Benvenuto nella chat", JOptionPane.QUESTION_MESSAGE);
			if (nomeUtente==null) nomeUtente="anonimo";
			invio.inviaDati(nomeUtente);
			
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(frmChat, e, null, 0);
			System.exit(0);
			
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChat = new JFrame();
		frmChat.setTitle("Chat");
		frmChat.setBounds(100, 100, 950, 700);
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().setLayout(new MigLayout("debug, fill", "[grow][]", "[][grow][]"));
		
		lblTitolo = new JLabel("CHAT MULTIPLA");
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmChat.getContentPane().add(lblTitolo, "cell 0 0 2 1,alignx center,aligny center");
		
		scrollPane = new JScrollPane();
		frmChat.getContentPane().add(scrollPane, "cell 0 1 5 1,grow");
		
		txtMessaggi = new JTextArea();
		scrollPane.setViewportView(txtMessaggi);
		txtMessaggi.setEditable(false);
		txtMessaggi.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtMessaggi.setText("Benvenuto nella Chat");
		txtMessaggi.append("\n");
		
		
		
		textInvio = new JTextField();
		textInvio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InviaMessaggio();
			}
		});
		textInvio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmChat.getContentPane().add(textInvio, "cell 0 2,growx");
		textInvio.setColumns(10);
		
		btnInvia = new JButton("Invia");
		btnInvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InviaMessaggio();
			}
		});
		btnInvia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmChat.getContentPane().add(btnInvia, "cell 1 2");
	}
	
	public void InviaMessaggio() {
		
		invio.inviaDati(textInvio.getText());
	}

}
