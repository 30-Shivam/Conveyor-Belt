import java.awt.*;
import java.awt.image.BufferedImage; 
import javax.imageio.*;
import java.io.*;

public class Scanner {
	private int x;
	private int y;
	private int width;
	private int height;
	private int length;
	private int increment;
	private MailCenter mail;
	private Color color;
	private BufferedImage img;
	private Color lightColor;
	private boolean isScanning;
	BufferedImage intl = null;
	BufferedImage dom = null; 
	BufferedImage unknown = null;


	public Scanner(MailCenter mail) {

		this.mail = mail;
		this.x = 380;
		this.y = (mail.getHeight() + 120);
		width = 150;
		height = 450;
		length = 90;
		increment = (int) (getLength() / (Math.sqrt(2)));
		color = new Color(150, 150, 150);
		img = null;
		lightColor = new Color(255, 228, 228);
		isScanning = false;
		try {
			intl = ImageIO.read(new File("intl.jpg"));
			dom = ImageIO.read(new File("dom.jpg"));
			unknown = ImageIO.read(new File("unknown.jpg"));
		} 
		catch (IOException e) {
		}
	}

	public BufferedImage getIntl() {	
		return intl;
	}

	public BufferedImage getUnknown() {	
		return unknown;
	}

	public BufferedImage getDom() {	
		return dom;
	}

	public void setImg(String result){

		if (result.equals("intl")) {
			img = getIntl();
		}

		else if (result.equals("unknown")) {
			img = getUnknown();
		}

		else {
			img = getDom();
		}
	}

	public boolean getScanning() {
		return isScanning;
	}

	public void setScanning(boolean sc) {
		isScanning = sc;
	}

	public BufferedImage getImg() {
		return img;
	}

	public int getX() {
		return x;
	}

	public MailCenter getMail() {
		return mail;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLength() {
		return length;
	}

	public int getIncrement() {
		return increment;
	}

	public MailCenter getCenter() {
		return mail;
	}

	public Color getColor() {
		return color;
	}


	public Color getLightColor() {
		return lightColor;
	}

	public void setLightColor(Color c) {
		lightColor = c;
	}

	public void paint(Graphics g) {
		int[] xTop = {getX(), (getX()+getIncrement()), (getX()+getWidth()+getIncrement()), (getX()+getWidth())};
		int[] yTop = {getY(), (getY()-getIncrement()), (getY()-getIncrement()), getY()};

		g.setColor(getColor());
		g.fillRect(getX(),getY(),getWidth(), getHeight());
		g.setColor(getColor().darker());
		g.fillPolygon(xTop, yTop, 4);
		g.setColor(getColor().darker());
		g.setColor(Color.BLACK);
		//outlines all the edges
		g.drawPolygon(xTop, yTop, xTop.length);
		g.drawRect(getX(), getY(), getWidth(), getHeight());

		if (getScanning() == true) {
			setLightColor(Color.RED);
		}
		else {
			setLightColor(new Color(255, 228, 228));
		}
		g.setColor(getLightColor());
		g.fillOval(getX()+5, getY()+5, 30, 30);
		g.setColor(Color.BLACK);
		g.drawOval(getX()+5, getY()+5, 30, 30);

		if (getScanning() == true) {

		}
		g.drawImage(getImg(), 0, 501, 100, 100, null);
	}
}
