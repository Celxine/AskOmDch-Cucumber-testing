package step.definitions;

import dependency.injection.DriverFactory;
import domainobjects.BillingDetails;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CheckoutPage;
import java.time.Duration;

public class CheckoutSteps {

    WebDriver driver = DriverFactory.getDriver();
    CheckoutPage checkoutPage = new CheckoutPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    @Given("user has added {string} to the cart")
    public void user_has_added_to_the_cart(String productName) {
        driver.get("https://askomdch.com/store");

        By addToCartBtn = By.xpath("//h2[normalize-space()='" + productName + "']/ancestor::li//a[contains(@class,'add_to_cart_button')]");

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        btn.click();

        By viewCartLink = By.cssSelector("a[title='View cart']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
    }

    @Given("user proceeds to the checkout page")
    public void user_proceeds_to_the_checkout_page() {

        By proceedBtn = By.cssSelector(".checkout-button");
        wait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();
    }

    @When("user fills the billing details with valid data")
    public void user_fills_the_billing_details_with_valid_data() {

        BillingDetails billing = new BillingDetails();
        billing.setFirstName("Celine");
        billing.setLastName("Test");
        billing.setCountry("United States (US)");
        billing.setAddress("123 Test Street");
        billing.setCity("New York");
        billing.setState("New York");
        billing.setZip("10001");

        billing.setEmail("celine" + System.currentTimeMillis() + "@test.com");

        checkoutPage.setBillingDetails(billing);
    }

    @When("user places the order")
    public void user_places_the_order() {
        checkoutPage.placeOrder();
    }

    @Then("the {string} message should be displayed")
    public void the_message_should_be_displayed(String expectedMessage) {

        By successMessage = By.cssSelector(".woocommerce-notice");
        String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();

        Assert.assertEquals(expectedMessage, actualText);
    }
}