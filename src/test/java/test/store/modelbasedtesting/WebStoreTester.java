package test.store.modelbasedtesting;

import edu.um.cps3230.WebStoreOperator;
import edu.um.cps3230.pageobjects.NavigationComponent;
import edu.um.cps3230.pageobjects.StockStatus;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class WebStoreTester implements FsmModel {
    WebDriver webDriver = new ChromeDriver();
    WebStoreOperator systemUnderTest;

    NavigationComponent navigationComponent;

    // Initial state
    CurrentPage currentPage = CurrentPage.HOME_PAGE;

    // Initial state variables
    boolean isCartEmpty = true;
    boolean productsInPage = false;
    boolean inStock = false;
    boolean loggedIn = false;

    @Override
    public Object getState() {
        return currentPage;
    }

    @Override
    public void reset(boolean testing) {
        //update state and state variables
        currentPage = CurrentPage.HOME_PAGE;
        isCartEmpty = true;
        productsInPage = false;
        inStock = false;
        loggedIn = false;
        //reset SUT
        if (testing) {
            //quit webdriver
            webDriver.quit();
            //open a new web driver
            webDriver = new ChromeDriver();
            systemUnderTest = new WebStoreOperator(webDriver);
            navigationComponent = new NavigationComponent(webDriver);
        }
    }

//    public boolean initiateWebStoreGuard() {
//        return currentPage == CurrentPage.HOME_PAGE;
//    }

//    public @Action void initiateWebStore() {
//        //open web store and set current state to homepage
//        systemUnderTest = new WebStoreOperator(webDriver);
//        currentPage = CurrentPage.HOME_PAGE;
//        productsInPage = true;
//    }

    public @Action void searchForPhone() {
        //search for phone
        systemUnderTest.searchProduct("phone");
//        currentPage = CurrentPage.PRODUCTS_PAGE;
        //assert that current product page is not empty
        if (!systemUnderTest.isProductPageEmpty())
            productsInPage = false;
    }


    public boolean returnToHomePageGuard() {
        //check that current state is not the home page
        return currentPage != CurrentPage.HOME_PAGE;
    }

    public @Action void returnToHomePage() {
        systemUnderTest.returnToHome();
        currentPage = CurrentPage.HOME_PAGE;
        productsInPage = false;
        inStock = false;
    }

    public boolean addToCartGuard() {
        //current state is product details page
        boolean currentState = currentPage == CurrentPage.PRODUCT_DETAILS_PAGE;
        //current state variables (f,f,y) or (t,f,f) or (t,f,t)
        // i.e. we check that productsInPage is false
        boolean stateVariables = !productsInPage;
        return currentState && stateVariables;
    }

    public @Action void addToCart() {
        //guard => productsInPage is false
        if (isCartEmpty && inStock) {
            //product in stock so it can be added to cart
            addToCart();
            //cart is not empty anymore
            isCartEmpty = false;
        } else if (!isCartEmpty && inStock) {
            //product in stock so it can be added to cart
            addToCart();
            //cart is already not empty
        } else if (!isCartEmpty && !inStock) {
            //product not in stock so it cannot be added to cart
            //cart remains not empty
        } else {
            //product not in stock so it cannot be added to cart
            //cart remains empty
        }
    }


    public boolean selectFirstProductGuard() {
        boolean currentStateCheck = currentPage == CurrentPage.PRODUCTS_VIEW_PAGE;
        //productsInPage should be true and inStock should be false
        boolean stateVariableCheck = productsInPage && !inStock;
        return currentStateCheck && stateVariableCheck;
    }

    public @Action void selectFirstProduct() {
        //select the first product
        systemUnderTest.selectFirstProduct();
        //set current state
        currentPage = CurrentPage.PRODUCT_DETAILS_PAGE;
        //set state variables
        //page has no selectable products
        productsInPage = false;
        //inStock true if product is in stock, else false
        inStock = systemUnderTest.getStockStatus() == StockStatus.IN_STOCK;
    }

    public boolean clearCartGuard() {
        return currentPage == CurrentPage.PURCHASE_PAGE && !isCartEmpty && !productsInPage && !inStock;
    }

    public @Action void clearCart() {
        //action
        systemUnderTest.clearCart();
        //state remains the same
        //update state variable
        isCartEmpty = true;
    }

    public boolean goToPurchasePageGuard() {
        return currentPage != CurrentPage.PURCHASE_PAGE;
    }

    public @Action void goToPurchasePage() {
        //action
        systemUnderTest.goToPurchasePage();
        //update state
        currentPage = CurrentPage.PURCHASE_PAGE;
        //update state variables
        productsInPage = false;
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
        return loggedIn;
    }

    public @Action void logout() {
        systemUnderTest.logout();
        loggedIn = false;
    }


    @Test
    public void WebStoreTesterRunner() {
        final GreedyTester tester = new GreedyTester(new WebStoreTester());

        tester.setRandom(new Random());

        tester.buildGraph();

        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");

        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());

        tester.generate(100);
        tester.printCoverage();
    }
}
