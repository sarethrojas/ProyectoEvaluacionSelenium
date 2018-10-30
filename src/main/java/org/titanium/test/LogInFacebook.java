package org.titanium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.*;
import org.titanium.pages.LogInPage;
import org.titanium.pages.homePage;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class LogInFacebook {

    WebDriver driver;
    LogInPage loginpage;
    homePage homepage;
    String expectedResult = "";
    String actualResult = "";
    String baseURL = "https://www.facebook.com/";
    String chromePath = System.getProperty("user.dir") + "/Drivers/chromedriver.exe";
    WebDriverWait wait;


    @BeforeMethod
    public void launchBrowser(){

        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
        wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterMethod
    public  void closeB(){
        driver.close();
    }

    @Test(priority = 0)
    public void happyPath() throws AWTException, InterruptedException {
        loginpage = new LogInPage(driver);
        homepage = new homePage(driver);

        loginpage.setEmail("m_sareth_8@hotmail.com");
        loginpage.setPass("sarethithaa1");
        loginpage.clickOnSubmit();

        Thread.sleep(1500);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2500);

        actualResult=homepage.UserName();
        expectedResult="Sary Avila";
        Assert.assertEquals(actualResult,expectedResult, "Falló inicio de sesión");

        homepage.logout();
        Thread.sleep(2500);
        homepage.Salir();
        Thread.sleep(2500);

        actualResult= loginpage.Password();
        expectedResult="";
        Assert.assertEquals(actualResult,expectedResult, "Falló logOut");

    }

    //Usuario correcto, Password incorrecto
    @Test
    public void userCpassI()  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail("m_sareth_8@hotmail.com");
        loginpage.setPass("123456789");
        loginpage.clickOnSubmit();
        expectedResult = "La contraseña que ingresaste es incorrecta. ¿Olvidaste tu contraseña?";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");

    }

    //Usuario incorrecto, contraseña incorrecta
   @Test
    public void UserIpassI()  {
        loginpage = new LogInPage(driver);
        loginpage.setEmail("eme_sareth@gmail.com");
        loginpage.setPass("123456789");
        loginpage.clickOnSubmit();
       expectedResult = "El correo electrónico que ingresaste no coincide con ninguna cuenta. Regístrate para crear una cuenta.";
       actualResult = loginpage.warningMess();
       Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario incorrecto, contraseña correcta
    @Test
    public void UserIpassC()  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail("eme_sareth@gmail.com");
        loginpage.setPass("sarethithaa1");
        loginpage.clickOnSubmit();
        expectedResult = "El correo electrónico que ingresaste no coincide con ninguna cuenta. Regístrate para crear una cuenta.";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario en blanco, contraseña correcta
    @Test
    public void UserBpassC()  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail("");
        loginpage.setPass("sarethithaa1");
        loginpage.clickOnSubmit();
        expectedResult = "El correo electrónico o el número de teléfono que ingresaste no coinciden con ninguna cuenta. Regístrate para crear una cuenta.";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario blanco, contraseña incorrecta
    @Test
    public void UserBpassI()  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail("");
        loginpage.setPass("123456789");
        loginpage.clickOnSubmit();
        expectedResult = "El correo electrónico o el número de teléfono que ingresaste no coinciden con ninguna cuenta. Regístrate para crear una cuenta.";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario en blanco, contraseña en blanco
    @Test
    public void UserBpassB()  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail("");
        loginpage.setPass("");
        loginpage.clickOnSubmit();
        expectedResult = "El correo electrónico o el número de teléfono que ingresaste no coinciden con ninguna cuenta. Regístrate para crear una cuenta.";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario correcto, Password en blanco
    @Test
    public void userCpassB()  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail("m_sareth_8@hotmail.com");
        loginpage.setPass("");
        loginpage.clickOnSubmit();
        expectedResult = "La contraseña que ingresaste es incorrecta. ¿Olvidaste tu contraseña?";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");

    }
}