package school.redrover.page.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.components.JobSideMenuComponent;

public abstract class BaseJobPage<SELF extends BaseJobPage<SELF>> extends BaseProjectPage<SELF> {
//for all jobs except Folder: Freestyle, Pipeline, Multi-configuration, Multibranch pipeline, Organization Folder

    private final JobSideMenuComponent<SELF> sideMenu;

    public BaseJobPage(WebDriver driver) {
        super(driver);
        this.sideMenu = new JobSideMenuComponent<>(driver, (SELF)this);
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
}
