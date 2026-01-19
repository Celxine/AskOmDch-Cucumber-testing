@login

Feature: Account Management

  Scenario: User fails to login with wrong password
    Given user is on the "My Account" page
    When user enters valid username "Col" and password "WRONG_PASS"
    And user clicks the login button
    Then user should see an error message "Error:"
