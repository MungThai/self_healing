package com.localhost.login.runners;

import com.localhost.login.pages.DriverFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

// command line: mvn clean test -Dtest=com.localhost.login.runners.JunitRunner
// or: mvn clean test -Dtest=JunitRunner

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.localhost.login.stepdefs"},
        tags = "@Login",
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "junit:target/cucumber-reports/cucumber.xml"
        }
)

public class JunitRunner {
    private static final Logger logger = LogManager.getLogger( JunitRunner.class );
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        logger.info("Setup ...");
        DriverFactory.getInstance().setUp();
        driver = DriverFactory.getInstance().getDriver();
        logger.info("Initialize driver");
    }


    @AfterClass
    public static void tearDown() throws Exception
    {
        logger.info("Tear down ...");
        DriverFactory.getInstance().closeDriver();
        logger.info("Close driver");
    }
}
