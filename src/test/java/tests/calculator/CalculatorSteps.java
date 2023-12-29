package tests.calculator;

import edu.um.cps3230.calculator.Calculator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CalculatorSteps {
    Calculator calculator;
    @Given("I am using a calculator")
    public void iAmUsingACalculator() {
        calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        calculator.add(arg0,arg1);
    }

    @Then("I should get the result {int}")
    public void iShouldGetTheResult(int arg0) {
        Assertions.assertEquals(arg0, calculator.result);
    }
}
