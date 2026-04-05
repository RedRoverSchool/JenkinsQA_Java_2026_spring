package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
@Ignore
public class WelcomePageTest extends BaseTest {

    @Test
    public void testHomePage() {
        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".empty-state-block >h1")).getText(),
                "Welcome to Jenkins!");
    }

    @Test
    public void testCreateAJobButton() {
        getDriver().findElement(By.xpath("//*[@href = 'newJob']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='add-item-panel']/h1")).getText(),
                "New Item");
    }

    @Test
    public void testNewItemButton() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='add-item-panel']/h1")).getText(),
                "New Item");
    }

    @Test
    public void testBuildHistoryButton() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='main-panel']/div[1]")).getText(),
                "Build History of Jenkins");
    }
    @Ignore
    @Test
    public void testEditDescriptionDashboard() {
        getDriver().findElement(By.xpath("//a[@href = 'editDescription']")).click();

        getDriver().findElement(By.xpath("//textarea[@name = 'description']"))
                .sendKeys("TestDescription");

        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id = 'description-content']")).getText(),
                "TestDescription");
    }

    @Test
    public void testEditDescriptionDashboardPreview() {
        getDriver().findElement(By.xpath("//a[@href = 'editDescription']")).click();

        getDriver().findElement(By.xpath("//textarea[@name = 'description']"))
                .sendKeys("TestDescriptionPreview");


        getDriver().findElement(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']"))
                .click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@class = 'textarea-preview']")).getText(),
                "TestDescriptionPreview");
    }

    @Test
    public void testSetUpAnAgentButton() {
        getDriver().findElement(By.xpath("//a[@href = 'computer/new']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='main-panel']/div/div/h1")).getText(),
                "New node");
    }

    @Test
    public void testConfigureACloudButton() {
        getDriver().findElement(By.xpath("//a[@href='cloud/']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='main-panel']/div/div/div[1]")).getText(),
                "Clouds");
    }

    @Test
    public void testLearnMoreButton() {
        Assert.assertEquals(
                getDriver().findElement(By.xpath(
                        "//a[@href='https://www.jenkins.io/redirect/distributed-builds']")).getText(),
                "Learn more about distributed builds");
    }

    @Test
    public void testRestApiButton() {
        getDriver().findElement(By.xpath("//a[@href = 'api/']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/h1")).getText(),
                "REST API");
    }
    @Test
    public void testManageJenkinsButton() {
        getDriver().findElement(By.xpath("//a[@id = 'root-action-ManageJenkinsAction']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div/div[1]")).getText(),
                "Manage Jenkins");
    }
    @Ignore
    @Test
    public void testSearchButton() {
        getDriver().findElement(By.xpath("//button[@id='root-action-SearchAction']")).click();

        Assert.assertTrue(getDriver().findElement(By.id("command-bar")).isDisplayed());
    }

    @Test
    public void testAboutJenkinsButton() {
        Assert.assertTrue(
        getDriver().findElement(By.xpath("//button[text()='Jenkins 2.541.3']")).isDisplayed());


    }
}
