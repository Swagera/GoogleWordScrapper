package Setup;

import Utils.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
    private final static WebDriver driver = WebDriverSetup.getDriver();

    public static void goToHomePage() {
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/index.en-gb.html?label=gen173nr-1BCAEoggI46AdIM1gEaLYBiAEBmAEeuAEXyAEM2AEB6AEBiAIBqAIDuAKuj-KjBsACAdICJGVmMzNiZDlhLTI0ZDctNDA2ZS04Mzc4LWI1MTFhNmZmMmVkZdgCBeACAQ&sid=d1cdc62f523554a279499db3c61d0efd&keep_landing=1&sb_price_type=total&lang=en-gb&soz=1&lang_changed=1");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    public static void search(String location, String from, String to, String monthAndYearFrom, String monthAndYearTo) {
        DateUtils dateUtils = new DateUtils();
        WebElement searchBox = driver.findElement(By.id(":Ra9:"));
        searchBox.click();
        searchBox.sendKeys(location);
        dateUtils.datePicker(from, to, monthAndYearFrom, monthAndYearTo);
        driver.findElement(By.xpath("//button[@class='fc63351294 a822bdf511 d4b6b7a9e7 cfb238afa1 c938084447 f4605622ad aa11d0d5cd']")).click();
    }
}

