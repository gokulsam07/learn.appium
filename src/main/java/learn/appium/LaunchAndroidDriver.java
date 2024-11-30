package learn.appium;

import java.io.IOException;
import java.net.URL;

import org.testng.annotations.Test;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

public class LaunchAndroidDriver {

	@Test
	public void launchAndroidDriverUsingEmulator() throws InterruptedException, IOException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		//options.setDeviceName("Pixel 8a API 34");
		options.setUdid("emulator-5554");
		options.setApp(System.getProperty("user.dir")+"/apps/DemoApp.apk");
		AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:2382"),options);
		driver.findElement(By.xpath("//*[@text='Sauce Labs Bike Light']")).click();
		driver.quit();
	}


	@Test
	public void launchAndroidDriverUsingRealDevice() throws InterruptedException, IOException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		options.setDeviceName("RZCX10JAS5D");
		options.setApp(System.getProperty("user.dir")+"/apps/DemoApp.apk");
		AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:2381"),options);
		driver.findElement(By.xpath("//*[@text='Sauce Labs Bike Light']")).click();
		driver.quit();
		
	}

}
