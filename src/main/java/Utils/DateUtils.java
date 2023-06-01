package Utils;

import Setup.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DateUtils {
    private WebDriverWait wait;
    private final WebDriver driver = WebDriverSetup.getDriver();
    public String getMonthName(String monthNumber) {
        return switch (monthNumber) {
            case "1" -> "january";
            case "2" -> "february";
            case "3" -> "march";
            case "4" -> "april";
            case "5" -> "may";
            case "6" -> "june";
            case "7" -> "july";
            case "8" -> "august";
            case "9" -> "september";
            case "10" -> "october";
            case "11" -> "november";
            case "12" -> "december";
            default -> "Invalid month number";
        };
    }
    public void datePicker(String from, String to, String monthAndYearFrom, String monthAndYearTo) {
        wait = new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='d47738b911 e246f833f7 fe211c0731']")))).click();
        if (isMonthValid(monthAndYearFrom)) {
            clickGivenDay(getDatePickerData(), from);
        } else {
            throw new IllegalArgumentException("error");
        }
        if (isMonthValid(monthAndYearTo)) {
            clickGivenDay(getDatePickerData(), to);
        } else {
            throw new IllegalArgumentException("error");
        }
    }
    public void clickGivenDay(List<WebElement> elementList, String day) {
        elementList.stream()
                .filter(element -> element.getText().contains(day))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public boolean isMonthValid(String monthAndYear) {
        boolean validMonth = false;
        while (!validMonth) {
            String currentMonth = driver.findElement(By.xpath("//h3[@class='ac78a73c96 ab0d1629e5']")).getText();
            String currentMonthLowercase = currentMonth.toLowerCase();
            if (!monthAndYear.contains(currentMonthLowercase)) {
                driver.findElement(By.xpath("//button[@class='fc63351294 a822bdf511 e3c025e003 fa565176a8 cfb238afa1 c334e6f658 ae1678b153 c9fa5fc96d be298b15fa']")).click();
            } else {
                validMonth = true;
                driver.findElement(By.xpath("//h3[@class='ac78a73c96 ab0d1629e5']")).click();
            }
        }
        return validMonth;
    }
    public List<WebElement> getDatePickerData(){
        WebElement dateWidgetFrom = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("aadb8ed6d3"))).get(0);
        List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
        return columns;
    }
}
