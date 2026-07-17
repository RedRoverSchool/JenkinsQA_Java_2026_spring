package school.redrover.cucumber;

public class TestContext {
    private Object currentPage;

    @SuppressWarnings("unchecked")
    public <T> T getCurrentPage() {
        return (T) currentPage;
    }

    public void setCurrentPage(Object page) {
        this.currentPage = page;
    }
}