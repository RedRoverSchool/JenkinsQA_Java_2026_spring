package school.redrover.page.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.external.GetInvolvedPage;
import school.redrover.page.common.BaseModel;
import school.redrover.page.external.JenkinsWebsite;

public class VersionComponent extends BaseModel {

    @FindBy(xpath = "//div[@class='jenkins-dropdown']")
    private WebElement jenkinsVersionMenu;

    @FindBy(css = "button.jenkins_ver")
    private WebElement buttonVersionMenu;

    @FindBy(xpath = "//a[@href='/manage/about']")
    private WebElement linkAboutJenkins;

    @FindBy(xpath = "//a[@href='https://www.jenkins.io/participate/']")
    private WebElement linkGetInvolved;

    @FindBy(xpath = "//a[@href='https://www.jenkins.io/']")
    private WebElement linkWebsite;

    public VersionComponent(WebDriver driver) {
        super(driver);
    }

    public VersionComponent clickJenkinsVersion() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonVersionMenu)).click();
        return this;
    }

    public boolean isJenkinsVersionMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(buttonVersionMenu)).click();
        return jenkinsVersionMenu.isDisplayed();
    }

    public GetInvolvedPage clickGetInvolved() {
        getWait5().until(ExpectedConditions.elementToBeClickable(linkGetInvolved)).click();
        return new GetInvolvedPage(getDriver());
    }

    public JenkinsWebsite clickWebsite() {
        getWait5().until(ExpectedConditions.elementToBeClickable(linkWebsite)).click();
        return new JenkinsWebsite(getDriver());
    }
}
