package com.herokuapp.theinternet.loginpagetests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;

public class NegativeLoginTests extends TestUtilities {

	@Parameters({ "username", "password", "expectedErrorMessage" })
	@Test(priority = 1, groups = { "smokeTests", "negativeTests" })
	public void negativeTest(String username, String password, String expectedErrorMessage) {
		log.info("Starting negativeTest");

		// Open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		log.info("Page is opened");

		// Enter wrong Username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);

		// Enter Password
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys(password);

		// Push login button
		WebElement loginButton = driver.findElement(By.className("radius"));
		loginButton.click();

		// Verifications
		// Same URL
		String expectedUrl = "http://the-internet.herokuapp.com/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

		// Login button is still visible
		loginButton = driver.findElement(By.className("radius"));
		Assert.assertTrue(loginButton.isDisplayed(), "The button is not displayed.");

		// Successful error message
		WebElement errorMessageElement = driver.findElement(By.id("flash"));
		String actualErrorMessage = errorMessageElement.getText();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"The message is not correctly displayed.\nexpectedErrorMessage: " + expectedErrorMessage
						+ "\nactualErrorMessage: " + actualErrorMessage);

	}

	/*
	 * @Test(priority = 1, groups = { "smokeTests", "negativeTests" }) public void
	 * invalidUsernameTest() { System.out.println("Starting invalidUsername test");
	 * // Create driver System.setProperty("webdriver.chrome.driver",
	 * "src/main/resources/chromedriver.exe"); WebDriver driver = new
	 * ChromeDriver(); driver.manage().window().maximize();
	 * 
	 * // Open the page String url = "http://the-internet.herokuapp.com/login";
	 * driver.get(url); System.out.println("Page is opened");
	 * 
	 * // Enter wrong Username WebElement username =
	 * driver.findElement(By.id("username")); username.sendKeys("invalidUsername");
	 * 
	 * // Enter Password WebElement password =
	 * driver.findElement(By.id("password"));
	 * password.sendKeys("SuperSecretPassword!");
	 * 
	 * // Push login button WebElement loginButton =
	 * driver.findElement(By.className("radius")); loginButton.click();
	 * 
	 * // Verifications // Same URL String expectedUrl =
	 * "http://the-internet.herokuapp.com/login"; String actualUrl =
	 * driver.getCurrentUrl(); Assert.assertEquals(actualUrl, expectedUrl);
	 * 
	 * // Login button is still visible loginButton =
	 * driver.findElement(By.className("radius"));
	 * Assert.assertTrue(loginButton.isDisplayed(), "The button is not displayed.");
	 * 
	 * // Successful error message WebElement successMessage =
	 * driver.findElement(By.id("flash")); String expectedErrorMessage =
	 * "Your username is invalid!"; String actualErrorMessage =
	 * successMessage.getText();
	 * Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
	 * "The message is not correctly displayed.\nexpectedErrorMessage: " +
	 * expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);
	 * 
	 * // Close browser driver.quit(); }
	 * 
	 * @Test(priority = 2, groups = { "negativeTests" }) public void
	 * invalidPasswordTest() { System.out.println("Starting invalidPassword test");
	 * // Create driver System.setProperty("webdriver.gecko.driver",
	 * "src/main/resources/geckodriver.exe"); WebDriver driver = new
	 * FirefoxDriver(); driver.manage().window().maximize();
	 * 
	 * // Open the page String url = "http://the-internet.herokuapp.com/login";
	 * driver.get(url); System.out.println("Page is opened");
	 * 
	 * // Enter wrong Username WebElement username =
	 * driver.findElement(By.id("username")); username.sendKeys("tomsmith");
	 * 
	 * // Enter Password WebElement password =
	 * driver.findElement(By.id("password")); password.sendKeys("invalidPassword");
	 * 
	 * // Push login button WebElement loginButton =
	 * driver.findElement(By.className("radius")); loginButton.click();
	 * 
	 * // Verifications // Same URL String expectedUrl =
	 * "http://the-internet.herokuapp.com/login"; String actualUrl =
	 * driver.getCurrentUrl(); Assert.assertEquals(actualUrl, expectedUrl);
	 * 
	 * // Login button is still visible loginButton =
	 * driver.findElement(By.className("radius"));
	 * Assert.assertTrue(loginButton.isDisplayed(), "The button is not displayed.");
	 * 
	 * // Successful error message WebElement successMessage =
	 * driver.findElement(By.id("flash")); String expectedErrorMessage =
	 * "Your password is invalid!"; String actualErrorMessage =
	 * successMessage.getText();
	 * Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
	 * "The message is not correctly displayed.\nexpectedErrorMessage: " +
	 * expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);
	 * 
	 * // Close browser driver.quit(); }
	 */

}
