package test.store;


import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.store.pageobjects.ProductsViewComponent;
import test.store.pageobjects.SearchComponent;
import test.store.pageobjects.Section;
import test.store.pageobjects.SectionsComponent;

import java.time.Duration;

public class UnitTest {
    WebDriver webDriver;
    SearchComponent searchComponent;

    SectionsComponent sectionsComponent;

    ProductsViewComponent productsViewComponent;
    Duration waitTime = Duration.ofSeconds(2);

    @BeforeEach
    public void setup() {
        webDriver = new ChromeDriver();

        // Got to google and disable cookies dialogue
        webDriver.get("https://www.klikk.com.mt");
        // Maximise current window
        webDriver.manage().window().maximize();
        searchComponent = new SearchComponent(webDriver);
        sectionsComponent = new SectionsComponent(webDriver);
        productsViewComponent = new ProductsViewComponent(webDriver);
    }

    @AfterEach
    public void teardown() {
        webDriver.quit();
    }

    @Test
    public void test() {
        searchComponent.search("phone");
        Assertions.assertEquals(400, productsViewComponent.getNumberOfProductsInPage());
        int x = 1;
    }

    @Test
    public void test1(){
        sectionsComponent.clickSectionById(Section.COMPUTING);
        int x = 1;

    }


    @Test
    public void search() {
    }
}
