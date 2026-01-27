package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage1{
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#inventory_container")
    WebElement inventoryContainer;

    public boolean isInventoryContainerPresent()  {
        return inventoryContainer.isDisplayed();
    }

}
