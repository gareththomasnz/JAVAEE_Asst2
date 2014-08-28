import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread {

	DataInputStream dis;
	DataOutputStream dos;
	
	Socket remoteClient;
	Server server;
	
	ArrayList<ServerThread> connectedClients;
	
	public ServerThread(Socket remoteClient, Server server, ArrayList<ServerThread> connectedClients)
	{
		this.remoteClient = remoteClient;
		this.connectedClients = connectedClients;
		try {
			this.dis = new DataInputStream(remoteClient.getInputStream());
			this.dos = new DataOutputStream(remoteClient.getOutputStream());
			this.server = server;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	

	public void run()
	{
		while(true)
		{
			try {
				int mesgType = dis.readInt();
				System.err.println(mesgType);
				switch(mesgType)
				{
					case ServerConstants.CHAT_MESSAGE:
						String data = dis.readUTF();
						System.err.println(data);
						server.getSystemLog().append(remoteClient.getInetAddress()+":"+remoteClient.getPort()+">"+data+"\n");
						
						for(ServerThread otherClient: connectedClients)
						{
							if(!otherClient.equals(this))
							{
								otherClient.getDos().writeInt(ServerConstants.CHAT_BROADCAST);
								otherClient.getDos().writeUTF(data);
							}
						}
						
						break;
				}
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return;
			}
		}
	}


	public DataOutputStream getDos() {
		return dos;
	}
}
