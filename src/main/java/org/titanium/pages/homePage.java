package org.titanium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {
    WebDriver driver;

    @FindBy (xpath = "//div[@class='linkWrap noCount']")
    WebElement userName;

    @FindBy(id = "logoutMenu")
    WebElement logoutMenu;

    @FindBy (xpath = "//li[@class='_54ni navSubmenu _6398 _64kz __MenuItem']")
    WebElement salir;



    public homePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String UserName(){
        String a;
        a=userName.getText();
        return a;
    }

    public void logout(){
        logoutMenu.click();
    }
    public void Salir(){salir.click();}

}
