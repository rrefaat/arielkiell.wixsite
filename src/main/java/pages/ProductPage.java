package pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductPage extends BaseClass {
    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    public By quantityBox = By.xpath("//input[@aria-label='Quantity']");
    public By upArrow = By.xpath("//span[@data-hook='number-input-spinner-up-arrow']");
    public By addToCartButton = By.xpath("//button[@data-hook='add-to-cart']");
    public By colorOptionsLocator = By.xpath("//*[@data-hook='color-picker-item']");

    // Method to randomly pick a color
    public void pickColor() {
        // Assert that color options are present
        List<WebElement> colorOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(colorOptionsLocator));
        Assert.assertTrue(colorOptions.size() > 0, "No color options available for selection!");

        // Randomly pick a color
        Random rand = new Random();
        int randomIndex = rand.nextInt(colorOptions.size());
        colorOptions.get(randomIndex).click();
    }

    // Method to increase product quantity
    public void increaseQuantity(int itemQty) {
        // Assert that the quantity box is visible
        WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityBox));
        Assert.assertTrue(quantityInput.isDisplayed(), "Quantity input box is not visible!");

        // Hover over the quantity input to make sure the up arrow is visible
        Actions actions = new Actions(driver);
        actions.moveToElement(quantityInput).perform();

        // Assert that the up arrow is available
        WebElement upArrowElement = wait.until(ExpectedConditions.elementToBeClickable(upArrow));
        Assert.assertTrue(upArrowElement.isDisplayed(), "Up arrow for increasing quantity is not visible!");

        // Calculate how many times to click the up arrow (initial quantity is 1)
        int clicksNeeded = itemQty - 1;
        for (int i = 0; i < clicksNeeded; i++) {
            clickOnElement(driver, upArrow);
        }
    }

    // Method to click 'Add to Cart'
    public void addToCart() {
        // Assert that the 'Add to Cart' button is visible and clickable
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        Assert.assertTrue(addToCartBtn.isDisplayed(), "'Add to Cart' button is not visible!");

        // Click the 'Add to Cart' button
        clickOnElement(driver, addToCartButton);
    }
}
