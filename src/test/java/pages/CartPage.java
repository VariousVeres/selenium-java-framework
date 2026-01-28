package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends ProductListPage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@data-test='cart-contents-container']")
    WebElement cartContentContainer;
    @FindBy(xpath = "//div[@data-test='inventory-item']")
    List<WebElement> cartInventoryItemsList;


    public boolean isShoppingCartOpened() {
        waitForVisibility(cartContentContainer);
        return cartContentContainer.isDisplayed();
    }

    public boolean isProductPresentInCart(String productName) {
        return cartInventoryItemsList.stream()
                .anyMatch(item -> item.findElement(By.xpath(".//*[@data-test='inventory-item-name']")).getText().equals(productName));
    }

    public void removeProductFromCart(String productName) {
        findProductBlock(productName).findElement(By.xpath(".//button[contains(@data-test,'remove')]")).click();
    }

}
