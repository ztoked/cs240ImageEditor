
public class Pixel 
{
	private int red;
	private int green;
	private int blue;
	
	public Pixel() 
	{
		red = 0;
		green = 0;
		blue = 0;
	}

	public Pixel(int red, int green, int blue) 
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() 
	{
		return red;
	}

	public void setRed(int value) 
	{
		red = value;
	}

	public int getGreen() 
	{
		return green;
	}

	public void setGreen(int value) 
	{
		green = value;
	}
	
	public int getBlue() 
	{
		return blue;
	}

	public void setBlue(int value) 
	{
		blue = value;
	}

	public String toString() 
	{
		return String.format("%d\n%d\n%d\n", red, green, blue);
	}
	
	public void invert()
	{
		red = 255 - red;
		green = 255 - green;
		blue = 255 - blue;
	}
	
	public void grayscale()
	{
		int tempRed = red;
		int tempGreen = green;
		int tempBlue = blue;
		red = (tempRed+tempGreen+tempBlue)/3;
		green = (tempRed+tempGreen+tempBlue)/3;
		blue = (tempRed+tempGreen+tempBlue)/3;
	}
}
