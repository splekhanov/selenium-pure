package selenium.pure.page.cart;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.pure.page.BasePage;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartPageTitle = By.xpath("//*[@data-test='title'][text()='Your Cart']");
    private final By cartItemList = By.xpath("//*[@data-test='cart-list']");
    private final By cartItem = By.xpath("//*[@data-test='inventory-item']");
    private final By itemTitle = By.xpath("//*[@data-test='inventory-item-name']");

    public CartPage() {
        waitForVisibilityOfElementLocated(cartPageTitle);
    }

    private List<WebElement> collectCartItems() {
        return getElement(cartItemList).findElements(cartItem);
    }

    @Step("Getting cart item with title {0}")
    public CartItemElement getCartItemByTitle(String itemTitle) {
        List<WebElement> items = collectCartItems();
        CartItemElement cartItemElement;
        int itemsAmount;

        if(items.isEmpty()) {
            throw new RuntimeException("Couldn't found any items. Inventory item list is empty");
        } else {
            itemsAmount = items.size();
        }

        for (int i = 0; i <= itemsAmount; i++) {
            String t = items.get(i).findElement(this.itemTitle).getText();
            if(t.equals(itemTitle)) {
                cartItemElement = new CartItemElement(items.get(i));
                return cartItemElement;
            }
        }
        throw new RuntimeException("Couldn't found item with title " + itemTitle);
    }

    public int getCartItemListSize() {
        return collectCartItems().size();
    }
}
