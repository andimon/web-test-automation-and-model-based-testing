//package test.store.webtestautomation;
//
//
//import edu.um.cps3230.pageobjects.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.time.Duration;
//
//public class UnitTest {
//    WebDriver webDriver;
//    NavigationComponent searchComponent;
//
//    SectionsComponent sectionsComponent;
//
//    ProductsViewComponent productsViewComponent;
//    ProductDetailsComponent productDetailsComponent;
//    Duration waitTime = Duration.ofSeconds(2);
//
//    @BeforeEach
//    public void setup() {
//        webDriver = new ChromeDriver();
//
//        // Got to google and disable cookies dialogue
//        webDriver.get("https://www.klikk.com.mt/product/30248_xiaomi-pocophone-poco-c65-4g-256gb--8gb-android-dual-sim-smartphone-black");
//        // Maximise current window
//        webDriver.manage().window().maximize();
//        searchComponent = new NavigationComponent(webDriver);
//        sectionsComponent = new SectionsComponent(webDriver);
//        productsViewComponent = new ProductsViewComponent(webDriver);
//        productDetailsComponent = new ProductDetailsComponent(webDriver);
//    }
//
//    @AfterEach
//    public void teardown() {
//        webDriver.quit();
//    }
//
//    @Test
//    public void test() {
//        Assertions.assertEquals("wow",productDetailsComponent.stockStatus());
//    }
//
//    @Test
//    public void test1(){
//        sectionsComponent.clickSectionById(Category.COMPUTING);
//        int x = 1;
//
//    }
//
//
//    @Test
//    public void search() {
//    }
//}
