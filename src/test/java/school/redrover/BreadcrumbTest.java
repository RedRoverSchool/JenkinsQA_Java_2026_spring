package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.util.List;

public class BreadcrumbTest extends BaseTest {

    public static final String FOLDER_PARENT = "FolderParent";
    public static final String FOLDER_CHILD = "FolderChild";

    private void goToMainPage() {
        WebElement logo = getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.app-jenkins-logo")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/all/newJob']"))).isDisplayed();
    }

    @Test
    public void testNavigateToParentFolder() {
        TestUtils.createJob(getDriver(), getWait10(), FOLDER_PARENT, TestUtils.JobType.FOLDER);
        goToMainPage();
        TestUtils.createNestedJob(getDriver(), getWait10(), FOLDER_PARENT, FOLDER_CHILD, TestUtils.JobType.FOLDER);
        goToMainPage();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_PARENT)))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_CHILD)))).click();

        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.className("job-index-headline"), FOLDER_CHILD));
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']/a[@href='/job/%s/']".formatted(FOLDER_PARENT)))).click();

        Assert.assertEquals(getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("job-index-headline"))).getText(), FOLDER_PARENT);
    }

    @Test(dependsOnMethods = "testNavigateToParentFolder")
    public void testNavigateToParentConfigPage() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_PARENT)))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_CHILD)))).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.className("job-index-headline"), FOLDER_CHILD));

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/job/%s/')]/following-sibling::div[@class='dropdown-indicator']".formatted(FOLDER_PARENT)))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='jenkins-dropdown__item ' and contains(@href, '/job/%s/configure')]".formatted(FOLDER_PARENT)))).click();

        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Configuration"));

        List<String> actualBreadcrumbs = getWait10()
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#breadcrumbs .jenkins-breadcrumbs__list-item")))
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .toList();

        Assert.assertEquals(actualBreadcrumbs, List.of(FOLDER_PARENT, "Configuration"), "Breadcrumbs sequence is wrong!");

    }
}

