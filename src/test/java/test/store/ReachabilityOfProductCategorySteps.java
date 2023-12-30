package test.store;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.experimental.categories.Categories;
import test.store.pageobjects.Section;

public class ReachabilityOfProductCategorySteps {
    @Given("I am user of the website")
    public void iAmUserOfTheWebsite() {
    }

    @When("I visit the store website")
    public void iVisitTheStoreWebsite() {
    }

    @ParameterType("DESKTOPSANDLAPTOPS | PHONESANDTABLETS | COMPUTING | GAMING | HOMEANDLIFE | ACCESSORIES | DEALS")
    public Section Section(String category){
        return Section.valueOf(category);
    }
    @And("I click on the {Section} category")
    public void iClickOnTheCOMPUTINGCategory(Section category) {
    }

    @Then("I should be taken to {Section} category")
    public void iShouldBeTakenToCOMPUTINGCategory(Section category) {
    }

    @And("the category should show at least {int} products")
    public void theCategoryShouldShowAtLeastProducts(int arg0) {
    }

    @When("I click on the first product in the results")
    public void iClickOnTheFirstProductInTheResults() {
    }

    @Then("I should be taken to the details page for that product")
    public void iShouldBeTakenToTheDetailsPageForThatProduct() {
    }
}
