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

public class CartSteps {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @Given("user is on the Cart page")
    public void user_is_on_cart_page() {
        driver.get("https://askomdch.com/cart/");
    }

    @When("user clicks the {string} icon")
    public void user_clicks_remove(String iconName) {

        WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("remove")));
        removeBtn.click();

        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @Then("the cart should be empty")
    public void verify_cart_is_empty() {

        boolean returnBtnVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("return-to-shop"))).isDisplayed();
        Assert.assertTrue("Cart is not empty!", returnBtnVisible);
    }

    @Then("user should see the message {string}")
    public void verify_empty_cart_message(String msg) {
        boolean isTextPresent = driver.getPageSource().contains(msg);
        Assert.assertTrue("Empty cart message missing!", isTextPresent);
    }
}