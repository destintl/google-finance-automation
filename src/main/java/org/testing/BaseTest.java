package org.testing;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testing.drivermanager.WebDriverManager;
import org.testng.annotations.AfterSuite;

public abstract class BaseTest {
    protected DriverManager driverManager;
    protected ThreadLocal<WebDriver> driver;
    protected boolean closeAfterFinish;

    public BaseTest(){
        try {
            this.driverManager = new WebDriverManager();
            this.driver = new ThreadLocal<>();
            WebDriver initializeDriver = this.driverManager.getDriver();
            this.setDriver(initializeDriver);
            this.closeAfterFinish = true;
            GlobalVariable.webDriver = initializeDriver;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    @SneakyThrows
    @AfterSuite(alwaysRun = true)
    public void cleanUp(){
        System.out.println("::After Test Suite::");
        if (this.closeAfterFinish){
            this.driverManager.destroyDriver(this.driver.get());
        }
    }
}