/*
 * The "site visitor of this side" UI operation is done in this class. This gets the cssSelector information from the xlsx file.
 */

package AlexaUIOperation;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SiteVisitors {
	
	WebDriver driver;
	
	public SiteVisitors(WebDriver driver){
		this.driver=driver;
	}
	
public List<WebElement> perform(Properties p,String operation,String objectName,String value) throws Exception{
		
	    WebElement txtValue = null; 
	    List<WebElement> table_collections = null;
		switch (operation.toUpperCase()) {
		case "GOTOURL":
			//Get url of application
			driver.get(p.getProperty(value));
			break;
		case "GETTEXT":
			//Get text of an element
			 
			 txtValue =driver.findElement(By.cssSelector(objectName));
			 table_collections= txtValue.findElements(By.xpath("//*[@id='demographics_div_country_table']/tbody/tr"));
			 break;

		default:
			break;
		}
		
		
		return table_collections;
	}


}
