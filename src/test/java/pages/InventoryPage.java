package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends ProductListPage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#inventory_container")
    WebElement inventoryContainer;
    @FindBy(xpath = "//div[@data-test='inventory-item']")
    List<WebElement> inventoryItemsList;

    @FindBy(xpath = "//a[@data-test='shopping-cart-link']")
    WebElement shoppingCartLink;

    public boolean isInventoryContainerPresent() {
        return inventoryContainer.isDisplayed();
    }

    public void addProductToCart(String productName) {
        findProductBlock(productName).findElement(By.xpath(".//*[contains(@data-test,'add-to-cart')]")).click();
    }

    public void clickOnShoppingCartLink() {
        waitForVisibility(shoppingCartLink);
        shoppingCartLink.click();
    }

}
