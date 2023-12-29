package test.store;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReachabilityOfProductCategorySteps {
    @Given("I am user of the website")
    public void iAmUserOfTheWebsite() {
    }

    @When("I visit the store website")
    public void iVisitTheStoreWebsite() {
    }

    @And("I click on the <category-name> category")
    public void iClickOnTheCategoryNameCategory() {
    }

    @Then("I should be taken to <category-name> category")
    public void iShouldBeTakenToCategoryNameCategory() {
    }

    @And("the category should show at least <num-products> products")
    public void theCategoryShouldShowAtLeastNumProductsProducts() {
    }

    @When("I click on the first product in the results")
    public void iClickOnTheFirstProductInTheResults() {
    }

    @Then("I should be taken to the details page for that product")
    public void iShouldBeTakenToTheDetailsPageForThatProduct() {
    }
}
