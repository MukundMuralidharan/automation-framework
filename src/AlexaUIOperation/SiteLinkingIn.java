/*
 * The "site linking in" UI operation is done in this class. This gets the cssSelector information from the xlsx file.
 */
package AlexaUIOperation;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SiteLinkingIn {

	WebDriver driver;
	
	public SiteLinkingIn(WebDriver driver){
		this.driver=driver;
	}
	public String perform(Properties p,String operation,String objectName,String value) throws Exception{
		
		String strVal = null;
		switch (operation.toUpperCase()) {
		case "GOTOURL":
			//Get url of application
			driver.get(p.getProperty(value));
			break;
		case "GETTEXT":
			//Get text of an element
			
			strVal =driver.findElement(By.cssSelector(objectName)).getText();
				
			break;

		default:
			break;
		}
		
		return strVal;
	}
	
	
}
