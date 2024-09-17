package pages;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductPage {
    private WebDriver driver;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }
    
    List<WebElement> colorOptions = driver.findElements(By.xpath("//input[@name='ColorPicker_group_0v5cfvpg']"));
    public By quantityBox = By.xpath("//input[@aria-label='Quantity']");
    public By upArrow = By.xpath("//span[@data-hook='number-input-spinner-up-arrow']");

    @FindBy(xpath = "//button[contains(text(),'Add to Cart')]")
    WebElement addToCartButton;

    

    public void pickColor() {
        // Randomly pick a color (either Black or White)
        Random rand = new Random();
        int randomIndex = rand.nextInt(colorOptions.size());
        colorOptions.get(randomIndex).click();
    }

     public void increaseQuantity(int itemQty) {
        Actions actions = new Actions(driver);
        
        // Hover over the quantity input to ensure arrows are visible
        WebElement quantityInput = driver.findElement(quantityBox);
        actions.moveToElement(quantityInput).perform();

        // Calculate how many times to click the up arrow (initial quantity is 1)
        int clicksNeeded = itemQty - 1;  // The default quantity is 1, so subtract 1

        WebElement upArrowButton = driver.findElement(upArrow);
        for (int i = 0; i < clicksNeeded; i++) {
            upArrowButton.click();
        }
    }
}
