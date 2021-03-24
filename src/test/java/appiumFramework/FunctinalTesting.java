package appiumFramework;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FunctinalTesting extends capability {
	
	AndroidDriver<AndroidElement> driver;
	
	@BeforeTest
	public void bt() throws IOException, InterruptedException{
		if(Runtime.getRuntime().exec("taskkill /f /im node.exe") != null) {
			Thread.sleep(5000);
			System.out.println("task Killed");
			service = startServer();
		}else {
			System.out.println("no task Killed");
		}
		
//		driver = capabilities(DEVICE_NAME, PLATFORM_NAME, APP_PACKAGE, APP_ACTIVITY, CHROMEDRIVER_EXECUTABLE);
//		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
	@Test(enabled=false)
	public void test() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("DIVYA");
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
        driver.findElement(By.xpath("//*[@text='Australia']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
	
	@Test(enabled=false)
	public void test1() throws InterruptedException {
//		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("DIVYA");
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
        driver.findElement(By.xpath("//*[@text='Australia']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String s = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        System.out.println(s);
        Assert.assertEquals(s,"Please enter your name");
	}
	
	@Test(enabled=false)
	public void test2() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("DIVYA");
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
        driver.findElement(By.xpath("//*[@text='Australia']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(4000);
        List<AndroidElement> ele = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
         System.out.println(ele.size());
//         
         driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Air Jordan 9 Retro\").instance(0))"); 
         for(int i=0;i<ele.size();i++) {
        	 String Name = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
        	 if(Name.equals("Air Jordan 9 Retro")) {
        		 driver.findElement(By.id("com.androidsample.generalstore:id/productAddCart")).click();
        	 }
        	 driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        	 String Name1 = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        	 Assert.assertEquals(Name1, "Air Jordan 9 Retro");
         }
         
	}
	
	@Test(enabled=true)
	public void test3() throws InterruptedException, IOException {
//		service = startServer();
		driver = capabilities(DEVICE_NAME, PLATFORM_NAME, APP_PACKAGE, APP_ACTIVITY, CHROMEDRIVER_EXECUTABLE);
		Thread.sleep(5000);
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("DIVYA");
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
        driver.findElement(By.xpath("//*[@text='Australia']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(4000);
//        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
//        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();
        
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        //this is to click on the second add to cart button 
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);
//        Need to validate the product price with the total price
        Double P1 = Double.parseDouble(driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText().substring(1));
        System.out.println(P1);
        
        Double P2 = Double.parseDouble(driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText().substring(1));
        System.out.println(P2);
        
        Double T = Double.parseDouble(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(1));
        System.out.println(T);
        Double TotalAmount = P1 + P2;
        System.out.println(TotalAmount);
        
        Assert.assertEquals(T, TotalAmount);
        
        service.stop();
	}
	
	@AfterTest
	public void at() {
		
	}
}
