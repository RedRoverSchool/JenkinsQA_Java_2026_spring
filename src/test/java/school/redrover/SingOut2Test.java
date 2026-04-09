package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class SingOut2Test extends BaseTest {

    private boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @Test
    public void testSingOutIsImmediate() {

        WebElement userButton = getDriver().findElement(By.id("root-action-UserAction"));

        Actions actions = new Actions(getDriver());
        actions.moveToElement(userButton).perform();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement dropdownMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
        );
        dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();

        Assert.assertFalse(isAlertPresent(getDriver()),
                "Не должно быть alert-окна подтверждения выхода. Выход должен быть мгновенным.");

        wait.until(ExpectedConditions.urlContains("login"));

    }
}
