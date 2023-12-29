package test.store;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.store.pageobjects.SearchComponent;

public class SearchFunctionalitySteps {
    private SearchComponent searchComponent;
    private WebDriver webDriver;



    @Given("I am a user of the website")
    public void iAmAUserOfTheWebsite() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.klikk.com.mt");
        searchComponent = new SearchComponent(webDriver);
    }

    @When("I search for a product using the term {string}")
    public void iSearchForAProductUsingTheTerm(String arg0) {
        searchComponent.search(arg0);
    }

    @Then("I should see the search results")
    public void iShouldSeeTheSearchResults() {

    }

    @And("there should be at least {int} products in the search results")
    public void thereShouldBeAtLeastProductsInTheSearchResults(int arg0) {
    }
}
