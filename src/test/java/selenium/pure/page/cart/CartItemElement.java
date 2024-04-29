package selenium.pure.page.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.pure.page.BasePage;

public class CartItemElement extends BasePage {

    private final WebElement item;

    private final By cartItemName = By.xpath("//*[@data-test='inventory-item-name']");
    private final By cartItemPrice = By.xpath("//*[@data-test='inventory-item-price']");
    private final By itemRemoveFromCartButton = By.xpath("//button[text()='Remove']");

    public CartItemElement(final WebElement element) {
        this.item = element;
    }

    public String getCartItemPrice() {
        String price = item.findElement(cartItemPrice).getText();
        return price;
    }

    public String getCartItemTitle() {
        return item.findElement(cartItemName).getText();
    }

    public void removeItemFromCart() {
        item.findElement(itemRemoveFromCartButton).click();
    }
}
