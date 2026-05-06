package school.redrover;

import school.redrover.common.BaseTest;

public class MultiConfigurationTest extends BaseTest {

    private final static String FREESTYLE = "Freestyle project";
    private final static String PROJECT_NAME = "testProject";
//
//    private BasePage  createProject(){
//        new BasePage(getDriver()).clickNewItem()
//                .waitToLoadCreatePage()
//                .typeProjectName(PROJECT_NAME)
//                .selectItemType(FREESTYLE)
//                .clickOK()
//                .waitToLoadConfigurePage()
//                .goToBasePage();
//
//        return new BasePage(getDriver());
//    }
//
//    @Test
//    public void testTriggerBuildsRemotely(){
//        String token = "testToken";
//
//        createProject()
//                .waitToLoadBasePage()
//                .clickJobDropdownMenu(PROJECT_NAME)
//                .clickConfigureFromDropdownMenu(PROJECT_NAME)
//                .waitToLoadConfigurePage()
//                .clickTriggerButton()
//                .clickTrigger("Trigger builds remotely (e.g., from scripts)")
//                .fillTriggerTokenField(token)
//                .clickSaveButton()
//                .waitToLoadStatusPage()
//                .clickToConfigurePage()
//                .clickTriggerButton();
//
//        String res = getDriver().findElement(By.xpath("//div[@class='hidden-password-legend']")).getText();
//
//        Assert.assertEquals(res, "Concealed");
//    }

}
