package com.ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TvPurchase {
	static  WebDriver driver;
	   @BeforeClass
		public static void OpenBrowser() {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\jeyapandi\\eclipse-workspace\\Flipkart-Junit\\driver\\chromedriver.exe");
	         driver= new ChromeDriver();
	        driver.get("https://www.flipkart.com/");
	        driver.manage().window().maximize();
	        		}
	   @AfterClass
		public static void CloseBrowser() {
	      driver.quit();
                   }
	   static  long  starttime;
	   @Before
	    public  void BeforeTest() {
		long starttime =System.currentTimeMillis();
	    }
	   @After
	    public  void AfterTest() {
	       long endstime= System.currentTimeMillis();
	       System.out.println(endstime-starttime);
	    }
	   @Test
	   public  void m1() {
		   driver.findElement(By.xpath("//button[text()='✕']")).click();
	   }
	   @Test
	   public  void m2() {
		   driver.findElement(By.name("q")).sendKeys("Mi Tv");
		   driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();
	  } 
	   @Test
	   public  void m3() throws IOException {
		   driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		WebElement TVselect = driver.findElement(By.xpath("(//div[@class='_4rR01T'])[1]"));
		String TVname=TVselect.getText();
		TVselect.click();
	    File f= new File("C:\\Users\\jeyapandi\\Desktop\\TvName.xlsx");
	    FileInputStream f1=new FileInputStream(f);
	    XSSFWorkbook book=new XSSFWorkbook(f1);
	    XSSFSheet sheet=  book.getSheet("Name");
	      sheet.getRow(0).createCell(1).setCellValue(TVname);
	      FileOutputStream w=new FileOutputStream(f);
	      book.write(w);
	      book.close();
	      }
	   @Test
	   public  void m4() throws IOException {
		   Set<String> Allwin=  driver.getWindowHandles();
			List<String> win= new ArrayList<String>();
			win.addAll(Allwin);
			String window =win.get(1);
			driver.switchTo().window(window);
			String tv=driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
            File f= new File("C:\\Users\\jeyapandi\\eclipse-workspace\\Flipkart-Junit\\src\\test\\resources\\Files\\TvName.xlsx");
		     FileInputStream f1=new FileInputStream(f);
			 XSSFWorkbook book=new XSSFWorkbook(f1);
			 XSSFSheet sheet=  book.getSheet("Name");
		  String data=   sheet.getRow(0).getCell(1).getStringCellValue();
		  book.close();
		  Assert.assertEquals(tv, data);
	     }
	   @Test
	   public void m5() throws IOException {
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source =ts .getScreenshotAs(OutputType.FILE);
		File target=new File("C:\\Users\\jeyapandi\\eclipse-workspace\\Flipkart-Junit\\Screenshot\\tv.png");
		FileUtils.copyFile(source, target);
	    WebElement highlights=	driver.findElement(By.xpath("//div[@class='_2taUHM']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
        js .executeScript("arguments[0].scrollIntoView(true)", highlights );
        File source1=ts.getScreenshotAs(OutputType.FILE);
        File target1=new File("C:\\Users\\jeyapandi\\eclipse-workspace\\Flipkart-Junit\\Screenshot\\tvHighlights.png");
        FileUtils.copyFile(source1,target1);
	}
}