package tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.xpath.operations.String;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utilities.WebBrowsers;
import utilities.readers.PropertiesReader;

public class TestBase {
    static WebDriver driver;

    @BeforeClass
    public void startDriver() {
        driver = WebBrowsers.chooseBrowserDriver(PropertiesReader.getValue("BROWSER"), false);
        WebBrowsers.staticmaximizeWindow(driver);
        driver.get(PropertiesReader.getValue("url"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
