import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Driver {
	int count = 0;
	
	static HashMap<String, windowGUI> map = new HashMap<>();
	String myname = "Natalie";
	String broadcast = ("255.255.255.255");
	InetAddress dest =null;
	InetAddress sender =null;
	public static Socket mySocket = new Socket(64000, Socket.SocketType.Broadcast);

	public Driver(String ip) {

		if (map.containsKey(ip)) {
			windowGUI currentChat = map.get(ip);
			String text = currentChat.msg.getText();
			currentChat.ChatHistory.append("Me: " + text + '\n');
			currentChat.msg.setText("");
			

		}

	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Connector().setVisible(true);
		

		
		DatagramPacket hello = null;

		do {

			System.out.println("Just woke up!!");

			

			hello = mySocket.receive();
			
			
			if (hello == null) {
				
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
					System.exit(-1);
				}
			
			
			}
			
			else {	
				
					byte[] inBuffer = new byte[100];
					for (int i = 0; i < inBuffer.length; i++) {
						inBuffer[i] = ' ';
					}
					
					inBuffer = hello.getData();
					
					String inMsg = new String(inBuffer);
					
					System.out.println("inMsg = " + inMsg);
					
					
					
					
					InetAddress senderAddress = hello.getAddress();
					int senderPort = hello.getPort();
					
					
					String [] strings = inMsg.split(" ");
					
					String ip = senderAddress.getHostAddress() ;
					String theirName ="";
					
					if(map.containsKey(ip)) {
						
						windowGUI currentChat = map.get(ip);
						currentChat.ChatHistory.append("You: " + inMsg + '\n');
					
						System.out.println(inMsg);
					}
					else if (strings[0].equals("?????")&&strings[1].equals("Natalie")) {
						theirName = strings[3];
						windowGUI chatWindow = new windowGUI(theirName, senderAddress, senderPort);
						map.put(ip, chatWindow);
						String 	Text = ("##### "+ "Natalie" + (" ##### ")+ mySocket.getAddress().getHostAddress());
						mySocket.send(Text,senderAddress, 64000);
						
						
		
					} else if (strings[0].equals("#####")) {
						theirName = strings[1];
						windowGUI chatWindow = new windowGUI(theirName, senderAddress, senderPort);
						map.put(ip, chatWindow);
						
						chatWindow.ChatHistory.append("");
						
						System.out.println(inMsg);
		
					} else {System.out.println("Not for me : " + inMsg);}
			
			}
			

		} while (true);

	}

}