package test.store.modelbasedtesting;

import edu.um.cps3230.WebStoreOperator;
import edu.um.cps3230.pageobjects.ProductsViewComponent;
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
    WebStoreOperator systemUnderTest = new WebStoreOperator(webDriver);

    // currentPage represents a state
    CurrentPage currentPage = CurrentPage.HOME_PAGE;


    // variables

    boolean isCartEmpty = true;
    boolean isProductPageEmpty = true;





    @Override
    public Object getState() {
        return currentPage;
    }

    @Override
    public void reset(boolean testing) {
        if (testing) {
            //quit webdriver
            webDriver.quit();
            //open new webdriver
            webDriver = new ChromeDriver();
            //reset sut
            systemUnderTest = new WebStoreOperator(webDriver);
            //open web store
            systemUnderTest.initiateWebStore();
            //set initial state
            currentPage = CurrentPage.HOME_PAGE;
        }
    }

    public boolean initiateWebStoreGuard() {
        return currentPage == CurrentPage.HOME_PAGE;
    }

    public @Action void initiateWebStore() {
        //open web store and set current state to homepage
        systemUnderTest.initiateWebStore();
        this.currentPage = CurrentPage.HOME_PAGE;
        this.isProductPageEmpty=true;
    }
    public @Action void searchForPhone() {
        //search for phone
        systemUnderTest.searchProduct("phone");
        this.currentPage = CurrentPage.PRODUCTS_PAGE;
        //assert that current product page is not empty
        if(!systemUnderTest.isProductPageEmpty())
            this.isProductPageEmpty = false;

    }

    public @Action void returnToHomePage() {
        systemUnderTest.returnToHome();
        this.isProductPageEmpty = true;
        this.currentPage = CurrentPage.HOME_PAGE;
    }

    public boolean addProductToCartGuard(){
        return this.currentPage == CurrentPage.PRODUCT_DETAILS_PAGE;
    }
    public @Action void addProductToCart(){
        if(systemUnderTest.isProductInStock()){
            //TODO
        }
    }


    public boolean selectFirstProductGuard(){
        return (this.currentPage == CurrentPage.PRODUCTS_PAGE) && !isProductPageEmpty;
    }
    public @Action void selectFirstProduct(){
        systemUnderTest.selectFirstProduct();
        this.currentPage = CurrentPage.PRODUCTS_PAGE;
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

        tester.generate(10);
        tester.printCoverage();
    }
}
