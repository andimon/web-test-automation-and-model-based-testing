package test.store.modelbasedtesting;

import edu.um.cps3230.WebStoreOperator;
import edu.um.cps3230.pageobjects.NavigationComponent;
import edu.um.cps3230.pageobjects.StockStatus;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class TestWebStoreTester implements FsmModel {

    private final Random random = new Random();
    private final int testSequenceLength = 1500;
    private static WebDriver webDriver = new ChromeDriver();
    WebStoreOperator systemUnderTest = new WebStoreOperator(webDriver);

    // State and state variables
    CurrentPage currentPage;
    boolean isCartEmpty, inStock, loggedIn;
    int productsInPage;

    @Override
    public Object getState() {
        return currentPage;
    }

    @AfterAll
    public static void teardown() {
        webDriver.quit();
    }

    @Override
    public void reset(boolean testing) {
        //update state and state variables
        webDriver.quit();
        currentPage = CurrentPage.HOME_PAGE;
        isCartEmpty = true;
        productsInPage = 0;
        inStock = false;
        loggedIn = false;
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.klikk.com.mt");
        systemUnderTest = new WebStoreOperator(webDriver);
        //reset SUT
        if (testing) {
            systemUnderTest = new WebStoreOperator(webDriver);
        }
    }


    public @Action void searchForPhone() {
        //action
        systemUnderTest.searchProduct("phone");
        //update state and state variables
        currentPage = CurrentPage.PRODUCTS_VIEW_PAGE;
        productsInPage = systemUnderTest.getNumberOfProducts();
        inStock = false;
    }


    public boolean returnToHomePageGuard() {
        //check expected current state and variables
        return currentPage != CurrentPage.HOME_PAGE;
    }

    public @Action void returnToHomePage() {
        //action
        systemUnderTest.returnToHome();
        //update state and state variables
        currentPage = CurrentPage.HOME_PAGE;
        productsInPage = 0;
        inStock = false;
    }

    public boolean addToCartGuard() {
        //check expected current state and variables
        return (currentPage == CurrentPage.PRODUCT_DETAILS_PAGE) && (productsInPage == 0) && inStock;
    }

    public @Action void addToCart() {
        //action
        systemUnderTest.addToCart();
        //update state and state variables
        isCartEmpty = false;
    }


    public boolean selectProductGuard() {
        //check expected current state and variables
        return (currentPage == CurrentPage.PRODUCTS_VIEW_PAGE) && (productsInPage > 0) && (!inStock);
    }

    public @Action void selectProduct() {
        //action
        systemUnderTest.selectProduct(random.nextInt(productsInPage));
        //update state and state variables
        currentPage = CurrentPage.PRODUCT_DETAILS_PAGE;
        productsInPage = 0;
        inStock = systemUnderTest.getStockStatus() == StockStatus.IN_STOCK;
    }

    public boolean clearCartGuard() {
        //check expected current state and variables
        return currentPage == CurrentPage.PURCHASE_PAGE && !isCartEmpty && (productsInPage == 0) && !inStock;
    }

    public @Action void clearCart() {
        //action
        systemUnderTest.clearCart();
        //update state and state variables
        isCartEmpty = true;
    }

    public boolean goToPurchasePageGuard() {
        return currentPage != CurrentPage.PURCHASE_PAGE;
    }

    public @Action void goToPurchasePage() {
        //action
        systemUnderTest.goToPurchasePage();
        //update state and state variables
        currentPage = CurrentPage.PURCHASE_PAGE;
        productsInPage = 0;
        inStock = false;
    }

    public boolean loginGuard() {
        return !loggedIn;
    }

    public @Action void login() {
        systemUnderTest.login("katijik879@grassdev.com", "test123!");
        loggedIn = true;
    }

    public boolean logoutGuard() {
        return currentPage != CurrentPage.PRODUCT_DETAILS_PAGE && loggedIn;
    }

    public @Action void logout() {
        systemUnderTest.logout();
        loggedIn = false;
        currentPage = CurrentPage.HOME_PAGE;
    }

    @Test
    public void WebStoreTesterRunnerUsingGreedyTester() {
        final Tester tester = new GreedyTester(new TestWebStoreTester());
        tester.setRandom(new Random());
        tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(testSequenceLength);
        tester.printCoverage();
    }

    @Test
    public void WebStoreTesterRunnerUsingRandomTester() {
        final Tester tester = new RandomTester(new TestWebStoreTester());
        tester.setRandom(new Random());
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(testSequenceLength);
        tester.printCoverage();
    }
}

