package learn.appium;


import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

public class SwipeOrScroll {

	@Test
	public void testSwipe() throws Exception {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		options.setDeviceName("Pixel 8a API 34");
		options.setApp(System.getProperty("user.dir")+"/apps/ApiDemos.apk");
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		driver.findElement(By.xpath("//*[@text='Views']")).click();
		scrollTillElement(driver,"//*[@text='WebView3']");
		driver.findElement(By.xpath("//*[@text='WebView3']")).click();	
	}



	private void scrollTillElement(AndroidDriver driver , String element) throws Exception {
		int attempts =0;
		int max=5;
		Dimension size=driver.manage().window().getSize();
		int startX= size.getWidth()/2;
		int startY = size.getHeight()/2;
		int endX=startX; 
		int endY=(int) (startY*0.25);
		while(attempts<max) {
			try {
				WebElement ele = driver.findElement(By.xpath(element));
				if (ele.isDisplayed()) {
					return;
				}
			} catch (Exception e) {
				attempts++;
				PointerInput finger1 =  new PointerInput(Kind.MOUSE,"finger1");
				Sequence scroll = new Sequence(finger1,1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX,startY))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1,Duration.ofMillis(200)))
						.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX,endY))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Collections.singletonList(scroll));	
			}
		}
	}
}
