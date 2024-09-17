package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopPage {
    WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'INg0tB FbHYze') and contains(text(),'Best Seller')]")
    WebElement bestSellerProduct;

    public ShopPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectBestSeller() {
        bestSellerProduct.click();
    }
}
