package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.manage.CredentialsPage;

import java.util.List;

public class AddCredentialsPage extends BasePage {

    public AddCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".jenkins-choice-list__item__label")
    private List<WebElement> kindOptions;

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

    @FindBy(xpath = "//div[@class='jenkins-choice-list__item__label'][contains(text(),'SSH')]" )
    private WebElement SSHUsernameWithKey;

    @FindBy(xpath = "//div[@class='jenkins-choice-list__item__label'][text()='Secret text']")
    private WebElement secretTextButton;

    @FindBy(name = "_.secret")
    private WebElement secretTextField;

    @FindBy(css = ".jenkins-dialog__title")
    private WebElement dialogTitle;

    @FindBy(id = "cr-dialog-submit")
    private WebElement createButton;

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextButton;

    public AddCredentialsPage createUsernameWithPassword(String user, String pass, String id, String desc) {
        getWait5().until(ExpectedConditions.visibilityOfAllElements(kindOptions)).getFirst().click();
        nextButton.click();
        getWait5().until(ExpectedConditions.visibilityOf(usernameTextField)).sendKeys(user);
        passwordTextField.sendKeys(pass);
        IDField.sendKeys(id);
        descriptionField.sendKeys(desc);

        return this;
    }

    public String getDialogTitle() {
        return getWait5().until(ExpectedConditions.visibilityOf(dialogTitle)).getText();
    }

    public AddCredentialsPage clickSecretTextButton() {
        secretTextButton.click();
        return this;
    }

    public AddCredentialsPage clickSSHCredentialsButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(SSHUsernameWithKey)).click();
        nextButton.click();
        return this;
    }

    public AddCredentialsPage setSSHCredentials(String id, String desc, String user, Boolean usernameAsSecret, Boolean enterDirectly,String key,String pass ){
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

    public AddCredentialsPage typeID(String ID) {
        IDField.sendKeys(ID);
        return this;
    }

    public AddCredentialsPage typeSecretText(String secret) {
        secretTextField.sendKeys(secret);
        return this;
    }

    public AddCredentialsPage clickNextButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(createButton));

        return this;
    }

    public CredentialsPage clickCreateButton() {
        createButton.click();
        return new CredentialsPage(getDriver());
    }
}
