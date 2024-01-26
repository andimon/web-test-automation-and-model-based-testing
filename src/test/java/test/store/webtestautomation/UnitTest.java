//package test.store.webtestautomation;
//
//
//import edu.um.cps3230.WebStoreOperator;
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
//
//    ProductsViewComponent productsViewComponent;
//    ProductDetailsComponent productDetailsComponent;
//    Duration waitTime = Duration.ofSeconds(2);
//
//    WebStoreOperator webStoreOperator;
//
//    @BeforeEach
//    public void setup() {
//        webDriver = new ChromeDriver();
//
//        // Got to google and disable cookies dialogue
//        webDriver.get("https://www.klikk.com.mt/");
//        // Maximise current window
//        webDriver.manage().window().maximize();
//        searchComponent = new NavigationComponent(webDriver);
//        productsViewComponent = new ProductsViewComponent(webDriver);
//        productDetailsComponent = new ProductDetailsComponent(webDriver);
//        webStoreOperator = new WebStoreOperator(webDriver);
//    }
//
//    @AfterEach
//    public void teardown() {
//    }
//
//    @Test
//    public void test() throws InterruptedException {
//        webStoreOperator.goToPurchasePage();
//    }
//
//
//}
