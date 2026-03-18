import java.time.Duration;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IlyaTest {
    @Test
    public void testOsipov() {
        WebDriver driver = new ChromeDriver();
        driver.get("");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith("", "");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        WebElement title = driver.findElement(By.xpath("//h2[text()='Уведомления']"));
        Assert.assertEquals(title.getText(), "Уведомления", "Элемент не найден.");
        driver.quit();
    }

    public class LoginPage {
        private final WebDriver driver;

        LoginPage(WebDriver driver) {

            this.driver = driver;
        }

        public void enterUsername(String username) {
            this.driver.findElement(By.xpath("//*[@id=\"input-vaadin-text-field-6\"]")).sendKeys(username);
        }

        public void enterPassword(String password) {
            this.driver.findElement(By.xpath("//*[@id=\"input-vaadin-password-field-7\"]")).sendKeys(password);
        }

        public void clickLogin() {
            this.driver.findElement(By.xpath("//*[@id=\"aispbpekbudget26-885494466\"]//vaadin-button")).click();
        }

        public void clickDeveloperButton() {
            this.driver.findElement(By.xpath("//*[@id=\"aispbpekbudget26-885494466\"]/vaadin-app-layout/vaadin-vertical-layout/vaadin-vertical-layout[3]/vaadin-button")).click();
        }

        public void loginWith(String user, String pass) {
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
            this.enterUsername(user);
            this.enterPassword(pass);
            this.clickLogin();
            this.clickDeveloperButton();
        }
    }
}