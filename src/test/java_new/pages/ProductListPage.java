package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public abstract class ProductListPage extends BasePage1 {

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@data-test='inventory-item']")
    protected List<WebElement> productItems;

    protected WebElement findProductBlock(String productName) {
        return productItems.stream()
                .filter(item ->
                        item.findElement(
                                By.cssSelector("[data-test='inventory-item-name']")
                        ).getText().equals(productName)
                )
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Product not found: " + productName)
                );
    }
}
