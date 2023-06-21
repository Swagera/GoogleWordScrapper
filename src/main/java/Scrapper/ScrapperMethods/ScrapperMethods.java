package Scrapper.ScrapperMethods;

import GUI.MessageDialog;
import Setup.WebDriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScrapperMethods {
    private String xpathPage;
    private List<WebElement> propertyCards;
    private WebDriver driver;

    private final List<String> hotelNames = new ArrayList<>();
    private final List<String> hotelReviewsCount = new ArrayList<>();
    private final List<String> hotelRate = new ArrayList<>();
    private final List<String> hotelPrice = new ArrayList<>();
    private final List<String> isBreakfast = new ArrayList<>();
    private final List<String> isCancellationFree = new ArrayList<>();

    public List<String> getHotelPrice() {
        return hotelPrice;
    }
    public List<String> getHotelRate() {
        return hotelRate;
    }
    public List<String> getHotelNames() {
        return hotelNames;
    }
    public List<String> getHotelReviewsCount() {
        return hotelReviewsCount;
    }
    public List<WebElement> getPropertyCards() {
        return driver.findElements(By.xpath("//div[@class='a826ba81c4 fa2f36ad22 afd256fc79 d08f526e0d ed11e24d01 ef9845d4b3 da89aeb942']"));
    }
    public void scrap(String page) {
        int pageInt = Integer.parseInt(page);
        driver = WebDriverSetup.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(250, TimeUnit.MILLISECONDS)
                .ignoring(Exception.class);
        try{
            WebElement popUpWindowClose = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='fc63351294 a822bdf511 e3c025e003 fa565176a8 f7db01295e c334e6f658 ae1678b153']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(popUpWindowClose).perform();
            actions.click(popUpWindowClose).perform();
        } catch (NoSuchElementException e) {
            //We do nothing here to ensure that the code continues to execute
        }
        boolean nextPageExists = true;
        int currentPage = 0;
        for (int i = 1; i <= pageInt && nextPageExists; i++) {
            if (i > 1) {
                xpathPage = "//*[@class='fc63351294 f9c5690c58' and text()=" + i + "]";
                driver.findElement(By.xpath(xpathPage)).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ba1f26500a a18ca6a206 abea3a9d71 c8b48968a2']")));
            }
            scrapHotelNames();
            scrapHotelReviewsCount();
            scrapHotelRate();
            scrapHotelPrice();
            isBreakfastIncluded();
            isCancellationFree();

            currentPage++;
            int nextPage = i+1;
            try {
                driver.findElement(By.xpath("//*[@class='fc63351294 f9c5690c58' and text()=" + nextPage + "]"));
            }
            catch (NoSuchElementException e) {
                nextPageExists = false;
                MessageDialog.showInfoDialog(null, "Only " + currentPage + " pages exist. These pages will be scrapped");
            }
        }
    }
    public List<String> scrapHotelNames() {

            propertyCards = getPropertyCards();
            for (WebElement card : propertyCards) {
                try{
                    WebElement hotelName = card.findElement(By.xpath(".//div[@class='fcab3ed991 a23c043802']"));
                    hotelNames.add(hotelName.getText());
                } catch (NoSuchElementException e) {
                    hotelNames.add("");
                }

            }
        return hotelNames;
    }
    public List<String> scrapHotelReviewsCount() {

            propertyCards = getPropertyCards();
            for (WebElement card : propertyCards) {
                try {
                   WebElement review = card.findElement(By.xpath(".//div[@class='d8eab2cf7f c90c0a70d3 db63693c62']"));
                    String text = review.getText();
                    String numericPart = text.replaceAll("\\D", "");
                    hotelReviewsCount.add(numericPart);
                } catch (NoSuchElementException e){
                    hotelReviewsCount.add("");
                }
            }
        return hotelReviewsCount;
    }
    public List<String> scrapHotelRate() {

        propertyCards = getPropertyCards();
        for (WebElement card : propertyCards) {
            try {
               WebElement rate = card.findElement(By.xpath(".//div[@class='b5cd09854e d10a6220b4']"));
                hotelRate.add(rate.getText());
            } catch (NoSuchElementException e) {
                hotelRate.add("");
            }
        }
        return hotelRate;
    }
    public List<String> scrapHotelPrice() {

        propertyCards = getPropertyCards();
        for (WebElement card : propertyCards) {
            try {
               WebElement price = card.findElement(By.xpath(".//span[@class='fcab3ed991 fbd1d3018c e729ed5ab6']"));
                hotelPrice.add(price.getText());
            } catch (NoSuchElementException e) {
                hotelPrice.add("");
            }
        }
        return hotelPrice;
    }
    public List<String> isBreakfastIncluded() {

        propertyCards = getPropertyCards();
        for (WebElement card : propertyCards) {
            try {
                card.findElement(By.xpath(".//span[@class='e05969d63d']"));
                isBreakfast.add("Yes");
            } catch (NoSuchElementException e) {
                isBreakfast.add("No");
            }
        }
        return isBreakfast;
    }
    public List<String> isCancellationFree() {

        propertyCards = getPropertyCards();
        for (WebElement card : propertyCards) {
            try {
                card.findElement(By.xpath(".//div[@class='d506630cf3']"));
                isCancellationFree.add("Yes");
            } catch (NoSuchElementException e) {
                isCancellationFree.add("No");
            }
        }
        return isCancellationFree;
    }
}
