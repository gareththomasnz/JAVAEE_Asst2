import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Server extends JFrame
{
	private static final long serialVersionUID = -2291453973624020582L;
	ServerSocket serverSocket;
	JTextArea systemLog = new JTextArea(5,60);
	ArrayList <ServerThread> connectedClients = new ArrayList<ServerThread>();
	
	public Server()
	{
		try {
			serverSocket = new ServerSocket(5000);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(new JScrollPane(systemLog),BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	public void start()
	{
		try
		{
			while(true) // keep accepting new clients
			{
				Socket client = serverSocket.accept();
								
				ServerThread st = new ServerThread(client,this,connectedClients);
				st.start();
				
				connectedClients.add(st);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args)
	{
		Server server = new Server();
		server.start();
	}
	public JTextArea getSystemLog() {
		return systemLog;
	}
	public void setSystemLog(JTextArea systemLog) {
		this.systemLog = systemLog;
	}
}
