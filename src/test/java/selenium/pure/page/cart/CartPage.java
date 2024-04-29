package selenium.pure.page.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.pure.page.BasePage;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartPageTitle = By.xpath("//*[@data-test='title'][text()='Your Cart']");
    private final By cartItemList = By.xpath("//*[@data-test='cart-list']");
    private final By cartItem = By.xpath("//*[@data-test='inventory-item']");

    public CartPage() {
        waitForVisibilityOfElementLocated(cartPageTitle);
    }

    public List<WebElement> collectCartItems() {
        return getElement(cartItemList).findElements(cartItem);
    }

    public int getCartItemListSize() {
        return collectCartItems().size();
    }
}
