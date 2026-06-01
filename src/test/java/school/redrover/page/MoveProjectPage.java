package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.page.common.BasePage;
import school.redrover.page.project.OrganizationFolderPage;


public class MoveProjectPage extends BasePage {

    @FindBy(xpath = "//select[@name='destination']")
    private WebElement selectDropdown;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement moveButton;

    public MoveProjectPage(WebDriver driver) {
        super(driver);
    }

    public MoveProjectPage selectWhereToMove(String location) {
        Select dropdown = new Select(selectDropdown);
        dropdown.selectByValue("/" + location);

        return this;
    }

    public OrganizationFolderPage clickMove() {
        moveButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel' and contains(., 'Full folder name')]")));

        return new OrganizationFolderPage(getDriver());
    }
}
