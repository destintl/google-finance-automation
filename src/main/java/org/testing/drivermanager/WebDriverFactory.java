package org.testing.drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    static WebDriver driver;

    public WebDriver getChromeDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver/chromedriver");

            // Define Chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.get("https://www.google.com/finance/");

            System.out.println("Application Started...");
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return driver;
    }
}
