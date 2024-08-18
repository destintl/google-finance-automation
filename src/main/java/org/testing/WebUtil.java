package org.testing;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;

@Slf4j
public class WebUtil {
    private static WebDriver webDriver = GlobalVariable.webDriver;

    public static void click(String xpath){
        boolean isVisible = checkVisibility(xpath, GlobalVariable.waitPageFast);
        boolean isClickable = checkClickability(xpath, GlobalVariable.waitPageFast);
        if (isVisible && isClickable){
            WebElement element = webDriver.findElement(By.xpath(xpath));
            element.click();
            System.out.println("Element with path " + xpath + " is clicked");
        }else{
            if (!isVisible){
                throw new NoSuchElementException("Element with " + xpath + " is not visible");
            }else{
                throw new IllegalStateException("Element with path " + xpath + " is not clickable");
            }
        }
    }


    public static void verifyElementVisible(String xpath){
        verifyElementVisible(xpath, GlobalVariable.waitPageFast);
    }

    public static void verifyElementVisible(String xpath, int timeout){
        boolean isVisible = checkVisibility(xpath, timeout);
        if (isVisible){
            System.out.println("Element with path " + xpath + " is visible");
        }else{
            throw new TimeoutException("Element with path " + xpath + " is not visible");
        }
    }

    public static String getText(String xpath, int timeout){
        boolean isVisible = checkVisibility(xpath, timeout);
        if(isVisible){
            String text;
            WebElement element = webDriver.findElement(By.xpath(xpath));
            text = element.getText();
            System.out.println("Element with path " + xpath + " has text: " + text);
            return text;
        }else{
            throw new NoSuchElementException("Element with path " + xpath + " is not visible");
        }
    }

    public static String getText(String xpath){
        return getText(xpath, GlobalVariable.waitPageFast);
    }

    public static void clearText(String xpath){
        boolean isVisible = checkVisibility(xpath, GlobalVariable.waitPageFast);
        if (isVisible){
            WebElement element = webDriver.findElement(By.xpath(xpath));
            element.clear();
            System.out.println("Text in element " + xpath + " is clear");
        }else{
            throw new NoSuchElementException("Element with " + xpath + " is not visible");
        }
    }

    private static boolean checkVisibility(String xpath, int timeout){
        boolean isVisible = false;
        try {
            By by = By.xpath(xpath);
            new WebDriverWait(webDriver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            isVisible = true;
        } catch (Exception e) {
            System.out.println("[verifyElementVisible_Exception] - " + e.getMessage());
            e.printStackTrace();
        }
        return isVisible;
    }

    private static boolean checkClickability(String xpath, int timeout){
        boolean isClickable = false;
        try {
            By by = By.xpath(xpath);
            new WebDriverWait(webDriver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(by));
            isClickable = true;
        } catch (Exception e) {
            System.out.println("[verifyElementVisible_Exception] - " + e.getMessage());
            e.printStackTrace();
        }
        return isClickable;
    }


    public static void compareString(String value1, String value2){
        if (value1.equals(value2)){
            System.out.println("Same");
        }
        else {
            System.out.println("Different");
        }
    }

    public static void setTextAndEnter(String xpath,  String text){
        boolean isVisible = checkVisibility(xpath, GlobalVariable.waitPageFast);
        if (isVisible){
            clearText(xpath);
            WebElement element = webDriver.findElement(By.xpath(xpath));
            element.sendKeys(text);
            element.sendKeys(Keys.ENTER);
            System.out.println("Text in element " + xpath + " inserted");
        }else{
            throw new NoSuchElementException("Element with " + xpath + " is not visible");
        }
    }

    public static String takeScreenshot() throws IOException {
        try {
            TakesScreenshot ss = (TakesScreenshot) webDriver;
            File source = ss.getScreenshotAs(OutputType.FILE);
            File destination = new File("screenshot/content.png");
            FileUtils.copyFile(source, destination);

            byte[] fileContent = FileUtils.readFileToByteArray(source);
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Exception occurred when trying to take a screenshot!");
        }
    }
}