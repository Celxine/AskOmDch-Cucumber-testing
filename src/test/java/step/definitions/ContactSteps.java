package step.definitions;

import dependency.injection.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ContactSteps {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Then("user should see the following contact details:")
    public void verify_contact_details(DataTable dataTable) {
        // Convert Data Table to List of Maps
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        String pageSource = driver.getPageSource();

        // Loop through the data and check if the text exists on the page
        for (Map<String, String> row : data) {
            String contentToCheck = row.get("Content");

            // Assert that the page contains the email or header text
            Assert.assertTrue("Text not found on page: " + contentToCheck,
                    pageSource.contains(contentToCheck));
        }
    }
}