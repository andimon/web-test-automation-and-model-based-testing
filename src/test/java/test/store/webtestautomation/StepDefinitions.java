package test.store.webtestautomation;

import edu.um.cps3230.pageobjects.*;
import io.cucumber.java.After;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URISyntaxException;

public class StepDefinitions {


    private NavigationComponent searchComponent;
    private ProductsViewComponent productsViewComponent;

    private ProductDetailsComponent productDetailsComponent;

    private NavigationComponent navigationComponent;
    private WebDriver webDriver;
    private int numberOfResults;

    private String nameOfFirstProduct;

    @After
    public void teardown(){
        webDriver.quit();
    }




    @Given("I am a user of the website")
    public void iAmAUserOfTheWebsite() {
        webDriver = new ChromeDriver();
        //maximise to ensure that the page does not render the burger menu
        webDriver.manage().window().maximize();
        searchComponent = new NavigationComponent(webDriver);
        navigationComponent = new NavigationComponent(webDriver);
        productDetailsComponent = new ProductDetailsComponent(webDriver);
        productsViewComponent = new ProductsViewComponent(webDriver);
    }

    @When("I visit the store website")
    public void iVisitTheStoreWebsite() {
        webDriver.get("https://www.klikk.com.mt");
    }


    @When("I search for a product using the term {string}")
    public void iSearchForAProductUsingTheTerm(String arg0) {
        webDriver.get("https://www.klikk.com.mt");
        searchComponent.search(arg0);
    }

    @Then("I should see the search results")
    public void iShouldSeeTheSearchResults() {
        numberOfResults = productsViewComponent.getNumberOfProducts();
    }

    @And("there should be at least {int} products in the search results")
    public void thereShouldBeAtLeastProductsInTheSearchResults(int arg0) {
        Assertions.assertTrue(arg0 <= numberOfResults);
    }


    @Then("I should be taken to {category} category")
    public void iShouldBeTakenToCOMPUTINGCategory(Category category) throws URISyntaxException {
        //assert using product tag bar text and approprate 
        String sectionURLQueryParam = UriComponentsBuilder.fromUriString(webDriver.getCurrentUrl()).build().getQueryParams().getFirst("t");
        String expectedQuery = category.queryParam;

        Assertions.assertEquals(expectedQuery, sectionURLQueryParam);
    }

    @And("the category should show at least {int} products")
    public void theCategoryShouldShowAtLeastProducts(int leastNumberOfProducts) {
        numberOfResults = productsViewComponent.getNumberOfProducts();
        Assertions.assertTrue(leastNumberOfProducts <= numberOfResults);
    }

    @When("I click on the first product in the results")
    public void iClickOnTheFirstProductInTheResults() {
        //select product and return name of product selectd
        nameOfFirstProduct = productsViewComponent.selectProduct(0);
    }

    @Then("I should be taken to the details page for that product")
    public void iShouldBeTakenToTheDetailsPageForThatProduct() {
        Assertions.assertEquals(nameOfFirstProduct, productDetailsComponent.getProductTitle());
    }

    @ParameterType("DESKTOPS_AND_LAPTOPS|PHONES_AND_TABLETS|COMPUTING|GAMING|HOME_AND_LIFE|ACCESSORIES|DEALS")
    public Category category(String categoryName) {
        return Category.valueOf(categoryName);
    }

    @When("I click on the {category} category")
    public void iClickOnTheCategory(Category category) {
        navigationComponent.clickSectionById(category);
    }
}
