package tests;

import base.BaseTest;
import org.junit.jupiter.api.*;
import pages.*;

import static org.junit.jupiter.api.Assertions.*;

public class ZaraTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    SearchPage searchPage;
    ProductPage productPage;
    CartPage cartPage;

    @Test
    public void testZaraShoppingFlow() throws InterruptedException {

        // 1. Siteye giriş ve login
        loginPage = new LoginPage();
        loginPage.acceptCookies.click();
        //loginPage.login(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));

        // 2. Menüden Erkek > Tümünü Gör
        homePage = new HomePage();
        homePage.navigateToErkekTumunuGor();
        Thread.sleep(6000); // dinamik bekleme tavsiye edilir

        // 3. Excel’den arama yapılacak kelime alınır ve arama yapılır
        searchPage = new SearchPage();
        String excelPath = "C:/Users/Huawei/Desktop/ZaraAutomationProject-main/testdata.xlsx";
        searchPage.searchReplaceWithAnotherKeywordFromExcel(excelPath, "Sayfa1");

        // 4. Rastgele bir ürün seçilir, fiyat ve isim alınır
        productPage = new ProductPage(driver);
        Thread.sleep(3000);
        ProductPage.Product selectedProduct = productPage.selectRandomProduct();
        Thread.sleep(3000);
        productPage.logProductInfo(selectedProduct);

        // 5. Ürün sepete eklenir
        if (selectedProduct != null) {
            productPage.addToCart();
        } else {
            fail("Ürün seçilemedi. Test sonlandırıldı.");
        }

        // 6. Sepete gidilir
        cartPage = new CartPage();
        cartPage.clickAddButton();
        Thread.sleep(1000);
        productPage.addToCart();
        Thread.sleep(6000);

        cartPage.goToCart();
        Thread.sleep(3000); // sepet sayfasının yüklenmesi bekleniyor

        // Sepet fiyatı normalize edilerek karşılaştırılır
        String cartPrice = cartPage.getCartPrice().replaceAll("[^0-9]", "");
        String selectedPrice = selectedProduct.price.replaceAll("[^0-9]", "");

        assertEquals(selectedPrice, cartPrice, "Sepetteki fiyat ürün sayfasıyla uyuşmuyor!");

        cartPage.increaseQuantityToTwo();
        Thread.sleep(2000);
        assertTrue(cartPage.isQuantityTwo(), "Ürün adedi 2 değil!");

        cartPage.deleteProduct();
        Thread.sleep(2000);
        assertTrue(cartPage.isCartEmpty(), "Sepet boş değil!");

        System.out.println("Test başarıyla tamamlandı.");


    }
}
