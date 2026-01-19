Feature: Store Browsing and Filtering

  Background:
    Given user is on the Store page

 Scenario: Filter products by Category
     When user clicks the "Women" category link
     Then the "Women's Category" page should be displayed
     And the product "Blue Denim Shorts" should be visible

  Scenario: Search for a specific product
    When user searches for "Jeans" in the sidebar
    Then the product "Basic Blue Jeans" should be visible

  Scenario: Sort products by Price (Low to High)
    When user selects "Sort by price: low to high" from the dropdown
    Then the first product should be "Anchor Bracelet"