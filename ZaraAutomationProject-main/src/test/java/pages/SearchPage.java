package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;
import utils.ExcelUtil;
import utils.LoggerHelper;
import org.apache.logging.log4j.Logger;

import javax.xml.xpath.XPath;
import java.util.List;
import java.util.Random;

public class SearchPage {

    WebDriver driver;
    Logger logger = LoggerHelper.getLogger(SearchPage.class);

    public SearchPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    // Arama kutusu
    @FindBy(xpath = "//span[text()='Ara']")
    public WebElement searchBox;

    @FindBy(id = "search-home-form-combo-input")
    private WebElement searchBoxInput;

    // Ürün
    @FindBy(xpath = "//*[@*='Zara Kahverengi / Köstebek - YAPRAK İŞLEMELİ GÖMLEK - 0 görseli']")
    public List<WebElement> productList;

    // Ürün Bilgi
    @FindBy(xpath = "//h1[@class='product-name']")
    public WebElement productName;

    // Ürün fiyatı
    @FindBy(xpath = "//span[@class='price__amount']")
    public WebElement productPrice;

    public void searchReplaceWithAnotherKeywordFromExcel(String path, String sheetName) {
        ExcelUtil excelUtil = new ExcelUtil(path, sheetName);

        String firstKeyword = excelUtil.getCellData(0, 0);
        String secondKeyword = excelUtil.getCellData(0, 1);

        // Arama kutusuna tıkla
        searchBox.click();

        // İlk kelimeyi yaz (şort)
        searchBoxInput.sendKeys(firstKeyword);
        logger.info("İlk arama kelimesi yazıldı: " + firstKeyword);

        // İçeriği temizle
        searchBoxInput.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        logger.info("Arama kutusu temizlendi.");

        // Yeni kelimeyi yaz (gömlek) ve Enter
        searchBoxInput.sendKeys(secondKeyword);
        searchBoxInput.sendKeys(Keys.ENTER);
        logger.info("İkinci arama kelimesi yazıldı ve Enter'a basıldı: " + secondKeyword);
    }

    public WebElement getRandomProduct() {
        List<WebElement> products = driver.findElements(By.xpath("//ul[@class='product-grid__product-list']//li"));
        Random rand = new Random();
        return products.get(rand.nextInt(products.size()));
    }

    // Ürün adını ve fiyatını al
    public String getSelectedProductInfo() {
        String name = productName.getText();
        String price = productPrice.getText();
        logger.info("Seçilen ürün adı: " + name);
        logger.info("Seçilen ürün fiyatı: " + price);
        return "Ürün: " + name + " - Fiyat: " + price;
    }
}
