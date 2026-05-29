package school.redrover.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.MoveProjectPage;
import school.redrover.page.RenameProjectPage;
import school.redrover.page.common.BasePage;

public class JobSideMenuComponent<T extends BasePage> extends BaseSideMenuComponent<T> {

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/move')]")
    private WebElement moveButton;

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/confirm-rename')]")
    private WebElement renameButton;

    public JobSideMenuComponent(WebDriver driver, T parentPage) {
        super(driver, parentPage);
    }

    public MoveProjectPage clickMove() {
        moveButton.click();
        return new MoveProjectPage(getDriver());
    }

    public RenameProjectPage clickRename() {
        renameButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit' and @value='Rename']")));

        return new RenameProjectPage(getDriver());
    }
}
