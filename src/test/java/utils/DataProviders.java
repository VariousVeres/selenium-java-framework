package utils;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "product-names")
    public Object[][] productNamesSetForOrderScenario(){
        return new Object[][] {{"Carrot Juice"}, {"Apple Pomace"}, {"Banana Juice"}};
    }
}
