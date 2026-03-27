import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class group_future_aqa_add_new_tests {
    @Test
    public void testKhairutdinovaOlgaSignInPageAlertMessageTextAndColor_1() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");

            WebElement inputUserName = driver.findElement(By.cssSelector("#j_username"));
            inputUserName.sendKeys("user");
            WebElement password = driver.findElement(By.cssSelector("#j_password"));
            password.sendKeys("qwerty");
            WebElement singButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
            singButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[text()='Invalid username or password']")));
            Assert.assertEquals(alertText.getText(), "Invalid username or password");

            String actualColor = alertText.getCssValue("color");
            Assert.assertTrue(actualColor.contains("oklch(0.6 0.2671 30)"),
                    "Цвет текста ошибки не красный: " + actualColor);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testKhairutdinovaOlgaSignInPageAlertInputBorderColor_2() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");

            WebElement inputUserName = driver.findElement(By.cssSelector("#j_username"));
            inputUserName.sendKeys("user");
            WebElement password = driver.findElement(By.cssSelector("#j_password"));
            password.sendKeys("qwerty");
            WebElement singButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
            singButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement usernameField = driver.findElement(By.cssSelector("#j_username"));
            WebElement passwordField = driver.findElement(By.cssSelector("#j_password"));

            Assert.assertTrue(usernameField.getAttribute("class").contains("error"),
                    "Поле username не подсвечено как ошибочное");
            Assert.assertTrue(passwordField.getAttribute("class").contains("error"),
                    "Поле password не подсвечено как ошибочное");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testKhairutdinovaOlgaSignInButtonColor_3() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");

            WebElement singButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
            String actualColor = singButton.getCssValue("color");

            Assert.assertTrue(actualColor.contains("rgba(254, 254, 254, 1)"),
                    "Цвет текста кнопки не синий: " + actualColor);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testKhairutdinovaOlgaHoverBorderColor_4() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");

            WebElement checkbox = driver.findElement(By.id("remember_me"));
            String borderColorBefore = checkbox.getCssValue("border-color");
            Actions actions = new Actions(driver);
            actions.moveToElement(checkbox).perform();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // ignore
            }

            String borderColorAfter = checkbox.getCssValue("border-color");
            Assert.assertTrue(borderColorAfter.contains("128, 128, 128") || borderColorAfter.contains("oklch(0.05 0.075 256.91)"),
                    "Цвет границы не стал серым: " + borderColorAfter);
        } finally {
            driver.quit();
        }
    }
}
