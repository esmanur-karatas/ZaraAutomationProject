package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = Logger.getLogger(ProductPage.class.getName());

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getProductsCount() {
        List<WebElement> products = driver.findElements(By.cssSelector("a.product-link._item.product-grid-product-info__name.link > h3"));
        return products.size();
    }

    public Product selectRandomProduct() {
        List<WebElement> productNames = driver.findElements(By.cssSelector("a.product-link._item.product-grid-product-info__name.link > h3"));
        List<WebElement> productPrices = driver.findElements(By.cssSelector("div.money-amount.price-formatted__price-amount > span.money-amount__main"));

        if (productNames.size() == 0 || productPrices.size() == 0) {
            logger.severe("Ürün veya fiyat bilgisi bulunamadı!");
            return null;
        }

        int randomIndex = new Random().nextInt(productNames.size());

        String selectedName = productNames.get(randomIndex).getText();
        String selectedPrice = productPrices.get(randomIndex).getText();

        logger.info("Seçilen ürün: " + selectedName + " | Fiyat: " + selectedPrice);

        // Ürüne tıkla
        productNames.get(randomIndex).click();

        // Ürün detay sayfasının yüklenmesini bekle
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-add-to-cart__button-icon")));

        return new Product(selectedName, selectedPrice);
    }

    public void addToCart() {
        WebElement addToCartBtn = driver.findElement(By.cssSelector(".product-add-to-cart__button-icon"));
        addToCartBtn.click();
        logger.info("Ürün sepete eklendi.");
    }

    // Ürün bilgilerini döndüren yardımcı metot
    public void logProductInfo(Product product) {
        if (product != null) {
            logger.info("Test - Ürün ismi: " + product.name);
            logger.info("Test - Ürün fiyatı: " + product.price);
        } else {
            logger.severe("Test - Ürün bilgisi bulunamadı!");
        }
    }

    public static class Product {
        public String name;
        public String price;

        public Product(String name, String price) {
            this.name = name;
            this.price = price;
        }
    }


}
