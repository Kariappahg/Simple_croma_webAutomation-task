package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.CromaHomePage;
import resources.ExcelData;
import resources.ReadExcel;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import resources.ExtentReport;

public class CromaSearchTest extends Base {
    WebDriver driver;
    private static Logger log = LogManager.getLogger(CromaSearchTest.class.getName());
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setUpExtent() {
        extent = ExtentReport.getReportObject();
    }

    @BeforeTest
    public void setUp() throws IOException, Exception {
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(this.getUrl());
        driver.manage().window().maximize();
        log.info("URL launched");
        test = extent.createTest("Croma Search Test");
    }

    @Test(dataProvider = "excelDataProvider")
    public void search(String searchData) throws Exception {
        CromaHomePage homePage = new CromaHomePage(driver);
        test.info("Navigating to search box");
        homePage.searchbox().sendKeys(searchData + Keys.ENTER);
        log.info("Search data entered");
        test.pass("Search data entered");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement brandSelection = wait.until(ExpectedConditions.elementToBeClickable(homePage.brandSelection()));
        brandSelection.click();
        log.info("Brand selection clicked");
        test.pass("Brand selection clicked");

        wait.until(ExpectedConditions.elementToBeClickable(homePage.brand1())).click();
        log.info("Brand 1 selected");
        test.pass("Brand 1 selected");

        wait.until(ExpectedConditions.elementToBeClickable(homePage.brand2())).click();
        log.info("Brand 2 selected");
        test.pass("Brand 2 selected");

        wait.until(ExpectedConditions.elementToBeClickable(homePage.brand3())).click();
        log.info("Brand 3 selected");
        test.pass("Brand 3 selected");

        
        WebElement sort = wait.until(ExpectedConditions.elementToBeClickable(homePage.sort()));
        sort.click();
        
        log.info("Sort filter opened");
        test.pass("Sort filter opened");

        
        wait.until(ExpectedConditions.elementToBeClickable(homePage.byDiscount())).click();
        Thread.sleep(3000);
        log.info("BY DISCOUNT selected");
        test.pass("BY DISCOUNT selected");

        double averagePrice = homePage.avgPriceOfProduct();
        log.info("The average price of the top 10 products sorted by discount is: " + averagePrice);
        test.pass("The average price of the top 10 products sorted by discount is: " + averagePrice);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        test.pass("Driver closed");
    }

    @AfterSuite
    public void tearDownExtent() {
        extent.flush(); 
    }

    @DataProvider(name = "excelDataProvider")
    public Iterator<Object[]> excelDataProvider() throws IOException {
        ArrayList<ExcelData> data = new ReadExcel().getData();
        ArrayList<Object[]> testData = new ArrayList<>();
        
        for (ExcelData excelData : data) {
            testData.add(new Object[]{excelData.getSearchData()});
        }
        
        return testData.iterator();
    }
}
