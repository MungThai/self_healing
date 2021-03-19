package com.localhost.login.runners;

import com.localhost.login.helper.Screenshot;
import com.localhost.login.pages.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.localhost.login.stepdefs"},
        tags = "@Login",
        plugin = {"pretty",
        }
)

public class TestNGRunner extends AbstractTestNGCucumberTests {
    private final Logger logger = LogManager.getLogger( TestNGRunner.class );
    protected static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        logger.info("Setup ...");
        DriverFactory.getInstance().setUp();
        driver = DriverFactory.getInstance().getDriver();
    }

    @DataProvider(parallel = true)
    public Object[][] scenario() {
        return super.scenarios();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        logger.info("Tear down ...");
        if( ITestResult.FAILURE == result.getStatus()) {
            new Screenshot().screenShot(driver, result);
        }
        DriverFactory.getInstance().closeDriver();
    }
}
