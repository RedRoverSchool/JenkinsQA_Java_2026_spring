import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class JenkinsSingoutButtonTest extends BaseTest {

        @Test
        public void testJenkinsSingOutButton(){

            WebElement userButton = getDriver().findElement(By.id("root-action-UserAction"));

            Actions actions = new Actions(getDriver());
            actions.moveToElement(userButton).perform();


            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            WebElement dropdownMenu = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
            );

            dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();
            WebElement buttonSingIn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))
            );

            Assert.assertEquals(buttonSingIn.getText(), "Sign in");
        }

        @Test
        public void testJenkinsSingOutButtonUserNameEmpty(){

            WebElement userButton = getDriver().findElement(By.id("root-action-UserAction"));

            Actions actions = new Actions(getDriver());
            actions.moveToElement(userButton).perform();


            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            WebElement dropdownMenu = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
            );

            dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();
            WebElement buttonSingIn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))
            );

            WebElement usernameField = getDriver().findElement(By.id("j_username"));
            String usernameValue = usernameField.getAttribute("value");
            Assert.assertEquals(usernameValue, "",
                    "Поле 'Username' должно быть пустым, но содержит: '" + usernameValue + "'");


        }

        @Test
        public void testJenkinsSingOutButtonPasswordEmpty(){

            WebElement userButton = getDriver().findElement(By.id("root-action-UserAction"));

            Actions actions = new Actions(getDriver());
            actions.moveToElement(userButton).perform();


            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            WebElement dropdownMenu = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
            );

            dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();
            WebElement buttonSingIn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))
            );

            WebElement passwordField = getDriver().findElement(By.id("j_password"));
            String passwordValue = passwordField.getAttribute("value");
            Assert.assertEquals(passwordValue, "",
                    "Поле 'Password' должно быть пустым, но содержит: '" + passwordValue + "'");


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
            try {
                Alert alert = getDriver().switchTo().alert();
                String alertText = alert.getText();
                Assert.fail("Неожиданно появилось alert-окно с текстом: '" + alertText + "'");
            } catch (NoAlertPresentException e) {

            }

            wait.until(ExpectedConditions.urlContains("login"));



        }

    }

