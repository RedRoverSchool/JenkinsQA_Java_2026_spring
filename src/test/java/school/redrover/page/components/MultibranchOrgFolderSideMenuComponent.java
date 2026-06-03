package school.redrover.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.PipelineSyntaxPage;
import school.redrover.page.common.BasePage;
import school.redrover.page.manage.CredentialsPage;

public class MultibranchOrgFolderSideMenuComponent<T extends BasePage> extends JobSideMenuComponent<T> {

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/console')]")
    private WebElement scanButton;

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/events')]")
    private WebElement eventsButton;

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/builds')]")
    private WebElement buildHistoryButton;

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/pipeline-syntax')]")
    private WebElement pipelineSyntaxButton;

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/credentials')]")
    private WebElement credentialsButton;

    public MultibranchOrgFolderSideMenuComponent(WebDriver driver, T parentPage) {
        super(driver, parentPage);
    }

    public PipelineSyntaxPage clickPipelineSyntax() {
        pipelineSyntaxButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='generatePipelineScript']")));

        return new PipelineSyntaxPage(getDriver());
    }

    public CredentialsPage clickCredentials() {
        credentialsButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-notice__description']/button[contains(., 'Add Credentials')]")));

        return new CredentialsPage(getDriver());
    }
}
