Feature: Search Functionality

  In order to help me decide which phone to get
  As a user of the technology shop
  I want to be able to have multiple choices of phones that are available
  on the market and be able to see the respective phone's specs/details

  @SearchFunctionalityTest
  Scenario: Search functionality:
    Given I am a user of the website
    When I search for a product using the term "phone"
    Then I should see the search results
    And there should be at least 5 products in the search results
    When I click on the first product in the results
    Then I should be taken to the details page for that product