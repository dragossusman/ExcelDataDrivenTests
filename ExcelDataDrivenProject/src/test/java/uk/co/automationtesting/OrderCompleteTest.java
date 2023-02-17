package uk.co.automationtesting;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;

@Listeners(resources.Listeners.class)
public class OrderCompleteTest extends Hooks {

    public OrderCompleteTest() throws IOException {
        super();
    }


    @Test
    public void endToEndTest() throws IOException, InterruptedException {
        ExtentManager.log("Starting OrderCompleteTest...");
        Homepage homepage = new Homepage();
        // click the toggle button first
        homepage.getToggle().click();
        Thread.sleep(3000);
        // click the test store link
        homepage.getTestStoreLink().click();
        // waiting for test store page to load
        Thread.sleep(3000);
        // click the first item
        ShopHomePage shopHome = new ShopHomePage();
        ExtentManager.pass("Reached the shop homepage...");
        shopHome.getProdOne().click();

        // select M size
        ProductPage productPage = new ProductPage();
        ExtentManager.pass("Reached the shop product page...");
        Select option = new Select(productPage.getSize());
        option.selectByVisibleText("M");
        // increase quantity by 1
        productPage.getQuantityIncrease().click();
        // press add to cart button
        productPage.getAddToCartBtn().click();
        ExtentManager.pass("Added product to basket...");

        // press proceed to checkout
        ShopContentPanel shopContentPanel = new ShopContentPanel();
        shopContentPanel.getCheckoutBtn().click();
        // wait for shopping cart to show up
        Thread.sleep(5000);
        // click promo code link
        ShoppingCart shoppingCart = new ShoppingCart();
        ExtentManager.pass("Reached shopping cart page...");
        shoppingCart.getHavePromo().click();
        // add a promo code
        shoppingCart.getPromoTextbox().sendKeys("20OFF");
        shoppingCart.getPromoAddBtn().click();
        // proceed checkout button
        shoppingCart.getProceedCheckoutBtn().click();

        // enter dummy data in personal info form
        OrderFormPersInfo orderFormPersInfo = new OrderFormPersInfo();
        orderFormPersInfo.getFirstNameField().sendKeys("test");
        orderFormPersInfo.getLastnameField().sendKeys("test");
        orderFormPersInfo.getEmailField().sendKeys("test45956@test.com");
        orderFormPersInfo.getTermsConditionsCheckbox().click();
        orderFormPersInfo.getContinueBtn().click();

        // enter dummy data in addresses info form
        OrderFormDelivery orderFormDelivery = new OrderFormDelivery();
        orderFormDelivery.getAddressField().sendKeys("plopilor 68");
        orderFormDelivery.getCityField().sendKeys("cluj");
        // select state
        Select state = new Select(orderFormDelivery.getStateDropdown());
        state.selectByIndex(1);
        // postal code
        orderFormDelivery.getPostcodeField().sendKeys("40038");
        // select country
        Select country = new Select(orderFormDelivery.getCountryDropdown());
        country.selectByVisibleText("United States");
        // press continue button
        orderFormDelivery.getContinueBtn().click();

        // select continue button in the shipping method form
        OrderFormShippingMethod orderFormShippingMethod = new OrderFormShippingMethod();
        orderFormShippingMethod.getContinueBtn().click();

        // in the payment form select pay by bank wire
        OrderFormPayment orderFormPayment = new OrderFormPayment();
        orderFormPayment.getPayByWireRadioBtn().click();
        // agree TOS
        orderFormPayment.getTermsConditionsCheckbox().click();
        // order
        orderFormPayment.getOrderBtn().click();
    }
}
