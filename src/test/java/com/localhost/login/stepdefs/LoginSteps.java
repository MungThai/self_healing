package com.localhost.login.stepdefs;

import com.localhost.login.pages.DriverFactory;
import com.localhost.login.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {
    protected WebDriver driver;
    private LoginPage loginPage;

    @Given("Launches login page")
    public void openBrowser() {
        this.driver = DriverFactory.getInstance().getDriver();
        loginPage = new LoginPage(driver);
    }

    @When("set username {string} and password {string}")
    public void login(String userName, String password) {
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
    }

    @When("click on Login button")
    public void clickButton() {
        loginPage.clickButton();
    }


    @Then("I expect Welcome")
    public void verify() {
        Assert.assertEquals("Welcome, mung! Logout", loginPage.getResult());
    }

}
