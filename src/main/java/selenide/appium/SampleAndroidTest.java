package selenide.appium;


import static com.codeborne.selenide.appium.SelenideAppium.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.appium.AppiumClickOptions.*;
import com.codeborne.selenide.appium.SelenideAppium;
import static io.appium.java_client.AppiumBy.*;

import org.testng.annotations.Test;

public class SampleAndroidTest {
	
	@Test
	public void doSampleTest() throws InterruptedException {
		Configuration.browser = AndroidDriverProvider.class.getName();
		SelenideAppium.launchApp();
		$x("//*[@text='Sauce Labs Bike Light']").click(tap());
		Thread.sleep(5000);
	}

}
