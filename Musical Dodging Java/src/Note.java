import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Note {
	
	public int x;
	public int y;
	public int width;
	public int height;
	private Color color;
	
	
	
	
	public Note(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = new Color(color.getGreen(), color.getBlue(), color.getRed());	
	}
	
	public Color getColor() {
		return color;
	}

	public boolean contains(Point lastPoint) {		
		return (new Rectangle(this.x, this.y, this.width, this.height).contains(lastPoint));	
	}
	
}
