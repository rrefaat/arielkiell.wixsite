package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    WebDriver driver;

    @FindBy(id="comp-iy4cwgmq1label")
    WebElement shopLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToShop() {
        shopLink.click();
    }
}