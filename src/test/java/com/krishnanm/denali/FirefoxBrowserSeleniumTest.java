package com.krishnanm.denali;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Unit test for simple App.
 */
public class FirefoxBrowserSeleniumTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FirefoxBrowserSeleniumTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(FirefoxBrowserSeleniumTest.class);
    }

    public void testUsingFirefoxBrowser() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        final WebDriver driver = new FirefoxDriver(capabilities);

        if (driver == null) {
            throw new RuntimeException("Driver is null attempting to run FirefoxDriver");
        }

        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();

        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return driver.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ex) {
                System.out.println("Error attempting to quit Firefox webdriver: " + ex.getMessage());
            }
        }
    }
}
