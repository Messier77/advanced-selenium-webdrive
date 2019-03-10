package com.herokuapp.theinternet.loginpagetests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;

public class PositiveLoginTests extends TestUtilities {

	@Test
	public void logInTest() {
		log.info("Starting login test");

		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		log.info("Page is opened");

		// Enter Username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(500);

		// Enter Password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");

		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Push Login button
		WebElement loginButton = driver.findElement(By.className("radius"));
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();

		// Verifications
		// New URL
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

		// Logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "The button is not displayed.");

		// Successful Login message
		WebElement successMessage = driver.findElement(By.id("flash"));
		String expectedSuccessMessage = "You logged into a secure area!";
		String actualSuccessMessage = successMessage.getText();
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"The message is not correctly displayed.\nexpectedSuccessMessage: " + expectedSuccessMessage
						+ "\nactualSuccessMessage: " + actualSuccessMessage);

	}

}
