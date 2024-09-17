package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CartPage {
    WebDriver driver;

    @FindBy(xpath = "//button[contains(text(),'View Cart')]") // Update this locator to match the actual element
    private WebElement viewCartButton;

    @FindBy(xpath = "//button[contains(text(),'Checkout')]") // Update this locator to match the actual element
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[@class='total-price']") // Update this locator based on how the price is displayed
    private WebElement totalPrice;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void viewCart() {
        viewCartButton.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void verifyTotalPrice(String expectedPrice) {
        Assert.assertEquals(getTotalPrice(), expectedPrice, "Price does not match expected total.");
    }
}
