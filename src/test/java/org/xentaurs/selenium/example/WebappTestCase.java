package org.xentaurs.selenium.example;

import java.net.URL;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebappTestCase {

    private final static String SELENIUM_URL = System.getProperty("selenium.url", "http://localhost:4444/wd/hub");
    private final static String SELENIUM_BROWSER = System.getProperty("selenium.browser", "chrome");
    private final static int SLEEP = Integer.parseInt(System.getProperty("sleep", "10000"));

    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities(SELENIUM_BROWSER, "", Platform.ANY);
        // Retry connecting
        WebDriverException ex = null;
        for (int i = 0; i < 10; i++) {
            try {
                this.driver = new RemoteWebDriver(new URL(SELENIUM_URL), capabilities);
                return;
            } catch (WebDriverException e) {
                ex = e;
                System.out.println(String.format("Error connecting to %s: %s. Retrying", SELENIUM_URL, e));
                Thread.sleep(1000);
            }
        }
        throw ex;
    }

    @Test
    public void WebappTestCase() throws Exception {

      driver.get("http://10.51.4.104/wordpress/wp-login.php");
      driver.findElement(By.id("user_login")).click();
      driver.findElement(By.id("user_login")).clear();
      driver.findElement(By.id("user_login")).sendKeys("admin");
      driver.findElement(By.id("user_pass")).clear();
      driver.findElement(By.id("user_pass")).sendKeys("admin");
      driver.findElement(By.id("rememberme")).click();
      driver.findElement(By.id("wp-submit")).click();
      driver.findElement(By.linkText("Add New")).click();
      driver.findElement(By.id("title")).clear();
      driver.findElement(By.id("title")).sendKeys("Test Post");
      driver.switchTo().frame("index=0");
      driver.findElement(By.xpath("//html")).click();
      driver.switchTo().frame("relative=parent");
      driver.findElement(By.id("post-preview")).click();
      driver.findElement(By.linkText("Edit \"Test Post\"")).click();
      driver.findElement(By.id("publish")).click();
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Skip to toolbar'])[1]/following::div[6]")).click();
      driver.findElement(By.linkText("All Posts")).click();
      driver.findElement(By.linkText("Test Post")).click();
      driver.findElement(By.linkText("test")).click();
      driver.findElement(By.linkText("Visit Site")).click();
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Skip to content'])[1]/following::img[1]")).click();
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Scroll down to content'])[1]/following::h2[1]")).click();
      driver.findElement(By.linkText("Test Post")).click();
      driver.findElement(By.id("comment")).click();
      driver.findElement(By.id("comment")).clear();
      driver.findElement(By.id("comment")).sendKeys("Test Comment");
      driver.findElement(By.id("content")).click();
      driver.findElement(By.id("submit")).click();

    }

    @After
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }
    private boolean isElementPresent(By by) {
      try {
        driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent() {
      try {
        driver.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    private String closeAlertAndGetItsText() {
      try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (acceptNextAlert) {
          alert.accept();
        } else {
          alert.dismiss();
        }
        return alertText;
      } finally {
        acceptNextAlert = true;
      }
    }
}
