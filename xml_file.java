import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
public class xml_file 
{
	public void convert(game o)
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream (new File("./game.xml"));
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(o);
			encoder.close();
			fos.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}

