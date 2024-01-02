Feature: Reachability of Product Categories

  In order to help me view items of a particular category
  As a user of the technology shop
  I want to be able to choose a category and see products related to that category

  @CategoriesTest
  Scenario: Reachability of product categories:
    Given I am a user of the website
    When I visit the store website
    And I click on the COMPUTING category
    Then I should be taken to COMPUTING category
    And the category should show at least 400 products
    When I click on the first product in the results
    Then I should be taken to the details page for that product
