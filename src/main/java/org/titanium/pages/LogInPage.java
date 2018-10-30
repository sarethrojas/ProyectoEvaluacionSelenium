package org.titanium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LogInPage {

    WebDriver driver;

    @FindBy(name = "email")
    WebElement txtEmail;

    @FindBy(name = "pass")
    WebElement txtPass;

    @FindBy(id = "loginbutton")
    WebElement btnSubmit;

    @FindBy(xpath = "//div[@class='_4rbf _53ij']")
    WebElement warningMessage;


    public LogInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setEmail(String email){
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setPass(String pass){
        txtPass.sendKeys(pass);
    }

    public String Password(){
        String a;
        a=txtPass.getText();
        return a;
    }


    public void clickOnSubmit(){
        btnSubmit.click();
    }

    public String warningMess(){
        String a;
        a=warningMessage.getText();
        return a;
    }

}
