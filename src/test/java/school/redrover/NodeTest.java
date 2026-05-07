package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.ArrayList;
import java.util.List;

public class NodeTest extends BaseTest {

    private static final String NEW_NODE_NAME = "New Test Node";
    private static final String DESCRIPTION = "Use only for urgent tasks";
    private static final String DIR = "D:\\Jenkins\\NewTestNode";
    private static final String LABELS = "Urgent";
    private static final String USAGE = "EXCLUSIVE";

    @Test
    public void testCreateNewNode(){

        List<String> nodesList = new HomePage(getDriver())
                .clickManageButton()
                .goToNodes()
                .createNewNode(NEW_NODE_NAME)
                .getNodesList();

        Assert.assertTrue(nodesList.contains(NEW_NODE_NAME));
    }

    @Test (dependsOnMethods = "testCreateNewNode")
    public void testNodeConfiguration(){

        List<String> expectAttributes = new ArrayList<>(List.of(DESCRIPTION, LABELS));

        List<String> actualAttributes = new HomePage(getDriver())
                .clickManageButton()
                .goToNodes()
                .goToNodeManagementPage(NEW_NODE_NAME)
                .goToNodeConfigPage(NEW_NODE_NAME)
                .changeDescription(DESCRIPTION)
                .changeDir(DIR)
                .changeLabel(LABELS)
                .changeUsage(USAGE)
                .saveChanges()
                .getConfigDescriptionList(LABELS);

        Assert.assertEquals(actualAttributes, expectAttributes);
    }

    @Test (dependsOnMethods = "testNodeConfiguration")
    public void testMarkNodeOffline(){
        boolean isNodeOffline = new HomePage(getDriver())
                .clickManageButton()
                .goToNodes()
                .goToNodeManagementPage(NEW_NODE_NAME)
                .markNodeOffline()
                .isNodeOffline();

        Assert.assertTrue(isNodeOffline);
    }

    @Test (dependsOnMethods = "testMarkNodeOffline")
    public void testBringTheNodeBackOnline(){
        boolean isNodeOnline = new HomePage(getDriver())
                .clickManageButton()
                .goToNodes()
                .goToNodeManagementPage(NEW_NODE_NAME)
                .bringNodeBackOnline()
                .isNodeOnline();

        Assert.assertTrue(isNodeOnline);
    }

    @Test (dependsOnMethods = "testBringTheNodeBackOnline")
    public void testDeleteNode(){

        List<String> actualNodeList = new HomePage(getDriver())
                .clickManageButton()
                .goToNodes()
                .goToNodeManagementPage(NEW_NODE_NAME)
                .deleteNode()
                .getNodesList();

        Assert.assertFalse(actualNodeList.contains(NEW_NODE_NAME));
    }
}
