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

    public void addToCart() {
        clickOnElement(driver, addToCartButton);
        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@name, 'tpapopup')]")));
    }

    public void viewCart() {
        clickOnElement(driver, viewCartButton);
    }

    public void proceedToCheckout() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        clickOnElement(driver, checkoutButton);
    }

    public void verifyTotalPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice));
        String priceText = getElemetText(driver, itemPrice);
        String totalPriceText = getElemetText(driver, totalPrice);
        // Remove any non-numeric characters (currency symbols, commas, etc.)
        String cleanedPrice = priceText.replaceAll("[^\\d.]", "");
        String cleanedTotalPrice = totalPriceText.replaceAll("[^\\d.]", "");
        try {
            float price = Float.parseFloat(cleanedPrice); // Convert the cleaned price text to an integer
            String itemQuantityText = driver.findElement(itemQuantity).getAttribute("value");
            int quantity = Integer.parseInt(itemQuantityText); // Convert quantity to an integer
            float expectedTotalPrice = price * quantity; // Calculate the expected total price
            System.out.println("expectedTotalPrice: " + expectedTotalPrice);
            // Convert total price to float after cleaning
            float total = Float.parseFloat(cleanedTotalPrice);
            // Assert that the actual total matches the expected total
            Assert.assertEquals(total, expectedTotalPrice, "Price does not match expected total.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Assert.fail("Failed to parse price or total price into a number.");
        }
    }
}
