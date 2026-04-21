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
import school.redrover.common.JenkinsUtils;

public class SignOutTest extends BaseTest {

    @Test
    public void signOutTest() {
        WebElement hoverOverAccountIcon = getDriver().findElement(By.id("root-action-UserAction"));
        new Actions(getDriver()).moveToElement(hoverOverAccountIcon).perform();
        getDriver().findElement(By.xpath("//a[@href='/logout']")).click();

        String signInTitle = getDriver().findElement(By.tagName("h1")).getText();

        Assert.assertEquals(signInTitle, "Sign in to Jenkins");
    }
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

    @Test (dependsOnMethods = "testSingOutIsImmediate")
    public void testJenkinsSingOutButton() {

        String currentUrl = getDriver().getCurrentUrl();
        String baseUrl = currentUrl.replaceFirst("(https?://[^/]+).*", "$1");
        getDriver().get(baseUrl);
        JenkinsUtils.login(getDriver());

        WebElement userButton = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("root-action-UserAction"))
        );

        Actions actions = new Actions(getDriver());
        actions.moveToElement(userButton).perform();

        WebElement dropdownMenu = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
        );
        dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();

        WebElement buttonSingIn = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))
        );

        Assert.assertEquals(buttonSingIn.getText(), "Sign in");
    }

    @Test (dependsOnMethods = "testSingOutIsImmediate")
     public void testJenkinsSingOutButtonUserNameEmpty() {

        String currentUrl = getDriver().getCurrentUrl();
        String baseUrl = currentUrl.replaceFirst("(https?://[^/]+).*", "$1");
        getDriver().get(baseUrl);

        JenkinsUtils.login(getDriver());

        WebElement userButton = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("root-action-UserAction"))
        );

        Actions actions = new Actions(getDriver());
        actions.moveToElement(userButton).perform();

        WebElement dropdownMenu = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
        );
        dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();

        WebElement usernameField = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("j_username"))
        );

        String usernameValue = usernameField.getAttribute("value");
        Assert.assertEquals(usernameValue, "",
                "Поле 'Username' должно быть пустым, но содержит: '" + usernameValue + "'");
    }

    @Test (dependsOnMethods = "testSingOutIsImmediate")
    public void testJenkinsSingOutButtonPasswordEmpty() {

        String currentUrl = getDriver().getCurrentUrl();
        String baseUrl = currentUrl.replaceFirst("(https?://[^/]+).*", "$1");
        getDriver().get(baseUrl);

        JenkinsUtils.login(getDriver());

        WebElement userButton = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("root-action-UserAction"))
        );

        Actions actions = new Actions(getDriver());
        actions.moveToElement(userButton).perform();

        WebElement dropdownMenu = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']"))
        );
        dropdownMenu.findElement(By.xpath(".//a[@href='/logout']")).click();

        WebElement passwordField = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("j_password"))
        );

        String passwordValue = passwordField.getAttribute("value");
        Assert.assertEquals(passwordValue, "",
                "Поле 'Password' должно быть пустым, но содержит: '" + passwordValue + "'");
    }

    @Test
    public void testDropdownMenuClosesWhenMouseMovesAway() {

        WebElement userButton = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("root-action-UserAction"))
        );

        Actions actions = new Actions(getDriver());
        actions.moveToElement(userButton).perform();


        WebElement dropdownMenu = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'jenkins-dropdown')]")
                )
        );
        Assert.assertTrue(dropdownMenu.isDisplayed(), "Меню не появилось после наведения");


        WebElement header = getDriver().findElement(By.tagName("header"));
        actions.moveToElement(header).click().perform();


        getWait5().until(ExpectedConditions.invisibilityOf(dropdownMenu));


        WebElement userButtonStillVisible = getDriver().findElement(By.id("root-action-UserAction"));
        Assert.assertTrue(userButtonStillVisible.isDisplayed(), "Пользователь разлогинился, кнопка не видна");


        boolean isLoginFormPresent = getDriver().findElements(By.id("j_username")).isEmpty();
        Assert.assertTrue(isLoginFormPresent, "Произошёл переход на страницу логина, сессия потеряна");
    }

}
