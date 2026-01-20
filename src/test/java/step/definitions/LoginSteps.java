package step.definitions;

import dependency.injection.DriverFactory;
import domainobjects.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    User user;

    @Given("user is on the {string} page")
    public void user_is_on_page(String pageTitle) {

        driver.get("https://askomdch.com/account");
    }

    @When("user enters valid username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {

        user = new User(username, password);

        loginPage.login(user);
    }

    @When("user clicks the login button")
    public void user_clicks_login() {

    }

    @Then("user should see an error message {string}")
    public void verify_error_message(String expectedError) {
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError));
    }
}