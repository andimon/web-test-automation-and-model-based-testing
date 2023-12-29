Feature: Simple calculations

  In order to help me deal with simple arithmetic
  As a user of the calculator
  I want to be able to perform addition, subtraction, multiplication and division on 2 integers.


#  Scenario: Simple Addition
#    Given I am using a calculator
#    When I add 5 and 2
#    Then I should get the result 7

  Scenario Outline: Simple addition using different values:
    Given I am using a calculator
    When I add <num1> and <num2>
    Then I should get the result <ans>

    Examples:
    |num1|num2|ans|
    |5   |2   |7  |
    |5   |-2  |3  |
    |2   |0   |2  |
    |0   |0   |0  |