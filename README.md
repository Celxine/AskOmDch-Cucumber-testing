# AskOmDch-Cucumber-testing
Project Description This project is an automated testing framework built for the AskOmDch e-commerce website. I created this to show I can use Cucumber BDD with Selenium and Java to test real-world scenarios. The main idea was to automate the critical things that a normal user would go through, like buying clothes, searching for items, and contacting support. I focused on making the code reusable and easy to understand by using the Page Object Model structure.

What I Tested I covered several different parts of the website to ensure the business logic works correctly.

Shopping Cart: I tested if a user can add items to the cart and then remove them. I verified that when you remove an item, the cart actually becomes empty and shows the correct message.

Checkout Process: I automated the guest checkout flow. This involved adding a product (like Blue Jeans or Shorts) and filling out the billing form with valid data to place an order successfully.

Store Navigation: Since users need to find products, I tested the sorting and filtering features. I verified that clicking "Women" actually takes you to the Women's category and that sorting by "Price: Low to High" correctly shows the cheapest items first.

Contact Page: I checked the Contact Us page to make sure the correct business information (like the email address) is visible to users.

Login : I also included a negative test for the login page to make sure that if someone enters the wrong password, they get an error message and cannot get in.

Tools and Techniques Used I used Java as the programming language and Maven. For the browser automation, I used Selenium WebDriver. The tests are written in Gherkin syntax (feature files) so they are easy to read. 

How to Run the Tests You can run the tests using the TestRunner.java file found in the runner folder. If you want to run everything, you can just run the class as a JUnit test. I have set it up to generate a simple HTML report in the target folder so you can see the results after the execution.
