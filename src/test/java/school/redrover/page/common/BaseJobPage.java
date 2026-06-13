package school.redrover.page.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.components.JobSideMenuComponent;

import java.util.List;

public abstract class BaseJobPage<SELF extends BaseJobPage<SELF>> extends BaseProjectPage<SELF> {
//for all jobs except Folder: Freestyle, Pipeline, Multi-configuration, Multibranch pipeline, Organization Folder

    @FindBy(xpath = "//ul[@class='permalinks-list']//a[contains(@class, 'permalink-link')]")
    private List<WebElement> permalinksList;

    private final JobSideMenuComponent<SELF> sideMenu;

    public BaseJobPage(WebDriver driver) {
        super(driver);
        this.sideMenu = new JobSideMenuComponent<>(driver, (SELF) this);
    }

    @Override
    public JobSideMenuComponent<SELF> getSideMenu() {
        return this.sideMenu;
    }

    public Boolean isPopUpMessageDisplayed(String popUpMessage) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("notification-bar"))).getText().equals(popUpMessage);
    }

    public Boolean getProjectIsDisabledMessage() {
        String projectIsDisabledMessage = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("enable-project"))).getText();

        return projectIsDisabledMessage.contains("This project is currently disabled");
    }

    public List<String> getPermalinksList() {
        return permalinksList.stream()
                .map(WebElement::getText)
                .toList();
    }

    public SELF waitForBuildtoFinish() {
        getWait10().until(driver -> {
            List<WebElement> iconProgress = driver.findElements(By.xpath("//div[@class='jenkins-app-bar']//*[local-name()='svg' and contains(@tooltip, 'In progress')]"));
            if (iconProgress.isEmpty()) {
                return true;
            }
            driver.navigate().refresh();
            return false;

        });
        return (SELF) this;
    }
}
