package com.localhost.login.pages;

import com.epam.healenium.annotation.PageAwareFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    protected WebDriver driver;

    @PageAwareFindBy(page="LoginPage", findBy = @FindBy(name="user"))
    public WebElement userName;

    @PageAwareFindBy(page="LoginPage", findBy = @FindBy(name="pass"))
    public WebElement password;

    @PageAwareFindBy(page="LoginPage", findBy = @FindBy(xpath = "//input[@value='Submit']"))
    public WebElement btnSubmit;

    @PageAwareFindBy(page="LoginPage", findBy = @FindBy(tagName = "h2"))
    public WebElement result;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUserName(String text) {
        userName.sendKeys(text);
    }

    public void setPassword(String text) {
        password.sendKeys(text);
    }

    public void clickButton() {
        if (btnSubmit.isEnabled()) {
            btnSubmit.click();
        }
    }

    public String getResult() {
        String res = result.getText();
        return res;
    }

}
