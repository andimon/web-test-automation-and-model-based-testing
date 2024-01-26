Feature: Reachability of Product Categories

  In order to help me view items of a particular category
  As a user of the technology shop
  I want to be able to choose a category and see products related to that category

  @CategoriesTest
  Scenario Outline: Reachability of product categories:
    Given I am a user of the website
    When I visit the store website
    And I click on the <category> category
    Then I should be taken to <category> category
    And the category should show at least <num-products> products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

    Examples:
      | category             | num-products |
      | DESKTOPS_AND_LAPTOPS | 200          |
      | PHONES_AND_TABLETS   | 1000         |
      | COMPUTING            | 1200         |
      | GAMING               | 200          |
      | HOME_AND_LIFE        | 700          |
      | ACCESSORIES          | 800          |
      | DEALS                | 150          |
