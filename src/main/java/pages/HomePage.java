package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;



public class HomePage extends BaseClass{
    WebDriver driver;

    public By shopLink = By.id("comp-iy4cwgmq1label");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void verifyHomePageIsLoaded() {
        String expectedTitle = "Home | interview";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Home page title doesn't match expected!");
    }

    public void goToShop() {
        verifyHomePageIsLoaded();
        WebElement shopElement = driver.findElement(shopLink);
        Assert.assertTrue(shopElement.isDisplayed(), "Shop link is not displayed on the home page!");
        clickOnElement(driver, shopLink);
    }

}