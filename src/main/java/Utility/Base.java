package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {
    protected static WebDriver driver;
    protected static Properties prop;
    protected static FileInputStream file;
    public static Logger log = LogManager.getLogger(Base.class);

    //Method : To Initialize the WebDriver
    public void init_Driver(String browserName) {


        if (browserName.equals("chrome")) {
            log.info("******Chrome Driver initialized******");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }

    //Metho: To Initialize the Property file.
    public void   initi_Properties() {

        prop=new Properties();
        try {
            file=new FileInputStream(System.getProperty("user.dir")+".\\src\\main\\resources\\Config.properties");
            prop.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
