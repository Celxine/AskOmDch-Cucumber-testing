@Store
Feature: Store Functionality

  Background:
    Given user is on the Store page

  @Regression
  Scenario: Navigate to Women's Category
    When user clicks the "Women's Category" category link
    Then the "Women's Category" page should be displayed

  @Regression
  Scenario: Navigate to Men's Category
    When user clicks the "Men's Category" category link
    Then the "Men's Category" page should be displayed

  @Smoke @Search
  Scenario: Search for a product in the sidebar
    When user searches for "Blue Denim" in the sidebar
    Then the first product should be "Blue Denim Shorts"

  @Regression
  Scenario: Sort products by popularity
    When user selects "Sort by popularity" from the dropdown
    Then the product "Blue Denim Shorts" should be visible