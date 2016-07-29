/*
 * This class is created to read all the properties that is used in the test case. For now i have the url but anything 
 * to be add/updates can be done on objects.properties and this class will read and store in a file.
 */

package AlexaUIOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {
	
	Properties p = new Properties();
	 
	public Properties getObjectRepository() throws IOException{
		//Read object repository file
		InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"//src//objects//object.properties"));
		//load all objects
		p.load(stream);
		 return p;
	}

}
