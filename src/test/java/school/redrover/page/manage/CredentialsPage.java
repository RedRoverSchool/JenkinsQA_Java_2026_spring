package school.redrover.page.manage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.AddCredentialsPage;
import school.redrover.page.common.BasePage;

import java.util.List;

public class CredentialsPage extends BasePage {

    public CredentialsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button[data-type='credentials-add-store-item']")
    private WebElement addCredentialsButton;

    @FindBy(xpath ="//span[text() = 'Close']")
    private WebElement closeButton;

    @FindBy(css = "div.credentials-card__title a")
    private List<WebElement> credentialLinks;

    @FindBy(xpath = ("//*[contains(text(), 'Delete credential')]"))
    private WebElement deleteCredentialOption;

    @FindBy(css = "button[data-id='ok']")
    private WebElement confirmOkButton;

    public AddCredentialsPage clickAddCredentialsButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(addCredentialsButton)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(closeButton));

        return new AddCredentialsPage(getDriver());
    }

    private By getCredentialLocator(String id) {
        return By.xpath(String.format("//*[contains(text(), '%s')]", id));
    }

    public boolean isCredentialVisible(String id) {
        return getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(getCredentialLocator(id)))
                .isDisplayed();
    }

    public List<String> getCredentialList() {
        return credentialLinks.stream()
                .map(WebElement::getText).toList();
    }

    public CredentialsPage clickDeleteCredential(String id) {

        By moreActionsByRowId = By.xpath("//div[contains(@class, 'credentials-card')][.//a[contains(@href, '" + id + "')]]//button[@tooltip='More actions']");

        WebElement moreActions = getWait10().until(ExpectedConditions.elementToBeClickable(moreActionsByRowId));
                moreActions.click();
        getWait5().until(ExpectedConditions.elementToBeClickable(deleteCredentialOption)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(confirmOkButton)).click();

        return this;
    }

    public boolean isCredentialDeleted(String id) {
        return  getWait10()
                .until(ExpectedConditions.invisibilityOfElementLocated(getCredentialLocator(id)));
    }
}
