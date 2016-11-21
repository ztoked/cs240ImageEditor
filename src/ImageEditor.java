import java.io.FileNotFoundException;

public class ImageEditor 
{
	public static void main(String args[]) throws FileNotFoundException
	{
		Image newImage = new Image();
		newImage.readImage(args);
		if(args[2].equals("invert"))
		{
			System.out.println("invert");
			newImage.invert();
		}
		
		if(args[2].equals("grayscale"))
		{
			System.out.println("grayscale");
			newImage.grayscale();
		}
		
		if(args[2].equals("emboss"))
		{
			System.out.println("emboss");
			newImage.emboss();
		}
		
		if(args[2].equals("motionblur"))
		{
			System.out.println("motion blur");
			newImage.blur(Integer.parseInt(args[3]));
		}
		newImage.write(args);
	}

}