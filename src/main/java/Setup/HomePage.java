package Setup;

import Setup.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private static WebDriver driver = WebDriverSetup.getDriver();

    public static void goToHomePage() {
        driver.get("https://www.google.com");
        driver.findElement(By.id("L2AGLb")).click();
    }
    public static void search(String query){
        driver.findElement(By.name("q")).sendKeys(query);
        driver.findElement(By.name("q")).submit();
    }
}
