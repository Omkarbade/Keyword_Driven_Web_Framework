package engine;

import  java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import Base.Base;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Utility;


public class KeyWordEngine {
    public static Logger log = LogManager.getLogger(Utility.class);
    public WebDriver driver;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;

    public Base base;
    public WebElement element;

    public final String SHEET_PATH = ".\\src\\main\\resources\\KeywordDrivenData.xlsx";

    public void startExecution(String sheetName) {

        FileInputStream file = null;
        try {
            file = new FileInputStream(SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);
        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {

                String locatorType = sheet.getRow( i+ 1).getCell(k + 1).toString().trim();
                String locatorValue = sheet.getRow( i+ 1).getCell(k + 2).toString().trim();
                String action = sheet.getRow( i+ 1).getCell(k + 3).toString().trim();
                String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

                switch (action) {
                    case "open browser":
                        base = new Base();
                        prop = base.init_properties();
                        log.info("******Chrome Driver initialized******");
                        if (value.isEmpty() || value.equals("NA")) {
                            driver = Base.init_driver(prop.getProperty("browser"));
                        } else {
                            driver = Base.init_driver(value);
                        }
                        break;

                    case "enter url":
                        log.info("******Enter url******");
                        if (value.isEmpty() || value.equals("NA")) {
                            driver.get(prop.getProperty("url"));
                        } else {
                            driver.get(value);
                        }
                        break;

				case "quit":
					driver.close();
                    log.info("****** close driver******");
					break;
                    default:
                        break;
                }

                switch (locatorType) {
                    case "id" -> {
                        log.info("******enter id******");
                        element = driver.findElement(By.id(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                    }
                    case "cssSelector" -> {
                        log.info("******use cssSelector path******");
                        element = driver.findElement(By.cssSelector(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                    }
                    case "name" -> {
                        element = driver.findElement(By.name(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                    }
                    case "xpath" -> {
                        log.info("******use xpath******");
                        element = driver.findElement(By.xpath(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                    }
                    case "className" -> {
                        log.info("******use class name******");
                        element = driver.findElement(By.className(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                    }
                    case "linkText" -> {
                        log.info("****** use linktext xpath******");
                        element = driver.findElement(By.linkText(locatorValue));
                        element.click();
                    }
                    case "partialLinkText" -> {
                        log.info("******use partiallink text******");
                        element = driver.findElement(By.partialLinkText(locatorValue));
                        element.click();
                        locatorType = null;
                    }
                    default -> {
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }
}
