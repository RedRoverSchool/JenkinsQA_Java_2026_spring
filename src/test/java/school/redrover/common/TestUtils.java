package school.redrover.common;

import org.openqa.selenium.WebDriver;
import school.redrover.page.*;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.projects.FolderPage;
import school.redrover.page.projectsConfig.*;

public class TestUtils {

    public enum JobType {
        PIPELINE("Pipeline"),
        FREESTYLE("Freestyle project"),
        MULTICONFIGURATION("Multi-configuration project"),
        FOLDER("Folder"),
        MULTIBRANCH_PIPELINE("Multibranch Pipeline"),
        ORGANIZATION_FOLDER("Organization Folder");

        private final String displayName;

        JobType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private static BaseConfigPage getConfigPage(JobType jobType, WebDriver driver) {
        return switch (jobType) {
            case PIPELINE -> new PipelineProjectConfigPage(driver);
            case FREESTYLE -> new FreestyleProjectConfigPage(driver);
            case MULTICONFIGURATION -> new MulticonfigurationConfigPage(driver);
            case FOLDER -> new FolderConfigPage(driver);
            case MULTIBRANCH_PIPELINE -> new MultibranchConfigPage(driver);
            case ORGANIZATION_FOLDER -> new OrganizationFolderConfigPage(driver);
        };
    }

    public static HomePage createJob(WebDriver driver, String projectName, JobType jobType) {
        BaseConfigPage configPage = getConfigPage(jobType, driver);
        return new HomePage(driver)
                .clickItemNewJob()
                .setProjectName(projectName)
                .scrollToTypeOfProject(jobType)
                .selectItemType(jobType)
                .clickOK(configPage)
                .goHomePage();
    }

    public static void createNestedJob(WebDriver driver, String projectName, String childName, JobType jobType) {
        BaseConfigPage configPage = getConfigPage(jobType, driver);
        new HomePage(driver)
                .clickOnProject(new FolderPage(driver), projectName)
                .clickNewItem()
                .setProjectName(childName)
                .scrollToTypeOfProject(jobType)
                .selectItemType(jobType)
                .clickOK(configPage)
                .goHomePage();
    }
}
