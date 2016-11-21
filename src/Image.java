import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Image 
{
	private int width;
	private int height;
	private Pixel[][] matrix = null;
	
	public Image()
	{
		width = 0;
		height = 0;
	}
	
	public void readImage(String args[]) throws FileNotFoundException
	{
		String fileName = args[0];
		File newFile = new File(fileName);
		FileReader read = new FileReader(newFile);
		Scanner scan = new Scanner(read);
		scan.useDelimiter("(\\s+)(#[^\\n]*\\n)?(\\s*)|(#[^\\n]*\\n)(\\s*)");
		scan.next();
		width = Integer.parseInt(scan.next());
		System.out.println(width);
		height = Integer.parseInt(scan.next());
		System.out.println(height);
		matrix = new Pixel[height][width];
		scan.next();
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				matrix[i][j] = new Pixel(Integer.parseInt(scan.next()),Integer.parseInt(scan.next()),Integer.parseInt(scan.next()));
			}
		}
		scan.close();
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void invert()
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				matrix[i][j].invert();
			}
		}
	}
	
	public void grayscale()
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				matrix[i][j].grayscale();
			}
		}
	}
	
	public void emboss()
	{
		Pixel[][] newMatrix = new Pixel[height][width];
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				if((i == 0)||(j == 0))
				{
					newMatrix[i][j] = new Pixel();
					newMatrix[i][j].setRed(128);
					newMatrix[i][j].setGreen(128);
					newMatrix[i][j].setBlue(128);
				}
				else
				{
					int maxDiff = 0;
					int redDiff = matrix[i][j].getRed() - matrix[i-1][j-1].getRed();
					int greenDiff = matrix[i][j].getGreen() - matrix[i-1][j-1].getGreen();
					int blueDiff  = matrix[i][j].getBlue() - matrix[i-1][j-1].getBlue();
					if((Math.abs(redDiff) >= Math.abs(greenDiff))&&(Math.abs(redDiff) >= Math.abs(blueDiff)))
					{
						maxDiff = redDiff;
					}
					else if(Math.abs(greenDiff) >= Math.abs(blueDiff))
					{
						maxDiff = greenDiff;
					}
					else
					{
						maxDiff = blueDiff;
					}
					maxDiff = maxDiff + 128;
					if(maxDiff < 0)
					{
						maxDiff = 0;
					}
					if(maxDiff > 255)
					{
						maxDiff = 255;
					}
					newMatrix[i][j] = new Pixel();
					newMatrix[i][j].setRed(maxDiff);
					newMatrix[i][j].setGreen(maxDiff);
					newMatrix[i][j].setBlue(maxDiff);
				}
			}
		}
		matrix = newMatrix;
	}
	
	public void blur(int n)
	{
		Pixel[][] newMatrix = new Pixel[height][width];
		if(n > 0)
		{
			for(int i = 0; i < height; i++)
			{
				for(int j = 0; j < width; j++)
				{
					int partRed = 0;
					int partGreen = 0;
					int partBlue = 0;
					int w = 0;
					if((j+n)>width)
					{
						w = width-j;
						for(int k = 0; k<w; k++)
						{
							partRed+=matrix[i][j+k].getRed();
							partGreen+=matrix[i][j+k].getGreen();
							partBlue+=matrix[i][j+k].getBlue();
						}
						partRed = partRed/w;
						partGreen = partGreen/w;
						partBlue = partBlue/w;
						newMatrix[i][j] = new Pixel(partRed,partGreen,partBlue);
					}
					else
					{
						for(int k = 0; k<n; k++)
						{
							partRed+=matrix[i][j+k].getRed();
							partGreen+=matrix[i][j+k].getGreen();
							partBlue+=matrix[i][j+k].getBlue();
						}
						partRed = partRed/n;
						partGreen = partGreen/n;
						partBlue = partBlue/n;
						newMatrix[i][j] = new Pixel(partRed,partGreen,partBlue);
					}
				}
			}
			matrix = newMatrix;
		}
	}
	
	public void write(String args[]) throws FileNotFoundException
	{
		StringBuilder str = new StringBuilder();
		str.append("P3\n");
		str.append(Integer.toString(width));
		str.append(" ");
		str.append(Integer.toString(height));
		str.append("\n");
		str.append("255\n");
		for(int i =0; i<height; i++)
		{
			for(int j = 0; j<width; j++)
			{
				str.append(matrix[i][j].getRed());
				str.append("\n");
				str.append(matrix[i][j].getGreen());
				str.append("\n");
				str.append(matrix[i][j].getBlue());
				str.append("\n");
			}
		}
		File out = new File(args[1]);
		PrintWriter printer = new PrintWriter(out);
		printer.write(str.toString());
		printer.close();
	}
}
