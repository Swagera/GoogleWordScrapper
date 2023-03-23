package Scrapper;

import Excel.ExcelFunctions;
import Setup.HomePage;
import Setup.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class Scrapper {
    private String xpath;
    private List<WebElement> searchPageResults;
    private final List<String> searchLinkText = new ArrayList<>();
    private WebDriver driver;
    public Scrapper(String query, int page, String fileName) throws Exception{
        WebDriverSetup.setupDriver();
        HomePage.goToHomePage();
        HomePage.search(query);
        scrap(page);

        ExcelFunctions excel = new ExcelFunctions();
        excel.saveToExcel(searchLinkText, fileName);
        WebDriverSetup.quitDriver();
    }
    public List<String> scrap(int page){

        driver = WebDriverSetup.getDriver();

        for (int i = 1; i <= page; i++) {
            if (i > 1) {
                xpath = "//a[@class='fl' and text()=" + i + "]";
                driver.findElement(By.xpath(xpath)).click();
            }
            searchPageResults = driver.findElements(By.xpath("//div[@class='yuRUbf']/a/h3"));

            for (WebElement results : searchPageResults) {
                searchLinkText.add(results.getText());
            }
        }
        return searchLinkText;
    }
}
