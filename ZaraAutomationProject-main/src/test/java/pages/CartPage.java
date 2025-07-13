package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;
import utils.LoggerHelper;
import org.apache.logging.log4j.Logger;

public class CartPage {

    WebDriver driver;
    Logger logger = LoggerHelper.getLogger(CartPage.class);

    public CartPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product-detail-cart-buttons__main-action")
    private WebElement addButton;

    // Sepete git simgesi (genelde header'da olur)
    @FindBy(xpath = "//a[@data-qa-id='layout-header-go-to-cart']")
    private WebElement cartTextButton;

    // Sepetteki ürün fiyatı
    @FindBy(className = "shop-cart-item-pricing__current")
    public WebElement cartPrice;

    // Adet artır butonu
    @FindBy(xpath = "(//*[@*='add-order-item-unit'])[1]")
    public WebElement increaseQuantityButton;

    // Ürün adedi gösterimi
    @FindBy(xpath = "(//*[@*='zds-quantity-selector__units shop-cart-item-quantity'])[1]")
    public WebElement quantityInput;

    // Ürünü sil butonu
    @FindBy(xpath = "(//*[@*='zds-quantity-selector__icon'])[1]")
    public WebElement deleteButton;

    // Sepetin boş olduğunu gösteren mesaj
    @FindBy(xpath = "//*[@*='zds-empty-state__title']")
    public WebElement emptyCartMessage;

    // Sepete git
    public void goToCart() {
        cartTextButton.click();
        logger.info("Sepet simgesine tıklanıyor.");
    }

    public void clickAddButton(){
        addButton.click();
    }
    // Sepetteki fiyatı al
    public String getCartPrice() {
        String price = cartPrice.getText();
        logger.info("Sepetteki fiyat: " + price);
        return price;
    }

    // Ürün adedini 2 yap
    public void increaseQuantityToTwo() {
        logger.info("Adet artırma butonuna tıklanıyor.");
        increaseQuantityButton.click();
    }

    // Adedin 2 olduğunu doğrula
    public boolean isQuantityTwo() {
        String value = quantityInput.getAttribute("value");
        logger.info("Ürün adedi: " + value);
        return value.equals("2");
    }

    public void deleteProduct() {
        try {
            logger.info("Ürün siliniyor.");
            while (deleteButton.isDisplayed()) {
                deleteButton.click();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            logger.warn("Ürün silme işlemi sırasında hata oluştu veya ürün zaten silinmiş olabilir.");
        }
    }

    // Sepetin boş olduğunu kontrol et
    public boolean isCartEmpty() {
        try {
            boolean empty = emptyCartMessage.isDisplayed();
            logger.info("Sepet boş mu? " + empty);
            return empty;
        } catch (NoSuchElementException e) {
            logger.warn("Sepet boş mesajı bulunamadı.");
            return false;
        }
    }
}
