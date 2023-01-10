import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class Parcel {

	private int x = 200;
	private int y = 300;
	private int width;
	private int length;
	private int height;
	private int increment;
	private int xa;
	private static int min;
	private static int max;
	private static int min2;
	private Color parcelColor;
	private MailCenter mail;
	Random random = new Random();
	private boolean space;
	private String yPos;

	public Parcel (MailCenter mail, int x) {
		this.x = x;
		this.mail = mail;
		this.xa= 1;
		this.y = mail.getHeight() + 120 + (450/2);
		parcelColor = randomColor();
		min = 20;
		max = 50;
		min2 = 10;
		height = (-1) * (random.nextInt(max-min) + min);
		width = random.nextInt(max-min2) + min2;
		length = random.nextInt(max-min) + min;
		increment = (int) (getLength() / (Math.sqrt(2)));
		space = false;
	}

	public void keyPressed(KeyEvent e) {
		//This checks to see which key was pressed, and then sets the appropriate
		//Boolean to true
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (isSpace() == false) {
				setSpace(true);
			} else {
				setSpace(false);
			}
		}
	}
	
	//accessors and mutators
	public void setXA(int xa) {
		this.xa = xa;
	}

	public int getXA() {
		return xa;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
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


	public Color getParcelColor() {
		return parcelColor;
	}

	public MailCenter getMail() {
		return mail;
	}

	private Color randomColor() {
		int randomize = (int) Math.floor(Math.random() * (3));

		if (randomize < 1) {
			return new Color(173,216,230);
		} else if (randomize >= 1 && randomize < 2) {
			return Color.green;
		} else {
			return Color.yellow;
		}
	}

	public void move() {
		setX(getX()+getXA());
	}
	
	public void sort(Scanner scan) {
		if (getX() + getXA() > scan.getX() && getX() + getXA() < scan.getX() + scan.getWidth()){

			if(getParcelColor().equals(new Color(173,216,230))) {
				setY(scan.getY() + (scan.getHeight()/6));
				scan.setImg("intl");
				setYPos("blue");
				
			} else if (getParcelColor().equals(Color.yellow)) {
				setY(scan.getY() + ((scan.getHeight() * 5)/6));
				scan.setImg("unknown");
				setYPos("yellow");
        
      } else {
				scan.setImg("dom");
				setYPos("green");
			}
			scan.setScanning(true);
		}

		if (getX() > scan.getX() + scan.getWidth() + scan.getIncrement()) {
			scan.setScanning(false);
    }
	}

	public void paint(Graphics g) {
		//create the points for both the top and side polygons
		int[] xPointsTop = {getX(), (getX()+getIncrement()), (getX()+getIncrement()+getWidth()), (getX() + getWidth())};
		int[] yPointsTop = {getY() + getHeight(), getY() + getHeight() - getIncrement(), getY() + getHeight() - getIncrement(), getY() + getHeight() };
		int[] xPointsSide = {(getX() + getWidth()), (getX() + getWidth() + getIncrement()), (getX() + getWidth() + getIncrement()), (getX() + getWidth())};
		int[] yPointsSide = {(getY() + getHeight()),(getY() + getHeight() - getIncrement()), (getY() - getIncrement()), getY()};

		// paints the actual parcel
		g.setColor(getParcelColor());
		g.fillRect(getX(),getY(),getWidth(), getHeight());
		g.setColor(getParcelColor().darker());
		g.fillPolygon(xPointsTop, yPointsTop, 4);
		g.setColor(getParcelColor().darker());
		g.fillPolygon(xPointsSide, yPointsSide, 4);
		g.setColor(Color.BLACK);
		//outlines all the edges		
		g.drawLine(getX(), getY(), (getX() + getWidth()), getY());
		g.drawLine(getX(), getY(), getX(), (getY()+getHeight()));
		g.drawLine(getX(), (getY()+getHeight()), (getX()+ getWidth()), (getY() + getHeight()));
		g.drawPolygon(xPointsTop, yPointsTop, 4);
		g.drawPolygon(xPointsSide, yPointsSide, 4);
	}

	public boolean isSpace() {
		return space;
	}

	public void setSpace(boolean space) {
		this.space = space;
	}
	
	public void setYPos(String str) {
		yPos = str;
	}
	
	public String getYPos() {
		return yPos;
	}
	
	public boolean checkXPos(Scanner scan) {
		if (getX() + getXA() > scan.getX()) {
			return true;
		}
		return false;
	}
}
