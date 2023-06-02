# Booking Scrapper

This is a web scrapping tool that allows you to scrap data from **booking.com** website. Data is saved within the excel file. It also has a GUI interface that allows you to:
* Choose check-in and check-out dates
* Choose number of pages you want to scrap data from
* Choose excel file name

## Description
This tool has been created with the use of:

- &nbsp; ![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=Selenium&logoColor=white)
- &nbsp; ![Gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
- &nbsp; ![Apache POI](https://img.shields.io/badge/Apache_poi-D22128?style=for-the-badge&logo=Apache&logoColor=white)
- &nbsp; ![TestNG](https://img.shields.io/badge/TestNG-0769AD?style=for-the-badge&logo=testng&logoColor=white)
- &nbsp; ![Java Swing](https://img.shields.io/badge/Java_Swing-000000?style=for-the-badge&logo=javaswing&logoColor=white)

## Getting Started
You can either download the ZIP file and run the BookingScrapper.bat file or clone repository to your IDE.

### Prerequisites
Java installed

Gradle installed

### Executing program
#### Through .bat file
1. Clone repository
2. Go to project directory
3. Double click **BookingScrapper.bat**
#### Through IDE
1. Clone repository 
2. Open project in your IDE (preferably IntelliJ IDEA)
3. Open terminal (alt+F12 on Windows)
4. execute `gradle run` command

#### Through Command Prompt
1. Clone repository
2. Open Command Prompt
3. Move to project directory `cd C:\[your path]\BookingScrapper`
4. execute `gradle run` command


## Bugs to fix
* Possibility of choosing dates that excess 90 days period
* No validation of chosen number of pages
* Headless mode throws an error

## TODO list
* Improve GUI
* Improve output excel file
* Create unit tests
* Add more scrapped data
* Possibility to choose currency
