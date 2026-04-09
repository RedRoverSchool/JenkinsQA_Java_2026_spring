package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

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

            WebElement dropdownMenu = getWait5().until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
            );
            dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();

            Assert.assertFalse(isAlertPresent(getDriver()),
                    "Не должно быть alert-окна подтверждения выхода. Выход должен быть мгновенным.");

            getWait5().until(ExpectedConditions.urlContains("login"));
        }

        @Test
        public void testJenkinsSingOutButtonUserNameEmpty() {
            WebElement userButton = getDriver().findElement(By.id("root-action-UserAction"));

            Actions actions = new Actions(getDriver());
            actions.moveToElement(userButton).perform();

            WebElement dropdownMenu = getWait5().until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
            );
            dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();

            getWait5().until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))
            );

            WebElement usernameField = getDriver().findElement(By.id("j_username"));
            String usernameValue = usernameField.getAttribute("value");
            Assert.assertEquals(usernameValue, "",
                    "Поле 'Username' должно быть пустым, но содержит: '" + usernameValue + "'");
        }

        @Test
        public void testJenkinsSingOutButtonPasswordEmpty() {

            WebElement userButton = getDriver().findElement(By.id("root-action-UserAction"));

            Actions actions = new Actions(getDriver());
            actions.moveToElement(userButton).perform();

            WebElement dropdownMenu = getWait5().until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
            );
            dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();

            getWait5().until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))
            );

            WebElement passwordField = getDriver().findElement(By.id("j_password"));
            String passwordValue = passwordField.getAttribute("value");
            Assert.assertEquals(passwordValue, "",
                    "Поле 'Password' должно быть пустым, но содержит: '" + passwordValue + "'");
        }
    }