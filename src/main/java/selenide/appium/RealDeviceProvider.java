package selenide.appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.WebDriverProvider;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

public class RealDeviceProvider implements WebDriverProvider {

	@Override
	public WebDriver createDriver(Capabilities capabilities) {
		AppiumDriver driver = null;
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		options.setUdid("emulator-5556");
		options.setApp(System.getProperty("user.dir")+"/apps/DemoApp.apk");
		try {
		 driver = new AppiumDriver(new URL("http://127.0.0.1:4724"),options);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

}
