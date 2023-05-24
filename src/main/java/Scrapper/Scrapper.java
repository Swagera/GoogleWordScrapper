package Scrapper;

import Excel.ExcelFunctions;
import Scrapper.ScrapperMethods.ScrapperMethods;
import Setup.HomePage;
import Setup.WebDriverSetup;



@SuppressWarnings("FieldCanBeLocal")
public class Scrapper {
    ScrapperMethods scrapperMethods;

    public Scrapper(String location, String from, String to, String monthFrom, String monthTo, String yearFrom, String yearTo, int page, String fileName) throws Exception{
        String monthAndYearFrom = monthFrom + " " + yearFrom;
        String monthAndYearTo = monthTo + " " + yearTo;

        WebDriverSetup.setupDriver();
        HomePage.goToHomePage();
        HomePage.search(location, from, to, monthAndYearFrom, monthAndYearTo);
        scrapperMethods = new ScrapperMethods();
        scrapperMethods.scrap(page);

        ExcelFunctions excel = new ExcelFunctions();
        excel.saveToExcel(scrapperMethods.getHotelNames(), scrapperMethods.getHotelReviewsCount(), scrapperMethods.getHotelRate(), fileName);
        WebDriverSetup.quitDriver();
    }
}

