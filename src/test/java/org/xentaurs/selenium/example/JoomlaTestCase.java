package org.xentaurs.selenium.example;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class ExampleTest {

    private final static String SELENIUM_URL = System.getProperty("selenium.url", "http://localhost:4444/wd/hub");
    private final static String SELENIUM_BROWSER = System.getProperty("selenium.browser", "chrome");
    private final static int SLEEP = Integer.parseInt(System.getProperty("sleep", "10000"));

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
    public void test() throws Exception {
      driver.get("https://joomla.xentaurs.com/");
      driver.findElement(By.linkText("Home")).click();
      driver.findElement(By.id("modlgn-username")).click();
      driver.findElement(By.id("modlgn-username")).clear();
      driver.findElement(By.id("modlgn-username")).sendKeys("admin");
      driver.findElement(By.id("modlgn-passwd")).clear();
      driver.findElement(By.id("modlgn-passwd")).sendKeys("amin");
      driver.findElement(By.id("modlgn-remember")).click();
      driver.findElement(By.name("Submit")).click();
      driver.findElement(By.linkText("Joomla")).click();
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Latest Articles'])[1]/following::span[1]")).click();
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='What is a Content Management System?'])[1]/following::p[2]")).click();
   }

    @After
    public void tearDown() throws Exception {
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
        fail(verificationErrorString);
      }
    }
  }
}
