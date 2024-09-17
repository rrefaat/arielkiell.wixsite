package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import utilities.WebBrowsers;
import utilities.readers.PropertiesReader;

public class TestBase {
    protected static WebDriver driver;
    public static String folderName;

    // Add a check to ensure driver is not null
    @BeforeClass
    public void startDriver() {
        if (driver == null) {
            driver = WebBrowsers.chooseBrowserDriver(PropertiesReader.getValue("BROWSER"), false);
            WebBrowsers.staticmaximizeWindow(driver);
            driver.get(PropertiesReader.getValue("url"));
        }
    }

    // Quit WebDriver after all tests
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Ensure driver is set to null after quitting
        }
    }

    @BeforeSuite
    public void setupFolder() {
        // Create a timestamp for the folder
        folderName = "Test_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        // Create the folder
        new File(PropertiesReader.getValue("SCREEN_SHOT") + folderName).mkdirs();
    }

    // Screenshot utility
    public static void takeScreenshot(WebDriver driver, String stepName) {
        // Create a timestamp for each screenshot
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());

        // Capture screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Set the destination for the screenshot inside the specific folder for this run
        String filePath = PropertiesReader.getValue("SCREEN_SHOT") + folderName + "/" + stepName + "_" + timestamp + ".png";

        try {
            // Copy the screenshot to the destination folder
            FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
