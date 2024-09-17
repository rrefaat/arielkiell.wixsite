package tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.ShopPage;

public class TestScenario extends TestBase{

    HomePage home;
    ShopPage shop;
    ProductPage product;
    CartPage cart;

    @BeforeMethod
    public void setUp() {
        home = new HomePage(driver);
        shop = new ShopPage(driver);
        product = new ProductPage(driver);
        cart = new CartPage(driver);
    }

    @Test
    public void testCheckout() {
        home.goToShop();
        takeScreenshot(driver,"step1_goToShop");
        
        shop.selectBestSeller();
        takeScreenshot(driver,"step2_selectBestSeller");

        product.pickColor();
        takeScreenshot(driver,"step3_pickColor");

        product.increaseQuantity(3);
        takeScreenshot(driver,"step4_increaseQuantity");

        cart.addToCart();
        takeScreenshot(driver,"step5_addToCart");

        cart.verifyTotalPrice();
        takeScreenshot(driver,"step6_verifyTotalPrice");

        cart.viewCart();
        takeScreenshot(driver,"step7_viewCart");

        cart.proceedToCheckout();
        takeScreenshot(driver,"step8_proceedToCheckout");
    }
}
