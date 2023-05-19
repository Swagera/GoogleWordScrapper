package Scrapper;

import Excel.ExcelFunctions;
import Setup.HomePage;
import Setup.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("FieldCanBeLocal")
public class Scrapper {
    private String xpath;
    private List<WebElement> searchPageResults;
    List<String> hotelNames = new ArrayList<>();
    private WebDriver driver;
    public Scrapper(String location, String from, String to, String monthFrom, String monthTo, int page, String fileName) throws Exception{
        WebDriverSetup.setupDriver();
        HomePage.goToHomePage();
        HomePage.search(location, from, to, monthFrom, monthTo);
        scrap(page);

        ExcelFunctions excel = new ExcelFunctions();
        excel.saveToExcel(hotelNames, fileName);
        WebDriverSetup.quitDriver();
    }
    public List<String> scrap(int page) throws InterruptedException {

        driver = WebDriverSetup.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(2000);
        for (int i = 1; i <= page; i++) {
            if (i > 1) {
                xpath = "//*[@class='fc63351294 f9c5690c58' and text()=" + i + "]";
                driver.findElement(By.xpath(xpath)).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ba1f26500a a18ca6a206 abea3a9d71 c8b48968a2']")));
            }
            searchPageResults = driver.findElements(By.xpath("//div[@class='fcab3ed991 a23c043802']"));

            for (WebElement results : searchPageResults) {
                hotelNames.add(results.getText());
            }
        }
        return hotelNames;
    }
    }

