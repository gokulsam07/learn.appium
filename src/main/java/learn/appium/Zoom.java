package learn.appium;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import org.testng.annotations.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

public class Zoom {

	@Test
	public void testSwipe() throws Exception {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		options.setDeviceName("Pixel 8a API 34");
		options.setApp(System.getProperty("user.dir")+"/apps/DemoApp.apk");
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		driver.findElement(AppiumBy.xpath("//*[@content-desc='open menu']")).click();
		driver.findElement(AppiumBy.xpath("//*[@text='Drawing']")).click();
		WebElement zoomEle = driver.findElement(AppiumBy.xpath("//android.webkit.WebView"));
		zoom(driver, zoomEle);
	}
	
	private Point getCentreOfElement(Point location, Dimension size) {
		return new Point(location.getX()+size.width/2,location.getY()+size.height/2);
	}

	private void zoom(AndroidDriver driver, WebElement ele) {
		Point eleCentre = getCentreOfElement(ele.getLocation(), ele.getSize());
		PointerInput finger1 = new PointerInput(Kind.TOUCH,"finger1");
		PointerInput finger2 = new PointerInput(Kind.TOUCH,"finger2");
		
		Sequence f1 = new Sequence(finger1,1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),eleCentre))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1,Duration.ofMillis(200)))
				.addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), eleCentre.getX(), eleCentre.getY()+100))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		Sequence f2 = new Sequence(finger2,1)
				.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),eleCentre))
				.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger2,Duration.ofMillis(200)))
				.addAction(finger2.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), eleCentre.getX(), eleCentre.getY()-100))
				.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Arrays.asList(f1,f2));
	}
}
