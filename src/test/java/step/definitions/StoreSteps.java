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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StoreSteps {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("user is on the Store page")
    public void user_is_on_store_page() {
        driver.get("https://askomdch.com/store");
    }

    @When("user clicks the {string} category link")
    public void user_clicks_category(String category) {
        driver.findElement(By.linkText(category)).click();
    }

    @Then("the {string} page should be displayed")
    public void verify_category_page_displayed(String pageName) {

        String urlPart = "";

        if(pageName.toLowerCase().contains("women")) {
            urlPart = "product-category/women";
        } else if (pageName.toLowerCase().contains("men")) {
            urlPart = "product-category/men";
        } else {
            urlPart = "product-category";
        }

        wait.until(ExpectedConditions.urlContains(urlPart));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("Expected URL to contain: " + urlPart, currentUrl.contains(urlPart));
    }

    @When("user searches for {string} in the sidebar")
    public void user_searches_sidebar(String query) {

        WebElement searchBox = driver.findElement(By.id("woocommerce-product-search-field-0"));
        searchBox.clear();
        searchBox.sendKeys(query);
        driver.findElement(By.cssSelector("button[value='Search']")).click();
    }

    @When("user selects {string} from the dropdown")
    public void user_sorts_by(String sortOption) {
        WebElement sortDropdown = driver.findElement(By.className("orderby"));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(sortOption);

        wait.until(ExpectedConditions.urlContains("orderby"));
    }

    @Then("the first product should be {string}")
    public void verify_first_product(String expectedProduct) {

        WebElement firstProduct = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//h2[contains(@class,'woocommerce-loop-product__title')])[1]")));
        Assert.assertEquals(expectedProduct, firstProduct.getText());
    }

    @Then("the product {string} should be visible")
    public void verify_product_visible(String productName) {
        boolean isVisible = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("body"), productName
        ));
        Assert.assertTrue("Product " + productName + " not found!", isVisible);
    }
}