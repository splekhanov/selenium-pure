package selenium.pure.page.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.pure.page.BasePage;

public class InventoryItemElement extends BasePage {

    private final WebElement item;

    private final By itemPrice = By.xpath("//*[@data-test='inventory-item-price']");
    private final By itemAddToCartButton = By.xpath("//button[text()='Add to cart']");
    private final By itemRemoveFromCartButton = By.xpath("//button[text()='Remove']");

    public InventoryItemElement(final WebElement element) {
        this.item = element;
    }

    public String getItemPrice() {
        return item.findElement(itemPrice).getText();
    }

    public void addItemToCart() {
        item.findElement(itemAddToCartButton).click();
        waitForElementVisible(item.findElement(itemRemoveFromCartButton));
    }
}
