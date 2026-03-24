import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;

public class TVotSwagLabsLoginTest {
    private static final String BASE_URL = "https://www.saucedemo.com";

    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        // Arrange
        driver = new ChromeDriver();
        driver
            .manage()
            .timeouts()
            .implicitlyWait(Duration.ofMillis(500));

        driver.get(BASE_URL);
        String title = driver.getTitle();

        // Ensure we are on the right page
        Assert.assertEquals(title, "Swag Labs");
    }

    @Test
    public void testStandardLoginShouldOpenProductPage() {
        // Act
        login("standard_user");

        // Asserts
        var url = driver.getCurrentUrl();
        Assert.assertEquals(url, BASE_URL + "/inventory.html");

        var header = driver
            .findElement(By
                .cssSelector("[data-test='primary-header'] .app_logo"))
            .getText();
        Assert.assertEquals(header, "Swag Labs");

        var secondHeader = driver
            .findElement(By
                .cssSelector("[data-test='secondary-header'] [data-test='title']"))
            .getText();

        Assert.assertEquals(secondHeader, "Products");
    }

    @DataProvider(name = "negative-tests")
    public Object[][] createNegativeTestsData() {
        return new Object[][] {
            { "unknown_user", "do not match any user" },
            { "locked_out_user", "user has been locked out" },
        };
    }

    @Test(dataProvider = "negative-tests")
    public void testNotExceptedLoginShouldShowError(String username, String expectedMessage) {
        // Act
        login(username);

        // Asserts
        Assert.assertTrue(
            hasErrorClass(getUsername()),
            "UserName should be marked by error class");

        Assert.assertTrue(
            hasErrorClass(getPassword()),
            "Password should be marked by error class");

        var errorMessageEl = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(
            errorMessageEl.isDisplayed(),
            "Error message should be displayed");

        var errorMessage = errorMessageEl.getText();
        Assert.assertTrue(
            errorMessage.contains(expectedMessage),
            "Not expected error message.\n" +
                " Expected: " + expectedMessage + "\n" +
                " Actual: " + errorMessage);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    private void login(String username) {
        getUsername().sendKeys(username);

        getPassword().sendKeys("secret_sauce");

        driver
            .findElement(By.cssSelector("input[data-test='login-button']"))
            .click();
    }

    private boolean hasErrorClass(WebElement element) {
        var classes = element.getAttribute("class");

        if (classes == null || classes.isEmpty()) {
            return false;
        }

        var classesList = Arrays.asList(classes.split(" "));

        return classesList
            .contains("error");
    }

    private WebElement getUsername() {
        return driver
            .findElement(By.cssSelector("input[data-test='username']"));
    }

    private WebElement getPassword() {
        return driver
            .findElement(By.cssSelector("input[data-test='password']"));
    }
}
