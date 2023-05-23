package Setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.List;

public class HomePage {
    private static WebDriver driver = WebDriverSetup.getDriver();
    private static WebDriverWait wait;

    public static void goToHomePage() {
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    public static void search(String location, String from, String to, String monthAndYearFrom, String monthAndYearTo) {
        WebElement searchBox = driver.findElement(By.id(":Ra9:"));
        searchBox.click();
        searchBox.sendKeys(location);
        datePicker(from, to, monthAndYearFrom, monthAndYearTo);
        driver.findElement(By.xpath("//button[@class='fc63351294 a822bdf511 d4b6b7a9e7 cfb238afa1 c938084447 f4605622ad aa11d0d5cd']")).click();
    }

    public static void datePicker(String from, String to, String monthAndYearFrom, String monthAndYearTo) {
        wait = new WebDriverWait(driver, 10);
        driver.findElement(By.xpath("//button[@class='d47738b911 e246f833f7 fe211c0731']")).click();
        if (isMonth(monthAndYearFrom)) {
            clickGivenDay(getDatePickerData(), from);
        } else {
            throw new IllegalArgumentException("dupa");
        }
        if (isMonth(monthAndYearTo)) {
            clickGivenDay(getDatePickerData(), to);
        } else {
            throw new IllegalArgumentException("dupa");
        }
    }

    public static void clickGivenDay(List<WebElement> elementList, String day) {
        elementList.stream()
                .filter(element -> element.getText().contains(day))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public static boolean isMonth(String monthFrom) {
        boolean validMonth = false;
        String currentMonth = driver.findElement(By.xpath("//h3[@class='ac78a73c96 ab0d1629e5']")).getText();
        currentMonth.toLowerCase();
        monthFrom = monthFrom.toLowerCase();
        while (!validMonth) {
            if (!monthFrom.contains(currentMonth)) {
                driver.findElement(By.xpath("//button[@class='fc63351294 a822bdf511 e3c025e003 fa565176a8 cfb238afa1 c334e6f658 ae1678b153 c9fa5fc96d be298b15fa']")).click();
                currentMonth = driver.findElement(By.xpath("//h3[@class='ac78a73c96 ab0d1629e5']")).getText();
            } else {
                validMonth = true;
                driver.findElement(By.xpath("//h3[@class='ac78a73c96 ab0d1629e5']")).click();
            }
        }
        return validMonth;
    }
    public static List<WebElement> getDatePickerData(){
        WebElement dateWidgetFrom = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("aadb8ed6d3"))).get(0);
        List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
        return columns;
    }
}

