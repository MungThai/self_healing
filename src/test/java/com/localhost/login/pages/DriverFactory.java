package com.localhost.login.pages;

import com.epam.healenium.SelfHealingDriver;
import com.localhost.login.utils.Utilities;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private final Logger logger = LogManager.getLogger(this.getClass());

    int TIME_OUT = 10;
    Utilities utils = new Utilities();

    private static final DriverFactory instance = new DriverFactory();

//    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public SelfHealingDriver driver;
    private WebDriver webDriver;

    private DriverFactory() {}

    public static DriverFactory getInstance() {
        return instance;
    }
    public  WebDriver getDriver() {
        return driver;
    }

    public void setUp() {
        initDriver();
      //  driver.set(webDriver);
        Config healeniumConfig = ConfigFactory.load("healenium.properties");

        driver = SelfHealingDriver.create(webDriver, healeniumConfig);
    }

    public void initDriver() {
        logger.info("Initial WebDriver");
        String runType = utils.getProperties("runType");

        switch (runType.toUpperCase()) {
            case "CHROME":
                runChrome();
                break;
            case "FIREFOX":
                runFireFox();
                break;
            default:
                throw new IllegalArgumentException("Run type '" + runType + "' isn't supported." );

        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
    }


    protected void runChrome() {
        logger.info("Run Chrome driver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);

        String OS = System.getProperty("os.name").toLowerCase();
/*
        if( OS.contains("linux") ) {
           System.out.println("==== Linux ======");
           chromeOptions.addArguments("--headless");
        }
*/
    //    chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--allowed-ips","--ignore-certificate-errors","--disable-web-security", "--allow-running-insecure-content");
  //      chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-blink-features");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

        webDriver = new ChromeDriver(chromeOptions);

        webDriver.get(utils.getProperties("webUrl"));
    }

    private void runFireFox() {
        logger.info("Run Firefox driver");
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get(utils.getProperties("webUrl"));
    }

    public void closeDriver() {
        driver.quit();
    }
}
