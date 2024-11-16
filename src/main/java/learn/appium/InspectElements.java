package learn.appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

public class InspectElements {
	
	@Test 
	public void loginPageTest() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		options.setDeviceName("Pixel 8a API 34");
		options.setApp(System.getProperty("user.dir")+"/apps/DemoApp.apk");
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(e->e.findElement(By.xpath("//*[@content-desc='open menu']")));
		driver.findElement(AppiumBy.xpath("//*[@content-desc='open menu']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Log In']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(e->e.findElement(By.xpath("//*[@content-desc='Username input field']")));
		driver.findElement(By.xpath("//*[@content-desc='Username input field']")).sendKeys("bob@example.com");
		driver.findElement(By.xpath("//*[@content-desc='Password input field']")).sendKeys("10203040");
		driver.findElement(AppiumBy.accessibilityId("Login button")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(e->e.findElement(By.xpath("//*[@text='Sauce Labs Bike Light']")));
		Assert.assertTrue("Login is unsuccessful",driver.findElement(By.xpath("//*[@text='Sauce Labs Bike Light']")).isDisplayed());
		driver.quit();
	}

}
