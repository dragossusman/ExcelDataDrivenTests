package pageObjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ProductPage extends BasePage {

    public WebDriver driver;
    By size = By.cssSelector("[data-product-attribute='1']");
    By quantityIncrease = By.cssSelector(".touchspin-up");
    By quantityDecrease = By.cssSelector(".touchspin-down");
    By addToCartBtn = By.cssSelector("[data-button-action]");

    By homepageLink = By.xpath("//*[@id=\"wrapper\"]/div/nav/ol/li[1]/a/span");

    public ProductPage() throws IOException {
        super();
    }

    public WebElement getSize() throws IOException {
        this.driver = getDriver();
        return driver.findElement(size);
    }

    public WebElement getQuantityIncrease() throws IOException {
        this.driver = getDriver();
        return driver.findElement(quantityIncrease);
    }

    public WebElement getAddToCartBtn() throws IOException {
        this.driver = getDriver();
        return driver.findElement(addToCartBtn);
    }

    public WebElement getHomepageLink() throws IOException {
        this.driver = getDriver();
        return driver.findElement(homepageLink);
    }
}
