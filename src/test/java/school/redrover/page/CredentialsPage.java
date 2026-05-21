package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    @FindBy(css = ".jenkins-dialog__title")
    private WebElement dialogTitle;

    @FindBy(css = ".jenkins-choice-list__item__label")
    private List<WebElement> kindOptions;

    @FindBy(id = "cr-dialog-next")
    private List<WebElement> nextButtons;

    @FindBy(xpath = "//div[@class='jenkins-choice-list__item__label'][contains(text(),'SSH')]" )
    private WebElement SSHUsernameWithKey;

    @FindBy(xpath = "//div[@class='jenkins-choice-list__item__label'][text()='Secret text']")
    private WebElement secretTextButton;

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextButton;

    @FindBy(id = "cr-dialog-submit")
    private WebElement createButton;

    @FindBy(name = "_.secret")
    private WebElement secretTextField;

    @FindBy(name = "_.username")
    private WebElement usernameTextField;

    @FindBy(name = "_.password")
    private WebElement passwordTextField;

    @FindBy(name = "_.passphrase")
    private WebElement passphraseTextField;

    @FindBy(name = "_.id")
    private WebElement IDField;

    @FindBy(name = "_.description")
    private WebElement descriptionField;

    @FindBy(css= "label.attach-previous")
    private WebElement treatUsernameAsSecretCheckbox;

    @FindBy(css= "label.jenkins-radio__label")
    private WebElement enterDirectlyCheckbox;

    @FindBy(css = "button.secret-update-btn")
    private WebElement addButton;

    @FindBy(xpath = ("//*[contains(text(), 'Delete credential')]"))
    private WebElement deleteCredentialOption;

    @FindBy(css = "button[data-id='ok']")
    private WebElement confirmOkButton;

    public CredentialsPage clickAddCredentialsButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(addCredentialsButton)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(closeButton));

        return this;
    }

    public String getDialogTitle() {
        return getWait5().until(ExpectedConditions.visibilityOf(dialogTitle)).getText();
    }

    public CredentialsPage createUsernameWithPassword(String user, String pass, String id, String desc) {
        getWait5().until(ExpectedConditions.visibilityOfAllElements(kindOptions)).getFirst().click();
        nextButtons.getFirst().click();
        getWait5().until(ExpectedConditions.visibilityOf(usernameTextField)).sendKeys(user);
        passwordTextField.sendKeys(pass);
        IDField.sendKeys(id);
        descriptionField.sendKeys(desc);

        return this;
    }

    private By getCredentialLocator(String id) {
        return By.xpath(String.format("//*[contains(text(), '%s')]", id));
    }

    public boolean isCredentialVisible(String id) {
        return getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(getCredentialLocator(id)))
                .isDisplayed();
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

    public CredentialsPage clickSecretTextButton() {
        secretTextButton.click();

        return this;
    }

    public CredentialsPage clickSSHCredentialsButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(SSHUsernameWithKey)).click();
        nextButtons.getFirst().click();
        return this;
    }

    public CredentialsPage setSSHCredentials(String id, String desc, String user, Boolean usernameAsSecret, Boolean enterDirectly,String key,String pass ){
        getWait5().until(ExpectedConditions.visibilityOf(usernameTextField)).sendKeys(user);
        IDField.sendKeys(id);
        descriptionField.sendKeys(desc);
        if (usernameAsSecret ==true){
            treatUsernameAsSecretCheckbox.click();}
        passphraseTextField.sendKeys(pass);
        if (enterDirectly ==true){
            enterDirectlyCheckbox.click();
            addButton.click();
            getDriver().findElement(By.xpath("//textarea[contains(@id,\"secretText\")]")).sendKeys(key);
        }
        return this;
    }

    public CredentialsPage clickNextButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(createButton));

        return this;
    }

    public CredentialsPage typeSecretText(String secret) {
        secretTextField.sendKeys(secret);

        return this;
    }

    public CredentialsPage typeID(String ID) {
        IDField.sendKeys(ID);

        return this;
    }

    public CredentialsPage clickCreateButton() {
        createButton.click();

        return this;
    }
}
