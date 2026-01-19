Feature: Checkout Functionality

  Scenario: Guest user places an order successfully
    Given user has added "Blue Jeans" to the cart
    And user proceeds to the checkout page
    When user fills the billing details with valid data
    And user places the order
    Then the "Thank you. Your order has been received." message should be displayed