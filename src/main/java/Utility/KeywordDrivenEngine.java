package Utility;


import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class KeywordDrivenEngine extends Base{
    private Workbook workbook;
    public Sheet sheet;
    public FileInputStream excelFile;
    public String locatorName = null;
    private String locatorValue = null;
    public String action;
    public String value;
    public Base base;

    public final String FILE_PATH = "C:\\Users\\OMKAR BADE\\IdeaProjects\\Keyword_Driven_Framework\\src\\main\\resources\\LoginTestKeywordData.xlsx";


    public void startExecution(String sheetName) {

        try {
            log.info("******take the excel file data******");
            excelFile = new FileInputStream(FILE_PATH);
            try {
                workbook = WorkbookFactory.create(excelFile);
            } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {

                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Take Excel file sheet into Sheet Class.
        sheet = workbook.getSheet("LoginTestData");
        int k = 0;
        System.out.println(sheet.getLastRowNum());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {

                String data = sheet.getRow(i + 1).getCell(k + 1).getStringCellValue().trim();
                System.out.println("Data:" + data);
                if (!data.equalsIgnoreCase("NA")) {
                    locatorName = data.split("=")[0].trim();
                    System.out.println("LocatorName: " + locatorName);
                    locatorValue = data.split("=")[1].trim();
                    System.out.println("LocatorValue: " + locatorValue);
                }

                action = sheet.getRow(i + 1).getCell(k + 2).getStringCellValue().trim();
                System.out.println("Action:" + action);
                value = sheet.getRow(i + 1).getCell(k + 3).getStringCellValue().trim();
                System.out.println("Value:" + value);

                switch (action) {
                    case "open browser":
                        base = new Base();
                        base.initi_Properties();
                        if (value.isEmpty() || value.equals("NA")) {
                            prop.getProperty("browser");
                        } else {
                            base.init_Driver(value);
                        }

                        break;

                    case "enter url":
                        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        if (value.isEmpty() || value.equals("NA")) {
                            prop.getProperty("url");
                        } else {
                            driver.get(value);
                        }

                        break;

                    case "sendkeys":

                        WebElement element1 = driver.findElement(By.xpath(locatorValue));
                        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        element1.clear();
                        element1.sendKeys(value);
                        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        break;

                    case "click":
                        WebElement element2 = driver.findElement(By.xpath(locatorValue));
                        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        element2.click();
                        Thread.sleep(5000);
                        break;
                    case "quit":
                        log.info("******Close browser******");
                        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        driver.quit();
                        break;

                    default:
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
