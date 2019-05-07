
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Connector extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public String yourName;
	public static int port;
	JPanel panel = new JPanel();
	public Socket sock;
	public InetAddress dest;
	String broadcast = ("255.255.255.255");
	
	static JTextField name = new JTextField(15);
	
	public JLabel label1 = new JLabel("Name: ");
	

	public static void main(String[] args) throws UnknownHostException {
		new Connector().setVisible(true);
		
		
		

			
	
	}
	
	public Connector() {
		add(panel);
		setTitle("Who you talking to?");
		setSize(400,200);
		setResizable(false);
		
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	
		add(label1);
		label1.setBounds(20,10,200,30);
		
		add(name);
		name.setBounds(120,10,150,30);
		//name.addActionListener(this);
		
		
		
	
		
		JButton button = new JButton("New chat");
		add(button);
		button.setBounds(20,150,100,30);
		button.addActionListener(this);
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		yourName = name.getText(); 
		
		
		try {
			dest = InetAddress.getByName(broadcast);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		
		String 	Text = ("????? "+ yourName + " " + "##### "+ "Natalie");
		Driver.mySocket.send(Text,dest, 64000);
		
		
		
		
	}
	
	

}