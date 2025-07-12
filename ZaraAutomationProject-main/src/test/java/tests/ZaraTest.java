package tests;

import base.BaseTest;
import org.junit.jupiter.api.*;
import pages.*;



public class ZaraTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    SearchPage searchPage;
    ProductPage productPage;
    CartPage cartPage;


    @Test
    public void testZaraShoppingFlow() throws InterruptedException {

        // 1. www.zara.com/tr/ sitesi açılır → BaseTest ile açıldı

        // 2. Login olunur
        loginPage = new LoginPage();
        loginPage.acceptCookies.click();
        //loginPage.login(ConfigReader.getProperty("email"),ConfigReader.getProperty("password"));
        //  Thread.sleep(6000);
        // 3. Menü → Erkek → Tümünü Gör
        homePage = new HomePage();
        homePage.navigateToErkekTumunuGor();

        Thread.sleep(6000);
//ARAMA
        searchPage = new SearchPage();
        String excelPath = "C:/Users/Huawei/Desktop/ZaraAutomationProject-main/testdata.xlsx";
        searchPage.searchReplaceWithAnotherKeywordFromExcel(excelPath, "Sayfa1");

        //ÜRÜN
        productPage = new ProductPage(driver);
        ProductPage.Product selectedProduct = productPage.selectRandomProduct();

        productPage.logProductInfo(selectedProduct);

        if (selectedProduct != null) {
            productPage.addToCart();
        }

    }
}
