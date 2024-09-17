package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopPage extends BaseClass{
    WebDriver driver;

    public By bestSellerProduct = By.xpath("//div[@data-hook='RibbonDataHook.RibbonOnImage' and contains(text(),'Best Seller')]/ancestor::a[@data-hook='product-item-container']");

    public ShopPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectBestSeller(){
        scrollToFindElement(driver, bestSellerProduct);
        clickOnElement(driver, bestSellerProduct);
    }
}
