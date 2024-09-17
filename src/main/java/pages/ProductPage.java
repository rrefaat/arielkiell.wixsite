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

public class ProductPage extends BaseClass{
    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public By quantityBox = By.xpath("//input[@aria-label='Quantity']");
    public By upArrow = By.xpath("//span[@data-hook='number-input-spinner-up-arrow']");
    public By addToCartButton = By.xpath("//button[@data-hook='add-to-cart']");   

    public void pickColor() {
        // Randomly pick a color
        List<WebElement> colorOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@data-hook='color-picker-item']")));
        Random rand = new Random();
        int randomIndex = rand.nextInt(colorOptions.size());
        colorOptions.get(randomIndex).click();
    }

     public void increaseQuantity(int itemQty) {
        Actions actions = new Actions(driver);
        // Hover over the quantity input to ensure arrows are visible
        WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityBox));
        actions.moveToElement(quantityInput).perform();
        // Calculate how many times to click the up arrow (initial quantity is 1)
        int clicksNeeded = itemQty - 1;  // The default quantity is 1, so subtract 1
        for (int i = 0; i < clicksNeeded; i++) {
            clickOnElement(driver, upArrow);
        }
    }
}
