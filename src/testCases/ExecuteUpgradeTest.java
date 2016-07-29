/*
 * Test execution of Upgrade/upsell  are run in this class. This test will navigate to plan page.
 * It gets the list of all upgrade/upsell by classname to a list and then run each of them see if it navigates to plan page.
 * if not it will fail the test. 
 * It uses Hybrid Frame work. 
 * The feed is obtained from UpgradeCase.xlsx. 
 * 
 */

package testCases;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import AlexaExcelFileIO.ReadExcelFile;
import AlexaUIOperation.ReadObject;
import AlexaUIOperation.Siteupgrade;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExecuteUpgradeTest {

	WebDriver webdriver = null;
	private static String[] links = null;
	
	@AfterTest
	public void tearDown(){
        webdriver.quit();
    }
	
	
	@Test(dataProvider="upgrade")
	public void testUpgradeTest(String testcaseName,String keyword,String objectName,String objectType,String value) throws Exception{
		
		if(testcaseName!=null&&testcaseName.length()!=0){
	    	   webdriver=new FirefoxDriver();
	    }
		
	        ReadObject object = new ReadObject();
	        Properties allObjects =  object.getObjectRepository();
	        Siteupgrade operation = new Siteupgrade(webdriver);
	      	
	        //Call perform function to perform operation on UI
	    	operation.perform(allObjects,keyword, objectName,value);
	    	
	    	//read className for all the upgrade/upsell 
	    	List<WebElement> elements = webdriver.findElements(By.className("upsell-block-lock"));
		    
		    
		    links=new String[elements.size()];
		    
		    for(int i=0;i<elements.size();i++){
		    	
		    	links[i]=elements.get(i).getAttribute("href");
		    	
		    	
		    }
		    
		    for(int i=0;i<elements.size();i++){
		    	if(links[i]==null){
		    		continue;
		    	}else{
		    	
		    	String linkVal=links[i].toString();
		    	
		    	webdriver.navigate().to(links[i]);
		    	
		    	Assert.assertEquals(linkVal, webdriver.getCurrentUrl());
		    	
		    	webdriver.navigate().back();
		    	}
		    }
	}
	
	@DataProvider(name="upgrade")
	   public Object[][] getDataFromDataprovider() throws IOException{
	   	Object[][] object = null;
	   	ReadExcelFile file = new ReadExcelFile();
	       
	   	   
	        //Read keyword sheet
	    Sheet alexaSheet = file.readExcel(System.getProperty("user.dir")+"//","UpgradeCase.xlsx" , "upgradeTest");
	        
	      //Find number of rows in excel file
	   	int rowCount = alexaSheet.getLastRowNum()-alexaSheet.getFirstRowNum();
	   	//System.out.println("row:"+rowCount);
	   	object = new Object[rowCount][5];
	   	for (int i = 0; i < rowCount; i++) {
	   		//Loop over all the rows
	   		Row row = alexaSheet.getRow(i+1);
	   		//Create a loop to print cell values in a row
	   		for (int j = 0; j < row.getLastCellNum(); j++) {
	   			//Print excel data in console
	   			object[i][j] = row.getCell(j).toString();
	   		}
	        
	   	}
	    	System.out.println("");
	    	  return object;	 
	  }

}
