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
import org.titanium.reports.BaseClass;
import org.titanium.reports.JyperionListener;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


@Listeners(JyperionListener.class)
public class LogInFacebook extends BaseClass {

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

    @DataProvider(name = "SearchProvider")
    public Object [][] getDataFromDataProvider(Method m) {

        if(m.getName().equals("LoginLogout")){
            return new Object[][]{
                    {"m_sareth_8@hotmail.com", "sarethithaa1"},
            };
        }else if(m.getName().equals("userCpassI")){
            return new Object[][]{
                    {"m_sareth_8@hotmail.com", "123456789"},
            };
        }else if(m.getName().equals("UserIpassI")){
            return new Object[][]{
                    {"eme_sareth@gmail.com", "123456789"},
            };
        }else if(m.getName().equals("UserBpassC")){
            return new Object[][]{
                    {"", "sarethithaa1"},
            };
        }else if(m.getName().equals("UserBpassI")){
            return new Object[][]{
                    {"", "123456789"},
            };
        }else if(m.getName().equals("UserBpassB")){
            return new Object[][]{
                    {"", ""},
            };
        }else if(m.getName().equals("UserIpassC")){
            return new Object[][]{
                    {"eme_sareth@gmail.com", ""},
            };
        }else{
            return new Object[][]{
                    {"m_sareth_8@hotmail.com", ""},
            };
        }

    }

//Tests
    @Test(dataProvider = "SearchProvider")
    public void LoginLogout(String email, String pass) throws AWTException, InterruptedException {
        loginpage = new LogInPage(driver);
        homepage = new homePage(driver);

        loginpage.setEmail(email);
        loginpage.setPass(pass);
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
    @Test(dataProvider = "SearchProvider")
    public void userCpassI(String email, String pass)  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail(email);
        loginpage.setPass(pass);
        loginpage.clickOnSubmit();

        expectedResult = "La contraseña que ingresaste es incorrecta. ¿Olvidaste tu contraseña?";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");

    }

    //Usuario incorrecto, contraseña incorrecta
   @Test(dataProvider = "SearchProvider")
    public void UserIpassI(String email, String pass)  {
        loginpage = new LogInPage(driver);
        loginpage.setEmail(email);
        loginpage.setPass(pass);
        loginpage.clickOnSubmit();

        expectedResult = "El correo electrónico que ingresaste no coincide con ninguna cuenta. Regístrate para crear una cuenta.";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario incorrecto, contraseña correcta
    @Test(dataProvider = "SearchProvider")
    public void UserIpassC(String email, String pass)  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail(email);
        loginpage.setPass(pass);
        loginpage.clickOnSubmit();

        expectedResult = "El correo electrónico que ingresaste no coincide con ninguna cuenta. Regístrate para crear una cuenta.";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario en blanco, contraseña correcta
    @Test(dataProvider = "SearchProvider")
    public void UserBpassC(String email, String pass)  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail(email);
        loginpage.setPass(pass);
        loginpage.clickOnSubmit();

        expectedResult = "El correo electrónico o el número de teléfono que ingresaste no coinciden con ninguna cuenta. Regístrate para crear una cuenta.";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario blanco, contraseña incorrecta
    @Test(dataProvider = "SearchProvider")
    public void UserBpassI(String email, String pass)  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail(email);
        loginpage.setPass(pass);
        loginpage.clickOnSubmit();

        expectedResult = "El correo electrónico o el número de teléfono que ingresaste no coinciden con ninguna cuenta. Regístrate para crear una cuenta.";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario en blanco, contraseña en blanco
    @Test(dataProvider = "SearchProvider")
    public void UserBpassB(String email, String pass)  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail(email);
        loginpage.setPass(pass);
        loginpage.clickOnSubmit();

        expectedResult = "El correo electrónico o el número de teléfono que ingresaste no coinciden con ninguna cuenta. Regístrate para crear una cuenta.";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");


    }

    //Usuario correcto, Password en blanco
    @Test(dataProvider = "SearchProvider")
    public void userCpassB(String email, String pass)  {
        loginpage = new LogInPage(driver);

        loginpage.setEmail(email);
        loginpage.setPass(pass);
        loginpage.clickOnSubmit();

        expectedResult = "La contraseña que ingresaste es incorrecta. ¿Olvidaste tu contraseña?";
        actualResult = loginpage.warningMess();
        Assert.assertEquals(actualResult,expectedResult, "Test no pasó, error en los datos");

    }
}
