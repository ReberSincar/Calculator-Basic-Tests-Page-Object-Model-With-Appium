package tests;

import org.junit.Test;
import pages.BasicScreen;

public class BasicTests {
    BasicScreen basicScreenPage = new BasicScreen();

    public BasicTests() throws Exception {
    }

    @Test
    public void MultiplyTest() throws Exception {
        basicScreenPage.multiplyOperation();
    }

    @Test
    public void MultiplyTestWithPoint() throws Exception {
        basicScreenPage.multiplyTestWithPoint();
    }

    @Test
    public void AddTest() throws Exception {
        basicScreenPage.addOperation();
    }

    @Test
    public void AddTestWithPoint() throws Exception {
        basicScreenPage.addTestWithPoint();
    }

    @Test
    public void SubTest() throws Exception {
        basicScreenPage.subOperation();
    }

    @Test
    public void SubTestWithPoint() throws Exception {
        basicScreenPage.subTestWithPoint();
    }

    @Test
    public void DivTest() throws Exception {
        basicScreenPage.divOperation();
    }

    @Test
    public void DivTestWithPoint() throws Exception {
        basicScreenPage.divTestWithPoint();
    }

    @Test
    public void PercentTest() throws Exception {
        basicScreenPage.percentOperation();
    }

    @Test
    public void PercentTestWithPoint() throws Exception {
        basicScreenPage.percentTestWithPoint();
    }
}
