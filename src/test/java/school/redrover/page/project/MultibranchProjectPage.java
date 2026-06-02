package school.redrover.page.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.common.BaseJobPage;
import school.redrover.page.components.MultibranchOrgFolderSideMenuComponent;

public class MultibranchProjectPage extends BaseJobPage<MultibranchProjectPage> {

    private final MultibranchOrgFolderSideMenuComponent<MultibranchProjectPage> fullSideMenu;

    public MultibranchProjectPage(WebDriver driver) {
        super(driver);
        this.fullSideMenu = new MultibranchOrgFolderSideMenuComponent<>(driver, this);
    }

    @Override
    public MultibranchOrgFolderSideMenuComponent<MultibranchProjectPage> getSideMenu() {
        return this.fullSideMenu;
    }

    public MultibranchProjectPage clickDeleteInSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(., 'Delete Multibranch Pipeline')]"))).click();

        return this;
    }

    public HomePage confirmDelete() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();

        return new HomePage(getDriver());
    }
}
