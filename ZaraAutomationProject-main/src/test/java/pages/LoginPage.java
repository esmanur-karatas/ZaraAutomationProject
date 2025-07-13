package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;
import utils.Driver;
import utils.LoggerHelper;

public class LoginPage {

    WebDriver driver;
    Logger logger = LoggerHelper.getLogger(LoginPage.class);

    public LoginPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    //@FindBy(xpath = "//*[@id='onetrust-accept-btn-handler']")
    @FindBy(xpath = "//*[@id='onetrust-reject-all-handler']")
    public WebElement acceptCookies;

    // Login butonu - Ana sayfadaki kullanıcı simgesi
    @FindBy(xpath = "//*[@data-qa-id='layout-header-user-logon']")
    public WebElement firstLoginButton;

    // 2. login buttonu
    @FindBy(xpath = "//*[@*='oauth-logon-button']")
    public WebElement secondLoginButton;


    // E-posta girişi (Login formunda)
    @FindBy(xpath = "//*[@*='logonId']")
    public WebElement emailInput;

    // Şifre girişi
    @FindBy(xpath = "//*[@*='current-password']")
    public WebElement passwordInput;

    // Giriş Yap (submit) butonu
    @FindBy(xpath = "//*[@*='logon-form-submit']")
    public WebElement submitButton;

    // Login işlemi
    public void clickAcceptCookies() {
        acceptCookies.click();
    }


    public void login(String email, String password) throws InterruptedException {
//        logger.info("Login sayfasına erişiliyor...");
//        firstLoginButton.click();
//        Thread.sleep(6000);
//        secondLoginButton.click();
//
//        logger.info("E-posta giriliyor: " + email);
//        emailInput.sendKeys(email);
//
//        logger.info("Şifre giriliyor.");
//        passwordInput.sendKeys(password);
//        Thread.sleep(6000);
//        logger.info("Giriş butonuna tıklanıyor.");
//        submitButton.click();

        logger.info("Session temizleniyor...");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        driver.manage().deleteAllCookies();

        logger.info("Login sayfasına erişiliyor...");
        firstLoginButton.click();
        Thread.sleep(6000);
        secondLoginButton.click();

        logger.info("E-posta giriliyor: " + email);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        Thread.sleep(6000);
        logger.info("Giriş butonuna tıklanıyor.");
        submitButton.click();
    }
}
