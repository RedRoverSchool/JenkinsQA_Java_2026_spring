package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class NodeTest extends BaseTest {

    private static final String NEW_NODE_NAME = "New Test Node";
    private static final String DESCRIPTION = "Use only for urgent tasks";
    private static final String DIR = "D:\\Jenkins\\NewTestNode";
    private static final String LABELS = "Urgent";

    @Test
    public void testCreateNewNode(){

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='computer']")).click();
        getDriver().findElement(By.xpath("//a[@href='new']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_NODE_NAME);
        getDriver().findElement(By.className("jenkins-radio__label")).click();
        getDriver().findElement(By.xpath("//button[@value='Create']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.xpath("//button[@value='Save']")))).click();

        List<String> actualNodeList = getDriver().findElements(By
                        .xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertTrue(actualNodeList.contains(NEW_NODE_NAME));
    }

    @Test (dependsOnMethods = "testCreateNewNode")
    public void testNodeConfiguration(){

        List<String> expectAttributes= new ArrayList<>(List.of(DESCRIPTION, LABELS));
        List<String> actualAttributes= new ArrayList<>();

        goToNewNodeManagementPage();

        getDriver().findElement(By.xpath("//a[@href='/computer/%s/configure']"
                .formatted(NEW_NODE_NAME.replace(" ", "%20")))).click();
        getDriver().findElement(By.xpath("//textarea[@name='nodeDescription']")).sendKeys(DESCRIPTION);
        getDriver().findElement(By.xpath("//input[@name='_.remoteFS']")).sendKeys(DIR);
        getDriver().findElement(By.xpath("//input[@name='_.labelString']")).sendKeys(LABELS);
        getDriver().findElement(By.xpath("//select[@name='mode']")).click();
        getDriver().findElement(By.xpath("//option[@value='EXCLUSIVE']")).click();
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        actualAttributes.add(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")))
                .getText());

        actualAttributes.add(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/label/%s']".formatted(LABELS)))).getText());

        Assert.assertEquals(actualAttributes, expectAttributes);
    }

    @Test (dependsOnMethods = "testCreateNewNode")
    public void testMarkNodeOffline(){
        goToNewNodeManagementPage();

        getDriver().findElement(By.xpath("//form [@action='markOffline']")).click();
        WebElement submitButton = getDriver().findElement(By.xpath("//*[@id='main-panel']/form/p/button"));
        getWait5().until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        Assert.assertEquals(getDriver().findElement(By.className("message")).getText(), "Disconnected by admin");
    }

    @Test (dependsOnMethods = "testMarkNodeOffline")
    public void testBringTheNodeBackOnline(){
        goToNewNodeManagementPage();

        WebElement submitButton = getDriver().findElement(By.className("jenkins-button--primary"));
        submitButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//form [@action='markOffline']")).getText()
                ,"Mark this node temporarily offline");
    }

    @Test (dependsOnMethods = "testCreateNewNode")
    public void testDeleteNode(){

        goToNewNodeManagementPage();

        getDriver().findElement(By.className("icon-edit-delete")).click();
        getDriver().findElement(By.xpath("//button [@data-id='ok']")).click();

        List<String> actualNodeList = getDriver().findElements(By
                        .xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertFalse(actualNodeList.contains(NEW_NODE_NAME));
    }

    private void goToNewNodeManagementPage(){
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='computer']")).click();
        getDriver().findElement(By.xpath("//a[@href='../computer/%s/']"
                .formatted(NEW_NODE_NAME.replace(" ", "%20")))).click();
    }
}
