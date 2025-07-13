package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.ConfigReader;
import utils.Driver;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {

        driver = Driver.getDriver(); // Tek bir tarayıcı örneği oluşturur
        driver.get(ConfigReader.getProperty("base_url")); // URL’yi config'den alır
        System.out.println("Tarayıcı başlatıldı: " + ConfigReader.getProperty("base_url"));


    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies(); // Çerez temizliği
            driver.quit(); // Tarayıcıyı kapat
            System.out.println("Tarayıcı kapatıldı.");
        }
    }
}

