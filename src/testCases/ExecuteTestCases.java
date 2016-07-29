/*
 * Test execution of site linking in, visitors and related sites are run in this class. 
 * It uses Hybrid Frame work. 
 * The feed is obtained from TestCase.xlsx. 
 * 
 */

package testCases;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

import AlexaUIOperation.ReadObject;
import AlexaUIOperation.SiteLinkingIn;
import AlexaUIOperation.SiteRelatedLinks;
import AlexaUIOperation.SiteVisitors;
import AlexaExcelFileIO.ReadExcelFile;

public class ExecuteTestCases {

	WebDriver webdriver = null;
    
	@BeforeTest
	public void testBeforeTest(){
    
		webdriver= new FirefoxDriver();		
	     
	}
	
	@AfterTest
	public void tearDown(){
		
        webdriver.quit();
    }
	 
	@Test(dataProvider="testData")
	public void testLinkingInValue(String testcaseName,String keyword,String objectName,String objectType,String value) throws Exception {
    	String getTxtValue=null; String flag="false";
		
		
		ReadObject object= new ReadObject();
		 Properties allObjects =  object.getObjectRepository();
		 SiteLinkingIn opUI= new SiteLinkingIn(webdriver);
		
		getTxtValue= opUI.perform(allObjects,keyword, objectName,value);
		if(getTxtValue!=null){
		  
	      String tmpVal=getTxtValue.replaceAll(",", "");
	      
	     if(Integer.parseInt(tmpVal)>100000){
		    flag= "true";
	     }else{
		    flag="false";
	     }
	  try{
	     Assert.assertEquals(flag, "true" ,"Sites linking in is larger than 100,000");
	  
	  }catch(Exception e){
		  System.out.print(e.getMessage());
	  }
	}
	   
		
		
   }

   @Test(dataProvider="visitor")
   public void testVisitorPercentage(String testcaseName,String keyword,String objectName,String objectType,String value) throws Exception {
	   
	   List<WebElement> getwebelement,gettablecollection;
	   String country=" Australia";
	   @SuppressWarnings("unused")
	   int row_num=1;
	   String flag="false";
	   
	  
		
		ReadObject object= new ReadObject();
	    Properties allObjects =  object.getObjectRepository();
		 
		 SiteVisitors siteVisit= new SiteVisitors(webdriver);
		 getwebelement=  siteVisit.perform(allObjects,keyword, objectName,value);
		
		 for(WebElement w_ele : getwebelement){
			 
			 gettablecollection=w_ele.findElements(By.xpath("td"));
			
			 
			 
			 String countryName = gettablecollection.get(0).getText();
			
			 if(countryName.equals(country)){
				 String percentage = gettablecollection.get(1).getText();
				 
				 String tmp= percentage.replace("%","");
				 if(Double.parseDouble(tmp)>=5){
					 flag="true";
				 }else{
					 flag="false";
				 }
			 }
			 			 
			 try{
			     Assert.assertEquals(flag, "true" ,"Site Visitor has Australia and the percentage is more than 5%");
			     break;
			  }catch(Exception e){
				  System.out.print(e.getMessage());
			  }
			 row_num++;
		 }	 
		 
    }
   
    @Test(dataProvider="relatedlink")
    public void testRelatedLinks(String testcaseName,String keyword,String objectName,String objectType,String value) throws Exception {
	   
	   List<WebElement> trElement;
	   int tot_size=0;
	   String flag="false";
	   
	   ReadObject object= new ReadObject();
	   Properties allObjects =  object.getObjectRepository();
		 
		 SiteRelatedLinks relatedlink= new SiteRelatedLinks(webdriver);
		 
		 trElement= relatedlink.perform(allObjects,keyword, objectName,value);
		 tot_size=trElement.size();
		 
		 
		 if(tot_size==10){
			 flag="true";
		 }else{
			 flag="false";
		 }
		 try{
			 
		     Assert.assertEquals(flag, "true" ," There are 10 related links in Related links ");
		     
		  }catch(Exception e){
			  System.out.print(e.getMessage());
		  }
   }
   
    
   @DataProvider(name="testData")
   public Object[][] getDataFromDataprovider() throws IOException{
   	Object[][] object = null;
   	ReadExcelFile file = new ReadExcelFile();
       
   	   
        //Read keyword sheet
    Sheet alexaSheet = file.readExcel(System.getProperty("user.dir")+"//","TestCase.xlsx" , "sitelinkingTest");
        
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

   
   @DataProvider(name="visitor")
   public Object[][] getFromDataprovider() throws IOException{
   	Object[][] object = null;
   	ReadExcelFile file = new ReadExcelFile();
       
   	   
        //Read keyword sheet
    Sheet alexaSheet = file.readExcel(System.getProperty("user.dir")+"//","TestCase.xlsx" , "sitevisitorTest");
        
      //Find number of rows in excel file
   	int rowCount = alexaSheet.getLastRowNum()-alexaSheet.getFirstRowNum();
   	
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
   
   @DataProvider(name="relatedlink")
   public Object[][] getFrmDataprovider() throws IOException{
   	Object[][] object = null;
   	ReadExcelFile file = new ReadExcelFile();
       
   	   
        //Read keyword sheet
    Sheet alexaSheet = file.readExcel(System.getProperty("user.dir")+"//","TestCase.xlsx" , "relatedlinksTest");
        
      //Find number of rows in excel file
   	int rowCount = alexaSheet.getLastRowNum()-alexaSheet.getFirstRowNum();
   	System.out.println("visitorrow:"+rowCount);
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
