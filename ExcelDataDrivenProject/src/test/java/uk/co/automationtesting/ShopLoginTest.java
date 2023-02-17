package uk.co.automationtesting;

import base.ExtentManager;
import base.Hooks;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.FileInputStream;
import java.io.IOException;

@Listeners(resources.Listeners.class)
public class ShopLoginTest extends Hooks {

    public ShopLoginTest() throws IOException {
        super();
    }

    @Test
    public void shopLoginTest() throws IOException, InterruptedException {
        ExtentManager.log("Starting shopLoginTest...");

        Homepage homepage = new Homepage();
        Thread.sleep(5000);
        // click the toggle button first
        homepage.getToggle().click();
        Thread.sleep(3000);
        // click the test store link
        homepage.getTestStoreLink().click();
        // waiting for test store page to load
        Thread.sleep(3000);
        // click the first item
        Homepage shopHome = new Homepage();
        ExtentManager.pass("Reached the shop homepage...");
        shopHome.getSignInLink().click();

        FileInputStream workbookLocation = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\java\\resources\\credentials.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Row row1 = sheet.getRow(1);
        Cell cellR1C0 = row1.getCell(0);
        Cell cellR1C1 = row1.getCell(1);

        String emailRow1 = cellR1C0.toString();
        String passwordRow1 = cellR1C1.toString();

        ShopLoginPage shop = new ShopLoginPage();
        shop.getEmail().sendKeys(emailRow1);
        shop.getPassword().sendKeys(passwordRow1);
        shop.getSubmitBtn().click();

        ShopYourAccount yourAccount = new ShopYourAccount();
        try{
            yourAccount.getSignOutBtn().click();
            ExtentManager.pass("User has signed in");
        }catch (Exception e){
            ExtentManager.fail("User could not sign in");
            Assert.fail();
        }

        Row row2 = sheet.getRow(2);
        Cell cellR2C0 = row2.getCell(0);
        Cell cellR2C1 = row2.getCell(1);

        String emailRow2 = cellR2C0.toString();
        String passwordRow2 = cellR2C1.toString();

        shop.getEmail().sendKeys(emailRow2);
        shop.getPassword().sendKeys(passwordRow2);
        shop.getSubmitBtn().click();

        try{
            yourAccount.getSignOutBtn().click();
            ExtentManager.pass("User has signed in");
        }catch (Exception e){
            ExtentManager.fail("User could not sign in");
            Assert.fail();
        }


    }
}
