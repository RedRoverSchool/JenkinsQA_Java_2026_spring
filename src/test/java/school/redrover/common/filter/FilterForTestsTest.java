package school.redrover.common.filter;

import org.testng.Assert;
import org.testng.IMethodInstance;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class FilterForTestsTest {

    private static class FakeTestClass {
    }

    private static class FakeTestClass2 {
    }

    @Test
    public void testDeletedClass() {
        List<String> fileList = List.of("D=src/test/java/FileTest.java");
        String dependenciesClasses = "";
        List<IMethodInstance> methodList = List.of(new FilterMock.MethodInstanceImpl(FilterForTestsTest.class));

        List<IMethodInstance> resultList = FilterUtils.filterMethods(fileList, dependenciesClasses, methodList);

        Assert.assertEquals(resultList.size(), 0);
    }

    @Test
    public void testNoChangesClass() {
        List<String> fileList = List.of();
        String dependencies = "";
        List<IMethodInstance> methodList = List.of(new FilterMock.MethodInstanceImpl(FilterForTestsTest.class));

        List<IMethodInstance> resultList = FilterUtils.filterMethods(fileList, dependencies, methodList);

        Assert.assertEquals(resultList.size(), 0);
    }

    @Test
    public void testAddNewTest() {
        List<String> fileList = List.of("A=src/test/java/school/redrover/ExampleTest.java");
        String dependencies = "";
        List<IMethodInstance> methodList = List.of(new FilterMock.MethodInstanceImpl(FilterForTestsTest.class));

        List<IMethodInstance> resultList = FilterUtils.filterMethods(fileList, dependencies, methodList);

        Assert.assertEquals(resultList.size(), 1);
    }

    @Test
    public void tesRenameTest() {
        List<String> fileList = List.of("R=src/test/java/school/redrover/ExampleTest.java");
        String dependencies = "";
        List<IMethodInstance> methodList = List.of(new FilterMock.MethodInstanceImpl(FilterForTestsTest.class));

        List<IMethodInstance> resultList = FilterUtils.filterMethods(fileList, dependencies, methodList);

        Assert.assertEquals(resultList.size(), 1);
    }

    @Test
    public void testModifiedTest() {
        List<String> fileList = List.of("M=src/test/java/ExampleTest.java");
        String dependencies = "";
        List<IMethodInstance> methodList = List.of(new FilterMock.MethodInstanceImpl(FilterForTestsTest.class));

        List<IMethodInstance> resultList = FilterUtils.filterMethods(fileList, dependencies, methodList);

        Assert.assertEquals(resultList.size(), 1);
    }

    @Test
    public void testClassWithDependencies() {
        List<String> changedFiles = List.of("M=src/test/java/school/redrover/page/BaseProjectStatusPage.java");
        String dependenciesClasses =
                "school.redrover.ui.page.BaseProjectStatusPage=%s;".formatted(FakeTestClass.class.getName()) +
                        "school.redrover.ui.page.BaseProjectStatusPage=%s".formatted(FakeTestClass2.class.getName());

        List<IMethodInstance> methodList = List.of(
                new FilterMock.MethodInstanceImpl(FakeTestClass.class),
                new FilterMock.MethodInstanceImpl(FakeTestClass2.class)
        );

        List<IMethodInstance> resultList = FilterUtils.filterMethods(changedFiles, dependenciesClasses, methodList);

        List<Class<?>> resultClasses = resultList.stream()
                .map(m -> m.getMethod().getTestClass().getRealClass())
                .collect(Collectors.toList());

        Assert.assertEquals(resultList.size(), 2);
        Assert.assertTrue(resultClasses.contains(FakeTestClass.class));
        Assert.assertTrue(resultClasses.contains(FakeTestClass2.class));
    }

    @Test
    public void testOneDependency() {
        List<String> changedFiles = List.of("M=src/test/java/school/redrover/page/ProjectStatusPage.java");
        String dependenciesClasses =
                "school.redrover.ui.page.ProjectStatusPage=%s;".formatted(FakeTestClass.class.getName());

        List<IMethodInstance> methodList = List.of(new FilterMock.MethodInstanceImpl(FakeTestClass.class));

        List<IMethodInstance> resultList = FilterUtils.filterMethods(changedFiles, dependenciesClasses, methodList);

        List<Class<?>> resultClasses = resultList.stream()
                .map(m -> m.getMethod().getTestClass().getRealClass())
                .collect(Collectors.toList());

        Assert.assertEquals(resultList.size(), 1);
        Assert.assertTrue(resultClasses.contains(FakeTestClass.class));
    }

    @Test
    public void testClassWithDependencyChain() {
        List<String> changedFiles = List.of("M=src/test/java/school/redrover/page/BaseProjectStatusPage.java");
        String dependenciesClasses =
                        "school.redrover.ui.page.BaseProjectStatusPage=school.redrover.ui.page.ProjectStatusPage" +
                        "school.redrover.ui.page.ProjectStatusPage=%s".formatted(FakeTestClass.class.getName());

        List<IMethodInstance> methodList = List.of(
                new FilterMock.MethodInstanceImpl(FakeTestClass.class)
        );

        List<IMethodInstance> resultList = FilterUtils.filterMethods(changedFiles, dependenciesClasses, methodList);

        List<Class<?>> resultClasses = resultList.stream()
                .map(m -> m.getMethod().getTestClass().getRealClass())
                .collect(Collectors.toList());

        Assert.assertEquals(resultList.size(), 1);
        Assert.assertTrue(resultClasses.contains(FakeTestClass.class));
    }

}
