package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import school.redrover.common.CucumberDriver;
import school.redrover.page.manage.ManagePage;
import school.redrover.page.plugin.AvailablePluginsPage;
import school.redrover.page.plugin.PluginPage;

import java.time.Duration;

public class RoleBasedAuthorizationStrategySteps {

    private ManagePage managePage;
    private PluginPage pluginPage;
    private AvailablePluginsPage availablePluginsPage;

    private String pluginNameInstall;

    @When("Go to Manage Page")
    public void goToManageJenkins() {
        managePage = new ManagePage(CucumberDriver.getDriver()).clickManageButton();
    }

    @And("Click to Plugin")
    public void goToPluginPage() {
        pluginPage = managePage.clickPluginsButton();
    }

    @And("Go to Available plugins")
    public void goToAvailablePlugins() {
        availablePluginsPage = pluginPage.clickAvailablePluginsButton();
    }

    @And("Search {string} plugin")
    public void setPluginNameInSearchAvailablePlugin(String pluginName) {
        availablePluginsPage.setSearchPluginName(pluginName);
    }

    @And("Select {string} plugin")
    public void selectPlugin(String pluginName) {
        pluginNameInstall = pluginName;
        availablePluginsPage.selectSearchResult(pluginName);
    }

    @And("Install plugin")
    public void clickInstallPlugins() {
        availablePluginsPage.clickInstallButton().getSuccessInstall(pluginNameInstall);
    }

    @And("Click to Security")
    public void clickSecurityButton() {
        CucumberDriver.getDriver().findElement(By.xpath("//a[@href='configureSecurity']")).click();
        new WebDriverWait(CucumberDriver.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='jenkins-section__title' and contains(text(), 'Authentication')]")));
    }

    @And("Select {string} Authorization")
    public void selectTypeAuthorization(String typeAuthorization) {
        By element = By.xpath("//div[div[contains(text(), 'Authorization')]]//select");

        new WebDriverWait(CucumberDriver.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
        WebElement dropdown = CucumberDriver.getDriver().findElement(element);
        new Select(dropdown).selectByVisibleText(typeAuthorization);
    }

    @And("Click Save Security button")
    public void clickSaveButton() {
        CucumberDriver.getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        new WebDriverWait(CucumberDriver.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Manage Jenkins')]")));
    }

    @And("Click to Manage and Assign Roles")
    public void clickManageAssignRolesButton() {
        CucumberDriver.getDriver().findElement(By.xpath("//a[@href='role-strategy']")).click();
        new WebDriverWait(CucumberDriver.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Global roles')]")));
    }

    @And("Enter {string} in the Role to add field")
    public void setNameRoleInRoleToAdd(String role) {
        CucumberDriver.getDriver().findElement(By.xpath("//input[@id='globalRolestext']")).sendKeys(role);
    }

    @And("Click to Add button")
    public void clickAddButton() {
        CucumberDriver.getDriver().findElement(By.xpath("//button[@data-table-id='globalRoles']")).click();
    }

    @And("Click Save Manage Roles button")
    public void clickSaveManageRolesButton() {
        CucumberDriver.getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
    }

    @Then("Role with name {string} is exists")
    public void checkRoleName(String roleName) {
        String role = new WebDriverWait(CucumberDriver.getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='globalRoles']//td[contains(text(), '%s')]".formatted(roleName)))).getText();
        // Возвращаю настройки авторизации, потому что потом падают тесты с созданием пользователей
        clickSecurityButton();
        selectTypeAuthorization("Logged-in users can do anything");
        clickSaveButton();

        Assert.assertEquals(roleName, role);
    }
}
