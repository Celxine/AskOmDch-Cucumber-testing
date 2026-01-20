package step.definitions;

import dependency.injection.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ContactPage;

import java.util.Map;

public class ContactSteps {

    WebDriver driver = DriverFactory.getDriver();
    ContactPage contactPage = new ContactPage(driver);

    @Given("user submits the contact form with:")
    public void user_submits_contact_form(io.cucumber.datatable.DataTable dataTable) {

        Map<String, String> data = dataTable.asMap(String.class, String.class);

        contactPage.submitContactForm(
                data.get("Name"),
                data.get("Email"),
                data.get("Message")
        );
    }

    @Then("user should see the message {string}")
    public void verify_contact_success(String expectedMessage) {
        Assert.assertEquals(expectedMessage, contactPage.getSuccessMessage());
    }
}