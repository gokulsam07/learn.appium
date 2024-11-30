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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

public class DragAndDrop {
	@Test
	public void dragAndDropTest() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		options.setDeviceName("Pixel 8a API 34");
		options.setApp(System.getProperty("user.dir")+"/apps/ApiDemos.apk");
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		tap(driver,driver.findElement(AppiumBy.xpath("//*[@text='Views']")));
		tap(driver,driver.findElement(By.xpath("//*[@text='Drag and Drop']")));
		
		WebElement src = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
		WebElement target = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_2"));
		//dragAndDrop(driver,src,target);
		simpleDD(driver,src,target);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Dropped!']")).isDisplayed());
		driver.quit();
	}
	
	private void dragAndDrop(AndroidDriver driver,WebElement src,WebElement target) {
		Point srcCentre = getCentreOfElement(src.getLocation(), src.getSize());
		Point targetCentre = getCentreOfElement(target.getLocation(), target.getSize());
		PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
		Sequence Seq = new Sequence(finger1,1)
		//move finger to the element
		.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), srcCentre))
		// to tap this action should be used
		.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		//pause for few sec to simulate tap
		.addAction(new Pause(finger1,Duration.ofMillis(500)))
		//drag and drop to 
		.addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), targetCentre))
		//release tap action
		.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singletonList(Seq));
	}
	
	private void simpleDD(AndroidDriver driver,WebElement src,WebElement target) {
		new Actions(driver).clickAndHold(src).pause(500).moveToElement(target).pause(500).release(target).build().perform();
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
