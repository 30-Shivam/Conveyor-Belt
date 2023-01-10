import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class ConveyorBelt {
	private int x;
	private int y;
	private int width;
	private int height;
	private int length;
	private int increment;
	private Color bottomColor;
	private int lineX1;
	private int lineX2;
	private int lineY1;
	private int lineY2;
	private Color beltColor;
	private boolean space;
	private boolean controlled;
	private int xa;
	private int wheelX;
	private int wheelY;
	private int wheelRadius;
	private int startAngle;
	private int endAngle;
	private int rotationSpeed;

	public ConveyorBelt(int x, int y, boolean ctrl) {

		this.x = x;
		this.y = y;
		height = -20;
		width = 500;
		length = 90; // make length a large number
		increment = (int) (getLength() / (Math.sqrt(2)));
		bottomColor = Color.GRAY.brighter();
		beltColor = new Color(72,72,72); 
		lineX1 = x;
		lineX2 = x + increment;
		lineY1 =y + height;
		lineY2 =y + height - increment;
		xa = 0;
		space = false;
		controlled = ctrl;
		wheelX = getX() + 3;
		wheelY = getY() + getHeight() + 3;
		wheelRadius = 15;
		startAngle = 0;
		endAngle = 359;
	}


	public void keyPressed(KeyEvent e){
		//This checks to see which key was pressed, and then sets the appropriate
		//Boolean to true
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (isSpace() == false) {
				setSpace(true);
			}
			else if (isSpace() == true) {
				setSpace(false);
			}
		}
	}

	public int getXA() {
		return xa;
	}
	
	public void setXA(int i) {
		xa = i;
	}
	
	public int getX() {
		return x;
	}

	public Color getBeltColor() {
		return beltColor;
	}

	public Color getBottomColor() {
		return bottomColor;
	}

	public int getY() {
		return y;
	}

	public int getIncrement() {
		return increment;
	}

	public int getLength() {
		return length;
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getLineX1() {
		return lineX1;
	}
	
	public void setLineX1(int lineX1) {
		this.lineX1 = lineX1;
	}
	
	public int getLineY1() {
		return lineY1;
	}
	
	public void setLineY1(int lineY1) {
		this.lineY1 = lineY1;
	}
	
	public int getLineX2() {
		return lineX2;
	}
	
	public void setLineX2(int lineX2) {
		this.lineX2 = lineX2;
	}
	
	public int getLineY2() {
		return lineY2;
	}
	
	public void setLineY2(int lineY2) {
		this.lineY2 = lineY2;
	}
	
	
	public boolean isSpace() {
		return space;
	}
	
	
	public void setSpace(boolean space) {
		this.space = space;
	}
	
	
	public boolean isControlled() {
		return controlled;
	}
	
	
	public void setControlled(boolean controlled) {
		this.controlled = controlled;
	}
	
	
	public int getWheelX() {
		return wheelX;
	}
	
	
	public void setWheelX(int wheelX) {
		this.wheelX = wheelX;
	}
	
	
	public int getWheelY() {
		return wheelY;
	}
	
	
	public void setWheelY(int wheelY) {
		this.wheelY = wheelY;
	}
	
	
	public int getWheelRadius() {
		return wheelRadius;
	}
	
	
	public void setWheelRadius(int wheelRadius) {
		this.wheelRadius = wheelRadius;
	}
	
	
	public int getStartAngle() {
		return startAngle;
	}
	
	
	public void setStartAngle(int startAngle) {
		this.startAngle = startAngle;
	}
	
	
	public int getEndAngle() {
		return endAngle;
	}
	
	
	public void setEndAngle(int endAngle) {
		this.endAngle = endAngle;
	}
	
	
	public int getRotationSpeed() {
		return rotationSpeed;
	}
	
	
	public void setRotationSpeed(int rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public void paint(Graphics g) {

		int[] xPointsTop = {getX(), (getX()+getIncrement()), (getX()+getIncrement()+getWidth()), (getX() + getWidth())};
		int[] yPointsTop = {getY() + getHeight(), getY() + getHeight() - getIncrement(), getY() + getHeight() - getIncrement(), getY() + getHeight() };

		g.setColor(getBottomColor());
		g.fillRect(getX(),getY(),getWidth(), getHeight());
		g.setColor(getBeltColor());
		g.fillPolygon(xPointsTop, yPointsTop, 4);
		g.setColor(Color.BLACK);
		g.drawLine(getX(), getY(), (getX() + getWidth()), getY());
		g.drawLine(getX(), getY(), getX(), (getY()+getHeight()));
		g.drawLine(getX(), (getY()+getHeight()), (getX()+ getWidth()), (getY() + getHeight()));
		g.drawPolygon(xPointsTop, yPointsTop, 4);

		int increment = 25;
		for (int i = 0; i < getWidth()/increment; i++) {
			if (getLineX1() + increment *i > getX() + getWidth()) {
				setLineX1(getLineX1() - increment);
				setLineX2(getLineX2() - increment);
			}

			g.drawLine(getLineX1() + increment * i, getLineY1(), getLineX2() + increment * i, getLineY2());	
		}
		
		for (int k = 0; k < getWidth() / increment; k++) {
			g.drawOval(getWheelX() + increment * k, getWheelY(), getWheelRadius(), getWheelRadius());
		}
		
		for (int p = 0; p < getWidth() / increment; p++) {
			g.setColor(getBottomColor().brighter());
			g.setColor(Color.BLACK);
			g.fillArc(getWheelX() + increment*p, getWheelY(), getWheelRadius(), getWheelRadius(), getStartAngle() , getEndAngle());
		}
		
		g.setColor(getBottomColor().darker());
		for(int s = 0; s < getWidth() / increment; s++) {
			g.fillArc(getWheelX() + increment*s, getWheelY(), getWheelRadius(), getWheelRadius(), getStartAngle(), getEndAngle() - 180);
			
			if (isSpace() == true) {
				
			}
		}
		if (isControlled() == true) {
			g.setColor(getBeltColor());
			g.fillRect((getX() + getWidth())/2, getY(), 50, 50);
			g.setColor(Color.black);
			g.drawRect((getX() + getWidth())/2, getY(), 50, 50);
			if (isSpace() == false) {
				g.setColor(Color.GREEN);				
			}
			else {
				g.setColor(Color.RED);
			}
			g.fillOval((getX() + getWidth())/2, getY(), 50, 50);
			g.setColor(Color.black);
			g.drawOval((getX() + getWidth())/2, getY(), 50, 50);
		}
	}

	public void move()
	{
		if (isSpace() == true) {
		} else if (isSpace() == false) {
			setLineX1(getLineX1() + getXA());
			setLineX2(getLineX2() + getXA());
			setStartAngle(getStartAngle() - getRotationSpeed());
		}
	}
}
