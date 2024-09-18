package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage extends BaseClass {
    WebDriver driver;
    WebDriverWait wait;

    public By itemQuantity = By.xpath("//*[@data-hook='product-quantity-input']");
    public By addToCartButton = By.xpath("//button[@data-hook='add-to-cart']");
    public By viewCartButton = By.xpath("//a[@data-hook='widget-view-cart-button']");
    public By checkoutButton = By.xpath("//button[@data-hook='CheckoutButtonDataHook.button']");
    public By totalPrice = By.xpath("//*[@data-hook='cart-widget-total']");
    public By itemPrice = By.xpath("//*[@data-hook='cart-widget-item-price']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Assert that the cart page is displayed correctly
    public void assertCartPageLoaded() {
        Assert.assertTrue(driver.getTitle().contains("Cart"), "The Cart Page did not load correctly.");
    }

    public void addToCart() {
        clickOnElement(driver, addToCartButton);
        // Switch to iframe that contains the cart popup
        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@name, 'tpapopup')]")));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton)).isDisplayed(), 
                "View Cart button is not displayed.");
    }

    public void viewCart() {
        clickOnElement(driver, viewCartButton);
    }

    public void proceedToCheckout() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        Assert.assertTrue(driver.findElement(checkoutButton).isDisplayed(), 
                "Checkout button is not visible.");
        clickOnElement(driver, checkoutButton);
    }

    // Verify total price against expected total
    public void verifyTotalPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice));
        String priceText = getElemetText(driver, itemPrice);
        String totalPriceText = getElemetText(driver, totalPrice);
        
        // Clean the text to remove currency symbols and commas
        String cleanedPrice = priceText.replaceAll("[^\\d.]", "");
        String cleanedTotalPrice = totalPriceText.replaceAll("[^\\d.]", "");
        
        try {
            float price = Float.parseFloat(cleanedPrice); // Parse the cleaned price text into a float
            String itemQuantityText = driver.findElement(itemQuantity).getAttribute("value");
            int quantity = Integer.parseInt(itemQuantityText); // Parse quantity to an integer
            float expectedTotalPrice = price * quantity; // Calculate expected total price
            
            System.out.println("Expected Total Price: " + expectedTotalPrice);
            
            float total = Float.parseFloat(cleanedTotalPrice); // Parse total price text into a float
            // Assert that the total price matches the expected total
            Assert.assertEquals(total, expectedTotalPrice, "Total price does not match the expected value.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Assert.fail("Failed to parse price or total price into a number.");
        }
    }
}
