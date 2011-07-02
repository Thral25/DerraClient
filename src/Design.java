
import javax.swing.*;
public class Design {
public static void main(String[] args){
	Gui frame=new Gui();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(715,500);
	frame.setVisible(true);
	Thread t =new Thread(frame);
	t.start();
	
}
}
