package school.redrover.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.external.GetInvolvedPage;
import school.redrover.page.common.BaseModel;
import school.redrover.page.external.JenkinsWebsite;

public class FooterVersionComponent extends BaseModel {

    @FindBy(xpath = "//a[@href='/manage/about']")
    private WebElement linkAboutJenkins;

    @FindBy(xpath = "//a[@href='https://www.jenkins.io/participate/']")
    private WebElement linkGetInvolved;

    @FindBy(xpath = "//a[@href='https://www.jenkins.io/']")
    private WebElement linkWebsite;

    public FooterVersionComponent(WebDriver driver) {
        super(driver);
    }

    public FooterVersionComponent clickButtonVersion() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.jenkins_ver"))).click();
        return this;
    }

    public boolean isVersionMenuDisplayed() {
        try {
            return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='jenkins-dropdown']"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public GetInvolvedPage clickGetInvolved() {
        linkGetInvolved.click();
        return new GetInvolvedPage(getDriver());
    }

    public JenkinsWebsite clickWebsite() {
        linkWebsite.click();
        return new JenkinsWebsite(getDriver());
    }
}
