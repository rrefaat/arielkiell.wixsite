package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.ShopPage;

public class TestScenario extends TestBase{

    HomePage home = new HomePage(driver);
    ShopPage shop = new ShopPage(driver);
    ProductPage product = new ProductPage(driver);
    CartPage cart = new CartPage(driver);

    @Test
    public void testCheckout() {
        home.goToShop();
        shop.selectBestSeller();
        product.pickColor(); // Choose any color
        product.increaseQuantity(3);
        //cart.viewCart();
        cart.proceedToCheckout();
        Assert.assertEquals(cart.getTotalPrice(), "54 CAD");
    }
}
