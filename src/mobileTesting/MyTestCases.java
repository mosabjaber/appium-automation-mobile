package mobileTesting;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {

	DesiredCapabilities caps = new DesiredCapabilities();

	String AppiumURL = "http://127.0.0.1:4723/wd/hub";

	AndroidDriver driver;

	@BeforeTest
	public void mySetup() {

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "mydevice1");

		File myApplication = new File("src/applications/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myApplication.getAbsolutePath());

	}

	@Test(enabled = false)
	public void addTwoNum() throws MalformedURLException {
		driver = new AndroidDriver(new URL(AppiumURL), caps);
		// inspect id for (4)
		driver.findElement(By.id("com.google.android.calculator:id/digit_4")).click();
		// inspect id for (+)
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		// inspect id for (8)
		driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();

		String actualNum = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		String expectedNum = "12";
		Assert.assertEquals(actualNum, expectedNum);

	}

	@Test(enabled = false)
	public void clickAllBtn() throws MalformedURLException {
		driver = new AndroidDriver(new URL(AppiumURL), caps);

		List<WebElement> allBtn = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < allBtn.size(); i++) {
			allBtn.get(i).click();
		}

	}

	@Test(enabled = false)
	public void clickNumBtn() throws MalformedURLException {
		driver = new AndroidDriver(new URL(AppiumURL), caps);

		List<WebElement> allBtn = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < allBtn.size(); i++) {
			if (allBtn.get(i).getAttribute("resource-id").contains("digit"))
				allBtn.get(i).click();

		}
		String actual = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		String expected = "7894561230";
		Assert.assertEquals(actual, expected);

	}

	@Test()
	public void clickEvenNum() throws MalformedURLException {
		driver = new AndroidDriver(new URL(AppiumURL), caps);

		List<WebElement> allBtn = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < allBtn.size(); i++) {
//			solution 1
//			if (allBtn.get(i).getAttribute("resource-id").contains("8")
//					|| allBtn.get(i).getAttribute("resource-id").contains("6")
//					|| allBtn.get(i).getAttribute("resource-id").contains("4")
//					|| allBtn.get(i).getAttribute("resource-id").contains("2")
//					|| allBtn.get(i).getAttribute("resource-id").contains("0"))
//				
//				allBtn.get(i).click();

//			solution 2
			if (allBtn.get(i).getAttribute("resource-id").contains("digit")) {
				String numStr = allBtn.get(i).getAttribute("resource-id")
						.replace("com.google.android.calculator:id/digit_", "");
				int numInt = Integer.parseInt(numStr);
				if (numInt % 2 == 0)
					allBtn.get(i).click();
			}

		}
//		String actual = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
//		String expected = "7894561230";
//		Assert.assertEquals(actual, expected);

	}
}
