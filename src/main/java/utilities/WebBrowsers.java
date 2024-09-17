package utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class WebBrowsers {

    public static WebDriver chooseBrowserDriver(String browserName, boolean headless) {
        switch (browserName) {
            case "Chrome": {
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions chromeOptions = getChromeOptions(headless);  // No need for casting
                return new ChromeDriver(chromeOptions);
            }
            case "Firefox": {
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                FirefoxOptions firefoxOptions = getFirefoxOptions(headless);  // No need for casting
                return new FirefoxDriver(firefoxOptions);
            }
            case "Edge": {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            default:
                return null;
        }
    }

    private static ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--lang=en");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-popup-blocking");
        if (headless) {
            chromeOptions.addArguments("--headless");  // If running in headless mode
        }
        return chromeOptions;
    }

    private static FirefoxOptions getFirefoxOptions(boolean headless) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(headless);  // Enable headless mode if requested
        return firefoxOptions;
    }

    public static void staticmaximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void quitWindow(WebDriver driver) {
        driver.quit();
    }
}
