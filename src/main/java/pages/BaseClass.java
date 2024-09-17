package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
    public static void clickOnElement(WebDriver driver, By locator) {
        isDisplayed(driver,locator);
        isClickable(driver,locator);
        driver.findElement(locator).click();
    }
    public static boolean isDisplayed(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
    }
    public static boolean isClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(locator)).isEnabled();
    }

    public static void  scrollToFindElement(WebDriver driver, By locator) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView()", driver.findElement(locator));
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
    }

    public static String getElemetText(WebDriver driver, By locator){
        isDisplayed(driver,locator);
        return driver.findElement(locator).getText();
    }

}
