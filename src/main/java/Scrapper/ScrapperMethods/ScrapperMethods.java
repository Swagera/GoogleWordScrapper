package Scrapper.ScrapperMethods;

import Setup.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ScrapperMethods {
    private String xpathPage;
    private List<WebElement> hotelNamesResults;
    private List<WebElement> hotelRateResults;
    private List<WebElement> hotelReviewsResults;
    private WebDriver driver;

    private List<String> hotelNames = new ArrayList<>();

    private List<Integer> hotelReviewsCount = new ArrayList<>();

    private List<String> hotelRate = new ArrayList<>();

    public List<String> getHotelRate() {
        return hotelRate;
    }
    public List<String> getHotelNames() {
        return hotelNames;
    }

    public List<Integer> getHotelReviewsCount() {
        return hotelReviewsCount;
    }
    public void scrap(int page) throws InterruptedException {
        driver = WebDriverSetup.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(2000);
        for (int i = 1; i <= page; i++) {
            if (i > 1) {
                xpathPage = "//*[@class='fc63351294 f9c5690c58' and text()=" + i + "]";
                driver.findElement(By.xpath(xpathPage)).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ba1f26500a a18ca6a206 abea3a9d71 c8b48968a2']")));
            }
            scrapHotelNames();
            scrapHotelReviewsCount();
            scrapHotelRate();
        }
    }
    public List<String> scrapHotelNames() {

            hotelNamesResults = driver.findElements(By.xpath("//div[@class='fcab3ed991 a23c043802']"));

            for (WebElement results : hotelNamesResults) {
                hotelNames.add(results.getText());
            }
        return hotelNames;
    }
    public List<Integer> scrapHotelReviewsCount() {

            hotelReviewsResults = driver.findElements(By.xpath("//div[@class='d8eab2cf7f c90c0a70d3 db63693c62']"));

            for (WebElement results : hotelReviewsResults) {
                String text = results.getText();
                int index = text.indexOf(' ');
                String filteredText = (index != -1) ? text.substring(0, index) : text;
                int parsedFilteredText = Integer.parseInt(filteredText);
                hotelReviewsCount.add(parsedFilteredText);
            }
        return hotelReviewsCount;
    }
    public List<String> scrapHotelRate() {

            hotelRateResults = driver.findElements(By.xpath("//div[@class='b5cd09854e d10a6220b4']"));

            for (WebElement results : hotelRateResults) {
                hotelRate.add(results.getText());
            }
        return hotelRate;
    }
}
