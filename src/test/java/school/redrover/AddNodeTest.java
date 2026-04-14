package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.util.List;

public class AddNodeTest extends BaseTest {
    private static final String NEV_NODE_NAME = "SecondNode";
    private static final String NODE_DESCRIPTION = "In a linked list: each node points to the next node.In a tree: nodes connect like branches";
    private static final String LABEL = "New Node";

    private final By settingsButton = By.id("root-action-ManageJenkinsAction");
    private final By nodesPage = By.xpath("//div[@id='main-panel']/section[2]/div/div[4]/a/div");
    private final By newNode = By.xpath("//div[@id='main-panel']/div[1]/div[2]/a[1]");
    private final By nodeName = By.id("name");
    private final By permanentAgent = By.xpath("//form[@id='createItemForm']//label");
    private final By creat = By.id("ok");
    private final By description = By.xpath("//div[@id='main-panel']/form//textarea");
    private final By numberOfExecutor = By.xpath("//form/div[1]/div[3]/div[2]/input");
    private final By label = By.xpath("//form/div[1]/div[5]/div[2]/input");
    private final By saveButton = By.xpath("//div[@id='bottom-sticker']//button");

    @Test
    public void testAddNewNode() {
        getDriver().findElement(settingsButton).click();
        getDriver().findElement(nodesPage).click();
        getDriver().findElement(newNode).click();
        getDriver().findElement(nodeName).sendKeys(NEV_NODE_NAME);
        getDriver().findElement(permanentAgent).click();
        getDriver().findElement(creat).click();

        getDriver().findElement(description).sendKeys(NODE_DESCRIPTION);
        getDriver().findElement(numberOfExecutor).clear();
        getDriver().findElement(numberOfExecutor).sendKeys("3");
        getDriver().findElement(label).sendKeys(LABEL);

        getDriver().findElement(saveButton).click();

        List<String> NodeNamesList = getDriver().findElements(By.xpath("//tbody//td[2]/a"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertTrue(NodeNamesList.size() > 1);
        Assert.assertTrue(NodeNamesList.contains(NEV_NODE_NAME));
    }
}
