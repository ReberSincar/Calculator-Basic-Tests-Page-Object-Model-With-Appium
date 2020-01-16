package tests;

import org.junit.Test;
import org.springframework.core.annotation.Order;
import pages.BasicScreen;

public class BasicTests {
    BasicScreen basicScreenPage = new BasicScreen();

    public BasicTests() throws Exception {
    }

    @Test
    @Order(1)
    public void MultiplyTest() throws Exception {
        basicScreenPage.multiplyOperation();
    }

    @Test
    @Order(2)
    public void MultiplyTestWithPoint() throws Exception {
        basicScreenPage.multiplyTestWithPoint();
    }

    @Test
    @Order(3)
    public void AddTest() throws Exception {
        basicScreenPage.addOperation();
    }

    @Test
    @Order(4)
    public void AddTestWithPoint() throws Exception {
        basicScreenPage.addTestWithPoint();
    }

    @Test
    @Order(5)
    public void SubTest() throws Exception {
        basicScreenPage.subOperation();
    }

    @Test
    @Order(6)
    public void SubTestWithPoint() throws Exception {
        basicScreenPage.subTestWithPoint();
    }

    @Test
    @Order(7)
    public void DivTest() throws Exception {
        basicScreenPage.divOperation();
    }

    @Test
    @Order(8)
    public void DivTestWithPoint() throws Exception {
        basicScreenPage.divTestWithPoint();
    }

    @Test
    @Order(9)
    public void PercentTest() throws Exception {
        basicScreenPage.percentOperation();
    }

    @Test
    @Order(10)
    public void PercentTestWithPoint() throws Exception {
        basicScreenPage.percentTestWithPoint();
    }
}
