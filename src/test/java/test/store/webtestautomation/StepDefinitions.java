package test.store;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.util.UriComponentsBuilder;
import test.store.pageobjects.*;


import java.net.URI;
import java.net.URISyntaxException;

import static io.cucumber.core.internal.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.URI;

public class StepDefinitions {


    private SearchComponent searchComponent;
    private SectionsComponent sectionsComponent;
    private ProductsViewComponent productsViewComponent;

    private ProductDetailsComponent productDetailsComponent;
    private WebDriver webDriver;
    private int numberOfResults;

    private String nameOfFirstProduct;

    @ParameterType("COMPUTING|ELECTRONICS|BOOKS")
    public Section category(String categoryName) {
        // Convert the string to a Category ENUM
        return Section.valueOf(categoryName);
    }

    @Given("I am a user of the website")
    public void iAmAUserOfTheWebsite() {
        webDriver = new ChromeDriver();
        //maximise to ensure that the page does not render the burger menu
        webDriver.manage().window().maximize();
        webDriver.get("https://www.klikk.com.mt");
        searchComponent = new SearchComponent(webDriver);
        sectionsComponent = new SectionsComponent(webDriver);
        productDetailsComponent = new ProductDetailsComponent(webDriver);
        productsViewComponent = new ProductsViewComponent(webDriver);
    }

    @When("I visit the store website")
    public void iVisitTheStoreWebsite() {
        System.out.println("User already in Website");
    }


    @When("I search for a product using the term {string}")
    public void iSearchForAProductUsingTheTerm(String arg0) {
        searchComponent.search(arg0);
    }

    @Then("I should see the search results")
    public void iShouldSeeTheSearchResults() {
        numberOfResults = productsViewComponent.getNumberOfProductsInPage();
    }

    @And("there should be at least {int} products in the search results")
    public void thereShouldBeAtLeastProductsInTheSearchResults(int arg0) {
        Assertions.assertTrue(arg0 <= numberOfResults);
    }


    @Then("I should be taken to {category} category")
    public void iShouldBeTakenToCOMPUTINGCategory(Section category) throws URISyntaxException {
        //assert using product tag bar text and approprate 
        String sectionURLQueryParam = UriComponentsBuilder.fromUriString(webDriver.getCurrentUrl()).build().getQueryParams().getFirst("t");
        Assertions.assertEquals("_computing",sectionURLQueryParam);
    }

    @And("the category should show at least {int} products")
    public void theCategoryShouldShowAtLeastProducts(int leastNumberOfProducts) {
        numberOfResults = productsViewComponent.getNumberOfProductsInPage();
        Assertions.assertTrue(leastNumberOfProducts<=numberOfResults);
    }

    @When("I click on the first product in the results")
    public void iClickOnTheFirstProductInTheResults() {
        productsViewComponent.clickOnFistProductInPage();
    }

    @Then("I should be taken to the details page for that product")
    public void iShouldBeTakenToTheDetailsPageForThatProduct() {
        Assertions.assertEquals(nameOfFirstProduct,productDetailsComponent.getProductTitle());
    }

    @When("I click on the {category} category")
    public void iClickOnTheCOMPUTINGCategory(Section category) {
        sectionsComponent.clickSectionById(category);
    }
}
