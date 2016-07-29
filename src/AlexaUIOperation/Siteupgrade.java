/*
 * The "site upgrade" UI operation is done in this class, where it reads the site URL and the rest is done in ExecuteUpgradeTest.
 *  This gets the cssSelector information from the xlsx file.
 */

package AlexaUIOperation;

import java.util.Properties;

import org.openqa.selenium.WebDriver;



public class Siteupgrade {

    WebDriver driver;
	
	public Siteupgrade(WebDriver driver){
		this.driver=driver;
	}
	
	public void perform(Properties p,String operation,String objectName,String value) throws Exception{
		
		
		if(operation.toUpperCase().equals("GOTOURL")){
			driver.get(p.getProperty(value));
		}
		
				
	}
	
}
