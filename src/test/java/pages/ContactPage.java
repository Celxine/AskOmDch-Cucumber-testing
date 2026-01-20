package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContactPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By nameFld = By.id("wpforms-161-field_0");
    private final By emailFld = By.id("wpforms-161-field_1");
    private final By messageFld = By.id("wpforms-161-field_2");
    private final By submitBtn = By.id("wpforms-submit-161");
    private final By successMsg = By.id("wpforms-confirmation-161");

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void submitContactForm(String name, String email, String message) {
        driver.get("https://askomdch.com/contact");
        enterText(nameFld, name);
        enterText(emailFld, email);
        enterText(messageFld, message);
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).getText();
    }

    private void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }
}