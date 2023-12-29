Feature: Reachability of Product Categories

  In order to help me view items of a particular category
  As a user of the technology shop
  I want to be able to choose a category and see products related to that category

  Scenario Outline: Reachability of product categories:
    Given I am user of the website
    When I visit the store website
    And I click on the <category-name> category
    Then I should be taken to <category-name> category
    And the category should show at least <num-products> products
    When I click on the first product in the results
    Then I should be taken to the details page for that product
    Examples:
      | category-name | num-products |
      |  "DS"         | 2            |
      | 5             | -2           |
      | 2             | 0            |
      | 0             | 0            |