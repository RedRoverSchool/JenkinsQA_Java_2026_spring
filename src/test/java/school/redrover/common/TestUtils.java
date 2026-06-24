package school.redrover.common;

import org.openqa.selenium.WebDriver;
import school.redrover.page.*;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.project.config.*;

public class TestUtils {

    public enum JobType {
        PIPELINE("Pipeline") {
            @Override
            public BaseConfigPage<?> getConfigPage(WebDriver driver) {
                return new PipelineProjectConfigPage(driver);
            }
        },
        FREESTYLE("Freestyle project") {
            @Override
            public BaseConfigPage<?> getConfigPage(WebDriver driver) {
                return new FreestyleProjectConfigPage(driver);
            }
        },
        MULTICONFIGURATION("Multi-configuration project") {
            @Override
            public BaseConfigPage<?> getConfigPage(WebDriver driver) {
                return new MulticonfigurationConfigPage(driver);
            }
        },
        FOLDER("Folder") {
            @Override
            public BaseConfigPage<?> getConfigPage(WebDriver driver) {
                return new FolderConfigPage(driver);
            }
        },
        MULTIBRANCH_PIPELINE("Multibranch Pipeline") {
            @Override
            public BaseConfigPage<?> getConfigPage(WebDriver driver) {
                return new MultibranchConfigPage(driver);
            }
        },
        ORGANIZATION_FOLDER("Organization Folder") {
            @Override
            public BaseConfigPage<?> getConfigPage(WebDriver driver) {
                return new OrganizationFolderConfigPage(driver);
            }
        };

        private final String displayName;

        JobType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public abstract BaseConfigPage<?> getConfigPage(WebDriver driver);
    }

    public static HomePage createJob(WebDriver driver, String projectName, JobType jobType) {
        return new HomePage(driver)
                .clickItemNewJob()
                .setProjectName(projectName)
                .scrollToTypeOfProject(jobType)
                .selectItemType(jobType)
                .clickOK(jobType.getConfigPage(driver))
                .goHomePage();
    }
}
