import java.awt.Color;
import java.awt.Graphics;

public class ScannerBackPannel extends Scanner{

	private int wX;
	private int y1;
	private int y2;
	private int y3;
	private int wHeight;
	private int wIncrement;
	private int wWidth;
	ScannerBackPannel(MailCenter mail) {
		super(mail);
		wHeight = -120;
		wWidth = super.getIncrement();
		wIncrement = (int) (getWWidth() / (Math.sqrt(2)));
		wX = super.getX() + super.getWidth();
		y1 = super.getY() + (super.getHeight()/6 + 30);
		y2 = 160 + (super.getHeight()/2);
		y3 = super.getY() + ((super.getHeight() * 5)/6 + 30);
	}

	@Override
	public void paint(Graphics g) {
		int[] xSide = {(getX() + getWidth()), (getX() + getIncrement() + getWidth()), (getX() + getIncrement() + getWidth()), (getX() + getWidth())};
		int[] ySide = {getY(), (getY() - getIncrement()),(getY() - getIncrement() + getHeight()), (getY() + getHeight()) };
		int[] WX = {getWX(), getWX() + getWWidth(), getWX() + getWWidth(), getWX()};
		int[] WY1 = {getY1() + getWHeight(), getY1() + getWHeight() - getWIncrement(), getY1() - getWIncrement(), getY1()};
		int[] WY2 = {getY2() + getWHeight(), getY2() + getWHeight() - getWIncrement(), getY2() - getWIncrement(), getY2()};
		int[] WY3 = {getY3() + getWHeight(), getY3() + getWHeight() - getWIncrement(), getY3() - getWIncrement(), getY3()};
	
		g.setColor(getColor().darker());
		g.fillPolygon(xSide, ySide, xSide.length);
		g.setColor(Color.BLACK);
		g.drawPolygon(xSide, ySide, xSide.length);
		g.fillPolygon(WX, WY1, WX.length);
		g.fillPolygon(WX, WY2, WX.length);
		g.fillPolygon(WX, WY3, WX.length);

	}

	public int getWHeight() {
		return wHeight;
	}

	public void setWHeight(int wHeight) {
		this.wHeight = wHeight;
	}

	public int getWIncrement() {
		return wIncrement;
	}

	public void setWIncrement(int wIncrement) {
		this.wIncrement = wIncrement;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getY3() {
		return y3;
	}

	public void setY3(int y3) {
		this.y3 = y3;
	}

	public int getWX() {
		return wX;
	}

	public void setWX(int wX) {
		this.wX = wX;
	}

	public int getWWidth() {
		return wWidth;
	}

	public void setWWidth(int wWidth) {
		this.wWidth = wWidth;
	}
}
