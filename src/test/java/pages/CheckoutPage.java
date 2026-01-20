package pages;

import domainobjects.BillingDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By countryDropdown = By.id("billing_country");
    private final By addressFld = By.id("billing_address_1");
    private final By cityFld = By.id("billing_city");
    private final By stateDropdown = By.id("billing_state");
    private final By zipFld = By.id("billing_postcode");
    private final By emailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // 2. ACTIONS
    public void setBillingDetails(BillingDetails billing) {
        enterText(firstNameFld, billing.getFirstName());
        enterText(lastNameFld, billing.getLastName());
        selectByText(countryDropdown, billing.getCountry());
        enterText(addressFld, billing.getAddress());
        enterText(cityFld, billing.getCity());
        selectByText(stateDropdown, billing.getState());
        enterText(zipFld, billing.getZip());
        enterText(emailFld, billing.getEmail());
    }

    public void placeOrder() {

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));

        btn.click();
    }


    private void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    private void selectByText(By locator, String text) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }
}