package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class HomePage extends BaseClass{
    WebDriver driver;

    public By shopLink = By.id("comp-iy4cwgmq1label");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToShop() {
        clickOnElement(driver, shopLink);
    }

}