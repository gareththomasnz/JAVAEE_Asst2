import javax.swing.JFrame;


public class GraphicsTest {
	public static void main(String[] args)
	{
		JFrame mainFrame = new JFrame("Graphics");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mainFrame.pack(); // there are no components to pack()
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);
		
		mainFrame.add(new CustomPanel());
	}
}
