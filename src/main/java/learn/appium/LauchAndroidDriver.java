package learn.appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

public class LauchAndroidDriver {

	@Test
	public void launchAndroidDriverUsingRealDevice() throws MalformedURLException, InterruptedException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		options.setDeviceName("RZCX10JAS5D");
		options.setApp(System.getProperty("user.dir")+"/apps/DemoApp.apk");
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		driver.findElement(By.xpath("//*[@text='Sauce Labs Bike Light']")).click();
		Thread.sleep(5000);
		driver.quit();
	}
	@Test
	public void launchAndroidDriverUsingEmulator() throws MalformedURLException, InterruptedException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		options.setDeviceName("Pixel 8a API 34");
		options.setApp(System.getProperty("user.dir")+"/apps/DemoApp.apk");
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		driver.findElement(By.xpath("//*[@text='Sauce Labs Bike Light']")).click();
		Thread.sleep(5000);
		driver.quit();
	}

}
