import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Gui extends JFrame implements Runnable{
private JTextArea messages;
private JTextArea users;
private JPanel msgpanel;
private JPanel userspanel;
private JPanel sendpanel;
private JTextField msg;
private JButton send;
private JPanel sendbuttonpan;
private JPanel connectionpan;
private JButton connect;
private JButton disconnect;
private Color bg=new Color(54,54,54);
private Client cl ;
	public Gui(){
		super("Client");
		cl=new Client();
		setLayout(null);
		messages=new JTextArea(20,20);
		users=new JTextArea(20,10);
		msgpanel=new JPanel();
		userspanel=new JPanel();
		sendpanel=new JPanel();
		sendbuttonpan=new JPanel();
		connectionpan=new JPanel();
		
		msg=new JTextField(31);
		send=new JButton("Send");
		connect=new JButton("Connect");
		disconnect=new JButton("Disconnect");
		msgpanel.setSize(455, 330);
		msgpanel.setLocation(0, 0);
		msgpanel.setBackground(bg);
		
		messages.setEditable(false);
		messages.setSize(450, 340);
		messages.setLineWrap(true);
		messages.setLocation(5, 7);
		
		userspanel.setSize(210, 330);
		userspanel.setLocation(475, 0);
		userspanel.setBackground(bg);

		users.setEditable(false);
		users.setSize(205, 340);
		users.setLineWrap(true);
		users.setLocation(480, 7);
		
		sendpanel.setSize(350, 30);
		sendpanel.setLocation(0, 380);
		sendpanel.setBackground(bg);
		
		sendbuttonpan.setLocation(360, 375);
		sendbuttonpan.setSize(67, 33);
		
		connectionpan.setLocation(475, 375);
		connectionpan.setSize(100,80);
		
		userspanel.add(users);
		msgpanel.add(messages);
		sendpanel.add(msg);
		sendbuttonpan.add(send);
		connectionpan.add(connect);
		connectionpan.add(disconnect);
		
		add(sendpanel);
		add(msgpanel);
		add(userspanel);
		add(sendbuttonpan);
		add(connectionpan);
		
		
		Thehandler handler=new Thehandler();
		connect.addActionListener(handler);
		disconnect.addActionListener(handler);
		send.addActionListener(handler);
	}
	
	private class Thehandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			if(event.getSource()==connect && cl.state!=true)
			{
				cl.connect();
				Thread t=new Thread(cl);
				t.start();
			}
			else if(event.getSource()==disconnect && cl.state==true){
				cl.disconnect();
				users.setText("");
			}
			else if (event.getSource()==send){
				cl.Send(msg.getText());
			}
		}
		
	}

	@Override
	public  void run() {
		while (true){
			if(cl.userschanged){
				users.setText(cl.users);
				cl.userschanged=false;		
			}
			if(cl.messageschanged){
				String str=cl.messages+"\r\n";
				messages.setText(str);
				cl.messageschanged=false;
					}
		}
		
	}

	

}

