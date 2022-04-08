package Lab1;

public class Rectangle {
	
	private int length;
	private int width;

	public Rectangle() {
		length = 1;
		width = 1;
	}
	
	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getArea() {
		int area = length * width;
		
		for (int i = 0; i < 10; i++) {
			System.out.println(area);
		}
		
		return area;
	}
	
	public boolean isSquare() {
		return length == width;
	}

}
