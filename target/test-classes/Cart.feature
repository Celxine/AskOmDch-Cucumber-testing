

@Cart
Feature: Cart Management

  Background:
    Given user has added "Blue Denim Shorts" to the cart
    And user is on the Cart page

  Scenario: User removes an item from the cart
    When user clicks the "Remove" icon
    Then the cart should be empty
    And user should see the message "Your cart is currently empty."