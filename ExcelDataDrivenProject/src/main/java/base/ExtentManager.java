package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager extends BasePage{

    public static ExtentReports extentReports;
    public static String extentReportPrefix;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public ExtentManager() throws IOException {
        super();
    }

    public static ExtentReports getReport(){
        if(extentReports == null){
            setupExtentReport("LiveProject");
        }
        return extentReports;
    }

    public static ExtentReports setupExtentReport(String testName){
        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/report/" +
                extentReportPrefix_Name(testName) + ".html");
        extentReports.attachReporter(spark);

        extentReports.setSystemInfo("Tester", "John Smith");
        spark.config().setReportName("Regression Test");
        spark.config().setDocumentTitle("Test Results");
        spark.config().setTheme(Theme.STANDARD);

        return extentReports;
    }

    public static String extentReportPrefix_Name(String testName){
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        extentReportPrefix = testName + "_" + date;
        return extentReportPrefix;
    }

    public static void flushReport(){
        extentReports.flush();
    }

    public synchronized static ExtentTest getTest(){
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String description){
        ExtentTest test = extentReports.createTest(name, description);
        extentTest.set(test);
        return getTest();
    }

    public synchronized static void log(String message){
        getTest().info(message);
    }

    public synchronized static void pass(String message){
        getTest().pass(message);
    }

    public synchronized static void fail(String message){
        getTest().fail(message);
    }

    public synchronized static void attachImage(){
        getTest().addScreenCaptureFromPath(getScreenShotDestinationPath());
    }
}
