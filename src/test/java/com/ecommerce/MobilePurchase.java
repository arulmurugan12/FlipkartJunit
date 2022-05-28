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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;




public class MobilePurchase {
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
   static  long starttime;
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
	   driver.findElement(By.name("q")).sendKeys("realme mobile");
	      driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();

} 
  static XSSFSheet sheet;
   @Test
   public  void m3 () throws Exception{
	   driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
   WebElement name=driver.findElement(By.xpath("(//div[@class='_4rR01T'])[1]"));
   String Mname=name.getText();
   name.click();
   File f= new File("C:\\Users\\jeyapandi\\Desktop\\Book1.xlsx");
   FileInputStream f1= new FileInputStream(f);
   XSSFWorkbook book= new XSSFWorkbook(f1);
   XSSFSheet sheet=book.getSheet("Mobile");
   sheet.getRow(0).createCell(1).setCellValue(Mname);
	FileOutputStream w= new FileOutputStream(f);
	book.write(w);
    w.close();
   book.close();
}
   @Test
   public void m4() throws Exception {
	Set<String> Allwin=  driver.getWindowHandles();
	List<String> win= new ArrayList<String>();
	win.addAll(Allwin);
	String Win =win.get(1);
	driver.switchTo().window(Win);
    WebElement FinalMname=	driver.findElement(By.xpath("//span[@class='B_NuCI']"));
   String mobileName= FinalMname.getText();
   File f= new File("C:\\Users\\jeyapandi\\eclipse-workspace\\Flipkart-Junit\\src\\test\\resources\\Files\\Book1.xlsx");
   FileInputStream f1= new FileInputStream(f);
   XSSFWorkbook book= new XSSFWorkbook(f1);
   XSSFSheet sheet=book.getSheet("Mobile");
  String data=sheet.getRow(0).getCell(1).getStringCellValue();
   book.close();
  Assert.assertEquals(data,mobileName);
}
   @Test
   public  void m5() throws IOException {
	   TakesScreenshot ts=(TakesScreenshot)driver;
   File source=   ts.getScreenshotAs(OutputType.FILE);
   File target= new File("C:\\Users\\jeyapandi\\eclipse-workspace\\Flipkart-Junit\\Screenshot\\realme.png");
   FileUtils.copyFile(source,target);
  WebElement down= driver.findElement(By.xpath("//div[@class='_3a9CI2']"));
   JavascriptExecutor js=(JavascriptExecutor)driver;
    js.executeScript("arguments[0].scrollIntoView(true)",down );
    File source1=   ts.getScreenshotAs(OutputType.FILE);
    File target1 = new File("C:\\Users\\jeyapandi\\eclipse-workspace\\Flipkart-Junit\\Screenshot\\highlights.png");
   FileUtils.copyFile(source1, target1);
}}
