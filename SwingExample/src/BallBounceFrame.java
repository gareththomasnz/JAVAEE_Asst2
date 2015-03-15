import javax.swing.JFrame;

class BallBounceFrame extends JFrame
{
	public BallBounceFrame() {
		super ("Bouncing Balls");
		setSize (600, 200);

		BallBouncePanel panel = new BallBouncePanel();
		getContentPane().add(panel);
		
		setVisible(true);
	}

	public static void main (String[] args) {
		BallBounceFrame s = new BallBounceFrame();
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}
