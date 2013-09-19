import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client extends JFrame implements ActionListener, Runnable{
	
	private static final long serialVersionUID = 980389841528802556L;
	JTextField chatInput = new JTextField(50);
	JTextArea chatHistory = new JTextArea(5,50);
	JButton chatMessage = new JButton("Send");
	
	Socket client;
	DataInputStream dis;
	DataOutputStream dos;
	
	public Client()
	{
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(chatInput,BorderLayout.CENTER);
		contentPane.add(chatMessage,BorderLayout.EAST);
		contentPane.add(new JScrollPane(chatHistory),BorderLayout.NORTH);
		
		pack();
		setVisible(true);
		
		chatMessage.addActionListener(this);
		
		try {
			client = new Socket("localhost",5000);
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			
			Thread clientThread = new Thread(this);
			clientThread.start();
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Client client = new Client();
		client.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		try {
			dos.writeInt(ServerConstants.CHAT_MESSAGE);
			dos.writeUTF(chatInput.getText());
			
			dos.flush();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		while(true)
		{
			try {
				int messageType = dis.readInt();
				
				switch(messageType)
				{
					case ServerConstants.CHAT_BROADCAST:
						chatHistory.append(dis.readUTF()+"\n");
						break;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
