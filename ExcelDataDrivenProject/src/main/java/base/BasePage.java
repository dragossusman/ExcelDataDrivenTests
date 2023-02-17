package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static base.WebDriverInstance.driver;

public class BasePage {
    public String url;
    public static Properties prop;

    public static String screenShotDestinationPath;

    public BasePage() throws IOException {
        prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
        prop.load(fileInputStream);
    }

    public static WebDriver getDriver() throws IOException {
        return WebDriverInstance.getDriver();
    }

    public String getUrl() throws IOException {
        this.url = prop.getProperty("url");
        return this.url;
    }

    public static String takeSnapShot(String name) throws IOException {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String destFile = System.getProperty("user.dir") + "\\target\\screenshots\\" + timestamp() + ".png";
        screenShotDestinationPath = destFile;
        try{
            FileUtils.copyFile(srcFile, new File(destFile));
        }catch (IOException e){
            e.printStackTrace();
        }
        return screenShotDestinationPath;
    }

    public static String timestamp() {
        return (new SimpleDateFormat("yyyy-MM-dd HH-mm-ss")).format(new Date());
    }

    public static String getScreenShotDestinationPath(){
        return screenShotDestinationPath;
    }
    public static void waitForElementInvisible(WebElement element, int timer) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), timer);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}