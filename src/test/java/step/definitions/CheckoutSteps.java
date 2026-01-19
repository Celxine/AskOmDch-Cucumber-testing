package step.definitions;

import dependency.injection.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutSteps {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    @Given("user has added {string} to the cart")
    public void user_has_added_product_to_cart(String productName) throws InterruptedException {
        driver.get("https://askomdch.com/store");

        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("woocommerce-product-search-field-0")));
        searchField.sendKeys(productName);
        driver.findElement(By.cssSelector("button[value='Search']")).click();

        WebElement productTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(@class, 'woocommerce-loop-product__title')]")));
        productTitle.click();

        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.name("add-to-cart")));
        addToCartBtn.click();

               Thread.sleep(5000);
    }
    @And("user proceeds to the checkout page")
    public void user_proceeds_to_checkout() {
        driver.get("https://askomdch.com/checkout");
    }

    @When("user fills the billing details with valid data")
    public void user_fills_billing_details() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_first_name"))).sendKeys("Cole");
        driver.findElement(By.id("billing_last_name")).sendKeys("Train");

        Select countrySelect = new Select(driver.findElement(By.id("billing_country")));
        countrySelect.selectByVisibleText("United States (US)");

        driver.findElement(By.id("billing_address_1")).sendKeys("123 Test Street");
        driver.findElement(By.id("billing_city")).sendKeys("Test City");

        try { Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        Select stateSelect = new Select(driver.findElement(By.id("billing_state")));
        stateSelect.selectByVisibleText("California");

        driver.findElement(By.id("billing_postcode")).sendKeys("90210");
        driver.findElement(By.id("billing_email")).sendKeys("cole.train@example.com");
    }

    @And("user places the order")
    public void user_places_the_order() {
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        WebElement placeOrderBtn = driver.findElement(By.id("place_order"));
        try {
            placeOrderBtn.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderBtn);
        }
    }

    @Then("the {string} message should be displayed")
    public void verify_success_message(String expectedMessage) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), expectedMessage));
        boolean isPresent = driver.getPageSource().contains(expectedMessage);
        Assert.assertTrue("Success message not found!", isPresent);
    }
}