package learn.appium;

import org.openqa.selenium.Point;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

public class GestureTap {
	

	@Test
	public void testTap() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		options.setDeviceName("Pixel 8a API 34");
		options.setApp(System.getProperty("user.dir")+"/apps/DemoApp.apk");
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		tap(driver,driver.findElement(AppiumBy.xpath("//*[@content-desc='open menu']")));
		tap(driver,driver.findElement(By.xpath("//android.widget.TextView[@text='Log In']")));
		driver.findElement(By.xpath("//*[@content-desc='Username input field']")).sendKeys("bob@example.com");
		driver.findElement(By.xpath("//*[@content-desc='Password input field']")).sendKeys("10203040");
		tap(driver,driver.findElement(AppiumBy.accessibilityId("Login button")));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Sauce Labs Bike Light']")).isDisplayed());
		driver.quit();
	}
	
	private void tap(AndroidDriver driver, WebElement element) {
		Point p = element.getLocation();
		Dimension d = element.getSize();
		Point locOfTap = getCentreOfElement(p, d);
		PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
		Sequence tapSeq = new Sequence(finger1,1)
		//move finger to the element
		.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), locOfTap))
		// to tap this action should be used
		.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		//pause for few sec to simulate tap
		.addAction(new Pause(finger1,Duration.ofMillis(200)))
		//release tap action
		.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singletonList(tapSeq));
		
	}
	
	private Point getCentreOfElement(Point location, Dimension size) {
		return new Point(location.getX()+size.width/2,location.getY()+size.height/2);
	}
}
