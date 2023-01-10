import javax.swing.*;
import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MailCenter extends JPanel {

	//	Parcel p = new Parcel(this);
	Parcel[] parcels = new Parcel[20];
	Scanner scan = new Scanner(this);
	ScannerBackPannel scanBack = new ScannerBackPannel(this);
	ConveyorBelt belt1 = new ConveyorBelt(-100, 160 + (450/2), true);
	ConveyorBelt belt2 = new ConveyorBelt(scan.getX()+scan.getWidth(), scan.getY() + (scan.getHeight()/6) + 30, false);
	ConveyorBelt belt3 = new ConveyorBelt(scan.getX()+scan.getWidth(), 160 + (scan.getHeight()/2), false);
	ConveyorBelt belt4 = new ConveyorBelt(scan.getX()+scan.getWidth(), scan.getY() + ((scan.getHeight() * 5)/6) + 30, false);
	private static int frameWidth = 1020;


	public MailCenter() {
		for (int i = 0; i< parcels.length; i++) {
			parcels[i] = new Parcel(this, i * -250 - 100);
		}

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				belt1.keyPressed(e);
			}
		}); 
		setFocusable(true);
	}

	private void move() {	
		for (Parcel p:parcels) {
			p.sort(scan);
		}
		for (Parcel p:parcels) {
			if (belt1.isSpace() == false) {
				p.move();
		  } else if(p.checkXPos(scan)) {
				p.move();
			}
			if (p.getX() + p.getWidth() > belt2.getX() &&(p.getYPos().equals("blue"))) {
				belt2.setXA(1);
				belt2.setRotationSpeed(4);
			}
			else if (p.getX() + p.getWidth() > belt3.getX() && p.getYPos().equals("green")) {
				belt3.setXA(1);
				belt3.setRotationSpeed(4);
			}
			else if (p.getX() + p.getWidth() > belt4.getX() && p.getYPos().equals("yellow")) {					
				belt4.setXA(1);
				belt4.setRotationSpeed(4);
			}
			
			if (p.getX() >= getFrameWidth()) {
				belt2.setXA(0);
				belt3.setXA(0);
				belt4.setXA(0);
				belt2.setRotationSpeed(0);
				belt3.setRotationSpeed(0);
				belt4.setRotationSpeed(0);
			}

			if (belt1.isSpace() == true) {
				belt1.setRotationSpeed(0);
			}
		}
		
		belt1.setXA(1);
		belt1.setRotationSpeed(4);
		belt1.move();
		belt2.move();
		belt3.move();
		belt4.move();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		scanBack.paint(g2d);
		belt1.paint(g2d);
		belt2.paint(g2d);
		belt3.paint(g2d);
		belt4.paint(g2d);
		for (Parcel p:parcels) {
			p.paint(g2d);
		}
		scan.paint(g2d);
		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public static void main(String[] args) throws InterruptedException  {

		JFrame frame = new JFrame("Mail Room");
		//frame.add(new CityScape());
		MailCenter m = new MailCenter();
		frame.add(m);
		frame.setSize(1020, 640);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
		while (true) {
			m.move(); 
			m.repaint(); 
			Thread.sleep(10); 
		}
	}

	public static int getFrameWidth() {
		return frameWidth;
	}

	public static void setFrameWidth(int frameWidth) {
		MailCenter.frameWidth = frameWidth;
	}

}
