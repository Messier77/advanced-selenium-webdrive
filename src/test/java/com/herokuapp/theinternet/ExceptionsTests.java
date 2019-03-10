package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExceptionsTests {
	WebDriver driver;

	@BeforeMethod
	private WebDriver setUp() {
		System.out.println("Creating driver");
		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		return driver;
	}

	@Test
	public void notVisibleTest() {
		// Open the page
		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);
		System.out.println("Page is opened");

		WebElement startButton = driver.findElement(By.xpath("//button"));
		startButton.click();

		WebElement finishText = driver.findElement(By.id("finish"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(finishText));

		Assert.assertTrue(finishText.getText().equals("Hello World!"), "Hello world! text is not present on page");
	}

	@Test
	public void timeoutTest() {
		// Open the page
		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);
		System.out.println("Page is opened");

		WebElement startButton = driver.findElement(By.xpath("//button"));
		startButton.click();

		WebElement finishText = driver.findElement(By.id("finish"));
		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {
			wait.until(ExpectedConditions.visibilityOf(finishText));
		} catch (TimeoutException e) {
			// Steps after exception
			System.out.println("Exception catched: " + e.getMessage());
			sleep(3000);
		}

		Assert.assertTrue(finishText.getText().equals("Hello World!"), "Hello world! text is not present on page");
	}

	@Test
	public void noSuchElementTest() {
		// Open the page
		String url = "http://the-internet.herokuapp.com/dynamic_loading/2";
		driver.get(url);
		System.out.println("Page is opened");

		WebElement startButton = driver.findElement(By.xpath("//button"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		Assert.assertTrue(
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"), "Hello World!")),
				"Failed to wait for text to be present in element");

		// WebElement finishText = driver.findElement(By.id("finish"));
		// Assert.assertTrue(finishText.getText().equals("Hello World!"), "Hello World!
		// text is not present on page");
	}

	@Test
	public void staleElementTest() {
		// Open the page
		String url = "http://the-internet.herokuapp.com/dynamic_controls";
		driver.get(url);
		System.out.println("Page is opened");

		WebElement button = driver.findElement(By.xpath("//button"));
		WebElement checkbox = driver.findElement(By.id("checkbox"));

		WebDriverWait wait = new WebDriverWait(driver, 10);

		button.click();
		Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkbox)), "Checkbox is still displayed, but it shouldn't be");

		// WebElement loading = driver.findElement(By.id("loading"));
		// wait.until(ExpectedConditions.invisibilityOf(loading));
		
		// Assert.assertFalse(checkbox.isDisplayed(), "Checkbox is displayed, but it shouldn't be");
	}

	@AfterMethod
	private void tearDown() {
		System.out.println("Closing driver");
		// Closing driver
		driver.quit();
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
