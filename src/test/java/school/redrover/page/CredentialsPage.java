package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CredentialsPage extends BasePage {
    public CredentialsPage(WebDriver driver) {super(driver);}

    public CredentialsPage openAddCredentialsDialog() {
                getWait5().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-type='credentials-add-store-item']"))).click();
                return this;
    }

    public String getDialogTitle() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".jenkins-dialog__title"))).getText();
    }

    public CredentialsPage createUsernameWithPassword(String user, String pass, String id, String desc) {
        getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".jenkins-choice-list__item__label"))).getFirst().click();
        getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("cr-dialog-next"))).getFirst().click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.username")))
                .sendKeys(user);
        getDriver().findElement(By.name("_.password")).sendKeys(pass);
        getDriver().findElement(By.name("_.id")).sendKeys(id);
        getDriver().findElement(By.name("_.description")).sendKeys(desc);
        getDriver().findElement(By.id("cr-dialog-submit")).click();
        return this;
    }

    public boolean isCredentialVisible(String id) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + id + "')]"))).isDisplayed();
    }
}
