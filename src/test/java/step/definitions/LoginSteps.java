package step.definitions;

import dependency.injection.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    @Given("user is on the {string} page")
    public void user_is_on_page(String pageName) {
        driver.get("https://askomdch.com/account");
    }

    @When("user enters valid username {string} and password {string}")
    public void user_enters_credentials(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
    }

    @When("user clicks the login button")
    public void user_clicks_login() {
        driver.findElement(By.name("login")).click();
    }

    @Then("user should see the {string} link")
    public void verify_dashboard_link(String linkText) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(linkText)));
            Assert.assertTrue(true);
        } catch (Exception e) {

            try {
                WebElement error = driver.findElement(By.className("woocommerce-error"));
                System.out.println(">>> REGISTRATION FAILED. SITE SAYS: " + error.getText());
            } catch (Exception ex) {
                System.out.println(">>> REGISTRATION FAILED. NO ERROR MESSAGE FOUND.");
            }
            Assert.fail("Could not find link: " + linkText);
        }
    }

    @Then("user should see an error message {string}")
    public void verify_error(String errorText) {
        boolean isPresent = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("woocommerce-error"), errorText));
        Assert.assertTrue("Error message not found!", isPresent);
    }

    @When("user enters a unique email and password {string}")
    public void user_registers_new_account(String password) {
        String uniqueEmail = "auto_" + UUID.randomUUID().toString().substring(0, 8) + "@gmail.com";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_email"))).sendKeys(uniqueEmail);
        driver.findElement(By.id("reg_password")).sendKeys(password);
    }

    @When("user clicks the register button")
    public void user_clicks_register() throws InterruptedException {
        WebElement regBtn = driver.findElement(By.name("register"));
        try {
            regBtn.click();
        } catch (Exception e) {

            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", regBtn);

        }
        Thread.sleep(3000);
    }
}