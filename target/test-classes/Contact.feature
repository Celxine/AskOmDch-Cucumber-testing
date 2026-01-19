@Contact
Feature: Contact Support

  @Smoke
  Scenario: Validate Contact Information
    Given user is on the "Contact Us" page
    Then user should see the following contact details:

      | Section | Content            |
      | Header  | Need Us?           |
      | Email   | askomdch@gmail.com |