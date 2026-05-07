package school.redrover.page.projects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.common.BaseProjectPage;

public class MultibranchProjectPage extends BaseProjectPage {

    public MultibranchProjectPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchProjectPage clickDeleteInSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(., 'Delete Multibranch Pipeline')]"))).click();

        return this;
    }

    public HomePage confirmDelete() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();

        return new HomePage(getDriver());
    }

    public MultibranchProjectPage clickRename(String projectName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Multibranch Pipeline']")));
        getDriver().findElement(By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(projectName))).click();

        return this;
    }

    public MultibranchProjectPage enterNewName(String newName) {
        WebElement newNameEl = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        newNameEl.clear();
        newNameEl.sendKeys(newName);

        return this;
    }

    public MultibranchProjectPage clickRenameButton() {
        getDriver().findElement(By.xpath("//button[@value='Rename']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Multibranch Pipeline']")));

        return this;
    }
}
