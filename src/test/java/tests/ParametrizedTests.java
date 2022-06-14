package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametrizedTests {
    @Test(priority = 1)
    @Parameters({"value1", "value2"})
    public void sum(int v1, int v2) {
        int finalsum = v1 + v2;
        System.out.println("The final sum of the given values is " + finalsum);
    }

    @Test(priority = 2)
    @Parameters({"Message1"})
    public void optional(@Optional("Optional Parameter Selected") String message) {
        System.out.println(message);
    }

    @DataProvider(name = "data-provider-1")
    public Object[][] dpMethod1() {
        return new Object[][]{{"First-Value"}, {"Second-Value"}};
    }

    @Test(dataProvider = "data-provider-1", priority =3)
    public void myTest(String val) {
        System.out.println("Passed Parameter Is : " + val);
    }

    @DataProvider(name = "data-provider-2")
    public Object[][] dpMethod2() {
        return new Object[][]{{1}, {2}, {3}, {4}};
    }

    @Test(dataProvider = "data-provider-2", priority =4)
    public void myTest(int val) {
        System.out.println("Passed Parameter Is : " + val);
    }


}
