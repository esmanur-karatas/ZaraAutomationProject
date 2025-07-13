package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;
import utils.LoggerHelper;

public class HomePage {
    WebDriver driver;
    Logger logger = LoggerHelper.getLogger(HomePage.class);

    public HomePage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    // Menü butonu (hamburger menü)
    @FindBy(xpath = "//*[@id='theme-app']/div/div/header/button")
    private WebElement menuButton; //public değil private olucak hepsi

    // Erkek kategorisi
    @FindBy(xpath = "(//*[@class='layout-categories-category__link link'])[2]")
    private WebElement erkekKategori;

    // “Tümünü Gör” butonu
    @FindBy(xpath = "//*[@id=\"theme-app\"]/div/div/div[1]/div/div[2]/nav/div[2]/div/div/div/div[2]/ul/li[4]/div/ul/li[2]/a/span")
    private WebElement tumunuGorButonu;

    // Menüden Erkek → Tümünü Gör’e git
    public void navigateToErkekTumunuGor() {
        menuButton.click(); //yorum satırına alınmış kaldırıldı ayrıca önce clicklenip sonra log alınır yoksa çalışmıyor
        logger.info("Ana menü butonuna tıklanıyor.");

        erkekKategori.click();
        logger.info("Erkek kategorisine tıklanıyor.");

        tumunuGorButonu.click();
        logger.info("Tümünü Gör butonuna tıklanıyor.");

    }
}

