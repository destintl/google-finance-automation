package org.testing.drivermanager;

import org.openqa.selenium.WebDriver;
import org.testing.DriverManager;

public class WebDriverManager implements DriverManager {
    private WebDriverFactory webDriverFactory;

    public WebDriverManager(){
        this.webDriverFactory = new WebDriverFactory();
    }

    @Override
    public WebDriver getDriver() {
        WebDriver webDriver = null;
        System.out.println("Starting application..");
        try {
            webDriver = webDriverFactory.getChromeDriver();
        }catch (Exception e){
            System.out.println("[getDriver_Exception] - " + e.getMessage());
            e.printStackTrace();
        }
        return webDriver;
    }

    @Override
    public void destroyDriver(WebDriver driver) {
        if (driver != null){
            driver.quit();
        }else{
            System.out.println("Chrome is not initialized yet");
        }
    }
}
