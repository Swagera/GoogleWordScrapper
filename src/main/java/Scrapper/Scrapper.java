package Scrapper;

import Excel.ExcelFunctions;
import Scrapper.ScrapperMethods.ScrapperMethods;
import Setup.HomePage;
import Setup.WebDriverSetup;
import Utils.DateUtils;


@SuppressWarnings("FieldCanBeLocal")
public class Scrapper {
    ScrapperMethods scrapperMethods;
    DateUtils dateUtils;

    public Scrapper(String location, String from, String to, String monthFrom, String monthTo, String yearFrom, String yearTo, String page, String fileName) throws Exception{
        dateUtils = new DateUtils();
        String monthAndYearFrom = dateUtils.getMonthName(monthFrom) + " " + yearFrom;
        String monthAndYearTo = dateUtils.getMonthName(monthTo) + " " + yearTo;

        WebDriverSetup.setupDriver();
        HomePage.goToHomePage();
        HomePage.search(location, from, to, monthAndYearFrom, monthAndYearTo);
        scrapperMethods = new ScrapperMethods();
        scrapperMethods.scrap(page);

        ExcelFunctions excel = new ExcelFunctions();
        excel.saveToExcel(scrapperMethods.getHotelNames(), scrapperMethods.getHotelReviewsCount(), scrapperMethods.getHotelRate(), scrapperMethods.getHotelPrice(), scrapperMethods.isBreakfastIncluded(), fileName);
        WebDriverSetup.quitDriver();
    }
}

